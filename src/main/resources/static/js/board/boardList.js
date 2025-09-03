document.querySelectorAll(".goDetail").forEach(function (element) {
    element.addEventListener("click", function() {
        const tr = this.closest("tr");
        const no = tr.dataset.no;
        locationProcess(`/board/${no}`);
    });
});