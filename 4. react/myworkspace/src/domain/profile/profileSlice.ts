// redux store (리덕스 저장소)에 하나의 state를
// 관리하고 처리할 수 있는 모듈 = slice라고 함 (여러개중 하나)

import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { pic } from "../../common/data";

// state 타입 내보내기, 외부에서 이것을 가져다 씀
export interface ProfileState {
  image: string | undefined;
  username: string | undefined;
}

// 1. state초기 상태 선언
// 빈값이라도 무조건 넣어야함, 안그럼 에러남
const initialState: ProfileState = {
  image: pic,
  username: "Happy Jane",
};

// 2. slice 를 생성
export const profileSlice = createSlice({
  name: "profile", // slice 의 이름 (state의 이름)
  initialState, // 이 slice의 state 의 초깃값
  // 변수명 : 속성명 다르면   initialState:initialState 이렇게 써야함
  // state 변경함수 목록
  reducers: {
    // 함수명 : (기존 state 변수명, action 변수명) => {state 변경함수 실행문}
    saveProfile: (state, action: PayloadAction<ProfileState>) => {
      // immer 가 내장되어있음, 따라서 state변수를 직접제어함 (js처럼)
      console.log(action.type);
      //profile/saveProfile
      console.log(action.payload);
      // {image: "data:image/jpeg;base64,/9j/4AAQSkZJRgA", username: "edited new name"}

      // action == 이렇게 넘어옴
      //  {
      //     image: url, username: inputRef.current?.value
      //  }

      const profile = action.payload; // 매개변수로 받은 데이터
      state.image = profile.image;
      state.username = profile.username;
    },
  },
});

// action creator 내보내기 -> 컴포넌트에서 사용하기 위함
// reducer 함수명에 맞는 action creator 들을 createSlice 할때 자동으로 생성함

// action = {type: "...", payload:{...}}  - 객체
// action.type: 처리할 액션의 종류를 나타내는 문자열
// action.payload : 처리할 데이터

// action creator는 action 객체를 생성하는 함수
// saveProfile(payload) => {type:"profile/saveProfile", payload}
export const { saveProfile } = profileSlice.actions;
// action creator 목록 중 saveProfile 을 내보냄

// slice.reducer
// state 변경 함수를 여러개 가지고 있는 객체
// == reducer 를 여러개 가지고 있는 객체
// slice.reducer:{function...(),function...(),function...(), } 함수 여러개 들어있음.
//  reducers == slice 변경하는 함수 1개 ,  reducer == reducers 함수 여러개, 즉 reducer가 더 큰거

// 내보내기 기본객체를 reducer함
export default profileSlice.reducer;

// reducers : state 변경함수 (28-34 라인) 하나로 묶어서 reducer라는 객체를 만듬
