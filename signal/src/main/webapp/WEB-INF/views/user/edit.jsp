<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>마이페이지</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/mypageStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bstyle2.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<style>
.message {
	width: 100%;
	height: 6.25em;
	border: solid 1px black;
	resize: none;
}
</style>


</head>

<body>

	<header>
		<jsp:include page="../header.jsp" />
	</header>
	<div id="wrap" class="wrap">
    <div class="wrap_title"></div>
    <div id="container">
        <!-- 프로필-->
        <div id="tabs-1">
            <div class="myprofile">
                <div class="profile_left">
                    <form role="form" method="post" action="/user/edit" autocomplete="off" enctype="multipart/form-data">
                        <div class="inputArea">
                            <label for="fileName">이미지</label>
                            <input type="file" id="fileName" name="file" />
                            <div class="select_img">
                                <img src="" />
                            </div>
                            <script>
                                $("#fileName").change(function() {
                                    if (this.files && this.files[0]) {
                                        var reader = new FileReader();
                                        reader.onload = function(data) {
                                            $(".select_img img")
                                                .attr("src", data.target.result)
                                                .width(500);
                                        }
                                        reader.readAsDataURL(this.files[0]);
                                    }
                                });
                            </script>
                            <%=request.getRealPath("/") %>
                        </div>
                </div>
                
                <p></p>
                <div class="profile_right">
                    <input type="hidden" name="ID" value="${__AUTH__.ID}">
                    <div>
                        <div class="right_top">닉네임</div>
                        <div class="right_contents">${__AUTH__.nickName}</div>
                    </div>
                    <div>
                        <div class="right_top">상태메시지</div>
                        <textarea name="statusMessage" cols="30" rows="10" placeholder="상태메세지를 입력해주세요." class="message"></textarea>
                    </div>
                    <div>
                        <div class="right_top">선호여행지</div>
                        <label>
                            <select id="likeArea" name="likeArea">
                                <option value="">좋아하는 지역을 선택하세요</option>
                                <option value="서울">서울</option>
                                <option value="강원">강원</option>
                                <option value="경기">경기</option>
                                <option value="충청">충청</option>
                                <option value="경상">경상</option>
                                <option value="전라">전라</option>
                                <option value="제주">제주</option>
                            </select>
                            <br>
                        </label>
                    </div>
                    <div>
                        <div class="right_top">MBTI</div>
                        <label>
                            <select name="MBTI">
                                <option value="">MBTI유형</option>
                                <option value="ISTJ">ISTJ</option>
                                <option value="ISFJ">ISFJ</option>
                                <option value="ESTJ">ESTJ</option>
                                <option value="ESFJ">ESFJ</option>
                                <option value="ISTP">ISTP</option>
                                <option value="ISFP">ISFP</option>
                                <option value="ESFJ">ESFJ</option>
                                <option value="ESTP">ESTP</option>
                                <option value="INFJ">INFJ</option>
                                <option value="INFP">INFP</option>
                                <option value="ENFP">ENFP</option>
                                <option value="ENFJ">ENFJ</option>
                                <option value="INTJ">INTJ</option>
                                <option value="INTP">INTP</option>
                                <option value="ENTP">ENTP</option>
                                <option value="ENTJ">ENTJ</option>
                            </select>
                        </label>
                    </div>
                    <div class="bt_wrap">
                        <button type="submit" id="modifyBtn">수정</button>
                        <button type="submit" id="cancelBtn">취소</button>
                    </div>
                    
                     <script>
                            $(function() {
                                $("#modifyBtn").click(function() {
                                    window.location.assign("/user/mypage");
                                });
                            });
                            
                            $(function() {
                            	$("#cancelBtn").click(function(){
                            		// history.back();
                            		
                            		location.href="/user/mypage";
                            	})
                            })
                        </script>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


	<footer>
		<jsp:include page="../footer.jsp" />
	</footer>







</body>

</html>