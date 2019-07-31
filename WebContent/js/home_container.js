var a = true;
$(document)
.ready(
		function() {
			//재로 사이드메뉴 열기/닫기
			var isopen = false;
			$('#side_btn').on('click', function() {
				if (isopen) {
					$('#side_menu').animate({
						right : -700
					}, 800);
					$('#side_btn img').css({
						'transform' : 'rotate(180deg)'
					});
					$('#side_btn').animate({
						'margin-left' : -100
					}, 1200);
					isopen = false;
				} else {
					$('#side_menu').animate({
						right : 0
					}, 800);
					$('#side_btn img').css({
						'transform' : 'rotate(0)'
					});
					$('#side_btn').animate({
						'margin-left' : 0
					}, 300);
					isopen = true;
				}
			});

			//드래그하는 부분
			var name = "";
			$('.ingredients').on('mousedown', function() {
				name = $(this).prop('id');
			}).draggable({
				scroll : false,
				helper : "clone",
				revert : true,
				opacity : 0.90
			});

			var select_num = 0;
			//더블클릭하는 부분
			$('.ingredients')
			.on(
					'dblclick',
					function() {
						select_num++;
						if (select_num < 10) {
							var selected = $(
							'#selected_ing_div')
							.html()
							+ " <div class='selected_ing'><button class='close_ing'></button><p>"
							+ name + "</p></div>";
							$('#selected_ing_div').html(
									selected);
							viewList();
						} else {
							alert("재료는 10개까지만 선택 가능합니다.");
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
									select_num--;
									viewList();
								})
					});



			//드롭하는부분			
			$('#main_contents')
			.droppable(
					{
						drop : function(event, ui) {
							select_num++;
							if (select_num < 10) {
								var selected = $(
								'#selected_ing_div')
								.html()
								+ " <div class='selected_ing'><button class='close_ing'></button><p>"
								+ name
								+ "</p></div>";
								$('#selected_ing_div')
								.html(selected);
								viewList();
							} else {
								alert("재료는 10개까지만 선택 가능합니다.");
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
										select_num--;
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

			//검색기능 구현
			var ingredients_num = $('.ingredients').length;//처음 DB에서 받아온 갯수를 저장
			var ingredients_name = new Array();
			for (var i = 0; i < ingredients_num; i++) {
				ingredients_name[i] = $('.ingredients').eq(i).attr(
				'id');
			} //배열에 이름들을 저장

			$('input')
			.on(
					'keyup',
					function() {
						var search = $(this).val();//현재 인풋창의 값을 받아옴
						var search_len = Hangul
						.disassemble(search).length;//현재 인풋창의 자음+모음수
						search = Hangul.assemble(search);

						$('.ingredients').hide();
						for (var i = 0; i < ingredients_num; i++) {
							//맞는것 실행시켜줌
							var ing_id = Hangul
							.disassemble(ingredients_name[i]
							+ "");//i번쨰 재료의 이름 를 자름
							var ing_len = ing_id.length;
							var ing_hangul = "";

							for (var j = 0; j < search_len; j++) {
								if (j < ing_len)
									ing_hangul += ing_id[j];
							}
							ing_hangul = Hangul
							.assemble(ing_hangul);//타자 친 숫자만큼 이름에서 가져옴            	
							if (search == ing_hangul) {
								$('#' + ingredients_name[i])
								.show();
							}

						}
					});

			//스크롤 내리기전 사이드메뉴
			$(document).scroll(function() {
				
				//사이드메뉴
				if ($(document).scrollTop() < 500) {
					var marginT = 700 - $(document).scrollTop();
					$('#side_menu').css('top',marginT + 'px');
					if ($(document).scrollTop() > 200)
						$('#side_btn').css('margin-top',($(document).scrollTop() - 200)+ 'px');
				} else {
					$('#side_menu').css('top',
					'200px');
				}
				
				//선택된 메뉴들
				if ($(document).scrollTop() < 680) {
					var marginT = 800 - $(document).scrollTop();
					$('#selected_ing_div').css({'top':marginT + 'px','opacity':1});
				} else {
					$('#selected_ing_div').css({'top':	'120px','opacity':0.5});					
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

			//재료 항목 이동 버튼
			var ing_m_cnt=0;
			$('#ing_m_left').on('click',function(){
				var left = $('#ing_menu').css('margin-left').split('p')[0];
				if(ing_m_cnt>-4)
					ing_m_cnt--;
				$('#ing_menu').animate({'margin-left':(ing_m_cnt*150)+'px'},200);
			});

			$('#ing_m_right').on('click',function(){
				var left = $('#ing_menu').css('margin-left').split('p')[0];
				if(ing_m_cnt<0)
					ing_m_cnt++;
				$('#ing_menu').animate({'margin-left':(ing_m_cnt*150)+'px'},200);
			});


			//재료 항목 별 클릭
			$('#ingredients_menu_div ul li').click(function(){
				var ty = $(this).attr('id');
				$('.ingredients').hide();
				$('.ingredients input').each(function(i,v){
					if($(v).attr('id')==ty){
						$(v).parent().show();
					}
				})
			})

		});