<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="ko">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">

                <title>동행찾기게시판</title>


 
                <link rel="stylesheet" href="../../resources/css/style.css">
                <link rel="stylesheet" href="../../resources/css/style2.css">
                <link rel="stylesheet" href="../../resources/css/groupboard.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle1.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle2.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle3.css">

                <script src="https://kit.fontawesome.com/1b2c39c9c4.js" crossorigin="anonymous"></script>

                <script defer src="js/script.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
            </head>

            <script>
            $(function () {
            	  $('#regBtn').click(function () {
            	    let currPage = "${SearchPageMaker.cri.currPage}";
            	    location = "/board/group/register?currPage=" + currPage;
            	  });
            	  $('.pageNum').on('click', function (e) {
            		    let selectedPageNum = e.currentTarget.textContent;
            		    let url = "/board/group/searchList?currPage=" + selectedPageNum;

            		    // area 파라미터를 추가
            		    url += "&area=" + encodeURIComponent('${SearchPageMaker.cri.area}');

            		    // startDate 파라미터를 추가
            		    url += "&startDate=" + encodeURIComponent('${SearchPageMaker.cri.startDate}');

            		    // endDate 파라미터를 추가
            		    url += "&endDate=" + encodeURIComponent('${SearchPageMaker.cri.endDate}');

            		    // memberNum 파라미터를 추가
            		    url += "&memberNum=" + encodeURIComponent('${SearchPageMaker.cri.memberNum}');

            		    // recruitStatus 파라미터를 추가
            		    url += "&recruitStatus=" + encodeURIComponent('${SearchPageMaker.cri.recruitStatus}');

            		    location.href = url;
            		});

                  });

            </script>


            <body>

                <header>
                    <%@include file="../../header.jsp" %>
                </header>
                <div class="place">

                    <form action="/board/group/searchList">
                        <div class="searchbox">

                            <div class="area">

                                <div class="area-select">
                                    <span><strong>지역</strong></span>
                                </div>

                                <div>
		                        	<label>
									  	<input type="radio" name="area" value="" checked>
									  	전체
									</label>                                
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
                                    <input class="caldate" type="date" name="startDate" data-placeholder="출발 날짜 선택"
                                        aria-required="true">
                                    <div>~</div>
                                    <input class="caldate" type="date" name="endDate" data-placeholder="도착 날짜 선택"
                                        aria-required="true">
                                </div>
                            </div>

                            <div class="groupmember">

                                <div class="groupselect">
                                    <span><strong>동행 인원</strong></span>
                                </div>

                                <div>
                                    <select name="memberNum">
                                        <option value="">제한 없음</option>
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
                                	<label><input type="radio" name="recruitStatus" value="" checked>전체</label>   
                                    <label><input type="radio" name="recruitStatus" value="모집중">모집중</label>
                                    <label><input type="radio" name="recruitStatus" value="모집완료">모집 완료</label>
                                    <label><input type="radio" name="recruitStatus" value="환승중">환승중</label>
                                    <label><input type="radio" name="recruitStatus" value="여행완료">여행 완료</label>
                                </div>
                            </div>
                            
                            
                            <button type="submit" id="submitBtn">검색</button>


                        </div>
                    </form>



                    <div class="place-main">

                        <c:forEach var="groupBoardVO" items="${__SEARCH__}">
                            <div class="main">

                                <div class="main-pic">
                                    <img src="../../../resources/img/backgroundImgEx.png" alt="기본배경사진">
                                </div>

                                <div class="main-title">

                                    <a href="#" target="_self">

                                        <div class="place-location">
                                            <strong>${groupBoardVO.area}</strong>
                                        </div>

                                        <div class="place-date">
                                            <fmt:formatDate pattern="yyyy/MM/dd" value="${groupBoardVO.startDate}" /> ~
                                            <fmt:formatDate pattern="yyyy/MM/dd" value="${groupBoardVO.endDate}" />
                                        </div>

                                        <div class="place-state">${groupBoardVO.recruitStatus}</div>

                                        <div class="title">
                                            <a
                                                href="/board/group/get?currPage=${pageMaker.cri.currPage}&postNo=${groupBoardVO.postNo}">${groupBoardVO.title}</a>
                                        </div>

                                    </a>
                                </div>

                                <div class="main-name">

                                    <div class="profile-img">
                                        <img src="../../../resources/img/defaultProfileImg.png" alt="profile-image1">
                                    </div>

                                    <div class="user-name"><strong>${groupBoardVO.nickName}</strong></div>
                                </div>

                            </div>
                        </c:forEach>

                    </div>

                </div>

                <div class="board_page">
                    <c:if test="${SearchPageMaker.prev}">
                        <div class="Prev"><a href="/board/group/searchList?currPage=${SearchPageMaker.startPage - 1}">Prev</a>
                        </div>
                    </c:if>
                    <c:forEach var="pageNum" begin="${SearchPageMaker.startPage}" end="${SearchPageMaker.endPage}">
                        <div class="pageNum ${SearchPageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
                    </c:forEach>
                    <c:if test="${SearchPageMaker.next}">
                        <div class="Next"><a href="/board/group/searchList?currPage=${SearchPageMaker.endPage + 1}">Next</a>
                        </div>
                    </c:if>
                </div>


                <div class="bottom">
                    <div class="searching">

                    </div>


                    <div class="bt_wrap">
                        <button type="button" id="regBtn" onclick="location.href='/board/group/register';">글쓰기</button>
                    </div>

                </div>

                <footer>
                    <%@include file="../../footer.jsp" %>
                </footer>


            </body>





            </html>