$(document).ready(function() {
    $.ajax({
        url: '/ssglanders/api/overall',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log(response.getIncomingArriveCount);
            $(".incomingcount").text(response.incomingcount);
            $(".outcomingcount").text(response.outcomingcount);
            $(".getNotApprovalCount").text(response.getNotApprovalCount);
            $(".getIncomingBeforeCount").text(response.getIncomingBeforeCount);
            $(".getIncomingArriveCount").text(response.getIncomingArriveCount);
            $(".getOutcomingBeforeCount").text(response.getOutcomingBeforeCount);
            $(".getOutcomingArriveCount").text(response.getOutcomingArriveCount);
            $(".getTotalUsingCapacity").text(response.getTotalUsingCapacity);

        },
        error: function (error) {
            console.log("Error:", error);
        }
    });
});


document.addEventListener("DOMContentLoaded", function () {

    const labels = cities.map(item => item.city);
    const totalCapacities = cities.map(item => item.totalcapacity);
    const totalUsingCapacities = cities.map(item => item.totalusingcapacity);

    const ctx = document.getElementById('warehouseChart').getContext('2d');
    const warehouseChart = new Chart(ctx, {
        type: 'bar',
        data: {

            labels: labels, // 창고 이름
            datasets: [{
                label: '총 용량',
                data: totalCapacities, // 각 창고의 총 용량
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 2
            }, {
                label: '현재 보관 중인 용량',

                data: totalUsingCapacities, // 각 창고에서 현재 사용 중인 용량
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 2
            }]
        },
        options: {
            indexAxis: 'x',
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {

    const incoming = monthly.incoming;
    const outcoming = monthly.outcoming;
    const ctx = document.getElementById('inOutChart').getContext('2d');
    const inOutChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            datasets: [{
                label: '입고',

                data: incoming,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 2
            }, {
                label: '출고',

                data: outcoming,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 2
            }]
        },
        options: {
            indexAxis: 'x',
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

});


document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById('myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['데이터 1', '데이터 2', '데이터 3', '데이터 4', '데이터 5'],
            datasets: [{
                label: '데이터 집합 1',
                data: [10, 20, 30, 40, 50],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(255, 205, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(54, 162, 235, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
        }
    });
});

<!-- 파이차트 끝 -->