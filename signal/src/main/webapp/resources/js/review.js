const carousel2 = document.querySelector(".review-content");
const firstImg2 = carousel2.querySelectorAll(".review-card")[0];
const arrowIcons2 = document.querySelectorAll(".review-container i");

const showHideIcons2 = () => {
    let scrollWidth = carousel2.scrollWidth - carousel2.clientWidth;  // 최대 스크롤할 수 있는 너비
    if(carousel2.scrollLeft == 0) {
        arrowIcons2[0].style.display = "none";
    } else {
        arrowIcons2[0].style.display = "block";
    } // if-else
    // arrowIcons2[0].style.display = carousel2.scrollLeft == 0 ? "none" : "block";
    if(carousel2.scrollLeft == scrollWidth) {
        arrowIcons2[1].style.display = "none";
    } else {
        arrowIcons2[1].style.display = "block";
    } // if-else
}

arrowIcons2.forEach(icon => {
    icon.addEventListener("click", () => {
        let firstImg2Width = firstImg2.clientWidth + 25;
        console.log(firstImg2.clientWidth);
        if(icon.id == "left") {
            carousel2.scrollLeft -= firstImg2Width;
        } else {
            carousel2.scrollLeft += firstImg2Width;
        } // if-else
        // carousel2.scrollLeft += icon.id == "left" ? -firstImg2Width : firstImg2Width;
        showHideIcons2();
        setTimeout(() => showHideIcons2(), 60);  // 60초 뒤에 showHideIcons 실행
    });
});
