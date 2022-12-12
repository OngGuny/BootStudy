<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<body>
	<div>
		<table>
		<c:choose>
		<c:when test="${sid !=null}"> 
		<form name="insertForm" action="/update" method="post">
		</c:when>
		<c:otherwise> 
		<form name="insertForm" action="/insert" method="post">
		</c:otherwise>
		</c:choose>
		<input type="hidden" name="sid" value="${student.sid}"/>
			<tbody>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${student.name }" /></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${student.age }" /></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${student.addr }" /></td>
				</tr>
			</tbody>
		</form>			
		</table>
	</div>
	<div>

		<c:choose>
		
		<c:when test="${param.sid != null }"> 
		<input type="button" value="수정" onclick="document.forms['insertForm'].submit();"/>
		</c:when>
		
		<c:otherwise>
		<input type="button" value="등록" onclick="document.forms['insertForm'].submit();"/>
		</c:otherwise>
		
		</c:choose>
		
		
		&nbsp;
		<input type="button" value="취소" onclick="history.back();"/>
	</div>
</body>
</html>