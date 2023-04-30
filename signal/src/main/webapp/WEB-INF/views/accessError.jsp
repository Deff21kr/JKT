<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.*" %>


    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Document</title>

    </head>

    <body>
        <h1>Access Denied Page</h1>
        
        <h2> <c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }"/> </h2>
        <h2> <c:out value="${msg }"/> </h2>

    </body>

    </html>