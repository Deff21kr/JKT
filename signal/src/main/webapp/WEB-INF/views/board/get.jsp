<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<%@ include file='CommonFavicon.jsp' %>

    <title>게시물 상세조회</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

    <script>
        $(function () {
            $('#listBtn').click(function () {
                location = "/board/list?currPage=${param.currPage}";
                // location.href = "/board/register";
            });
            $('#modifyBtn').click(function () {
                location = "/board/modify?currPage=${param.currPage}&bno=${__BOARD__.bno}";
            });
        });
    </script>

    <style>
        #wrapper {
            width: 1024px;
            font-family: D2Coding;
            margin: 0 auto;
            font-size: 16px;
        }
        
        table {
            width: 80%;
            font-weight: bold;
        }

        textarea {
        	font-size: 13px;
        }

        #listBtn, #modifyBtn {
            width: 80px;
            height: 30px;
            border: 0;
            font-size: 0;
            font-weight: bold;
            text-align: center;
            cursor: pointer;
        }
        #modifyBtn{
            margin-left: 50px;
            background: palevioletred;
            font-size: 15px;
        }

        #listBtn {
            background: skyblue;
            font-size: 15px;
        }

    </style>

</head>

<body>
    <%@ include file='CommonInvoke.jsp' %>

    <div id="wrapper">
        <table>
            <tr>
                <td>글번호</td>
                <td>${ __BOARD__.bno}</td>
            </tr>
            <tr>
                <td>작성자</td>
               	<td>${ __BOARD__.writer}</td>
            </tr>
            <tr>
                <td>제목</td>
                <td>${ __BOARD__.title}</td>
            </tr>
            <tr>
                <td>최초등록일</td>
                <td>${ __BOARD__.insertTs}</td>
            </tr>
            <tr>
                <td>최초수정일</td>
                <td>${ __BOARD__.updateTs}</td>
            </tr>
            <tr>
                <td>내용</td>
                <td><textarea cols="60" rows="10" readonly>${ __BOARD__.content}</textarea></td>
            </tr>
            <tr class="buttons">
                <td colspan="2">
                    <button type="button" id="modifyBtn">MODIFY</button>
                    <button type="button" id="listBtn">LIST</button>
                </td>
            </tr>
        </table>
    </div>

</body>

</html>