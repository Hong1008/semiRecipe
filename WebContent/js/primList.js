$(document).ready(
		function() {
			var list_cnt = 550;
			var column = '';
			var order = '';
			var nation_nm = '';
			var recipe_nm_ko = '';
			var searchType = '';
			$('.sort,.ing_menu_li,.icon-search,#searchText').on('click keydown',
					function listAjax(key) {
						if ($(this).is('.sort')) {
							column = $(this).children('.column').val();
							order = $(this).children('.order').val();
						}
						if ($(this).is('.ing_menu_li')) {
							nation_nm = $(this).attr('id');
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
									+ '&nation_nm=' + nation_nm + '&recipe_nm_ko=' + recipe_nm_ko
									+ '&searchType=' + searchType,
							url : 'list',
							success : function(res) {
								$('.thumbnails>div div').remove();
								$('.thumbnails>div').html(res);
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
						if ($(document).height() == ($(window).height() + $(
								document).scrollTop())) {
							load('.thumbnails', list_cnt);
						}
						$('#quick_menu').stop().animate({
							top : defTop + $(this).scrollTop()
						}, 100);
					})
			
		})