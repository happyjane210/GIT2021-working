import { useEffect, useState } from "react";

interface PaginationProp {
  blockSize: number;
  totalPages: number;
  currentPage: number;
  onPageChanged?: (pageNo: number) => void; //  2.
  // 반환값 없는 매서드(함수), page 번호를 매개변수로 받음
  // props up , event down으로 받아옴
}

const Pagination = ({
  blockSize,
  totalPages,
  currentPage,
  onPageChanged,
}: PaginationProp) => {
  // 현제 페이지 블럭번호
  // Math.floor() : 주어진 수 이하의 가장 큰 정수를 반환하는 함수, 매개변수는 숫자
  const [currentBlock, setCurrentBlock] = useState<number>(
    Math.floor(currentPage / blockSize) //????  (currentPage / blockSize) 뭐지
  );

  // sideEffect: ---
  // currentPage가 바뀌면 currentBlock이 바뀌어야함

  useEffect(() => {
    setCurrentBlock(Math.floor(currentPage / blockSize));
  }, [currentPage, blockSize]);

  console.log(`--totalPages: ${totalPages}`);
  console.log(`--blockSize: ${blockSize}`);
  console.log(`--currentPage: ${currentPage}`);
  console.log(`--currentBlock: ${currentBlock}`);

  return (
    <nav>
      <ul className="pagination">
        {/* PREVIOUS 영역 */}
        {currentBlock !== 0 && ( // [0] 첫번째 페이지UI 는 preview없음, 첫번째가 아닐때만 실행
          <li className="page-item">
            <a
              className="page-link"
              href="!#"
              onClick={(e) => {
                e.preventDefault();
                setCurrentBlock(currentBlock - 1); // ???
                onPageChanged && onPageChanged(currentBlock * blockSize - 1); // 3.   / -1은 페이지 뒤로가기 1칸
                //         2번째 페이지블럭부터 대입   [1] * 2 - 1 = [1]  : 페이지2
              }}
            >
              PREV
            </a>
          </li>
        )}

        {/* 페이지 번호 영역 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 작으면 남아있는 페이지수 만큼 페이지번호를 만듦 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 크거나 같으면 블록사이즈만큼 페이지번호 만듦 */}
        {/*
           Array.from() : 배열객체, 반복가능Iterator객체를 복사해 새로운 Array배열객체를 만듬
           Array.prototype.keys() : 배열의 각 인덱스를 키 값으로 하는 새로운 Array Iterator 객체를 반환하는 매서드
           Array.prototype.map()  :  배열 내 모든 요소에 대해 주어진 함수를 호출한 결과를 모아 새로운 배열을 반환하는 매서드
        */}
        {
          // 현재 블록기준으로 페이지번호를 생성함
          // 예) index = 0, 1, 2
          //     currentBlock = 2, blockSize = 3
          //     num = 6, 7, 8

          Array.from(
            Array(
              totalPages - currentBlock * blockSize < blockSize
                ? totalPages - currentBlock * blockSize
                : blockSize
            ).keys()
          )
            .map((index) => currentBlock * blockSize + index)
            .map((num) => (
              <li
                className={`page-item ${currentPage === num ? "active" : ""}`}
                key={`page-${currentBlock * blockSize + num}`}
              >
                <a
                  className="page-link"
                  href="!#"
                  onClick={(e) => {
                    e.preventDefault();
                    onPageChanged && onPageChanged(num);
                  }}
                >
                  {num}
                  {/* num + 1 */}
                </a>
              </li>
            ))
        }

        {/* NEXT 영역 */}
        {/* 현재 블록 기준 남아있는 페이지가 블록사이즈보다 크면 NEXT 보임 */}
        {/* 총페이지 - 현재페이지블럭 * 몇개보여줄건지 > 몇개보여줌 */}
        {/*  10   - ( x[ 0, 1, 2, 3]  *  3 ) >  3  */}
        {totalPages - currentBlock * blockSize > blockSize && (
          <li className="page-item">
            <a
              className="page-link"
              href="!#"
              onClick={(e) => {
                e.preventDefault();
                setCurrentBlock(currentBlock + 1);
                onPageChanged &&
                  onPageChanged(currentBlock * blockSize + blockSize);
                //              [0]  * 2 + 2 = [2]
              }}
            >
              NEXT
            </a>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Pagination;
