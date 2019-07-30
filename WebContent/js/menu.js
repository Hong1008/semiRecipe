$(document).ready(function(){
	$('.menu_li img').on('mouseover',function(){
		var src = $(this).prop('src');
		src = src.substring(0,src.length-4);
		console.log(src);
		$(this).prop('src',src+'_hover.PNG');
	})

	$('.menu_li img').on('mouseout',function(){
		var src = $(this).prop('src').split('_')[0];
		console.log(src);
		$(this).prop('src',src+'.PNG');
	})

	$(document).scroll(function(){
		if($(document).scrollTop()<500){
			var pos=$('#header').css('background-position-y');
			pos=-500-$(document).scrollTop()*0.23;
			console.log(pos);
			$('#header').css('background-position-y',pos+'px');
			$('#menu_bar img').css('margin-top',pos+'px');
		}else{  			
		}
	})
	
	$('.menu_li').click(function(){
		var id = $(this).attr('id');
		location.href='/semiRecipe/recipe/'+id;
	})
	
	$('#log_container').click(function(){
		location.href='/semiRecipe/recipe/login';
	})
})