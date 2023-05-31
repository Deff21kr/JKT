<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> 여행후기 게시판 목록</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reviewboard.css">
        <script src="https://kit.fontawesome.com/e6512126dd.js" crossorigin="anonymous"></script>

        <!-- jQuery -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"
            integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"
            integrity="sha512-KgffulL3mxrOsDicgQWA11O6q6oKeWcV00VxgfJw4TcM8XRQT8Df9EsrYxDf7tpVpfl3qcYD96BpyPvA4d1FDQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    </head>

    <body>
    	<%@include file="../../header.jsp" %>
    	
        <main class="container">
            <h1>여행후기 게시판</h1>

            <div class="prompt-box">
                <div class="prompt-message">
                    여행시그널 유저라면 누구나 작성 가능한 후기게시판입니다.
                    <br>
                    국내여행이면 어디든! 여행후기를 작성해주세요~
                </div>
                <button type="button" class="writeBtn">후기 작성</button>
            </div>

            <section class="boardlist">
                <ul class="boardlist">
                    <c:forEach var="boardVO" items="${__LIST__}">
                        <li>
                            <div class="post-wrap">
                                <div class="profile">
                                    <a href="#" class="profile-img">
                                        <img src="${pageContext.request.contextPath}/resources/img/profile.jpg"
                                            alt="프로필사진" />
                                    </a>
                                    <a href="#" class="nickname">${boardVO.nickName}</a>
                                    <span class="like"><i class="fa-regular fa-heart"></i> 0</span>
                                    <span class="view"><i class="fa-solid fa-eye"></i> ${boardVO.readCnt}</span>
                                </div>
                                <div class="post">
                                    <a href="/board/review/get?currPage=${pageMaker.cri.currPage}&postNo=${boardVO.postNo}"
                                        class="post-link post-link-${boardVO.postNo}"></a>
                                </div>
                                <div class=area>
                      			<i class="fa-solid fa-location-dot"> ${boardVO.area}</i>
                  				</div>
                            </div>
                        </li>

                        <script>
                            $(function () {
                                var postNo = '${boardVO.postNo}';    

                            $.ajax({
                                url: "/board/review/getAttachList",
                                type: "GET",
                                data: { postNo: postNo },
                                dataType: "json",
                                async: false, // 동기식 요청 설정
                                success: function(arr) {
                                    console.log(arr);
                                    // 받아온 JSON 데이터에 대한 처리
									
                                    var str = "";
                                    var contextPath = "${pageContext.request.contextPath}";
                                    var attach = arr[0]; // 첫번째 첨부 파일 정보 가져옴
                                    var fileCallPath = encodeURIComponent(attach.uploadPath + "/thumb_" + attach.uuid + "_" + attach.fileName);
                                    var imagePath = contextPath + "/fileupload/display?fileName=" + fileCallPath;

                                    str += "<div class='post-thumbnail' data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "'>";
                                    str += "<img src='" + imagePath + "' alt='사진1' class='postimg'></div>";

                                    $(".post-link-" + postNo).html(str); // postNo에 해당하는 클래스를 가진 요소에 내용 설정
                                    console.log("리스트 목록 사진 불러오기 완료");
                                },
                                error: function(xhr, status, error) {
                                        console.log(error);
                                }                 
                                });//ajax
                            })
                        </script>
                    </c:forEach>
                </ul>
            </section>
        </main>

        <div id="pagination">
            <ul>
                <!-- 조건문 생성 : jstl core  // 페이지네이션 공유속성 pageMaker -->
                <c:if test="${pageMaker.prev}">
                    <li class="Prev">
                        <a href="/board/list?currentPage=${pageMaker.startPage - 1}">Prev</a>
                    </li>
                </c:if>

                <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pageNum ${pageMaker.cri.currPage == pageNum ? 'current':''}">${pageNum}</li>
                </c:forEach>
                <c:if test="${pageMaker.next}">
                    <li class="Next">
                        <a href="/board/list?currPage=${pageMaker.endPage + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </div>

        <script>
            $(function () {
                $('.writeBtn').click(function () {
                    let currPage = "${pageMaker.cri.currPage}";
                    location.href = "/board/review/register?currPage=" + currPage;
                }); //onclick

                $('.pageNum').on('click', function (e) {
                    let selectedPageNum = e.currentTarget.textContent;
                    location = "/board/review/list?currPage=" + selectedPageNum;
                });
            }); // .jq
        </script>
	
		<%@include file="../../footer.jsp" %>
    </body>

    </html>