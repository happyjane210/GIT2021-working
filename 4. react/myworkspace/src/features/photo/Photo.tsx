import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import Pagination from "../../components/Pagination";
import { AppDispatch, RootState } from "../../store";
import { requestFetchPhotos } from "./photoSaga";
//import style from "../profile/Profile.module.scss";

const Photo = () => {
  //const profile = useSelector((state: RootState) => state.profile);
  //7. 컴포넌트에 selector 지정
  // state 에 photo state를 가지고 오겠다
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();
  const dispatch = useDispatch<AppDispatch>();

  // 컴포넌트가 마운팅되는 시점에 실행
  // dispatch객체가 생성되고,
  // photo.isFetched를 가져올때와 바뀔때마다 실행됨
  // dispatch 객체는 위에서 생성된 후 바뀌지 않으므로
  // dispatch 객체에 따른 effect가 발생하지는 않음
  useEffect(() => {
    console.log(dispatch);
    console.log(photo.isFetched);

    // 데이터 fetch가 안되어있으면 데이터를 받아옴
    if (!photo.isFetched) {
      // 서버에서 데이터를 받아오는 action을 디스패치함
      dispatch(requestFetchPhotos()); // 1)
    }
  }, [dispatch, photo.isFetched]);

  return (
    <div>
      <h2 className="text-center my-5">
        <b>PHOTOS</b>
      </h2>

      {/* 버튼 */}
      {/* 리프레쉬 버튼 & 추가버튼 -> 추가화면*/}
      <div className="d-flex justify-content-end my-2">
        <button
          className="btn btn-secondary me-2"
          onClick={() => {
            dispatch(requestFetchPhotos());
          }}
        >
          REFRESH
          <i className="bi bi-arrow-counterclockwise ms-2"></i>
        </button>
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

      {/* 컨텐트 */}
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
            {/* 프로필 부분 */}
            {/* <div className="d-flex card-header">
              <div
                className={`${style.thumb} me-1`}
                style={{ backgroundImage: `url(${item.profileUrl})` }}
              ></div>
              <span className={`${style.username} `}>{item.username}</span>
            </div> */}

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
              <p>{item.createdTime}</p>
            </div>
          </div>
        ))}
      </div>

      {/* 페이지네이션 */}
      <div className="d-flex justify-content-center my-5">
        <Pagination pageBlockSize={2} totalPage={3} />
      </div>
    </div>
  );
};

export default Photo;
