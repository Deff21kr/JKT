<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QnA 게시글 상세조회</title>
        
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
                $('#modifyBtn').click(function () {
                    location = "/board/qna/modify?currPage=${param.currPage}&postNo=${__BOARD__.postNo}";
                });
            });
        </script>
    </head>

    <body>
    	<%@include file="../../header.jsp" %>
    	
        <!-- QnA 글 보기 -->
        <div class="board_wrap">
            <div class="board_title">
                <strong>QnA</strong>
            </div>
            <div class="board_view_wrap">
                <div class="board_view">
                    <div class="title">${ __BOARD__.title}</div>
                    <div class="info">
                        <dl>
                            <dt>번호</dt>
                            <dd>${__BOARD__.postNo}</dd>
                        </dl>
                        <dl>
                            <dt>작성자</dt>
                            <dd>${__BOARD__.nickName}</dd>
                        </dl>
                        <dl>
                            <dt>등록일</dt>
                            <dd>${__BOARD__.regiDate}</dd>
                        </dl> 
                        <dl>
                            <dt>조회수</dt>
                            <dd>${__BOARD__.readCnt}</dd>
                        </dl>
                    </div>
                    <div class="content" readonly>${__BOARD__.content}</div>
                </div>
				
				<!-- 댓글 -->
                <%@include file="../../boardComment.jsp" %>

                <!-- bt : button -->
                <div class="bt_wrap">
                    <button type="button" id="listBtn">목록</button> 
                    <c:if test="${__BOARD__.nickName.equals(__AUTH__.nickName)}">
                  <button type="button" id="modifyBtn">수정</button> 
                    </c:if>
                </div>
            </div>
        </div>
    
    	<%@include file="../../footer.jsp" %>
    	
    </body>
    
    </html>
