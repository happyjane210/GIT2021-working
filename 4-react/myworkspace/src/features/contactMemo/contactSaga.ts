import contactReducer, {
  addContact,
  initialContact,
  modifyContact,
  removeContact,
} from "./contactSlice";
import { call, put, takeEvery, takeLatest } from "@redux-saga/core/effects";
import { createAction, nanoid, PayloadAction } from "@reduxjs/toolkit";
import { ContactItem } from "./contactSlice";
import api, { ContactItemReqeust, ContactItemResponse } from "./contactApi";
import { AxiosResponse } from "axios";
import {
  endProgress,
  startProgress,
} from "../../components/progress/progressSlice";
import { addAlert } from "../../components/alert/alertSlice";
import { initialCompleted } from "../photo/photoSlice";

//============== Saga Action 생성 ====================

// contact 를 추가하는 action 생성
export const requestAddContact = createAction<ContactItem>(
  `${contactReducer.name}/requestAddContact`
);

// contact 를 백엔드에서 가져오는 action

export const requestFetchContacts = createAction(
  `${contactReducer.name}/requestFetchContacts`
);

// contact 를 삭제하는 action 생성
export const requestRemoveContact = createAction<number>(
  `${contactReducer.name}/requestRemoveContact`
);

// contact 를 수정하는 action 생성
export const requestModifyContact = createAction<ContactItem>(
  `${contactReducer.name}/requestModifyContact`
);

//============== Saga Action 처리 ====================

function* addData(action: PayloadAction<ContactItem>) {
  yield console.log("--addData--");
  yield console.log(action);

  try {
    // action 의 payload로 넘어온 객체
    const contactItemPayload = action.payload;

    // rest api로 보낼 요청객체
    const contactItemReqeust: ContactItemReqeust = {
      name: contactItemPayload.name,
      phone: contactItemPayload.phone,
      email: contactItemPayload.email,
      memo: contactItemPayload.memo,
      createdTime: contactItemPayload.createdTime,
    };

    // spinner 보여주기
    yield put(startProgress());

    //----- 1. rest api에 post 데이터 보냄
    console.log(api.add);

    const result: AxiosResponse<ContactItemResponse> = yield call(
      api.add,
      contactItemReqeust
    );

    // spinner 사라지게 하기
    yield put(endProgress());

    //----- 2. redux state를 변경함
    const contactItem: ContactItem = {
      id: result.data.id,
      name: result.data.name,
      phone: result.data.phone,
      email: result.data.email,
      memo: result.data.memo,
      createdTime: result.data.createdTime,
    };

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "success", message: "저장되었습니다." })
    );

    yield put(addContact(contactItem));

    yield put(initialCompleted());
  } catch (e: any) {
    // 에러 발생 처리

    // spinner 사라지게 하기
    yield put(endProgress());

    // alert 박스를 추가해줌
    yield put(
      addAlert({ id: nanoid(), variant: "danger", message: e.message })
    );
  }
}

// 서버에서 GET으로 데이터를 가져오고, redux state를 초기화
function* fetchData() {
  yield console.log("--fetchData--");

  // spinner 보여주기
  yield put(startProgress());

  // 백엔드에서 데이터 받아오기
  const result: AxiosResponse<ContactItemResponse[]> = yield call(api.fetch);

  // spinner 사라지게 하기
  yield put(endProgress());

  // 서버에서 받아온 응답데이터 배열 Response[]을 state에 넣을 수 있는 payload배열로 변환
  const contacts = result.data.map(
    (item) =>
      ({
        id: item.id,
        name: item.name,
        phone: item.phone,
        email: item.email,
        memo: item.memo,
        createdTime: item.createdTime,
      } as ContactItem)
  );

  // state 초기화 reducer 실행
  yield put(initialContact(contacts));
}

// 삭제 처리

function* removeData(action: PayloadAction<number>) {
  yield console.log("--removeData--");

  const id = action.payload;

  yield put(startProgress());

  const result: AxiosResponse<boolean> = yield call(api.remove, id);

  // spinner 사라지게 하기
  yield put(endProgress());

  // 반환값이 true면 state변경 1건 삭제
  if (result.data) {
    yield put(removeContact(id));
  }

  // completed 속성삭제
  yield put(initialCompleted());
}

// 수정 처리

function* modifyData(action: PayloadAction<ContactItem>) {
  yield console.log("--modifyData--");

  // action 의 payload로 넘어온 객체
  const contactItemPayload = action.payload;

  // rest api로 보낼 요청객체
  const contactItemReqeust: ContactItemReqeust = {
    name: contactItemPayload.name,
    phone: contactItemPayload.phone,
    email: contactItemPayload.email,
    memo: contactItemPayload.memo,
    createdTime: contactItemPayload.createdTime,
  };

  // spinner 보여주기
  yield put(startProgress());

  const result: AxiosResponse<ContactItemResponse> = yield call(
    api.modify,
    contactItemPayload.id,
    contactItemReqeust
  );

  // spinner 사라지게 하기
  yield put(endProgress());

  //----- 2. redux state를 변경함
  // 백엔드에서 처리한 데이터 객체로 state를 변경할 payload객체를 생성
  const contactItem: ContactItem = {
    id: result.data.id,
    name: result.data.name,
    phone: result.data.phone,
    email: result.data.email,
    memo: result.data.memo,
    createdTime: result.data.createdTime,
  };

  // state 변경
  yield put(modifyContact(contactItem));

  // completed 속성 삭제 - 초기화
  yield put(initialCompleted());
}

// 1. saga acton 감지 (take) generator함수
export default function* contactSaga() {
  // action creator 함수
  yield takeEvery(requestAddContact, addData); // 데이터 추가/수정/삭제

  yield takeLatest(requestFetchContacts, fetchData);

  // 삭제 처리할 액션 등록
  yield takeEvery(requestRemoveContact, removeData);

  yield takeEvery(requestModifyContact, modifyData);
}
