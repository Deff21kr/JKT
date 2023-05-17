<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

            // =========== 조회수 중복방지(진행중) ==================
            const postNo = '게시물 번호';
            let ip = '';
            const xhr = new XMLHttpRequest();
            // API 호출
            xhr.open('GET', 'https://api.ipify.org?format=json');
            xhr.onload = function() {
                const data = JSON.parse(xhr.responseText); // API 응답처리
                ip = data.ip // ip 주소를 변수에 저장

                // 로컬스토리지에서 해당 게시물의 조회수 정보 가져옴
                const readPosts = JSON.parse(localStorage.getItem('readPosts')) || {};
                let readPost = readPosts[postno] || {readCnt: 0, ips: {}};

                if(!readPost.ips[ip]) {
                readPost.readCnt++; //조회수 증가
                readPost.ips[ip] = true // 해당 IP 주소를 조회한 목록에 추가
                readPosts[postNo] = readPost; // 조회한 게시물 정보 저장
                localStorage.setItem('readPosts', JSON.stringify(readPosts)); // 로컬스토리지에 저장
            } // if

            }; // xhr
            xhr.send(); // API 호출
            
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
                <%@include file="../../QnAboardComment.jsp" %>

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
