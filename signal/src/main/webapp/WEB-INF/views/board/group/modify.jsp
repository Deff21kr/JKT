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
        <link rel="stylesheet" href="../../resources/css/groupboard.css">
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
        
        <script>
            $(function () {
                $('#listBtn').click(function () {
                    location = "/board/group/list?currPage=${param.currPage}";
                });
                $('#removeBtn').click(function () {
                    let formObj = $("form");
                    formObj.attr("action", "/board/group/remove");
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
            <form action="/board/group/modify" method="POST">
                            <div class="searchbox">

                    <div class="area">

                        <div class="area-select">
                            <span><strong>지역</strong></span>
                        </div>

                        <div>
                            <label>
                                <input type="radio" name="area" value="서울">
                                서울
                            </label>
                            <label>
                                <input type="radio" name="area" value="경기">
                                경기
                            </label>
                            <label>
                                <input type="radio" name="area" value="충청">
                                충청
                            </label>
                            <label>
                                <input type="radio" name="area" value="전라">
                                전라
                            </label>
                            <label>
                                <input type="radio" name="area" value="경상">
                                경상
                            </label>
                            <label>
                                <input type="radio" name="area" value="제주">
                                제주
                            </label>
                        </div>
                    </div>

                    <div class="calendar">

                        <div class="calselect">
                            <span><strong>날짜 </strong></span>
                        </div>

                        <div>
                            <input class="caldate" type="date" name="startDate" data-placeholder="출발 날짜 선택" required
                                aria-required="true">
                            <div>~</div>
                            <input class="caldate" type="date" name="endDate" data-placeholder="도착 날짜 선택" required
                                aria-required="true">
                        </div>
                    </div>

                    <div class="groupmember">

                        <div class="groupselect">
                            <span><strong>동행 인원</strong></span>
                        </div>

                        <div>
                            <select name="memberNum">
                                <option value="1">제한 없음</option>
                                <option value="2">2명</option>
                                <option value="3">3명</option>
                                <option value="4">4명</option>
                                <option value="5">5명</option>
                                <option value="6">6명</option>
                                <option value="7">7명</option>
                                <option value="8">8명</option>
                                <option value="9">9명</option>
                                <option value="10">10명</option>
                            </select>
                        </div>

                    </div>

                    <div class="groupstatus">

                        <div class="groupstatus-select">
                            <span><strong>동행 현황</strong></span>
                        </div>

                        <div>
                            <label><input type="radio" name="recruitStatus" value="모집중">모집중</label>
							<label><input type="radio" name="recruitStatus" value="모집완료">모집 완료</label>
							<label><input type="radio" name="recruitStatus" value="환승중">환승중</label>
							<label><input type="radio" name="recruitStatus" value="여행완료">여행 완료</label>
                        </div>
                    </div>


                </div>
                
                <input type="hidden" name="currPage" value="${param.currPage}">
                <input type="hidden" name="postNo" value="${__BOARD__.postNo}">
                <input type="hidden" name="nickname" value="${__AUTH__.nickName}">
                <input type="hidden" name="postNo" value="${__BOARD__.postNo}">
                <input type="hidden" name="nickName" value="${__AUTH__.nickName}">
                <div class="board_title">
                    <strong>동행 찾기</strong>
                </div>
                <div class="board_write_wrap">
                    <div class="board_write">
                        <div class="title">
                            <dl>
                                <dt>제목</dt>
                                <dd><input input type="text" name="title" value="${__BOARD__.title}" required></dd>
                                <dt>동행이름</dt>
                                <dd><input type="text" name="groupName" value="${__BOARD__.groupName}" required></dd>
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