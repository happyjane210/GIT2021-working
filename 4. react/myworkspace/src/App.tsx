// JSX : Javascript 기반의 HTML 태그 형식

// 각각의 태그(element)들은 javascript 객체이다.
// 일반적인 html 태그 표기법과 다름

// JSX Element
// const element = {
// 	<h1 className =“greeting”>
// 		Hello, world!
// 	</h1>
// };

// 실제 컴파일되는 결과
// const element = React,createElement(
// 	‘h1’, //태그
// 	{className: ‘greeting’}, //속성
// 	‘Hello, world’ //컨텐트(내용)
// );

//document.createElement("div");
//실제 DOM을 생성함 (위 예시와 다름)

//React.createElement("div", ...);
//가상 DOM을 생성함
// 가상 DOM == javascript 객체
// 내부적으로 가상 DOM tree를 관리함

//일반 DOM
// DOM을 조작할 때마다 랜더링을 함, 성능저하

// 가상DOM
// 렌더링 주기에 따라서 변동사항만 가상DOM에서 읽어와서 랜더링함

//--------------------------------------------------

// //Function Component
// - 대문자로 시작한다.
// - JSX Element를 반환한다.
// -  JS함수인데,  JSX Element를 반환함 == Component
// -  리액트에서 컴포넌트는 JSX Element를 랜더링 하는 함수
import "./App.scss";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { Suspense, lazy } from "react";

import Home from "./domain/Home";
//import Navigation from "./Navigation";

// SPA ( Single Page Application)
// - 페이지 파일이 1개, index.html
// - 특정 영역(Switch)에 컴포넌트(js)를 로딩함
// - 애플리케이션이 컴파일될 때 import한 컴포넌트가 같이 컴파일 됨
//     -> 컴파일 됐을 때 파일크기가 커짐, 초기 로딩할 때 시간 걸림
// - 한덩어리로 받아오기 때문에

//Lazy-Loading 처리
// 컴포넌트를 방문하는 시점에 로딩함

const Todo = lazy(() => import("./domain/todo/Todo"));
const Feed = lazy(() => import("./domain/feed/Feed"));
const Contact = lazy(() => import("./domain/contact/Contact"));
// React == 컴포넌트 개발 라이브러리
function App() {
  return (
    <Router>
      {/* main container */}
      <div className="mx-auto">
        {/* nav의 top 이 app bar만큼 와야함 */}
        <header className="bg-primary app-bar shadow">App Bar</header>
        <nav className="menu-drawar position-fixed bg-light shadow-sm">
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>

            <li>
              <Link to="/Todo">Todo</Link>
            </li>

            <li>
              <Link to="/Feed">Feed</Link>
            </li>
            <li>
              <Link to="Contact">Contact</Link>
            </li>
          </ul>
        </nav>
        <main style={{ marginLeft: "200px" }}>
          {/* 홈페이지 메인 메뉴가 오른쪽으로 200만큼 가있음 */}
          {/* Suspense 컴포넌트로 로딩중에 보여줄 화면을 처리하는 것 */}
          {/* fallback={로딩중 보여줄 컴포넌트} */}

          <Suspense
            fallback={
              <div>
                <h1 style={{ color: "tomato" }}>Loading....⏳⏳⏳</h1>
              </div>
            }
          >
            <Switch>
              {/* Switch 영역에 컴포넌트가 로딩됨 */}
              {/* 해당 경로에 대해서 로딩할 컴포넌트 */}
              <Route path="/" component={Home} exact />
              <Route path="/todo" component={Todo}></Route>
              <Route path="/Feed" component={Feed} />
              <Route path="/Contact" component={Contact} />
            </Switch>
          </Suspense>
        </main>
      </div>
    </Router>
  );
}

// App.tsx 모듈의 기본 내보내기를 App 함수로 함
export default App;
