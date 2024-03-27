document.addEventListener("DOMContentLoaded", function () {
    const navToggle = document.querySelector(".mobile-nav-toggle");
    const navDiv = document.querySelector(".nav-div");

    navToggle.addEventListener("click", function () {
        navDiv.classList.toggle("show"); // 'show' 클래스를 토글하여 메뉴 표시
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const navToggle = document.querySelector(".mobile-nav-toggle");
    const navDiv = document.querySelector(".nav-div");
    const navFlexColumn = navDiv.querySelector(".nav.flex-column");

    navToggle.addEventListener("click", function () {
        // navDiv의 표시 상태를 토글
        if (navDiv.style.display === 'none' || navDiv.style.display === '') {
            navDiv.style.display = 'flex';
            navFlexColumn.style.display = 'flex'; // 필요한 경우 .nav.flex-column의 display도 조절
        } else {
            navDiv.style.display = 'none';
            navFlexColumn.style.display = 'none'; // 필요한 경우
        }
    });
});

window.addEventListener("resize", function () {
    const navDiv = document.querySelector(".nav-div");
    const navFlexColumn = navDiv.querySelector(".nav.flex-column");
    if (window.innerWidth > 768) {
        navDiv.style.display = "flex"; // 또는 원하는 다른 display 값
        navDiv.classList.remove("show"); // 'show' 클래스 제거
        navFlexColumn.style.display = "flex";
    } else {
        if (navDiv.classList.contains("show")) {
            navDiv.style.display = "flex";
        } else {
            navDiv.style.display = "none";
        }
        navFlexColumn.style.display = "none";
    }
});

// 페이지

// document.querySelector(".pagination").addEventListener("click", function (e) {
//     e.preventDefault();
//     e.stopPropagation();
//
//     const target = e.target;
//
//     if (target.tagName !== 'A') {
//         return;
//     }
//
//     const num = target.getAttribute("data-num");
//     self.location = `/todo/list?page=${num}`;
//
//     const formObj = document.querySelector("form");
//     formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`;
//
//     formObj.submit();
// }, false);

// 옵션 선택 시 hidden 필드 업데이트
function updateHiddenField(selectElement) {
    var selectedValue = selectElement.value;
    document.getElementById("selectedValueHidden").value = selectedValue;
}


// clear 버튼
document.querySelector(".clearBtn").addEventListener("click", function (e){
    e.preventDefault()
    e.stopPropagation()

    self.location ='/ssglanders/list'

},false)

// 테이블 클릭
function handleRowClick(event) {
    var target = event.target;
    if (target.tagName === 'TD') {
        var tno = target.parentElement.getAttribute('data-tno');
        window.location.href = '/ssglanders/details?oid=' + oid;
    }
}
//페이지 function
function handlePaginationClick(event) {
    event.preventDefault();
    event.stopPropagation();

    const target = event.target;

    if (target.tagName !== 'A') {
        return;
    }

    const num = target.getAttribute("data-num");
    window.location.href = `/todo/list?page=${num}`;

    const formObj = document.querySelector("form");
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'page';
    input.value = num;
    formObj.appendChild(input);

    formObj.submit();
}

// 배송상태 select

// $(document).ready(function(){
//     $(".form-select").change(function(){
//         var selectedValue = $(this).val();
//         if(selectedValue !== ""){
//             $.ajax({
//                 type: "POST",
//                 url: "/ssglanders/outList",
//                 data: { selectedValue: selectedValue },
//                 success: function(response){
//                     console.log("POST 요청이 성공하였습니다.");
//                     // 원하는 작업을 수행합니다.
//                 },
//                 error: function(){
//                     console.log("POST 요청이 실패하였습니다.");
//                     // 오류 처리를 수행합니다.
//                 }
//             });
//         }
//     });
// });