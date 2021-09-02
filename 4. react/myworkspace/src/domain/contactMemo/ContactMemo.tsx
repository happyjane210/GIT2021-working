import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { RootState } from "../../store";

const ContactMemo = () => {
  //7. 컴포넌트에 selector지정
  // state에 contact state를 가지고옴

  const contact = useSelector((state: RootState) => state.contact);
  //   const contact = useSelector((state: RootState) => state.contact);
  const history = useHistory();

  return (
    <div style={{ width: "60vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>CONTACT MANAGER</b>
      </h2>
      {/* 9. 추가 버튼 -> 추가화면 */}
      <div className="d-flex justify-content-end my-2">
        <button
          className="btn btn-primary"
          onClick={() => {
            history.push("/ContactMemo/ContactCreate");
          }}
        >
          추가
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
          {/* 8. state 데이터 배열을 map 함수로 출력 */}
          {contact.data.map((item, index) => (
            <>
              <tr
                key={index}
                onClick={() => {
                  history.push("/ContactMemo/ContactDetail");
                }}
              >
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.phone}</td>
                <td>{item.email}</td>
                <td>{item.createTime}</td>
              </tr>
            </>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ContactMemo;
