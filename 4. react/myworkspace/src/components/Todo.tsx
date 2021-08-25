import { useRef, useState } from "react";
import Alert from "./base/Alert";

interface TodoState {
  id: number;
  memo: string | undefined;
  createTime: number;
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
  const [todoList, setTodoList] = useState<TodoState[]>([
    { id: 2, memo: "환영합니다🎉", createTime: new Date().getTime() },
    { id: 1, memo: "안녕하세요😄", createTime: new Date().getTime() },
  ]);

  const [isError, setIsError] = useState(false);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    //입력값이 없으면 에러 메시지 표시
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

    formRef.current?.reset();

    setIsError(false);
  };

  const del = (id: number) => {
    // 불변성 때문에 splice를사용할 수 없음
    // 주로 filter 함수를 사용
    // filter 함수로 헤당 id를 제외하고 새로운 배열을 리턴함
    setTodoList(todoList.filter((item) => item.id !== id));
  };

  const edit = (id: number, mod: boolean) => {
    setTodoList(
      todoList.map((item) => {
        if (item.id === id) {
          item.isEdit = mod;
        }

        return item;
      })
    );
  };

  const save = (id: number) => {};

  return (
    <>
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
        {todoList.length == 0 && (
          <li className="list-group-item">데이터가 없습니다.</li>
        )}

        {todoList.map((item) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              {!item.isEdit && <span className="me-1">{item.memo}</span>}
              {!item.isEdit && (
                <span style={{ fontSize: "0.75rem" }}>
                  -{" "}
                  {getTimeString(
                    item.modifyTime ? item.modifyTime : item.createTime
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
                  del(item.id);
                }}
              >
                삭제
              </button>
            )}

            {item.isEdit && (
              <button
                className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
                onClick={() => {
                  save(item.id);
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
    </>
  );
};

export default Todo;
