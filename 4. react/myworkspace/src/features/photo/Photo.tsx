import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { RootState } from "../../store";
import style from "../profile/Profile.module.scss";

const Photo = () => {
  const profile = useSelector((state: RootState) => state.profile);
  //7. 컴포넌트에 selector 지정
  // state 에 photo state를 가지고 오겠다
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();

  return (
    <div>
      <h2 className="text-center my-5">
        <b>PHOTOS</b>
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
        {/* 8. state 데이터 배열을 map 함수로 출력 */}
        {photo.data.map((item, index) => (
          // card 시작
          <div
            // key={`photo-item-${index}`}
            onClick={() => {
              // id 값을 물고 이동
              history.push(`/Photo/Detail/${item.id}`);
            }}
            className="card"
            style={{
              width: "calc((100% - 3rem) / 4)",
              marginLeft: index % 4 === 0 ? "0" : "1rem",
              marginTop: index > 3 ? "1rem" : "0",
              cursor: "pointer",
            }}
          >
            <div className="d-flex card-header">
              <div
                className={`${style.thumb} me-1`}
                style={{ backgroundImage: `url(${item.profileUrl})` }}
              ></div>
              <span className={`${style.username} `}>{item.username}</span>
            </div>

            <img
              src={item.photoUrl}
              alt={item.title}
              className="card-img-top"

              // onClick={() => {
              //   history.push(`/photos/${item.id}`);
              // }}
              // 사진 클릭하면 상세페이지 / 수정페이지
            />

            <div className="card-body">
              <h5 className="card-title">{item.title}</h5>
              <p>{item.createTime}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Photo;
