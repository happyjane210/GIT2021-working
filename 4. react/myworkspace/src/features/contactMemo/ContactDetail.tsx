import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router";
import { AppDispatch, RootState } from "../../store";
import { requestRemoveContact } from "./contactSaga";

const ContactDetail = () => {
  const { id } = useParams<{ id: string }>();
  console.log(id);

  const contactItem = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );

  const isRemoveCompleted = useSelector(
    (state: RootState) => state.contact.isRemoveCompleted
  );

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  // 감지 후 에러 없으면 실행
  useEffect(() => {
    isRemoveCompleted && history.push("/ContactMemo");
    console.log("--useEffect RemoveCompleted--");
  }, [isRemoveCompleted, history]);

  const clickDelet = () => {
    dispatch(requestRemoveContact(+id)); // saga action으로 대체
    history.push("/ContactMemo");
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>Contact Details</b>
      </h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>NAME</th>
              <td>{contactItem?.name}</td>
            </tr>
            <tr>
              <th>PHONE</th>
              <td>{contactItem?.phone}</td>
            </tr>
            <tr>
              <th>E-MAIL</th>
              <td>{contactItem?.email}</td>
            </tr>
            <tr>
              <th>MEMO</th>
              <td>{contactItem?.memo}</td>
            </tr>
            <tr>
              <th>Created Time</th>
              <td>{contactItem?.createdTime}</td>
            </tr>
          </tbody>
        </table>
      </form>

      <div className="d-flex mt-3">
        <div style={{ width: "50%" }}>
          <button
            className="btn btn-outline-secondary"
            onClick={() => {
              history.push("/ContactMemo");
            }}
          >
            <i className="bi bi-grid-fill me-1"></i>
            목록
          </button>
        </div>
        <div style={{ width: "50%" }} className="d-flex justify-content-end">
          <button
            className="btn btn-outline-info me-1"
            onClick={() => {
              history.push(`/ContactMemo/ContactEdit/${id}`);
            }}
          >
            수정
            <i className="bi bi-vector-pen ms-1" />
          </button>
          <button
            className="btn btn-outline-danger"
            onClick={() => {
              clickDelet();
            }}
          >
            삭제
            <i className="bi bi-trash-fill" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default ContactDetail;
