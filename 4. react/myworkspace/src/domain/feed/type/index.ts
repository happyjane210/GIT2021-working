interface FeedState {
  id: number;
  url: string | undefined;
  type: string | undefined;
  content?: string | undefined;
  dataUrl?: string | undefined;
  createTime: number;
  modifyTime?: number;
  isEdit?: boolean;
  username: string | undefined;
}

export type { FeedState };
