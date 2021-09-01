import { useRef, useState } from "react";
import { FeedState } from "./type/index";

interface ModalProp {
  item: FeedState;
  onClose: () => void;
  onSave: (editItem: FeedState) => void;
}

const FeedWithModal = ({ item, onClose, onSave }: ModalProp) => {
  const [isChange, setIsChange] = useState(false);
  const [url2, setUrl2] = useState(item.url);
  const [type, setType] = useState("");

  const fileRef = useRef<HTMLInputElement>(null);
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  const add = () => {
    // 모달창에서 선택한 파일
    if (fileRef.current?.files?.length) {
      const file = fileRef.current?.files[0];
      const fileType = file.type;
      setType(fileType);

      const reader = new FileReader(); //base64로 읽는 기능

      reader.onload = () => {
        const url = reader.result?.toString();
        setUrl2(url);
        setIsChange(true);
      };
      reader.readAsDataURL(file); //base64로 바꿔주는 능력
    }
  };

  const save = (url2: string | undefined) => {
    const feed: FeedState = {
      id: item.id, //기존값
      url: url2, // 새로운 url
      type: type, // 새로운 type
      content: textareaRef.current?.value, // 수정한 텍스트
      createTime: item.createTime,
      username: item.username,
      image: item.image,
    };

    onSave(feed);
  };

  return (
    <>
      {/* 모달 영역   그레이 컴포넌트 따로 필요없음*/}
      <div
        style={{ backgroundColor: "rgba(0,0,0,0.4)" }}
        className="modal d-block"
        onClick={() => {
          onClose();
        }}
        // 전체영역 클릭하면 모달 사라짐
      >
        <div className="modal-dialog">
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h3 className="modal-title">👀 Edit Post 🐾</h3>
              <button
                type="button"
                className="btn-close"
                aria-label="Close"
                onClick={() => {
                  onClose();
                }}
                // 클릭하면 onClose라고 넘어온 함수를 실행함
                // 함수에 대한 참조를 넘겨줌(메모리주소)
              ></button>
            </div>
            <div className="modal-body" key={item.id}>
              <input
                aria-describedby="inputGroupFileAddon04"
                aria-label="Upload"
                type="file"
                accept="image/png, image/jpeg, video/mp4"
                ref={fileRef}
                className="form-control me-1"
                onChange={(e) => {
                  e.preventDefault();
                  add();
                }}
              />

              {isChange === false
                ? item.type &&
                  (item.type?.includes("image") ? (
                    <img
                      src={item.url}
                      className="card-img-top mt-2"
                      alt={item.content}
                    />
                  ) : (
                    <video className="card-img-top mt-2" controls>
                      <source src={item.url} type="video/mp4"></source>
                    </video>
                  ))
                : isChange &&
                  (item.type?.includes("image") ? (
                    <img
                      src={url2}
                      className="card-img-top mt-2"
                      alt={textareaRef.current?.value}
                    />
                  ) : (
                    <video className="card-img-top mt-2" controls>
                      <source src={url2} type={type}></source>
                    </video>
                  ))}

              <textarea // 입력창
                box-sizing="border-box"
                className="form-control mt-2 mb-1 w-100"
                defaultValue={item.content}
                ref={textareaRef}
              ></textarea>
              {/* <input
                type="text"
                defaultValue={item.memo}
                className="w-100"
                ref={inputRef}
                // 여기서 변경된 텍스트를 저장해서 목록에 반영
                // onKeyPress={() => onAdd()}
              />  */}
              {/* 이부분 피드에서 video, image, 수정버튼, textarea,  */}
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-outline-dark"
                onClick={() => {
                  onClose();
                }}
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-outline-warning"
                onClick={() => {
                  save(url2);
                  // 3. 세이브 버튼 클릭하면 save() 함수 실행  [event 발생]
                }}
              >
                Save
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default FeedWithModal;
