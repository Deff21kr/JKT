<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Travel Signal - Main Page</title>
    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <!-- style -->
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/style2.css">
    <link rel="stylesheet" href="../../resources/css/mainpage.css">
   
    <script src="https://kit.fontawesome.com/1d07350235.js" crossorigin="anonymous"></script>

    <!-- script -->
    <script src="../../resources/js/mainpage.js" defer></script>

  </head>

  <body>
   
    <header>
      <jsp:include page="header.jsp" />
    </header>
    
    <!-- 동행 찾기 -->
    <div class="groupboard-menu">
      <strong><a href="/board/group/list" target="_self">동행인이 기다리고 있어요</a></strong>
      <button class="plusBtn" onclick="location.href='/board/group/list'">
        <i class="fa-solid fa-plus"></i> 더보기
      </button>
    </div>

    <div class="container">
      <i id="left" class="fa-solid fa-angle-left"></i>
      <div class="content">
          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="card">
              <div class="image">
                  <img src="../../resources/img/9.png" alt="">
              </div>

              <div class="recruit-status">
                  <button type="button" id="recruitBtn">모집중</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>
          
      </div>
      <i id="right" class="fa-solid fa-angle-right"></i>
    </div>

    <!-- main -------------------------------------------->
    <main>
      
      <section>
        <div class="place">
          <div class="place-menu">
            <div>
              <img src="img/travelsignal2.png" alt="여행시그널로고">
              <strong><a href="#" target="_self">시그널 광장</a></strong>
            </div>

            <button class="plusBtn">
              <i class="fas fa-plus"></i> 더보기</button>
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
          </div>
        </div>

        <div class="middle">
          <div class="middle-menu">
            <div>
              <img src="img/travelsignal2.png" alt="여행시그널로고">
              <strong><a href="#" target="_self">Q&A</a></strong>
            </div>

            <button class="plusBtn"><i class="fas fa-plus"></i> 더보기</button>
          </div>
          <div class="middle-content">
            <ul>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
              <li>
                <a href="#" target="_self">
                  <span>Q&A</span>
                  2박 3일 서울 추천코스 알려주세요!
                </a>
              </li>
            </ul>
          </div>
        </div>
      </section>

    </main>

    <!-- footer -->
    <jsp:include page="footer.jsp" />

  </body>

  </html>