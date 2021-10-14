import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { requestFetchContacts } from "./contactSaga";

const ContactMemo = () => {
  //7. 컴포넌트에 selector지정
  // state에 contact state를 가지고옴

  const contact = useSelector((state: RootState) => state.contact);
  //   const contact = useSelector((state: RootState) => state.contact);
  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    console.log(dispatch);
    console.log(contact.isFetched);

    // 데이터 fetch가 안되어있으면 데이터를 받아옴
    if (!contact.isFetched) {
      // 서버에서 데이터를 받아오는 action 을 디스패치함
      dispatch(requestFetchContacts());
    }
  }, [dispatch, contact.isFetched]);

  return (
    <div style={{ width: "60vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>📞CONTACT MANAGER📞</b>
      </h2>
      {/* 9. 추가 버튼 -> 추가화면 */}
      <div className="d-flex justify-content-end my-2">
        <button
          className="btn btn-secondary me-2"
          onClick={() => {
            dispatch(requestFetchContacts());
          }}
        >
          REFRESH
          <i className="bi bi-arrow-counterclockwise ms-2"></i>
        </button>

        <button
          className="btn btn-primary"
          onClick={() => {
            history.push("/ContactMemo/ContactCreate");
          }}
        >
          ADD
          <i className="bi bi-plus ms-2"></i>
        </button>
      </div>

      <table className="table table-hover my-5">
        <thead>
          <tr>
            <th>No.</th>
            <th>NAME</th>
            <th>PHONE</th>
            <th>E-MAIL</th>
            <th>Create Time</th>
          </tr>
        </thead>
        <tbody>
          {contact.data.length === 0 && (
            <tr className="text-center">
              <td colSpan={5}>⛔ NOT FOUND DATA 4️⃣0️⃣4️⃣ ⛔</td>
            </tr>
          )}
          {/* 8. state 데이터 배열을 map 함수로 출력 */}
          {contact.data.map((item, index) => (
            <tr
              key={item.id}
              onClick={() => {
                history.push(`/ContactMemo/ContactDetail/${item.id}`);
              }}
            >
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.phone}</td>
              <td>{item.email}</td>
              <td>{item.createdTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ContactMemo;
