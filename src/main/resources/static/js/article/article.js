const insertFormBtn = document.getElementById("insertFormBtn");
if (insertFormBtn) {
    insertFormBtn.addEventListener("click", () => {
       locationProcess("/article/insertForm");
    });
}

const articleListBtn = document.getElementById("articleListBtn");
if (articleListBtn) {
    articleListBtn.addEventListener("click", () => {
       locationProcess("/article/articleList");
    });
}

const articleInsertBtn = document.getElementById("articleInsertBtn");
if (articleInsertBtn) {
    articleInsertBtn.addEventListener("click", () => {
        if(!chkData("#name", "작성자명을")) return;
        else if(!chkData("#title", "제목을")) return;
        else if(!chkData("#content", "내용을")) return;
        else{
            formSubmit("insertForm", "post", "/article/articleInsert");
        }
    });
}

const articleCancelBtn = document.getElementById("articleCancelBtn");
if (articleCancelBtn) {
    articleCancelBtn.addEventListener("click", function () {
        const form = this.closest("form");
        form.reset();
    });
}

const updateFormBtn = document.getElementById("updateFormBtn");
if (updateFormBtn) {
    updateFormBtn.addEventListener("click", () => {
        formSubmit("dataForm", "post", "/article/updateForm");
    });
}

const articleUpdateBtn = document.getElementById("articleUpdateBtn");
if (articleUpdateBtn) {
    articleUpdateBtn.addEventListener("click", () => {
       if(!chkData("#title", "제목을")) return;
       	else if(!chkData("#content", "내용을")) return;
       	else{
       		formSubmit("updateForm", "post", "/article/articleUpdate");
       	}
    });
}

const articleDeleteBtn = document.getElementById("articleDeleteBtn");
if (articleDeleteBtn) {
    articleDeleteBtn.addEventListener("click", () => {
       if(confirm("정말로 삭제하시겠습니까?")){
            formSubmit("dataForm", "post", "/article/articleDelete");
       	}
    });
}