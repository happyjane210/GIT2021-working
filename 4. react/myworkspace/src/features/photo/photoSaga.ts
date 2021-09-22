import photoReducer from "./photoSlice";

//-- saga action을 생성하는 부분

import { createAction } from "@reduxjs/toolkit";
import { PhotoItem } from "./photoSlice";

// photo를 추가하도록 요청하는 action
//       : action은 type과 payload로 구성되어있음
// {type, payload}
// {type: "photo/requestAddPhoto", payload: {title, photoUrl...}}

// photo를 추가하도록 요청하는 action creator를 생성 (action만 따로 만듬)
//       : action 객체를 반환하는 것이 action creator
// createAction<Payload타입>(Action.type)

// const item : photoItem = {title, photoUrl}
// const sagaAction = requestAddPhoto(item);
// sagaAction > {type: "photo/requestAddPhoto", payload: {title, photoUrl...}}
export const requestAddPhoto = createAction<PhotoItem>(
  `${photoReducer.name}/requestAddPhoto`
);

// photo redux state 처리와 관련된 saga action들을 감지(take)할 saga를 생성
// saga는 generator함수로 작성

export default function* photoSaga() {}
