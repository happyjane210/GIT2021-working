import contactReducer, { addContact, initialContact } from "./contactSlice";
import { call, put, takeEvery, takeLatest } from "@redux-saga/core/effects";
import { createAction, PayloadAction } from "@reduxjs/toolkit";
import { ContactItem } from "./contactSlice";
import api, { ContactItemReqeust, ContactItemResponse } from "./contactApi";
import { AxiosResponse } from "axios";

//============== Saga Action 생성 ====================

export const requestAddContact = createAction<ContactItem>(
  `${contactReducer.name}/requestAddContact`
);

// contact 를 백엔드에서 가져오는 action

export const requestFetchContacts = createAction(
  `${contactReducer.name}/requestFetchContacts`
);

//============== Saga Action 처리 ====================

function* addData(action: PayloadAction<ContactItem>) {
  yield console.log("--addData--");
  yield console.log(action);

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

  //----- 1. rest api에 post 데이터 보냄
  console.log(api.add);

  const result: AxiosResponse<ContactItemResponse> = yield call(
    api.add,
    contactItemReqeust
  );

  //----- 2. redux state를 변경함
  const contactItem: ContactItem = {
    id: result.data.id,
    name: result.data.name,
    phone: result.data.phone,
    email: result.data.email,
    memo: result.data.memo,
    createdTime: result.data.createdTime,
  };

  yield put(addContact(contactItem));
}

// 서버에서 GET으로 데이터를 가져오고, redux state를 초기화
function* fetchData() {
  yield console.log("--fetchData--");

  // 백엔드에서 데이터 받아오기
  const result: AxiosResponse<ContactItemResponse[]> = yield call(api.fetch);

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

  console.log(contacts);

  // state 초기화 reducer 실행
  yield put(initialContact(contacts));
}

// 1. saga acton 감지 (take) generator함수
export default function* contactSaga() {
  // action creator 함수
  yield takeEvery(requestAddContact, addData); // 데이터 추가/수정/삭제

  yield takeLatest(requestFetchContacts, fetchData);
}
