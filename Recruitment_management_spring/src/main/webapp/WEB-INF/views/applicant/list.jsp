<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용관리 프로그램</title>
<link href="/css/listStyle.css?after" rel="stylesheet">    
</head>
<body>
    <div class="table-container"> 
        <div class="header">
            <h1>채용관리 프로그램</h1>
            <button type="button" onclick="redirectToInputForm()">신규입력</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th rowspan="2">지원자번호</th>
                    <th rowspan="2">성명</th>
                    <th colspan="2">서류전형 (20%)</th>
                    <th colspan="2">필기전형 (40%)</th>
                    <th colspan="5">면접전형 (40%)</th>
                    <th rowspan="2">총점</th>
                    <th rowspan="2">순위</th>
                </tr>
                <tr>
                    <th>원점수</th>
                    <th>반영점수</th>
                    <th>원점수</th>
                    <th>반영점수</th>
                    <th>면접관1</th>
                    <th>면접관2</th>
                    <th>면접관3</th>
                    <th>평점</th>
                    <th>반영점수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="applicant" items="${applicants}">
                    <tr>
                        <td>${applicant.a_no}</td>
                        <td>
                            ${applicant.a_name}
                    		<button onclick="location.href='/applicant/modify?a_no=${applicant.a_no}'">수정</button>
   	 						<button onclick="if(confirm('정말 삭제하시겠습니까?')) { location.href='/applicant/delete?a_no=${applicant.a_no}'; }">삭제</button>
                       </td>
                        <td>
                            <fmt:formatNumber value="${applicant.d_score}" type="number" maxFractionDigits="0"/>                
                        </td>
                        <td>
                            <fmt:formatNumber value="${Math.floor(applicant.d_score * 0.2 * 10) / 10}" type="number" maxFractionDigits="1"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${applicant.w_score}" type="number" maxFractionDigits="0"/>                                    
                        </td>
                        <td>
                            <fmt:formatNumber value="${Math.floor(applicant.w_score * 0.4 * 10) / 10}" type="number" maxFractionDigits="1"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${applicant.i1_score}" type="number" maxFractionDigits="0"/>                        
                        </td>
                        <td>
                            <fmt:formatNumber value="${applicant.i2_score}" type="number" maxFractionDigits="0"/>                        
                        </td>
                        <td>
                            <fmt:formatNumber value="${applicant.i3_score}" type="number" maxFractionDigits="0"/>                        
                        </td>
                        <td>
                            <fmt:formatNumber value="${Math.floor((applicant.i1_score + applicant.i2_score + applicant.i3_score) / 3.0 * 100) / 100}" type="number" maxFractionDigits="2"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${Math.floor((applicant.i1_score + applicant.i2_score + applicant.i3_score) / 3.0 * 0.4 * 100) / 100}" type="number" maxFractionDigits="2"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${Math.floor(applicant.totalScore * 100) / 100}" type="number" maxFractionDigits="2"/>
                        </td>
                        <td>${applicant.rank}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        function redirectToInputForm() {
            window.location.href = '/applicant/write_view'; // 입력 폼의 URL로 변경
        }
    </script>
</body>
</html>
