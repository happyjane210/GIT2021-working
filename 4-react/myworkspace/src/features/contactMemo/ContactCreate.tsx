import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import { AppDispatch, RootState } from "../../store";
import { ContactItem } from "./contactSlice";
import { requestAddContact } from "./contactSaga";
import Alert from "../../components/alert/Alert";

const ContactCreate = () => {
  const [isError, setIsError] = useState(false);

  // 11. 입력폼에 ref 객체 연결
  const nameInput = useRef<HTMLInputElement>(null);
  const phoneInput = useRef<HTMLInputElement>(null);
  const emailInput = useRef<HTMLInputElement>(null);
  const memoInput = useRef<HTMLTextAreaElement>(null);

  // 12. 셀렉터, 디스패치, 히스토리 가져오기
  // 컨텍트 데이터 가져오기
  const contactData = useSelector((state: RootState) => state.contact.data);
  // dispatch 함수 만들기
  const dispatch = useDispatch<AppDispatch>();
  // history 객체 가져오기
  const history = useHistory();

  // 13. clickAdd 함수 만들기
  const clickAdd = () => {
    // 필수값 없으면 경고창
    if (
      !nameInput.current?.value ||
      !phoneInput.current?.value ||
      !emailInput.current?.value
    ) {
      setIsError(true);
      return;
    }

    // 13. 추가할 객체 생성
    const item: ContactItem = {
      id: contactData.length ? contactData[0].id + 1 : 1,
      name: nameInput.current?.value,
      phone: phoneInput.current?.value,
      email: emailInput.current?.value,
      memo: memoInput.current?.value,
      createdTime: new Date().getTime(),
    };

    console.log(item);

    // 14. 디스패칭 dispatch(action함수)
    dispatch(requestAddContact(item));

    history.push("/ContactMemo");
  };

  // 10. 추가할 화면 생성
  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>Contact Creator</b>
      </h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>NAME</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  placeholder="Name"
                  ref={nameInput}
                />
              </td>
            </tr>
            <tr>
              <th>PHONE</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  placeholder="Phone number"
                  ref={phoneInput}
                />
              </td>
            </tr>
            <tr>
              <th>E-MAIL</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  placeholder="E-mail address"
                  ref={emailInput}
                />
              </td>
            </tr>
            <tr>
              <th>MEMO</th>
              <td>
                <textarea
                  className="form-control"
                  rows={10}
                  placeholder="Leave a massage...  (Optional)"
                  ref={memoInput}
                ></textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </form>

      {isError && (
        <Alert
          message="Please enter contact infomations."
          variant={"warning"}
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      <div className="mt-3">
        <button
          className="btn btn-outline-secondary float-start"
          onClick={() => {
            history.push("/ContactMemo");
          }}
        >
          <i className="bi bi-grid-fill me-1"></i>
          목록
        </button>
        <button
          className="btn btn-outline-primary float-end"
          onClick={() => {
            clickAdd();
          }}
        >
          저장
          <i className="bi bi-check ms-1" />
        </button>
      </div>
    </div>
  );
};

export default ContactCreate;
