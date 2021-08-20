// Generaot
// 숫자값을 랜덤 -50 ~50 범위로 생성하고
// 배열 state 에 추가
// 숫자 목록을 ul > li 로 출력

import { useState } from "react";

// 기존 js방식 DOM 요소르 직접 조작

// React:
// virtural Dom 요소와 관련된
// state 나 prop를 조작

// 원시타입 primitive type: number, string, boolean
// 값이 바뀌어야만 다시 랜더링함

// reference type참조타임: object, array
// 참조가 바뀌어야만 다시 랜더링함
// 컨포넌트를 여러개 쓸 수 있음

const Generator = () => {
  // useState<number[]>([]); 함수에 배열 안에 들어갈 타입을 넘버로 지정해준다.<number[]>
  const [numbers, setNumber] = useState<number[]>([]);

  const generate = () => {
    const num = Math.trunc(Math.random() * 100 - 50);
    setNumber([num, ...numbers]);
    //setNumber(변경되는 state)
    //[] 대괄호로 새로운 배열을 만듬
    // 기존배열 복사, ... 나열 연산
    // 새로운 배열에 첫 요로소 num값, 나머지는 기존 배열
  };

  return (
    <div>
      <h2>Generator</h2>
      <button
        onClick={() => {
          generate();
        }}
      >
        GENERATOR
      </button>
      <div>{numbers}</div>
      <ul>
        {/* JSX Element 내부에서는 중괄호로 코드를 침 , */}
        {
          //중괄호 안에서는 슬래쉬 주석 쓸수있음, for함수 못써서 map함수 써야함
          numbers.map((num, index) =>
            num < 0 ? (
              <li style={{ color: "red" }} key={index}>
                {num}
              </li>
            ) : (
              <li style={{ color: "green" }} key={index}>
                {num}
              </li>
            )
          )
        }
      </ul>
    </div>
  );
};

export default Generator;
