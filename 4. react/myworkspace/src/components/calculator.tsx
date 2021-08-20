//1.
// Calculator 컴포넌트
// prompt 로 입력값 두번 받음
// a= promt,b= promt

import { useState } from "react";

// 1. 버튼을 클릭하면 prompt로 입력값을 두번 받음
// a = prompt, b = prompt

// 2. 그다음에 연산자를 prompt로 받음
// +, -, *, /

// 3. 입력값 두개에 대한 연산 결과를 화면 출력
// state를 사용해야함

// 예) 입력값1: 10
//     입력값2: 5
//     연산자: *
//     결과값: 50
//     <div>50</div>

const Calculator = () => {
  const [result, setResult] = useState(0);
  const calculate = () => {
    const a = prompt("첫번째 숫자");
    const b = prompt("두번째 숫자");
    const op = prompt("연산자, (+, -, *, /)");

    console.log(`${a}${op}${b}`);

    // const code = `alert(${a}${op}${b})`;
    // eval(code);
    setResult(eval(`${a}${op}${b}`));
  };

  return (
    <div>
      <h2>Calculator</h2>
      <button
        onClick={() => {
          calculate();
        }}
      >
        Start
      </button>
      <div>{result}</div>
    </div>
  );
};

export default Calculator;
