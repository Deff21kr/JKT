<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<script src="https://kit.fontawesome.com/7235c72187.js" crossorigin="anonymous"></script>

				<link rel="shortcut icon" href="../../imgs/여행시그널-removebg-preview.png" type="image/x-icon">

				<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
						background-color: #f5f5f5;
						padding: 20px;
						text-align: center;
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

					.plannav {
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

					.plan.active::before,
					.plan.active::after {
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

				<%@ include file="/WEB-INF/views/header.jsp" %>


					<section id="sec">

						<div class="plannerlist">
							<p>여행기간<br>
								<fmt:formatDate value="${__MYPLAN__.startDate}" pattern="yyyy-MM-dd" /> ~
								<fmt:formatDate value="${__MYPLAN__.endDate}" pattern="yyyy-MM-dd" />
							</p>
						</div>

						<div class="plannav">
							<c:forEach begin="1" end="${__MYPLAN__.duration}" varStatus="step">
								<p>DAY ${step.index}</p>
							</c:forEach>
						</div>

						<c:forEach begin="1" end="${__MYPLAN__.duration}" varStatus="status">
							<div class="maincontent"
								style="width: 1112px; min-height: 500px; margin: auto; position: relative; border-top: 1px solid lightgrey; margin-top: 25px;">


								<div class="day-plan" style="margin: 20px 0px;">
									<div class="plantop"
										style="margin: 10px auto 10px; display: flex; justify-content: space-between; align-items: center; width: 800px; height: 65px; padding: 5px;">
										<div class="plandate">
											<strong><span>DAY ${status.index}</span></strong>
										</div>

										<button class="planwrite" onclick="location.href='/board/myplan/register?planNo=${__MYPLAN__.planNo}'">
											플래너작성
										</button>
									</div>

									<div
										style="max-width:800px; min-height: 300px; border: 1px solid; margin: auto; border-radius: 20px;">

										<div class="plan-container">
										
											<c:set var="startIndex" value="1" />
											<c:forEach var="detailVO" items="${__DETAILPLAN__}" varStatus="num">
											
											
												<c:set var="currentIndex" value="${startIndex + num.index}" />
												
											
												<c:if test="${status.index == detailVO.planDay}">
													
													<div class="plan">
														<span style="display: flex; align-items: center;">
															<i class="fas"
																style="width: 30px; height: 30px; display: flex; justify-content: center; align-items: center; border-radius: 20px; margin-right: 10px; background: #4463e6; color: #fff;">${currentIndex}</i>
															<p>
																<fmt:formatDate value="${detailVO.planTime}"
																	pattern="a hh:mm" />
															</p>
															&nbsp;:&nbsp;
															<p>${detailVO.place}</p>
														</span>

														<!-- <h3 class="plan-title">자주 묻는 질문 1</h3> -->

														<p class="plan-text">
															${detailVO.detailPlan}
														</p>

														<button class="plan-modify"
															style="position: absolute; top: 30px; right: 140px; width: 60px; height: 30px; background: #4463e6; color: #fff; border: none; border-radius: 25px;  cursor: pointer;">
															수정
														</button>

														<button class="plan-delete"
															style="position: absolute; top: 30px; right: 75px; width: 60px; height: 30px; background: #fff; color: black; border: 1px solid #9fa4a8; border-radius: 25px; cursor: pointer;">
															삭제
														</button>

														<button class="plan-toggle">
															<i class="fas fa-chevron-down"></i>
															<i class="fas fa-times"></i>
														</button>
													</div>
												</c:if>
												
												
											</c:forEach>

										</div>

									</div>

								</div>


							</div>
						</c:forEach>


					</section>

					<%@ include file="/WEB-INF/views/footer.jsp" %>


						<script>
							jQuery('.plan-toggle').on('click', function (e) {
								e.preventDefault();

								var $plan = jQuery(this).closest('.plan');

								if ($plan.hasClass('active')) {
									$plan.removeClass('active');
								}
								else {
									jQuery('.plan.active').removeClass('active');
									$plan.addClass('active');
								}
							});




        // const toggles = document.querySelectorAll(".plan-toggle");

        // toggles.forEach((toggle) => {
        //     toggle.addEventListener("click", () => {
        //         toggle.parentNode.classList.toggle("active");
        //     });
        // });


						</script>




			</body>

			</html>