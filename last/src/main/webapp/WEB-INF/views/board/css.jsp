<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 찾는 질문</title>
<style>
  body {
    font-family: Arial, sans-serif;
    border: 7px solid blue;
  }
  .container {
    max-width: 800px;
    margin: auto;
    padding: 20px;
  }
  
  h1 {
    text-align: center;
    margin-bottom: 30px;
  }
  .faq {
    border-bottom: 1px solid #ccc;
    padding: 10px 0;
    border: 1px solid ;
  }
  .faq:last-child {
    border-bottom: none;
  }
  .faq h3 {
    cursor: pointer;
    margin-bottom: 10px;
  }
  .answer {
    display: none;
    margin-bottom: 10px;
  }
</style>
<script>
  function toggleAnswer(element) {
    var answer = element.nextElementSibling;
    answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
  }
</script>
</head>
<body>
  <div>
    <h2></h2>
  </div>

  <div class="container">
    <h1>자주 찾는 질문</h1> <hr>
    <div class="faq">
      <h3 onclick="toggleAnswer(this)">Q1: 질문 1 내용은 무엇인가요?</h3>
      <div class="answer">
        A1: 질문 1에 대한 답변입니다. 내용을 자세히 기술하세요.
      </div>
    </div>

    <div class="faq">
      <h3 onclick="toggleAnswer(this)">Q2: 질문 2 내용은 무엇인가요?</h3>
      <div class="answer">
        A2: 질문 2에 대한 답변입니다. 내용을 자세히 기술하세요.
      </div>
    </div>

    <div class="faq">
      <h3 onclick="toggleAnswer(this)">Q3: 질문 3 내용은 무엇인가요?</h3>
      <div class="answer">
        A3: 질문 3에 대한 답변입니다. 내용을 자세히 기술하세요.
      </div>
    </div>
  
    
    <div class="faq">
      <h3 onclick="toggleAnswer(this)">Q4: 질문 4 내용은 무엇인가요?</h3>
      <div class="answer">
        A4: 질문 4에 대한 답변입니다. 내용을 자세히 기술하세요.
      </div>
    </div>

    <div class="faq">
      <h3 onclick="toggleAnswer(this)">Q4: 질문 5 내용은 무엇인가요?</h3>
      <div class="answer">
        A5: 질문 5의 대한 답변입니다. 내용을 자세히 기술하세여
      </div>
    </div>
  </div>

</body>
</html>
