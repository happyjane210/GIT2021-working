const PhotoCreate = () => {
  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Photos Creat</h2>{" "}
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  placeholder="제목..."
                />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  rows={10}
                  className="form-control"
                  placeholder="(선택) 설명...."
                ></textarea>
              </td>
            </tr>
            <tr>
              <th>이미지</th>
              <td>
                <input className="form-control" type="file" accept="image/*" />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div className="d-flex justify-content-end">
        <button className="btn btn-primary">
          저장
          <i className="bi bi-check2 ms-2"></i>
        </button>
      </div>
    </div>
  );
};

export default PhotoCreate;
