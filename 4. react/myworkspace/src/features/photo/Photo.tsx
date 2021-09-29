import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { getTimeString } from "../../common/lib/string";
import Pagination from "../../components/Pagination";
import { AppDispatch, RootState } from "../../store";
import { requestFetchPagingPhotos, requestFetchPhotos } from "./photoSaga";
//import style from "../profile/Profile.module.scss";

const getTiemString = (unixtime: number) => {
  // 1초: 1000
  // 1분: 60 * 1000
  // 1시간: 60 * 60 * 1000
  // 1일: 24 * 60 * 60 * 1000
  const day = 24 * 60 * 60 * 1000;

  const dateTime = new Date(unixtime);

  return unixtime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
};

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
      // dispatch(requestFetchPhotos());
      dispatch(
        requestFetchPagingPhotos({
          page: 0,
          size: photo.pageSize,
        })
      );
    }
  }, [dispatch, photo.isFetched, photo.pageSize]);

  // 콜백함수  4. onPageChanged(currentBlock * blockSize - 1); = 배열[1]
  const handlePageChanged = (page: number) => {
    console.log("--page: " + page);
    // setCurrentPage(page);
    dispatch(
      requestFetchPagingPhotos({
        page, // 페이지 [1]:배열 => (2):화면  를 백엔드에 요청.
        size: photo.pageSize, // 한페이지에 보여줄 사진개수 2개
      })
    );
  };

  const handlePageSizeChanged = (e: React.ChangeEvent<HTMLSelectElement>) => {
    console.log(e.currentTarget.value);
    dispatch(
      requestFetchPagingPhotos({
        page: photo.page,
        size: +e.currentTarget.value,
      })
    );
  };

  return (
    <div>
      <h2 className="text-center">Photos</h2>
      {/* 버튼 */}
      <div className="d-flex justify-content-end mb-2">
        <select
          className="form-select form-select-sm me-2"
          style={{ width: "60px" }}
          onChange={(e) => {
            handlePageSizeChanged(e);
          }}
        >
          {[2, 4, 8, 12].map((size) => (
            <option value={size} selected={photo.pageSize === size}>
              {size}
            </option>
          ))}
        </select>
        <button
          className="btn btn-secondary me-2"
          onClick={() => {
            dispatch(requestFetchPhotos());
          }}
        >
          <i className="bi bi-arrow-clockwise"></i>
          새로고침
        </button>
        <button
          className="btn btn-primary"
          onClick={() => {
            history.push("/photos/create");
          }}
        >
          <i className="bi bi-plus" />
          추가
        </button>
      </div>
      {/* 컨텐트 */}
      <div className="d-flex flex-wrap">
        {/* state 데이터 배열에 map함수로 출력 */}
        {photo.data.map((item, index) => (
          <div
            key={`photo-item-${index}`}
            className="card"
            style={{
              //  100를 초과된 마진 1rem 씩을
              width: "calc((100% - 3rem) / 4)", // (총길이(100%) - 초과 공백 3rem) / 4  // (103-3)=(100/4)
              marginLeft: index % 4 === 0 ? "0" : "1rem", // 사진 사이에 3개 공백 , 1rem
              marginTop: index > 3 ? "1rem" : "0",
            }}
          >
            {/* 컨텐트 wrapper -- 시작 */}
            <div
              style={{ cursor: "pointer" }}
              onClick={() => {
                // id값을 물고 이동해야함
                history.push(`/photos/detail/${item.id}`);
              }}
            >
              <img
                src={item.photoUrl}
                className="card-img-top"
                alt={item.title}
              />
              <div className="card-body">
                <h5 className="card-title">{item.title}</h5>
                <h6 className="text-muted">
                  {getTimeString(item.createdTime)}
                </h6>
              </div>
            </div>
            {/* 컨텐트 wrapper -- 끝 */}
          </div>
        ))}
      </div>
      {/* 페이지네이션 */}
      <div className="d-flex justify-content-center mt-4">
        <Pagination
          blockSize={3} // 고정값
          totalPages={photo.totalPages}
          currentPage={photo.page}
          onPageChanged={handlePageChanged} // 1. 콜백함수
        />
      </div>
    </div>
  );
};

export default Photo;
