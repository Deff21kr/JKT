<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>후기 상세 페이지</title>
        <link rel="stylesheet" href="../../resources/css/readreview.css">
        <script src="https://kit.fontawesome.com/e6512126dd.js" crossorigin="anonymous"></script>

        <!-- 부트스트랩 슬라이더 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
      
    </head>

    <body>
    
    	<%@include file="../../header.jsp" %>
        <main class="wrap-container">
            <section class="head">
				<p class="post-No">${ __REVIEW__.postNo }</p>
                <div class="user-info">
                    <a href="#" class="profile-img">
                        <img src="${pageContext.request.contextPath}/resources/img/profile.jpg" alt="프로필사진" />
                    </a>
                    <a href="#" class="nickname">${ __REVIEW__.nickName }</a>
                    <div class="regi-date"><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${ __REVIEW__.regiDate }"/></div>
                </div>
            </section>
            <section class="post-title">
                <div> ${ __REVIEW__.title }</div>
            </section>

            <hr>
            <div class="container">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                        <li data-target="#myCarousel" data-slide-to="4"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                    
                    </div>

                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>

                </div>
            </div>

            <section class="post-content">
                <br>
                <div class="area"> #${__REVIEW__.area } </div>
                <br>
                <p>${ __REVIEW__.content }</p>
            </section>
        </main>
        
        <div class="like-area">
                <button class="likebtn">
            	<!-- <span class="likecount"> 0 </span> -->
                <i id="like" class="fa-regular fa-heart"></i> 좋아요
                </button>
            <script>
                $('#like').click(function() {
                    $.ajax({
                        url:'${pageContext.request.contextPath}/board/review/like',
                        type: 'POST',
                        data: {
                            postNo : '${__REVIEW__.postNo}',
                            nickName : '${__AUTH__.nickName}'
                        }, // data
                        dataType : 'json',
                        success: function(result) {
                            console.log("result() invoked." + result);

                            if(result == 1) {
                                alert('좋아요를 눌렀습니다.');
                            } else {
                                alert('좋아요를 취소했습니다.');
                            } // if-else
                        },
                        error: function() {
                            console.log('실패');
                        }
                    })
                });
            </script>

       	</div>
        <div class="btn-menu">
            		<button class="listBtn"> 목록 </button>
            		<button class="modifyBtn"> 수정 </button>
       	</div>
        <!-- 댓글영역 -->
        <%@include file="../../reviewboardComment.jsp" %>
        <%@include file="../../footer.jsp" %>
        <script>
        
            $(function () {
				
            	
            	var postNo = "<c:out value='${__REVIEW__.postNo}' />";
				

                $.getJSON("/board/review/getAttachList", { postNo: postNo }, function(arr){
                    console.log(arr);

                    var str = "";
                    var isFirstImage = true;
                    var contextPath = "${pageContext.request.contextPath}";

                    $(arr).each(function(i, attach){
                        // var fileCallPath = encodeURIComponent(attach.uploadPath+"/thumb_"+attach.uuid+"_"+attach.fileName);
                        var fileCallPath = encodeURIComponent(attach.uploadPath+"/"+attach.uuid+"_"+attach.fileName);
                        var imagePath = contextPath + "/fileupload/display?fileName=" + fileCallPath;
                        
                        if (isFirstImage) {
                        str += "<div class='item active' data-path='"+attach.uploadPath+"'data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' >";
                        str += "<img src='" + imagePath + "' alt='사진"+(i+1)+"' style='width:100%;'></div>";
                        isFirstImage = false;
                        } else {
                        str += "<div class='item' data-path='"+attach.uploadPath+"'data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' >";
                        str += "<img src='" + imagePath + "' alt='사진"+(i+1)+"' style='width:100%;'></div>";
                        }//if-else
                    });//each

                    $(".carousel-inner").html(str); // 수정된 부분
                    console.log("사진추가완료");
                });//getJSON


                $('.listBtn').click(function () {
              
                    location = "/board/review/list?currPage=${param.currPage}";
                }); //onclick
                
                $('.modifyBtn').click(function () {
                	
                    location = "/board/review/modify?currPage=${param.currPage}&postNo=${ __REVIEW__.postNo }";
                }); //onclick

            }); // .jq
        </script>
        
    </body>

    </html>