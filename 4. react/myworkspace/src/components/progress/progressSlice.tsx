import { createSlice } from "@reduxjs/toolkit";

// redux-toolkit에는 immer가 내장되어있기 때문에
// state타입은 객체 타입만 가능합
// 원시타입(string, number, boolean) dksehla
interface ProgressState {
  status: boolean;
}

const initialState: ProgressState = {
  status: false,
};

const progressSlice = createSlice({
  name: "progress",
  initialState,
  reducers: {
    // payload (매개변수) 안받는, 없는 reducer
    startProgress: (state) => {
      state.status = true;
    },
    endProgress: (state) => {
      state.status = false;
    },
  },
});

// 상수를 만들고 내보내기를 같이함
export const { startProgress, endProgress } = progressSlice.actions;
export default progressSlice.reducer;
