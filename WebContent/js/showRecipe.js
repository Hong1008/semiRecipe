$(document).ready(function(){
	$('.thumbnails .js-load').on('click',function(){
		showRecipe($(this).attr('id'),"#selected_recipe");
		console.log("end");
		return false;
	});
})

function showRecipe(selected,selector){
	$.ajax({
		type:'GET',
		dataType:'text',
		data:'recipe_id='+selected,
		url:'showRecipe',
		success: function(res){
			$(selector).html(res);
			comList();
		}
	})
	var offset = $(selector).offset();
        $('html, body').animate({scrollTop : offset.top}, 400);

}

function insertCom(){
	var com_content = $('.insertCom #CMT_input').val();
	var user_id = $('.insertCom #user_id').val();
	var rating = $('.insertCom #rate').val();
	var com_board = $('#CMT>div').attr('id');
	var key = $('#CMT #key').val();
	if(user_id==''){
		alert('먼저 로그인을 해주세요');
		return;
	}
	$.ajax({
		type:'GET',
		dataType:'text',
		data:'com_board='+com_board+'&key='+key
		+'&user_id='+user_id+'&rating='+rating+'&com_content='+com_content,
		url:'insertCom',
		success: function(res){
			comList();
		}
	})
}

function comList(){
	$.ajax({
		type:'POST',
		dataType:'text',
		data:'com_board='+$('#CMT>div').attr('id')+'&key='+$('#CMT #key').val(),
		url:'comList',
		success: function(res){
			$('#CMT>div').html(res);
		}
	})
}