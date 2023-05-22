<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  <!DOCTYPE html>
  <html lang="ko">

  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>여행시그널</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>


    <!-- style -->
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">

    <!-- fonts awesome -->
    <script src="https://kit.fontawesome.com/7235c72187.js" crossorigin="anonymous"></script>

    <link rel="shortcut icon" href="../../imgs/여행시그널-removebg-preview.png" type="image/x-icon">

	<script defer>
	    $(function () {
	        $('#cancel').click(function () {
	            location = "/board/myplan/main";
	        });
	    });
	    
        $(function () {
      	  $('form').submit(function () {
      	    $('#make').prop('disabled', true);
      	  });
      	});

		
        $(function () {
       	    $('#startDate').prop('disabled', true);
       	    $('#endDate').prop('disabled', true);
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
        padding: 20px;
      }

      a {
        text-decoration: none;
        color: black;
      }

      #sec {
        width: 1200px;
        height: 800px;
        padding: 20px;
      }

      /* 팝업창 */
      #create-planner-page {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
      }

      fieldset {
      	position: fixed;
      	margin: 0 auto;
	    left: 0;
	    right: 0;
        margin: auto;
        background: white;
        width: 450px;
        height: 390px;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }

      fieldset>h1 {
        text-align: center;
        margin-bottom: 20px;
      }

      fieldset>form {
        width: 376px;
        margin: auto;
      }

      form>div {
        margin-bottom: 20px;
      }

      label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
      }

      input[type="text"],
      input[type="date"] {
        width: 100%;
        padding: 8px;
        border-radius: 5px;
        border: 1px solid rgb(208, 207, 207);
      }

      .submit-button {
        text-align: center;
        margin-top: 20px;
      }

      #make {
        width: 100px;
        height: 30px;
        background: #4463e6;
        border-radius: 3px;
        border: 1px solid blue;
        color: white;
        cursor: pointer;
        margin-right: 15px;
      }

      #cancel {
        width: 100px;
        height: 30px;
        background: white;
        border-radius: 3px;
        border: 1px solid black;
        cursor: pointer;
      }
    </style>
  </head>

  <body>

    <%@ include file="/WEB-INF/views/header.jsp" %>

      <section id="sec">

        <fieldset>
          <h1>나만의 플래너 생성</h1>

          <form action="/board/myplan/modifyPlan" method="POST">
            <input type="hidden" name="nickName" value="${sessionScope.__AUTH__.nickName}">
            <input type="hidden" name="planNo" value="${__PLAN__.planNo }">
            <div>
              <label for="planName">플래너 이름:</label>
              <input type="text" id="planName" name="planName" value="${__PLAN__.planName}" required>
            </div>

            <div>
              <label for="startDate">여행 출발:</label>
              <input type="date" id="startDate" name="startDate" value="<fmt:formatDate value="${__PLAN__.startDate}" pattern="yyyy-MM-dd" />">
            </div>

            <div>
              <label for="endDate">여행 종료:</label>
              <input type="date" id="endDate" name="endDate" value="<fmt:formatDate value="${__PLAN__.endDate}" pattern="yyyy-MM-dd" />">
            </div>

            <div class="submit-button">
              <input type="submit" id="make" value="수정하기">
              <button type="button" id="cancel">취소</button>
            </div>

          </form>

        </fieldset>

      </section>

      <%@ include file="/WEB-INF/views/footer.jsp" %>
      

	<script defer="defer">
		document.addEventListener('DOMContentLoaded', function() {
			  var startDateInput = document.getElementById('startDate');
			  var endDateInput = document.getElementById('endDate');

			  var startDatepicker = flatpickr(startDateInput, {
			    dateFormat: 'Y-m-d', // 선택한 날짜의 형식 설정
			    minDate: 'today', // 현재 날짜부터 선택 가능하도록 설정
			    onChange: function(selectedDates, selectedDate) {
			      // 시작 날짜가 변경되면 끝나는 날짜의 최소값을 설정
			      if (selectedDate !== undefined) {
			        endDatepicker.set('minDate', selectedDate);
			      }
			    }
			  });

			  var endDatepicker = flatpickr(endDateInput, {
			    dateFormat: 'Y-m-d', // 선택한 날짜의 형식 설정
			    minDate: 'today', // 현재 날짜부터 선택 가능하도록 설정
			  });
			});
		

	</script>
  </body>

  </html>