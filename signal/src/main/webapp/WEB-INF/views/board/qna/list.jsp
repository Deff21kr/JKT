<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA List</title>

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
            $('#regBtn').click(function () {
                let currPage = "${pageMaker.cri.currPage}";
                location = "/board/qna/register?currPage="+currPage;
            });
            $('.pageNum').on('click', function(e) {
                let selectedPageNum = e.currentTarget.textContent;
                location = "/board/qna/list?currPage="+selectedPageNum;
            });
        });
       
    </script>
</head>

<body>
    <%@include file="../../header.jsp" %>
    
    <!-- QnA 글 목록 -->
    <div class="board_wrap">
        <div class="board_title">
            <strong>QnA</strong>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <!-- <input type="hidden" name="readcnt" value="${boardVO.readcnt}"> -->
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="nickname">작성자</div>
                    <div class="date">등록일</div>
                    <div class="readcnt">조회수</div>
                </div>
                <div>
                    <c:forEach var="boardVO" items="${__LIST__}">
                        <div class="num">${boardVO.postNo}</div>
                        <div class="title">
                        <a href="/board/qna/get?currPage=${pageMaker.cri.currPage}&postNo=${boardVO.postNo}" >${boardVO.title}</a></div>
                        <div class="nickname">${boardVO.nickName}</div>
                        <div class="date"><fmt:formatDate pattern="yyyy/MM/dd" value="${boardVO.regiDate}" /></div>
                        <div class="readcnt">${boardVO.readCnt}</div>
                    </c:forEach>
                </div>
            </div>
            
		         
            
            <div class="board_page">
                    <c:if test="${pageMaker.prev}">
                        <div class="Prev"><a href="/board/qna/list?currPage=${pageMaker.startPage - 1}">Prev</a></div>
                    </c:if>
                    <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <div class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
                    </c:forEach>
                    <c:if test="${pageMaker.next}">
                        <div class="Next"><a href="/board/qna/list?currPage=${pageMaker.endPage + 1}">Next</a></div>
                    </c:if>
            </div>
            <!-- bt : button -->
            <div class="bt_wrap"> 
                <button type="button" id="regBtn">등록</button>
            </div>
        </div>
    </div>
    
    <%@include file="../../footer.jsp" %>

</body>

</html>