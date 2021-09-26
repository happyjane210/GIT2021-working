import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { requestModifyPhoto } from "./photoSaga";
import { modifyPhoto, PhotoItem } from "./photoSlice";

const PhotoEdit = () => {
  // useSelector 로  global state랑 묶어줌 (store)
  // useParams (id값 객체화 해서 불러와줌)
  const { id } = useParams<{ id: string }>();
  console.log(id);

  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  );

  const isModifyCompleted = useSelector(
    (state: RootState) => state.photo.isModifyCompleted
  );

  const dispatch = useDispatch<AppDispatch>();
  const history = useHistory();

  // 파일이 바뀔때 state를 업데이트 해서 img태그에 미리보기 나오기위함
  const [url, setUrl] = useState<string | undefined>(photoItem?.photoUrl);

  const titleInput = useRef<HTMLInputElement>(null);
  const descrInput = useRef<HTMLTextAreaElement>(null);
  const fileInput = useRef<HTMLInputElement>(null);

  useEffect(() => {
    isModifyCompleted && history.push("/Photo");
  }, [isModifyCompleted, history]);

  // 파일 선택 이벤트 처리
  const changeFile = () => {
    console.log("change");

    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        setUrl(reader.result?.toString());
      };
      reader.readAsDataURL(imageFile);
    }
  };

  const clickSave = () => {
    // 파일이 있을때 처리
    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        if (photoItem) {
          // 기존 데이터 카피
          const item = { ...photoItem };
          // 변경 속성만 대입
          item.title = titleInput.current ? titleInput.current.value : "";
          item.description = descrInput.current?.value;
          item.photoUrl = reader.result ? reader.result.toString() : "";
          item.fileType = imageFile.type;
          item.fileName = imageFile.name;

          saveItem(item);
        }
      };

      reader.readAsDataURL(imageFile);
    }
    // 파일이 없을때 처리
    else {
      if (photoItem) {
        // 기존 데이터 카피
        const item = { ...photoItem };
        // 변경 할 속성만 대입
        item.title = titleInput.current ? titleInput.current.value : "";
        item.description = descrInput.current?.value;

        saveItem(item);
      }
    }
  };

  const saveItem = (item: PhotoItem) => {
    // saga action으로 대체
    dispatch(requestModifyPhoto(item));

    history.push("/Photo");
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>Photo Edit</b>
      </h2>
      <form>
        <table className="table" style={{ width: "40vw" }}>
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  defaultValue={photoItem?.title}
                  ref={titleInput}
                />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  rows={5}
                  defaultValue={photoItem?.description}
                  ref={descrInput}
                ></textarea>
              </td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <img
                  style={{ width: "25vw" }}
                  src={url}
                  alt={photoItem?.title}
                />
                <input
                  className="form-control"
                  type="file"
                  accept="image/*"
                  ref={fileInput}
                  onChange={() => {
                    changeFile();
                  }}
                />
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
              history.push("/photo");
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
              history.push(`/Photo/Detail/${id}`);
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

export default PhotoEdit;
