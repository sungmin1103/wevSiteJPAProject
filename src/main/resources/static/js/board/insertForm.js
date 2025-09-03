document.getElementById("boardInsertBtn").addEventListener("click", () => {
    if(!chkData("#name", "작성자명을")) return;
    else if(!chkData("#title", "제목을")) return;
    else if(!chkData("#content", "내용을")) return;
    else{
        formSubmit("insertForm", "post", "/board/boardInsert");
    }
});