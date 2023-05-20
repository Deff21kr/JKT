<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/registerStyle.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>


</head>

<body>
	<div id="con">
		<div id="login">
			<div id="login_form">
				<!--로그인 폼-->

				<form action="/common/register" method="post">

			<script>

			$(document).ready(function() {
				  var inval_Arr = new Array(7).fill(false);

				  // 모든 공백 체크 정규식
				  var empJ = /\s/g;
				  // 아이디 정규식
				  var idJ = /^[a-zA-Z0-9]{8,16}$/;
				  // 비밀번호 정규식
				  var pwJ = /^[A-Za-z0-9]{8,20}$/;
				  // 이름 정규식
				  var nameJ = /^[가-힣A-Za-z]{2,10}$/;
				  var nickJ = /^[가-힣A-Za-z0-9]{2,10}$/;
				  // 이메일 검사 정규식
				  var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
				  // 핸드폰 검사 정규식
				  var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

				  //============== ID 중복 및 유효성 검사 ===============//
				  $("#ID").blur(function() {
				    var ID = $('#ID').val();

				    if (ID == "") {
				      $('#id_check').text('아이디를 입력바랍니다.');
				      $('#id_check').css('color', 'red');
				      inval_Arr[0] = false;
				      return;
				    }

				    if (!idJ.test(ID)) {
				      $('#id_check').text("영어 대/소문자와 숫자 8~16자리");
				      $('#id_check').css('color', 'red');
				      inval_Arr[0] = false;
				      return;
				    }

				    $.ajax({
				      url: '${pageContext.request.contextPath}/common/idCheck?ID=' + ID,
				      type: 'get',
				      dataType: 'json',
				      success: function(data) {
				        console.log("1 = 중복o / 0 = 중복x : " + data);

				        if (data == 1) {
				          $("#id_check").text("중복된 아이디입니다.");
				          $("#id_check").css("color", "red");
				          inval_Arr[0] = false;
				        } else {
				          $("#id_check").text("합격!");
				          $("#id_check").css("color", "yellowgreen");
				          inval_Arr[0] = true;
				        }
				      },
				      error: function() {
				        console.log("실패");
				      }
				    });
				  }); // 아이디 유효성 및 중복 검사

				  //============== 비밀번호 유효성 검사 ===============//
				  $("#password, #password2").blur(function() {
				    var password = $('#password').val();
				    var password2 = $('#password2').val();

				    // 비밀번호 유효성 검사
				    if (password == "") {
				      $('#pw_check').text('비밀번호를 입력해주세요.');
				      $('#pw_check').css('color', 'red');
				      inval_Arr[1] = false;
				    } else if (!pwJ.test(password)) {
				      $('#pw_check').text("영어 대/소문자와 숫자 8~20자리");
				      $('#pw_check').css('color', 'red');
				      inval_Arr[1] = false;
				    } else {
				      $('#pw_check').text("합격!");
				      $('#pw_check').css('color', 'yellowgreen');
				      inval_Arr[1] = true;
				    }

				    // 비밀번호 확인 검사
				    if (password2 == "") {
				      $('#pw_check2').text("비밀번호를 확인해주세요");
				      $('#pw_check2').css('color', 'red');
				      inval_Arr[2] = false;
				    } else if (password != password2) {
				      $('#pw_check2').text("입력하신 비밀번호가 다릅니다.");
				      $('#pw_check2').css('color', 'red');
				      inval_Arr[2] = false;
				    } else {
				      $('#pw_check2').text("합격!!");
				      $('#pw_check2').css('color', 'yellowgreen');
				      inval_Arr[2] = true;
				    }
				  });

				  //============== 이름 유효성 검사 ===============//
				  $("#name").blur(function() {
				    var name = $('#name').val();
				    if (nameJ.test(name)) {
				      $('#name_check').text('합격!');
				      $('#name_check').css('color', 'yellowgreen');
				      inval_Arr[3] = true;
				    } else {
				      $('#name_check').text('이름을 확인해주세요');
				      $('#name_check').css('color', 'red');
				      inval_Arr[3] = false;
				    }
				  });

				  //============== 이메일 유효성 검사 ===============//
				  $("#EMail").blur(function() {
				    var EMail = $('#EMail').val();
				    if (mailJ.test(EMail)) {
				      $('#mail_check').text('합격!');
				      $('#mail_check').css('color', 'yellowgreen');
				      inval_Arr[4] = true;
				    } else {
				      $('#mail_check').text('이메일을 확인해주세요');
				      $('#mail_check').css('color', 'red');
				      inval_Arr[4] = false;
				    }
				  });

				  //============== 핸드폰 유효성 검사 ===============//
				  $("#phoneNumber").blur(function() {
				    var phone = $('#phoneNumber').val();
				    if (phoneJ.test(phone)) {
				      $('#phone_check').text('합격!');
				      $('#phone_check').css('color', 'yellowgreen');
				      inval_Arr[5] = true;
				    } else {
				      $('#phone_check').text('휴대폰을 확인해주세요');
				      $('#phone_check').css('color', 'red');
				      inval_Arr[5] = false;
				    }
				  });

				  //============== 닉네임 중복 및 유효성 검사 ===============//
				  $("#nickName").blur(function() {
				    var nickName = $('#nickName').val();

				    if (nickName == "") {
				      $('#nick_check').text('닉네임을 입력바랍니다.');
				      $('#nick_check').css('color', 'red');
				      inval_Arr[6] = false;
				    } else if (!nickJ.test(nickName)) {
				      $('#nick_check').text("한글 및 영어 대/소문자와 숫자 2~10자리");
				      $('#nick_check').css('color', 'red');
				      inval_Arr[6] = false;
				    } else {
				      $.ajax({
				        url: '${pageContext.request.contextPath}/common/nickNameCheck?nickName=' + nickName,
				        type: 'get',
				        dataType: 'json',
				        success: function(data) {
				          console.log("1 = 중복o / 0 = 중복x : " + data);

				          if (data == 1) {
				            $("#nick_check").text("중복된 닉네임입니다.");
				            $("#nick_check").css("color", "red");
				            inval_Arr[6] = false;
				          } else {
				            $("#nick_check").text("합격!");
				            $("#nick_check").css("color", "yellowgreen");
				            inval_Arr[6] = true;
				          }
				        },
				        error: function() {
				          console.log("실패");
				        }
				      });
				    }
				  });

				  $('#reg_submit').click(function() {
					 
				    var validAll = true;
				    for (var i = 0; i < inval_Arr.length; i++) {

				      if (inval_Arr[i] == false) { // 유효성 검사를 하나라도 통과하지 못했다면
				        validAll = false;
				        console.log(i +" : "+inval_Arr[i]);
				      }
				    }

				    if (validAll) { // 유효성 모두 통과
				    	 $("#reg_submit").attr("disabled", false);
				    } else {
				    	 
				      alert('기각');
				      return false;
				    }
				  });
				});


			
            </script>
					<h3 class="login" style="letter-spacing: -1px;">회원가입</h3>
					<hr>

					<label> <!-- <span>ID</span> -->
						<p style="text-align: left; font-size: 12px; color: #666">ID</p> <input
						type="text" placeholder="아이디" class="size" id="ID" name="ID"
						maxlength="20" required>
						<div id="id_check" style="font-size: 12px; padding-top: 5px;"></div>
						<p></p>
					</label>
					<!--아이디-->

					<label> <!-- <span>PW</span> -->
						<p style="text-align: left; font-size: 12px; color: #666">Password
						</p> <input type="password" placeholder="비밀번호" class="size"
						id="password" name="password" required>
						<div id="pw_check" style="font-size: 12px; padding-top: 5px;"></div>
						<p style="text-align: left; font-size: 12px; color: #666">Password
							Check</p> <input type="password" placeholder="비밀번호 확인" class="size"
						id="password2" name="password2" required>
						<div id="pw_check2" style="font-size: 12px; padding-top: 5px;"></div>
					</label>
					<!--비밀번호-->

					<label>
						<p style="text-align: left; font-size: 12px; color: #666;">NickName</p>
						<input type="text" placeholder="닉네임" class="size" id="nickName"
						name="nickName" required>
						<div id="nick_check" style="font-size: 12px; padding-top: 5px;"></div>
					</label> <label>
						<p style="text-align: left; font-size: 12px; color: #666;">Name</p>
						<input type="text" placeholder="이름" class="size" id="name"
						name="name" required>
						<div id="name_check" style="font-size: 12px; padding-top: 5px;"></div>
					</label>


					<tr>
						<p style="text-align: left; font-size: 12px; color: #666;">생년월일</p>
						<td><input type="date" class="textform" id="birthDate"
							name="birthDate" required></td>
						<div id="birth_check" style="font-size: 12px; padding-top: 5px;"></div>
					</tr>


					<div>
						<label>
							<p style="text-align: left; font-size: 12px; color: #666;">성별선택</p>
							<input type="radio" name="gender" value="man" required>남성
							<input type="radio" name="gender" value="woman" required>여성
							<div id="gender_check" style="font-size: 12px; padding-top: 5px;"></div>
						</label>
					</div>

					<div>
						<p style="text-align: left; font-size: 12px; color: #666;">MBTI</p>
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
					</div>

					<label for="likeArea">좋아하는 지역</label> <select id="likeArea"
						name="likeArea" required>
						<option value="">좋아하는 지역을 선택하세요</option>
						<option value="서울">서울</option>
						<option value="강원">강원</option>
						<option value="경기">경기</option>
						<option value="충청">충청</option>
						<option value="경상">경상</option>
						<option value="전라">전라</option>
						<option value="제주">제주</option>
					</select><br> <label> <!-- <span>PW</span> -->
						<p style="text-align: left; font-size: 12px; color: #666">Mobile
							Phone</p> <input placeholder="-빼고 숫자만 입력" class="size num2"
						id="phoneNumber" name="phoneNumber" required>
						<div id="phone_check" style="font-size: 12px; padding-top: 5px;"></div>
					</label>
					<!--전화번호-->


					<label> <!-- <span>PW</span> -->
						<p style="text-align: left; font-size: 12px; color: #666">E-mail</p>
						<input type="email" placeholder="이메일" class="size" id="EMail"
						name="EMail" required>
						<div id="mail_check" style="font-size: 12px; padding-top: 5px;"></div>
					</label>
					<!--이메일-->

					<!-- HTML -->


					<br>
					<p>
						<input type="submit" value="회원가입" id="reg_submit" class="btn">

					</p>
				</form>

				<hr>
				<p class="find">
					<span><a href="login.jsp">로그인 페이지로 이동</a></span>
				</p>
			</div>
			<div></div>
			<!--con-->
			<!-- 애니메이션을 담당하게 될 자바스크립트 참조 -->
			<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
			<!-- 특정홈페이지에서 제이쿼리 호출 -->
			<script src="js/bootstrap.js"></script>
		</div>
	</div>
</body>

</html>