import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router";
import { AppDispatch, RootState } from "../../store";
import { removeContact } from "./contactSlice";

const ContactDetail = () => {
  const { id } = useParams<{ id: string }>();
  console.log(id);

  const contactData = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );
  console.log(contactData);

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  const clickDelet = () => {
    dispatch(removeContact(+id));
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
              <td>{contactData?.name}</td>
            </tr>
            <tr>
              <th>PHONE</th>
              <td>{contactData?.phone}</td>
            </tr>
            <tr>
              <th>E-MAIL</th>
              <td>{contactData?.email}</td>
            </tr>
            <tr>
              <th>MEMO</th>
              <td>{contactData?.memo}</td>
            </tr>
            <tr>
              <th>Created Time</th>
              <td>{contactData?.createdTime}</td>
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
