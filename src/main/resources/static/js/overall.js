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
                borderWidth: 1
            }, {
                label: '현재 보관 중인 용량',
                data: totalUsingCapacities, // 각 창고에서 현재 사용 중인 용량
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
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
                borderWidth: 1
            }, {
                label: '출고',
                data: outcoming,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});
