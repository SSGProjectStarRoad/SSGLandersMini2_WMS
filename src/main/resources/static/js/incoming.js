

// 모달에 해당 행의 정보를 전달하는 함수
// $('.open-modal-btn').click(function() {
//     var dto = $(this).data('dto');
//
//     // 모달에 정보를 채웁니다
//     $('#myModal .modal-title').text('입고 등록');
//     $('#firstcategory').val(dto.firstcategory);
//     $('#secondcategory').val(dto.secondcategory);
//     $('#thirdcategory').val(dto.thirdcategory);
//     $('#name').val(dto.name);
//
//     // 모달을 엽니다
//     $('#myModal').modal('show');
// });
$(document).ready(function() {
    $('.open-modal-btn').click(function() {
        var dto = $(this).data('dto');

        // 모달에 정보를 채웁니다
        $('#myModal .modal-title').text('입고 등록');
        $('#firstcategory').val(dto.firstcategory);
        $('#secondcategory').val(dto.secondcategory);
        $('#thirdcategory').val(dto.thirdcategory);
        $('#name').val(dto.name);

        // 모달을 엽니다
        $('#myModal').modal('show');
    });
});


// 데이터 제출 함수
function submitData() {
    var quantity = $('#quantity').val();
    var regdate = $('#regdate').val();
    var warehouseAddress = $('#input-warehouse-address').val();

    // 데이터를 서버로 전송하는 예시
    $.ajax({
        url: '/submit-data',
        method: 'POST',
        data: {
            quantity: quantity,
            regdate: regdate,
            warehouse_address: warehouseAddress
        },
        success: function(response) {
            // 성공 시 처리할 로직
        },
        error: function(xhr, status, error) {
            // 오류 발생 시 처리할 로직
        }
    });

    // 모달 닫기
    $('#myModal').modal('hide');
}






// 페이지 function
// 페이지네이션 클릭 이벤트 리스너
document.querySelector(".pagination").addEventListener("click", function (e) {
    e.preventDefault();
    e.stopPropagation();

    const target = e.target;

    if (target.tagName === 'A') {
        const num = target.getAttribute("data-num");
        const formObj = document.querySelector("form");

        // 페이지 번호를 폼에 추가하고 폼 제출
        formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`;
        formObj.submit();
    }
});


// 모달 창 열기

// 모달이 열릴 때, DTO 정보를 사용하여 모달 내용을 채웁니다.


// 등록 버튼 클릭 시 데이터를 제출하는 함수

//데이터 제출
// function submitData() {
//     var quantity = $('#quantity').val();
//     var regdate = $('#regdate').val();
//     var warehouseAddress = $('#input-warehouse-address').val();
//
//     // 수량, 입고일, 추가 입력 사항 데이터를 사용하여 추가 작업 수행
//     // 이후에 필요한 데이터를 서버로 전송하거나 추가 로직을 수행할 수 있음
//
//     // 데이터를 서버로 전송하는 예시
//     $.ajax({
//         url: '/submit-data',
//         method: 'POST',
//         data: {
//             quantity: quantity,
//             regdate: regdate,
//             warehouse_address: warehouse_address
//         },
//         success: function(response) {
//             // 성공 시 처리할 로직
//         },
//         error: function(xhr, status, error) {
//             // 오류 발생 시 처리할 로직
//         }
//     });
//
//     // 모달 닫기
//     $('#myModal').modal('hide');
// }

// 창고 조회

// $(document).ready(function() {
//     $('.select-warehouse').click(function(e) {
//         e.preventDefault(); // 기본 이벤트 동작 방지
//
//         var inputValue = $('#input-warehouse-address').val(); // 입력된 값 가져오기
//
//         // AJAX를 사용하여 서버에 입력된 값 전송
//         $.ajax({
//             url: '/submit-data',
//             type: 'POST',
//             data: { input: inputValue },
//             success: function(response) {
//                 // 서버로부터 반환된 결과를 처리
//                 var warehouses = response.warehouses;
//
//                 // 결과를 모달에 출력
//                 var modalBody = $('#myModal2 .modal-body');
//                 modalBody.empty(); // 이전 내용 삭제
//
//                 // 테이블 요소 생성
//                 var table = $('<table class="modal-table"></table>');
//                 var thead = $('<thead><tr><th>번호</th><th>주소</th><th>선택</th></tr></thead>');
//                 var tbody = $('<tbody></tbody>');
//
//                 // 결과 리스트를 모달에 추가
//                 warehouses.forEach(function(warehouse) {
//                     // 창고번호와 상품명을 표시하는 셀 생성
//                     var warehouseCell = $('<td data-label="창고번호">' + warehouse.wid + '</td>');
//                     var productNameCell = $('<td data-label="상품명">' + warehouse.address + '</td>');
//
//                     // 선택 버튼 생성
//                     var selectButton = $('<button type="submit" class="btn btn-primary select-modal-btn">선택</button>');
//                     selectButton.click(function() {
//                         $('#input-warehouse-address').val(warehouse.address); // 선택된 값을 입력란에 설정
//                         $('#myModal2').modal('hide'); // 모달 닫기
//                     });
//                     var selectCell = $('<td data-label="선택"></td>').append(selectButton);
//
//                     // 셀들을 하나의 행으로 구성
//                     var row = $('<tr></tr>').append(warehouseCell, productNameCell, selectCell);
//
//                     // 행을 모달에 추가
//                     modalBody.append(row);
//                 });
//
//                 // 모달 열기
//                 $('#myModal2').modal('show');
//             },
//             error: function(xhr, status, error) {
//                 console.error('Error:', error);
//             }
//         });
//     });
// });
//
//
//
// // Clear 버튼 클릭 이벤트 리스너
// document.querySelector(".clearBtn").addEventListener("click", function (e) {
//     e.preventDefault();
//     e.stopPropagation();
//
//     self.location = `/ssglanders/inRegister`;
// }, false);
