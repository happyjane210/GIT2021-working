import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import Contact from "../contactLine/ContactLine";

// 4. 필요한 데이터 구조 interface로 만들기
// 외부에서 쓸수있도록 export
export interface ContactItem {
  id: number;
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
  memo?: string;
  createTime: string;
}

// 5. ContactState선언
interface ContactState {
  data: ContactItem[]; // contact 데이터 배열
  isFetched: boolean;
}

// 6. contact state 를 목록 -> array 로 변환
const initialState: ContactState = {
  data: [
    {
      id: 2,
      name: "홍길동",
      phone: "02-1588-1588",
      email: "honggildong@123.com",
      createTime: new Date().toLocaleTimeString(),
    },
    {
      id: 1,
      name: "Name",
      phone: "010-0000-0000",
      email: "example@123.com",
      createTime: new Date().toLocaleTimeString(),
    },
  ],
  isFetched: false,
};

//1. 슬라이스 생성 contact state 를 공유할 것.
const contactSlice = createSlice({
  name: "contact",
  initialState,
  reducers: {
    // 14-1. reducer 함수 생성
    // state와 action 받는 리듀서
    addContact: (state, action: PayloadAction<ContactItem>) => {
      const contact = action.payload;

      console.log("----in reducer function----");
      console.log(contact);

      state.data.unshift(contact);
    },
  },
});

export const { addContact } = contactSlice.actions;

//2. contact 슬라이스 리듀서 밖으로 공유
export default contactSlice.reducer;
