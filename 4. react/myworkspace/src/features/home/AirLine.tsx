import { ApexOptions } from "apexcharts";
import { useEffect, useRef, useState } from "react";
import Chart from "react-apexcharts";
//import lineData from "./AirLineData";
import barData from "./airBarData";
import axios from "axios";

const AirLine = () => {
  const [chartData, setChartData] = useState<{
    options: ApexOptions;
    series: {
      // series는  name, data가 있는 배열
      name: string;
      data: number[];
    }[];
  }>();

  const cityRef = useRef<HTMLSelectElement>(null);

  const getData = async () => {
    const result = await axios.get<
      // 2. 백엔드에서 데이터 받아옴
      {
        // 받아오는 타입 지정, 이런 객체를 배열타입으로 받는다
        dataTime: string;
        sidoName: string;
        cityName: string;
        pm10Value: number;
        pm25Value: number;
      }[]
    >(
      `${process.env.REACT_APP_API_BASE}/opendata/air/sido/current/${
        cityRef.current ? cityRef.current.value : "강남구"
      }`
    );

    const data = result.data.reverse();
    //    const data = lineData;

    // 차트 옵션, x축
    const options: ApexOptions = {
      title: {
        text: `서울 자치구별 미세먼지 현황 (${
          cityRef.current ? cityRef.current.value : "강남구"
        })`,
        align: "center",
        margin: 20,
        style: {
          fontSize: "20px",
        },
      },

      xaxis: {
        // 배열 -> 배열, 요소의 개수가 동일함, 요소의 형시은 다름 == map 함수 사용
        // ["2021-10-06 01:00", ... , "2021-10-07 03:00"]
        categories: data
          .map((item) => item.dataTime)
          .map((itme) => itme.substr(11, 5)),
      },
    };

    // 실제 값
    const series = [
      {
        name: "PM10",
        data: data.map((item) => item.pm10Value),
      },
      {
        name: "PM2.5",
        data: data.map((item) => item.pm25Value),
      },
    ];

    setChartData({ options, series });
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <div>
      {chartData && (
        <div>
          <div
            style={{ width: "1000px" }}
            className="d-flex justify-content-end"
          >
            <select
              className="form-select form-select-sm me-2"
              style={{ width: "110px" }}
              onChange={(e) => {
                getData();
              }}
              ref={cityRef}
            >
              {barData
                .map((item) => item.cityName)
                .map((item) => (
                  <option key={`sel-${item}`} value={item}>
                    {item}
                  </option>
                ))}
            </select>
          </div>

          <Chart
            options={chartData.options}
            series={chartData.series}
            type="line"
            width="1000"
            height="400"
          />
        </div>
      )}
    </div>
  );
};

export default AirLine;
