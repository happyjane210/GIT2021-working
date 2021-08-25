// Counter 컴포넌트
// state(상태)

import { useState } from "react";

const Counter = () => {
  const [count, setCount] = useState(0);

  // 증가시키는 함수
  // state는 직접 변경 불가, setCount라는 변경함수로 변경해야함.

  const increase = () => {
    setCount(count + 1);
  };

  return (
    <div>
      <h2>Counter</h2>
      <div>
        {/* addEventListener("click",()=>{}) 와 같음
        onClick 되면 increase() 라는 증가 기능 함수 실행 */}
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

// const Counter = () => {
//   //cosnt [state명, state변경함수명] = useState(초깃값);
//   const [count, setCount] = useState(0);

//   const increase = () => {
//     setCount(count + 1);
//   };
//   return (
//     <div>
//       <h2>Counter</h2>
//       <div>
//         {/* addEventlistner 와 같은 것이다. */}
//         <button
//           onClick={() => {
//             increase();
//           }}
//         >
//           COUNT
//         </button>
//         <span>{count}</span>
//       </div>
//     </div>
//   );
// };

export default Counter;
