import { createSlice, PayloadAction } from "@reduxjs/toolkit";
//import { pic } from "./index";

// 4. 필요한 데이터 구조를 interface 로 만듬
// - 목록조회: 4열 그리드화면으로 목록조회(프로필, 타이틀, 이미지)
// - 사진추가: 추가버튼으로 제목, 설명, 이미지파일 선택 후 추가, 목록버튼

export interface PhotoItem {
  id: number;
  title: string;
  description?: string;
  photoUrl: string;
  fileType: string;
  fileName: string;
  createdTime: number;
  // profileUrl: string;
  // username: string;
}
// 얘도 외부에서 쓸수있도록 export 해줘야함

// 5. PhotoState 선언 - 백엔드 연동 고려해서 설계
interface PhotoState {
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 여부
  isAddCompleted?: boolean; // 데이터 추가가 완료되었는지 여부
  isRemoveCompleted?: boolean; // 데이터 삭제가 완료되었는지 여부
  isModifyCompleted?: boolean; // 데이터 수정이 완료되었는지 여부
}

// 6. photo state를 목록 -> array 로 변환
const initialState: PhotoState = {
  data: [
    // {
    //   id: 1,
    //   title: "profile photo",
    //   // profileUrl: pic,
    //   // username: "JANE LEE",
    //   description: "25ages moment",
    //   photoUrl: pic,
    //   fileType: "image/jpeg",
    //   fileName: "SONO9438-3.jpg",
    //   createdTime: new Date().getTime(),
    // },
  ],
  isFetched: false,
};

//1. 슬라이스 만들기
// createSlice 할때 자동으로 payload action 함수 실행하는 함수 만들어줌
const photoSlice = createSlice({
  name: "photo",
  initialState, // 변수명, 속성명이 똑같음
  reducers: {
    // 14-1. reducer 함수 생성
    // state 와 action을 받는 리듀서
    //     action의 타입이  페이로드액션매개변수 <페이로드 타입>
    //PayloadAction<payload 타입>
    // payload로 item 객체를 받음
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;

      console.log("----in reducer function----");
      console.log(photo);

      // 생성된 포토 카드를 데이터 앞에 쌓아줌
      state.data.unshift(photo);
      state.isAddCompleted = true; //추가가 완료되었음으로 상태표시
    },

    // payload 없는 reducer
    // completed 관련된 속성을 삭제함 (undefined)
    initialCompleted: (state) => {
      delete state.isAddCompleted;
    },

    // payload 로 id 값을 받음
    // action: PayloadAction<number>
    // reducer 넘어오는 action payload있는 액션인데,
    // payload 의 타입이 number 이다.
    removePhoto: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id에 해당하는 아이템의 index를 찾고 그 index로 splice 를 한다.
      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1 // 한 건만 삭제를 한다
      );
      state.isRemoveCompleted = true; // 삭제 완료 되었음을 표시
    },

    modifyPhoto: (state, action: PayloadAction<PhotoItem>) => {
      // 생성해서 넘긴 객체
      const modifyItem = action.payload;
      // state에 있는 객체
      const photoItem = state.data.find((item) => item.id === modifyItem.id);

      // state에 있는 객체의 속성을 넘긴 객체의 속성으로 변경
      if (photoItem) {
        photoItem.title = modifyItem.title;
        photoItem.description = modifyItem.description;
        photoItem.photoUrl = modifyItem.photoUrl;
        photoItem.fileName = modifyItem.fileName;
        photoItem.fileType = modifyItem.fileType;
      }
      state.isModifyCompleted = true; // 변경되었음을 표시
    },

    // 5)
    // payload값으로 state를 초기화하는 reducer 필요함
    initialPhoto: (state, action: PayloadAction<PhotoItem[]>) => {
      const photos = action.payload;
      // 백엔드에서 받아온 데이터  6)
      state.data = photos;
      state.isFetched = true;
    },
  },
});

// 14. action export
export const {
  addPhoto,
  removePhoto,
  modifyPhoto,
  initialPhoto,
  initialCompleted,
} = photoSlice.actions;

// 2. 슬라이스 리듀서를 밖으로 공유함
export default photoSlice.reducer;
