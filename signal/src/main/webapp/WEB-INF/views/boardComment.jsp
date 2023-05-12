<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>QnA 게시글 상세조회</title>

            <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
            <link rel="stylesheet" href="../../resources/css/style1.css">
            <link rel="stylesheet" href="../../resources/css/style2.css">
            <link rel="stylesheet" href="../../resources/css/bstyle1.css">
            <link rel="stylesheet" href="../../resources/css/bstyle2.css">
            <link rel="stylesheet" href="../../resources/css/bstyle3.css">

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

            <script>
                $(function () {

                    $('#listBtn').click(function () {
                        location = "/board/qnaList?currPage=${param.currPage}";
                    });

                    $('#modifyBtn')
                        .click(
                            function () {
                                location = "/board/qnaEdit?currPage=${param.currPage}&postno=${__BOARD__.postno}";
                            });


                }); // .jq

                // 댓글 삭제 해봄
                $(document).on('click', '.delete-comment', function () {
                    let commentno = $(this).data("comment-no");
                    let postno = "${__BOARD__.postno}";
                    $.ajax({
                        url: '/board/delete',
                        type: 'POST',
                        data: {
                            commentno: commentno,
                            postno: postno
                        },
                        success: function (result) {
                            console.log(result);
                            alert('삭제가 완료되었습니다.');
                            // 성공적으로 삭제한 후 댓글 목록을 다시 로드하거나 페이지를 새로 고침
                            location.reload();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(result);
                            // 에러 처리 로직
                            alert('댓글을 삭제하는데 실패했습니다.');
                        }
                    });
                });

                $(document).on('click', '.edit-btn', function () {
                    $(this).closest('.reply_list').find('.display-area').hide();
                    $(this).closest('.reply_list').find('.edit-area').show();
                });

                $(document).on('click', '.submit-edit-btn', function () {
                    // let newContent = $(this).siblings('.edit-content').val();
                    let commentno = $(this).data("comment-no");
                    let postno = $(this).data("post-no");
                    let content = $(this).siblings(".edit-content").val();
                 // let newContent = $(this).siblings('.edit-content').val();
                // let commentno = $(this).closest('.submit-edit-btn').data("comment-no");
                // let postno = $(this).closest('.submit-edit-btn').data("post-no");
                // let content = $(this).closest('.submit-edit-btn').data("content");

                $.ajax({
                    data: {
                        commentno: commentno,
                        postno: postno,
                        // newContent: newContent
                        content: content
                    },
                    url: '/board/edit',
                    type: 'POST',
                    success: function (result) {
                        alert('수정이 완료되었습니다.')
                        location.reload();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(result);
                        // 에러 처리 로직
                        alert('댓글을 수정하는데 실패했습니다.');
                    }
                });
                });


            </script>
        </head>

        <body>
            <h1>
                <%=request.getRequestURI()%>
            </h1>
            <hr>

            <header>
    			<jsp:include page="header.jsp" />
   			</header>

                <!-- QnA 글 보기 -->
                <div class="board_wrap">
                    <div class="board_title">
                        <strong>QnA</strong>
                    </div>
                    <div class="board_view_wrap">
                        <div class="board_view">
                            <div class="title">${ __BOARD__.title}</div>
                            <div class="info">
                                <dl>
                                    <dt>번호</dt>
                                    <dd>${__BOARD__.postno}</dd>
                                </dl>
                                <dl>
                                    <dt>작성자</dt>
                                    <dd>${__BOARD__.nickname}</dd>
                                </dl>
                                <dl>
                                    <dt>등록일</dt>
                                    <dd>${__BOARD__.regidate}</dd>
                                </dl>
                                <dl>
                                    <dt>조회수</dt>
                                    <dd>13</dd>
                                </dl>
                            </div>
                            <div class="content" readonly>${__BOARD__.content}</div>
                        </div>
                    </div>
                </div>






                <!-- Q&A 댓글 목록 -->
                <div class="reply_wrap">
                    <div class="reply_title">댓글</div>
                    <c:forEach items="${__COMMENT_LIST__}" var="comment">
                        <div class="reply_list">
                            <div>
                                <div class="nickname">${comment.nickname}</div>
                                <div class="date">${comment.regidate}</div>
                                <div class="date">${comment.modifydate}</div>


                                <div class="reply_bt_wrap" id="Clickbt">
                                    <button type="button" class="edit-btn">수정</button>
                                    <button type="submit" class="delete-comment"
                                        data-comment-no="${comment.commentno}">삭제</button>
                                    <button type="submit" onclick="location.href='#';">신고</button>
                                </div>

                                <div class="content">${comment.content}</div>
                            </div>
                            <div class="edit-area" style="display:none;">
                                <textarea class="edit-content" placeholder="수정할 내용을 입력해라"></textarea>
                                <button type="button" class="submit-edit-btn" data-comment-no="${comment.commentno}"
                                    data-post-no="${__BOARD__.postno}" data-content="${comment.content}">수정 제출</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- 댓글 작성 -->
                <form action="/board/qnaReply" method="post">
                    <input type="hidden" name="postno" value="${__BOARD__.postno}">
                    <div class="reply_write">
                        <div>
                            <div class="nickname">${__BOARD__.nickname}</div>
                            <div class="content">
                                <textarea id="content" name="content" placeholder="내용을 작성해주세요."></textarea>
                            </div>
                            <div class="writeButton">
                                <button type="submit" class="replyWriteBtn">등록</button>
                            </div>
                        </div>
                    </div>
                </form>




                <!-- bt : button -->
                <div class="bt_wrap">
                    <button type="button" id="listBtn">목록</button>
                    <button type="button" id="modifyBtn">수정</button>
                </div>



     	<footer>
      		<jsp:include page="footer.jsp" />
    	</footer>


        </body>

        </html>