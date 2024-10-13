<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신규 입력</title>
    <link href="/css/inputStyle.css?after" rel="stylesheet">        
</head>
<body>
    <div class="container">
        <h1>신규 지원자 입력</h1>
        <form action="/applicant/write" method="post">
            <label for="a_name">지원자 성명:</label>
            <input type="text" id="a_name" name="a_name" required /> 
            <label for="d_score">서류전형 원점수 (0-100):</label>
            <input type="number" id="d_score" name="d_score" min="0" max="100" required />   
            <label for="w_score">필기전형 원점수 (0-100):</label>
            <input type="number" id="w_score" name="w_score" min="0" max="100" required />  
            <label for="i1_score">면접관1 점수 (0-100):</label>
            <input type="number" id="i1_score" name="i1_score" min="0" max="100" required />   
            <label for="i2_score">면접관2 점수 (0-100):</label>
            <input type="number" id="i2_score" name="i2_score" min="0" max="100" required />    
            <label for="i3_score">면접관3 점수 (0-100):</label>
            <input type="number" id="i3_score" name="i3_score" min="0" max="100" required />    
            <button type="submit">저장</button>
        </form>      
        <br>
        <a href="/applicant/list">목록으로 돌아가기</a>
    </div>
</body>
</html>

