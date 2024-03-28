$(document).ready(function () {

    // 회원정보 수정 모달 열기
    let editButtons = document.querySelectorAll('.edit-btn');
    editButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 수정 버튼 클릭 시 해당 사용자 정보를 모달에 표시
            var username = this.dataset.username;
            var password = this.dataset.password;
            var name = this.dataset.name;
            var telnum = this.dataset.telnum;
            var address = this.dataset.address;
            // 모달에 가져온 사용자 정보 표시
            fillModalWithData(username, password, name, telnum, address);
        });
    });


    // 회원정보 수정 완료 버튼 클릭 시
    $(document).on('click', '.btn-update-real', function () {
        // 수정된 정보를 가져와서 서버로 전송
        var username = $('#username').val();
        var name = $('#name').val();
        var telnum = $('#telnum').val();
        var address = $('#address').val();

        console.log('사용자 정보: ', username)

        var userData = {
            username,
            name,
            telnum,
            address
        };

        console.log('수정된 사용자 정보:', userData);

        // AJAX를 통해 서버에 수정된 사용자 정보 전송
        $.ajax({
            url: '/user/update', // 서버의 URL. 필요에 따라 수정하세요.
            type: 'POST',
            contentType: 'application/json', // JSON 형식의 데이터 전송을 명시
            data: JSON.stringify(userData), // JavaScript 객체를 JSON 문자열로 변환
            success: function (response) {
                // 성공적으로 수정되면 페이지 새로고침
                alert('사용자 정보가 성공적으로 수정되었습니다.');
                location.reload();
            },
            error: function (error) {
                // 수정 실패 시 에러 메시지 표시
                console.error('수정 실패:', error);
                alert('사용자 정보 수정 실패');
            }
        });
    });

    // 수정 모달에 데이터를 채우는 기능
    function fillModalWithData(username, password, name, telnum, address) {
        // 모달의 입력 필드에 데이터를 채웁니다.
        $('#username').val(username);
        $('#password').val(password);
        $('#name').val(name);
        $('#telnum').val(telnum);
        $('#address').val(address);
        // 모달 열기
        $('#myModal').modal('show');
    }

    // 회원 삭제 버튼 클릭 시
    $('.confirmDelete').on('click', function () {
        var username = $(this).data('username');

        // AJAX를 통해 서버에 사용자 삭제 요청 전송
        $.ajax({
            type: 'POST',
            url: '/user/delete',
            contentType: 'application/json',
            data: JSON.stringify({
                "username": username
            }),
            success: function (response) {
                // 성공적으로 삭제되면 페이지 새로고침
                alert("사용자가 성공적으로 삭제되었습니다.");
                location.reload();
            },
            error: function (xhr, status, error) {
                // 삭제 실패 시 에러 메시지 표시
                console.error("사용자 삭제 에러:", error);
                alert("사용자 삭제에 실패했습니다. 다시 시도해주세요.");
            }
        });
    });

    // 페이지네이션 기능
    $(document).ready(function () {
        $(".pagination").on("click", "a", function (e) {
            e.preventDefault(); // 기본 이동 방지

            const num = $(this).data("num"); // 클릭된 페이지 번호
            const currentUrl = window.location.href;
            const newUrl = updateQueryStringParameter(currentUrl, 'page', num); // 페이지 번호를 업데이트한 새 URL 생성

            window.location.href = newUrl; // 새 URL로 페이지 이동
        });
    });

// URL의 쿼리 스트링 파라미터를 업데이트하는 함수
    function updateQueryStringParameter(uri, key, value) {
        var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
        var separator = uri.indexOf('?') !== -1 ? "&" : "?";
        if (uri.match(re)) {
            return uri.replace(re, '$1' + key + "=" + value + '$2');
        } else {
            return uri + separator + key + "=" + value;
        }
    }

});

// 다음 우편번호 검색 API 사용
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업을 통한 검색 결과 항목 클릭 시 실행
            var addr = ''; // 주소_결과값이 없을 경우 공백
            var extraAddr = ''; // 참고항목

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
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

            //주소합치기
            function getFullAddress() {
                var userAdd1 = document.getElementById("UserAdd1").value;
                var userAdd2 = document.getElementById("UserAdd2").value;
                var fullAddress = userAdd1 + " " + userAdd2;
                return fullAddress; // 합쳐진 전체 주소 반환
            }

            // 선택된 우편번호와 주소 정보를 input 박스에 넣는다.
            document.getElementById('zipp_code').value = data.zonecode;
            document.getElementById("UserAdd1").value = addr;
            document.getElementById("UserAdd1").value += extraAddr;
            document.getElementById("UserAdd2").focus(); // 우편번호 + 주소 입력이 완료되었음으로 상세주소로 포커스 이동
        }
    }).open();

}



