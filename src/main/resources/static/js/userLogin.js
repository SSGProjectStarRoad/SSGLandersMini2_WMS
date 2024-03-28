// 모달 창 표시 함수
// 특정 ID를 가진 모달 창을 화면에 표시
// 동시에 다른 모달 창을 숨겨!
function showModal(modalId) {
    var modals = document.getElementsByClassName('modal');
    for (var i = 0; i < modals.length; i++) {
        modals[i].style.display = 'none';
    }
    var modal = document.getElementById(modalId);
    modal.style.display = 'block';
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

// 아이디 찾기 기능
    function findUserId() {
        var name = document.getElementById('findname').value;
        var phone = document.getElementById('phone').value;

        // AJAX 요청을 사용하여 서버에 name과 phone 데이터를 전송
        $.ajax({
            url: '/user/find-id', // 요청을 보낼 서버의 URL 주소
            type: 'POST', // HTTP 요청 방식 (GET, POST 등)
            contentType: 'application/json', // 서버로 보내는 데이터의 타입
            data: JSON.stringify({name: name, phone: phone}), // 서버로 보낼 데이터
            success: function (response) {
                // 서버로부터 응답을 성공적으로 받았을 때 실행할 코드
                alert('아이디 찾기 성공! 찾은 아이디: ' + response.userId);
                document.getElementById('findIdModal').querySelector('form').reset();
                window.location.href = '/user/userLogin'
            },
            error: function () {
                // 요청 실패 시 실행할 코드
                alert('아이디를 찾을 수 없습니다.');
            }
        });
    }

    function findUserPw() {
        var username = document.getElementById('id').value;
        var telnum = document.getElementById('phonePw').value;

        // AJAX 요청을 사용하여 서버에 사용자 정보를 전송
        $.ajax({
            url: '/user/find-pw', // 요청을 보낼 서버의 URL 주소
            type: 'POST', // HTTP 요청 방식 (GET, POST 등)
            contentType: 'application/json', // 서버로 보내는 데이터의 타입
            data: JSON.stringify({username: username, telnum: telnum}), // 서버로 보낼 데이터
            success: function (response) {
                // 서버로부터 임시 비밀번호를 성공적으로 받았을 때 실행할 코드
                alert('임시 비밀번호 생성 완료! 임시 비밀번호: ' + response.tempPassword);
                document.getElementById('findPwModal').querySelector('form').reset();
                window.location.href = '/user/userLogin'
            },
            error: function () {
                // 요청 실패 시 실행할 코드
                alert('비밀번호를 찾을 수 없습니다.');
            }
        });
    }

// 아이디 유효성 검사
    $(document).ready(function () {
        $('#registerUsername').on('input', function () {
            var username = $(this).val();
            var errorBubble = $('#usernameError');
            if (username.length < 5 || username.length > 20 || !/^[a-zA-Z0-9]*$/.test(username)) {
                errorBubble.text("아이디는 최소 5자 이상,영문자와 숫자로 입력합니다.");
                errorBubble.show(); // 오류 메시지를 보여줍니다.
            } else {
                errorBubble.hide(); // 오류 메시지를 숨깁니다.
            }
        });
    });

// 비밀번호 유효성 검사
    $(document).ready(function () {
        // 비밀번호 유효성 검사
        $('#registerPassword, #confirmPassword').on('input', function () {
            var password = $('#registerPassword').val();
            var confirmPassword = $('#confirmPassword').val();
            var passwordErrorBubble = $('#passwordError');

            if (password.length < 8 || !/[a-zA-Z]/.test(password) || !/\d/.test(password) || !/[@#$%^&+=!]/.test(password)) {
                passwordErrorBubble.text("비밀번호는 최소 8자 이상,영문자와 숫자,특수문자로 입력합니다.");
                passwordErrorBubble.show(); // 오류 메시지를 보여줍니다.
            } else if (password !== confirmPassword) {
                passwordErrorBubble.text("비밀번호가 일치하지 않습니다.");
                passwordErrorBubble.show(); // 오류 메시지를 보여줍니다.
            } else {
                passwordErrorBubble.hide(); // 오류 메시지를 숨깁니다.
            }
        });
    });


// 회원가입 폼 제출 시 실행될 함수 (AJAX 요청 포함)
// 폼 제출을 AJAX 요청으로 처리해 페이지 리로드 없이 서버에 데이터 전송
    $('#signupForm').submit(function (e) {
        e.preventDefault();

        var usertype = $('#roleSelect').val();
        var username = $('#registerUsername').val();
        var password = $('#registerPassword').val();
        var name = $('#name').val();
        var telnum = $('#telnum').val();
        var address = $('#UserAdd1').val() + ' ' + $('#UserAdd2').val();

        var formData = {
            usertype: usertype,
            username: username,
            password: password,
            name: name,
            telnum: telnum,
            address: address
        };

        // AJAX 요청을 설정합니다.
        $.ajax({
            type: 'POST',
            url: '/user/register',
            contentType: 'application/json',
            data: JSON.stringify(formData),
        }).done(function (response) {
            // 회원가입 성공 처리
            alert('회원가입 성공!');
            window.location.href = '/user/userLogin'
        }).fail(function (error) {
            // 회원가입 실패 처리
            alert('회원가입 실패: ' + error.responseText);
        });
    });


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
                document.getElementById("UserAdd1").value = addr;
                document.getElementById("UserAdd1").value += extraAddr;
                document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동

                // 외부 API에서 가져온 주소를 주소 입력란에 설정
                document.getElementById("address").value = addr + extraAddr;
            }
        }).open();
    }


// DOM이 로드된 후 실행
// 페이지 로딩 완료 후, 회원가입 폼에 이벤트 리스너 추가
// 제출 시 submitRegisterForm 함수가 호출되도록 설정
    document.addEventListener('DOMContentLoaded', function () {
        // 회원가입 폼 이벤트 리스너 등록
        var signupForm = document.getElementById('signupForm'); // 수정된 부분
        if (signupForm) {
            signupForm.addEventListener('submit', submitRegisterForm);
        }
    });

// ajax는 비동기적으로 서버와 통신할 수 있게 해줌
// 사용자가 폼을 제출할 때 전체 페이지를 새로고침 하지 않아도 서버에 데이터 전송하고 응답 받기 가능
// 회원가입 후 모달 창을 닫고 사용자에게 알림을 보내는 것과 같이 작은 변경사항 적용 가능
// 전체 페이지를 새로고침하지 않기 때문에, 서버로의 요청이 더 가벼움
// 모달 창 구현과 직접적인 관련이 있는 건 아니지만
// ajax는 폼 데이터를 서버로 비동기적으로 전송해 사용자가 페이지를 떠나지 않고도 작업을 완료할 수 있도록
// 페이지의 나머지 부분 사용자와 상호작용 계속 가능
// 웹이 더 빠르고 반응적으로 동작 가능!!!

// 예를 들어, 사용자가 회원가입 폼을 작성하고 제출 버튼을 클릭했다고 가정
// ajax를 사용하지 않는다면 사용자는 폼 데이터가 서버로 전송되는 동안 페이지를 떠나게 되고
// 처리 결과를 보기 위해 새로운 페이지로 이동하거나 현재 페이지를 새로고침해야 함

// ajax를 사용한다면
// 1. 자바 스크립트를 통해 이벤트 핸들러 설정 : 사용자가 제출 버튼을 클릭할 때
// 페이지 리로드 방지하고 대신 자바 스크립트 함수 실행하도록 설정

// 2. ajax 요청 전송 : 자바 스크립트에선 폼 데이터 취합해 서버로의 비동기 요청 시작
// 이 과정에서 페이지 리로드 되지 않음!

// 3. 서버에서 응답 처리 : 서버는 요청을 처리 후 응답을 보냄
// 자바 스크립트는 이 응답을 받아 사용자에게 적절한 피드백을 제공
// 예, 회원가입 성공 메시지를 보여주거나 입력 오류를 알릴 수 있음

// 즉!!! 사용자는 폼 제출과 같은 작업을 수행하면서도 페이지를 떠나거나 리로드할 필요 없음
