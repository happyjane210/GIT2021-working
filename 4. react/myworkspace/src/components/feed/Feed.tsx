import React, { useRef, useState } from "react";
import { FeedState } from "./type/index";
import FeedWithModal from "./FeedWithModal";
import produce from "immer";
import { stat } from "fs";
import Alert from "../base/Alert";

const getTimeString = (unixTime: number) => {
  const dateTime = new Date(unixTime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Feed = () => {
  const [isEdit, setIsEdit] = useState(false);
  const [isError, setIsError] = useState(false);
  const [feedList, setFeedList] = useState<FeedState[]>([]);
  // state 배열타입 FeedState 타입 콜백함수,setFeedList 통해서만 바뀔수있음
  // setState : ()없는 콜백함수, 변환함수, 변환하는 값을 feedList 본체로 보내줌
  const formRef = useRef<HTMLFormElement>(null); //HTMLFormElement 객체 타입
  const fileRef = useRef<HTMLInputElement>(null);
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  console.log(textareaRef);
  console.log(textareaRef.current?.value);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return; // 엔터를 누르면 입력이 됨  근데 작동안되는거 같음
    }

    if (!textareaRef.current?.value && !fileRef.current?.files?.length) {
      setIsError(true);
      return;
    }

    // fileRef 안에 current 안에 files 의 길이
    // 파일은 배열, 여러개 담을수 있고 배열 길이가 있음
    // fileRef 에 뭔가 존재하면 if 실행함
    if (fileRef.current?.files?.length) {
      const text = textareaRef.current?.value;
      const file = fileRef.current?.files[0];
      const reader = new FileReader(); //base64로 읽는 기능
      reader.readAsDataURL(file); //base64로 바꿔주는 능력

      reader.onload = () => {
        // 그 결과물을 로드 해줌
        post(
          // 함수
          reader.result?.toString() /*base64 1번값*/,
          file.type /*사진 이면 image/png 2번값*/,
          text // 3번째값
        );
      };
    } else {
      post(undefined, undefined, undefined);
    }

    formRef.current?.reset();
  };

  const post = (
    dataUrl: string | undefined, //base64   매개변수
    fileType: string | undefined, //image/png   매개변수
    inputText: string | undefined //text   매개변수
  ) => {
    // data 객체인데 타입이 FeedState
    const data: FeedState = {
      // feedList 안에 값이 있으면 기존 배열 id에 +1을 해주고 123 순으로 쌓는다;
      //0보다 작으면(기존값 없으면) 1로 만든다, 첫번재 요소가 됨
      id: feedList.length > 0 ? feedList[0].id + 1 : 1,
      url: dataUrl?.toString(),
      type: fileType,
      content: inputText,
      createTime: new Date().getTime(), // 현재시간을 알려주는 매서드.
    };
    console.log(data);

    setFeedList([data, ...feedList]);
    // 방금 위에서 만들어준 객체 data
    // ... 스프레드 연산자: 사용하면 하나의 배열로 합쳐줌
    // data 새값이 위로, ...feedList 기존 배열 밑으로 쌓임
  };

  // id: 내가 클릭한 id 받음
  const del = (clikedId: number) => {
    //                               전체아이디 [1,2,3,4,5]     클릭한 아이디  4
    setFeedList(feedList.filter((item) => item.id !== clikedId));
    //                                  feedList = [1,2,3,5]
    // filter  map 같은점: 모든 요소를 훑는다 / map 다른점: 한번 훑어서 조건에 맞는애를 내보냄
  };

  //==================

  const eidtItem = useRef<FeedState>({
    id: 0,
    url: "", //
    type: "", //
    content: "", //
    createTime: 0,
  });

  const edit = (item: FeedState) => {
    eidtItem.current = item;
    setIsEdit(true);
  };

  //==================

  const save = (eidtItem: FeedState) => {
    setFeedList(
      produce((state) => {
        const item = state.find((item) => item.id === eidtItem.id);
        if (item) {
          item.url = eidtItem.url;
          item.type = eidtItem.type;
          item.content = eidtItem.content;
        } else if (!eidtItem.url) {
          // 사진 선택 안하면 그대로 남겨두고 싶은데 모르겠음
          return;
        }
      })
    );

    setIsEdit(false);
  };

  return (
    <>
      <h2 className="text-center my-5">
        <b>🎨 FEED 🎨</b>
      </h2>
      {isEdit && (
        <FeedWithModal
          item={eidtItem.current}
          onClose={() => {
            setIsEdit(false);
          }}
          onSave={(eidtItem) => {
            save(eidtItem);
          }}
        />
      )}
      <form // JSX 태그 / HTMl 테그 아님, JSX코드는 아무런 능력없음, 이름이 form인거고, 실제 기능은 없음, 기능을 하기 위해 Ref 붙여줌
        ref={formRef}
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault(); // submit 에 기본 발동을 못하게 한다.
        }}
      >
        <textarea // 입력창
          box-sizing="border-box"
          className="form-control mb-1 w-100"
          placeholder="Leave a message here"
          ref={textareaRef}
        ></textarea>
        <div className="d-flex">
          <input
            ref={fileRef}
            type="file"
            className="form-control me-1"
            accept="image/*, video/*"
            onChange={() => {}}
          />
          <button
            className="btn btn-outline-dark text-nowrap"
            type="button"
            onClick={() => {
              add(null);
            }}
          >
            입력
          </button>
        </div>
      </form>

      {isError && (
        <Alert
          message={"파일이나 텍스트를 포스팅하세요."}
          variant={"danger"}
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      {/* map 배열: 배열 요소를 하나씩 꺼내서 한바퀴 돌리고, 다음 요소 꺼내서 돌리고*/}
      {feedList.map((item) => (
        <div className="card mt-1" key={item.id}>
          {item.type &&
            (item.type?.includes("image") ? (
              <img src={item.url} className="card-img-top" alt={item.content} />
            ) : (
              <video className="card-img-top" controls>
                <source src={item.url} type="video/mp4"></source>
              </video>
            ))}

          <div className="card-body">
            <p className="card-text">{item.content}</p>

            <span className="text-secondary">
              {getTimeString(
                item.modifytime ? item.modifytime : item.createTime
              )}
            </span>
          </div>
          <div className="d-grid gap-2 d-md-flex justify-content-md-end me-3 mb-3">
            <button
              className="btn btn-outline-success  me-md-1"
              onClick={() => {
                edit(item); // 수정 모달팝업 띄우기
              }}
            >
              수정
            </button>
            <button
              className="btn btn-outline-secondary "
              onClick={(e) => {
                e.preventDefault();
                del(item.id);
              }}
            >
              삭제
            </button>
          </div>
        </div>
      ))}
    </>
  );
};

export default Feed;
