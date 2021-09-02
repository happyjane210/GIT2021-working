import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router";
import { AppDispatch, RootState } from "../../store";

const ContactDetail = () => {
  const contactData = useSelector((state: RootState) => state.contact.data);
  const dispatch = useDispatch<AppDispatch>();
  const history = useHistory();

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
              <td>{}</td>
            </tr>
            <tr>
              <th>PHONE</th>
              <td></td>
            </tr>
            <tr>
              <th>E-MAIL</th>
              <td></td>
            </tr>
            <tr>
              <th>MEMO</th>
              <td></td>
            </tr>
          </tbody>
        </table>
      </form>

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
          className="btn btn-outline-danger float-end"
          //   onClick={() => {
          //     clickAdd();
          //   }}
        >
          삭제
          <i className="bi bi-trash-fill ms-1" />
        </button>
        <button
          className="btn btn-outline-info float-end me-2"
          //   onClick={() => {
          //     clickAdd();
          //   }}   bi bi-scissors
        >
          수정
          <i className="bi bi-vector-pen ms-1" />
        </button>
      </div>
    </div>
  );
};

export default ContactDetail;
