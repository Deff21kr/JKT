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

<title>Travel Signal - Main Page</title>
<link rel="shortcut icon" href="/resources/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

<!-- style -->
<link rel="stylesheet" href="../../resources/css/style.css">
<link rel="stylesheet" href="../../resources/css/style2.css">
<link rel="stylesheet" href="../../resources/css/mainpage.css">

<script src="https://kit.fontawesome.com/1d07350235.js"
	crossorigin="anonymous"></script>

<!-- script -->
<script src="../../resources/js/mainpage.js" defer></script>
<script src="../../resources/js/review.js" defer></script>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script defer="defer">
	$(function() {
		$('.content > .card > .image').on('click', function(e) {
			var postNum = $(this).prev().val();
			location = '/board/group/get?postNo=' + postNum + '&currPage=1';
		});
	});

	$(function() {
		$('.content > .card > .detail').on('click', function(e) {
			var postNum = $(this).prev().prev().prev().val();
			location = '/board/group/get?postNo=' + postNum + '&currPage=1';
		});
	});

	/* $(function () {
	    var postNo = $('.reviewPostNo').val();    

	$.ajax({
	    url: "/board/review/getAttachList",
	    type: "GET",
	    data: { postNo: postNo },
	    dataType: "json",
	    async: false, // 동기식 요청 설정
	    success: function(arr) {
	        console.log(arr);
	        // 받아온 JSON 데이터에 대한 처리

	            var str = "";
	            var contextPath = "${pageContext.request.contextPath}";
	            var attach = arr[i]; // 첫번째 첨부 파일 정보 가져옴
	            var fileCallPath = encodeURIComponent(attach.uploadPath + "/thumb_" + attach.uuid + "_" + attach.fileName);
	            var imagePath = contextPath + "/fileupload/display?fileName=" + fileCallPath;
	
	            str += "<div class='post-thumbnail' data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "'>";
	            str += "<img src='" + imagePath + "' alt='사진1' class='postimg'></div>";
	
	            $(".post-link-" + postNo).html(str); // postNo에 해당하는 클래스를 가진 요소에 내용 설정
	            console.log("리스트 목록 사진 불러오기 완료");
	    },
	    error: function(xhr, status, error) {
	            console.log(error);
	    }                 
	    });//ajax */
</script>

</head>

<body>

	<header>
		<jsp:include page="header.jsp" />
	</header>

	<!-- 동행 찾기 -->
	<div class="menu">
		<strong><a href="/board/group/list" target="_self">동행인이
				기다리고 있어요</a></strong>
		<button class="plusBtn" onclick="location.href='/board/group/list'">
			<i class="fa-solid fa-plus"></i> 더보기
		</button>
	</div>

	<div class="container">
		<i id="left" class="fa-solid fa-angle-left"></i>
		<div class="content">

			<c:forEach items="${__GROUPBOARD__}" var="groupVO">
				<div class="card">
					<input type="hidden" class="postNum" value="${groupVO.postNo}">
					<div class="image">
						<img src="../../resources/img/9.png" alt="">
					</div>

					<div class="recruit-status">
						<button type="button" id="recruitBtn">${groupVO.recruitStatus}</button>
					</div>

					<div class="detail">
						<div class="info">
							<button type="button" id="area">${groupVO.area}</button>
							<button type="button" id="day">
								<fmt:formatDate value="${groupVO.startDate}"
									pattern="yyyy/MM/dd" />
								~
								<fmt:formatDate value="${groupVO.endDate}" pattern="yyyy/MM/dd" />
							</button>
						</div>

						<div class="title">${groupVO.title}</div>

						<div class="profile">
							<img src="../../resources/img/profile.jpg" alt=""> <span
								class="nickName">${groupVO.nickName}</span>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
		<i id="right" class="fa-solid fa-angle-right"></i>
	</div>

	<!-- review 게시판 -->
	<div class="menu">
		<strong><a href="/board/review/list" target="_self">동행인과
				추억을 쌓아보아요</a></strong>
		<button class="plusBtn" onclick="location.href='/board/review/list'">
			<i class="fa-solid fa-plus"></i> 더보기
		</button>
	</div>

	<div class="review-container">
		<i id="left" class="fa-solid fa-angle-left"></i>
		<div class="review-content">
			<c:forEach items="${__REVIEW__}" var="reviewVO">

				<div class="review-card">
					<div class="image">
						<input type="hidden" class="reviewPostNo"
							value="${reviewVO.postNo}"> <a
							href="/board/review/get?currPage=${pageMaker.cri.currPage}&postNo=${reviewVO.postNo}"
							class="post-link post-link-${reviewVO.postNo}"></a>
					</div>

					<div class="review-like">
						<button type="button" id="likeBtn">
							<i class="fa-solid fa-heart"></i>
						</button>
					</div>

					<div class="title">
						<button type="button" id="locationBtn">
							<i class="fa-solid fa-location-dot"></i>${reviewVO.area}
						</button>
					</div>

					<div class="profile">
						<img src="../../resources/img/profile.jpg" alt=""> <span
							class="nickName">${reviewVO.nickName}</span>
					</div>
				</div>

				<script>
					$(
							function() {
								var postNo = '${reviewVO.postNo}';

								$
										.ajax({
											url : "/board/review/getAttachList",
											type : "GET",
											data : {
												postNo : postNo
											},
											dataType : "json",
											async : false, // 동기식 요청 설정
											success : function(arr) {
												console.log(arr);
												// 받아온 JSON 데이터에 대한 처리

												var str = "";
												var contextPath = "${pageContext.request.contextPath}";
												var attach = arr[0]; // 첫번째 첨부 파일 정보 가져옴
												var fileCallPath = encodeURIComponent(attach.uploadPath
														+ "/thumb_"
														+ attach.uuid
														+ "_"
														+ attach.fileName);
												var imagePath = contextPath
														+ "/fileupload/display?fileName="
														+ fileCallPath;

												str += "<div class='post-thumbnail' data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "'>";
												str += "<img src='" + imagePath + "' alt='사진1' class='postimg'></div>";

												$(".post-link-" + postNo).html(
														str); // postNo에 해당하는 클래스를 가진 요소에 내용 설정
												console
														.log("리스트 목록 사진 불러오기 완료");
											},
											error : function(xhr, status, error) {
												console.log(error);
											}
										});//ajax
							})
				</script>
			</c:forEach>

		</div>
		<i id="right" class="fa-solid fa-angle-right"></i>
	</div>

	<!-- QnA 게시판 -->
	<div class="menu">
		<strong><a href="/board/qna/list" target="_self">동행인에게
				물어보세요</a></strong>
		<button class="plusBtn" onclick="location.href='/board/qna/list'">
			<i class="fa-solid fa-plus"></i> 더보기
		</button>
	</div>

	<div class="middle">
		<div class="middle-content">
			<ul>
				<c:forEach items="${__QNA__}" var="QnAVO">

					<li><a href="/board/qna/get?postNo=${QnAVO.postNo}&currPage=1"
						target="_self"> <span>Q&A</span> ${QnAVO.title}
					</a></li>

				</c:forEach>

			</ul>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="footer.jsp" />

</body>

</html>