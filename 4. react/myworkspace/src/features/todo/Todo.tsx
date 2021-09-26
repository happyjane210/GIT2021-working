import { RootState } from "../../store";
import { useSelector } from "react-redux";
import { useRef, useState } from "react";
import { TodoState } from "./type"; // ./type (뒤에) 뭐가 없으면 폴더 안에 index.ts/js/tsx 등을 로딩함
import TodoEditModal from "./TodoEditModal";
import Alert from "../../components/alert/Alert";
import produce from "immer";
//import style from "../profile/Profile.module.scss";

const getTimeString = (unixTime: number) => {
  const now = new Date(); // 현재날짜-시간객체
  now.getTime(); // 현재시간의 밀리세컨드
  // 1초:1000 ms
  // 1분: 60 * 1000 ms
  // 1시간 : 60 * 60 * 1000 ms
  // 1일 : 24 * 60 * 60 * 1000 ms
  const day = 24 * 60 * 60 * 1000;

  //Locale: timeZone, currency 등
  //js 에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixTime);

  // 현재시간 보다 24시간 이전이면 날짜를 보여주고
  // 현재시간 보다 24시간 미만이면 시간을 보여줌
  return unixTime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
};

const Todo = () => {
  // profile state를 가져옴 + state 가 변경되면 컴포넌트를 업데이트함
  const profile = useSelector((state: RootState) => state.profile);

  const [todoList, setTodoList] = useState<TodoState[]>([
    {
      id: 2,
      memo: "환영합니다🎉",
      username: profile.username,
      createTime: new Date().getTime(),
    },
    {
      id: 1,
      memo: "안녕하세요😄",
      username: profile.username,
      createTime: new Date().getTime(),
    },
  ]);

  // 수정팝업을 띄울지 아닐지
  const [isEdit, setIsEdit] = useState(false);

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
      username: profile.username,
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

  // 컴포넌트가 업데이트 되도 유지할수있는 변수
  // useRef 무언가 참고하는 변수
  const eidtItem = useRef<TodoState>({
    id: 0,
    memo: "",
    username: profile.username,
    createTime: 0,
  });

  // 모달팝업을 true 띄우기, false 닫기
  const edit = (item: TodoState) => {
    eidtItem.current = item;
    setIsEdit(true);
  };

  // 7. todo 변수값을 eidtItem에 받아서 함수 실행
  const save = (eidtItem: TodoState) => {
    setTodoList(
      // state 업데이트
      produce((state) => {
        // item 이라는 변수 생성        전체아이디 중에서 === 변경 해당 아이디와 같은 id찾음 => 변경된 id 찾아서
        const item = state.find((item) => item.id === eidtItem.id);
        if (item) {
          // 해당 id 찾고 변경된 텍스트값으로 memo 변환
          item.memo = eidtItem.memo;
        }
      })
    );

    // 모달창닫기
    setIsEdit(false);
  };

  // const edit = (id: number, mod: boolean) => {
  //   setTodoList(
  //     todoList.map((item) => {
  //       if (item.id === id) {
  //         item.isEdit = mod;
  //       }

  //       return item;
  //     })
  //   );
  // };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>할 일 관리</b>
      </h2>

      {/* Feed 에 profile 추가 */}
      {/* <div className="d-flex card-header">
        <div
          className={`${style.thumb} me-1`}
          style={{ backgroundImage: `url(${profile.image})` }}
        ></div>
        <span className={`${style.username} `}>{profile.username}</span>
      </div> */}
      {/* Feed 에 profile 추가 */}

      {isEdit && (
        <TodoEditModal // 리액트에서 컴포넌트는 함수, 반환하는 값이 jsx.Element
          item={eidtItem.current} // ModalProp 리액트에서 prop은 매개변수임
          onClose={() => {
            setIsEdit(false);
          }}
          onSave={(editItem) => {
            save(editItem);
            // 1. onSave 속성으로 이 함수를 TodoEditModal({item,onClose,onSave}) 로 통째로 전달  : [Props Down]
            // 6. 저쪽 한바퀴 돌고 todo 객체 내용을 editItem에 담아서 save() 함수 실행
            // 6. TodoState 의 [id 기존, memo 수정된 텍스트입력값, createTime 기존 ] -> save() 함수에 반영
          }}
        />
      )}
      {/* isEdit state가 true 일 때만 Modal창 보임 
      onClose 속성으로 함수를 TodoEditModal 의 속성으로 보냄*/}

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
          className="btn btn-outline-primary text-nowrap"
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
        {todoList.length === 0 && (
          <li className="list-group-item">데이터가 없습니다.</li>
        )}

        {todoList.map((item) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              {!item.isEdit && <span className="me-1">{item.memo}</span>}
              {!item.isEdit && (
                <span style={{ fontSize: "0.75rem" }}>
                  -{item.username}, {getTimeString(item.createTime)}
                </span>
              )}

              {item.isEdit && (
                <input type="text" className="w-100" defaultValue={item.memo} />
              )}
            </div>

            <button
              className="btn btn-outline-secondary btn-sm ms-2 me-1 text-nowrap"
              onClick={() => {
                edit(item); // 수정창 모달팝업 띄우기
              }}
            >
              수정
            </button>
            {/* 수정 버튼 누르면 edit 함수로 가서 기본값false 인 isEdit state를 true로 바꾸고 창에 보여짐 */}

            <button
              className="btn btn-outline-secondary btn-sm  text-nowrap"
              onClick={() => {
                del(item.id);
              }}
            >
              삭제
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Todo;
