import "./App.scss";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Header from "./components/Header";
import Button from "./components/Button";
import Counter from "./components/Counter";
import Calculator from "./components/calculator";
import Generator from "./components/Generator";
import AccountManager from "./components/AccountManager";
import { Component } from "react";

function App() {
  return (
    <Router>
      <div style={{ width: "500px", margin: "0 auto" }}>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/counter">Home</Link></li>
            <li><Link to="/calculator">Home</Link></li>
            <li><Link to="/generator">Home</Link></li>
            <li><Link to="/account-maneger">Home</Link></li>
            <li><Link to="/bootstrap">Home</Link></li>

          </ul>
        </nav>

        <main>
          <swich>
            <Route path="/" component={Components} exact />
            <Route path="/counter" component={Counter} />
            <Route path="/calculator" component={Calculator} />
            <Route path="/generator" component={BootStrap} />
            <Route />
            <Route />
            <Route />
            <Route />
          </swich>
        </main>

        <Counter />

        <Calculator />

        <Generator />

        <AccountManager />
      </div>
    </Router>
  );
}

{
  /* <Header />  닫는태그 하나에 함께 써줌

이 위로가 일종의 바디테그 여기서부터가 시작이다.
App.tsx 모듈 기본 내보내기를 App 함수로 함*/
}
export default App;
