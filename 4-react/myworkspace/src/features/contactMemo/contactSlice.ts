import { createSlice, PayloadAction } from "@reduxjs/toolkit";

// 4. 필요한 데이터 구조 interface로 만들기
// 외부에서 쓸수있도록 export
export interface ContactItem {
  id: number;
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
  memo?: string;
  createdTime: number;
}

// 5. ContactState선언
interface ContactState {
  data: ContactItem[]; // contact 데이터 배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 여부
  isAddCompleted?: boolean; // 데이터 추가가 완료되었는지 여부
  isRemoveCompleted?: boolean; // 데이터 삭제가 완료되었는지 여부
  isModifyCompleted?: boolean; // 데이터 수정이 완료되었는지 여부
}

// 6. contact state 를 목록 -> array 로 변환
const initialState: ContactState = {
  data: [
    // {
    //   id: 2,
    //   name: "홍길동",
    //   phone: "02-1588-1588",
    //   email: "honggildong@123.com",
    //   memo: "This is the second contact",
    //   createdTime: new Date().getTime(),
    // },
    // {
    //   id: 1,
    //   name: "Name",
    //   phone: "010-0000-0000",
    //   email: "example@123.com",
    //   memo: "This is the first contact",
    //   createdTime: new Date().getTime(),
    // },
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
      state.isAddCompleted = true; //추가가 완료되었음으로 상태표시
    },

    initialCompleted: (state) => {
      delete state.isAddCompleted;
    },

    removeContact: (state, action: PayloadAction<number>) => {
      const id = action.payload;

      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1
      );
      state.isRemoveCompleted = true;
    },

    modifyContact: (state, action: PayloadAction<ContactItem>) => {
      const modifyItem = action.payload;
      const contactItem = state.data.find((item) => item.id === modifyItem.id);

      // state에 있는 객체의 속성을 넘긴 객체의 속성으로 변경
      if (contactItem) {
        contactItem.name = modifyItem.name;
        contactItem.phone = modifyItem.phone;
        contactItem.email = modifyItem.email;
        contactItem.memo = modifyItem.memo;
      }
      state.isModifyCompleted = true;
      // 변경되었음을 표시
    },

    // payload 값으로 state를 초기화하는 reducer 필요
    initialContact: (state, action: PayloadAction<ContactItem[]>) => {
      const contacts = action.payload;

      // 백엔드에서 받아온 데이터
      state.data = contacts;
      state.isFetched = false;
    },
  },
});

export const {
  addContact,
  removeContact,
  modifyContact,
  initialContact,
  initialCompleted,
} = contactSlice.actions;

//2. contact 슬라이스 리듀서 밖으로 공유
export default contactSlice.reducer;
