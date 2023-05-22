<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <title>Header</title>
         <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/favicon.ico" type="image/x-icon">
   		 <link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico" type="image/x-icon">
        
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/mainpage.css">
       <!-- fonts awesome -->
    <script src="https://kit.fontawesome.com/1486713bf1.js" crossorigin="anonymous"></script>
    <!-- swiper script -->
    <!-- script
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <script defer src="${pageContext.request.contextPath }/js/script.js"></script>  -->
    </head>
    
    <body>
         <header> 

      <div class="signup-container">
        <%-- 세션에서 로그인 정보를 가져옴 --%>
          <c:set var="login" value="${sessionScope.__AUTH__}" />

          <%-- 로그인 상태에 따라 버튼 보이기/숨기기 --%>
            <c:if test="${empty login}">
              <div class="signup-container">
                <div><a href="/common/loginPost">로그인</a></div>
                <div><a href="/common/register">회원가입</a></div>
              </div>
            </c:if>
            <c:if test="${not empty login}">
              <div class="signup-container">
                <div><a href="/user/mypage">'${login.ID}'</a> 안녕</div>
                <div><a href="/common/logout">로그아웃</a></div>
              </div>
            </c:if>
      </div>

      <ul class="category">
        <li><a href="/" target="_self"><img src="${pageContext.request.contextPath }/resources/img/travelsignal2.png" alt="여행시그널로고"></a> </li>
        <li><a href="/board/group/list" target="_self">동행 찾기</a> </li>
        <li><a href="/board/qna/list" target="_self">QnA</a> </li>
        <li><a href="/user/mygroup" target="_self">동행현황</a> </li>
        <li><a href="/board/myplan/main" target="_self">플래너</a> </li>
        <li><a href="/board/review/list" target="_self">여행후기</a> </li>
        <li>
          <div>
            <input type="text">
            <i class="fab fa-searchengin search-icon"></i>
          </div>
        </li>
      </ul>
       <hr class="header-bottom-line">
    </header>
        
    </body>

    </html>