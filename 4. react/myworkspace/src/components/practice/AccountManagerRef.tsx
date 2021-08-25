import { useRef, useState } from "react";

const ListItem = ({ money }: { money: number }) => {
  return (
    <li style={{ color: money < 0 ? "red" : "green" }}>
      {money < 0 ? "출금 : " : "입금 : "}
      {money}
    </li>
  );
};

//계좌관리
const Account = () => {
  const [logs, setLogs] = useState<number[]>([]);
  const inputRef = useRef<HTMLInputElement>(null);

  const transact = (type: "입금" | "출금") => {
    const input = inputRef.current?.value;

    let money = 0;
    if (input) {
      money = type === "입금" ? +input : -input;
    }

    if (type === "입금") {
      setLogs([money, ...logs]);
    } else {
      if (check(logs, money)) {
        setLogs([money, ...logs]);
      } else {
        alert("잔액이 부족합니다.");
      }
    }
    inputRef.current && (inputRef.current.value = "");
  };

  const check = (logs: number[], money: number) => {
    let sum = 0;
    if (logs.length > 0) {
      sum = logs.reduce((acc, log) => acc + log);
    }
    //0보다 크면 true : 값이 들어있다는 뜻
    return sum + money >= 0;
  };

  return (
    <div>
      <h2>Account Manager</h2>
      <input type="text" ref={inputRef} />
      <button
        onClick={() => {
          transact("입금");
        }}
      >
        입금
      </button>
      <button
        onClick={() => {
          transact("출금");
        }}
      >
        출금
      </button>
      {logs.length > 0 && (
        <span>잔액: {logs.reduce((acc, log) => acc + log)}</span>
      )}
      <ul>
        {logs.map((money, index) => (
          <ListItem key={index} money={money} />
        ))}
      </ul>
    </div>
  );
};
export default Account;
