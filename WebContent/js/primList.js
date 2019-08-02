$(document).ready(function(){
	$('.sort').click(function(){
		var column = $(this).children('.column').val();
		var order = $(this).children('.order').val();
		$.ajax({
			type:'POST',
			dataType:'text',
			data:'column='+column+'&order='+order,
			url:'list',
			success:function(res){
				$('.thumbnails div').remove();
				$('.thumbnails').html(res);
			}
		})
		if(column=='recipe_nm_ko'){
			if($('#sortNM .order').val()=='desc')
				$('#sortNM .order').val('asc');
			else if($('#sortNM .order').val()=='asc')
				$('#sortNM .order').val('desc');
		}
		return false;
	})
	
	$('#search').on('keyup',function(){
		if(window.event.keycode==13){
			
		}
	})
})