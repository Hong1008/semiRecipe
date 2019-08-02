$(document).ready(function() {
	$('#nChangeBtn').on('click', function() { // 변경버튼 눌렀을 때

		if ($('#nBox').css('display') == "none") { // 변경버튼 처음 눌렀을 때
			first();
		} else { // 변경버튼 눌러서 입력 값 설정한 후 다시 변경버튼 눌렀을 때
			var nickname = $('#nBox').val();
			var nonchar = /^[가-힣a-zA-Z0-9]{2,10}$/;

			if (nickname == "") {
				alert("필수 정보입니다.");
			} else if (!nonchar.test(nickname)) {
				alert("2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.");
			} else if (nickname == '${dto.nickname}') {
				alert("현재 사용중인 닉네임입니다.");
			} else {
				$.ajax({
					type : 'POST',
					dataType : 'text',
					url : '/semiRecipe/recipe/checkNickname',
					data : 'nickname=' + nickname,
					success : function(res) {
						if (res != "") {
							alert(res);
						} else {
							var chk = confirm('수정하시겠습니까?');
							if (chk) {				// 확인을 누르면
								$('#nValue').text(nickname);
								modAfter();
							}
						}

					}
				});
			}

			return false;
		}

	});

	$('#nChangeCancleBtn').on('click', function() {
		$('#nValue').css('display', 'inline');
		$('#nBox').css('display', 'none');
		$('#nChangeCancleBtn').css('display', 'none');
	});
	
	$('#finishBtn').on('click', function() {
		var nickname = $('#nBox').val();
		changeNickname(nickname);
		
		alert("변경 완료 되었습니다.");
		location.reload();
	});

});

function first() {	// 변경버튼 처음 눌렀을 때
	$('#nValue').css('display', 'none');
	$('#nBox').css('display', 'inline');
	$('#nChangeCancleBtn').css('display', 'inline');
}

function modAfter() {
	$('#nBox').css('display', 'none');
	$('#nValue').css('display', 'inline');
	$('#nChangeCancleBtn').css('display', 'none');
} 


function changeNickname(nickname) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/nickname',
		data : 'nickname=' + nickname,
		success : function() {
		}
	});
}