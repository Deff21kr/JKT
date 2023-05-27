<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>마이페이지</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/mypageStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle2.css">

				<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
				<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
				<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
				<script>
				  let tabId = ''; // 탭 ID 변수를 선언합니다.

				$(document).ready(function() {

					  $('ul li a').on('click', function(e) {
					    e.preventDefault();
					    tabId = $(this).attr('href'); // 탭 ID 값을 업데이트합니다.
					    let url = window.location.href.split('#')[0]; // 현재 URL에서 # 이전 부분만 가져옵니다.
					    console.log("ID: " + tabId);
					    console.log("URL: " + url);
					    window.location.href = url + tabId; // 현재 URL과 탭의 ID를 합쳐서 새로운 URL로 이동합니다.
					  });

					  $('.pageNum').on('click', function(e) {
						tabId =  window.location.hash;
					    e.preventDefault();
					    let selectedPageNum = e.currentTarget.textContent;
					    console.log("ID2: " + tabId);
					    window.location.href ="/user/mypage?currPage=" + selectedPageNum + tabId; // 현재 URL과 탭 ID, 페이지 번호를 합쳐서 새로운 URL로 이동합니다.
					  });
					});

				
				
				$(function () {
					$("#container").tabs();
					heightStyle: "content"
				});

				$(function() {
					$("#modifyBtn").click(function() {
						location = "/user/edit"
						console.log("클리이이이이이익");
					});
				});
				$(function() {
					$('.pageNum').on('click', function(e) {
						let selectedPageNum = e.currentTarget.textContent;
						location = "/user/mypage?#currPage=" + selectedPageNum + "tabs-2";
					});
				});

				$(function() {
					$('.myGroup').on('click', function(e) {
						var content = $(this).next('.content');
						if (content.css('display') === 'none') {
							content.css('display', 'block');
						} else {
							content.css('display', 'none');
						}
					});
				});
				</script>


<style>
.hide {
	display: none;
	
}

#tabs-3 .board_list .post .content div {
	width: 15.5%;
	display: inline-block;
	font-size: 2rem;
	text-align: center;
	padding-top: 15px;
}

#tabs-3 .board_list .post .content .group {
	width: 20%;
	display: inline-block;
	font-size: 2rem;
	text-align: center;
	padding-top: 15px;
}
</style>

</head>

<body>
	<header>
		<jsp:include page="../header.jsp" />
	</header>

	<div id="wrap" class="wrap">
		<div class="wrap_title">
			<h2>
				<strong>마이페이지</strong>
			</h2>
		</div>

		<div id="container">

			<ul>
				<li><a href="#tabs-1">
						<div class="c">프로필</div>
				</a></li>
				<li><a href="#tabs-2">
						<div class="c">작성글</div>
				</a></li>
				<li><a href="#tabs-3">
						<div class="c">마이동행</div>
				</a></li>
				<li><a href="#tabs-4">
						<div class="c">찜한 글</div>
				</a></li>
				<li><a href="#tabs-5">
						<div class="c">좋아요한 글</div>
				</a></li>
				<li><a href="#tabs-6">
						<div class="c">개인 정보 수정</div>
				</a></li>
			</ul>

			<!-- 프로필-->
			<div id="tabs-1">


				<div class="myprofile">

					<div class="profile_left">

						<div class="img_container">
							<div class="img_contents">
								<img
									src="${pageContext.request.contextPath}/resources/img/임의 프로필.png"
									alt="프로필사진">
							</div>
							<label> <input type="file" accept="image/*"
								style="width: 1px; height: 1px; font-size: 0px; line-height: 0; opacity: 0;">
							</label>
						</div>
						<!-- 프로필 이미지 수정 -->

					</div>

					<div class="profile_right">
						<div>
							<div class="right_top">닉네임</div>
							<div class="right_contents">${__AUTH__.nickName}</div>
						</div>
						<div>
							<div class="right_top">상태메시지</div>
							<div class="right_contents">${__AUTH__.statusMessage}</div>
						</div>
						<div>
							<div class="right_top">평점</div>
							<div class="right_contents">${__AUTH__.rating}</div>
						</div>
						<div>
							<div class="right_top">선호여행지</div>
							<div class="right_contents">${__AUTH__.likeArea}</div>
						</div>
						<div>
							<div class="right_top">MBTI</div>
							<div class="right_contents">${__AUTH__.MBTI}</div>
						</div>


						<div class="bt_wrap">
							<button type="submit" id="modifyBtn">수정</button>
						</div>

					</div>




				</div>

			</div>
			<!-- 프로필 끝-->

			<!-- 작성글 -->
			<div id="tabs-2">
				<!-- <h3>작성글</h3> -->

				<div class="board_list">

					<div class="top">
						<div class="board">게시판</div>
						<div class="title">제목</div>
						<div class="writer">작성자</div>
						<div class="date">작성일</div>
					</div>
					<!-- 불러올 작성글 대략 10개정도 -->


					<div class="post">
						<c:forEach var="item" items="${_LIST_}">
							<input type="hidden" name="nickName" value="${item.nickName}">
							<div id="wirteList">
								<div class="board">${item.boardName}</div>
								<div class="title">${item.title}</div>
								<div class="writer">${item.nickName}</div>
								<div class="date">
									<fmt:formatDate value="${item.regiDate}" pattern="yyyy-MM-dd" />
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="board_page" id="boardPage">
						<c:if test="${writePageMaker.prev}">
							<div class="Prev">
								<a href="/user/mypage?currPage=${writePageMaker.startPage - 1}">Prev</a>
							</div>
						</c:if>

						<c:forEach var="pageNum" begin="${writePageMaker.startPage}"
							end="${writePageMaker.endPage}">
							<div
								class="pageNum ${writePageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
						</c:forEach>

						<c:if test="${writePageMaker.next}">
							<div class="Next">
								<a href="/user/mypage?currPage=${writePageMaker.endPage + 1}">Next</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>





			<!-- 마이동행 -->
			<div id="tabs-3">
				<!-- <h3>동행내역</h3> -->
				<div class="board_list">

					<div class="top">
						<div class="area">장소</div>
						<div class="group">동행이름</div>
						<div class="writer">작성자</div>
						<div class="status">결과</div>
						<div class="startDate">동행시작</div>
						<div class="endDate">동행종료</div>
					</div>
					<!-- 불러올 동행내역 대략 10개정도 -->
					<div class="post">

						<c:set var="count" value="0" />

						<c:forEach var="applist" items="${__APPLIST__}" varStatus="numNo">

							<c:if test="${__AUTH__.nickName == applist.nickName}">
								<c:set var="count" value="0" />

								<div class="myGroup">
									<div class="area">${applist.area}</div>
									<div class="group">${applist.groupName}</div>
									<div class="writer">${applist.writer}</div>
									<div class="status">${applist.outCome}</div>
									<div class="startDate">
										<fmt:formatDate value="${applist.startDate}"
											pattern="yyyy-MM-dd" />
									</div>
									<div class="endDate">
										<fmt:formatDate value="${applist.endDate}"
											pattern="yyyy-MM-dd" />
									</div>
								</div>

							</c:if>


							<c:if test="${applist.outCome eq '수락'}">
								<c:set var="count" value="${count + 1}" />

								<div class="content hide">
									<!-- 숨겨진 내용 -->
									<div class="num">번호</div>
									<div class="group">동행이름</div>
									<div class="nick">닉네임</div>
									<div class="rate">평점</div>
									<div class="startDate">동행시작</div>
									<div class="endDate">동행종료</div>
								</div>
								<div class="">
									<div class="num">${count}</div>
									<div class="group">${applist.groupName}</div>
									<div class="nick">${applist.nickName}</div>
									<div class="rate">${rating.ratedUserNickName}</div>
									<div class="startDate">
										<fmt:formatDate value="${applist.startDate}"
											pattern="yyyy-MM-dd" />
									</div>
									<div class="endDate">
										<fmt:formatDate value="${applist.endDate}"
											pattern="yyyy-MM-dd" />
									</div>
								</div>
							</c:if>


						</c:forEach>


					</div>

					<div class="board_page">
						<c:if test="${pageMaker.prev}">
							<div class="Prev">
								<a href="/user/mypage?currPage=${pageMaker.startPage - 1}">Prev</a>
							</div>
						</c:if>

						<c:forEach var="pageNum" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<div
								class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<div class="Next">
								<a href="/user/mypage?currPage=${pageMaker.endPage + 1}">Next</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<!-- 찜한 글 -->
			<div id="tabs-4">
				<!-- <h3>찜한글</h3> -->

				<div class="wish_list">

					<div class="top">
						<div class="group">동행이름</div>
						<div class="title">제목</div>
						<div class="area">지역</div>
						<div class="startDate">동행시작</div>
						<div class="endDate">동행끝</div>
						<div class="status">현황</div>
					</div>
					<!-- 불러올 작성글 대략 10개정도 -->
					<div class="post">
						<div>
							<div class="group">우리동행</div>
							<div class="title">
								<a href="#">서울ㄲㄲ?</a>
							</div>
							<div class="area">서울</div>
							<div class="startDate">2022-01-01</div>
							<div class="endDate">2022-01-03</div>
							<div class="status">현황</div>
						</div>
						<div>
							<div class="group">쟤네동행</div>
							<div class="title">
								<a href="#">나랑ㄲㄲ?</a>
							</div>
							<div class="area">대전</div>
							<div class="startDate">2022-06-05</div>
							<div class="endDate">2022-0103</div>
							<div class="status">현황</div>
						</div>
						<div>
							<div class="group">얘네동행</div>
							<div class="title">
								<a href="#">현댂ㄲ?</a>
							</div>
							<div class="area">대구</div>
							<div class="startDate">2022-09-09</div>
							<div class="endDate">2022-11-05</div>
							<div class="status">현황</div>
						</div>
						<div>
							<div class="group">남의동행</div>
							<div class="title">
								<a href="#">부산ㄲㄲ?</a>
							</div>
							<div class="area">부산</div>
							<div class="startDate">2023-01-01</div>
							<div class="endDate">2023-01-01</div>
							<div class="status">현황</div>
						</div>
						<div>
							<div class="group">바보동행</div>
							<div class="title">
								<a href="#">바보임</a>
							</div>
							<div class="area">일본</div>
							<div class="startDate">2023-01-03</div>
							<div class="endDate">2023-02-02</div>
							<div class="status">현황</div>
						</div>

					</div>

					<div class="board_page">
						<a href="#" class="bt first"> < <</a> <a href="#" class="bt prev">
							< </a> <a href="#" class="num on">1</a> <a href="#" class="num">2</a>
						<a href="#" class="num">3</a> <a href="#" class="num">4</a> <a
							href="#" class="num">5</a> <a href="#" class="bt next"> > </a> <a
							href="#" class="bt last"> > > </a>

					</div>
				</div>
			</div>

			<!-- 좋아요한 글 -->
			<div id="tabs-5">
				<!-- <h3>좋아요한글</h3> -->

				<div class="like_list">

					<div class="top">
						<div class="title">제목</div>
						<div class="writer">작성자</div>
						<!-- <div class="area">지역</div> -->
						<div class="date">작성일</div>
						<!-- <div class="like">좋아요 여부</div> -->
						<!-- 토글로 클릭시 바로 좋아요 해제 가능 -->
					</div>
					<!-- 불러올 작성글 대략 10개정도 -->
					<div class="post">
						<div>
							<div class="title">
								<a href="#">1번글</a>
							</div>
							<div class="writer">작성자</div>
							<!-- <div class="area">지역</div> -->
							<div class="date">작성일</div>
							<!-- <div class="like">좋아요</div> -->
						</div>
						<div>
							<div class="title">
								<a href="#">2번글</a>
							</div>
							<div class="writer">작성자</div>
							<!-- <div class="area">지역</div> -->
							<div class="date">작성일</div>
							<!-- <div class="like">좋아요</div> -->
						</div>
						<div>
							<div class="title">
								<a href="#">3번글</a>
							</div>
							<div class="writer">작성자</div>
							<!-- <div class="area">지역</div> -->
							<div class="date">작성일</div>
							<!-- <div class="like">좋아요</div> -->
						</div>
						<div>
							<div class="title">
								<a href="#">41번글</a>
							</div>
							<div class="writer">작성자</div>
							<!-- <div class="area">지역</div> -->
							<div class="date">작성일</div>
							<!-- <div class="like">좋아요</div> -->
						</div>
						<div>
							<div class="title">
								<a href="#">51번글</a>
							</div>
							<div class="writer">작성자</div>
							<!-- <div class="area">지역</div> -->
							<div class="date">작성일</div>
							<!-- <div class="like">좋아요</div> -->
						</div>
					</div>

					<div class="board_page">
						<a href="#" class="bt first"> < < </a> <a href="#" class="bt prev">
							< </a> <a href="#" class="num on">1</a> <a href="#" class="num">2</a>
						<a href="#" class="num">3</a> <a href="#" class="num">4</a> <a
							href="#" class="num">5</a> <a href="#" class="bt next"> > </a> <a
							href="#" class="bt last"> > > </a>

					</div>
				</div>
			</div>

			<!-- 회원정보 수정 -->
			<div id="tabs-6">
				<!-- <h3>회원정보수정</h3> -->
				<form method="post" action="/mypage">

					<label for="name">이름</label> <input type="text" id="name"
						name="name" value="${__AUTH__.name}"><br> <label
						for="email">이메일</label> <input type="text" id="email" name="Email"
						value="${__AUTH__.EMail}"><br>

					<!-- <button type="button" onclick="sendEmail()">이메일 인증<button><br> -->
					<label for="emailAuth">인증번호</label> <input type="text"
						id="emailAuth" name="emailAuth" disabled><br> <label
						for="password">현재 비밀번호</label> <input type="password"
						id="password" name="password" required><br> <label
						for="newPassword">새 비밀번호</label> <input type="password"
						id="newPassword" name="password"><br> <label
						for="confirmPassword">새 비밀번호 확인</label> <input type="password"
						id="confirmPassword" name="password"><br> <input
						type="submit" value="수정">
				</form>
			</div>



		</div>

	</div>

	<footer>
		<jsp:include page="../footer.jsp" />
	</footer>






</body>

</html>
