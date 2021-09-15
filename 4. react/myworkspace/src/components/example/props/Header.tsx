// 컴포넌트 정의
// 리액트 컴포넌트 = JSX를 반환하는 함수
// <></>: fragment(빈조각)
// 어떤 태그형식으로 반환되지 않음, 빈태그
// 여러개의 부모 앨리먼트 반환 못함.

// 컴포넌트에 속성으로 color, title을 반을 것임
// 컴포넌트의 속성(prop) : 함수의 매개변수처럼 외부에서 넘겨받는 변수

// const 함수명 : React.FC<속성타입> = ({속성1, 속성2}) => {
//
// }

interface HeaderProp {
  //color : string;
  // union type : 값의 집합
  // 지정한 값만 들어올수 있다.
  color: "green" | "red" | "blue";
  title: string;
}

//FC: function Component 단축어
const Header: React.FC<HeaderProp> = ({ color, title }) => {
  // const Header = ({color, title}: HeaderProp) => {
  return (
    <div>
      <h1 style={{ color: "red" }}>React</h1>
      <h1 style={{ color: "green" }}>Typescript</h1>
      <h1 style={{ color: "blue" }}>this is H1</h1>
      <h2 style={{ color: "blue" }}>this is H2</h2>
      <h3 style={{ color: "blue" }}>this is H3</h3>
    </div>
  );

  //return<></><div></div>//여러개의 부모 엘리먼트를 반환하지 못함
};

//컴포넌트 내보내기
export default Header;
