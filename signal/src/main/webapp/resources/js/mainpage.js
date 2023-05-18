const carousel = document.querySelector(".content");
const firstImg = carousel.querySelectorAll(".card")[0];
const arrowIcons = document.querySelectorAll(".container i");

const showHideIcons = () => {
    let scrollWidth = carousel.scrollWidth - carousel.clientWidth;  // 최대 스크롤할 수 있는 너비
    if(carousel.scrollLeft == 0) {
        arrowIcons[0].style.display = "none";
    } else {
        arrowIcons[0].style.display = "block";
    } // if-else
    // arrowIcons[0].style.display = carousel.scrollLeft == 0 ? "none" : "block";
    if(carousel.scrollLeft == scrollWidth) {
        arrowIcons[1].style.display = "none";
    } else {
        arrowIcons[1].style.display = "block";
    } // if-else
}

arrowIcons.forEach(icon => {
    icon.addEventListener("click", () => {
        let firstImgWidth = firstImg.clientWidth + 25;
        console.log(firstImg.clientWidth);
        if(icon.id == "left") {
            carousel.scrollLeft -= firstImgWidth;
        } else {
            carousel.scrollLeft += firstImgWidth;
        } // if-else
        // carousel.scrollLeft += icon.id == "left" ? -firstImgWidth : firstImgWidth;
        showHideIcons();
        setTimeout(() => showHideIcons(), 60);  // 60초 뒤에 showHideIcons 실행
    });
});