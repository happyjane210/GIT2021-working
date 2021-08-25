import React, { useRef, useState } from "react";

interface FeedState {
  id: number;
  url: string | undefined;
  type: string | undefined;
  content?: string | undefined;
  dataUrl?: string | undefined;
  createTime: number;
  modifytime?: number;
}

const getTimeString = (unixTime: number) => {
  const dateTime = new Date(unixTime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Feed = () => {
  const [feedList, setFeedList] = useState<FeedState[]>([]);

  const formRef = useRef<HTMLFormElement>(null);
  const fileRef = useRef<HTMLInputElement>(null);
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  console.log(textareaRef);
  console.log(textareaRef.current?.value);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

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
  };

  const post = (
    dataUrl: string | undefined, //base64   매개변수
    fileType: string | undefined, //image/png   매개변수
    inputText: string | undefined //text   매개변수
  ) => {
    const data: FeedState = {
      id: feedList.length > 0 ? feedList[0].id + 1 : 1,
      url: dataUrl?.toString(),
      createTime: new Date().getTime(),
      type: fileType,
      content: inputText,
    };
    console.log(data);

    setFeedList([data, ...feedList]);
  };

  // id: 내가 클릭한 id 받음
  const del = (clikedId: number) => {
    setFeedList(feedList.filter((item) => item.id !== clikedId));
  };

  return (
    <>
      <h2 className="text-center my-5">Feed</h2>
      <form
        ref={formRef}
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault();
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
          />
          <button
            className="btn btn-primary text-nowrap"
            type="button"
            onClick={() => {
              add(null);
            }}
          >
            입력
          </button>
        </div>
      </form>
      {feedList.map((item) => (
        <div className="card mt-1" key={item.id}>
          {item.type &&
            (item.type?.includes("image") ? (
              <img src={item.url} className="card-img-top" alt={item.content} />
            ) : (
              <video className="card-img-top" controls>
                <source src={item.url} type="video/*"></source>
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
          <a
            href="#!"
            onClick={(e) => {
              e.preventDefault();
              del(item.id);
              //  삭제 버튼 누른 애의 id
            }}
            className="link-secondary fs-6 text-nowrap"
          >
            삭제
          </a>
        </div>
      ))}
    </>
  );
};

export default Feed;
