document.addEventListener("DOMContentLoaded", function () {
    const navToggle = document.querySelector(".mobile-nav-toggle");
    const navDiv = document.querySelector(".nav-div");

    navToggle.addEventListener("click", function () {
        navDiv.classList.toggle("show"); // 'show' 클래스를 토글하여 메뉴 표시
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const navToggle = document.querySelector(".mobile-nav-toggle");
    const navDiv = document.querySelector(".nav-div");
    const navFlexColumn = navDiv.querySelector(".nav.flex-column");

    navToggle.addEventListener("click", function () {
        // navDiv의 표시 상태를 토글
        if (navDiv.style.display === 'none' || navDiv.style.display === '') {
            navDiv.style.display = 'flex';
            navFlexColumn.style.display = 'flex'; // 필요한 경우 .nav.flex-column의 display도 조절
        } else {
            navDiv.style.display = 'none';
            navFlexColumn.style.display = 'none'; // 필요한 경우
        }
    });
});

window.addEventListener("resize", function () {
    const navDiv = document.querySelector(".nav-div");
    const navFlexColumn = navDiv.querySelector(".nav.flex-column");
    if (window.innerWidth > 768) {
        navDiv.style.display = "flex"; // 또는 원하는 다른 display 값
        navDiv.classList.remove("show"); // 'show' 클래스 제거
        navFlexColumn.style.display = "flex";
    } else {
        if (navDiv.classList.contains("show")) {
            navDiv.style.display = "flex";
        } else {
            navDiv.style.display = "none";
        }
        //navFlexColumn.style.display = "none";
    }
});
