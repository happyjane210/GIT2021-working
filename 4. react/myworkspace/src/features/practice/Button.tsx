// Button 컴포넌트 생성
// <button ... JSX 엘리먼트를 반환하는 엘리먼트
// 속성:
// color : 글자색 ("white", "black")
// backgroundColor: 배경색 ("red","green","blue")
// text: 버튼의 텍스트 (대문자로 표시)

interface ButtonProp {
  color: "white" | "black";
  // backgroundColor: "red" | "green" | "blue";
  variant: "primary" | "secondary" | "warning";
  title: string;
}

const Button: React.FC<ButtonProp> = ({ variant, title }) => {
  let bgColor = "blue";
  let color = "black";

  switch (variant) {
    case "primary":
      bgColor = "blue";
      color = "white";
      break;

    case "secondary":
      bgColor = "grey";
      color = "black";
      break;

    case "warning":
      bgColor = "red";
      color = "white";
  }

  return (
    <button
      style={{
        color: color,
        backgroundColor: bgColor,
      }}
    >
      {title.toUpperCase()}
    </button>
  );
};

export default Button;
