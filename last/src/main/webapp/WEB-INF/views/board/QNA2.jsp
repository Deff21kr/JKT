<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>메인페이지</title>

  <!-- swiper style -->
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
      integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog=="
      crossorigin="anonymous"
    />
  <link
  rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"
  />
  <!-- style -->
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/style2.css">
  <link rel="stylesheet" href="css/mainpage.css">
  <link rel="stylesheet" href="css/QNA.css">
  <!-- script -->
  <script defer src="js/script.js"></script>
  <!-- fonts awesome -->
  <script src="https://kit.fontawesome.com/1486713bf1.js" crossorigin="anonymous"></script>
  <!-- swiper script -->
  <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
  
  
  



  
  
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
      <li><a href="#" target="_self" >동행 찾기</a> </li>
      <li><a href="#" target="_self" >QnA</a> </li>
      <li><a href="#" target="_self" >동행 환승</a> </li>
      <li><a href="#" target="_self" >플래너</a> </li>
      <li><a href="http://localhost:8008/final/loginpage.html" target="_self" >여행후기</a> </li>
      <li>
        <div>
          <input type="text">
          <i class="fab fa-searchengin search-icon"></i>
        </div>
      </li>
    </ul>
  </header>

  <hr class="header-bottom-line">

  
<div class="content-wrapper">
      <h1 class="center"><span>자주묻는질문</span></h1>
    </div>
    <br>
    <hr>
    <div class="faq-container">
      <div class="faq">
        <h3 class="faq-title">자주 묻는 질문 1</h3>

        <p class="faq-text">대답 11111222222222222222222222222222222222222222222222222222222211111111111111</p>

        <button class="faq-toggle">
          <i class="fas fa-chevron-down"></i>
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="faq">
        <h3 class="faq-title">자주 묻는 질문 2</h3>

        <p class="faq-text">대답 2</p>

        <button class="faq-toggle">
          <i class="fas fa-chevron-down"></i>
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="faq">
        <h3 class="faq-title">자주 묻는 질문 3</h3>

        <p class="faq-text">대답 3</p>

        <button class="faq-toggle">
          <i class="fas fa-chevron-down"></i>
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="faq">
        <h3 class="faq-title">자주 묻는 질문 4</h3>

        <p class="faq-text">대답 4</p>

        <button class="faq-toggle">
          <i class="fas fa-chevron-down"></i>
          <i class="fas fa-times"></i>
        </button>
      </div>
    </div>
	

     <form action="#" method="post" class="form">
      <input type="button" value="1">
    </form>

    <footer>
      <hr>
      <div>
        <div class="A-foot">
          <div class="logo">
            <img src="img/travelsignal2.png" alt="여행시그널로고">
          </div>
          <div>
            <div class="cs1"><a href="#"><strong>자주묻는게시판</strong></a></div>
            <div class="cs2"><a href="#"><strong>1:1문의게시판</strong></a></div>
          </div>
          <!-- <div class="comp">
            <span>주식회사 여행시그널</span>
          </div> -->
          <div class="priv">
            <a href="#" id="priv" target="_self">개인정보 처리방침</a>
            <a href="#" id="prov" target="_self">이용약관</a>
          </div>
        </div>
        </div>

    </footer>

    
   

  </body>
  </html>