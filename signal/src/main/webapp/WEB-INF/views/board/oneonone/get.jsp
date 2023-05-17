<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>1:1 고객게시판 상세조회</title>
        
        <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
        <link rel="stylesheet" href="/resources/css/style1.css">
        <link rel="stylesheet" href="/resources/css/style2.css">
        <link rel="stylesheet" href="/resources/css/bstyle1.css">
        <link rel="stylesheet" href="/resources/css/bstyle2.css">
        <link rel="stylesheet" href="/resources/css/bstyle3.css">
          
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
        
        <script>
            $(function () {
                $('#listBtn').click(function () {
                    location = "/board/oneonone/list?currPage=${param.currPage}";
                });
                $('#modifyBtn').click(function () {
                    location = "/board/oneonone/modify?currPage=${param.currPage}&postNo=${__1on1_BOARD__.postNo}";
                });
                $('#replyBtn').click(function () {
                    location = "/board/oneonone/reply?currPage=${param.currPage}&postNo=${__1on1_BOARD__.postNo}&repRoot=${__1on1_BOARD__.repRoot}&repStep=${__1on1_BOARD__.repStep}&repIndent=${__1on1_BOARD__.repIndent}";
                });
            });
            
        </script>
    </head>

    <body>
    	<%@include file="../../header.jsp" %>
    	
        <!-- 1대1 글 보기 -->
        <div class="board_wrap">
            <div class="board_title">
                <strong>1대1 문의게시판</strong>
            </div>
            <div class="board_view_wrap">
                <div class="board_view">
                    <div class="title">${ __1on1_BOARD__.title}</div>
                    <div class="info">
                        <dl>
                            <dt>번호</dt>
                            <dd>${__1on1_BOARD__.postNo}</dd>
                        </dl>
                        <dl>
                            <dt>작성자</dt>
                            <dd>${__1on1_BOARD__.nickName}</dd>
                        </dl>
                        <dl>
                            <dt>등록일</dt>
                            <dd>${__1on1_BOARD__.regiDate}</dd>
                        </dl>
                        <dl>
                        	<c:if test="${not empty __1on1_BOARD__.modifyDate}">
	                        	<dt>수정일</dt>
	                            <dd>${__1on1_BOARD__.modifyDate}</dd>
                        	</c:if>
                            
                        </dl>

                    </div>
                    <div class="content" readonly>${__1on1_BOARD__.content}</div>
                </div>


                <!-- bt : button -->
                <div class="bt_wrap">
                    <button type="button" id="listBtn">목록</button> 
                    <button type="button" id="modifyBtn">수정</button>
                    <button type="button" id="replyBtn">답글</button>
                </div>
            </div>
        </div>
    
    	<%@include file="../../footer.jsp" %>
    	
    </body>
    
    </html>