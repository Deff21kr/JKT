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


<!-- style -->
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/footer.css">
<!-- <script src="js/js.js" defer></script> -->

<!-- fonts awesome -->
<script src="https://kit.fontawesome.com/7235c72187.js"
	crossorigin="anonymous"></script>

<link rel="shortcut icon" href="../../imgs/여행시그널-removebg-preview.png"
	type="image/x-icon">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

<script type="text/javascript">
// 플래너 삭제
function remove() {

} // remove

// 플래너 수정
$(function () {
    $('#modify').click(function () {
        location.href = "/board/myplan/modifyPlan?planNo=${__MYPLAN__.planNo}";
    });
    
    $('#remove').click(function () {
    	  if (confirm("플래너를 삭제하시겠습니까?")) {
    		    var form = $("<form>");
    		    form.attr("action", "/board/myplan/deletePlan");
    		    form.attr("method", "POST");
    		    form.append("<input type='hidden' name='planNo' value='" + ${__MYPLAN__.planNo} + "'>");
    		    form.appendTo("body").submit();

    		    alert("플래너가 삭제되었습니다.");
    		  } // if
    });    
});


</script>

<style>
/* 전체 스타일 */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

/* 섹션 스타일 */
#sec {
	padding: 20px;
}

.plannerlist {
	position: relative;
	background-color: #f5f5f5;
	padding: 20px;
	text-align: center;
	background-color: #f5f5f5;
}

.temp {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.datebtn {
	margin-right: 30px;
	border: 1px solid black;
	border-radius: 10px;
	padding: 5px;
}

.maincontent {
	background-color: #fff;
	padding: 20px;
	margin-top: 25px;
	border-top: 1px solid lightgrey;
	position: relative;
}

/* .plannav {
	display: flex;
	justify-content: center;
	margin: 20px 0px;
}

.plannav p {
	margin: 0 10px;
	padding: 5px 10px;
	background-color: #4463e6;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
} */
.dml>button {
	background: #4463e6;
	margin-left: 5px;
	width: 50px;
	height: 30px;
	color: #fff;
	border: 0px solid;
	cursor: pointer;
}



.plannav {
	z-index: 1;
	position: fixed;
	top: 50%;
	left: 30px;
	transform: translateY(-50%);
	background-color: #f8f8f8;
	padding: 10px;
	border-radius: 10px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	width: 100px;
	text-align: center;
}

.plannav p {
	margin: 3px 0px;
	padding: 8px;
	cursor: pointer;
	font-size: 14px;
	color: #333;
	transition: background-color 0.3s ease;
}

.plannav p:hover {
	background-color: #e0e0e0;
}

.plannav p.active {
	background-color: #ccc;
	color: #fff;
}

p.quickmenu.on {
	color: #fff;
	background: #a3b7cd;
}

.day-plan {
	margin: 20px 0;
}

.plantop {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 800px;
	height: 65px;
	padding: 5px;
	margin: 10px auto;
}

.plandate strong {
	font-size: 20px;
}

.planwrite {
	width: 90px;
	background-color: #4463e6;
	color: #fff;
	border: none;
	border-radius: 25px;
	cursor: pointer;
	height: 40px;
}

.plan-container {
	max-width: 600px;
	margin: 0 auto;
}

.plan {
	background-color: aliceblue;
	border: 1px solid #9fa4a8;
	border-radius: 10px;
	margin: 20px 0;
	padding: 30px;
	position: relative;
	overflow: hidden;
	transition: 0.3s ease;
	border: 2px outset;
}

.plan:hover {
	box-shadow: 0px 3px 6px cadetblue;
}

.plan.active {
	background-color: #fff;
	box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1), 0 3px 6px rgba(0, 0, 0, 0.1);
	border: 2px outset;
}

.plan.active::before, .plan.active::after {
	content: url(img/travelsignal2.png);
	font-size: 7rem;
	position: absolute;
	opacity: 0.06;
	/* top: 20px; */
	left: 0;
	right: 0;
	text-align: center;
	z-index: 0;
	margin: 0 auto;
	padding: 1px;
}

.plan-title {
	margin: 0 35px 0 0;
}

.plan-text {
	display: none;
	margin: 25px 0 0;
	word-wrap: break-word;
}

.plan.active .plan-text {
	display: block;
}

.plan-toggle {
	background-color: transparent;
	border: 2px solid black;
	border-radius: 50%;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
	padding: 0;
	position: absolute;
	top: 30px;
	right: 30px;
	height: 30px;
	width: 30px;
	border: 1px outset;
}

.plan-toggle:focus {
	outline: 0;
}

.plan-toggle .fa-times {
	display: none;
}

.plan.active .plan-toggle .fa-times {
	color: #fff;
	display: block;
}

.plan.active .plan-toggle .fa-chevron-down {
	display: none;
}

.plan.active .plan-toggle {
	background-color: #9fa4a8;
}

.plan.active .plan-text {
	border-top: 1px solid;
	padding: 15px;
}
</style>

</head>

<body>

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<%-- 본인이 작성한 플래너가 아니면 플래너 메인으로 이동 --%>
	<c:if test="${sessionScope.__AUTH__.nickName != __MYPLAN__.nickName}">
		<script>
			window.location.href = "http://localhost:8080/board/myplan/main";
		</script>
	</c:if>

	<section id="sec">

		<div class="plannerlist">
			<p style="font-size: 13px; font-weight: bold;">
				여행기간<br>
				<%-- 본인이 작성한 플래너만 볼수있게 --%>
				<c:if
					test="${sessionScope.__AUTH__.nickName == __MYPLAN__.nickName}">
					<fmt:formatDate value="${__MYPLAN__.startDate}"
						pattern="yyyy-MM-dd" /> ~
					<fmt:formatDate value="${__MYPLAN__.endDate}"
						pattern="yyyy-MM-dd" />
				</c:if>
			</p>

			<div class="dml" style="position: absolute; top: 20px; right: 180px;">
				<button id="modify" type="button" >수정</button>
				<button id="remove" type="button" >삭제</button>
			</div>
		</div>


		<div class="plannav">
			<%-- 본인이 작성한 플래너만 볼수있게 --%>
			<c:if test="${sessionScope.__AUTH__.nickName == __MYPLAN__.nickName}">
				<p class="quicktop">
					<i class="fas fa-chevron-up"></i>
				</p>
				<hr style="width:80px" margin="3px 0px">
				<c:forEach begin="1" end="${__MYPLAN__.duration}" varStatus="step">
					<p class="quickmenu" data-day="${step.index}">DAY ${step.index}</p>
				</c:forEach>
				<hr style="width:80px" margin="3px 0px">
				<p class="quickbottom">
					<i class="fas fa-chevron-down"></i>
				</p>
			</c:if>
		</div>



		<c:if test="${sessionScope.__AUTH__.nickName == __MYPLAN__.nickName}">



			<c:forEach begin="1" end="${__MYPLAN__.duration}" varStatus="status">
				<div class="maincontent day${status.index}"
					style="width: 1112px; min-height: 500px; margin: auto; position: relative; border-top: 1px solid lightgrey; margin-top: 25px;">


					<div class="day-plan" style="margin: 20px 0px;">
						<div class="plantop"
							style="margin: 10px auto 10px; display: flex; justify-content: space-between; align-items: center; width: 800px; height: 65px; padding: 5px;">
							<div class="plandate">
								<strong><span>DAY ${status.index}</span></strong>
							</div>

							<button class="planwrite"
								onclick="location.href='/board/myplan/register?planNo=${__MYPLAN__.planNo}&planDay=${status.index}'">
								플래너작성</button>
						</div>

						<div
							style="max-width: 800px; min-height: 300px; border: 1px solid; margin: auto; border-radius: 20px;">

							<div class="plan-container">

								<c:set var="startIndex" value="1" />
								<c:forEach var="detailVO" items="${__DETAILPLAN__}"
									varStatus="num">


									<c:set var="currentIndex" value="${startIndex + num.index}" />


									<c:if test="${status.index == detailVO.planDay}">

										<div class="plan">
											<span style="display: flex; align-items: center;"> <i
												class="fas"
												style="width: 30px; height: 30px; display: flex; justify-content: center; align-items: center; border-radius: 20px; margin-right: 10px; background: #4463e6; color: #fff;">${currentIndex}</i>
												<p>
													<fmt:formatDate value="${detailVO.planTime}"
														pattern="a hh:mm" />
												</p> &nbsp;:&nbsp;
												<p>${detailVO.place}</p>
											</span>

											<!-- <h3 class="plan-title">자주 묻는 질문 1</h3> -->

											<p class="plan-text">${detailVO.detailPlan}</p>

											<button class="plan-modify"
												onclick="modifyBtn(${detailVO.detailPlanNo})"
												style="position: absolute; top: 30px; right: 140px; width: 60px; height: 30px; background: #4463e6; color: #fff; border: none; border-radius: 25px; cursor: pointer;">
												수정</button>


											<button type="button" class="plan-delete"
												onclick="removeBtn(${detailVO.detailPlanNo})"
												style="position: absolute; top: 30px; right: 75px; width: 60px; height: 30px; background: #fff; color: black; border: 1px solid #9fa4a8; border-radius: 25px; cursor: pointer;">
												삭제</button>


											<button class="plan-toggle">
												<i class="fas fa-chevron-down"></i> <i class="fas fa-times"></i>
											</button>
										</div>
									</c:if>

								</c:forEach>

							</div>

						</div>

					</div>


				</div>
			</c:forEach>

		</c:if>


	</section>

	<%@ include file="/WEB-INF/views/footer.jsp"%>


	<script defer="defer">
		jQuery('.plan-toggle').on('click', function(e) {
			e.preventDefault();

			var $plan = jQuery(this).closest('.plan');

			if ($plan.hasClass('active')) {
				$plan.removeClass('active');
			} else {
				jQuery('.plan.active').removeClass('active');
				$plan.addClass('active');
			}
		});

		
		
		// 상세계획 삭제
		  function removeBtn(detailPlanNo) {
		    if (confirm("계획을 삭제하시겠습니까?")) {
		      var form = $("<form>");
		      form.attr("action", "/board/myplan/remove");
		      form.attr("method", "POST");
		      form.append("<input type='hidden' name='detailPlanNo' value='" + detailPlanNo + "'>");
		      form.append("<input type='hidden' name='planNo' value='" + ${__MYPLAN__.planNo} + "'>");
		      form.appendTo("body").submit();
		
		      alert("계획이 삭제되었습니다.");
		    } // if
		  } // removeBtn

		  // 상세계획 수정
		  function modifyBtn(detailPlanNo) {
			  if(confirm("계획을 수정하시겠습니까?")) {
			    location.href = "/board/myplan/modify?planNo=${__MYPLAN__.planNo}&detailPlanNo=" + detailPlanNo;
			  } // if
			} // function

			
			// 퀵메뉴 스크롤 이동
			const menuItems = document.querySelectorAll(".quickmenu");

			menuItems.forEach(function(item) {
			  item.addEventListener("click", function() {
			    const day = this.getAttribute("data-day");
			    const targetElement = document.querySelector(`.maincontent.day`+day);
			    
			    menuItems.forEach(function(item) {
			      item.classList.remove("on");
			    });
			    
			    this.classList.add("on");
			    targetElement.scrollIntoView({ behavior: "smooth", block: "center" });

			  });
			});
			
			// 스크롤 이동시마다 퀵메뉴 day 변경
			window.addEventListener("scroll", function() {
				  const scrollPosition = window.scrollY;
				  
				  menuItems.forEach(function(item) {
				    const day = item.getAttribute("data-day");
				    const targetElement = document.querySelector(`.maincontent.day`+day);
				    
				    if (targetElement.offsetTop <= scrollPosition + window.innerHeight / 2 &&
				        targetElement.offsetTop + targetElement.offsetHeight > scrollPosition + window.innerHeight / 2) {
				      menuItems.forEach(function(item) {
				        item.classList.remove("on");
				      });
				      
				      item.classList.add("on");
				    }
				  });
				});
			
			// 맨 위로 이동
			$('.quicktop').click(function(){
				  $('html, body').animate({scrollTop: 0}, 'slow');
			})
			
			// 맨 아래로 이동
			$('.quickbottom').click(function(){
				$('html, body').animate({scrollTop: $(document).height()}, 'slow');
			})

			


			


	</script>




</body>

</html>