//import Pagination from "../../components/Pagination";
import AirBar from "./AirBar";
import AirLine from "./AirLine";
import CovidBar from "./CovidBar";

const Home = () => {
  //const [currentPage, setCurrentpage] = useState(0);

  {
    /*
    const handlePageChanged = (page: number) => {
    setCurrentpage(page);
  };
  
  */
  }

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
      <AirBar />
      <AirLine />
      <CovidBar />
      {/*
      <Pagination
        blockSize={10}
        totalPages={25}
        currentPage={currentPage}
        onPageChanged={handlePageChanged}
      />
      */}
    </div>
  );
};

export default Home;
