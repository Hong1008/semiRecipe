var loc = $(location).attr('href');
$(document).ready(function(){
		//어떤 메뉴인지 loc에 저장
		loc = loc.split('/');
		loc=loc[loc.length-1];		
		if(loc=='home'){
			$('#header').css({'height':'1100px',
				'background-image':'url("/semiRecipe/img/home_back.JPG")',
				'background-position':'-135px -400px'});
			$('#info').hide();
			$('#menu_bar img').attr('src','/semiRecipe/img/home_back.JPG').css({
				'margin-left':'-135px',
				'margin-top':'-400px',
				'width':'2110px'});
		}
	
	
	$('.menu_li img').on('mouseover',function(){
		var src = $(this).prop('src');
		src = src.substring(0,src.length-4);
		$(this).prop('src',src+'_hover.PNG');		
	})
	
	$('.menu_li img').on('mouseout',function(){
		var src = $(this).prop('src').split('_')[0];
		if($(this).prop('src').split('_')[1]=='hover.PNG')
			$(this).prop('src',src+'.PNG');
		})	
	
	$(document).scroll(function(){
		var posy=400;
		if($(document).scrollTop()<1200){
			var pos=$('#header').css('background-position-y');
			pos=-posy-$(document).scrollTop()*0.23;
			$('#header').css('background-position-y',pos+'px');
			$('#menu_bar img').css('margin-top',pos+'px');
			$('#info').css('top',-pos+'px');
		} 
		$('#menu_bar img').css('opacity',(1000-$(document).scrollTop()*$(document).scrollTop()*0.0013)/1000);
		$('#info').css('opacity',(1000-$(document).scrollTop()*$(document).scrollTop()*0.05)/1000);
			
		
		
	})
	
	$('.menu_li').click(function(){
		var id = $(this).attr('id');
		location.href='/semiRecipe/recipe/'+id;
	})
	
	$('#myPage').click(function(){
		var id = $(this).attr('id');
		location.href='/semiRecipe/recipe/'+id;
	})
	
	$('.home').click(function(){
		var id = $(this).attr('class');
		location.href='/semiRecipe/recipe/'+id;
	})
	
	$('#login').click(function(){
		var id = $(this).attr('id');
		location.href='/semiRecipe/recipe/'+id+'Form';
	})
	
	$('#logout').click(function(){
		alert("로그아웃 되셨습니다.");
		var id = $(this).attr('id');
		location.href='/semiRecipe/recipe/'+id;
	})
})