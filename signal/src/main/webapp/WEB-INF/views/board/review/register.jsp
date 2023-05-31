<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글쓰기 페이지</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/writereview.css">
<script src="https://kit.fontawesome.com/e6512126dd.js"
	crossorigin="anonymous"></script>

<!-- jQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
	integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"
	integrity="sha512-KgffulL3mxrOsDicgQWA11O6q6oKeWcV00VxgfJw4TcM8XRQT8Df9EsrYxDf7tpVpfl3qcYD96BpyPvA4d1FDQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
	$(function() {

		$('.listBtn').click(function() {

			location = "/board/review/list?currPage=${param.currPage}";
		}); //onclick

		var formObj = $("form[name='reviewform']");
		$("button[type='submit']").on("click",function(e) {
			e.preventDefault();
			console.log("submit clicked.");

			var str = "";

			$(".uploadResult ul li").each(function(i, obj) {
				var jobj = $(obj);
				
				console.dir(jobj);

				str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+ jobj.attr("data-filename") + "'>";
				str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+ jobj.attr("data-path") + "'>";
				str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+ jobj.attr("data-uuid") + "'>";
			});

			formObj.append(str).submit();
			console.log(str);
			console.log("폼태그 제출완료!!!!");

			//폼제출후 제출버튼 비활성화
	        $("button[type='submit']").prop('disabled', true);
	       
		});
		
		

		// 파일 확장자 및 파일 크기 유효성 검사
		var regex = new RegExp("(.*?)\.(jpg|jpeg|png|gif)$");
		var maxSize = 5242880; //5MB

		function checkExtension(fileName, fileSize) {
			if (fileSize >= maxSize) {
				alert("파일 사이즈가 초과되었습니다.");
				$('#image-upload').val(''); // 사용자가 등록한 file의 value를 지움
				return false;
			}
			if (!regex.test(fileName)) {
				alert("이미지 파일만 업로드할 수 있습니다.");
				$('#image-upload').val(''); // 사용자가 등록한 file의 value를 지움
				return false;
			}
			return true;//위의 if문에 해당하지 않을시 통과
		}//checkExtension

		//업로드할 이미지 미리보기 
		function showUploadedFile(uploadResultArr) {
			if (!uploadResultArr || uploadResultArr.length == 0) {
				return false;
			}

			var uploadUL = $(".uploadResult ul");
			var str = "";

			$(uploadResultArr).each(function(i, obj) {
		
				// 파일이름 인코딩
				var fileCallPath = encodeURIComponent(obj.uploadPath+ "/thumb_"+ obj.uuid+ "_"+ obj.fileName);
				
				str += "<li data-path='" + obj.uploadPath + "'";
 				str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "'"
                str += " ><div>";
				str += "<span>" + obj.fileName + "</span>";
				str += "<button type='button' data-file=\'" + fileCallPath + "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
				str += "<img src='/fileupload/display?fileName="+ fileCallPath + "'>";
				str += "</div>";
				str += "</li>";

				var originPath = obj.uploadPath + "/"+ obj.uuid + "_" + obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g), "/");
			});//each
			// 업로드 결과가 나올 ul에 li 추가
			uploadUL.append(str);
		}//showUploadedFile

		$("input[type='file']").change(function(e) {
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;

			for (let i = 0; i < files.length; i++) {
				if (!checkExtension(files[i].name, files[i].size)) {
					return false; // 유효성 검사를 통과하지 못하면 업로드를 하지 않음
				}//파일 유효성 검사

				formData.append("uploadFile", files[i]);

				if (files.length > 5) {
					alert("파일은 최대 5개까지만 업로드할 수 있습니다.");
					$('#image-upload').val('');
					return false;
				}//파일최대갯수 검사

			}//파일추가

			$.ajax({
				url : '/fileupload/multiFileAjaxUpload',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType : 'json',
				success : function(result) {
					alert("업로드 성공");
					
					//호출 시 받은 결과값을 json데이터로 받음. -> 콘솔창 확인
					console.log(result);

					//결과값의 파일이름을 화면에 출력 
					showUploadedFile(result); 
					},
				error : function(xhr, status, error) {
					console.log(error);
					}
			});//ajax
		});//첨부파일이 추가될 시 수행

		//추가된 첨부파일 삭제처리
		$(".uploadResult").on("click", "button", function(e) {

			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			console.log(targetFile);

			var targetLi = $(this).closest("li");

			$.ajax({
				url : '/fileupload/deleteFile',
				data : {fileName : targetFile,type : type},
				dataType : 'text',
				type : 'POST',
				success : function(result) {
					alert(result);
					targetLi.remove();
				}
			});//ajax
		});//on
	}); // .jq
</script>
</head>


<body>
	<%@include file="../../header.jsp" %>

	<div class="container">
		<h1>여행후기 작성</h1>

		<!-- 파일 업로드 영역 -->
		<div class="uploadDiv">
			파일첨부란 <input id="image-upload" type="file" name="uploadFile" multiple />
			<!-- <button class="uploadBtn"> UPLOAD </button> -->
		</div>

		<!-- 첨부파일 미리보기 -->
		<div class="uploadResult">
			<ul>

			</ul>
		</div>

		<!-- 글쓰기 영역 -->
		<form name="reviewform" action="/board/review/register" method="POST">
			<div class="content-wrap">
				<input type="text" name="area" placeholder=" 어디로 여행하셨나요? (장소입력)" maxlength="10" class="area" required>			
				<input type="hidden" name="nickName" value ="${__AUTH__.nickName }" required>
				<input type="text"name="title" placeholder=" 제목을 입력하세요 " maxlength="30" class="title"required>
				<textarea class="post_content" name="content" cols="50" rows="100"
					placeholder="내용을 입력하세요"></textarea>
			</div>

			<div class="button">
				<button type="submit" class="submitBtn">작성 완료</button>
				<button type="button" class="listBtn">작성 취소</button>
			</div>
		</form>
	</div>
	
	<%@include file="../../footer.jsp" %>
</body>

</html>