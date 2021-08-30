import { useRef, useState } from "react";
import { FeedState } from "./type";
import { RootState } from "../../store";
import { useSelector } from "react-redux";
import FeedWithModal from "./FeedWithModal";
import Alert from "../../components/Alert";
import produce from "immer";
import style from "../profile/Profile.module.scss";

const getTimeString = (unixTime: number) => {
  const now = new Date();
  now.getTime();
  const day = 24 * 60 * 60 * 1000;
  const dateTime = new Date(unixTime);

  return unixTime - new Date().getTime() >= day
    ? dateTime.toLocaleDateString()
    : dateTime.toLocaleTimeString();
  // 현재시간 보다 24시간 이전이면 날짜를 보여주고
  // 현재시간 보다 24시간 미만이면 시간을 보여줌
};

const Feed = () => {
  const profile = useSelector((state: RootState) => state.profile);

  const [isEdit, setIsEdit] = useState(false);
  const [isError, setIsError] = useState(false);
  const [feedList, setFeedList] = useState<FeedState[]>([]);

  const formRef = useRef<HTMLFormElement>(null);
  const fileRef = useRef<HTMLInputElement>(null);
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    if (!textareaRef.current?.value && !fileRef.current?.files?.length) {
      setIsError(true);
      return;
    }

    if (fileRef.current?.files?.length) {
      const text = textareaRef.current?.value;
      const file = fileRef.current?.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);

      reader.onload = () => {
        post(reader.result?.toString(), file.type, text);
      };
    } else {
      post(undefined, undefined, undefined);
    }

    formRef.current?.reset();
  };

  const post = (
    dataUrl: string | undefined,
    fileType: string | undefined,
    inputText: string | undefined
  ) => {
    const data: FeedState = {
      id: feedList.length > 0 ? feedList[0].id + 1 : 1,
      url: dataUrl?.toString(),
      type: fileType,
      content: inputText,
      createTime: new Date().getTime(),
      username: profile.username,
    };
    console.log(data);

    setFeedList([data, ...feedList]);
  };

  const del = (clikedId: number) => {
    setFeedList(feedList.filter((item) => item.id !== clikedId));
  };

  //=================

  const editItem = useRef<FeedState>({
    id: 0,
    url: "",
    type: "",
    content: "",
    createTime: 0,
    username: profile.username,
  });

  const edit = (item: FeedState) => {
    editItem.current = item;
    setIsEdit(true);
  };

  //=================

  const handleSave = (editItem: FeedState) => {
    setFeedList(
      produce((state) => {
        const item = state.find((item: FeedState) => item.id === editItem.id);
        if (item) {
          item.url = editItem.url;
          item.type = editItem.type;
          item.content = editItem.content;
        } else if (!editItem.url) {
          return;
        }
      })
    );
    setIsEdit(false);
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">
        <b>🎨 FEED 🎨</b>
      </h2>
      {isEdit && (
        <FeedWithModal
          item={editItem.current}
          onClose={() => {
            setIsEdit(false);
          }}
          onSave={(editItem) => {
            handleSave(editItem);
          }}
        />
      )}
      <form
        ref={formRef}
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault();
        }}
      >
        <textarea
          box-sizing="border-box"
          className="form-control mb-1"
          placeholder="Leave a message here..."
          ref={textareaRef}
        ></textarea>
        <div className="d-flex">
          <input
            type="file"
            ref={fileRef}
            className="form-control me-1"
            accept="image/*, video/*"
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
          message={"파일이나 텍스트를 포스팅 하세요."}
          variant={"danger"}
          onClose={() => {
            setIsError(false);
          }}
        />
      )}

      {feedList.map((item) => (
        <div className="card mt-3" key={item.id}>
          <div className="d-flex card-header">
            <div
              className={`${style.thumb} me-1`}
              style={{ backgroundImage: `url(${profile.image})` }}
            ></div>
            <span className={`${style.username} `}>{profile.username}</span>
          </div>
          <div className="card-body">
            {item.type &&
              (item.type?.includes("image") ? (
                <img
                  src={item.url}
                  alt={item.content}
                  className="card-img-top"
                />
              ) : (
                <video className="card-img-top" controls>
                  <source src={item.url} type="video/mp4" />
                </video>
              ))}

            <p className="card-text">{item.content}</p>
            <span>
              - {item.username}, {getTimeString(item.createTime)}
            </span>
          </div>
          <div className="d-grid gap-2 d-flex justify-content-end me-3 mb-3">
            <button
              className="btn btn-outline-success me-md-1"
              onClick={() => {
                edit(item);
              }}
            >
              수정
            </button>
            <button
              className="btn btn-outline-secondary"
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
    </div>
  );
};

export default Feed;
