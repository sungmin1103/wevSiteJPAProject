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

const keywordInput = document.getElementById("keyword");
const searchSelect = document.getElementById("search");
let value = "";
let regex;

const searchKeyword = function() {
    const keyword = keywordInput.value;
    const search = searchSelect.value;

    if((keyword !== "" || startDate.value !== "") && search !== "content") {
        value = search === 'title' ? ".list tr .goDetail"
            : search === 'name' ? ".list tr td.name"
            : search ==='regDate' ? ".list tr td.date"
            : "";

        const elements = document.querySelectorAll(value);
        elements.forEach(el => {
            if (search === 'regDate') {
                //날짜 하이라이트만 날짜 부분으로 적용
                const dateText = el.textContent;
                const dateOnly = dateText.slice(0, 10); //  yyyy-mm-dd
                const regex = new RegExp(dateOnly, 'g');
                el.innerHTML = dateText.replace(regex, `<span class='board-required'>${dateOnly}</span>`);
            } else {
                const regex = new RegExp(keyword, "gi");
                el.innerHTML = el.innerHTML.replace(regex, match => `<span class='board-required'>${match}</span>`);
            }
        });
    }
};

const dateSearchDisplay = function() {
    const dateAreas = document.querySelectorAll(".dateArea");
    const textArea = document.querySelector(".textArea");

    const isDateSearch = searchSelect.value == "regDate";

    // dateArea 모든 요소에 대해 hidden 토글
    dateAreas.forEach(el => el.hidden = !isDateSearch);
    textArea.hidden = isDateSearch;

    if (!isDateSearch) {
        keywordInput.focus();
    }
};

document.getElementById("searchBtn").addEventListener("click", function () {
    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");

    if (searchSelect.value !== "regDate") {
        startDate.value = "";
        endDate.value = "";
        if (!chkData("#keyword", "검색어를")) return;
    } else {
        keywordInput.value = "";
        if (!chkData("#startDate", "시작날짜를")) return;
        if (!chkData("#endDate", "종료날짜를")) return;

        if (startDate.value > endDate.value) {
            alert("시작날짜가 종료날짜보다 더 클 수 없습니다.");
            return;
        }
    }


    document.getElementById("page").value = 1;
    formSubmit("searchForm", "get", "/board/boardList");
});

document.getElementById("allSearchBtn").addEventListener("click", function () {
    locationProcess("/board/boardList");
});

searchSelect.addEventListener("change", dateSearchDisplay);

// 초기 설정
dateSearchDisplay();
searchKeyword();