<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Document</title>
        <link rel="stylesheet" href="../resources/css/registerStyle.css">

    </head>

    <body>
        <div id="con">
            <div id="login">
                <div id="login_form"><!--로그인 폼-->

                    <form action="/register" method="post">
                        <h3 class="login" style="letter-spacing:-1px;">회원가입</h3>
                        <hr>

                        <!-- <span>ID</span> -->
                        <p style="text-align: left; font-size:12px; color:#666">ID</p>
                        <input type="text" placeholder="아이디" class="size" name="ID">
                        <p></p>
                        </label><!--아이디-->

                        <label>
                            <!-- <span>PW</span> -->
                            <p style="text-align: left; font-size:12px; color:#666">Password </p>
                            <input type="password" placeholder="비밀번호" class="size" name="password">
                        </label><!--비밀번호-->

                        <label>
                            <p style="text-align: left; font-size: 12px; color: #666;">NickName</p>
                            <input type="text" placeholder="닉네임" class="size" name="nickName">
                        </label>
                        <label>
                            <p style="text-align: left; font-size: 12px; color: #666;">Name</p>
                            <input type="text" placeholder="이름" class="size" name="name">
                        </label>


                        <tr>
                            <p style="text-align: left; font-size: 12px; color: #666;">생년월일</p>
                            <td><input type="date" class="textform" name="birthDate"/></td>
                        </tr>


                        <div>
                            <label>
                                <p style="text-align: left; font-size: 12px; color: #666;">성별선택</p>

                                <input type="radio" name="gender" value="man">남성
                                <input type="radio" name="gender" value="woman">여성
                            </label>
                        </div>

                        <div>
                            <p style="text-align: left; font-size: 12px; color: #666;">MBTI</p>
                            <select name="MBTI">
                                <option value="">MBTI유형</option>
                                <option value="관리자형">ISTJ</option>
                                <option value="관리자형">ISFJ</option>
                                <option value="관리자형">ESTJ</option>
                                <option value="관리자형">ESFJ</option>
                                <option value="탐험가형">ISTP</option>
                                <option value="탐험가형">ISFP</option>
                                <option value="탐험가형">ESFJ</option>
                                <option value="탐험가형">ESTP</option>
                                <option value="외교관형">INFJ</option>
                                <option value="외교관형">INFP</option>
                                <option value="외교관형">ENFP</option>
                                <option value="외교관형">ENFJ</option>
                                <option value="분석가형">INTJ</option>
                                <option value="분석가형">INTP</option>
                                <option value="분석가형">ENTP</option>
                                <option value="분석가형">ENTJ</option>
                            </select>
                        </div>

                        <label for="likeArea">좋아하는 지역</label>
                        <select id="likeArea" name="likeArea" required>
                            <option value="">좋아하는 지역을 선택하세요</option>
                            <option value="asia">아시아</option>
                            <option value="europe">유럽</option>
                            <option value="northamerica">북아메리카</option>
                            <option value="southamerica">남아메리카</option>
                            <option value="africa">아프리카</option>
                            <option value="oceania">오세아니아</option>
                        </select><br>

                        <label>
                            <!-- <span>PW</span> -->
                            <p style="text-align: left; font-size:12px; color:#666">Mobile Phone</p>
                            <select class="size num1">
                                <option value="1" selected>SKT</option>