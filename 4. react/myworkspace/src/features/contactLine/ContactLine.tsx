import produce from "immer";
import React, { useEffect, useRef, useState } from "react";
import Alert from "../../components/Alert";
import { ContactItem } from "../contactMemo/contactSlice";

import api from "./contactApi";

interface ContactLineState {
  id: number;
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
  isEdit?: boolean; // 수정모드인지
}

const Contact = () => {
  const [conList, setConList] = useState<ContactLineState[]>([
    { id: 1, name: "Name", phone: "010-1234-5678", email: "example@123.com" },
  ]);

  const [isError, setIsError] = useState<boolean>(false);
  //const [isEmpty, setIsEmpty] = useState<boolean>(false);

  const inputRef1 = useRef<HTMLInputElement>(null);
  const inputRef2 = useRef<HTMLInputElement>(null);
  const inputRef3 = useRef<HTMLInputElement>(null);
  const tbodyRef = useRef<HTMLTableSectionElement>(null);
  const inputEdit = useRef<HTMLInputElement>(null);
  //2가지 기존거를 쓴다. 2. 새로운 ref를 쓴다. <되는데 버그 터짐>
  //해보면 도움이 된다 왜냐 내가 이문제로 선생님한테 2번 빠꾸 먹고 고쳐서 3트만에 성곡한 부분
  //쌤 투두를 참고하세요
  // 다 알려줬음 쌤이 알려주면 독푸는 거라 그래서 ㄷㄷ;
  const formRef = useRef<HTMLFormElement>(null);

  const fetchData = async () => {
    // 응답 데이터 타입 자동으로 결정
    const res = await api.fetch();

    // axios에서 응답받은 데이터는 data 속성에 들어가 있음
    // 서버로부터 받은 데이터를 state 객체로 변환함
    const contacts = res.data.map((item) => ({
      id: item.id,
      name: item.name,
      phone: item.phone,
      email: item.email,
    })) as unknown as ContactLineState[];

    setConList(contacts);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const add = async (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    if (
      !inputRef1.current?.value ||
      !inputRef2.current?.value ||
      !inputRef3.current?.value
    ) {
      setIsError(true);
      return;
    }

    //-------------백엔드 연동부분------------------------------
    const result = await api.add({
      name: inputRef1.current?.value,
      phone: inputRef2.current?.value,
      email: inputRef3.current?.value,
    });
    console.log(result);

    //------------- state 변경 부분-----서버에서 넘어온 데이터로수정------------
    const con: ContactLineState = {
      id: result.data.id,
      name: result.data.name,
      phone: result.data.phone,
      email: result.data.email,
    };

    // 프론트 Add
    // const con: ContactLineState = {
    //   id: conList.length > 0 ? conList[0].id + 1 : 1,
    //   name: inputRef1.current?.value,
    //   phone: inputRef2.current?.value,
    //   email: inputRef3.current?.value,
    // };

    setConList([con, ...conList]);

    formRef.current?.reset();

    setIsError(false);
  };

  const del = async (id: number, index: number) => {
    console.log(id);

    // -------------백엔드 연동부분------------------------------
    const result = await api.remove(id);
    console.log(result);

    //-------------- state 변경 부분-----------------------------
    setConList(
      produce((state) => {
        state.splice(index, 1);
      })
    );

    // list 처리 방식, 해당하는 id 만 제외하고 다시 만든다
    //setConList(conList.filter((item) => item.id !== id));
  };

  const edit = (id: number, isEdit: boolean) => {
    setConList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.isEdit = isEdit;
        }
      })
    );
  };

  const save = async (id: number, index: number) => {
    //tbody에서 몇번째 tr인지 명시를 해줘야함
    const tr = tbodyRef.current?.querySelectorAll("tr")[index];
    const inputName = tr?.querySelectorAll("input"); //NodeList
    const inputArr = Array.prototype.slice.call(inputName); //NodeList ->Array

    // -------------백엔드 연동부분------------------------------
    const result = await api.modify(id, {
      name: inputArr[0].value,
      phone: inputArr[1].value,
      email: inputArr[2].value,
    });

    //--------------수정된 state 변경 부분----------백엔드 수정 처리---------
    setConList(
      produce((state) => {
        const item = state.find((item) => item.id === id);
        if (item) {
          item.name = result.data.name;
          item.phone = result.data.phone;
          item.email = result.data.email;
          item.isEdit = false;
        }
      })
    );
  };

  return (
    <div style={{ width: "60vw" }} className="mx-auto">
      <div>
        <h2 className="text-center my-5">
          <b>CONTACT LIST</b>
        </h2>

        <form
          className="d-flex"
          ref={formRef}
          onSubmit={(e) => e.preventDefault()}
        >
          <input
            type="text"
            className="form-control me-2 text"
            placeholder="NAME"
            ref={inputRef1}
            onKeyPress={(e) => {
              add(e);
            }}
          />
          <input
            type="text"
            className="form-control me-2 text"
            placeholder="010-XXXX-XXXX"
            ref={inputRef2}
            onKeyPress={(e) => {
              add(e);
            }}
          />
          <input
            type="text"
            className="form-control me-2 text"
            placeholder="e-mail@mail.com"
            ref={inputRef3}
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
            ADD
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

        <table className="table table-striped my-5">
          <thead>
            <tr>
              <th>No.</th>
              <th>NAME</th>
              <th>PHONE</th>
              <th>E-MAIL</th>
              <th>FUNCTION</th>
            </tr>
          </thead>
          <tbody ref={tbodyRef}>
            {conList.length == 0 && (
              <tr className="text-center">
                <td colSpan={5}>⛔ NOT FOUND DATA 4️⃣0️⃣4️⃣ ⛔</td>
              </tr>
            )}

            {conList.map((item, index) => (
              <>
                {!item.isEdit && (
                  <tr>
                    <td>
                      <b>{item.id}</b>
                    </td>
                    <td>{item.name}</td>
                    <td>{item.phone}</td>
                    <td>{item.email}</td>
                    <td>
                      <button
                        className="btn btn-outline-success btn-sm me-md-1"
                        onClick={() => {
                          edit(item.id, true); // 수정창 띄우기
                        }}
                      >
                        수정
                      </button>
                      <button
                        className="btn btn-outline-secondary btn-sm"
                        onClick={() => {
                          del(item.id, index);
                        }}
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                )}

                {item.isEdit && (
                  <tr>
                    <td>{item.id}</td>
                    <td>
                      <input
                        type="text"
                        defaultValue={item.name}
                        ref={inputEdit}
                        className="form-control me-2 text"
                        onKeyPress={(e) => {
                          if (e) {
                            if (e.code !== "Enter") return;
                          }
                          save(item.id, index);
                        }}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        defaultValue={item.phone}
                        ref={inputEdit}
                        className="form-control me-2 text"
                        onKeyPress={(e) => {
                          if (e) {
                            if (e.code !== "Enter") return;
                          }
                          save(item.id, index);
                        }}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        defaultValue={item.email}
                        ref={inputEdit}
                        className="form-control me-2 text"
                        onKeyPress={(e) => {
                          if (e) {
                            if (e.code !== "Enter") return;
                          }
                          save(item.id, index);
                        }}
                      />
                    </td>
                    <td>
                      <button
                        className="btn btn-outline-success btn-sm me-md-1"
                        onClick={() => {
                          save(item.id, index);
                        }}
                      >
                        저장
                      </button>

                      <button
                        className="btn btn-outline-secondary btn-sm"
                        onClick={() => {
                          edit(item.id, false);
                        }}
                      >
                        취소
                      </button>
                    </td>
                  </tr>
                )}
              </>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Contact;
