
$(document).ready(function(){

		var list_cnt = 550;
		var column = '';
		var order = '';
		var nation_nm = '';
		var recipe_nm_ko = '';
		var searchType = '';
		$('.sort,.icon-search,#searchText').on('click keydown',
				function listAjax(key) {
					if ($(this).is('.sort')) {
						column = $(this).children('.column').val();
						order = $(this).children('.order').val();
					}
					if($(this).is('#searchText')){
						if(key.keyCode != 13){
							return;
						}
					}
					
					if(key.keyCode == 13 || $(this).is('.icon-search')){
						recipe_nm_ko = $('#searchText').val();
						searchType = $('#searchDrop').val();
					}
					
					$.ajax({
						type : 'POST',
						dataType : 'text',
						data : 'column=' + column + '&order=' + order
								 + '&recipe_nm_ko=' + recipe_nm_ko
								+ '&searchType=' + searchType,
						url : 'selfRecipe',
						success : function(res) {
							$('.thumbnails>div').remove();
							$('.thumbnails').html(res);
							$('.thumbnails').height(550);
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

		function load(id, cnt) {
			var hei = $('.thumbnails').height();
			hei = hei + cnt;
			if($('.thumbnails').height()<=$('.thumbnails #listSize').height()){
				$('.thumbnails').height(hei);
			}
		}

		var defTop = parseInt($('#quick_menu').css("top"));
		$(document).scroll(
				function() {
					if ($(document).height() == ($(window).height() + $(document).scrollTop())) {
						load('.thumbnails', list_cnt);
					}
					$('#quick_menu').stop().animate({
						top : defTop + $(this).scrollTop()
					}, 100);
				})
		
	
	
	/*recipe_id 보내주는 부분*/
	$('.js-load	').on('click', function() {
		console.log('js-load');
		console.log( $(this).attr('id'));
			$.ajax({
				type : 'POST',
				dataType : 'text',
				data : 'recipe_id=' + $(this).attr('id'),
				url : 'selfView',
				success : function(res) { $('#selected_recipe').html(res);}
			});
			return false;
	});		
}); 


