import { useEffect, useRef, useState } from "react";
import Alert from "../../components/alert/Alert";

import produce from "immer";

import api from "./todoApi";

//import api from "./todoApi";

// state 1건에 대한 타입
interface TodoItemState {
  id: number;
  memo: string | undefined;
  createdTime: number;
  modifyTime?: number;
  isEdit?: boolean; //수정모드인지 여부
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
  // 빈값여부 state
  const [isError, setIsError] = useState<boolean>(false);

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
    // process.env.변수명
    const res = await api.fetch();
    // 응답 데이터 타입을 자동으로 결정
    // axios.get<응답데이터타입>(응답URL)
    // GET 응답 URL HTTP/1.1

    // axios에서 응답받은 데이터는 data 속성에 들어가 있음
    // 서버로부터 받은 데이터를 state 객체로 변환함
    const todos = res.data.map((item) => ({
      id: item.id,
      memo: item.memo,
      createdTime: item.createdTime,
    })) as TodoItemState[];

    setLoading(false);
    setTodoList(todos); // todoState 업데이트

    console.log("--2. await azios.get completed--");
  };

  useEffect(() => {
    console.log("--1. mounted--");
    console.log("--3. completed--");

    fetchData();
  }, []);

  // 1. await키워드 쓰기 위해서 await를 쓰는 함수가 async 메서드로 선언되어야함
  const add = async (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    //입력값이 없으면 에러 메시지 표시
    if (!inputRef.current?.value) {
      setIsError(true);
      return;
    }

    //-------------백엔드 연동부분------------------------------
    //                              더할 객체를 싸서 보내줌
    const result = await api.add({ memo: inputRef.current?.value });
    console.log(result);

    //------------- state 변경 부분-----서버에서 넘어온 데이터로수정------------
    const todo: TodoItemState = {
      id: result.data.id, // optional chaning
      memo: result.data.memo,
      createdTime: result.data.createdTime,
    };

    // 프론트에서 사용하는 id 값
    // const todo: TodoItemState = {
    //   id: todoList.length > 0 ? todoList[0].id + 1 : 1,
    //   memo: inputRef.current?.value,
    //   createdTime: new Date().getTime(),
    // };

    setTodoList(
      produce((state) => {
        state.unshift(todo);
      })
    );

    formRef.current?.reset();

    setIsError(false);
  };

  const del = async (id: number, index: number) => {
    console.log(id);

    // 불변성 때문에 splice를사용할 수 없음
    // 주로 filter 함수를 사용
    // filter 함수로 헤당 id를 제외하고 새로운 배열을 리턴함
    // -------------백엔드 연동부분------------------------------
    const result = await api.remove(id);
    console.log(result);
    //-------------- state 변경 부분-----------------------------
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

  const save = async (id: number, index: number) => {
    console.log(ulRef.current);
    console.log(index);

    const input = ulRef.current
      ?.querySelectorAll("li")
      [index].querySelector("input");
    console.log(input);

    // -------------백엔드 연동부분------------------------------
    // 수정차리 요청
    // input이 없으면 그냥 반환,
    if (!input) return;
    const result = await api.modify(id, { memo: input.value });

    //--------------수정된 state 변경 부분----------백엔드 수정 처리---------
    setTodoList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.memo = result.data.memo; // 수정된 부분
          item.modifyTime = new Date().getTime(); // 이건 그냥 놔둬
          item.isEdit = false; // 화면에 수정모드, 뷰모드제어용 (클라이언트 처리)
        }
      })
    );

    //---- 프론트 엔드 수정 처리
    // setTodoList(
    //   produce((state) => {
    //     const item = state.find((item) => item.id === id);
    //     if (item) {
    //       item.memo = input?.value;
    //       item.modifyTime = new Date().getTime();
    //       item.isEdit = false;
    //     }
    //   })
    // );
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

      <ul className="list-group list-group-flush mt-3" ref={ulRef}>
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
        {!isLoading && todoList.length === 0 && (
          <li className="list-group-item">데이터가 없어요😓</li>
        )}

        {/* 데이터와 UI요소 바인딩 */}
        {todoList.map((item, index) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              {/* 보기모드일때 보이는 내용 */}
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
