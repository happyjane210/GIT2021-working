import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { pic } from "./index";

// 4. 필요한 데이터 구조를 interface 로 만듬
// - 목록조회: 4열 그리드화면으로 목록조회(프로필, 타이틀, 이미지)
// - 사진추가: 추가버튼으로 제목, 설명, 이미지파일 선택 후 추가, 목록버튼

export interface PhotoItem {
  id: number;
  profileUrl: string;
  username: string;
  title: string;
  description?: string;
  photoUrl: string;
  createTime: string;
}
// 얘도 외부에서 쓸수있도록 export 해줘야함

// 5. PhotoState 선언 - 백엔드 연동 고려해서 설계
interface PhotoState {
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 정보
}

// 6. photo state를 목록 -> array 로 변환
const initialState: PhotoState = {
  data: [
    {
      id: 6, //id 는 숫자이거나 증가되는 문자열
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 5,
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 4,
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 3,
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 2,
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 1,
      profileUrl: pic,
      username: "JANE LEE",
      title: "profile photo",
      description: "25ages moment",
      photoUrl: pic,
      createTime: new Date().toLocaleTimeString(),
    },
  ],
  isFetched: false,
};

//1. 슬라이스 만들기
// createSlice 할때 자동으로 payload action 함수 실행하는 함수 만들어줌
const photoSlice = createSlice({
  name: "photo",
  initialState, // 변수명, 속성명이 똑같음
  reducers: {
    // 14. reducer 함수 생성
    // state 와 action을 받는 리듀서
    //     action의 타입이  페이로드액션매개변수 <페이로드 타입>
    //PayloadAction<payload 타입>
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;

      console.log("----in reducer function----");
      console.log(photo);

      // 생성된 포토 카드를 데이터 앞에 쌓아줌
      state.data.unshift(photo);
    },
  },
});

// 14. action export
export const { addPhoto } = photoSlice.actions;

// 2. 슬라이스 리듀서를 밖으로 공유함
export default photoSlice.reducer;
