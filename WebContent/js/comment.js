var star_rate=0;
var star_num=0;

$(document).ready(function(){	
	$('#CMT_input').on('focus',function(){
		if($(this).val()==""){
			$(this).css({'width':'0px','border-bottom':'1px solid rgba(10,10,10,0.8)'}).animate({'width':'520px'},220);
			$('#CMT_submit').show();
			$('#CMT_reset').show();
		}
	}).on('focusout',function(){
		if($(this).val()==""){
			$(this).css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
			$('#CMT_submit').hide();
			$('#CMT_reset').hide();
		}		
	}).on('keydown keyup', function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+12 );	
	});



	$('#CMT_reset').on('click',function(){
		$('#CMT_input').css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
		$('#CMT_submit').hide();
		$('#CMT_reset').hide();
		$('#CMT_input').val('');
		star_num=0;
		rating(star_num);
	})

	
	$('.star').on('click',function(){
		star_num=$(this).attr('id').split('_')[1];
		console.log('star_num='+star_num);
		rating(star_num,'#star_');
		$('#rate').attr('value',star_num);
	})
	
	$('.star').on('mouseover',function(){
		star_rate=$(this).attr('id').split('_')[1];
		console.log('star_rate='+star_rate);
		rating(star_rate,'#star_');
	})	

	$('#star_div').on('mouseleave',function(){
		rating(star_num,'#star_');
	})
	
	var con = $('.rated_div');
	for(var i=0;i<con.length;i++ ){
		var num = con.eq(i).find('.rate_num').attr('id');
		var id = '#'+con.eq(i).attr('id')+" #rate_";
		console.log('num:' + num);
		console.log('id:' + id);
		rating(num,id)
		
	}
	
	
})
function rating(num,id){
	for(var i=1;i<=5;i++){
		console.log(id+i);
		if(i<=num)
		$(id+i).css('opacity','0.9');
		else
			$(id+i).css('opacity','0.3');
	}
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
