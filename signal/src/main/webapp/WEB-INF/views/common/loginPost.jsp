<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <title>Travel Signal - Login Page</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/favicon.ico" type="image/x-icon">
  <link rel="icon" href="${pageContext.request.contextPath }/resources/favicon.ico" type="image/x-icon">

  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/loginpage.css">
  <!-- fonts awesome -->
  <script src="https://kit.fontawesome.com/1486713bf1.js" crossorigin="anonymous"></script>
  
  <script>
        // To display sign-in result.
        var result = "${ param.__RESULT__ }";

        if(result != null && result.length > 0) {
            alert(result);
        } // if
    </script>
    
</head>
<body>
	<header>
		<jsp:include page="../header.jsp" />
	</header>

    <main style = "margin : 270px auto">
      <form action="/common/loginPost" method="post">
        <fieldset>
          <legend><h3>로그인</h3></legend>
          <div class="user-area">
            <label for="userid">
              <i class="fas fa-user"></i>
            </label>
            <input id="userid" name="ID" type="text" autocomplete="none" placeholder="로그인">
          </div>
          <div class="user-area">
            <label for="userpw">
              <i class="fas fa-lock"></i>
            </label>
            <input id="userpw" name="password" type="password" placeholder="비밀번호">
          </div>
          <a href="#">비밀번호를 잊었습니까?</a>
          
          <div class="rememberme">Remember-me <input type="checkbox" name="rememberMe" class="remembermeBtn"></div>
                  <p></p>
  
                  <div class="sign"><button type="submit" class="signBtn">Sign-in</button></div>
        </fieldset>
      </form>
      
      <div class="message">
        ${msg}
    </div>
    
   
    
    </main>
 
  
  <footer>
	<jsp:include page="../footer.jsp" />
  </<footer>>
  
</body>
</html>