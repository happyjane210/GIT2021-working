import { useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { addPhoto, PhotoItem } from "./photoSlice";

const PhotoCreate = () => {
  // 11. 입력폼에 ref 객체 연결
  const titleInput = useRef<HTMLInputElement>(null);
  const descrTxta = useRef<HTMLTextAreaElement>(null);
  const fileInput = useRef<HTMLInputElement>(null);

  // 12. 셀랙터, 디스패치, 히스토리 가져오기

  // 포토 데이터 배열 가져오기
  const photoData = useSelector((state: RootState) => state.photo.data);
  // 프로필 정보 가져오기
  const profile = useSelector((state: RootState) => state.profile);

  // dispatch 함수 만들기
  const dispatch = useDispatch<AppDispatch>();

  // history 객체 가져오기
  const history = useHistory();

  // 13. clickAdd 함수 만들기
  const clickAdd = () => {
    // console.log(titleInput.current?.value);
    // console.log(descrTxta.current?.value);
    // if (fileInput.current?.files?.length) {
    //   console.log(fileInput.current?.files[0]);
    // }

    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        // 13. 새로 추가할 객체 생성

        const item: PhotoItem = {
          // 기존 데이터의 id 중에서 가장 큰 것 +1
          id: photoData.length ? photoData[0].id + 1 : 1,
          // 프로필 정보
          profileUrl: profile.image ? profile.image : "",
          username: profile.username ? profile.username : "",
          // 입력 정보
          title: titleInput.current ? titleInput.current.value : "",
          description: descrTxta.current?.value,
          photoUrl: reader.result ? reader.result.toString() : "",
          fileType: imageFile.type,
          fileName: imageFile.name,
          // 시스템 값 (작성일시, 수정일시, 수정한사람...)
          createTime: new Date().toLocaleTimeString(),
        };
        console.log(item);

        // *** 디스패칭
        // 1. addPhoto 함수에서 Action 객체를 생성함
        //   -> {type: "photo/addPhoto", payload:item}
        // 2. Action 객체를 Dispatcer에 전달함
        // 3. Dispatcher에 Action.type에 맞는 리듀서 함수를 실행
        //   -> 기존 state와 payload를 매개변수로 넣어주고 실행
        //dispatch(addPhoto(item));

        // action creator를 사용하지 않고 아래 방법으로도 가능
        // type: slice이름: reducer함수이름
        // payload: PayloadeActon<페이로드타입> 에 맞는 데이터 객체

        //예)
        // type: <photo />
        // payload: item

        // 14-2. 디스패칭 dispatch(action함수)
        dispatch(addPhoto(item));
        // 예시, 둘다 가능
        // dispatch({
        //   type: "photo/addPhoto",
        //   payload: item,
        // });

        history.push("/photo");
      };
      reader.readAsDataURL(imageFile);
    }
  };

  // 9. 추가할 화면 생성
  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>Photos Creator</b>
      </h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input className="form-control" type="text" ref={titleInput} />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  rows={10}
                  ref={descrTxta}
                ></textarea>
              </td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <input
                  className="form-control"
                  type="file"
                  accept="image/*"
                  ref={fileInput}
                />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div>
        <button
          className="btn btn-outline-secondary float-start"
          onClick={() => {
            history.push("/photo");
          }}
        >
          <i className="bi bi-grid-fill me-1"></i>
          목록
        </button>
        <button
          className="btn btn-outline-primary float-end"
          onClick={() => {
            clickAdd();
          }}
        >
          저장
          <i className="bi bi-check ms-1" />
        </button>
      </div>
    </div>
  );
};

export default PhotoCreate;
