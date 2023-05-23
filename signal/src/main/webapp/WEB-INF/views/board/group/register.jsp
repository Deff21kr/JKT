<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>동행찾기 게시판 게시글 작성</title>

        <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
        <link rel="stylesheet" href="../../resources/css/style1.css">
        <link rel="stylesheet" href="../../resources/css/style2.css">
        <link rel="stylesheet" href="../../resources/css/bstyle1.css">
        <link rel="stylesheet" href="../../resources/css/bstyle2.css">
        <link rel="stylesheet" href="../../resources/css/bstyle3.css">
        <link rel="stylesheet" href="../../resources/css/groupboardRegister.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

        <script>
            $(function () {
                $('#listBtn').click(function () {
                    // location.href = "/board/group/list?currPage=<%=request.getParameter("currPage")%>";
                    // 이전 페이지로 이동
                    history.back();
                    // 이전 페이지로 이동한 후에 새로고침
                    window.location.replace(document.referrer);
                });
            });
        </script>
    </head>

    <body>
        <header>
            <%@include file="../../header.jsp" %>
        </header>


        <div class="board_wrap">
            <!-- <input type="hidden" name="nickname" value="${__BOARD__.nickname}"> -->
            <div class="board_title">
                <strong>동행찾기</strong>
            </div>


            <form action="/board/group/register" method="POST">
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
                            <select name="memberNum" required>
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


                <div class="board_write_wrap">
                    <div class="board_write">
                        <div class="title">
                            <dl>
                                <dt>제목</dt>
                                <dd><input type="text" name="title" placeholder="제목 입력" required></dd>
                                <dt>동행이름</dt>
                                <dd><input type="text" name="groupName" placeholder="동행이름" required></dd>
                            </dl>
                        </div>
                        <div class="info">
                            <dl>
                                <dt>작성자</dt>
                                <dd><input type="hidden" name="nickName" value="${__AUTH__.nickName }" required>
                                    ${__AUTH__.nickName }</dd>
                                <!-- <dd>${__BOARD__.nickName}</dd> -->
                            </dl>
                        </div>
                        <div class="content">
                            <textarea placeholder="내용을 작성해주세요." name="content" required></textarea>
                        </div>
                    </div>

                    <!-- bt : button -->
                    <div class="bt_wrap">
                        <button type="submit" id="submitBtn">등록</button>
                        <button type="button" id="listBtn">취소</button>
                        <!-- <a href="view" class="on">등록</a>
                    <a href="list">취소</a> -->
                    </div>
                </div>
            </form>
        </div>

        <%@include file="../../footer.jsp" %>

    </body>

    </html>