// { 함수속성 }
// 함수속성의 타입: (매개변수타입) => 리턴타입
// 함수를 부모 컴포넌트로 부터 매개변수(prop)를 받음
// 자식컴포넌트에서 이벤트가 발생하면

import { useRef } from "react";
import { TodoState } from "./type/index";

interface ModalProp {
  item: TodoState;
  onClose: () => void;
  onSave: (editItem: TodoState) => void;
  // 콜백함수: 매개변수로 함수가 넘어가는것
  // 2. 참조: onSave는 editItem 이라는 TodoState가 있고 반환은 void(값없음)
}

// 2. ModalProp = onSave={(editItem) => {save(editItem); 여기에 함수 넣어줌  [Props Down] 받음
const TodoEditModal = ({ item, onClose, onSave }: ModalProp) => {
  const inputRef = useRef<HTMLInputElement>(null);

  // 4. 버튼 클릭 save() 함수 받고 실행 , 새로운 todo 객체  [수정된 객체 생성]
  const save = () => {
    // 새로운 todo 객체를 만들어서 기존 값을 수정함
    const todo: TodoState = {
      id: item.id, // 기존값
      memo: inputRef.current?.value, // 수정된 텍스트 입력값  <- 여기만 수정
      createTime: item.createTime, // 기존
    };

    // 5. 수정된 객체를 onSave(todo) 에 넘겨줌   [Event Up]    여기에  todo 객체가 editItem으로 이름 바뀜
    // 5. 이 함수 실행 본체는 1.으로 들어감   <TodoEditModal (속성) onSave={(editItem) => {save(editItem);/>
    onSave(todo);
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
              <h5 className="modal-title">Edit Todo</h5>
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
            <div className="modal-body">
              <input
                type="text"
                defaultValue={item.memo}
                className="w-100"
                ref={inputRef}
                // 여기서 변경된 텍스트를 저장해서 목록에 반영
                // onKeyPress={() => onAdd()}
              />
              {/* 이부분 피드에서 video, image, 수정버튼, textarea,  */}
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => {
                  onClose();
                }}
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={() => {
                  save();
                  // 3. 세이브 버튼 클릭하면 save() 함수 실행  [event 발생]
                }}
              >
                Save changes
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default TodoEditModal;
