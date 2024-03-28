

// script 주소검색 api
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




//상세보기모달


document.addEventListener('DOMContentLoaded', function () {
    var exampleModal = document.getElementById('exampleModal2');
    exampleModal.addEventListener('shown.bs.modal', function (event) {

        // 버튼에서 모달로 정보 전달
        var button = event.relatedTarget;
        var id = button.getAttribute('data-id');
        var name = button.getAttribute('data-name');
        var address = button.getAttribute('data-address');
        var warehousetype = button.getAttribute('data-warehousetype');
        var capacity = button.getAttribute('data-capacity');
        var usingcapacity = button.getAttribute('data-usingcapacity');

        // 모달에 정보 설정
        var modalTitle = exampleModal.querySelector('.modal-title');
        var modalBodyId = document.getElementById('modal-warehouse-id');
        var modalBodyName = document.getElementById('modal-warehouse-name');
        var modalBodyAddress = document.getElementById('modal-warehouse-address');
        var modalBodyType = document.getElementById('modal-warehouse-type');
        var modalBodyCapacity = document.getElementById('modal-warehouse-capacity');
        var modalBodyUsingCapacity = document.getElementById('modal-warehouse-usingcapacity');

        modalTitle.textContent = '창고 상세 - ' + name;
        modalBodyId.value = id;
        modalBodyName.value = name;
        modalBodyAddress.value = address;
        modalBodyType.value = warehousetype;
        modalBodyCapacity.value = capacity;
        modalBodyUsingCapacity.value = usingcapacity;

        // 지도 로직 추가 (지도 관련 로직을 여기에 추가)

        //지도
        initMap(address, name);
    });




    // 지도관련
    function initMap(address, name) {
        console.log("initMap called with address: " + address + ", name: " + name); // 함수 호출 로그
            //카카오맵 API 로드 실패 알리는 로그.
        if (typeof kakao !== 'undefined' && kakao.maps) {
            console.log('카카오 지도 API 로드 성공!');
        } else {
            console.log('카카오 지도 API 로드 실패...');
        }


        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 임시 지도의 중심좌표
                level: 6 // 지도의 확대 레벨
            };
        console.log(address);

        // 지도 생성
        var map = new kakao.maps.Map(mapContainer, mapOption);
        console.log("map created!!")

        // 주소-좌표 변환 객체 생성
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표 검색(addressSearch). data-address의 address
        geocoder.addressSearch(address, function (result, status) {
            // if정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우로 장소 설명 표시
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">' + name + '</div>' // 인포윈도우에 표시될 내용
                });
                infowindow.open(map, marker);

                // 지도의 중심을 결과값으로 받은 위치로 이동
                map.setCenter(coords);
            }else{
                console.log("주소 검색 실패 유효하지 않은 주소")
            }
        });
    }
});




//수정 및 삭제 스크립트

$(document).ready(function() {


    // 삭제 버튼 클릭 이벤트
    $("#remove-button").click(function() {
        var warehouseId = $("#modal-warehouse-id").val(); // 창고 ID 가져오기
        // AJAX 요청으로 삭제 메소드 호출
        $.ajax({
            url: "/ssglanders/removeWarehouse", // 컨트롤러의 삭제 메소드 URL
            type: "POST",
            data: {
                wid: warehouseId,
            },
            success: function(response) {
                // 성공적으로 삭제되면 페이지를 새로고침하거나 사용자에게 알림
                alert('창고가 삭제되었습니다.');
                location.reload(); // 또는 다른 로직 구현
            },
            error: function(error) {
                // 오류 처리
                console.error('삭제 실패:', error);
                alert('창고 삭제에 실패했습니다.');
            }
        });
    });


    //모달창의 첫번째 수정버튼 !봉인!

    // $('#modify-button').click(function() {
    //     // 입력 필드의 readonly 속성을 제거하여 수정 가능하게 만듦
    //     $('#exampleModal2 input:not(#modal-warehouse-id)').removeAttr('readonly');
    //
    //     // 수정 완료 버튼을 동적으로 생성하고 추가함
    //     var completeButton = $('<button type="button" id="complete-modify-button" class="btn btn-success">수정하기</button>');
    //     $('.modal-footer').prepend(completeButton);
    //
    //     // 기존 수정 버튼을 숨김
    //     $(this).hide();
    // });
    //
    // // 수정 완료 버튼의 클릭 이벤트 핸들러 추가
    // $(document).on('click', '#complete-modify-button', function() {
    //     var warehouseData = {
    //         wid: $('#modal-warehouse-id').val(),
    //         wname: $('#modal-warehouse-name').val(),
    //         address: $('#modal-warehouse-address').val(),
    //         warehousetype: $('#modal-warehouse-type').val(),
    //         capacity: $('#modal-warehouse-capacity').val(),
    //         usingcapacity: $('#modal-warehouse-usingcapacity').val(),
    //     };
    //
    //     // AJAX 요청으로 서버의 modify 컨트롤러 호출
    //     $.ajax({
    //         url: '/ssglanders/modify', // 서버의 URL. 필요에 따라 수정하세요.
    //         type: 'POST',
    //         contentType: 'application/json', // JSON 형식의 데이터 전송을 명시
    //         data: JSON.stringify(warehouseData), // JavaScript 객체를 JSON 문자열로 변환
    //         success: function(response) {
    //             // 성공적으로 수정되면 페이지를 새로고침하거나 사용자에게 알림
    //             alert('창고 수정 성공');
    //             location.reload(); // 또는 다른 로직 구현
    //         },
    //         error: function(error) {
    //             // 오류 처리
    //             console.error('수정 실패:', error);
    //             alert('창고 수정 실패');
    //         }
    //     });
    // });



});

document.addEventListener('DOMContentLoaded', function() {
    const params = new URLSearchParams(window.location.search);
    const success = params.get('success');
    if (success) {
        sessionStorage.setItem('warehouseRegistrationSuccess', '창고가 성공적으로 등록되었습니다.');
        // alert('창고가 등록되었습니다.');
        window.location.href = '/ssglanders/warehouse';
    }
    const successMessage = sessionStorage.getItem('warehouseRegistrationSuccess');
    if (successMessage) {
        alert(successMessage);
        // 메시지 표시 후 세션 스토리지에서 해당 메시지 제거
        sessionStorage.removeItem('warehouseRegistrationSuccess');
    }
    // window.location.href = '/ssglanders/warehouse';
});


//페이지네이션
$(document).ready(function() {
    $(".pagination").on("click", "a", function(e) {
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
    }
    else {
        return uri + separator + key + "=" + value;
    }
}









//상세보기모달관련
// document.addEventListener('DOMContentLoaded', function () {
//     // '상세보기' 버튼에 대한 이벤트 리스너 설정
//     const detailButtons = document.querySelectorAll('[data-wid]');
//     detailButtons.forEach(button => {
//         button.addEventListener('click', function () {
//             const warehouseId = this.getAttribute('data-wid');
//             // 상세 정보를 조회하는 AJAX 요청 구현 예시
//             fetch(`/ssglanders/read?wid=${warehouseId}`)
//                 .then(response => response.json())
//                 .then(data => {
//                     // 모달 창에 데이터 채우기
//                     // 예를 들어, data가 { wid: 'w001', wname: '서울 가전 창고 1', ... } 형태일 때
//                     document.querySelector('#exampleModal2 .modal-title').textContent = `${data.wname} 상세`;
//                     // 상세 정보를 모달에 표시하는 코드 작성
//                     // ...
//                 })
//                 .catch(error => console.error('Error:', error));
//         });
//     });
//     document.querySelector('#registerForm').addEventListener('submit', function (e) {
//         e.preventDefault(); // 폼 기본 제출 동작 방지
//
//         // FormData 객체를 사용해 폼 데이터 수집
//         const formData = new FormData(this);
//
//         // 창고 등록을 위한 AJAX 요청 구현
//         fetch('/ssglanders/register', {
//             method: 'POST',
//             body: formData
//         })
//             .then(response => {
//                 if (response.ok) {
//                     alert('창고가 성공적으로 등록되었습니다.');
//                     window.location.reload(); // 페이지 새로고침 또는 다른 방법으로 목록 갱신
//                 } else {
//                     alert('등록에 실패했습니다.');
//                 }
//             })
//             .catch(error => console.error('Error:', error));
//     });
// });

// $(document).ready(function() {
//     $('#registerButton').click(function() {
//         // AJAX 요청을 통해 서버의 register 메소드 호출
//         $.ajax({
//             url: '/ssglanders/register', // 서버의 URL. 필요에 따라 수정하세요.
//             type: 'POST',
//             data: {
//             zipp_code: $('#zipp_code').val(), // 우편번호 입력 필드의 값을 가져옵니다.
//                 warehousetype: $('#warehousetype').val(), // 창고 타입 선택 필드의 값을 가져옵니다.
//                 userAdd1: $('#UserAdd1').val(), // 주소 입력 필드 1의 값을 가져옵니다.
//                 userAdd2: $('#UserAdd2').val(), // 주소 입력 필드 2의 값을 가져옵니다.
//                 capacity: $('#capacity').val(), // 수용량 입력 필드의 값을 가져옵니다.
//                 wname: $('#wname').val() // 창고명 입력 필드의 값을 가져옵니다.
//         },
//             success: function(response) {
//                 // 등록 성공 시 처리 로직
//                 // 예: 모달 창 열기, 성공 메시지 표시 등
//                 console.log('등록 성공')
//                 $('#exampleModal1').modal('show'); // 모달 창 열기
//             },
//             error: function(error) {
//                 // 오류 처리 로직
//                 console.error('등록 실패:', error);
//             }
//         });
//     });
// });





