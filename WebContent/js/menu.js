$(document).ready(function(){
	var this_page =  'menu01';

	$('.menu_li img').on('mouseenter',function(){
		if($(this).parents().prop('id')!='menu1')
		$(this).animate({'margin-top':0},400);
	})
	$('.menu_li img').on('mouseleave',function(){
		if($(this).parents().prop('id')!='menu1')
		$(this).animate({'margin-top':-80},400);
	})
	


})