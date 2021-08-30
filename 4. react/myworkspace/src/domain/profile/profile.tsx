// 일반적인 컴포넌트는 소문자, 리액트 컴포넌트는 대문자로 시작함
import { pic } from "../../common/data";
import style from "./Profile.module.scss";

const Profile = () => {
  return (
    <div>
      <div className="d-flex">
        <div
          style={{
            backgroundImage: `url(${pic})`,
          }}
          className={`${style.thumb} me-2`}
        ></div>
        <span className={`${style.username} text-light`}>Happy Jane</span>
      </div>
    </div>
  );
};

export default Profile;

// center, no-repeat, cover: 굉장히 중요 이렇게 해야 사진이 가운데로 맞춰짐

// 컴포넌트 안애서만 사용할 스타일 시트는 이렇게 만듬 - 클래스 이름 지정으로
// className=".Profile_thumb_1sp_F me-1"
// 스타일변수.클래스명
// 클래스명이 "모듈명_클래스명__"
