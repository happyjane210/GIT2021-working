import axios from "axios";

interface ContacLinetItemResponse {
  id: number;
  name: string;
  phone: string;
  email: string;
  createdTime: number;
}

interface ContactLineItemReqeust {
  name: string | undefined;
  phone: string | undefined;
  email: string | undefined;
  createdTime: number;
}

// 서버하고 데이터 연동하는 api처리 목록을 별도의 객체로 작성
const contactLineApi = {
  fetch: () =>
    axios.get<ContacLinetItemResponse[]>(
      `${process.env.REACT_APP_API_BASE}/linecontacts`
    ),

  // 추가
  add: (contactItem: ContactLineItemReqeust) =>
    axios.post<ContacLinetItemResponse>(
      `${process.env.REACT_APP_API_BASE}/linecontacts`,
      contactItem
    ),

  // 삭제
  remove: (id: number) =>
    axios.delete<boolean>(
      `${process.env.REACT_APP_API_BASE}/linecontacts/${id}`
    ),

  // 수정
  modify: (id: number, contactItem: ContactLineItemReqeust) =>
    axios.put<ContactLineItemReqeust>(
      `${process.env.REACT_APP_API_BASE}/linecontacts/${id}`,
      contactItem
    ),
};

export default contactLineApi;
