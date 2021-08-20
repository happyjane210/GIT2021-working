// Counter 컴포넌트
// React state(상태)

// const [state이름, state변경함수명] = useState(초깃값);
// prop : 화면에 랜더링 결과에 영향을 미치는 값인데 외부에서 매개변수로 옴
// state: 화면에 랜더링 결과에 영향을 미치는 변수,  내부에서 선언함

// state 변수는 변경불가 immutable 불변성
//count = 1; 직접적인 변경불가
// setCount(count + 1);  -> state 변경함수로만 변경할 수 있음

// state 변경함수로 state변경했을 때만 컴포넌트를 업데이트함(다시그림)

// 화면에 뭔가 변경을 일으켜야함, 내부에서 처리: state 외부에서 오는 것: prop
// state 변경함수를 실행하면 state가 있는 컴포넌트가 모두 다시 그려짐
// -> 실제로는 변경하상이 있는부분만 다시그려짐
// 기존

import { useState } from "react";

const Counter = () => {
  const [count, setCount] = useState(0);

  const increase = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <h2>Counter</h2>
      <div>
        <button
          onClick={() => {
            increase();
          }}
        >
          COUNT
        </button>
        <span>{count}</span>
      </div>
    </div>
  );
};

export default Counter;

// onClick 버튼 태그에 addeventListener 해준거랑 같음
