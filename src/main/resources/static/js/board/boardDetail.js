document.getElementById("updateFormBtn").addEventListener("click", function() {
    formSubmit("dataForm", "post", "/board/updateForm");
});

document.getElementById("boardDeleteBtn").addEventListener("click", () => {
    if (confirm("정말 삭제하시겠습니까?")) {
        formSubmit("dataForm", "post", "/board/boardDelete");
    }
});