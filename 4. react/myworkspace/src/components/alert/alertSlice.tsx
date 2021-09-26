import { createSlice, nanoid, PayloadAction } from "@reduxjs/toolkit";

//nanoid 문자열로 id 만드는 함수

interface AlertItem {
  id: string;
  variant: "success" | "danger" | "info" | "warnning";
  message: string;
}

const initialState: AlertItem[] = [
  //   { id: nanoid(), variant: "success", message: "수정되었습니다." },
  //   { id: nanoid(), variant: "danger", message: "오류입니다." },
];

const alertSlice = createSlice({
  name: "alert",
  initialState,
  reducers: {
    addAlert: (state, action: PayloadAction<AlertItem>) => {
      const alertItem = action.payload;
      state.unshift(alertItem); // 추가할 얼럿을 앞쪽에
    },

    // index값 찾아와서 splice
    removeAlert: (state, action: PayloadAction<string>) => {
      const id = action.payload;
      state.splice(
        state.findIndex((item) => item.id === id),
        1
      );
    },
  },
});

export const { addAlert, removeAlert } = alertSlice.actions;

export default alertSlice.reducer;
