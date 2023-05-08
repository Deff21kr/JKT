<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


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
                    $('#modifyBtn').click(function () {
                        location = "/board/qnaEdit?currPage=${param.currPage}&postNo=${__BOARD__.postNo}";
                    });
                });

                $(document).ready(function () {
                    loadComments();
                });

                function loadComments() {
                    $.ajax({
                        url: "/board/comments",
                        type: "GET",
                        data: {
                            postNo: "${__BOARD__.postNo}"
                        },
                        success: function (response) {
                            let commentHtml = "";
                            $.each(response, function (index, comment) {
                                commentHtml += `
                <div>
                    <div class="nickname">${comment.nickName}</div>
                    <div class="date">(${comment.regiDate})</div>
                    <div class="reply_bt_wrap" id="Clickbt">
                        <button type="submit" onclick="location.href='/board/comments/edit?commentNo=${comment.commentNo}&postNo=${__BOARD__.postNo}';">수정</button>
                        <button type="submit" onclick="location.href='/board/comments/delete?commentNo=${comment.commentNo}&postNo=${__BOARD__.postNo}';">삭제</button>
                        <button type="submit" onclick="location.href='/board/comments/report?commentNo=${comment.commentNo}&postNo=${__BOARD__.postNo}';">신고</button>
                    </div>
                    <div class="content">${comment.content}</div>
                </div>`;
                            });
                            $(".reply_list").html(commentHtml);
                        },
                        error: function () {
                            alert("댓글 목록을 가져오는 데 실패하였습니다. 다시 시도해주세요.");
                        }
                    });
                }
            </script>
        </head>

        <body>
            <%@include file="../header.jsp" %>

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
                                    <dd>${__BOARD__.postNo}</dd>
                                </dl>
                                <dl>
                                    <dt>작성자</dt>
                                    <dd>${__BOARD__.nickName}</dd>
                                </dl>
                                <dl>
                                    <dt>등록일</dt>
                                    <dd>${__BOARD__.regiDate}</dd>
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
                    <form action="/board/comments" method="post">
                        <input type="hidden" name="nickname" value="${ __COMMENT_LIST__.nickName }"> 
                    </form>
                        <div class="reply_title">댓글</div>
                        <div class="reply_list">
                            <c:forEach var="comment" items="${__COMMENT_LIST__}">
                                <div>
                                    <div class="nickname">${comment.nickName}</div>
                                    <div class="date">${comment.regiDate}</div>
                                    <div class="reply_bt_wrap" id="Clickbt">
                                        <button type="submit"
                                            onclick="location.href='/board/comments/edit?commentNo=${__COMMENT_LIST__.commentNo}&postNo=${__BOARD__.postNo}';">수정</button>
                                        <button type="submit"
                                            onclick="location.href='/board/comments/delete?commentNo=${__COMMENT_LIST__.commentNo}&postNo=${__BOARD__.postNo}';">삭제</button>
                                        <button type="submit"
                                            onclick="location.href='/board/comments/report?commentNo=${__COMMENT_LIST__.commentNo}&postNo=${__BOARD__.postNo}';">신고</button>
                                    </div>
                                    <div class="content">${comment.content}</div>
                                </div>
                            </c:forEach>
                        </div>
                </div>


                <!-- 댓글 작성 -->
                <div class="reply_write">
                    <div>
                        <div class="nickname">${__COMMENT_LIST__.nickName}</div>
                        <div class="content">
                            <textarea id="commentContent" placeholder="내용을 작성해주세요."></textarea>
                        </div>
                        <div class="writeButton">
                            <button type="button" onclick="submitComment();">등록</button>
                        </div>
                    </div>
                </div>


                <!-- bt : button -->
                <div class="bt_wrap">
                    <button type="button" id="listBtn">목록</button>
                    <button type="button" id="modifyBtn">수정</button>
                </div>
                
                

                <%@include file="../footer.jsp" %>

                    <script>
                        function submitComment() {
                            var commentContent = $("#commentContent").val().trim();
                            if (commentContent === "") {
                                alert("댓글 내용을 입력해주세요.");
                                return;
                            }

                            $.ajax({
                                url: "/board/comments/insert",
                                type: "POST",
                                data: {
                                    postNo: "${__COMMENT_LIST__.postNo}",
                                    content: commentContent
                                },
                                success: function (response) {
                                    if (response.result === "success") {
                                        location.reload();
                                    } else {
                                        alert("댓글 등록에 실패하였습니다. 다시 시도해주세요.");
                                    }
                                },
                                error: function () {
                                    alert("서버와의 통신에 실패하였습니다. 다시 시도해주세요.");
                                }
                            });
                        }
                    </script>

        </body>

        </html>