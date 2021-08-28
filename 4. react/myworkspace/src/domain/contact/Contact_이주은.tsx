import React, { useRef, useState } from "react";
import Alert from "../base/Alert";

interface ContactState {
  id: number;
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
  isEdit?: boolean; // 수정모드인지
}

const Contact = () => {
  const [conList, setConList] = useState<ContactState[]>([
    { id: 1, name: "Name", phone: "010-1234-5678", email: "example@123.com" },
  ]);
  const [isError, setIsError] = useState(false);
  const [isEdit, setIsEdit] = useState(false);

  const inputRef1 = useRef<HTMLInputElement>(null);
  const inputRef2 = useRef<HTMLInputElement>(null);
  const inputRef3 = useRef<HTMLInputElement>(null);

  const formRef = useRef<HTMLFormElement>(null);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
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

    const con: ContactState = {
      id: conList.length > 0 ? conList[0].id + 1 : 1,
      name: inputRef1.current?.value,
      phone: inputRef2.current?.value,
      email: inputRef3.current?.value,
    };

    setConList([con, ...conList]);

    formRef.current?.reset();

    setIsError(false);
  };

  const del = (id: number) => {
    setConList(conList.filter((item) => item.id !== id));
  };

  const edit = (item: ContactState) => {
    setIsEdit(true);
  };

  return (
    <>
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
          <tbody>
            {conList.map((item) => (
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
                          edit(item); // 수정창 모달팝업 띄우기
                        }}
                      >
                        EDIT
                      </button>
                      <button
                        className="btn btn-outline-secondary btn-sm"
                        onClick={() => {
                          del(item.id);
                        }}
                      >
                        DELETE
                      </button>
                    </td>
                  </tr>
                )}

                {item.isEdit && (
                  <tr>
                    <td>{item.id}</td>
                    <td>
                      <input type="text" />
                    </td>
                    <td>
                      <input type="text" />
                    </td>
                    <td>
                      <input type="text" />
                    </td>
                    <td>
                      <button></button>
                    </td>
                    <td>
                      <button></button>
                    </td>
                  </tr>
                )}
              </>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default Contact;
