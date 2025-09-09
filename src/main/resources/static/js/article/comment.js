const template = (data) => {
    const div = document.getElementById('commentList');

    // 템플릿 복제
    const template = document.getElementById('item-template');
    const element = template.cloneNode(true); // true는 하위 요소까지 복제
    element.removeAttribute('id');
    element.dataset.id = data.id;
    element.classList.add('comment');

    element.querySelector('.name').textContent += data.nickname;

    const formattedDate = getDateFormat(new Date(data.cdate));
    element.querySelector('.cdate').textContent += formattedDate;

    let body = data.body.replace(/(\r\n|\r|\n)/g, "<br />");
    element.querySelector('.card-text').innerHTML = body;

    div.appendChild(element);
};

const dataReset = () => {
    const commentForm = document.getElementById('commentForm');
    commentForm.reset();
}

const no = document.getElementById('no').value;
fetch(`/comments/all/${no}`)
.then(response => response.json())
.then(data => {
    for (let value of data) {
        template(value);
    }
})
.catch(error => {
    alert("덧글 목록을 불러오는데 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
    console.error(error);
});
document.addEventListener("click", function (e) {
    if (e.target.id === "commentInsertBtn") {
        if (!checkForm("#nickname","작성자를"))  return;
        else if (!checkForm("#body","내용을"))  return;
        else{
            /* JSON.stringify(): JavaScript 값이나 객체를 JSON 문자열로 변환. */
            fetch('/comments/commentInsert', {
                method: "post",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    nickname: document.getElementById('nickname').value,
                    body: document.getElementById('body').value,
                    article: {
                        no: no
                    }
                })
            })
            .then(response => response.json())
            .then(data => {
                if(data!==""){
                    alert("댓글 등록이 완료되었습니다.");
                    //let data = JSON.parse(result);
                    dataReset();
                    template(data);
                }
            })
            .catch(error => {
                alert("댓글 입력에 문제가 있어 잠시 후 다시 진행해 주세요.");
                console.error(error);
            });
        }
    }
});
document.addEventListener("click", function (e) {
    if (e.target.classList.contains("commentUpdateFormBtn")) {
        e.preventDefault();

        // 기존 "취소" 버튼이 있다면 제거
        const resetBtn = document.querySelector("#commentForm .resetBtn");
        if (resetBtn) resetBtn.remove();

        const card = e.target.closest("div.comment");
        document.getElementById("id").value = card.dataset.id;

        const nickname = card.querySelector(".card-header .name").textContent;
        const nicknameInput = document.getElementById("nickname");
        nicknameInput.value = nickname;
        nicknameInput.readOnly = true;

        const bodyHtml = card.querySelector(".card-text").innerHTML;
        const bodyText = bodyHtml.replace(/(<br>|<br\/>|<br \/>)/gi, '\r\n');
        document.getElementById("body").value = bodyText;

        const sendBtn = document.querySelector("#commentForm button[type='button']");
        sendBtn.id = "commentUpdateBtn";

        const resetButton = document.createElement("button");
        resetButton.type = "button";
        resetButton.className = "btn btn-secondary col-sm-1 resetBtn mx-2";
        resetButton.textContent = "취소";

        // sendBtn 다음에 삽입
        sendBtn.parentNode.insertBefore(resetButton, sendBtn.nextSibling);
    }
});

const init = () => {
    const nicknameInput = document.getElementById("nickname");
    nicknameInput.readOnly = false;

    const commentButton = document.querySelector("#commentForm button[type='button']");
    if (commentButton) {
        commentButton.id = "commentInsertBtn";
    }

    const resetBtn = document.querySelector("#commentForm button.resetBtn");
    if (resetBtn) {
        resetBtn.remove();
    }

    document.getElementById("id").value = 0;
};

document.addEventListener("click", function (e) {
    if (e.target.classList.contains("resetBtn")) {
        dataReset();
        init();
    }
});

document.addEventListener("click", function (e) {
    if (e.target.id === "commentUpdateBtn") {
        const commentId = document.getElementById("id").value;

        if (!checkForm("#body", "댓글내용을")) return;
        const body = document.getElementById("body").value;

        fetch(`/comments/${commentId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ body: body })
        })
        .then(response => response.json())
        .then(data => {
            if (data !== "") {
                alert("댓글 수정이 완료되었습니다.");
                dataReset();
                init();

                const commentElement = document.querySelector(`#commentList div[data-id='${commentId}']`);
                if (commentElement) {
                    const contentDiv = commentElement.querySelector(".card-text");
                    contentDiv.innerHTML = data.body.replace(/(\r\n|\r|\n)/g, "<br />");
                }
            }
        })
        .catch(error => {
            alert("시스템에 문제가 있어 잠시 후 다시 진행해 주세요.");
            console.error(error);
        });
    }
});

document.addEventListener("click", function (e) {
    if (e.target.classList.contains("commentDeleteBtn")) {
        e.preventDefault();

        const commentElement = e.target.closest("div.comment");
        const commentId = commentElement.dataset.id;
        console.log(commentId);

        if (confirm("선택하신 댓글을 삭제하시겠습니까?")) {
            fetch(`/comments/${commentId}`, {
                method: "DELETE"
            })
            .then(response => {
                commentElement.remove();
                alert("댓글 삭제가 완료되었습니다.");
            })
            .catch(error => {
                alert("댓글 삭제에 문제가 있어 잠시 후 다시 진행해 주세요.");
                console.error(error);
            });
        }
    }
});