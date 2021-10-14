import { useEffect, useRef, useState } from "react";
import Alert from "../../components/alert/Alert";

import produce from "immer";
import { userInfo } from "os";

//import api from "./todoApi";

// state 1건에 대한 타입
interface TodoItemState {
  id: number;
  memo: string | undefined;
  createdTime: number;
  modifyTime?: number;
  isEdit?: boolean; //수정모드인지 여부
}

// 서버로 부터 받아오는 데이터 1건에 대한 타입
interface TodoItemReponse {
  id: number;
  memo: string;
  createdTime: number;
}

const getTimeString = (unixTime: number) => {
  //Locale: timeZone, currency 등
  //js 에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixTime);

  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Todo = () => {
  // todo 여러건에 대한 state
  const [todoList, setTodoList] = useState<TodoItemState[]>([
    { id: 2, memo: "환영합니다🎉", createdTime: new Date().getTime() },
    { id: 1, memo: "안녕하세요😄", createdTime: new Date().getTime() },
  ]);

  // 데이터 로딩처리 여부를 표시
  const [isLoading, setLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const ulRef = useRef<HTMLUListElement>(null);

  //useEffect:특정조건일때 작동하는 코드를 작성할 수 있게 하는 React Hook
  // React Hook: 클래스컴포넌트에서만 할 수 있었던작업을 함수형 컴포넌트에서 사용할 수 있게함
  // -> 클래스 컴포넌트 state, 컴포넌트 라이프사이클을 처리할 수 있음stateful
  // -> 함수형 컴포넌트 다른 컴포넌트로 부터 받은 prop으로 화면에 렌더링만 stateless

  // useEffect(처리할 함수, [조건변수]):
  // 의존변수의 값/참조가 바뀔때마다 함수가 처리됨
  // 예) props, state바뀌면 추가적인 처리

  // 의존변수 목록이 빈배열 []
  //  ->  컴포넌트가 처음 렌더링 되고 마운팅 된 후에 시점에 처리가 됨

  // es7 버전 방식
  // async 비동기 처리를 할 수 있는 함수
  // 코드가 순차적인 처리가 아닌 다른 컨텍스트(context)에 수행될 수 있도록 함
  // await 키워드는 async 함수 ㅏㅇㄴ에서만 사용가능함
  // Promise 객체를 반환하는 함수만 await 키워드를 사용할 수 있음
  const fetchData = async () => {
    // 함수를 호출하고 리턴값을 받는 것과 비슷한 구조
    // await: resolve(해결함수)가 실행되기 전까지 대기
    // resolve 가 실행되면 resolve 함수의 매개변수를 반환
    const res = await fetch("http://localhost:8080/todos");
    const data: TodoItemReponse[] = await res.json();

    const todos = data.map((item) => ({
      id: item.id,
      memo: item.memo,
      createdTime: item.createdTime,
    })) as TodoItemState[];

    setTodoList(todos); // todoState 업데이트

    console.log("--2. fetch completed--");
  };

  useEffect(() => {
    console.log("--1. mounted--");
    console.log("--3. completed--");

    fetchData();
  }, []);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    //입력값이 없으면 에러 메시지 표시
    if (!inputRef.current?.value) {
      setIsError(true);
      return;
    }

    const todo: TodoItemState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      memo: inputRef.current?.value,
      createdTime: new Date().getTime(),
    };

    setTodoList(
      produce((state) => {
        state.unshift(todo);
      })
    );

    formRef.current?.reset();

    setIsError(false);
  };

  const del = (id: number, index: number) => {
    // 불변성 때문에 splice를사용할 수 없음
    // 주로 filter 함수를 사용
    // filter 함수로 헤당 id를 제외하고 새로운 배열을 리턴함
    setTodoList(
      produce((state) => {
        state.splice(index, 1);
      })
    );
  };

  const edit = (id: number, mod: boolean) => {
    setTodoList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.isEdit = mod;
        }
      })
    );
  };

  const save = (id: number, index: number) => {
    console.log(ulRef.current);
    console.log(index);

    const input = ulRef.current
      ?.querySelectorAll("il")
      [index].querySelector("input");
    console.log(input);

    setTodoList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.memo = input?.value;
          item.modifyTime = new Date().getTime();
          item.isEdit = false;
        }
      })
    );
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
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
          message={"입력값이 없습니다."}
          variant={"danger"}
          // 닫기 버튼을 클릭할 때 처리하는 함수를 넘김
          // 부모 컴포넌트에서 받은 함수를 실행
          //() => {setIsError(false);}
          // event-up: 부모로 이벤트를 넘겨줌
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      <ul className="list-group list-group-flush mt-3">
        {/* 로딩중 처리 표시 */}
        {isLoading && (
          <li className="list-group-item text-center">
            <div
              className="spinner-border text-primary text-center"
              role="status"
            >
              <span className="visually-hidden">Loading...</span>
            </div>
          </li>
        )}
        {/* 빈데이터 표시 */}
        {!isLoading && todoList.length == 0 && (
          <li className="list-group-item">데이터가 없어요😓</li>
        )}

        {todoList.map((item, index) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              {!item.isEdit && <span className="me-1">{item.memo}</span>}
              {!item.isEdit && (
                <span style={{ fontSize: "0.75rem" }}>
                  -{" "}
                  {getTimeString(
                    item.modifyTime ? item.modifyTime : item.createdTime
                  )}
                </span>
              )}

              {item.isEdit && (
                <input type="text" className="w-100" defaultValue={item.memo} />
              )}
            </div>

            {!item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
                onClick={() => {
                  edit(item.id, true);
                }}
              >
                수정
              </button>
            )}

            {!item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm  text-nowrap"
                onClick={() => {
                  del(item.id, index);
                }}
              >
                삭제
              </button>
            )}

            {item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
                onClick={() => {
                  save(item.id, index);
                }}
              >
                저장
              </button>
            )}

            {item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm text-nowrap"
                onClick={() => {
                  edit(item.id, false);
                }}
              >
                취소
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Todo;
