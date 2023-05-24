<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> 여행 후기 수정페이지 </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modifyReview.css">

        <!-- jQuery -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
            integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"
            integrity="sha512-KgffulL3mxrOsDicgQWA11O6q6oKeWcV00VxgfJw4TcM8XRQT8Df9EsrYxDf7tpVpfl3qcYD96BpyPvA4d1FDQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    </head>

    <body>
        <main class="wrap-container">
            <h1>여행후기 수정페이지 </h1>
            <hr>
            <section class="head">
                <div class="user-info">
                    <a href="#" class="profile-img">
                        <img src="${pageContext.request.contextPath}/resources/img/profile.jpg" alt="프로필사진" />
                    </a>
                    <a href="#" class="nickname">${ __REVIEW__.nickname }</a>
                    <p class="regi-date">
                        <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${ __REVIEW__.regiDate }" />
                    </p>
                </div>
            </section>
            
            <form action="/board/review/modify" method="post">
                <section class="title">
                    <input type="hidden" name="postNo" value="${ __REVIEW__.postNo }">
                    <input type="hidden" name="nickname" value="${ __REVIEW__.nickname }">
                    <p> <input type="text" class="modifytitle" name="title" value="${ __REVIEW__.title }"></p>
                </section>

                <hr>

                <section class="content">
                    <br>
                    <textarea class="modifycontent" name="content" cols="50"
                        rows="50">${ __REVIEW__.content }</textarea>
                </section>
            </form>
        </main>
        

        <div class="btn-menu">
            <button class="listBtn"> 목록 </button>
            <button type="submit" name="submit" class="submitBtn" > 수정완료 </button>
            <button class="removeBtn"> 삭제 </button>
        </div>

        <script>
            $(function () {
                $('.listBtn').click(function () {

                	location = "/board/review/list?currPage=${param.currPage}";
                }); //onclick

                $('.removeBtn').click(function () {

                    let formObj = $("form");

                    // 폼태그의 속성을 변경
                    formObj.attr("action","/board/review/remove");
                    formObj.attr("method","POST");
                    formObj.submit();
                    
                }); //onclick

            }); // .jq
        </script>
    </body>

    </html>