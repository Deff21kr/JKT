<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>QnA 게시글 상세조회</title>

<!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style1.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle1.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle3.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

<script>
	// 댓글 삭제 
	$(document).on('click', '.delete-comment', function() {
		let commentNo = $(this).data("comment-no");
		let postNo = "${__BOARD__.postNo}";
		$.ajax({
			url : '/board/qna/delete',
			type : 'POST',
			data : {
				commentNo : commentNo,
				postNo : postNo
			},
			success : function(result) {
				console.log(result);
				alert('삭제가 완료되었습니다.');
				// 성공적으로 삭제한 후 댓글 목록을 다시 로드하거나 페이지를 새로 고침
				location.reload();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(result);
				// 에러 처리 로직
				alert('댓글을 삭제하는데 실패했습니다.');
			}
		}); // ajax
	});

	$(document).on('click', '.edit-btn', function() {
		$(this).closest('.reply_list').find('.display-area').hide();
		$(this).closest('.reply_list').find('.edit-area').show();
	});

	$(document).on('click', '.submit-edit-btn', function() {
		// let newContent = $(this).siblings('.edit-content').val();
		let commentNo = $(this).data("comment-no");
		let postNo = $(this).data("post-no");
		let content = $(this).siblings(".edit-content").val();
		// let newContent = $(this).siblings('.edit-content').val();
		// let commentno = $(this).closest('.submit-edit-btn').data("comment-no");
		// let postno = $(this).closest('.submit-edit-btn').data("post-no");
		// let content = $(this).closest('.submit-edit-btn').data("content");

		$.ajax({
			data : {
				commentNo : commentNo,
				postNo : postNo,
				// newContent: newContent
				content : content
			},
			url : '/board/qna/edit',
			type : 'POST',
			success : function(result) {
				alert('수정이 완료되었습니다.')
				location.reload();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(result);
				// 에러 처리 로직
				alert('댓글을 수정하는데 실패했습니다.');
			}
		}); // ajax

	});
</script>

</head>

<body>
	<!-- 댓글 목록 -->
	<div class="reply_wrap">
		<div class="reply_title">댓글</div>
		<c:forEach items="${__COMMENT_LIST__}" var="comment">
			<div class="reply_list">
				<div>
					<div class="nickname">${comment.nickName}</div>
					<div class="date">${comment.regiDate}</div>
					<div class="date">${comment.modifyDate}</div>

					<div class="reply_bt_wrap" id="Clickbt">
						<c:if test="${comment.nickName.equals(__AUTH__.nickName)}">
							<button type="button" class="edit-btn">수정</button>
						</c:if>
						<c:if test="${comment.nickName.equals(__AUTH__.nickName)}">
							<button type="submit" class="delete-comment"
								data-comment-no="${comment.commentNo}">삭제</button>
						</c:if>
						<button type="submit" onclick="location.href='#';">신고</button>
					</div>
					<div class="content">${comment.content}</div>
				</div>

				<div class="edit-area" style="display: none;">
					<textarea class="edit-content" placeholder="수정할 내용을 입력해라"></textarea>
					<button type="button" class="submit-edit-btn"
						data-comment-no="${comment.commentNo}"
						data-post-no="${__BOARD__.postNo}"
						data-content="${comment.content}">수정 제출</button>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- 댓글 작성 -->
	<form action="/board/qna/qnaReply" method="post">
		<input type="hidden" name="postNo" value="${__BOARD__.postNo}">
		<input type="hidden" name="nickName" value="${__AUTH__.nickName}">
		<div class="reply_write">
			<div>
				<div class="nickname">${__AUTH__.nickName}</div>
				<div class="content">
					<textarea id="content" name="content" placeholder="내용을 작성해주세요."></textarea>
				</div>
				<div class="writeButton">
					<button type="submit" class="replyWriteBtn">등록</button>
				</div>
			</div>
		</div>
	</form>

</body>

</html>