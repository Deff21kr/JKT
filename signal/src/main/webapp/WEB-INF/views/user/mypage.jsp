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
	href="${pageContext.request.contextPath }/resources/css/mypage2Style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle2.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<script>
				  let tabId = ''; // 탭 ID 변수를 선언합니다.
				$(document).ready(function() {

					  $('.tabs').on('click', function(e) {
					    e.preventDefault();
					    tabId = $(this).attr('href'); // 탭 ID 값을 업데이트합니다.
					    let url = window.location.href.split('#')[0]; // 현재 URL에서 # 이전 부분만 가져옵니다.
					    let url2 = window.location.href.split('?')[0];
					    let queryString = window.location.search; // 쿼리 문자열을 가져옵니다.
					    console.log("queryString : " + queryString);
					    console.log("ID: " + tabId);
					    console.log("URL: " + url);
					    console.log("URL2: " + url2);
					    
					    // 쿼리 문자열에 'currPage'가 있는지 확인합니다.
					    if (queryString.includes('currPage')) {
					      window.location.href = url2 + tabId; // 현재 URL과 탭의 ID를 합쳐서 새로운 URL로 이동합니다.
					    } else {
					      window.location.href = url + tabId; // 현재 URL과 탭의 ID를 합쳐서 새로운 URL로 이동합니다.
					    }
					  });

					  $('.pageNum').on('click', function(e) {
						tabId =  window.location.hash;
						 let url = window.location.href.split('#')[0];
					    e.preventDefault();
					    let selectedPageNum = e.currentTarget.textContent;
					    console.log("ID2: " + tabId);
					    console.log("페이지: " );
					    window.location.href ="/user/mypage"+"?currPage="+selectedPageNum+ tabId;
					    
				
					  });
					});

				
				
				$(function () {
					$("#container").tabs();
					heightStyle: "content"
				});

				$(function() {
					$("#modifyBtn").click(function() {
						location = "/user/edit"
					});
				});

				$(function() {
					$('.writeList').on('click', function(e) {
						var postNum = $(this).find('.postNum').val();
						var boardName = $(this).find('.board').html();
						if(boardName == 'QnA'){
							console.log('============>', boardName)
							location = '/board/qna/get?postNo='+ postNum + '&currPage=1';
						} else if(boardName == '동행찾기'){
							console.log('============>', boardName)
							location = '/board/group/get?postNo='+ postNum + '&currPage=1';
						} else if(boardName == '여행후기'){
							console.log('============>', boardName)
							location = '/board/review/get?postNo='+ postNum + '&currPage=1';
						}
					});
				});
				
					$(document).ready(function() {
						  $('.rateresult').submit(function(e) {
						    e.preventDefault(); // 폼 기본 제출 동작 막기
						    
						    var button = $(this).find('button');								    // AJAX 요청 생성
						    $.ajax({
						      url: '/user/rate', // 폼의 action 속성 값
						      type: 'post',
						      data: $(this).serialize(), // 폼 데이터 직렬화
						      success: function(response) {
						    	  
						        // 성공적으로 요청을 보냈을 때 수행할 작업
						        console.log('AJAX 요청 성공');
						       // button.prop('disabled', true);
						        //alert(response);
						      },
						      error: function(xhr, status, error) {
						        // 요청을 보내는 중에 오류가 발생했을 때 수행할 작업
						        console.error('AJAX 요청 오류');
						        console.log('상태:', status);
						        console.log('오류:', error);
						        
						        button.prop('disabled', false);
						      	alert(data);
	
						      }
						    });
						  });
						});
				
				var friend = [];
				$(document).ready(function() {
					$('.myGroup').off('click').on('click', function(e) {
						
					if($('.content3').length > 0 ){
						var content = $(this).nextUntil('.myGroup');
						content.hide();
						$('.content3').remove();
						return false;
					}
					
					if($(this).find('.status').html() == '진행중' || $(this).find('.status').html() == '거절'){
						return false;
					}
						
				    var groupNo = $(this).find('.groupNo').val();
				    var brother = $(this).next().next();
				    var bro = $(this).next();
				    var clonedBrother = brother.clone();
				    var closestMyGroup = $(this).closest('.myGroup');

				    $('.content2').addClass('hide');
				    $('.content').addClass('hide');
					$('.content3').remove(); // 모든 content3 클래스 제거

					bro.removeClass('hide');
					
				    $.ajax({
				      url: '/user/mypage/friend',
				      type: 'post',
				      data: { groupNo: groupNo },
				      dataType: 'json',
				      success: function(data) {
				        console.log('성공');
				        console.log(data);
				        
				        bro.show();
				        
				        for (var i = data.length - 1; i >= 0; i--) {
				          friend = data[i];
				          console.log(friend);
				        	
				            clonedBrother = brother.clone();		// content2 클래스 복제
				            clonedBrother.attr('class', 'content3')	// content2 복제한 클래스 content3으로 이름 바꿈
				            clonedBrother.find('.num').html(i + 1);
				            clonedBrother.find('.group').html(friend.groupName);
				            clonedBrother.find('.nick').html(friend.nickName);
				            clonedBrother.find('.rate').find('.ratedUserNickName').val(friend.nickName);
				            var starElements = clonedBrother.find('.rate').find('.star'); // 별점 요소들을 선택
				            starElements.each(function(index) {
				              var starElement = $(this);
				              var originalId = starElement.attr('id'); 
				              var modifiedStarId = originalId ? originalId + String(i) : 'star' + (index + 1) + String(i); // 기존 id가 존재하면 그대로 사용, 없으면 'star' + 숫자 + 숫자 형태로 생성
				              starElement.attr('id', modifiedStarId);
				              starElement.next('label').attr('for', modifiedStarId); // 해당 별점 요소에 대응하는 label의 for 속성을 설정
				            });
				            
				            $(clonedBrother).insertAfter(brother);
				             
				        }
				        $('.content3').show();
				          
				      },
				      // ... (error handling)
				    });
				  });
				});
	
			$(function(){
				$('.rateBtn').click(function(){
					location.replace('/user/mypage#tabs-3');
				})
			})
			
			$(function() {
				$('.pin').on('click', function(e) {
					var postNum = $(this).find('.pinPostNo').val();
						location = '/board/group/get?postNo='+ postNum + '&currPage=1';
				});
			});
					

				</script>


<script>
  $("#fileName").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(500);        
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
 </script>


<style>



#ive {

	width: 80%;
	height: 80%;
	margin-left: 20px;
	border-radius: 50%;


}

.hide {
	display: none;
}

#tabs-3 .board_list .post .content div {
	width: 20%;
	display: inline-block;
	font-size: 2rem;
	text-align: center;
	padding-top: 15px;
}



#tabs-3 .board_list .post .content .rateresult {
	width: 40%;
	display: inline-flex;
	font-size: 2rem;
	text-align: center;
	padding: 0px;
}


#tabs-3 .board_list .post .content .rateresult>div {
	width: 50%;
	display: inline-block;
	font-size: 2rem;
	text-align: center;
	padding-top: 15px;
}

#tabs-3 .board_list .post .content2 div {
	width: 20%;
	display: inline-block;
	font-size: 1.5rem;
	text-align: center;
	padding-top: 15px;
}

#tabs-3 .board_list .post .content2 .rateresult {
	width: 40%;
	display: inline-flex;
	font-size: 1.5rem;
	text-align: center;
	padding: 0px;
}

#tabs-3 .board_list .post .content2 .rateresult>div {
	width: 50%;
	display: inline-block;
	font-size: 1.5rem;
	text-align: center;
	padding-top: 15px;
}

#tabs-3 .board_list .post .content3 div {
	width: 20%;
	display: inline-block;
	font-size: 1.5rem;
	text-align: center;
	padding-top: 15px;
}

#tabs-3 .board_list .post .content3 .rateresult {
	width: 40%;
	display: inline-flex;
	font-size: 1.5rem;
	text-align: center;
	padding: 0px;
}

#tabs-3 .board_list .post .content3 .rateresult>div {
	width: 50%;
	display: inline-block;
	font-size: 1.5rem;
	text-align: center;
	padding-top: 15px;
}

.myGroup {
	cursor: pointer;
}

.content {
	border: 1px solid
}

.rate {
	display: inline-block;
}

.rate input[type="radio"] {
	display: none;
}

.rate label {
	cursor: pointer;
	color: #ccc;
	font-size: 32px;
}

.rate input[type="radio"]:checked ~ label {
	color: #ffcc00;
}

.result>button[type="submit"] {
	cursor: pointer;
}

.board_list>.post>.writeList>div{
	cursor: pointer;
}

#tabs-4 > div > div.post > .pin{
	cursor: pointer;
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
				<li><a href="#tabs-1" class="tabs">
						<div class="c">프로필</div>
				</a></li>
				<li><a href="#tabs-2" class="tabs">
						<div class="c">작성글</div>
				</a></li>
				<li><a href="#tabs-3" class="tabs">
						<div class="c">마이동행</div>
				</a></li>
				<li><a href="#tabs-4" class="tabs">
						<div class="c">찜한 글</div>
				</a></li>
				<li><a href="#tabs-5" class="tabs">
						<div class="c">좋아요한 글</div>
				</a></li>
				<li><a href="#tabs-6" class="tabs">
						<div class="c">개인 정보 수정</div>
				</a></li>
			</ul>

			<!-- 프로필-->
			<div id="tabs-1">


				<div class="myprofile">
			
					<div class="profile_left">
   						<img src="${pageContext.request.contextPath}/resources/img/ive.jpg" id="ive"/>
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
							<c:choose>
								<c:when test="${__rating__.ratedRating != null}">
									<div class="right_contents">
										<fmt:formatNumber value="${__rating__.ratedRating}" pattern=".0" />
									</div>
									<!-- <div class="right_contents">${__rating__.ratedRating}</div> -->
								</c:when>
								<c:otherwise>
									<div class="right_contents">0.0</div>
								</c:otherwise>
							</c:choose>
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
							<div class="writeList">
								<input type="hidden" class="postNum" name="postNo" value="${item.postNo}">
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
						<c:set var="counter" value="0" />

						<c:forEach var="applist" items="${__APPLIST__}" varStatus="numNo">

							<c:if test="${__AUTH__.nickName == applist.nickName}">
								<c:set var="count" value="0" />

								<div class="myGroup">
									<input type="hidden" name="groupNo" class="groupNo"
										value="${applist.groupNo}">
									<div class="area">${applist.area}</div>
									<input type="hidden" name="groupNo" value="${applist.groupNo }">
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
							
							<c:set var="count" value="${count + 1}" />
		                     <c:if test="${count == 1}">
		                        <div class="content hide">
		                           <!-- 숨겨진 내용 -->
		                           <div class="num">번호</div>
		                           <div class="group">동행이름</div>
		                           <div class="nick">닉네임</div>
		                           <div class="rateresult">
		                              <div class="rate">평점</div>
		                              <div class="result">제출</div>
		                           </div>
		
		                        </div>
		                     </c:if>
							

							<div class="content2 hide">
								<div class="num">${count}</div>
								<div class="group"></div>
								<div class="nick"></div>

								<form action="/user/rate" method="post" class="rateresult">
									<div class="rate" style="padding: 5px 0px;">
										<input type="hidden" name="raterUserNickName" value="${__AUTH__.nickName}"> 
										<input type="hidden" name="ratedUserNickName" class="ratedUserNickName" value="">

										<c:forEach begin="1" end="5" step="1" varStatus="numA">
											<c:set var="counter" value="${counter + 1}" />
											<input type="radio" id="star${counter}" class="star"
												name="rating" value="${6 - numA.index}" />
											<label for="star${counter}"> &#9733; </label>
										</c:forEach>


									</div>
									<div class="result">
										<button id="rateForm" type="submit" class="rateBtn">제출</button>
									</div>

								</form>
							</div>
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
					<c:forEach var="pin" items="${__pinList__}">
						<div class="pin">
						<input type="hidden" class="pinPostNo" value="${pin.postNo}">
						<div class="group">${pin.groupName}</div>
						<div class="title">${pin.title}</div>
						<div class="area">${pin.area}</div>
						<div class="startDate">${pin.startDate}</div>
						<div class="endDate">${pin.endDate}</div>
						<div class="status">${pin.recruitStatus}</div>
						</div>
						</c:forEach>

					</div>

					<div class="board_page">
						<c:if test="${pinPageMaker.prev}">
							<div class="Prev">
								<a href="/user/mypage?currPage=${pinPageMaker.startPage - 1}">Prev</a>
							</div>
						</c:if>

						<c:forEach var="pageNum" begin="${pinPageMaker.startPage}"
							end="${pinPageMaker.endPage}">
							<div
								class="pageNum ${pinPageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</div>
						</c:forEach>

						<c:if test="${pinPageMaker.next}">
							<div class="Next">
								<a href="/user/mypage?currPage=${pinPageMaker.endPage + 1}">Next</a>
							</div>
						</c:if>
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
	<!-- 프로필 끝-->

	

	<footer>
		<jsp:include page="../footer.jsp" />
	</footer>


</body>

</html>
