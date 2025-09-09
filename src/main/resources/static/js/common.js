function chkData(selector, message) {
    const element = document.querySelector(selector);
    const value = element.value.trim();

    if (value === "" || value === "0") {
        alert(`${message} 입력해 주세요.`);
        element.value = "";
        element.focus();
        return false;
    }
    return true;
}

const formSubmit = (selector, method, action) => {
    const form = document.getElementById(selector);
    form.method = method;
    form.action = action;
    form.submit();
}

const formReset = (selector) => {
    const form = document.getElementById(selector);
    form.reset();
}

const locationProcess = (url) => {
    location.href = url;
}

function checkData(selector, message) {
    const $element = $(selector);

    if ($element.val().trim() === "") {
        alert(`${message} 입력해 주세요.`);
        $element.val("");
        $element.focus();
        return false;
    }
    return true;
}

const actionProcess = (selector, method, action) => {
    const $form = $(selector);
    $form.attr({
        "method":method,
        "action":action
    });
    $form.submit();
};

const resetProcess = (selector) => {
    const $form = $(selector);
    $form[0].reset();
};

const chkFile = (item) =>{
    const fileInput = typeof item === 'string' ? document.querySelector(item) : item;

    const fileName = fileInput.value;
    const ext = fileName.split('.').pop().toLowerCase();
    const allowedExts = ['gif','png','jpg'];    //허용 확장자 배열

    if( !allowedExts.includes(ext)){
        alert('업로드 가능한 파일 확장자는 gif, png, jpg입니다.');
        fileInput.value = "";
        return false;
    }

    return true;
}

// enctype 속성의 기본값은 "apllication/x-www-form-urlcencoded". POST 방식 폼 전송에 기본 값으로 사용

const formFileSubmit = (selector, method, action) =>{
    const form = document.getElementById(selector);
    form.method = method;
    form.enctype = "multipart/form-data";
    form.action = action;
    form.submit();
}

/**
*함수명 : getDateFormat
*설명: Date 객체를 'YYYY-MM-DD'형식으로 반환
*참고
*padStart(문자열 길이, [앞쪽에 채울 문자 (기본값: 공백 "")]):
        문자열 앞쪽에 특정 문자를 채워 저장한 길이만큼 맞춰주는 문장려(String) 메서드
*/

function getDateFormat(dateValue){
    const year = dateValue.getFullYear();
    const month = String(dateValue.getMonth() +1 ).padStart(2,'0');
    const day = String(dateValue.getDate()).padStart(2,'0');

    return `${year}-${month}-${day}`;
}

function checkForm(itemSelector, msg){
    const input = document.querySelector(itemSelector);
    if(input.value.trim() ===""){
        input.placeholder =`${msg}입력해 주세요.`;
        input.value = "";
        input.focus();
        return false;
    }
    return true;
}