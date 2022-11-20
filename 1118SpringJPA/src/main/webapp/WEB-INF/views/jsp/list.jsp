<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- jstl pom 에 추가해줘야함  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<body>
	<div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>나이</th>
					<th>주소</th>
					<th>등록일시</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td>${student.sid}</td>
					<td>${student.name }</td>
					<td>${student.age }</td>
					<td>${student.addr }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${student.regDate}" /></td>
					<td><a href="/updateForm?sid=${student.sid}">수정</a></td>
					<td><a href="/delete?sid=${student.sid}">삭제</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<input type="button" value="등록" onclick="location.href='/insertForm';" />
	</div>
</body>
</html>