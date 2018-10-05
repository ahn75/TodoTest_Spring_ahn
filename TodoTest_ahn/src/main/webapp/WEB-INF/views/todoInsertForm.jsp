<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="/common/header.jsp"%>

<style>
	.error {
		color: red;
	}
</style>

<section>

	<h2> [ 회원 TODO 입력 Page ] </h2>
	
	<form id='reg_form' action="${pageContext.request.contextPath }/insert" method='post' >
	
		<table>
			<tr>
				<td><label for='member_id'>회원아이디: </label></td>
				<td><input id='mem_id' type='text' name='member_id'
					value='${member.member_id }' readonly="readonly"></td>
			</tr>
			
			<tr>
				<td> TODO </td>
				<td><textarea type='text' name='product_todo'
					placeholder="Enter name..."  rows="10" cols="30" required > </textarea>
					
				</td>
			</tr>
			
			<tr>
				<td> 일정 선택 </td>
				<td><input type='date' name='todo_date'
					value="${today }">
				<form:errors path="todoUpdate.todo_date" class="error"></form:errors>	
				</td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center">
					<input id='reg_button' type='submit' value='업데이트'>
				</td>
			</tr>
		</table>
		
	</form>

</section>

<%@ include file="/common/footer.jsp"%>


<script>

	var request;
	var idCheckDone = false;
	
	function idinput_change() {
		idCheckDone = false;	
	}
	
	function register() {
		console.log('register called');
		
		if (idCheckDone == false) {
			alert('id check를 하세요')
			return false;
		} 
		
		var params = '';
		var formObj = document.getElementById('reg_form');
		request = new XMLHttpRequest();
		var url = "${pageContext.request.contextPath}/register";
		var elem = formObj.elements;
		
		for (var i = 0; i < elem.length; i++) {
			if (elem[i].tagName == "SELECT") {
	            value = elem[i].options[elem[i].selectedIndex].value;
	        } else {
	            value = elem[i].value;                
	        }
			
			if (value != '') {
				params += elem[i].name + '=' + encodeURIComponent(value) + '&';	
			}
			
		}
		
		console.log(params);
		
		try {
			request.onreadystatechange = getResult;
			request.open("POST", url, true);
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.send(params);
		} catch(e) {
			alert('서버로 요청이 실패');
		}
		
		return false;
	}
	
	function getResult() {
		if (request.readyState == 4) {
			var result = request.responseText;
			
			if (result == 'success') {
				alert('업데이트 성공')
			} else {
				alert('업데이트 실패')
			}
		}
	}
	
	function getIdCheckResult() {
		if (request.readyState == 4) {
			var result = request.responseText;
			
			if (result == 'available') {
				idCheckDone = true;
				
				alert('사용가능')
			} else {
				alert('이미 사용된 아이디 입니다. 다른 아이디를 쓰세요')
			}
		}
	}
	
	function pw_validate() {
		var pw1 = document.getElementById('mem_pw1');
		var pw2 = document.getElementById('mem_pw2');
		
		if (pw1 == pw2) {
			return true;
		} else {
			return false;
		}
	}
	
	function id_check() {
		
		var id = document.getElementById('mem_id');
		console.log('id check called: ' + id.value)
		
		var params = 'id=' + id.value;
		var formObj = document.getElementById('reg_form');
		request = new XMLHttpRequest();
		var url = "${pageContext.request.contextPath}/idcheck";
		
		try {
			request.onreadystatechange = getIdCheckResult;
			request.open("POST", url, true);
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.send(params);
		} catch(e) {
			alert('서버로 요청이 실패');
		}
		
		return false;
		
	}
	
	function list_member() {
		console.log("list called")
		location.href = "${pageContext.request.contextPath}/list"
	}
	
</script>

