
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i"
	rel="stylesheet">
<link rel="stylesheet" href="../fontello-ea422c41/css/fontello.css">
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">
<script src="../ckeditor/ckeditor.js"></script>
<script src="/semiRecipe/js/plugin/hangul.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('.starRev span').click(function(){
	  $(this).parent().children('span').removeClass('on');
	  $(this).addClass('on').prevAll('span').addClass('on');
	  return false;
	});
	
	search();
		
	function search(){
			//검색기능 구현
			var recipes_num = $('.recipes').length;//처음 DB에서 받아온 갯수를 저장
			var recipes_name = new Array();
			for (var i = 0; i < recipes_num; i++) {
				recipes_name[i] = $('.recipes').eq(i).text();
			} //배열에 이름들을 저장

			$('#revRecipeSelect')
			.on(
					'keyup',
					function() {
						var search = $(this).val();//현재 인풋창의 값을 받아옴
						var search_len = Hangul
						.disassemble(search).length;//현재 인풋창의 자음+모음수
						search = Hangul.assemble(search);

						$('.recipes').hide();
						for (var i = 0; i < recipes_num; i++) {
							//맞는것 실행시켜줌
							var ing_id = Hangul
							.disassemble(recipes_name[i]
							+ "");//i번쨰 재료의 이름 를 자름
							var ing_len = ing_id.length;
							var ing_hangul = "";

							for (var j = 0; j < search_len; j++) {
								if (j < ing_len)
									ing_hangul += ing_id[j];
							}
							ing_hangul = Hangul
							.assemble(ing_hangul);//타자 친 숫자만큼 이름에서 가져옴            	
							if (search == ing_hangul) {
								$('#' + recipes_name[i]).show();
							}

						}
					});		
			
	}
});
</script>
<style type="text/css">
body {
	background-color: #FFF5E6;
}

#writeframe {
	height: 800px;
	max-width: 68rem;
	margin: 0 auto;
	margin-top: 10px;
}

#writeform {
	margin: 0 auto;
}

table {
	float: center;
	margin: 0 auto;
}

td, tr {
	color: #8D4738;
	padding: 3px;
}

.starR {
	background: url('/semiRecipe/review/images/reviewStar.png') no-repeat
		right 0;
	background-size: auto 100%;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-indent: -9999px;
	cursor: pointer;
}

.starR.on {
	background-position: 0 0;
}

.recipes{
	display:inline-block;
	width:190px;
}

#revRecipeSelect::placeholder {
	color: #8D4738;
}

#recipeSelectList {
	background-color: white;
	border: 1px solid #a9a9a9;
	list-style-type: none;
}
.scrollBlind{
    height:100%;
    overflow-y:scroll;
    
}

.view{
	height: 500px;
	overflow-y: hidden;
}
.icon-th-large-outline {
	padding: 3px;
	float: left;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(242, 159, 5, 0.8);
	border: 1px solid #F29F05;
	border-radius: 0.2rem;
	font-size: 20px;
}

.icon-comment {
	padding: 3px;
	float: center;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(242, 159, 5, 0.8);
	border: 1px solid #F29F05;
	border-radius: 0.2rem;
	font-size: 20px;
}

</style>
</head>
<body>
	<div>
		<%@ include file="../jsp/menu.jsp"%>
	</div>
	<div id="writeframe">
		<form name="frm" method="post" enctype="multipart/form-data"
			action="#" id="writeform">
			<table>

				<tr>
					<td width="20%" align="center">레시피 선택</td>
					<td width="800%"><input type="text" id="revRecipeSelect"
						name="recipe" placeholder="레시피 검색"
						style="width: 800px; height: 20px; font-size: 15px;" /></td>
				</tr>

				<tr id="choo">
					<td width='20%' align='center'></td>
					<td width='800px'>
						<div class="view">
						<div class="scrollBlind" style="width:800px;">
						<ul id='recipeSelectList'>
							<c:forEach items="${requestScope.recipes }" var="dto">
								<li class="recipes" id="${dto.RECIPE_NM_KO }">${dto.RECIPE_NM_KO }</li>
							</c:forEach>
						</ul>
						</div>
						</div>
					</td>
				</tr>

				<tr>
					<td width="20%" align="center">레시피 별점</td>
					<td width="80%">
						<div class="starRev">
							<span class="starR on">별1</span> <span class="starR on">별2</span>
							<span class="starR on">별3</span> <span class="starR on">별4</span>
							<span class="starR on">별5</span> <span class="starR on">별6</span>
							<span class="starR on">별7</span> <span class="starR on">별8</span>
							<span class="starR on">별9</span> <span class="starR on">별10</span>
						</div>
					</td>
				</tr>

				<tr>
					<td width="20%" align="center">제목</td>
					<td width="80%"><input type="text" name="subject"
						style="width: 800px; height: 20px;" /></td>
				</tr>

				<tr>
					<td width="20%" align="center">내용</td>
					<td width="80%"><textarea name="editor1" id="editor1"
							rows="30" cols="80"></textarea></td>
				</tr>
				<script>
                CKEDITOR.replace('editor1');
            </script>

				<tr>
					<td width="20%" align="center">파일첨부</td>
					<td width="80%" id="fileDiv"><input type="file" name="upload" /></td>
				</tr>
				<tr>
					<td height="10px"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button class="icon-comment">
							확인</button>
				</tr>
				<tr>
					<td height="20px"></td>
				</tr>
				<tr>
					<td align="right" colspan="2"><a href="review"
						class="icon-th-large-outline"> 목록</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

