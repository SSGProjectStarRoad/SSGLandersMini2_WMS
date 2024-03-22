document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById('warehouseChart').getContext('2d');
    const warehouseChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['서울', '평택', '부산', '대구', '광주'], // 창고 이름
            datasets: [{
                label: '총 용량',
                data: [100, 120, 150, 130, 160], // 각 창고의 총 용량
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }, {
                label: '현재 보관 중인 용량',
                data: [45, 60, 80, 70, 100], // 각 창고에서 현재 사용 중인 용량
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
    const ctx = document.getElementById('inOutChart').getContext('2d');
    const inOutChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            datasets: [{
                label: '입고',
                data: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120], // 임의의 입고 데이터
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }, {
                label: '출고',
                data: [120, 110, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10], // 임의의 출고 데이터
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
