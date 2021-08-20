import Header

const Components = () => {
  return (
    <div>
      <h1 style={{ color: "red" }}>Hello React with Typescript</h1>
      {/*컴포넌트의 속성*/}
      {/* <Header color={"red"} title={"React"} />
      <Header color={"green"} title={"Javascipt"} />
      <Header color={"blue"} title={"Typescript"} /> */}

      {/*재사용하지 않는 컴포넌트
      리액트는 컴포넌트 명 대문자임, 그래서 함수도 대문자로 시작
      만약 컬러 요소 항목을 하나씩 써줬으면 그 안에 써준 항목만 여기에 쓸 수 있음*/}
      <Header color={"red"} title={"react"} />
      <Header color={"green"} title={"Typescript"} />
      <Header color={"blue"} title={"Function Component"} />

      {/* <Button color={"black"} backgrounColor={"red"} text={"Delete"} />
      <Button color={"white"} backgrounColor={"green"} text={"Done"} /> */}
      <Button variant={"primary"} text={"Add"} />
      <Button variant={"secondary"} text={"Delete"} />
      <Button variant={"warning"} text={"Delete"} />
    </div>
  );
};

export default Components;
