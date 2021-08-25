// 계좌관리

import { useState } from "react";

const ListItem = ({ money }: { money: number }) => {
  return (
    <li style={{ color: money < 0 ? "red" : "green" }}>
      {money < 0 ? "출금: " : "입금: "}
      {money}
    </li>
  );
};

const AccountManager = () => {
  const [logs, setLogs] = useState<number[]>([]);

  const transact = (mod: "deposit" | "withdraw") => {
    const msg = mod === "deposit" ? "입금 금액" : "출금 금액";
    const input = prompt(`${msg}을 입력해주세요.`);

    let money = 0;
    if (input) {
      money = mod === "deposit" ? +input : -input;
    }

    if (mod === "deposit") {
      setLogs([money, ...logs]);
    } else {
      if (check(logs, money)) {
        setLogs([money, ...logs]);
      } else {
        alert("잔액이 부족합니다.");
      }
    }
  };

  const check = (logs: number[], money: number) => {
    let sum = 0;
    if (logs.length > 0) {
      sum = logs.reduce((acc, log) => acc + log);
    }
    return sum + money >= 0;
  };

  return (
    <div>
      <h2>Account Manager</h2>
      <button
        onClick={() => {
          transact("deposit");
        }}
      >
        입금
      </button>
      <button
        onClick={() => {
          transact("withdraw");
        }}
      >
        출금
      </button>
      {logs.length > 0 && (
        <span>잔액: {logs.reduce((acc, log) => acc + log)} </span>
      )}
      <ul>
        {logs.map((money, index) => (
          <ListItem key={index} money={money} />
        ))}
      </ul>
    </div>
  );
};

export default AccountManager;
