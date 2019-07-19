<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../menu/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<link rel="stylesheet" href="/semiRecipe/css/home_container.css">
<!-- container연결-->

<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>
<script src="/semiRecipe/js/plugin/hangul.js"></script>
<!--플러그인 연결-->


<script type="text/javascript">

	var a = true;	 
    $(document).ready(function(){
   	 //재로 사이드메뉴 열기/닫기
        var isopen=false;
        $('#side_btn').on('click',function(){
            if(isopen){
                $('#side_menu').animate({right:-700},800);
                $('#side_btn img').css({'transform':'rotate(180deg)'});
                $('#side_btn').animate({ 'margin-left':-100},1200);
                isopen=false;
            }
            else{
                $('#side_menu').animate({right:0},800);
                $('#side_btn img').css({'transform':'rotate(0)'});
                 $('#side_btn').animate({ 'margin-left':0},300);
                isopen=true;
            }
        })
        
		

		 //재로 갯수만큼 재료div 생성
<%--         var ing_num=<%=aList.size()%>;
        
        var ing_html="";
        <%
        for(var i=0;i<ing_num;i++){
            ing_html+="<div class='ingredients' id='재료'></div>";
        }
        %>
        $('#ing_maker').html(ing_html); --%>
		
        //드래그하는 부분
        var name="";
        $('.ingredients').on('mousedown',function(){
            name=$(this).prop('id');
        }).draggable({ 
            scroll: false,
            helper: "clone",
            revert: true,
            opacity: 0.90
        });
        
        $('.ingredients').on('dblclick',function(){
            select_num++;
            if(select_num<10){
                var selected =  $('#selected_ing_div').html()
                +" <div class='selected_ing'><button class='close_ing'></button><p>"
                +name+"</p></div>";
                $('#selected_ing_div').html(selected);
            }else{
                alert("재료는 10개까지만 선택 가능합니다.");
            }
        })

        //드롭하는부분
        var select_num=0;
        $('#main_contents').droppable({
            drop: function( event, ui ) {            
                select_num++;
                if(select_num<10){
                    var selected =  $('#selected_ing_div').html()
                    +" <div class='selected_ing'><button class='close_ing'></button><p>"
                    +name+"</p></div>";
                    $('#selected_ing_div').html(selected);
                }else{
                    alert("재료는 10개까지만 선택 가능합니다.");
                }

                //드롭된 재료들 삭제
                $('.close_ing').on('click',function(){
                    $(this).parents('.selected_ing').remove();
                    select_num--;
                })            
            }
        });        
        
       
      //검색기능 구현
        var ingredients_num = $('.ingredients').length;//처음 DB에서 받아온 갯수를 저장
      	var ingredients_name = new Array();
     	for(var i=0;i<ingredients_num;i++){
     		ingredients_name[i] = $('.ingredients').eq(i).attr('id');
     		console.log(i+":"+ingredients_name[i]);
     	} //배열에 이름들을 저장
        
        $('input').on('keyup',function(){          	
            var search = $(this).val();//현재 인풋창의 값을 받아옴
            var search_len = Hangul.disassemble(search).length;//현재 인풋창의 자음+모음수
            search = Hangul.assemble(search);        	

        	$('.ingredients').hide();
            for(var i=0;i<ingredients_num;i++){
            	//맞는것 실행시켜줌
            	var ing_id = Hangul.disassemble(ingredients_name[i]+"");//i번쨰 재료의 이름 를 자름
            	var ing_len = ing_id.length;
            	var ing_hangul ="";
            	
            	for(var j=0;j<search_len;j++){
            		if(j<ing_len)
                		ing_hangul +=ing_id[j];
            	}
            	ing_hangul = Hangul.assemble(ing_hangul);//타자 친 숫자만큼 이름에서 가져옴            	
				if(search==ing_hangul){
					$('#'+ingredients_name[i]).show();
				}
            }            
        })   	   
}); 

    </script>


</head>


<body>
	<div id='wrap' class='test'>
		<!--여기서부터 컨테이너시작-->
		<div id='container'>
			<div id='main_contents'>
				<div id='selected_ing_div'></div>
			</div>
			<div id='side_menu'>
				<div id='side_btn'>
					<img src="/semiRecipe/img/home_open_btn.png">
				</div>
				<div id='search_div'>
					<label for='search'><img
						src="/semiRecipe/img/home_search_img.PNG"></label><input type="text"
						name="search" autocomplete="off">
				</div>
				<div id='ingredients_div'>
					<ul id='ing_menu'>
						<li class='ing_menu_li'></li>
						<li class='ing_menu_li'></li>
						<li class='ing_menu_li'></li>
						<li class='ing_menu_li'></li>
					</ul>
					<div id='ing_maker'>
						<c:forEach items="${aList }" var="dto">
							<div class='ingredients' id='${dto.irdnt_nm}'>${dto.irdnt_nm}</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!--여기까지 컨테이너 끝-->
		<footer></footer>
	</div>
</body>
</html>