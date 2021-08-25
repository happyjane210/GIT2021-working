// Calculator 컴포넌트
// 1.버튼을 클릭하면 prompt로 입력값을 두번 받음.
// a = prompt , b = prompt

import { useState } from "react";

// 2.연산자를 prompt로 받음
// + , x , - , /

// 3. 입력값 두개에 대한 연산 결과를 화면 출력
// state를 사용해야함

const Calculator = () => {
  const [result, setResult] = useState(0);
  const calculate = () => {
    const a = prompt("숫자1");
    const b = prompt("숫자2");
    const op = prompt("연산자");

    console.log(`${a}${op}${b}`);
    //eval문자열
    //문자열이 자바스크립트코드로 실행할 수 있으면 실행
    // eslint-disable-next-line
    setResult(eval(`${a}${op}${b}`));

    //state값에 변동이 없으면 컴포넌트를 업데이트 하지 않음
    //기존 == 20 인데 새로 넣는값도 20이면 안바뀜
  };

  return (
    <div>
      <h1>Calculator</h1>
      <button
        onClick={() => {
          calculate();
        }}
      >
        계산기 시작
      </button>
      <div>{result}</div>
    </div>
  );
};

export default Calculator;
