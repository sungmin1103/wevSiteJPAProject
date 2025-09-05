/*document.getElementById("boardInsertBtn").addEventListener("click", () => {
    if(!chkData("#name", "작성자명을")) return;
    else if(!chkData("#title", "제목을")) return;
    else if(!chkData("#content", "내용을")) return;
    else{
        formSubmit("insertForm", "post", "/board/boardInsert");
    }
});*/

document.getElementById("file").addEventListener("change", function() {
    chkFile(this);
});

document.getElementById("boardInsertBtn").addEventListener("click", () => {
    const file = document.getElementById("#file");
    if(!chkData("#name", "작성자명을")) return;
    else if(!chkData("#title", "제목을")) return;
    else if(!chkData("#content", "내용울")) return;
    //else if (!chkData("#file","업로드할 이미지 파일을")) return; 필수요소
    else{
        formFileSubmit("insertForm", "post", "/board/boardInsert");
    }
});