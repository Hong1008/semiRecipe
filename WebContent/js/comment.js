$(document).ready(function(){
	$('#CMT_input').on('focus',function(){
		if($(this).val()==""){
		$(this).css({'width':'0px','border-bottom':'1px solid rgba(10,10,10,0.8)'}).animate({'width':'620px'},320);
		$('#CMT_submit').show();
		$('#CMT_reset').show();
		$('#star_div').show();
		}
	}).on('focusout',function(){
		if($(this).val()==""){
		$(this).css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
			$('#CMT_submit').hide();
			$('#CMT_reset').hide();
			$('#star_div').hide();
		}		
	}).on('keydown keyup', function () {
		  $(this).height(1).height( $(this).prop('scrollHeight')+12 );	
	});
	
	
	
	$('#CMT_reset').on('click',function(){
		$('#CMT_input').css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
		$('#CMT_submit').hide();
		$('#CMT_reset').hide();
	})
	
	$('.star').on('click',function(){
		$(this).attr('src','/semiRecipe/img/menu_myPage.png');
	})
	
})