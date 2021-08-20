//컴포넌트 정의
//리액트 컴포넌트 = JSX를 반환하는 함수
//<></> Fragment 조각
// 어떤태그형식으로 변환되지 않음, 빈태그.
// 스타일 속성이 객체 형태임 style={{}}

// 컴포넌트에 속성으로 color, tiltle을 받을 것임

// 지정하는 방법1
interface HeaderProp {
    // color: sting;
    // union type: 값의 집합
    color: "green" | "red" | "blue";
    title: string;
}

// const 함수명 : React.FC<속성타입>
const Header: React.FC<HeaderProp> = ({ color, title }) => {
    // const Header = ({color, title}: HeaderProp) => {
    return <h1 style={{ color: color }}>{title}</h1>;  // 여러개의 부모 앨리먼트를 반환하지 못함 무조건 하나만 됨 디브나 빈거 
};
// 받는 객체에서 속성만
export default Header;