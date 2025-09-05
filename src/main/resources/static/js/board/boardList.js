document.querySelectorAll(".goDetail").forEach(function (element) {
    element.addEventListener("click", function() {
        const tr = this.closest("tr");
        const no = tr.dataset.no;
        locationProcess(`/board/${no}`);
    });
});

/* 페이징 처리 */
document.querySelectorAll(".page-item a").forEach(function(anchor) {
    anchor.addEventListener("click", function(e) {
        e.preventDefault();
        const pageNumber = this.dataset.number;
        document.getElementById("page").value = pageNumber;
        formSubmit("searchForm", "get", "/board/boardList");
    });
});