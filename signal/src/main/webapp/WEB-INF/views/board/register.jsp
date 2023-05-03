<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<%@ include file='CommonFavicon.jsp' %>

    <title>게시물 수정</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

    <script>
        $(function () {
            $('#listBtn').click(function () {
            	location = "/board/list?currPage=${param.currPage}";
                // location.href = "/board/register";
            }); 
            
        });
    </script>

<style>
    #wrapper {
        width: 1024px;

        margin: 0 auto;

        font-family: D2Coding;
        font-size: 16px;
    }
    table {
        width: 70%;

        font-weight: bold;
    }
    textarea {
        font-size: 14px;
    }
    #submitBtn {
        background-color: red;
    }
    #listBtn {
        background-color: greenyellow;
    }
    .botton {
        padding: 10px;
        text-align: center;
    }
    #listBtn, #submitBtn, {
        width: 80px;
        height: 30px;

        border: 0;

        font-size: 15px;
        font-weight: bold;

        color: white;

        cursor: pointer;
    }

</style>

</head>

<body>
    <%@ include file='CommonInvoke.jsp' %>

    <div id="wrapper">
        <form action="/board/register" method="POST">
            <table>
                <tr>
                    <td>작성자</td>
                    <!-- 필수 입력사항 : required -->
                    <td><input type="text" name="writer" required></td>
                </tr>
                <!-- 수정화면에서 출력O 수정 가능, type : text -->
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${ __BOARD__.title}" size="70" required></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td><textarea cols="60" rows="10" name="content" required>${ __BOARD__.content}</textarea></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit" id="submitBtn">SUBMIT</button>
                        <button type="button" id="listBtn">LIST</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>

</html>