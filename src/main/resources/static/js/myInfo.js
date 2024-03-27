// 모달 창 표시 함수
// 특정 ID를 가진 모달 창을 화면에 표시
// 동시에 다른 모달 창을 숨겨!
function showModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = 'block'; // 모달 창 표시
    modal.style.zIndex = 101; // 활성화된 모달의 z-index를 높임
}

// 모달 창 닫기 함수, 인자로 모달의 ID를 받습니다.
function closeModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = 'none';
    modal.style.zIndex = 100; // 비활성화된 모달의 z-index를 낮춤
}

// 모달 창 바깥 클릭 시 닫기 이벤트
// 사용자가 모달 창 외부를 클릭할 때 해당 모달 창 닫기!!
window.onclick = function (event) {
    if (event.target.classList.contains('modal')) {
        closeModal(event.target.id);
    }
}

function deleteAccount() {
    if (confirm("정말로 계정을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.")) {
        $.ajax({
            url: '/user/deleteAccount',
            type: 'POST',
            //여기서는 data 속성을 보낼 필요가 없습니다.
            //일반적으로 서버에서 현재 로그인한 사용자의 username을 직접 처리하는 방법을 선택하는 것이 좋습니다. AJAX 요청에 데이터를 전송할 필요 없이,
            //서버 사이드에서 사용자 인증 정보를 통해 필요한 작업을 수행할 수 있습니다.
            success: function (response) {
                alert("계정이 성공적으로 삭제되었습니다.");
                window.location.href = '/user/userLogin'
            },
            error: function (xhr, status, error) {
                alert("오류 발생: " + error);
            }
        });
    }
}

// 회원 정보 수정 페이지에도 주소 검색 기능을 추가
function updateUserInfo() {

    // 입력 필드에서 데이터를 가져옵니다.
    var password = $("#password").val();
    var newpassword = $("#newpassword").val(); // 새 비밀번호 확인을 위한 필드
    var telnum = $("#telnum").val();

    // 새 비밀번호와 비밀번호 재입력이 일치하는지 확인
    if (password !== newpassword) {
        alert('새 비밀번호가 일치하지 않습니다.');
        return; // 비밀번호가 일치하지 않으면 함수 실행 종료
    }

    // 새 주소 구성
    var baseAddress = $("#UserAdd1").val(); // 기본 주소
    var detailAddress = $("#UserAdd2").val(); // 상세 주소
    var newAddress = baseAddress + " " + detailAddress; // 기본 주소와 상세 주소 결합



    // 폼 데이터를 객체로 구성합니다.
    var userInfo = {
        password: password, // 현재 비밀번호, 필요에 따라 사용
        telnum: telnum,
        address: newAddress
    };

    // AJAX 요청을 설정합니다.
    $.ajax({
        url: '/user/updateInfo',
        type: 'POST',
        contentType: 'application/json', // JSON 형태로 데이터를 보냄을 명시
        data: JSON.stringify(userInfo), // 객체를 JSON 문자열로 변환
        success: function (response) {
            // 성공 시 클라이언트에 대한 처리
            alert('정보가 성공적으로 업데이트되었습니다.');
            window.location.href = '/ssglanders/overall';
        },
        error: function (xhr, status, error) {
            // 오류 처리
            alert('정보 업데이트 중 오류가 발생했습니다: ' + error);
        }
    });
}
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            var addr = ''; // 주소_결과값이 없을 경우 공백
            var extraAddr = ''; // 참고항목

            if (data.userSelectedType === 'R') { // 도로명 주소를 선택
                addr = data.roadAddress;
            } else { // 지번 주소를 선택
                addr = data.jibunAddress;
            }

            if (data.userSelectedType === 'R') {
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
            } else {
                document.getElementById("UserAdd1").value = '';
            }

            // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
            document.getElementById('zipp_code_id').value = data.zonecode;
            document.getElementById("UserAdd1").value = addr + extraAddr;
            document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동

            // 외부 API에서 가져온 주소를 주소 입력란에 설정
            document.getElementById("address").value = addr + extraAddr;
        }
    }).open();
}
// DOM이 완전히 로드된 후에 이벤트 리스너를 추가
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('searchAddressBtn').addEventListener('click', function() {
        execDaumPostcode();
    });
});

function goBack() {
    window.history.back();
}
