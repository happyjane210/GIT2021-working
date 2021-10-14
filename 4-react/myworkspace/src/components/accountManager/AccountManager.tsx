// 계좌관리

import { useState } from "react";

// 버튼: 입금버튼, 출금버튼
// 버튼 클릭시에 입금 금액 또는 출금 금액을 입력 받을 수 있음.

// 목록: 입금, 출금액 목록을 ul > li로 표시한다.
// 입금 금액은 <li> 입금: 금액 (녹색)</li> 으로 표시
// 출금 금액은 <li> 출금: -금액 (빨간색)</li> 으로 표시

// 총액: 잔액을 입금, 출금 버튼 우측에 표시한다.

let a = 0;
let b = 0;
const ListItem = ({ money }: { money: number }) => {
  return (
    <li style={{ color: money < 0 ? "red" : "green" }}>
      {money < 0 ? "출금: " : "입금: "}
      {money}
    </li>
  );
};

const AccountManager = () => {
  const [result, SetResult] = useState(0);
  const [account, SetAccount] = useState<number[]>([]);

  const input = () => {
    a = Number(prompt("입금하실 금액을 적어주세요."));
    SetResult(result + a);
    SetAccount([a, ...account]);
  };

  const output = () => {
    b = -Number(prompt("출금하실 금액을 적어주세요"));
    if (b * -1 > result) {
      alert("잔액이 부족합니다.");
    } else {
      SetResult(result + b);
      SetAccount([b, ...account]);
    }
  };

  return (
    <div>
      <h2>Account Manager</h2>
      <span>
        <button
          onClick={() => {
            input();
          }}
        >
          입금
        </button>
        <button
          onClick={() => {
            output();
          }}
        >
          출금
        </button>
        <span>잔액{result}</span>
      </span>
      <ul>
        {account.map((num, index) => (
          <ListItem key={index} money={num} />
        ))}
      </ul>
    </div>
  );
};

export default AccountManager;
