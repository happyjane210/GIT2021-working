// 계좌관리
// 입력박스, 버튼두개
// 입력박스에는 입출금 금액을 넣음
// 버튼: 입금버튼, 출금버튼

import { useState } from "react";

let a = 0;
let b = 0;

const ListItem = ({ key, num }: { key: number; num: number }) => {
  let color = num === a ? "green" : "red";
  return (
    <li key={key} style={{ color: color }}>
      {num}
    </li>
  );
};

const AccountManager = () => {
  const [account, setAccount] = useState<number[]>([]);

  const [money, setResult] = useState(0);

  const save = () => {
    a = Number(prompt("입금 금액", ""));
    setResult(money + a);
    setAccount([a, ...account]);
  };

  const withdraw = () => {
    b = Number(prompt("출금 금액", ""));
    setResult(money - b);
    setAccount([b, ...account]);
  };

  return (
    <div>
      <h2>Account Manager</h2>
      <button
        onClick={() => {
          save();
        }}
      >
        입금
      </button>
      <button
        onClick={() => {
          withdraw();
        }}
      >
        출금
      </button>
      <span>잔액:{money}</span>
      <ul>
        {account.map((num, index) => (
          <ListItem key={index} num={num} />
        ))}
      </ul>
    </div>
  );
};

export default AccountManager;
