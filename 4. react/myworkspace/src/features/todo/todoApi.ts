// 백엔드 todo 와 통신하는 컴포넌트 (java Spring)
import axios from "axios";

// 서버로 부터 받아오는 데이터 1건에 대한 타입
interface TodoItemReponse {
  id: number;
  memo: string;
  createdTime: number;
}

interface TodoItemRequest {
  memo: string;
}

// 서버하고 데이터 연동하는 api처리 목록을 별도의 객체로 작성
const todoApi = {
  // fetch 의 리턴타입  Promise<AxiosResponse<TodoItemReponse[]>>
  fetch: () =>
    axios.get<TodoItemReponse[]>(`${process.env.REACT_APP_API_BASE}/todos`),

  // axios.post<응답타입>(요청url, 요청객체(JSON바디))
  // POST 요청URL HTTP/1.1 {...}
  // 받는객채 => 돌려주는 객체
  // 추가
  add: (todoItem: TodoItemRequest) =>
    axios.post<TodoItemReponse>(
      `${process.env.REACT_APP_API_BASE}/todos`,
      todoItem
    ),

  // 삭제
  // axios.post<응답타입>(요청URL)
  // DELETE 요청 URL HTTP/1.1
  //       id 값 받아옴    리턴 타입
  remove: (id: number) =>
    axios.delete<boolean>(`${process.env.REACT_APP_API_BASE}/todos/${id}`),

  // 수정
  // axios.post<응답타입>(요청url, 요청객체(JSON바디))
  // POST 요청URL HTTP/1.1 {...}
  //          받아오는 값
  modify: (id: number, todoItem: TodoItemRequest) =>
    axios.put<TodoItemReponse>( // 리턴하는 형식
      `${process.env.REACT_APP_API_BASE}/todos/${id}`, // 리턴하는 URL, 값
      todoItem
    ),
};

export default todoApi;
