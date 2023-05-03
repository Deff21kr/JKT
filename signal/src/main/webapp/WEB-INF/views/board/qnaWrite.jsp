<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QnA 게시글 작성</title>
        
        <link rel="stylesheet" href="../../resources/css/css.css">
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
    
        <script>
            $(function () {
                $('#listBtn').click(function () {
                    location.href = "/board/qnaList?currPage=${param.currPage}";
                });
            });
        </script>
    </head>

    <body>
    	<%@include file="../header.jsp" %>
    	
        <div class="board_wrap">
            <form action="/board/qnaWrite" method="POST">
            <input type="hidden" name="nickname" value="${__BOARD__.nickname}">
                <div class="board_title">
                    <strong>QnA</strong>
                </div>
                <div class="board_write_wrap">
                    <div class="board_write">
                        <div class="title">
                            <dl>
                                <dt>제목</dt>
                                <dd><input type="text" value="${__BOARD__.title}" placeholder="제목 입력" required></dd>
                            </dl>
                        </div>
                        <div class="info">
                            <dl>
                                <dt>작성자</dt>
                                <!-- <dd><input type="text" placeholder="작성자 입력" required></dd> -->
                                <dd>${__BOARD__.nickname}</dd>
                            </dl>
                        </div>
                        <div class="content">
                            <textarea placeholder="내용을 작성해주세요." required>${__BOARD__.content}</textarea>
                        </div>
                    </div>
                    
                  <!-- bt : button -->
                  <div class="bt_wrap"> 
                    <button type="submit" id="submitBtn">등록</button> 
                    <button type="button" id="listBtn">취소</button> 
                    <!-- <a href="view" class="on">등록</a>
                    <a href="list">취소</a> -->
                  </div>
                </div>
            </form>
        </div>
        
        <%@include file="../footer.jsp" %>
        
    </body>

    </html>