import { configureStore } from "@reduxjs/toolkit";
import profileReducer from "../features/profile/profileSlice";
import photoReducer from "../features/photo/photoSlice";
import contactReducer from "../features/contactMemo/contactSlice";
import progressReducer from "../components/progress/progressSlice";
import alertReducer from "../components/alert/alertSlice";

// 최상위 saga
import rootSaga from "../saga";
import createSagaMiddleware from "@redux-saga/core";

// saga middleware생성
// middleware는 중간에 처리해주는 소프트웨어
// redux saga는 redex상태처리 전,후에 뭔가를 해주는 라이브러리
const sagaMiddleware = createSagaMiddleware();

// global state(전역 상태) 저장소
// global state : profile, todo, contact … 여러개 state가 있음
// ** 이러한 state들은 다른 컴포넌트와 state가 공유 됨

// 라이브 함수를 이용해 저장소 만듬.
//store 라는 변수 내보내기 함
// configureStore() 라는 함수를 이용해서 reducer 를 만듬
export const store = configureStore({
  reducer: {
    //각, state 별로 처리할 reducer목록

    profile: profileReducer,
    // state 이름: reducer 이름
    // profile state 처리하는 reducer 등록

    photo: photoReducer,
    //  3. photo state를 처리하는 reducer 등록

    contact: contactReducer,
    //  3. contact state 를 처리하는 reducer 등록

    progress: progressReducer,

    alert: alertReducer,
  },

  // redux store(dispatcher)에 미들웨어 적용
  // middleware는 여러개 사용할 수 있음
  // [defaultMiddleware],[sagaMiddleware],[thunkMiddleware]
  //
  middleware: [sagaMiddleware],
  devTools: true, //개발툴 사용여부
});

// Redux
// component -> dispatch(reduxAction) -> dispatcher
//  -> reducer -> store 내부(state 변경)

// Redux-Saga
// component -> dispatch(sagaAction) -> dispatcher
// -> saga -> API 서버연동 -> put(reducAction) -> dispatcher -> reducer -> store 내부(state 변경)

// saga middleware를 실행
// rootSaga 와 하위에 정의한 감지(take)할 redux Action들에 대해서 감지 시작
sagaMiddleware.run(rootSaga);

//typescript에서는 몇가지 타입을 선언 해야함

// root state 타입 정의  store.getState 함수의 리턴타입을 쓰겠다. ReturnType
export type RootState = ReturnType<typeof store.getState>;

// dispatch 타입 정의
// dispatch 함수의 generic type
export type AppDispatch = typeof store.dispatch;
