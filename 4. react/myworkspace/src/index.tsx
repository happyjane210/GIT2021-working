import React from "react";
import ReactDOM from "react-dom";
import "./index.css";

// 모듈 : 부분 코드
// App.tsx 모듈을 가져오고 App이름을 선언함
// export default로 내보낸 객체가 App 이름으로 선언됨
import App from "./App"; //App.tsx //.tsx 생략가능
import reportWebVitals from "./reportWebVitals";

//id가 root인 요소에 App 컴포넌트를 랜더링함
ReactDOM.render(
  <React.StrictMode>
    <App /> {/* App 컴포넌트 즉 App() ui조각을 받아서 랜더링 한다. */}
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
