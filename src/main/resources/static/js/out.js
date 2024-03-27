
// clear 버튼
document.querySelector(".clearBtn").addEventListener("click", function (e){
    e.preventDefault()
    e.stopPropagation()

    self.location ='/ssglanders/list'

},false)

document.querySelector(".pagination").addEventListener("click", function (e) {
    e.preventDefault()
    e.stopPropagation()

    const target = e.target  // 클릭한 객체 타겟으로 가져온다

    if(target.tagName !== 'A') {
        return
    }
    const num = target.getAttribute("data-num")
    self.location = `/ssglanders/outApproval?page=\${num}`

    const formObj = document.querySelector("form")
    formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`

    formObj.submit();

},false)

// 테이블 클릭
// function handleRowClick(event) {
//     var target = event.target;
//     if (target.tagName === 'TD') {
//         var tno = target.parentElement.getAttribute('data-tno');
//         window.location.href = '/ssglanders/details?oid=' + oid;
//     }
// }

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

// 우편번호
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업을 통한 검색 결과 항목 클릭 시 실행
            var addr = ''; // 주소_결과값이 없을 경우 공백
            var extraAddr = ''; // 참고항목

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 도로명 주소를 선택
                addr = data.roadAddress;
            } else { // 지번 주소를 선택
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } else {
                document.getElementById("UserAdd1").value = '';
            }



            // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
            document.getElementById('zipp_code_id').value = data.zonecode;
            document.getElementById("UserAdd1").value = addr;
            document.getElementById("UserAdd1").value += extraAddr;
            document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동
        }
    }).open();
}

function registerWaybill() {
    var uponNum = document.getElementById('zipp_code_id').value;
    var userAdd1 = document.getElementById('UserAdd1').value;
    var userAdd2 = document.getElementById('UserAdd2').value;
    var date = document.getElementById('date').value;
    var tekbe = document.getElementById('tekbe').value;
    var oid = document.getElementById('oid').value;

    console.log(uponNum + " " + userAdd1 + " " + userAdd2+" "+date+" "+tekbe);

    $.ajax({
        url: '/ssglanders/registerWay', // 서버의 URL에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ uponNum: uponNum, userAdd1: userAdd1, userAdd2: userAdd2, date: date, tekbe: tekbe,oid:oid }),
        success: function(response) {
            alert('등록하였습니다.');
            window.location.href = window.location.pathname; // 현재 페이지로 다시 로드
        },
        error: function() {
            alert('등록 실패. 목록을 확인해주세요.');
        }
    });
}

function modifyShipping() {
    let sid = document.getElementById('sid').value;
    let wbid = document.getElementById('wbid').value;

    console.log(sid+" "+wbid);
    $.ajax({
        url: '/ssglanders/modifyShipping', // 서버의 URL에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ sid: sid, wbid:wbid}),
        success: function(response) {
            alert('수정하였습니다.');
            window.location.href='/ssglanders/outList'
        },
        error: function() {
            alert('등록 실패. 목록을 선택해 주세요.');
        }
    });
}

function removeOid(){
    let oid = document.getElementById('oid').value;

    $.ajax({
        url: '/ssglanders/removeOid', // 서버의 URL에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ oid: oid}),
        success: function(response) {
            alert('삭제하였습니다.');
            window.location.href='/ssglanders/outList'
        },
        error: function() {
            alert('삭제 실패. 목록을 선택해 주세요.');
        }
    });
}

function changeStatus(){
    let oid = document.getElementById('selectedOid').value;
    let status = document.getElementById('status').value;

    $.ajax({
        url: '/ssglanders/status', // 서버의 URL에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ oid: oid, status: status}),
        success: function(response) {
            alert('변경하였습니다.');
            window.location.href='/ssglanders/outList'
        },
        error: function() {
            alert('변경 실패.');
        }
    });
}

function outApproval(){
    let oid = document.getElementById("approvalOid").value;

    $.ajax({
        url: '/ssglanders/outApproval', // 서버의 URL에 맞게 수정
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ oid: oid}),
        success: function(response) {
            alert('승인합니다. 운송장작성해주세요.');
            window.location.href = window.location.pathname;
        },
        error: function() {
            alert('승인 실패.');
        }
    })
}

function approvalAlert(){
    alert('승인합니다. 운송장작성해주세요.');
}

// $('.outcoming').on('click', function () {
//     window.location.href = '/ssglanders/outApproval';
// });
//
// $('.incoming').on('click', function () {
//     window.location.href = '/ssglanders/inApproval';
