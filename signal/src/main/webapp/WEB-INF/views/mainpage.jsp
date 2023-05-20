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
    <script src="../../resources/js/review.js" defer></script>
    

  </head>

  <body>
   
    <header>
      <jsp:include page="header.jsp" />
    </header>
    
    <!-- 동행 찾기 -->
    <div class="menu">
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

              <div class="detail">
                <div class="info">
                    <button type="button" id="area">전라</button>
                    <button type="button" id="day">2023/05/19~2023/05/27</button>
                </div>

                <div class="title">2박 3일 서울여행 동행 구함</div>
              
                <div class="profile">
                    <img src="../../resources/img/profile.jpg" alt="">
                    <span class="nickName">Hyeon</span>
                </div>
              </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="image">
                <img src="../../resources/img/9.png" alt="">
            </div>

            <div class="recruit-status">
                <button type="button" id="recruitBtn">모집중</button>
            </div>

            <div class="detail">
              <div class="info">
                  <button type="button" id="area">전라</button>
                  <button type="button" id="day">2023/05/19~2023/05/27</button>
              </div>

              <div class="title">2박 3일 서울여행 동행 구함</div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
            </div>
          </div>
          
      </div>
      <i id="right" class="fa-solid fa-angle-right"></i>
    </div>

    <!-- review 게시판 -->
    <div class="menu">
      <strong><a href="/board/review/list" target="_self">동행인과 추억을 쌓아보아요</a></strong>
      <button class="plusBtn" onclick="location.href='/board/review/list'">
        <i class="fa-solid fa-plus"></i> 더보기
      </button>
    </div>

    <div class="review-container">
      <i id="left" class="fa-solid fa-angle-left"></i>
      <div class="review-content">
          <div class="review-card">
              <div class="image">
                  <img src="../../resources/img/r2.jpg" alt="">
              </div>

              <div class="review-like">
                  <button type="button" id="likeBtn">
                      <i class="fa-solid fa-heart"></i>
                  </button>
              </div>

              <div class="title">
                  <button type="button" id="locationBtn">
                      <i class="fa-solid fa-location-dot"></i>서울
                  </button>
              </div>
            
              <div class="profile">
                  <img src="../../resources/img/profile.jpg" alt="">
                  <span class="nickName">Hyeon</span>
              </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>

          <div class="review-card">
            <div class="image">
                <img src="../../resources/img/r2.jpg" alt="">
            </div>

            <div class="review-like">
                <button type="button" id="likeBtn">
                    <i class="fa-solid fa-heart"></i>
                </button>
            </div>

            <div class="title">
                <button type="button" id="locationBtn">
                    <i class="fa-solid fa-location-dot"></i>서울
                </button>
            </div>
          
            <div class="profile">
                <img src="../../resources/img/profile.jpg" alt="">
                <span class="nickName">Hyeon</span>
            </div>
          </div>
      </div>
      <i id="right" class="fa-solid fa-angle-right"></i>
    </div>

    <!-- QnA 게시판 -->
    <div class="menu">
      <strong><a href="/board/qna/list" target="_self">동행인에게 물어보세요</a></strong>
      <button class="plusBtn" onclick="location.href='/board/qna/list'">
        <i class="fa-solid fa-plus"></i> 더보기
      </button>
    </div>

    <div class="middle">
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

    <!-- footer -->
    <jsp:include page="footer.jsp" />

  </body>

  </html>