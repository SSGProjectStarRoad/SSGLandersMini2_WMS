function loadUserData() {
    // Ajax를 사용하여 사용자 정보를 가져오는 요청을 보냅니다.
    $.ajax({
        type: "GET", // GET 메서드를 사용하여 데이터를 요청합니다.
        url: "/user/getUserData", // 사용자 정보를 가져오는 엔드포인트 URL입니다.
        success: function(response) {
            console.log(response);
            // 요청이 성공하면 사용자 정보를 받아옵니다.
            // 받아온 정보를 각 입력 필드에 채워넣습니다.
            $('#username').val(response.username);
            $('#password').val(response.password);
            $('#usertype').val(response.usertype);
            $('#name').val(response.name);
            $('#address').val(response.address);
            $('#telnum').val(response.telnum);
        },
        error: function(xhr, status, error) {
            // 요청이 실패할 경우 에러 처리를 합니다.
            console.error("Error loading user data:", error);
            // 사용자에게 오류 메시지를 표시
            alert("Failed to load user data. Please try again later.");
        }
    });
}

