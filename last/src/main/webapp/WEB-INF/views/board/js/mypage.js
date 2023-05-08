$(function() {
  let now = new Date();
  let year = now.getFullYear();
  let month = (now.getMonth() + 1) > 9 ? ""+(now.getMonth() + 1) : "0" + (now.getMonth() + 1);
  let day = (now.getDate()) > 9 ? ""+(now.getDate()) : "0"+(now.getDate());

  // 년도 selectbox 
  for(let i = 1900; i <= year; i++) {
    let yy = i;
    $("#year").append("<option value=''"+yy+">"+yy+"년</option>");
  }

  //월별 selectbox 
  for(let i = 1; i <= 12; i++) {
    let mm = i > 9 ? i : "0" + i;
    $("#month").append("<option value="+mm+">"+mm+"월</option>");
  }

  // 일별 selectbox 
  for(let i = 1; i <= 31; i++) {
    let dd = i > 9 ? i : "0"+i;
    $("#day").append("<option value="+dd+">"+dd+"일</option>");
  }

  $("#year > option[value=" + year + "]").attr("selected", true);
  $("#month > option[value=" + month + "]").attr("selected", true);
  $("#day > option[value=" + day + "]").attr("selected", true);

}); // .jq