document.querySelector(".pagination").addEventListener("click", function (e) {
    e.preventDefault()
    e.stopPropagation()
    const target = e.target
    if (target.tagName !== 'A') {
        return
    }
    const num = target.getAttribute("data-num")
    self.location = `/ssglanders/stock?page=\${num}` //백틱(` `)을이용해서템플릿처리
}, false);

function submitselect() {
    document.getElementById("pageSizeSelect").form.submit();
}
function submitwnameselect() {
    var selectElement = document.getElementById("storage1");
    var selectedIndex = selectElement.selectedIndex;
    var selectedOption = selectElement.options[selectedIndex];
    var wname = selectedOption.dataset.wname;
    console.log(wname);
    $.ajax({
        url: "/ssglanders/stock", // 컨트롤러의 삭제 메소드 URL
        type: "POST",
        contentType: 'application/json',
        data: JSON.stringify({wname: wname}),

        success: function (response) {
            $('#stocktable').html('');
            response.forEach(function (stockItem) {

                var name = stockItem.name;
                var quantity = stockItem.quantity;
                var newRow = '<tr><td>' +'상품명 : ' + name +' / '+ '수량 :' + quantity + '</td></tr>';
                $('#stocktable').append(newRow);
            });
        }
    });
}

function submitData() {
    var stockproduct = $('#modal-stock-name').val();
    var stockwarehouse = $('#modal-stock-wname').val();
    var stockquantity = $('#quantity').val();
 console.log(stockquantity);
 console.log(stockwarehouse);
 console.log(stockproduct);

    $.ajax({
        url: '/ssglanders/registerOutcoming',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: stockproduct,
            wname: stockwarehouse,
            quantity: stockquantity,
        }),
        success: function (response) {
            // 모달 창 닫기 및 값 초기화
            $('#outmodal').modal('hide');
            $('#quantity').val('');
            alert('요청 성공했습니다.');
        },
        error: function (error) {
            alert('요청에 실패했습니다.');
        }
    });

}
document.addEventListener('DOMContentLoaded', function () {
    $('#outmodal60').modal('show'); // 모달을 보이도록 트리거하는 부분
});

// function fillCells() {
//     var quantity = parseInt(document.getElementById("storage2").value);
//     var tableCells = document.querySelectorAll("#myTable td");
//     var totalquantity
//
//     if (quantity <= 40) {
//         var startingIndex = 40 - quantity;
//         tableCells.forEach(function(cell, index) {
//             if (index >= startingIndex) {
//                 cell.style.backgroundColor = "rgb(210,161,131)";
//             } else {
//                 cell.style.backgroundColor = "";
//             }
//         });
//         document.getElementById("message").innerText = "";
//     } else {
//         document.getElementById("message").innerText = "용량 초과입니다.";
//     }
// }
function displayStorage() {
    const selectElement1 = document.getElementById("storage1");
    const selectedOption1 = selectElement1.options[selectElement1.selectedIndex].text;
    const storageLocationElement = document.getElementById("storagetitle");
    storageLocationElement.textContent = `${selectedOption1}`;
}

function updateDestination() {
    var sourceSelect = document.getElementById("storage1");
    var selectedValue = sourceSelect.options[sourceSelect.selectedIndex].value;

    var destinationSelect = document.getElementById("storage2");
    var selectedOption = sourceSelect.options[sourceSelect.selectedIndex].text;
    destinationSelect.innerHTML = '';

    // Create new option and append it
    var newOption = document.createElement("option");
    newOption.text = selectedValue;
    newOption.value = selectedValue;
    destinationSelect.add(newOption);
}

function updatefill() {
    const fillBar = document.querySelector('.capacity-bar .bar .fill');
    var capacitySelect = document.getElementById("storage1");
    var capacityvalue = capacitySelect.options[capacitySelect.selectedIndex].value;
    const capacity = (capacityvalue / 40) * 100; // 수용량 값 (0-100)
    console.log(capacitySelect);
    console.log(capacityvalue);
    console.log(capacity);
    fillBar.style.width = `${capacity}%`;
}

function updatecapacity() {
    var capacitySelect = document.getElementById("storage1");
    var total = document.getElementById("totalcapacity");
    var capacityval = capacitySelect.options[capacitySelect.selectedIndex];
    console.log(capacityval);
    if (capacityval) {
        var usingCapacity = capacityval.getAttribute("data-usingcapacity");
        var capacity = capacityval.getAttribute("data-capacity");
        total.textContent = '총 수용량 ' + usingCapacity + ' / ' + capacity;
    }
}

function hiddenlist() {
    // let liststatus = document.getElementById("myTable").style.display;
    if (document.getElementById("myTable").style.display === "none") {
        document.getElementById("myTable").style.display = "block";
    } else document.getElementById("myTable").style.display = "none";
}

// document.addEventListener('DOMContentLoaded', function () {

//     exampleModal.addEventListener('shown.bs.modal', function (event) {
//                 var exampleModal = document.getElementById('outmodal');
//         // 버튼에서 모달로 정보 전달
//         var button = event.relatedTarget;
//         var dtoname = button.getAttribute('data-dtoname');
//         var dtowname = button.getAttribute('data-dtowname');
//         // 모달에 정보 설정
//         var modalTitle = exampleModal.querySelector('.modal-title');
//         var modalBodyName = document.getElementById('modal-warehouse-name');
//         var modalBodyWname = document.getElementById('modal-warehouse-wname');
//
//         modalTitle.textContent = '창고 상세 - ' + dtoname;
//         modalBodyId.value = id;
//         modalBodyName.value = dtoname;
//         modalBodyWname.value = dtowname;
//         console.log(dtowname);
//         console.log(dtoname);
//     });
// });
function inputdata(index) {
    var selectElement1 = document.getElementById("test");
    var selectElement2 = document.getElementById("test");
    var dtoname = selectElement1.dataset.dtoname;
    var dtowname = selectElement2.dataset.dtowname;
    console.log(index);
    console.log(dtoname);
    console.log(dtowname);
    var modalBodyName = document.getElementById('modal-stock-name');
    var modalBodyWname = document.getElementById('modal-stock-wname');
    modalBodyName.value = dtoname;
    modalBodyWname.value = dtowname;
}