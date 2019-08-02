
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i" rel="stylesheet">
<link rel="stylesheet" href="../fontello-ea422c41/css/fontello.css">
<link rel="stylesheet" href="/semiRecipe/fontello-ea422c41/css/fontello.css">
<script src="../ckeditor/ckeditor.js"></script>
<style type="text/css">
   body{
     background-color: #FFF5E6;
   }

   #writeframe{
    height : 800px;
   	max-width : 68rem;
   	margin : 0 auto;
   	border : 2px solid red;
   	margin-top : 10px;
   }
   
   #writeform{
   	 margin : 0 auto;
   }
   
   table{
   	 border : 1px solid green;
   	 float : center;
   	 margin : 0 auto;
   	 
   }
   
   td, tr{
     color : #8D4738;
     padding : 3px;
   }
   
   .icon-th-large-outline{
   	 padding : 3px;
   	 float : left; 
   	 text-decoration: none;
   	 color : #FFF5E6;
   	 background-color : rgba(141,71,56,0.7);
   	 border : 1px solid rgba(141,71,56,0.3);
   	 border-radius: 0.2rem; 
   	 font-size : 15px;
   }
</style>
</head>
<body>
	<div>
		<%@ include file="../jsp/menu.jsp" %>
	</div>
<div id ="writeframe">
	<form name="frm" method="post" enctype="multipart/form-data" action="#" id="writeform">
		<table>

			<tr>
				<td width="20%" align="center">글쓴이</td>
				<td width="80%"><input type="text" name="writer" placeholder="로그인 정보 받아와야함" style="width:770px;" /></td>
			</tr>

			<tr>
				<td width="20%" align="center">레시피 선택</td>
				<td width="80%"><input type="text" name="recipe" placeholder="레시피 목록 검색해서 선택 가능해야함" style="width:770px;"/></td>
			</tr>

			<tr>
				<td width="20%" align="center">제목</td>
				<td width="80%"><input type="text" name="subject" style="width:770px;"/></td>
			</tr>

			<tr>
				<td width="20%" align="center" >내용</td>
				<td width="80%"><textarea name="editor1" id="editor1" rows="30" cols="80"></textarea></td>
			</tr>
			<script>
                CKEDITOR.replace('editor1');
            </script>
            
			<tr>
				<td width="20%" align="center">파일첨부</td>
				<td width="80%" id="fileDiv"><input type="file" name="upload" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="글쓰기" class="icon-comment" />
			</tr>
			<tr>
				<td align="right" colspan="2"><a href="reviewBoard.jsp" class="icon-th-large-outline"> 목록</a></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>

