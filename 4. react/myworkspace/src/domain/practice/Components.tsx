import Header from "./Header";
import Button from "./Button";

const components = () => {
  return (
    <div>
      {/* 속성값을 변경하여 재사용하는 컴포넌트 */}
      {/* Component의 속성(prop) */}
      {/* 속성명={속성값} */}
      {/* JSX 주석다는 법 */}
      {/* 재사용 하지 않는 컴포넌트 */}
      <Header color={"red"} title={"React"} />
      <Header color={"green"} title={"Typescript"} />
      <Header color={"blue"} title={"Java"}></Header>

      <Button color={"black"} variant={"primary"} title={"add"}></Button>
      <Button color={"white"} variant={"primary"} title={"delete"}></Button>
      <Button color={"white"} variant={"secondary"} title={"done"}></Button>
    </div>
  );
};

export default components;
