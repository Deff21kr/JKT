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
<title>프로필 수정</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/mypageStyle.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<style>
.edit_write .profile_right {
	float: right;
    width: 63%;
    background-color: aliceblue;
    border-radius: 10px;
    padding: 19%;
    padding-bottom: 157px;
}

.edit_write .profile_right div {
	width: 100%;
	padding-top: 15px; /* 간격을 더 추가 */
	padding-bottom: 15px; /* 간격을 더 추가 */
}

.edit_write .profile_right div div {
	display: inline-block;
}

.edit_write .profile_right div .right_top {
	width: 25%;
	font-size: 2rem;
	color: black;
	vertical-align: middle;
}

.edit_write .profile_right div .right_contents {
	width: fit-content;
	font-size: 1.6rem;
	vertical-align: middle;
	color: blue;
}

.edit_write .profile_right .bt_wrap {
	text-align: center;
}

.message {
	width: 100%;
	height: 5.25em;
	border: solid 1px black;
	resize: none;
}

/* 추가된 부분: 버튼 스타일과 hover 효과 */
#modifyBtn {
	background-color: #2b65f8;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	transition-duration: 0.4s; 
}

#modifyBtn:hover {
	background-color: #45a049;
}

/* 폼 요소에 hover 효과 추가 */
textarea:hover, select:hover {
	border: 1px solid #aaa;
}
</style>

</head>

<body>
	<div class="edit_write">
		<div class="profile_right">
			<form action="/user/edit" method="post" class="dataform">
				<input type="hidden" name="ID" value="${__AUTH__.ID}">
				<div>
					<div class="right_top">닉네임</div>
					<div class="right_contents">${__AUTH__.nickName}</div>
				</div>
				<div>
					<div class="right_top">상태메시지</div>
					<textarea name="statusMessage" cols="30" rows="10"
						placeholder="상태메세지를 입력해주세요." class="message"></textarea>
				</div>
				<label> <span
					style="text-align: left; font-size: 20px; color: #666;">MBTI</span>
					<select name="MBTI" required>
						<option value="">MBTI유형</option>
						<option value="ISTJ">ISTJ</option>
						<option value="ISFJ">ISFJ</option>
						<option value="ESTJ">ESTJ</option>
						<option value="ESFJ">ESFJ</option>
						<option value="ISTP">ISTP</option>
						<option value="ISFP">ISFP</option>
						<option value="ESFJ">ESFJ</option>
						<option value="ESTP">ESTP</option>
						<option value="INFJ">INFJ</option>
						<option value="INFP">INFP</option>
						<option value="ENFP">ENFP</option>
						<option value="ENFJ">ENFJ</option>
						<option value="INTJ">INTJ</option>
						<option value="INTP">INTP</option>
						<option value="ENTP">ENTP</option>
						<option value="ENTJ">ENTJ</option>
				</select>
				</label> <label>
					<p style="text-align: left; font-size: 20px; color: #666;">좋아하는
						지역</p> <select id="likeArea" name="likeArea" required>
						<option value="">좋아하는 지역을 선택하세요</option>
						<option value="서울">서울</option>
						<option value="강원">강원</option>
						<option value="경기">경기</option>
						<option value="충청">충청</option>
						<option value="경상">경상</option>
						<option value="전라">전라</option>
						<option value="제주">제주</option>
				</select><br>

				</label>

				<div class="bt_wrap">
					<button type="submit" id="modifyBtn">수정</button>
					<script>
						$(function() {
							$("#modifyBtn").click(function() {
								window.location.assign = "/user/mypage";
							});
						});
					</script>
					
				</div>
			</form>
		</div>
	</div>

	<script>
		$(function() {
			$('.modify').on('click', function() {
				location = "/user/mypage";
			});
		});
	</script>
</body>

</html>