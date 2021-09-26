// 컴포넌트 분리 이유
// - 다른데서도 재사용하기 위해서
// - 코드가 길어서 분리
// - 성능적인 관점에서 분리(렌더링 범위 축소: 부모컴포넌트가 업데이트 하면 자식도 업데이트됨)

//import { is } from "immer/dist/internal";
import React, { useRef, useState } from "react";
import Alert from "../alert/Alert";

// 한건에 대한 타입
interface TodoState {
  id: number;
  memo: string | undefined;
  createTime: number;
  modiftyTime?: number;
}
//toLocaleTimeString 현재 지역기준 시간이 문자열로 나옴
// Locale: tiemzone, currency 등
// js에서는 브라우저의 정보를 이용함
const getTimeString = (unixtime: number) => {
  const dateTime = new Date(unixtime);

  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const TodoExercise = () => {
  // 여러건에 대한 state
  // 참고) new Date().getTime() -> unix time 생성됨
  // 한번만 제어하지 않고 여려번 반복해서 제어해야하기 때문에 [] 배열로 넣음
  const [todoList, setTodoList] = useState<TodoState[]>([
    { id: 2, memo: "환영합니다🎉", createTime: new Date().getTime() },
    { id: 1, memo: "안녕하세요😋", createTime: new Date().getTime() },
  ]);

  // 빈값여부 state
  const [isError, setIsError] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);

  // 이벤트 객체가 있을때 입력박스에서 엔터 입력
  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    // 입력값이 없으면 에러메시지 표시
    if (!inputRef.current?.value) {
      setIsError(true);
      return;
    }

    const todo: TodoState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      memo: inputRef.current?.value,
      createTime: new Date().getTime(),
    };

    setTodoList([todo, ...todoList]);
    // optional chaning
    // current가 없으면 null출력, 있으면 value 출력, 선택의 여지를 남김 ? 체이닝

    formRef.current?.reset();

    setIsError(false);
  };

  const del = (id: number) => {
    setTodoList(todoList.filter((item) => item.id !== id));
  };

  return (
    <div>
      <h2 className="text-center my-5">할 일 관리</h2>
      <form
        className="d-flex"
        ref={formRef}
        onSubmit={(e) => e.preventDefault()}
      >
        <input
          type="text"
          className="form-control me-2"
          placeholder="할 일 ..."
          ref={inputRef}
          onKeyPress={(e) => {
            add(e);
          }}
        />
        <button
          type="button"
          className="btn btn-primary text-nowrap"
          onClick={() => {
            add(null);
          }}
        >
          추가
        </button>
      </form>
      {isError && (
        <Alert
          message={"내용을 입력해주세요."}
          variant={"danger"}
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      <ul className="list-group list-group-flush mt-3">
        {/* 데이터와 UI요소 바인딩 , 데이터를 고칠때마다 UI요소 달라짐*/}
        {todoList.map((item) => (
          <li className="list-group-item" key={item.id}>
            <button className="btn btn-outline-secondary btn-sm me-2">
              삭제
            </button>
            <span className="me-2">{item.memo}</span>
            <span style={{ fontSize: "0.75rem" }}>
              -{" "}
              {getTimeString(
                item.modiftyTime ? item.modiftyTime : item.createTime
              )}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TodoExercise;
