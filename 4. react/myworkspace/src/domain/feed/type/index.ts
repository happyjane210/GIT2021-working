interface FeedState {
  // 틀  ? 는 쓸수도 있고 안쓸수도 있고,  물음표 없는 건 꼭써야함
  id: number;
  url: string | undefined;
  type: string | undefined;
  content?: string | undefined;
  dataUrl?: string | undefined;
  createTime: number;
  modifytime?: number;
  isEdit?: boolean;
}

export type { FeedState };
