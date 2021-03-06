import photoReducer, {
  addPhoto,
  initialCompleted,
  initialPagedPhoto,
  initialPhoto,
  modifyPhoto,
  PhotoPage,
  removePhoto,
} from "./photoSlice";
import { createAction, nanoid, PayloadAction } from "@reduxjs/toolkit";
import { PhotoItem } from "./photoSlice";
import {
  call,
  put,
  select,
  takeEvery,
  takeLatest,
} from "@redux-saga/core/effects";
import api, {
  PhotoItemRequest,
  PhotoItemResponse,
  PhotoPagingReponse,
} from "./photoApi";
import { AxiosResponse } from "axios";
import {
  endProgress,
  startProgress,
} from "../../components/progress/progressSlice";
import { addAlert } from "../../components/alert/alertSlice";
import { RootState } from "../../store";

/* ------------- saga action을 생성하는 부분 -------------- */

export interface PageRequest {
  page: number;
  size: number;
}

/* ------------- saga action을 생성하는 부분 -------------- */

// photo를 추가하도록 요청하는 action -----  : action은 type과 payload로 구성되어있음
// {type, payload}
// {type: "photo/requestAddPhoto", payload: {title, photoUrl...}}

// photo를 추가하도록 요청하는 action creator를 생성 (action만 따로 만듬)
//       : action 객체를 반환하는 것이 action creator

//----------action creator 호출
// const item : photoItem = {title, photoUrl}
// const sagaAction = requestAddPhoto(item);

//----------생성된 action 객체
// sagaAction > {type: "photo/requestAddPhoto", payload: {title, photoUrl...}}

//----------생성된 action 객체를 dispatch
// dispatch(sagaAction);

// photo를 추가하도록 요청하는 action creator를 생성
// createAction<Payload타입>(Action.type)
export const requestAddPhoto = createAction<PhotoItem>( // 3)
  //  액션의 범위 지정 / 액션
  `${photoReducer.name}/requestAddPhoto`
);

// photo를 가져오는 action
//                 함수             action creator
export const requestFetchPhotos = createAction(
  `${photoReducer.name}/requestFetchPhotos`
);

// photo를 페이징으로 가져오는 action
//                                      action만듬  <action의 payload 타입이 PageRequest>
export const requestFetchPagingPhotos = createAction<PageRequest>(
  `${photoReducer.name}/requestFetchPagingPhotos`
);

// photo를 삭제하는 action                      id 값
//                             액션을 만듬, <액션의 payload값이 number임>
export const requestRemovePhoto = createAction<number>(
  `${photoReducer.name}/requestRemovePhoto`
);

// photo를 수정하는 action
export const requestModifyPhoto = createAction<PhotoItem>(
  `${photoReducer.name}/requestModifyPhoto`
);

/* ------------- saga action을 처리하는 부분 -------------- */

// 서버에 POST로 데이터를 보내 추가하고, redux state를 변경
function* addData(action: PayloadAction<PhotoItem>) {
  yield console.log("--addData--");
  yield console.log(action);

  try {
    // action의 payload로 넘어온 객체
    const photoItemPayload = action.payload;

    // rest api 로 보낼 요청객체
    const photoItemRequest: PhotoItemRequest = {
      title: photoItemPayload.title,
      description: photoItemPayload.description,
      photoUrl: photoItemPayload.photoUrl,
      fileType: photoItemPayload.fileType,
      fileName: photoItemPayload.fileName,
    };

    //===========1. rest api에 post 데이터 보냄
    // 함수를 호출함(비동기함수)
    // call(비동기함수, 매개변수1, 매개변수2...)  -> 함수를 호출함
    // 함수가 Promise를 반환하면, (비동기함수)
    // saga 미들웨어에서 현제 yield에 대기상태로 있음
    // promise가 resolve(처리완료)되면 다음 yield로 처리가 진행됨
    // reject(거부/에러)되면 예외를 던짐 (throw) -> try... catch 문으로 받을 수 있음

    // spinner 보여주기
    yield put(startProgress());

    // await api.add(photoItemRequest) 이 구문과 일치함
    // 결과값 형식을 지정해야함  : result
    const result: AxiosResponse<PhotoItemResponse> = yield call(
      api.add,
      photoItemRequest
    );

    // spinner 사라지게 하기
    yield put(endProgress());

    //===========2. redux state를 변경함

    // 2021-09-28 페이징 처리 추가 로직
    const photoData: PhotoItem[] = yield select(
      (state: RootState) => state.photo.data
    );

    // 현재 데이터가 있으면  ????
    if (photoData.length > 0) {
      // 가장 마지막 요소의 id값을 가져오고 삭제함  ??????
      const deleteId = photoData[photoData.length - 1].id;
      yield put(removePhoto(deleteId));
    }

    // 백엔드에서 처리한 데이터 객체로 state를 변경할 payload객체를 생성
    const photoItem: PhotoItem = {
      id: result.data.id,
      title: result.data.title,
      description: result.data.description,
      photoUrl: result.data.photoUrl,
      fileType: result.data.fileType,
      fileName: result.data.fileName,
      createdTime: result.data.createdTime,
    };

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "success", message: "저장되었습니다." })
    );

    // dispatcher(action)과 동일함
    // useDispatch로 dispatcher 만든 것은 컴포넌트에서만 가능
    // put 이펙트를 사용함
    yield put(addPhoto(photoItem));

    // completed 속성 삭제
    yield put(initialCompleted());

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "success", message: "저장되었습니다." })
    );
  } catch (e: any) {
    // 에러발생

    // spinner 사라지게 하기
    yield put(endProgress());

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "danger", message: e.message })
    );
  }
}

// Redux 사이드 이펙트
// 1. api 연동
// 2. 파일처리
// 3. 처리중 메시지 보여주기/감추기
// 4. 에러메시지 띄우기
// 서버에서 GET으로 데이터를 가져오고, redux state를 초기화
function* fetchData() {
  // 3)
  yield console.log("--fetchData--");

  // spinner 보여주기
  yield put(startProgress());

  try {
    // 백엔드에서 데이터 받아오기
    const result: AxiosResponse<PhotoItemResponse[]> = yield call(api.fetch);

    // spinner 사라지게 하기
    yield put(endProgress());

    // PhotoItemResonse[] => PhotoItem[]
    // 서버에서 받아온 배열(응답데이터)을 state에 넣을 수 있는 payload배열로 변환과정
    const photos = result.data.map(
      (item) =>
        ({
          id: item.id,
          title: item.title,
          description: item.description,
          photoUrl: item.photoUrl,
          fileType: item.fileType,
          fileName: item.fileName,
          createdTime: item.createdTime,
        } as PhotoItem)
    );

    // state 초기화 reducer실행
    yield put(initialPhoto(photos)); // 4)
  } catch (e: any) {
    // spinner 사라지게 하기
    yield put(endProgress());

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "danger", message: e.message })
    );
  }
}

function* fetchPagingData(action: PayloadAction<PageRequest>) {
  yield console.log("--fetchPagingData--");

  const page = action.payload.page;
  const size = action.payload.size;

  // ????????? 이해안됨
  localStorage.setItem("photo_page_size", size.toString());

  // spinner 보여주기
  yield put(startProgress());

  try {
    // 백엔드에서 데이터 받아오기 ,   rest api 연동
    const result: AxiosResponse<PhotoPagingReponse> = yield call(
      // api
      api.fetchPaging, // api
      page,
      size
    );

    // spinner 사라지게 하기
    yield put(endProgress());

    // 받아온 페이지 데이터를 Payload 변수로 변환
    const photoPage: PhotoPage = {
      // slice
      // 응답데이터배열을 액션페이로드배열로 변환
      // PhotoItemReponse[] => PhotoItem[]
      data: result.data.content.map(
        (item) =>
          ({
            // 사진 정보 객체 == data
            id: item.id,
            title: item.title,
            description: item.description,
            photoUrl: item.photoUrl,
            fileType: item.fileType,
            fileName: item.fileName,
            createdTime: item.createdTime,
          } as PhotoItem)
      ),
      // 딸려온 페이지에 대한 정보
      totalElements: result.data.totalElements, // DB에 저장되어있는 총 photo의 개수  / 6
      totalPages: result.data.totalPages, // 총 페이지 수  / 3
      page: result.data.number, // 현재 페이지, 배열[]  / 0
      pageSize: result.data.size, // 1개 페이지안에서 게시글 개수 (포토 개수 몇개 들어가) /2
      isLast: result.data.last, // 마지막 게시물(사진)인지? *마지막 페이지면 true, 마지막 아니면 false
      // 마지막 페이지에 마지막 사진이 있음, 결국 같은말
    };

    // state 초기화 reducer 실행
    // photoPage객체를 dispatch
    yield put(initialPagedPhoto(photoPage)); // slice
  } catch (e: any) {
    // 에러발생
    // spinner 사라지게 하기
    yield put(endProgress());

    // alert박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "danger", message: e.message })
    );
  }
}

//            (액션객체: payload가 있는 액션, <payload의 타입이 number - 제너릭타입>)
function* removeData(action: PayloadAction<number>) {
  yield console.log("--removeData--");

  // payload값이 id값이다
  const id = action.payload;

  // spinner 보여주기
  yield put(startProgress());

  // rest api 연동
  const result: AxiosResponse<boolean> = yield call(api.remove, id);

  // spinner 사라지게 하기
  yield put(endProgress());

  // 반환값이 true이면
  if (result.data) {
    // state 변경 - 1건 삭제
    yield put(removePhoto(id));
  } else {
    // alert박스를 추가해줌
    yield put(
      addAlert({
        id: nanoid(),
        variant: "danger",
        message: "오류로 저장되지 않았습니다.",
      })
    );
  }

  // completed 속성 삭제
  yield put(initialCompleted());

  // ??????????????????????????????????
  // 현재 페이지 데이터를 다시 가져옴
  // 현재 페이지와 사이즈 값을 읽어옴
  const page: number = yield select((state: RootState) => state.photo.page);
  const size: number = yield select((state: RootState) => state.photo.pageSize);

  yield put(requestFetchPagingPhotos({ page, size }));
}

function* modifyData(action: PayloadAction<PhotoItem>) {
  yield console.log("--modifyData--");

  // action의 payload로 넘어온 객체
  const photoItemPayload = action.payload;

  // rest api로 보낼 요청객체
  const photoItemRequest: PhotoItemRequest = {
    title: photoItemPayload.title,
    description: photoItemPayload.description,
    photoUrl: photoItemPayload.photoUrl,
    fileType: photoItemPayload.fileType,
    fileName: photoItemPayload.fileName,
  };

  // spinner 보여주기
  yield put(startProgress());

  // rest api 연동
  const result: AxiosResponse<PhotoItemResponse> = yield call(
    api.modify,
    photoItemPayload.id,
    photoItemRequest
  );

  // spinner 사라지게 하기
  yield put(endProgress());

  // 백엔드에서 처리한 데이터 객체로 state를 변경할 payload객체를 생성
  const photoItem: PhotoItem = {
    id: result.data.id,
    title: result.data.title,
    description: result.data.description,
    photoUrl: result.data.photoUrl,
    fileType: result.data.fileType,
    fileName: result.data.fileName,
    createdTime: result.data.createdTime,
  };

  // state 변경 , put = ts 의 디스패치
  yield put(modifyPhoto(photoItem));

  // completed 속성 삭제 - 초기화
  yield put(initialCompleted());
}

/* ------------- saga action을 감지(take)하는 부분 -------------- */
// photo redux state 처리와 관련된 saga action들을 감지(take)할 saga를 생성
// saga는 generator함수로 작성
export default function* photoSaga() {
  // takeEvery(처리할 액션, 액션을 처리할 함수)  == 데이터 추가/수정/삭제
  // dispatcher - 동일한 타입의 액션은 모두 처리함
  yield takeEvery(requestAddPhoto, addData);
  //                    2)

  // takeLatest(처리할액션, 액션을처리할함수)  == 데이터 조회
  // 동일한 타입의 액션중에서 가장 마지막 액션만 처리, 이전 액션은 취소
  // 이전에 처리하던 함수는 모두 취소하고 맨마지막 함수만 실행
  yield takeLatest(requestFetchPhotos, fetchData); // 2)

  yield takeLatest(requestFetchPagingPhotos, fetchPagingData);

  // 처리할 액션 등록, 삭제처리
  yield takeEvery(requestRemovePhoto, removeData);

  yield takeEvery(requestModifyPhoto, modifyData);

  // takeEvery : 추가, 수정, 삭제
  // takeLatest : 목록 조회
}
