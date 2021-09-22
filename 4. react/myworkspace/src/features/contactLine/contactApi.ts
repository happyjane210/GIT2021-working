import axios from "axios";

interface ContactItemResponse {
  id: number;
  name: string;
  phone: string;
  email: string;
}

interface ContactItemReqeust {
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
}

// 서버하고 데이터 연동하는 api처리 목록을 별도의 객체로 작성
const contactApi = {
  fetch: () =>
    axios.get<ContactItemResponse[]>(
      `${process.env.REACT_APP_API_BASE}/contacts`
    ),

  // 추가
  add: (contactItem: ContactItemReqeust) =>
    axios.post<ContactItemResponse>(
      `${process.env.REACT_APP_API_BASE}/contacts`,
      contactItem
    ),

  // 삭제
  remove: (id: number) =>
    axios.delete<boolean>(`${process.env.REACT_APP_API_BASE}/contacts/${id}`),

  // 수정
  modify: (id: number, contactItem: ContactItemReqeust) =>
    axios.put<ContactItemResponse>(
      `${process.env.REACT_APP_API_BASE}/contacts/${id}`,
      contactItem
    ),
};

export default contactApi;
