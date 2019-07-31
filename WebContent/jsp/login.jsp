<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#submit').on('click',function(){
		if($('#id').val()!="" && $('#pw').val()!=""){
			$.ajax({
	            type:'POST',
	            dataType:'text',
	            url:'../recipe/login',
	            data:'id='+$('#id').val()+'&pw='+$('#pw').val(),
	            success: function(res) {
	            	if(res!=1) {
	        			alert("회원정보를 다시 확인해주세요.");
	        		}else {
	            		location.href='home';
	        		}
	            }
			});
			return;
		}else {
			alert("아이디와 비밀번호를 모두 입력해주세요.");
			
		}
		
	});
	
	$('#register').on('click',function(){
		alert("회원가입 페이지로 이동합니다.");
		location.href='registerForm';
	});
});
</script>

</head>
<body>
<form>
	<p>아이디 : <input type="text" id="id" name="id" required="required" /></p>
	<p>비밀번호 : <input type="password" id="pw" name="pw" required="required" /></p>
	<p>
		<input type="button" id="submit" name="submit" value="로그인" />
		<input type="button" id="register" name="register" value="회원가입" />
	</p>
</form>
</body>
</html>