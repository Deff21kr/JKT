<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>동행찾기게시판</title>

    <!-- swiper style -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
    <!-- style -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/style2.css">
    <link rel="stylesheet" href="css/mainpage.css">
    <!-- fonts awesome -->
    <script src="https://kit.fontawesome.com/1b2c39c9c4.js" crossorigin="anonymous"></script>
    <!-- swiper script -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <!-- script -->
    <script defer src="js/script.js"></script>
</head>


<body>

    <!-- header -->
    <header>

        <div class="signup-container">
            <div><a href="loginpage.html">로그인</a></div>
            <div><a href="#">회원가입</a></div>
        </div>

        <ul class="category">
            <li><a href="main.html" target="_self"><img src="img/travelsignal2.png" alt="여행시그널로고"></a> </li>
            <li><a href="#" target="_self">동행 찾기</a> </li>
            <li><a href="#" target="_self">QnA</a> </li>
            <li><a href="#" target="_self">동행 환승</a> </li>
            <li><a href="#" target="_self">플래너</a> </li>
            <li><a href="#" target="_self">여행후기</a> </li>
            <li>
                <div>
                    <input type="text">
                    <i class="fab fa-searchengin search-icon"></i>
                </div>
            </li>
        </ul>
    </header>

    <hr class="header-bottom-line">

    <main>
        <div class="place">
            <div class="place-menu">
                <div>
                    <!-- <img src="img/travelsignal2.png" alt="여행시그널로고"> -->
                    <strong><a href="#" target="_self">동행 찾기</a></strong>
                </div>
            </div>

            <div class="searchbox">

                <div class="area">

                    <div class="area-select">
                        <span><strong>지역</strong></span>
                    </div>

                    <div>
                        <button>서울</button>
                        <button>경기</button>
                        <button>강원</button>
                        <button>충청</button>
                        <button>전라</button>
                        <button>경상</button>
                        <button>제주</button>
                    </div>
                </div>

                <div class="calendar">

                    <div class="calselect">
                        <span><strong>날짜 </strong></span>
                    </div>

                    <div>
                        <input class="caldate" type="date" data-placeholder="출발 날짜 선택" required aria-required="true">
                        <div>~</div>
                        <input class="caldate" type="date" data-placeholder="도착 날짜 선택" required aria-required="true">
                    </div>
                </div>

                <div class="groupmember">

                    <div class="groupselect">
                        <span><strong>동행 인원</strong></span>
                    </div>

                    <div>
                        <select name="group-mem">
                            <option value="1">제한 없음</option>
                            <option value="2">2명</option>
                            <option value="3">3명</option>
                            <option value="4">4명</option>
                            <option value="5">5명</option>
                            <option value="6">6명</option>
                            <option value="7">7명</option>
                            <option value="8">8명</option>
                            <option value="9">9명</option>
                            <option value="10">10명</option>
                        </select>
                    </div>

                </div>

                <div class="groupstatus">

                    <div class="groupstatus-select">
                        <span><strong>동행 현황</strong></span>
                    </div>

                    <div>
                        <button>모집중</button>
                        <button>모집 완료</button>
                        <button>환승중</button>
                        <button>여행 완료</button>
                    </div>
                </div>


            </div>


            <div class="place-main">
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic1.png" alt="건물사진">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>부산고수</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic3.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>을용타</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic5.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>춘수리</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic2.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>훔바훔바</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic1.png" alt="건물사진">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>부산고수</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic3.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>을용타</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic5.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>춘수리</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic2.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>훔바훔바</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic1.png" alt="건물사진">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>부산고수</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic3.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>을용타</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic5.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>춘수리</strong></div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-pic">
                        <img src="img/pic2.png" alt="">
                    </div>
                    <div class="main-title">
                        <a href="#" target="_self">
                            <div class="place-location"><strong>경상</strong></div>
                            <div class="place-date">03.15 ~ 03.18</div>
                            <div class="place-state" data-state="1">동행진행중</div>
                        </a>
                        <p class="content">부산갈싸람?</p>
                    </div>
                    <div class="main-name">
                        <div class="profile-img">
                            <img src="img/pic2.png" alt="profile-image1">
                        </div>
                        <div class="user-name"><strong>훔바훔바</strong></div>
                    </div>
                </div>

            </div>

            <div class="bottom">
                <div class="searching">

                </div>

                <div class="write">
                    <button>글쓰기</button>
                </div>

            </div>

        </div>

        <div class="middle">
    </main>

    <!-- footer -->
    <footer>
        <hr>
        <div class="sfoot">
            <div class="afoot">
                <div class="logo">
                    <img src="img/travelsignal2.png" alt="여행시그널로고">
                </div>
                <div class="comp">
                    <span>주식회사 여행시그널</span>
                </div>
                <div class="priv">
                    <a href="#" id="priv" target="_self">개인정보 처리방침</a>
                    <a href="#" id="prov" target="_self">이용약관</a>
                </div>
            </div>

            <div class="bfoot">
                <button class="cust" onclick="">
                    고객센터
                </button>
                <p id="mail">이메일 문의 : signal@travel.com</p>
            </div>
        </div>
    </footer>

</body>

</html>