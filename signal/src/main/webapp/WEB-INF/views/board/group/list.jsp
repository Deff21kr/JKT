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


                <!-- style -->
                <link rel="stylesheet" href="../../resources/css/style.css">
                <link rel="stylesheet" href="../../resources/css/style2.css">
                <link rel="stylesheet" href="../../resources/css/groupboard.css">
                <!-- fonts awesome -->
                <script src="https://kit.fontawesome.com/1b2c39c9c4.js" crossorigin="anonymous"></script>
                <!-- script -->
                <script defer src="js/script.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
            </head>

            <script>
                $(function () {
                    $('#regBtn').click(function () {
                        let currPage = "${pageMaker.cri.currPage}";
                        location = "/board/group/register?currPage=" + currPage;
                    });
                    $('.pageNum').on('click', function (e) {
                        let selectedPageNum = e.currentTarget.textContent;
                        location = "/board/group/list?currPage=" + selectedPageNum;
                    });
                });
            </script>


            <body>

                <header>
                    <%@include file="../../header.jsp" %>
                </header>
                <div class="place">
                    <div class="searchbox">

                        <div class="area">

                            <div class="area-select">
                                <span><strong>지역</strong></span>
                            </div>

                            <div>
                                <button>서울</button>
                                <button>경기</button>
                                <button>강원</button>
                                <button>충청</button>
                                <button>전라</button>
                                <button>경상</button>
                                <button>제주</button>
                            </div>
                        </div>

                        <div class="calendar">

                            <div class="calselect">
                                <span><strong>날짜 </strong></span>
                            </div>

                            <div>
                                <input class="caldate" type="date" data-placeholder="출발 날짜 선택" required
                                    aria-required="true">
                                <div>~</div>
                                <input class="caldate" type="date" data-placeholder="도착 날짜 선택" required
                                    aria-required="true">
                            </div>
                        </div>

                        <div class="groupmember">

                            <div class="groupselect">
                                <span><strong>동행 인원</strong></span>
                            </div>

                            <div>
                                <select name="group-mem">
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
                                <button>모집중</button>
                                <button>모집 완료</button>
                                <button>환승중</button>
                                <button>여행 완료</button>
                            </div>
                        </div>


                    </div>



                    <div class="place-main">

                        <c:forEach var="boardVO" items="${__LIST__}">
                            <div class="main">

                                <div class="main-pic">
                                    <img src="img/pic1.png" alt="건물사진">
                                </div>

                                <div class="main-title">

                                    <a href="#" target="_self">
                                        <div class="place-location"><strong>${boardVO.area}</strong></div>
                                        <div class="place-date">
                                            <fmt:formatDate pattern="yyyy/MM/dd" value="${boardVO.startDate}" />
                                        </div>
                                        <div class="place-state">${boardVO.recruitStatus}</div>
                                        <div class="title"><a
                                                href="/board/group/get?currPage=${pageMaker.cri.currPage}&postNo=${boardVO.postNo}">${boardVO.title}</a>
                                        </div>
                                    </a>
                                </div>

                                <div class="main-name">

                                    <div class="profile-img">
                                        <img src="img/pic2.png" alt="profile-image1">
                                    </div>

                                    <div class="user-name"><strong>부산고수</strong></div>
                                </div>

                            </div>
                        </c:forEach>


                        <div class="board_page">
                            <c:if test="${pageMaker.prev}">
                                <div class="Prev"><a
                                        href="/board/group/list?currPage=${pageMaker.startPage - 1}">Prev</a></div>
                            </c:if>
                            <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                                <div class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
                            </c:forEach>
                            <c:if test="${pageMaker.next}">
                                <div class="Next"><a href="/board/group/list?currPage=${pageMaker.endPage + 1}">Next</a>
                                </div>
                            </c:if>
                        </div>

                    </div>

                    <div class="bottom">
                        <div class="searching">

                        </div>

                        <div class="write">
                            <button type="button" id="regBtn"
                                onclick="location.href='/board/group/register';">글쓰기</button>
                        </div>

                    </div>

                </div>

                <div class="middle">
                    </main>

                    <!-- footer -->
                    <footer>
                        <hr>
                        <div class="sfoot">
                            <div class="afoot">
                                <div class="logo">
                                    <img src="img/travelsignal2.png" alt="여행시그널로고">
                                </div>
                                <div class="comp">
                                    <span>주식회사 여행시그널</span>
                                </div>
                                <div class="priv">
                                    <a href="#" id="priv" target="_self">개인정보 처리방침</a>
                                    <a href="#" id="prov" target="_self">이용약관</a>
                                </div>
                            </div>

                            <div class="bfoot">
                                <button class="cust" onclick="">
                                    고객센터
                                </button>
                                <p id="mail">이메일 문의 : signal@travel.com</p>
                            </div>
                        </div>
                    </footer>

            </body>

            </html>