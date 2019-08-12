var startPage = true;
$(document)
.ready(
		function() {			
			//$("html, body").animate({ scrollTop: 0 }, 1);

			$('html, body').css({'overflow': 'hidden', 'height': '100%'});
			

			//드롭하는부분		
			$('#selected_ing_div')
			.droppable(
					{
						drop : function(event, ui) {
							//처음 드랍이면 아래로 이동
							if(startPage){
								$('html, body').animate({scrollTop : '1200px'}, 800);
								$('html, body').css({'overflow': 'auto', 'height': 'auto'});
								startPage=false;
							}
							var aaaa= $('.selected_ing');
							var selected = false;
							for(var i=0; i<aaaa.length;i++){
								if(aaaa.eq(i).children('p').text() == name){
									selected=true;
								}
							}
							if(selected){
								alert("이미 선택된 재료입니다..");								
							}else{			
								console.log($('.selected_ing').length);
								if ($('.selected_ing').length < 5) {
									var selected = $(
									'#selected_ing_list')
									.html()
									+ " <div class='selected_ing'><button class='close_ing'></button><p>"
									+ name
									+ "</p></div>";
									$('#selected_ing_list')
									.html(selected);
									viewList();
								} else {
									alert("재료는 5개까지만 선택 가능합니다.");
								}
							}
								
							

							//드롭된 재료들 삭제
							$('.close_ing')
							.on(
									'click',
									function() {
										$(this)
										.parents(
										'.selected_ing')
										.remove();
										viewList();
									})
						}
					});

			//추천 레시피 부분
			function viewList(){
				var irdnt_nms = new Array();

				$(".selected_ing p").each(function(i,v){
					irdnt_nms[i] = $(v).text();
				})
				var jsonString = JSON.stringify(irdnt_nms);
				$.ajax({
					type:'POST',
					dataType:'text',
					data:'irdnt_nm='+jsonString,
					url:'viewResult',
					success: function(res){
						$("#selected_recipe_view").html(res);
					}
				})
			}

			//스크롤 내리기전 사이드메뉴
			$(document).scroll(function() {		
				
				if($(document).scrollTop() < 700){
					$('.recipeBtn#nextBtn img').animate({'opacity':0},300);	
					$('.recipeBtn#prevBtn img').animate({'opacity':0},300);	
				}else{
					$('.recipeBtn#nextBtn img').clearQueue().animate({'opacity':1},300);	
					$('.recipeBtn#prevBtn img').clearQueue().animate({'opacity':1},300);	
				}

				//선택된 메뉴들
				if($(document).scrollTop() < 600 ){
					$('#selected_ing_div').css({'top':	'700px','opacity':1});	
				}else if ($(document).scrollTop() < 1200 && $(document).scrollTop() >= 600) {
					var marginT = 1300 - $(document).scrollTop();
					$('#selected_ing_div').css({'top':marginT + 'px','opacity':1});
				} else {
					$('#selected_ing_div').css({'top':	'160px','opacity':0.5});					
				}
			});
			//마우스 올리면 투명도 제거
			$('#selected_ing_div').on('mouseover',function(){
				$(this).clearQueue().animate({'opacity':1},300);
			})
			$('#selected_ing_div').on('mouseleave',function(){
				if($(document).scrollTop() >= 650)
					$(this).clearQueue().animate({'opacity':0.5},300);
			})
		});