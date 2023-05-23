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
            $(function () {
                $('#listBtn').click(function () {
                    // location = "/board/group/list?currPage=${param.currPage}";
                    // 이전 페이지로 이동
                    history.go(-1);
                    // 이전 페이지로 이동한 후에 새로고침
                    window.location.replace(document.referrer);
                });
                $('#modifyBtn').click(function () {
                    location = "/board/group/modify?currPage=${param.currPage}&postNo=${__BOARD__.postNo}";
                });
              
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
                <div class="reply_wrap">
                    <div class="reply_title">댓글</div>
                        <div class="reply_list">
                            <div>
                                <div class="nickname">서울이좋아</div>
                                <div class="date">(2023-04-18)</div>
                                <div class="reply_bt_wrap" id="Clickbt">
                                    <button type="submit" onclick="location.href='#';">신고</button>
                                </div>
                                <div class="content">남산타워 가보셨나요?</div>
                            </div>
                            <div>
                                <div class="nickname">서잘알</div>
                                <div class="date">(2023-04-18)</div>
                                <div class="reply_bt_wrap" id="Clickbt">
                                    <!-- <a href="#" class="modify">수정</a> -->
                                    <!-- <a href="#">삭제</a> -->
                                    <button type="submit" onclick="location.href='#';">신고</button>
                                </div>
                                <div class="content">홍대가보세요</div>
                            </div>
                            <div>
                                <div class="nickname">hyeonna</div>
                                <div class="date">(2023-04-18)</div>
                                <div class="reply_bt_wrap" id="Clickbt">
                                    <button type="submit" onclick="location.href='replyEdit';">수정</button>
                                    <button type="submit" onclick="location.href='#';">삭제</button>
                                    <!-- <a href="replyEdit" class="modify">수정</a> -->
                                    <!-- <a href="#">삭제</a> -->
                                    <!-- <a href="#">신고</a> -->
                                </div>
                                <div class="content">댓글 달아주셔서 감사합니다!</div>
                            </div>
                        </div>

                        <!-- 댓글 작성 -->
                        <div class="reply_write">
                            <div>
                                <div class="nickname">hyeonna</div>
                                <div class="content">
                                <textarea placeholder="내용을 작성해주세요."></textarea>
                                </div>
                                <div class="writeButton">
                                    <button type="submit" onclick="location.href='#';">등록</button>
                                </div>
                            </div>
                        </div>
                </div>

                <!-- bt : button -->
                <div class="bt_wrap">
                <c:if test="${not __BOARD__.nickName.equals(__AUTH__.nickName)}">
	               <button type="button" id="applyBtn">신청</button> 
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