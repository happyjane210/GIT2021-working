const Todo = () => {

    // 1건에 대한 타입
    interface TodoState{
        id: number;
        memo: string;
        createTime: number;
        modifyTime: number;
    }

    const Todo =() => {
        const [todoList, setTodoList] = useState([]);

        return (
        <>
          <h2 className="text-center my-5">할 일 관리</h2>
          <form className="d-flex">
          <input  type="text" className="form-control me-2" placeholder="할 일 ...">
          <button  type="button" className="btn btn-primary text-nowrap">추가</button>
        </form>
        ) 
        </>;



    }

};

export default Todo();
