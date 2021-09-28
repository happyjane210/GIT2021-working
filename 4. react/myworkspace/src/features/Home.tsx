import { useState } from "react";
import Pagination from "../components/Pagination";

const Home = () => {
  const [currentPage, setCurrentpage] = useState(0);

  const handlePageChanged = (page: number) => {
    setCurrentpage(page);
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5" style={{ color: "pink" }}>
        🌺Welcome to Home Component🌺
      </h2>
      <div style={{ color: "yellowgreen" }}>
        <br />
        <div>
          <b>This is Home Component</b>
        </div>
        <div>
          <b>This is Home Component</b>
        </div>
        <div>
          <b>This is Home Component</b>
        </div>
        <div>
          <b>This is Home Component</b>
        </div>
        <div>
          <b>This is Home Component</b>
        </div>
      </div>
      <Pagination
        blockSize={10}
        totalPages={25}
        currentPage={currentPage}
        onPageChanged={handlePageChanged}
      />
    </div>
  );
};

export default Home;
