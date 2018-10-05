<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/common/header.jsp"%>

<section>
	<h3>회원 목록 조회</h3>
	
	<table border = 1 >
		<tr>
			<th> 회원아이디 </th>
			<th> 회원성명 </th>

		</tr>
		<c:forEach items="${list }" var="member">
		<tr>
			<td><a href="${pageContext.request.contextPath }/update?id=${member.member_id}" >${member.member_id}</a></td>
			<td>${member.member_name}</td>

		</tr>
		</c:forEach>
	</table>

</section>

<%@ include file="/common/footer.jsp"%>


<script>

</script>