import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { RootState } from "../../store";

const Photo = () => {
  //7. 컴포넌트에 selector 지정
  // state 에 photo state를 가지고 오겠다
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();

  return (
    <div>
      <h2 className="text-center">
        <b>Photos</b>
      </h2>
      {/* 9. 추가버튼 -> 추가화면 */}
      <div className="d-flex justify-content-end my-2">
        <button
          className="btn btn-primary"
          onClick={() => {
            history.push("/Photo/Create");
          }}
        >
          추가
          <i className="bi bi-plus ms-2"></i>
        </button>
      </div>
      <div className="d-flex flex-wrap">
        {/* 8. state 데이터 배열에 map 함수로 출력 */}
        {photo.data.map((item, index) => (
          <div
            key={`photo-item-${index}`}
            className="card"
            style={{
              width: "calc((100% - 3rem) / 4)",
              marginLeft: index % 4 === 0 ? "0" : "1rem",
              marginTop: index > 3 ? "1rem" : "0",
            }}
          >
            <div className="card-header">
              <img width={24} src={item.profileUrl} alt={item.username} />
              <span className="ms-2">{item.username}</span>
            </div>

            <img
              src={item.photoUrl}
              alt={item.title}
              className="card-img-top"
              style={{ cursor: "pointer" }}
              // onClick={() => {
              //   history.push(`/photos/${item.id}`);
              // }}
            />

            <div className="card-body">
              <h5 className="card-title">{item.title}</h5>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Photo;
