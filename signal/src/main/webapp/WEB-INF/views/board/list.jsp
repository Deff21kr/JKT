<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<%@ include file='CommonFavicon.jsp' %>

    <title>게시판 전체목록 조회</title>

    <style>
        * {
            margin:  0, auto;
            padding: 0;
        }

        #wrapper {
            width: 1024px;
            font-family: D2Coding;
            font-size: 16px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px ridge green;
            text-align: center;
        }
        
        th {
            padding: 10px;
            color: white;
            background-color: purple;
            font-size: 18px;
        }
        
        td {
            padding: 5px;
        }
        
        caption {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: bold;
        }

        td:nth-of-type(2) {
            width: 40%;
            padding-left: 7px;
            text-align: left;
            font-weight: bold;
        }

        h1 {
           padding:  20px; 
        }

        tr:hover {
            background: bisque;
        }
        #regBtn {
            width: 150px;
            height: 40px;
            margin-top: 10px;
            border: 1px solid gray;
            font-size: 17px;
            font-weight: bold;
            background: red;
            color: white;
            cursor: pointer;
        }
        a, a:link, a:visited {
            text-decoration: none;
            color: black;
            cursor: pointer;
        }

        #pagination ul {
            float: right;
            list-style: none;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }

        #pagination ul > li {
            float: left;
            width: 40px;
            height: 30px;
            /* border: 1px solid purple; */
            /* 행 높이 맞춤 */
            line-height: 30px;  
            cursor: pointer;
        }

        .Prev, .Next {
            width: 70px !important;
            color: white;
            background-color: blueviolet;
        }

        .current {
            border: 2px solid red;
        }

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>

    <script>
        $(function () {
            $('#regBtn').click(function () {
                console.clear();
                let currPage = "${pageMaker.cri.currPage}";
                location = "/board/register?currPage="+currPage;
                // location.href = "/board/register";
            });

            $('.pageNum').on('click', function (e) {
                let selectedPageNum = e.currentTarget.textContent;
                location.href = "/board/list?currPage="+selectedPageNum;
            });
        });
    </script>
</head>

<body>
    <%@ include file='CommonInvoke.jsp' %>
    <div id="wrapper">

        <h3>${result}</h3>

        <button type="button" id="regBtn">REGISTER</button>

        <table border="1">
            <caption>tbl_board</caption>
            <thead>
                <tr>
                    <th>bno</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>insertTs</th>
                    <th>updateTs</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach var="boardVO" items="${__LIST__}">
                    <tr>
                        <td>${boardVO.bno}</td>
                        <td><a href="/board/get?currPage=${pageMaker.cri.currPage}&bno=${boardVO.bno}">${boardVO.title}</a></td>
                        <td>${boardVO.writer}</td>
                        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${boardVO.insertTs}" /></td>
                        <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${boardVO.updateTs}" /></td>
                    </tr>
                </c:forEach>
            </tbody>

            <tfoot></tfoot>
        </table>
        <p>&nbsp;</p>

        <div id="pagination">
            <ul>
                <c:if test="${pageMaker.prev}">
                    <li class="Prev"><a href="/board/list?currPage=${pageMaker.startPage - 1}">Prev</a></li>
                </c:if>
                <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pageNum ${pageMaker.cri.currPage == pageNum? 'current':''}">${pageNum}</li>
                    <!-- <li>${pageNum}</li> -->
                </c:forEach>
                <c:if test="${pageMaker.next}">
                    <li class="Next"><a href="/board/list?currPage=${pageMaker.endPage + 1}">Next</a></li>
                </c:if>
            </ul>
        </div>

    </div>

</body>

</html>