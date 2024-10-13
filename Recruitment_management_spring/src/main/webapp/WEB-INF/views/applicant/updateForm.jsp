<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>지원자 수정</title>
    <link href="/css/updateStyle.css?after" rel="stylesheet">    
</head>
<body>
    <div class="container">
        <h1>지원자 수정</h1>
		<form action="/applicant/modify" method="post">
            <input type="hidden" name="a_no" value="${applicantVO.a_no}"/>
            <label for="a_name">지원자 성명:</label>
            <input type="text" id="a_name" name="a_name" value="${applicantVO.a_name}" required>
            <label for="d_score">서류전형 원점수 (0-100):</label>
            <input type="number" id="d_score" name="d_score" value="${applicantVO.d_score}" required min="0" max="100" step="1" onchange="calculateTotalScore()">
            <label for="w_score">필기전형 원점수 (0-100):</label>
            <input type="number" id="w_score" name="w_score" value="${applicantVO.w_score}" required min="0" max="100" step="1" onchange="calculateTotalScore()">
            <label for="i1_score">면접관1 점수 (0-100):</label>
            <input type="number" id="i1_score" name="i1_score" value="${applicantVO.i1_score}" required min="0" max="100" step="1" onchange="calculateTotalScore()">
            <label for="i2_score">면접관2 점수 (0-100):</label>
            <input type="number" id="i2_score" name="i2_score" value="${applicantVO.i2_score}" required min="0" max="100" step="1" onchange="calculateTotalScore()">
            <label for="i3_score">면접관3 점수 (0-100):</label>
            <input type="number" id="i3_score" name="i3_score" value="${applicantVO.i3_score}" required min="0" max="100" step="1" onchange="calculateTotalScore()">
            <label for="total_score">총 점수:</label>
            <input type="number" id="total_score" name="total_score" value="0" readonly>
            <button type="submit">저장</button>
        </form>
        <br>
        <a href="/applicant/list">목록으로 돌아가기</a> 
    </div>
</body>
    <script>
        function calculateTotalScore() {
            const dScore = parseInt(document.getElementById('d_score').value) || 0; // 서류 점수
            const wScore = parseInt(document.getElementById('w_score').value) || 0; // 필기 점수
            const i1Score = parseInt(document.getElementById('i1_score').value) || 0; // 면접관 1 점수
            const i2Score = parseInt(document.getElementById('i2_score').value) || 0; // 면접관 2 점수
            const i3Score = parseInt(document.getElementById('i3_score').value) || 0; // 면접관 3 점수
            const interviewAverage = (i1Score + i2Score + i3Score) / 3;
            const totalScore = (dScore * 0.2) + (wScore * 0.4) + (interviewAverage * 0.4);
            
            document.getElementById('total_score').value = totalScore.toFixed(2); 
        }
    </script>
</html>
