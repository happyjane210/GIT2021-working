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
import { Provider } from "react-redux"; // react 앱에 redux store를 제공함
import { store } from "./store"; // redux store

import Home from "./features/home/Home";
import Profile from "./features/profile/Profile";
//import Navigation from "./Navigation";
import Progress from "./components/progress/Progress";
import AlertStack from "./components/alert/AlertStack";
import EventMessage from "./components/EventMessage";

// SPA ( Single Page Application)
// - 페이지 파일이 1개, index.html
// - 특정 영역(Switch)에 컴포넌트(js)를 로딩함
// - 애플리케이션이 컴파일될 때 import한 컴포넌트가 같이 컴파일 됨
//     -> 컴파일 됐을 때 파일크기가 커짐, 초기 로딩할 때 시간 걸림
// - 한덩어리로 받아오기 때문에

//Lazy-Loading 처리
// 컴포넌트를 방문하는 시점에 로딩함
const Todo = lazy(() => import("./features/todo/Todo"));
const Feed = lazy(() => import("./features/feed/Feed"));
const Contact = lazy(() => import("./features/contactLine/ContactLine"));

const Photo = lazy(() => import("./features/photo/Photo"));
const PhotoCreate = lazy(() => import("./features/photo/PhotoCreate"));
const PhotoDetail = lazy(() => import("./features/photo/PhotoDetail"));
const PhotoEdit = lazy(() => import("./features/photo/PhotoEdit"));

const ContactMemo = lazy(() => import("./features/contactMemo/ContactMemo"));
const ContactCreate = lazy(
  () => import("./features/contactMemo/ContactCreate")
);
const ContactDetail = lazy(
  () => import("./features/contactMemo/ContactDetail")
);
const ContactEdit = lazy(() => import("./features/contactMemo/ContactEdit"));

const TodoLine = lazy(() => import("./features/todo/TodoInlineEdit"));
// React == 컴포넌트 개발 라이브러리
function App() {
  return (
    <Provider store={store}>
      <Router>
        {/* main container */}
        <div className="mx-auto">
          {/* nav의 top 이 app bar만큼 와야함 
        d-flex justify-content-end  profile 오른쪽으로 정렬*/}
          <header className="app-bar d-flex justify-content-end bg-primary shadow position-fixed">
            <Profile />
          </header>
          <nav className="drawer-menu position-fixed bg-light shadow-sm">
            <h4 className="ms-2 my-2">MY WORKSPACE</h4>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>

              <li>
                <Link to="/Todo">Todo</Link>
              </li>

              <li>
                <Link to="/TodoLine">TodoLine</Link>
              </li>

              <li>
                <Link to="/Feed">Feed</Link>
              </li>
              <li>
                <Link to="/Photo">Photo</Link>
              </li>

              <li>
                <Link to="/Contact">Contact-inLine</Link>
              </li>

              <li>
                <Link to="/ContactMemo">Contact-Manager</Link>
              </li>
            </ul>
          </nav>
          <main className="content-container">
            {/* 홈페이지 메인 메뉴가 오른쪽으로 200만큼 가있음 */}
            {/* Suspense 컴포넌트로 로딩중에 보여줄 화면을 처리하는 것 */}
            {/* fallback={로딩중 보여줄 컴포넌트} */}

            <Suspense
              fallback={
                <div className="mt-5">
                  <h1 style={{ color: "tomato" }}>Loading....⏳⏳⏳</h1>
                </div>
              }
            >
              <Switch>
                {/* Switch 영역에 컴포넌트가 로딩됨 */}
                {/* 해당 경로에 대해서 로딩할 컴포넌트 */}
                {/* exact 공부하기 매우 중요 라우터 관련 */}
                <Route path="/" component={Home} exact />
                {/* exact 안써주면 밑에 애들도 "/" 까지만 읽고 홈컴포넌트 로딩함 
                     정확히 "/"만 입력했을때 홈컴포넌트 실행 */}
                <Route path="/Todo" component={Todo} exact />
                <Route path="/Feed" component={Feed} />
                <Route path="/Contact" component={Contact} exact />
                <Route path="/Photo" component={Photo} exact />
                <Route path="/Photo/Create" component={PhotoCreate} exact />
                <Route path="/Photo/Detail/:id" component={PhotoDetail} />
                <Route path="/Photo/Edit/:id" component={PhotoEdit} />
                {/* 넘길때 id 매개 변수 받아서 감 */}

                <Route path="/ContactMemo" component={ContactMemo} exact />
                <Route
                  path="/ContactMemo/ContactCreate"
                  component={ContactCreate}
                  exact
                />
                <Route
                  path="/ContactMemo/ContactDetail/:id"
                  component={ContactDetail}
                />
                <Route
                  path="/ContactMemo/ContactEdit/:id"
                  component={ContactEdit}
                />
                {/* exact :  정확이 path경로와 일치할 때만 로딩해라, 속성 : true|false  */}
                {/* 정확히 "/Photo" 만 입력했을 때 포토 실행 */}
                {/* /Photo & /Photo/create  이름이 겹치는 부분이 있기때문에 내부 교집합을 exact로 바운더리 처리 해줌*/}
                {/* <Route path="/Photo/Detail" component={PhotoDetail} /> */}

                <Route path="/TodoLine" component={TodoLine} exact />
              </Switch>
            </Suspense>

            <Progress />
            <AlertStack />
            <EventMessage />
          </main>
        </div>
      </Router>
    </Provider>
  );
}

// App.tsx 모듈의 기본 내보내기를 App 함수로 함
export default App;
