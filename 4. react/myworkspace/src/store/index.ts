import { configureStore } from "@reduxjs/toolkit";
import profileReducer from "../domain/profile/profileSlice";
import photoReducer from "../domain/photo/photoSlice";
// global state(전역 상태) 저장소
// global state : profile, todo, contact … 여러개 state가 있음
// ** 이러한 state들은 다른 컴포넌트와 state가 공유 됨

// 라이브 함수를 이용해 저장소 만듬.
//store 라는 변수 내보내기 함
// configureStore() 라는 함수를 이용해서 reducer 를 만듬
export const store = configureStore({
  reducer: {
    profile: profileReducer,
    // state 이름: reducer 이름
    // profile state 처리하는 reducer 등록
    photo: photoReducer,
    // photos처리하는 reducer 등록
  }, //각, state 별로 처리할 reducer목록
  devTools: true, //개발툴 사용여부
});

//typescript에서는 몇가지 타입을 선언 해야함

// root state 타입 정의  store.getState 함수의 리턴타입을 쓰겠다. ReturnType
export type RootState = ReturnType<typeof store.getState>;

// dispatch 타입 정의
// dispatch 함수의 generic type
export type AppDispatch = typeof store.dispatch;
