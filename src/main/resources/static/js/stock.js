// document.querySelector(".pagination").addEventListener("click", function (e) {
//     e.preventDefault()
//     e.stopPropagation()
//     const target = e.target
//     if(target.tagName !== 'A') {
//         return
//     }
//     const num = target.getAttribute("data-num")
//     self.location = `/ssglanders/stock?page=\${num}` //백틱(` `)을이용해서템플릿처리
// },false)

function submitselect() {
    document.getElementById("pageSizeSelect").form.submit();
}
document.addEventListener('DOMContentLoaded', function() {
    $('#outmodal60').modal('show'); // 모달을 보이도록 트리거하는 부분
});

function fillCells() {
    var quantity = parseInt(document.getElementById("storage2").value);
    var tableCells = document.querySelectorAll("#myTable td");

    if (quantity <= 40) {
        var startingIndex = 40 - quantity;
        tableCells.forEach(function(cell, index) {
            if (index >= startingIndex) {
                cell.style.backgroundColor = "rgb(210,161,131)";
            } else {
                cell.style.backgroundColor = "";
            }
        });
        document.getElementById("message").innerText = "";
    } else {
        document.getElementById("message").innerText = "용량 초과입니다.";
    }
}
function displayStorage() {
    const selectElement1 = document.getElementById("storage1");
    const selectedOption1 = selectElement1.options[selectElement1.selectedIndex].text;
    const storageLocationElement = document.getElementById("storagetitle");
    storageLocationElement.textContent = `${selectedOption1} 창고`;
}
function updateDestination() {
    var sourceSelect = document.getElementById("storage1");
    var selectedValue = sourceSelect.options[sourceSelect.selectedIndex].value;

    var destinationSelect = document.getElementById("storage2");
    var selectedOption = sourceSelect.options[sourceSelect.selectedIndex].text;

    // Remove all existing options
    destinationSelect.innerHTML = '';

    // Create new option and append it
    var newOption = document.createElement("option");
    newOption.text = selectedValue;
    newOption.value = selectedValue;
    destinationSelect.add(newOption);
}

