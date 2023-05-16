<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">

      <title>Travel Signal - Transfer Page</title>

      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/transfer.css">

    </head>

    <body>


      <header>
        <jsp:include page="../header.jsp" />
      </header>


      <div class="board_wrap"></div>
      <div class="board_title">
        <h2>
          <strong>동행</strong>
        </h2>
      </div>

      <div class="board_trans_condition">
        <form action="search.do" method="get">

          <div class="board_trans_condition_area">
            <div class="title">
              <strong>지역 </strong>
            </div>
            <button type="submit" name="area" value="Seoul">서울</button>
            <button type="submit" name="area" value="Gangwon">강원</button>
            <button type="submit" name="area" value="Gyeonggi">경기</button>
            <button type="submit" name="area" value="Chungcheong">충청</button>
            <button type="submit" name="area" value="Gyeongsang">경상</button>
            <button type="submit" name="area" value="Jeolla">전라</button>
            <button type="submit" name="area" value="Jeju">제주</button>
          </div>

          <div class="board_trans_condition_date">
            <div class="title">
              <strong>날짜 </strong>
            </div>
            <span>출발</span> <input type="date" name="startDate"> <span>종료</span>
            <input type="date" name="endDate">
          </div>



          <div class="board_trans_condition_person">
            <div class="title">
              <strong>인원</strong>
            </div>
            <select name="peopleCount">
              <option value="*">제한없음</option>
              <option value="3">2명</option>
              <option value="2">3명</option>
              <option value="3">4명</option>
              <option value="4">5명</option>
              <option value="5">6명</option>
              <option value="7">7명</option>
              <option value="8">8명</option>
              <option value="9">9명</option>
              <option value="10">10명</option>
            </select> <input type="submit" value="검색">
          </div>
        </form>
      </div>

      <div class="board_list_wrap">

        <div class="board_list">

          <div class="top">
            <div class="num">번호</div>
            <div class="title">동행이름</div>
            <div class="writer">신청자</div>
            <div class="area">지역</div>
            <div class="status">동행 상태</div>
            <div class="result">신청 결과</div>
            <div class="member">모집인원</div>
            <div class="current">현재인원</div>
          </div>

          <div class="board_list_post">
            <form class="a" action="/user/mygroup" method="POST">
              <div>
                <c:forEach var="group" items="${__GROUP__}">
                  <c:if test="${group.outCome == '진행중' || group.outCome == '수락'}">
                    <div class="num">${group.appNo}</div>
                    
                    <div class="title">${group.groupName}</div>
                    <div class="writer">${group.ID}</div>
                    <div class="area">${group.area}</div>
                    <div class="status">${group.recruitStatus}</div>
                    <c:if test="${group.outCome == '진행중'}">
                      <div class="result">
                        <button class="accept-button" type="submit" name="outCome" value="수락">수락</button>
                        <button class="reject-button" type="submit" name="outCome" value="거절">거절</button>
                        <input type="hidden" name="appNo" value="${group.appNo}">
                        <input type="hidden" name="groupNo" value="${group.groupNo}">
                      </div>
                    </c:if>
                    <c:if test="${group.outCome == '수락'}">
                      <div class="result">수락</div>
                    </c:if>
                    
                    <div class="member">${group.memberNum}</div>
                    <div class="current">${group.currentMember}</div>
                  </c:if>
                </c:forEach>


              </div>
            </form>
          </div>

          <div class="board_page">
            <a href="#" class="bt first">
              < < </a> <a href="#" class="bt prev">
                  < </a> <a href="#" class="num on">1</a> <a href="#" class="num">2</a> <a href="#" class="num">3</a> <a
                      href="#" class="num">4</a> <a href="#" class="num">5</a> <a href="#" class="bt next">></a> <a
                      href="#" class="bt last">> ></a>

          </div>
        </div>
      </div>

      <footer>
        <jsp:include page="../footer.jsp" />
      </footer>


    </body>

    </html>