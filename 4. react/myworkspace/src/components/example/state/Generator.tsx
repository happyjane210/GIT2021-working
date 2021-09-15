//Generator

import { useState } from "react";

// 숫자 값을 랜덤 -50 ~ 50 범위로 생성하고
// 배열 state에 추가
// 숫자 목록을 ul > li로 출력

// 기존 javascript
// DOM 요소를 직접적으로 조작

// React
// 가상DOM에 state 또는 prop를 조작

const ListItem = ({ num }: { num: number }) => {
  const color = num < 0 ? "red" : "green";
  return <li style={{ color: color }}></li>;
};

const Generator = () => {
  // useState<타입>
  // state의 타입을 지정해줄 수 있음
  const [numbers, setNumber] = useState<number[]>([]); //number 타입의 배열

  const generate = () => {
    const num = Math.floor(Math.random() * 100 - 50); // 0 ~ 99 였다가 -50 이니까 -50 ~ 49;
    // numbers.push(num) // 이건 직접대입이며 배열이나 객체같은 참조타입의 요소는 새로운 객체를 참조해야 한다.
    setNumber([num, ...numbers]);
    //배열에 새로운 값을 더해 새로운 배열을 참조 하려면 스프레드 형태로 기존값에 합쳐주면 된다.
    //[] : 새로운 배열 생성
    //[]
    //[...numbers] : 기존 배열 복사, ...나열 연산
    //[0, 1, 2, 3]
    //[num, ...numbers]: 새로운 배열에 첫번째요소로 num값, 나머지는 기존배열
    //[-17, 0, 1, 2, 3]
  };

  return (
    <div>
      <h2>Generator</h2>
      <button
        onClick={() => {
          generate();
        }}
      >
        GENERATE
      </button>
      <div>{numbers}</div>
      <ul>
        {
          //세미콜론(;)을 한번만 쓸수 있는 코드
          //map : 기존 배열 크기와 동일하나 요소가 밴경된 배열을 반환
          //숫자 배열 -> JSX배열로 변환
          numbers.map((num, index) => (
            // num < 0 ? (
            //   <li style={{ color: "red" }} key={index}>
            //     {num}
            //   </li>
            // ) : (
            //   <li style={{ color: "green" }} key={index}>
            //     {num}
            //   </li>
            // )
            <ListItem key={index} num={num} />
          ))
        }
      </ul>
    </div>
  );
};

export default Generator;
