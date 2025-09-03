const insertFormBtn = document.getElementById("insertFormBtn");
if (insertFormBtn) {
    insertFormBtn.addEventListener("click", () =>{
        locationProcess("/board/insertForm");
    });
}

const cancelBtn = document.getElementById("boardListBtn");
if (cancelBtn) {
    cancelBtn.addEventListener("click", function() {
        const form = this.closest("form");
        formReset(form.id);
    });
}

const boardListBtn = document.getElementById("boardListBtn");
if (boardListBtn) {
    boardListBtn.addEventListener("click", () => {
        locationProcess("/board/boardList");
    });
}
