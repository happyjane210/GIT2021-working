import React from "react";

interface TodoState {
  id: number;
  memo: string | undefined;
  username: string | undefined;

  createTime: number;
  modifyTime?: number;
  isEdit?: boolean; //수정모드인지 여부
}

export type { TodoState };
