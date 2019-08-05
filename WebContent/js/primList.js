$(document).ready(function() {
	var list_cnt = '16'
	$('.sort').click(function() {
		var column = $(this).children('.column').val();
		var order = $(this).children('.order').val();
		$.ajax({
			type : 'POST',
			dataType : 'text',
			data : 'column=' + column + '&order=' + order,
			url : 'list',
			success : function(res) {
				$('.thumbnails div').remove();
				$('.thumbnails').html(res);
				load('.thumbnails', list_cnt);
			}
		})
		if (column == 'recipe_nm_ko') {
			if ($('#sortNM .order').val() == 'desc')
				$('#sortNM .order').val('asc');
			else if ($('#sortNM .order').val() == 'asc')
				$('#sortNM .order').val('desc');
		}
		return false;
	})

	$('#search').on('keyup', function() {
		if (window.event.keycode == 13) {

		}
	})

	
	
	load('.thumbnails', list_cnt);
	$("#js-btn-wrap .button").on("click", function() {
		load('.thumbnails', list_cnt, '#js-btn-wrap');
	})

	function load(id, cnt, btn) {
		var girls_list = id + " .js-load:not(.active)";
		var girls_length = $(girls_list).length;
		var girls_total_cnt;
		if (cnt < girls_length) {
			girls_total_cnt = cnt;
		} else {
			girls_total_cnt = girls_length;
			$('.button').hide()
		}
		$(girls_list + ":lt(" + girls_total_cnt + ")").addClass("active");
	}
	
	var defTop = parseInt($('#quick_menu').css("top"));
	$(document).scroll(function(){
		if($(document).height()==($(window).height()+$(document).scrollTop())){
			load('.thumbnails', list_cnt);
		}
		$('#quick_menu').stop().animate({top:defTop+$(this).scrollTop()},100);
	})
	  
})