import { ApexOptions } from "apexcharts";
import axios from "axios";
import { useEffect, useState } from "react";
import Chart from "react-apexcharts";

const AirBar = () => {
  const [chartData, setChartData] = useState<{
    options: ApexOptions;
    series: {
      // series는  name, data가 있는 배열
      name: string;
      data: number[];
    }[];
  }>();

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
    >(`${process.env.REACT_APP_API_BASE}/opendata/air/sido/current`);

    const data = result.data;

    // 차트의 옵션들, x축 문자열
    const options: ApexOptions = {
      title: {
        text: `서울 미세먼지 현황 (${result.data[0].dataTime})`,
        align: "center",
        margin: 20,
        style: {
          fontSize: "20px",
        },
      },

      xaxis: {
        // 배열 -> 배열, 요소의 개수가 동일함, 요소의 형시은 다름 == map 함수 사용
        // ["강남구", ... , "중랑구"]
        categories: data.map((item) => item.cityName),
      },
      fill: {
        colors: [
          ({ value, seriesIndex }: { value: number; seriesIndex: number }) => {
            // seriesIndex : 0 , PM10
            // seriesIndex : 1 , PM2.5

            let color = "";

            if (seriesIndex === 0) {
              // PM 10일때
              if (value <= 30) color = "rgb(50, 161, 255)";
              else if (value > 30 && value <= 80) color = "rgb(0, 199, 60)";
              else if (value > 80 && value <= 150) color = "rgb(253, 155, 90)";
              else color = "rgb(255, 89, 89)";
            } else if (seriesIndex === 1) {
              // PM 2.5일때
              if (value <= 15) color = "rgb(50, 161, 255)";
              else if (value > 15 && value <= 35) color = "rgb(0, 199, 60)";
              else if (value > 35 && value <= 75) color = "rgb(253, 155, 90)";
              else color = "rgb(255, 89, 89)";
            }
            return color;
          },
        ],
      },
    };

    // 실제 값들
    const series = [
      {
        name: "PM10 미세먼지",
        data: data.map((item) => +item.pm10Value),
      },
      {
        name: "PM2.5 초미세먼지",
        data: data.map((item) => +item.pm25Value),
      },
    ];

    // 3. state 를 set해야 그래프가 다시 그려짐
    setChartData({ options, series });
  };

  useEffect(() => {
    getData(); // 1. 처음 마운팅 렌더링 될때, getData 호출
  }, []);

  return (
    <div>
      {chartData && (
        <div>
          <Chart
            options={chartData?.options}
            series={chartData?.series}
            type="bar"
            width="1000"
            height="400"
          />
        </div>
      )}
    </div>
  );
};

export default AirBar;
