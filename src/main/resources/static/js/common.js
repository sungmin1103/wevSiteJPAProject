function chkData(selector, message){
    console.log(selector);
    const element = document.querySelector(selector);
    const value = element.value.trim();

    if (value === "" || value ==="0") {
        alert(`${message} 입력해 주세요.`);
        element.value = "";
        element.focus();
        return false;
    }
    return true;
}

const formSubmit = (selector, method, action) =>{
    const form = document.getElementById(selector);
    form.method = method;
    form.action = action;
    form.submit();
}

const formReset = (selector)=>{
    const form = document.getElementById(selector);
    form.reset();
}

const locationProcess=(url)=>{
    location.href = url;
}

function dataCheck(itemSelector, outSelector, msg) {
    const item = document.querySelector(itemSelector);
    const out = document.querySelector(outSelector);
    const value = item.value.trim();

    if (value === "") {
    out.textContent = `${msg} 입력해 주세요`;
    item.value = "";
    item.focus();
    return false;
  }
  return true;
}

const chkFile = (item) => {
    const fileInput = typeof item ==='string' ? document.querySelector(item) : item;

    const fileName = fileInput.value;
    const ext = fileName.split('.').pop().toLowerCase();
    const allowedExts = ['gif', 'png', 'jpg'];  // 하용 확장자 배열

    if (!allowedExts.includes(ext)) {
        alert('업로드 가능한 파일 확장자는 gif, png, jpg입니다.');
        fileInput.value = "";
        return false;
    }
    return true;
}

// enctype 속성의 기본 값은 "application/x-www-form-urlcencoded". POST방식 폼 전송에 기본 값으로 사용
const formFileSubmit = (selector, method, action) => {
    const form = document.getElementById(selector);
    form.method = method;
    form.enctype = "multipart/form-data";
    form.action = action;
    form.submit();
}

//function checkData(selector, message) {
//    const $element = $(selector);
//
//    if ($element.val().trim() === "") {
//        alert(`${message} 입력해 주세요.`);
//        $element.val("");
//        $element.focus();
//        return false;
//    }
//    return true;
//}
//
//const actionProcess = (selector, method, action) => {
//const $form = $(selector);
//$form.attr({
//    "method":method,
//    "action":action
//    });
//    $form.submit();
//};
//
//const resetProcess = (selector) => {
//    const $form = $(selector);
//    $form[0].reset();
//}
//
//const DeleteProcess = (selector) => {
//
//}