import { createSlice } from "@reduxjs/toolkit";
import { pic } from "./index";

interface PhotoItem {
  id: number;
  profileUrl: string;
  username: string;
  title: string;
  description?: string; // 같음 description: string | undifined;
  photoUrl: string;
}

interface PhotoState {
  data: PhotoItem[]; // 포토 아이템 배열
  isFetched: boolean; //서버에서 데이터를 받아오는지에 대한 정보
}

const initialState: PhotoState = {
  data: [
    {
      id: 1,
      profileUrl: pic,
      username: "JANE LEE",
      title: "프로필",
      description: "프로필 사진",
      photoUrl: pic,
    },
    {
      id: 2,
      profileUrl: pic,
      username: "JANE LEE",
      title: "프로필",
      description: "프로필 사진",
      photoUrl: pic,
    },
    {
      id: 3,
      profileUrl: pic,
      username: "JANE LEE",
      title: "프로필",
      description: "프로필 사진",
      photoUrl: pic,
    },
    {
      id: 4,
      profileUrl: pic,
      username: "JANE LEE",
      title: "프로필",
      description: "프로필 사진",
      photoUrl: pic,
    },
    {
      id: 5,
      profileUrl: pic,
      username: "JANE LEE",
      title: "프로필",
      description: "프로필 사진",
      photoUrl: pic,
    },
  ],
  isFetched: false,
};

const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {},
});

export default photoSlice.reducer;

// 기본구조

// const photosSlice = createSlice({
//     name: "photos",
//     initialState:{},
//     reducers:{}
// })

// export default photosSlice.reducer;
