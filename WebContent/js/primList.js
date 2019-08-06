$(document).ready(
		function() {
			var list_cnt = 550;
			var column = '';
			var order = '';
			var nation_nm = '';
			$('.sort,.ing_menu_li').click(
					function listAjax() {
						if ($(this).is('.sort')) {
							console.log($(this).attr('class'))
							column = $(this).children('.column').val();
							order = $(this).children('.order').val();
						}
						if ($(this).is('.ing_menu_li')) {
							console.log($(this).attr('class'))
							nation_nm = $(this).attr('id');
						}
						$.ajax({
							type : 'POST',
							dataType : 'text',
							data : 'column=' + column + '&order=' + order
									+ '&nation_nm=' + nation_nm,
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