document.querySelector(".pagination").addEventListener("click", function (e) {
    e.preventDefault()
    e.stopPropagation()
    const target = e.target
    if(target.tagName !== 'A') {
        return
    }
    const num = target.getAttribute("data-num")
    self.location = `/ssglanders/stock?page=\${num}` //백틱(` `)을이용해서템플릿처리
},false)

function submitselect() {
    document.getElementById("pageSizeSelect").form.submit();
}
document.addEventListener('DOMContentLoaded', function() {
    $('#outmodal60').modal('show'); // 모달을 보이도록 트리거하는 부분
});

function fillCells() {
    var quantity = parseInt(document.getElementById("storage2").value);
    var tableCells = document.querySelectorAll("#myTable td");
    var totalquantity

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
    storageLocationElement.textContent = `${selectedOption1}`;
}

function updateDestination() {
    // const fillBar = document.querySelector('.capacity-bar .bar .fill');
    var sourceSelect = document.getElementById("storage1");
    var selectedValue = sourceSelect.options[sourceSelect.selectedIndex].value;

    var destinationSelect = document.getElementById("storage2");
    var selectedOption = sourceSelect.options[sourceSelect.selectedIndex].text;
    // const capacity = (selectedValue / 20) * 100; // 수용량 값 (0-100)
    // fillBar.style.width = `${capacity}%`;
    // Remove all existing options
    destinationSelect.innerHTML = '';

    // Create new option and append it
    var newOption = document.createElement("option");
    newOption.text = selectedValue;
    newOption.value = selectedValue;
    destinationSelect.add(newOption);
}

function updatefill(){
    const fillBar = document.querySelector('.capacity-bar .bar .fill');
    var capacitySelect = document.getElementById("storage1");
    var capacityvalue = capacitySelect.options[capacitySelect.selectedIndex].value;
    const capacity = (capacityvalue / 40) * 100; // 수용량 값 (0-100)
    console.log(capacitySelect);
    console.log(capacityvalue);
    console.log(capacity);
    fillBar.style.width = `${capacity}%`;
}
function hiddenlist(){
    // let liststatus = document.getElementById("myTable").style.display;
    if(document.getElementById("myTable").style.display === "none"){
        document.getElementById("myTable").style.display = "block";
    }else document.getElementById("myTable").style.display = "none";
}

