//

import { useRef, useState } from "react";

const Hello = () => {
  //useState<state타입>(초기값)
  // state타입을 union타입으로 설정가능
  const [userName, serUserName] = useState<string | undefined>("");

  //참조객체 생성
  // useRef<참조 객체 타입>(초기값);
  // JSX Element를 참조하는 객체라면
  // JSX Element로 랜더링 되는 HTML 요소의 타입을 넣어야함, 기본값은 null
  // HTMLInputElement <input>고유의 엘리먼트
  // (null) : 기본값, input의 첫번째 기본값은 아무것도 없으니 null
  const inputRef = useRef<HTMLInputElement>(null);

  const hello = () => {
    // JSX Element를 참조하는 객체일 때
    // 참조객체.current는 현재 랜더링된 HTML요소
    // console.log(inputRef);
    // console.log(inputRef.current);
    // inputRef.current가 null/undefined가 아니면 value속성을 참조해라
    // console.log(inputRef.current?.value);

    // current 객체가 있으면 == 랜더링된 HTML요소 있으면
    // current.value == 입력박스의 입력값

    // current 객체가 없으면 == 랜더링된 HTML요소 없음(랜더링 되기 전, null)
    // current?.value == undefined
    const input = inputRef.current;
    const inputName = input?.value;
    serUserName(inputName);

    if (input) {
      input.value = "";
    }
  };

  //   console.log(inputRef.current); //current가 null이면 undefined를 반환
  //   console.log(inputRef.current?.value);

  // 모양만 html처럼 생긴 tsx코드 껍데기 코드에 처리기능을 넣어주려고 쓰는게 ref

  return (
    <div>
      <h2>Hello</h2>
      <input type="text" ref={inputRef} />
      <button
        onClick={() => {
          hello();
        }}
      >
        인사
      </button>
      <div>Hello, {userName}!</div>
    </div>
  );
};

export default Hello;
