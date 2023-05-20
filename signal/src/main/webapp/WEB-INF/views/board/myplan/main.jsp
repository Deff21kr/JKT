<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>여행시그널</title>

<!-- fonts awesome -->
<script src="https://kit.fontawesome.com/7235c72187.js"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>


<script>
	$(function() {
		$('.plannerlist-make').click(function() {
			location = "/board/myplan/makePlan";
		});
		$('.pageNum').on('click', function(e) {
			let selectedPageNum = e.currentTarget.textContent;
			location = "/board/myplan/main?currPage=" + selectedPageNum;
		});
	});
</script>


<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	width: 1200px;
	margin: auto;
}

header {
	border-bottom: 1px solid rgb(208, 207, 207);
}

a {
	text-decoration: none;
	color: black;
}

p {
	font-size: 13px
}

div>ul>li {
	font-size: 15px;
}

/* #sec {
						width: 1200px;
						height: 1000px;
						border: 1px solid;
					} */
.topmain {
	width: 1112px;
	height: 130px;
	display: flex;
	justify-content: space-between;
	align-items: end;
	margin: auto;
	padding: 20px 12px;
}

.plannerlist {
	/* margin: 50px 0px 20px 50px; */
	width: 140px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
	/* background: aliceblue;
						border: 1px solid rgb(167, 208, 245); */
	border-radius: 17px;
}

.plannerlist-make {
	/* margin: 50px 0px 20px 50px; */
	width: 140px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #4463e6;
	color: white;
	border: none;
	border-radius: 17px;
	cursor: pointer;
}

.plannerlist-make>span {
	color: white;
	font-weight: lighter;
}

.plannerlist span {
	font-size: 20px;
}

.planContent {
	display: block;
	float: left;
	width: 350px;
	height: 380px;
	border: 1px solid #dfdfdf;
	margin: 20px 10px 20px 10px;
}

.planContent:hover {
	border: 1px solid #4463e6;
}

.board_page {
	/* 위로 여백 떨어짐 */
	margin-top: 30px;
	text-align: center;
	font-size: 0;
}

.board_page .Prev, .board_page .pageNum, .board_page .Next {
	display: inline-block;
	width: 32px;
	height: 35px;
	box-sizing: border-box;
	border: 1px solid rgb(208, 207, 207);
	border-left: 0;
	vertical-align: middle;
	line-height: 100%;
}

/* .board_page a {
						display: inline-block;
						width: 32px;
						height: 35px;
						box-sizing: border-box;
						border: 1px solid rgb(208, 207, 207) ;
						border-left: 0;
						vertical-align: middle;
						line-height: 100%;
					} */
.board_page .Prev, .board_page .Next {
	width: 40px;
	padding-top: 10px;
	font-size: 1.2rem;
	/* << 사이 조정 */
	letter-spacing: -1px;
	cursor: pointer;
}

.board_page .pageNum {
	padding-top: 9px;
	font-size: 1.4rem;
	cursor: pointer;
}

/* .board_page a.num.on {
						border-color: #2B65F8;
						background: #2B65F8 ;
						color: #fff;
					} */
.current {
	border-color: #2B65F8;
	background: #2B65F8;
	color: #fff;
}

.board_page .Prev {
	border-left: 1px solid rgb(208, 207, 207);
}

.board_page .pageNum {
	border-left: 1px solid rgb(208, 207, 207);
}
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<section id="sec">

		<div class="topmain">
			<div class="plannerlist">
				<strong> <span> 플래너 리스트 </span>
				</strong>

			</div>

			<button class="plannerlist-make">
				<span>플래너 만들기</span>
			</button>
		</div>

		<div class="maincontent"
			style="border: 1px solid gainsboro; width: 1113px; min-height: 700px; margin: 0px auto 40px; border-radius: 20px; padding: 20px 0px;">

			<c:if test="${not empty __AUTH__.nickName}">
				<c:forEach var="MyPlanVO" items="${__MYPLAN__}">

					<a href="/board/myplan/get?planNo=${MyPlanVO.planNo}"
						class="planContent">
						<div class="title"
							style="background: ghostwhite; width: 100%; height: 150px; border-bottom: 1px solid #dfdfdf; padding: 20px 20px; display: flex; flex-direction: column; justify-content: center;">
							<div>
								<p>플래너명 : ${MyPlanVO.planName}</p>
								<br>
							</div>

							<div style="display: flex; justify-content: space-between;">
								<div>
									<p>여행기간 :</p>
									<br>



									<p>
										<fmt:formatDate value="${MyPlanVO.startDate}"
											pattern="yyyy-MM-dd" />
										~
										<fmt:formatDate value="${MyPlanVO.endDate}"
											pattern="yyyy-MM-dd" />
									</p>
								</div>
								<div
									style="justify-content: center; display: flex; align-items: center;">
									<button
										style="width: 90px; height: 50px; font-size: 14px; color: white;
	/* display: flex; */ background: #4463e6; border: none; border-radius: 30px; cursor: pointer;">플래너보기</button>
								</div>
							</div>
						</div>
						<div style="width: 348px; height: 230px; padding: 15px;">
							<ul style="max-height: 180px; overflow: hidden;">
								<h4 style="margin: 10px;">여행 일정</h4>


								<c:set var="count" value="0" />

								<c:forEach var="JoinDTO" items="${__JOINLIST__}">
									<c:if test="${MyPlanVO.planNo == JoinDTO.planNo}">
										<c:set var="count" value="${count + 1}" />
										<c:if test="${count <= 5}">
											<li style="list-style: inside; margin: 10px;">${JoinDTO.place}</li>
										</c:if>
									</c:if>
								</c:forEach>

							</ul>
						</div>
					</a>
				</c:forEach>
			</c:if>
			<!-- float 마지막은 clear:both -->
			<div class="clear" style="clear: both;"></div>


			<div class="board_page">
				<c:if test="${pageMaker.prev}">
					<div class="Prev">
						<a href="/board/oneonone/list?currPage=${pageMaker.startPage - 1}">Prev</a>
					</div>
				</c:if>
				<c:forEach var="pageNum" begin="${pageMaker.startPage}"
					end="${pageMaker.endPage}">
					<div
						class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}
					</div>
				</c:forEach>
				<c:if test="${pageMaker.next}">
					<div class="Next">
						<a href="/board/oneonone/list?currPage=${pageMaker.endPage + 1}">Next</a>
					</div>
				</c:if>
			</div>



		</div>



	</section>


	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>

</html>