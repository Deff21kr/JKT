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
      
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
   	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

    <script>
	    $(function () {
	        $('.pageNum').on('click', function(e) {
	            let selectedPageNum = e.currentTarget.textContent;
	            location = "/user/mygroup?currPage="+selectedPageNum;
	        });
	    });
       
    </script>

    </head>

    <body>

      <header>
        <jsp:include page="../header.jsp" />
      </header>


      <div class="board_wrap">
      <div class="board_title">
        <h2>
          <strong>동행 신청 현황</strong>
        </h2>
      </div>

       <div class="board_trans_condition">
       	<h3 style="font-size : 2rem; margin-left : 30px;">동행의 신청자를 보여줍니다.</h3>
       </div>
      <!--  <form action="search.do" method="get">

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
      </div> -->

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
            <div class="currmem">현재인원</div>
          </div>

          <div class="board_list_post">
              <div>
              
                <c:forEach var="group" items="${__GROUP__}">
                    <div class="num">${group.appNo}</div>
                    <div class="title">${group.groupName}</div>
                    <div class="writer">${group.nickName}</div>
                    <div class="area">${group.area}</div>
                    <div class="status">${group.recruitStatus}</div>
                    <c:if test="${group.outCome == '진행중'}">
	                     <div class="result">
	                      <form class="a" action="/user/mygroup" method="POST">
		                        <button class="accept-button" type="submit" name="outCome" value="수락">수락</button>
		                        <button class="reject-button" type="submit" name="outCome" value="거절">거절</button>
		                        <input type="hidden" name="appNo" value="${group.appNo}">
		                        <input type="hidden" name="groupNo" value="${group.groupNo}">
	                        </form>
	                     </div>
                    </c:if>
                    <c:if test="${group.outCome != '진행중' }">
                    <div class="result">${group.outCome}</div>
                    </c:if>
                    
                    <div class="member">${group.memberNum}</div>
                    <div class="currmem">${group.currentMember}</div>
                </c:forEach>

              </div>
            
          </div>

          <div class="board_page">
            <c:if test="${pageMaker.prev}">
                 <div class="Prev"><a href="/user/mygroup?currPage=${pageMaker.startPage - 1}">Prev</a></div>
            </c:if>
            <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <div class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
            </c:forEach>
            
            <c:if test="${pageMaker.next}">
                <div class="Next"><a href="/user/mygroup?currPage=${pageMaker.endPage + 1}">Next</a></div>
            </c:if>
           
          

          </div>
        </div>
      </div>
</div>
      <footer>
        <jsp:include page="../footer.jsp" />
      </footer>


    </body>

    </html>