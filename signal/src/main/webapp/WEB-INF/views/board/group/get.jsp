<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>동행찾기 게시글 상세조회</title>
        
        <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
        <link rel="stylesheet" href="../../resources/css/style1.css">
        <link rel="stylesheet" href="../../resources/css/style2.css">
        <link rel="stylesheet" href="../../resources/css/bstyle1.css">
        <link rel="stylesheet" href="../../resources/css/bstyle2.css">
        <link rel="stylesheet" href="../../resources/css/bstyle3.css">
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
        
        <script>
        var currPage = "${param.currPage}";
        var postNo = "${__BOARD__.postNo}";
        
            $(function () {
                $('#listBtn').click(function () {
                    location = "/board/group/list?currPage=${param.currPage}";
                });
                $('#modifyBtn').click(function () {
                    location = "/board/group/modify?currPage=${param.currPage}&postno=${__BOARD__.postNo}";
                });
                <!--
                $('#applyBtn').click(function () {
                    location.href = "/board/group/get?currPage=" + currPage + "&postno=" + postNo;
                });
              
                -->
               
            });
        </script>
    </head>

    <body>
    	<%@include file="../../header.jsp" %>
    	
        <!-- 게시글 보기 -->
        <div class="board_wrap">
            <div class="board_title">
                <strong>동행찾기</strong>
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
                            <dd>13</dd>
                        </dl>
                    </div>
                    <div class="content" readonly>${__BOARD__.content}</div>
                </div>

                <!-- 댓글 목록 -->
              	<%@include file="../../boardComment2.jsp" %>
              	 

                <!-- bt : button -->
                <div class="bt_wrap">
                <c:if test="${not __BOARD__.nickName.equals(__AUTH__.nickName)}">
	                <form action="/user/mygroup/register" method="post" >
	                	<input type="hidden" name="ID" value="${__AUTH__.ID }" >
	                	<input type="hidden" name="postNo" value="${__BOARD__.postNo }" >
	                	<input type="hidden" name="currPage" value="${param.currPage }" >
						<button type="submit" id="applyBtn">신청</button> 	                
	                </form>
            	</c:if>
                	
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