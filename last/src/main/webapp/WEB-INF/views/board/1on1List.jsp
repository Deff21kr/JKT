<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>1:1 문의 게시판</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>1:1 문의 게시판</h1>
    <table>
        <thead>
            <tr>
                <th>게시글번호</th>
                <th>닉네임</th>
                <th>제목</th>
                <th>내용</th>
                <th>등록일</th>
                <th>수정일</th>
            </tr>
        </thead>
        <tbody>
            <%-- 게시글 데이터 반복 출력 --%>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td>${post.postNo}</td>
                    <td>${post.nickname}</td>
                    <td>${post.title}</td>
                    <td>${post.content}</td>
                    <td>${post.regiDate}</td>
                    <td>${post.modifyDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
