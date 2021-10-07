import { ApexOptions } from "apexcharts";
import axios from "axios";
import { useEffect, useState } from "react";
import Chart from "react-apexcharts";

const CovidBar = () => {
  const [chartData, setChartData] = useState<{
    options: ApexOptions;
    series: {
      name: string;
      data: number[];
    }[];
  }>();

  const getData = async () => {
    const result = await axios.get<
      {
        stdDay: string;
        gubun: string;
        overFlowCnt: number;
        localOccCnt: number;
      }[]
    >(`${process.env.REACT_APP_API_BASE}/opendata/covid/sido/daily`);

    const data = result.data;

    const options: ApexOptions = {
      title: {
        text: `전국 코로나 감염 현황 (${result.data[0].stdDay})`,
        align: "center",
        margin: 20,
        style: {
          fontSize: "20px",
        },
      },

      xaxis: {
        categories: data.map((item) => item.gubun),
      },
    };

    const series = [
      {
        name: "국내발생",
        data: data.map((item) => +item.localOccCnt),
      },
      {
        name: "해외유입",
        data: data.map((item) => +item.overFlowCnt),
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
          <Chart
            options={chartData?.options}
            series={chartData?.series}
            type="bar"
            width="1000"
          />
        </div>
      )}
    </div>
  );
};

export default CovidBar;
