import { fork } from "redux-saga/effects";
import photoSaga from "../features/photo/photoSaga";

// 최상위의 Saga(generator 함수)를 내보내기함
// 그 하위에, photoSaga, contactSaga 등등 이 있음
// 기능별 각각 saga action별로 처리할 saga 들을 넣어줌
export default function* rootSaga() {
  //비동기로 하위 사가를 처리함
  yield fork(photoSaga);
  //yield fork(contactSaga);
}
