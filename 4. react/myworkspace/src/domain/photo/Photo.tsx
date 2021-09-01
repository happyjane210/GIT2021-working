import { profile } from "console";
import React from "react";
import { useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { RootState } from "../../store";

const Photo = () => {
  const photo = useSelector((state: RootState) => state.photo);
  const history = useHistory();

  return (
    <div>
      <h2 className="text-center">PHOTOS</h2>
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
        {/* flex 가로배치, flex-wrap 화면 넘어가면 다음줄 정렬 */}
        {photo.data.map((item, index) => (
          <div
            className="card"
            style={{
              width: "calc((100% - 3rem) / 4)", // 화면 전체의 4등분 - 1rem 씩 공백
              marginLeft: index % 4 === 0 ? "0" : "1rem", // 사진 index 0 (첫번째)-> 왼쪽 여백없음, 그 다음은 1rem 씩 공백
              marginTop: index > 3 ? "1rem" : 0, // index 4부터 (다섯번째, 다음줄) 탑 1rem, 맨 윗줄은 공백 0
            }}
          >
            {/* 한줄에 4개만 나오게 */}
            <div className="card-header">
              <img
                width={24}
                height={24}
                src={item.profileUrl}
                alt={item.username}
              />
              <span>{item.username}</span>
            </div>
            <img
              src={item.photoUrl}
              className="card-img-top"
              alt={item.title}
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
