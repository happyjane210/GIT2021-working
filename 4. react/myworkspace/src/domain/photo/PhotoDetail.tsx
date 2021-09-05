import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router";
import { AppDispatch, RootState } from "../../store";
import { removePhoto } from "./photoSlice";

const PhotoDetail = () => {
  // 3. 상세 컴포넌트에서 id 값을 가져와야함
  // useParams<제너릭 타입>, 매개변수들을 객체화할 형식
  // Generic 타입: 타입을 매개변수로 넣음
  // 타입에 따라 처리를 다르게 하기 위함
  // 객체지향 다형성(ploy mophism) :같은 이름의 함수가 내부적으로 처리를 다르게 해줌
  const { id } = useParams<{ id: string }>();
  console.log(id);

  // 타입 단언을 하지 않으면 추론에 의해서 PhotoItem | undefined 형식이됨
  const photoItem = useSelector((state: RootState) =>
    state.photo.data.find((item) => item.id === +id)
  ); // 반환형식을 타입추론으로 처리
  //  as PhotoItem 타입단언 (type assertion)
  console.log(photoItem);

  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  const handleDelete = () => {
    dispatch(removePhoto(+id)); // id값만 넣어서 삭제
    history.push("/Photo"); // 목록화면으로 이동
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto my-5">
      <h2 className="text-center my-5">
        <b>Photo Detail</b>
      </h2>

      {/* {!photoItem && <div className="text-center">데이터가 없습니다.</div>} */}

      <table className="table">
        <tbody>
          <tr>
            <th>제목</th>
            <td>{photoItem?.title}</td>
          </tr>
          <tr>
            <th>설명</th>
            <td>{photoItem?.description}</td>
          </tr>
          <tr>
            <th>이미지</th>
            <td>
              <img
                style={{ width: "25vw" }}
                src={photoItem?.photoUrl}
                alt={photoItem?.title}
              />
            </td>
          </tr>
        </tbody>
      </table>

      <div className="d-flex">
        <div style={{ width: "50%" }}>
          <button
            className="btn btn-outline-secondary"
            onClick={() => {
              history.push("/Photo");
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
              history.push(`/Photo/edit/${id}`);
            }}
          >
            수정
            <i className="bi bi-vector-pen ms-1" />
          </button>

          <button
            className="btn btn-outline-danger"
            onClick={() => {
              handleDelete();
            }}
          >
            삭제
            <i className="bi bi-trash-fill ms-1" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default PhotoDetail;
