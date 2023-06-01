<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="UTF-8">
				<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">

				<title>아이디 찾기</title>

				<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/findidpwStyle.css">
				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/idfindStyle.css">
				<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bstyle2.css"> -->

				<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
				<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
				<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
				<script>
					$(function () {
						$("#container").tabs();
						heightStyle: "content"
					});


					$(document).ready(function () {
						var inval_Arr = new Array(2).fill(false);

						//============== 존재하는 회원인지 검사 ===============//
						$("#name").blur(function () {
							var name = $('#name').val();

							$.ajax({
								url: '${pageContext.request.contextPath}/common/checkUserName?name=' + name,
								type: 'get',
								dataType: 'json',
								success: function (data) {
									console.log("1 = 중복o / 0 = 중복x : " + data);

									if (data == 1) {
										$("#name_check").text("존재하는 회원입니다.");
										$("#name_check").css("color", "yellowgreen");
										inval_Arr[0] = true;
									} else {
										$("#name_check").text("존재하지 않는 회원입니다.");
										$("#name_check").css("color", "red");
										inval_Arr[0] = false;
									}
								},
								error: function () {
									console.log("실패");
								}
							});
						});

						//   ============ 이메일 인증 ===================
						let code = '';
						$('#mail_Check_Btn').click(function () {
							const EMail = $('#EMail').val(); // 이메일 주소값 얻어오기!
							console.log('완성된 이메일 : ' + EMail); // 이메일 오는지 확인
							const checkInput = $('#mail_Check_Input') // 인증번호 입력하는곳 
							var name = $('#name').val();

							$.ajax({
								url: '${pageContext.request.contextPath}/common/nameEMail',
								type: 'get',
								data: {
									name: name,
									EMail: EMail
								},
								dataType: 'json',
								success: function (data) {

									if (data == 1) {
										$.ajax({
											type: 'get',
											url: "${pageContext.request.contextPath}/common/mailCheck?EMail=" + EMail, // GET방식이라 Url 뒤에 email을 뭍힐수있다.

											success: function (data) {
												console.log("data : " + data);
												checkInput.attr('disabled', false);
												code = data;
												alert('인증번호가 전송되었습니다.')
											}
										}); // end ajax
									} else {
										alert("등록된 이메일이 아닙니다.");
									}
								},
								error: function () {
									console.log("실패");
								}
							});



						}); // end send eamil

						$('#mail_Check_Input').blur(function () {
							const inputCode = $('#mail_Check_Input').val();
							const $resultMsg = $('#mail_check_num');

							if (inputCode == code) {
								$resultMsg.html('인증번호가 일치합니다.');
								$resultMsg.css('color', 'green');
								$('#mail_Check_Btn').attr('disabled', true);
								$('#EMail').attr('readonly', true);
								inval_Arr[1] = 'true';

							} else {
								$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
								$resultMsg.css('color', 'red');
								inval_Arr[1] = 'false';
							}
						});


						$("#reg_submit").click(function () {
							var name = $('#name').val();
							var EMail = $('#EMail').val();

							var validAll = true;
							for (var i = 0; i <= 1; i++) {
								if (inval_Arr[i] == false) {
									validAll = false;
									console.log(i + " : " + inval_Arr[i]);
								}
							}

							if (validAll) {
								$.ajax({
									url: "${pageContext.request.contextPath}/common/showId",
									type: "get",
									data: {
										name: name,
										EMail: EMail
									},
									dataType: 'json',
									success: function (data) {
										if (data.success) {
											var ID = data.ID;
											$('#tabs-1').empty().append('<p class="message">회원님의 아이디는 "' + ID + '" 입니다.</p>');
										} else {
											$('#tabs-1').empty().append('<p class="message">아이디를 찾지 못했습니다.</p>');
										}
									},
									error: function () {
										console.log('아이디 찾기 요청 실패');
									}
								});
							} else {
								alert('기각');
								return false;
							}
						});

					});



				</script>

			</head>

			<body>
				<header>
					<jsp:include page="../header.jsp" />
				</header>

				<div id="wrap" class="wrap">
					<div class="wrap_title">
						<h2>
							<strong>아이디 찾기</strong>
						</h2>
					</div>

					<div id="container">

						<ul>
							<li>
								<a href="#tabs-1">
									<div class="myprofile_text">아이디 찾기</div>
								</a>
							</li>

						</ul>

						<div id="tabs-1">
							ul

							<div class="myprofile">


								<div class="profile_right">
									<label>
										<p style="text-align: left; font-size: 20px; color: #666;">Name</p>
										<input type="text" placeholder="이름" class="size" id="name" name="name" required>
										<div id="name_check" style="font-size: 12px; padding-top: 5px;"></div>
									</label>

									<label>
										<p style="text-align: left; font-size: 20px; color: #666">E-mail</p>
										<div class="abc">
											<input type="email" placeholder="이메일" class="size" id="EMail" name="EMail"
												required>
											<button type="button" id="mail_Check_Btn" class="email_btn" />인증번호 전송
										</div>

										<p style="text-align: left; font-size: 20px; color: #666">인증번호</p>
										<input type="text" class="size" id="mail_Check_Input" disabled="disabled"
											placeholder="인증번호를 입력해주세요." required>

										<div id="mail_check" style="font-size: 12px; padding-top: 5px;"></div>
										<div id="mail_check_num" style="font-size: 12px; padding-top: 5px;"></div>
									</label>

									<p class="showid">
										<input type="submit" value="아이디 찾기" id="reg_submit" class="btn">
									</p>
								</div>




							</div>

						</div>



					</div>

					<footer>
						<jsp:include page="../footer.jsp" />
					</footer>



			</body>

			</html>