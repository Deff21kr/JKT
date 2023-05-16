<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QnA 게시물 수정</title>
        
        <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle2.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle3.css">
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
        
        <script>
            $(function () {
                $('#listBtn').click(function () {
                    location = "/board/qna/list?currPage=${param.currPage}";
                });
                $('#removeBtn').click(function () {
                    let formObj = $("form");
                    formObj.attr("action", "/board/qna/remove");
                    formObj.attr("method", "POST");
                    formObj.submit();
                });
            });
        </script>
    </head>
    <body>
    	<%@include file="../../header.jsp" %>
        
        <!-- QnA 글쓰기 수정 -->
        <div class="board_wrap">
            <form action="/board/qna/modify" method="POST">
                <input type="hidden" name="currPage" value="${param.currPage}">
                <input type="hidden" name="postNo" value="${__BOARD__.postNo}">
                <input type="hidden" name="nickName" value="${__AUTH__.nickName}">
                <div class="board_title">
                    <strong>QnA</strong>
                </div>
                <div class="board_write_wrap">
                    <div class="board_write">
                        <div class="title">
                            <dl>
                                <dt>제목</dt>
                                <dd><input input type="text" name="title" value="${__BOARD__.title}"></dd>
                            </dl>
                        </div>
                        <div class="info">
                            <dl>
                                <dt>작성자</dt>
                                <dd>${__AUTH__.nickName}</dd>
                            </dl>
                        </div>
                        <div class="content">
                            <textarea cols="60" rows="10" name="content">${__BOARD__.content}</textarea>
                        </div>
                    </div>
                    
                    <!-- bt : button -->
                    <div class="bt_wrap"> 
                        <button type="submit" id="submitBtn">수정</button>
                        <button type="button" id="removeBtn">삭제</button>
                        <button type="button" id="listBtn">취소</button>  
                    </div>
                </div>
            </form>
        </div>
        
        <%@include file="../../footer.jsp" %>

    </body>
    
    </html>