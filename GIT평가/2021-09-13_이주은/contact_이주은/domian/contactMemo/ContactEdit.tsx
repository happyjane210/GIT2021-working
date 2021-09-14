import { useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router";
import { AppDispatch, RootState } from "../../store";
import { saveContact } from "./contactSlice";

const ContactEdit = () => {
  const { id } = useParams<{ id: string }>();
  console.log(id);

  const contactData = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  const newName = useRef<HTMLInputElement>(null);
  const newPhone = useRef<HTMLInputElement>(null);
  const newEmail = useRef<HTMLInputElement>(null);
  const newMemo = useRef<HTMLTextAreaElement>(null);

  const clickSave = () => {
    if (contactData) {
      const item = { ...contactData };
      item.name = newName.current?.value;
      item.phone = newPhone.current?.value;
      item.email = newEmail.current?.value;
      item.memo = newMemo.current?.value;

      dispatch(saveContact(item));
      history.push("/ContactMemo");
    }
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>Contact Edit</b>
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
                  defaultValue={contactData?.name}
                  ref={newName}
                />
              </td>
            </tr>
            <tr>
              <th>PHONE</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  defaultValue={contactData?.phone}
                  ref={newPhone}
                />
              </td>
            </tr>
            <tr>
              <th>E-MAIL</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  defaultValue={contactData?.email}
                  ref={newEmail}
                />
              </td>
            </tr>
            <tr>
              <th>MEMO</th>
              <td>
                <textarea
                  className="form-control"
                  rows={10}
                  defaultValue={contactData?.memo}
                  ref={newMemo}
                ></textarea>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div className="d-flex">
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
            className="btn btn-outline-primary me-1"
            onClick={() => {
              clickSave();
            }}
          >
            저장
            <i className="bi bi-check ms-1" />
          </button>

          <button
            className="btn btn-outline-danger"
            onClick={() => {
              history.push(`/ContactMemo/ContactDetail/${id}`);
            }}
          >
            취소
            <i className="bi bi-box-arrow-left ms-1" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default ContactEdit;
