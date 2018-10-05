<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/common/header.jsp"%>

<section>

	<h3>회원 TODO 조회</h3>
	
	<table border = 1>
		<tr>
			<th> 회원아이디 </th>
			<th> 회원성명 </th>
			<th> 할일 </th>
			<th> 일자 </th>
		</tr>
		
		<c:forEach items="${todo }" var="record">
		<tr>
			<td><a href="${pageContext.request.contextPath}/insert?id=${record.member_id }" > ${record.member_id } </a></td>
			<td> ${record.member_name} </td>
			<td> ${record.product_todo} </td>
			<td> ${record.todo_date} </td>
		</tr>
		</c:forEach>
		
	</table>

</section>

<%@ include file="/common/footer.jsp"%>

<script>

</script>