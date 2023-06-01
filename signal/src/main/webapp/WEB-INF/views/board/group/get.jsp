<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>동행찾기 게시글 상세조회</title>

      <!-- <link rel="stylesheet" href="../../resources/css/css.css"> -->
      <link rel="stylesheet" href="../../resources/css/style1.css">
      <link rel="stylesheet" href="../../resources/css/style2.css">
      <link rel="stylesheet" href="../../resources/css/bstyle1.css">
      <link rel="stylesheet" href="../../resources/css/bstyle2.css">
      <link rel="stylesheet" href="../../resources/css/bstyle3.css">

      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
      <script src="https://kit.fontawesome.com/1d07350235.js" crossorigin="anonymous"></script>

      <script>
        var currPage = "${param.currPage}";
        var postNo = "${__BOARD__.postNo}";
        var nickName = "${__AUTH__.nickName}";

        $(function () {
          $('#listBtn')
            .click(
              function () {

                var searchParam = location.search
                var params = new URLSearchParams(searchParam);
                var getCurrPage = params.get('currPage');
                var getArea = params.get('area');
                var getStartDate = params.get('startDate');
                var getEndDate = params.get('endDate');
                var getMemberNum = params.get('memberNum');
                var getRecruitStatus = params.get('recruitStatus');
                console.log("search : " + searchParam);
                var queryParams = "?currPage=" + getCurrPage
                  + "&area=" + getArea + "&startDate="
                  + getStartDate + "&endDate=" + getEndDate
                  + "&memberNum=" + getMemberNum
                  + "&recruitStatus=" + getRecruitStatus;
                console.log(getCurrPage);
                console.log(getArea);
                console.log(getStartDate);
                console.log(getEndDate);
                console.log(getMemberNum);
                console.log(getRecruitStatus);

                if (searchParam.startsWith("?postNo=")) {
                  location.href = "/board/group/list";
                } else if (searchParam.startsWith("?currPage=")) {
                  location.href = "/board/group/list?currPage="
                    + getCurrPage;
                } else {
                  location.href = "/board/group/searchList"
                    + queryParams;
                }
              });

          $('#modifyBtn').click(
            function () {
              location.href = "/board/group/modify?currPage=" + currPage
                + "&postNo=" + postNo;
            });

          $('#applyBtn')
            .click(
              function () {
                $
                  .ajax({
                    url: '${pageContext.request.contextPath}/user/mygroup/register',
                    type: 'post',
                    data: {
                      nickName: nickName,
                      postNo: postNo,
                      currPage: currPage
                    },
                    dataType: 'json',
                    success: function (data) {
                      var model = parseInt(data);
                      console.log("1 = 중복o / 0 = 중복x : "
                        + model);

                      if (model == 1) { // id 이미 있음
                        console.log('Data 1:', model);
                        console.log(model);
                        console.log(typeof (model));
                        alert('이미 등록된 ID입니다.');

                      } else {
                        console.log('Data 2:', model);
                        console.log(model);
                        console.log(typeof (model));
                        alert('신청이 완료되었습니다.');
                      }
                    },

                    error: function () {
                      console.log("실패");
                    }

                  });

                $('#modifyBtn')
                  .click(
                    function () {
                      n
                      location = "/board/group/modify?currPage=${param.currPage}&postNo=${__BOARD__.postNo}";
                    });

                $('#applyBtn')
                  .click(
                    function () {

                      $
                        .ajax({
                          url: '${pageContext.request.contextPath}/user/mygroup/register',
                          type: 'post',
                          data: {
                            nickName: nickName,
                            postNo: postNo,
                            currPage: currPage
                          },
                          dataType: 'json',
                          success: function (
                            data) {
                            var model = parseInt(data);
                            console
                              .log("1 = 중복o / 0 = 중복x : "
                                + model);

                            if (model == 1) { // id 이미 있음
                              console
                                .log(
                                  'Data 1:',
                                  model);
                              console
                                .log(model);
                              console
                                .log(typeof (model));
                              alert('이미 등록된 ID입니다.');

                            } else {
                              console
                                .log(
                                  'Data 2:',
                                  model);
                              console
                                .log(model);
                              console
                                .log(typeof (model));
                              alert('신청이 완료되었습니다.');
                            }
                          },

                          error: function () {
                            console
                              .log("실패");
                          }
                        });

                    });
              });
        });
      </script>

    </head>

    <body>
      <%@include file="../../header.jsp" %>

        <!-- 게시글 보기 -->
        <div class="board_wrap">
          <div class="board_title">
            <strong>동행찾기</strong>
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
                  <dd>${__BOARD__.readCnt}</dd>
                </dl>
                <!-- 찜 -->
                <div class="pin">찜
                  <i id="pinBtn" class="fa-regular fa-thumbs-up"></i>
                  <!-- <button type="button" id="pinBtn">찜</button> -->
                </div>
                <script>
                  $('#pinBtn')
                    .click(
                      function () {
                        $
                          .ajax({
                            url: '${pageContext.request.contextPath}/board/group/pin',
                            type: 'POST',
                            data: {
                              postNo: '${__BOARD__.postNo}',
                              nickName: '${__AUTH__.nickName}'
                            }, // data
                            dataType: 'json',
                            success: function (
                              result) {
                              console
                                .log("result()invoked."
                                  + result);

                              if (result == 1) {
                                $('#pinBtn')
                                  .addClass(
                                    'active');
                                alert('게시물을 찜했습니다.');
                              } else {
                                $('#pinBtn')
                                  .removeClass(
                                    'active');
                                alert('찜을 취소했습니다.');
                              }
                            },
                            error: function () {
                              console.log('실패');
                            }
                          })
                      });
                </script>
              </div>
              <div class="content" readonly>${__BOARD__.content}</div>
            </div>

            <!-- 댓글 목록 -->
            <%@include file="../../boardComment2.jsp" %>


              <!-- bt : button -->
              <div class="bt_wrap">
                <c:if test="${not __BOARD__.nickName.equals(__AUTH__.nickName)}">
                  <button type="button" id="applyBtn">신청</button>
                </c:if>

                <button type="button" id="listBtn">목록</button>

                <c:if test="${__BOARD__.nickName.equals(__AUTH__.nickName)}">
                  <button type="button" id="modifyBtn">수정</button>
                </c:if>
              </div>
          </div>
        </div>

        <%@include file="../../footer.jsp" %>

    </body>

    </html>