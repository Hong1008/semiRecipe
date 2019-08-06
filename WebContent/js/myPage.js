$(document).ready(function() {
	$('#pwChangeBtn').on('click', function() {
		if ($('.pwHide').css('display') == "none") { // 변경버튼 처음 눌렀을 때
			$('.pwHide').css('display','table-cell');
		} else {
			var now_pw = $('#now_pw').val();
			var new_pw = $('#new_pw').val();
			var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
			
			if (now_pw == "" || new_pw == "") {
				alert("현재 비밀번호와 변경 비밀번호를 모두 입력하셔야 합니다.");
			} else if (!isPW.test(new_pw)) {
				alert("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
			} else {
				$.ajax({
					type : 'POST',
					dataType : 'text',
					url : '/semiRecipe/recipe/checkNowPw',
					data : 'loginId='+$('#loginId').text()+'&now_pw=' + now_pw,
					success : function(res) {
						if (res != "") {
							alert(res);
						} else {
							var chk = confirm('수정하시겠습니까?');
							if (chk) {				// 확인을 누르면
								pwChangeAfter();
							}
						}
					}
				});
			}
			
			return false;
		}
	});
	
	
	
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
								nChangeAfter();
							}
						}

					}
				});
			}

			return false;
		}

	});

	$('#nChangeCancleBtn').on('click', function() {			// 닉네임 변경 버튼 누른 후 취소 버튼 눌렀을 때
		$('#nValue').css('display', 'inline');
		$('#nBox').css('display', 'none');
		$('#nChangeCancleBtn').css('display', 'none');
	});
	
	$('#birthChangeBtn').on('click', function() {		// 생년월일 수정 버튼 눌렀을 때
		var year = $('#yy').val();
		var month = $('#mm').val();
		var day = $('#dd').val();
		
		if(month<10){
			month = "0"+month;
		}
		if(day<10){
			day = "0"+day;
		}
		
		
		if($('.newBirthday').css('display')=="none"){		// 처음 눌렀을 때 (수정 전)
			$('#birthValue').css('display','none');
			$('.newBirthday').css('display','table-cell');
			getBirthday();
		} else {										// 수정 후에 눌렀을 때
			if(year!="" && month!="" && day!="") {
				var chk = confirm('수정하시겠습니까?');
				if (chk) {				// 확인을 누르면
					$('#birthValue').text(year+"-"+month+"-"+day);
					birthChangeAfter();
				}
			}else {
				alert("생년월일을 정확하게 선택해주세요.");
				return false;
			}
			
		}
		
		
	});
	
	$('#yy').change(function(){
		$('#mm').val("");
		$('#dd').val("");
		removeMonth();
		removeDay();
		appendMonth();
	});
	
	$('#mm').change(function(){
		$('#dd').val("");
		var day = validDate($('#yy').val(), $('#mm').val());
		removeDay();
		appendDay(day);
	});
	
	
	$('#modCancleBtn').on('click', function() {				// 제일 하단에 취소 버튼 눌렀을 때
		var cancleCheck = confirm('수정을 취소하시겠습니까?'); 
		if(cancleCheck) {
			location.href='/semiRecipe/recipe/home';
		} 
	});
	
	$('#modFinishBtn').on('click', function() {				// 제일 하단에 수정 완료 버튼 눌렀을 때
		var new_nickname = $('#nValue').text();
		var new_pw = $('#new_pw').val();
		var new_birth = $('#birthValue').text();
		
		var modCheck = confirm('수정하시겠습니까?'); 
		if(modCheck) {
			
			if(new_nickname!="") {
				updateNickname(new_nickname);
			}else{
				alert("닉네임 변경 버튼을 누르세요");
				return false;
			}
			
			if(new_pw!="") {
				updatePassword(new_pw);
			}
			
			if(new_birth!="") {
				updateBirthday(new_birth);
			}
			
			alert('수정 완료되었습니다.');
			var goHome = confirm('홈으로 가시겠습니까?');
			
			if(goHome){
				location.href='/semiRecipe/recipe/home';
			}
			
		} 
	});
	

});

function first() {	// 변경버튼 처음 눌렀을 때
	$('#nValue').css('display', 'none');
	$('#nBox').css('display', 'inline');
	$('#nChangeCancleBtn').css('display', 'inline');
}

function nChangeAfter() {
	$('#nBox').css('display', 'none');
	$('#nValue').css('display', 'inline');
	$('#nChangeCancleBtn').css('display', 'none');
} 

function pwChangeAfter() {
	$('.pwHide').css('display','none');
}

function birthChangeAfter() {
	$('#birthValue').css('display','inline');
	$('.newBirthday').css('display','none');
} 


function updatePassword(new_pw) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/pw',
		data : 'pw=' + new_pw,
		success : function() {
		}
	});
}

function updateNickname(new_nickname) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/nickname',
		data : 'nickname=' + new_nickname,
		success : function() {
		}
	});
}

function updateBirthday(new_birth) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/birthday',
		data : 'birthday=' + new_birth,
		success : function() {
		}
	});
}

function getYear() {
	var birthValue = $('#birthValue').text();
	var beforeYear = birthValue.split("-")[0];

	var date = new Date();
	var year = date.getFullYear();

	for(var i=year;i>=1900;i--) {
		$('#yy').append(new Option(i+"년",i));                        
	}
	
	$("#yy").val(beforeYear).prop("selected", true);
}

function getMonth() {
	var birthValue = $('#birthValue').text();
	var beforeMonth = birthValue.split("-")[1];
	
	for(var i=1;i<=12;i++){
		$('#mm').append(new Option(i+"월",i));
	}
	
	$("#mm").val(Number(beforeMonth)).prop("selected", true);
}

function getDay(day) {
	var birthValue = $('#birthValue').text();
	var beforeDay = birthValue.split("-")[2];
	
	for(var i=1;i<=day;i++) {
		$('#dd').append(new Option(i+"일",i));
	}
	
	$("#dd").val(Number(beforeDay)).prop("selected", true);
}

function getBirthday() {
	var birthValue = $('#birthValue').text();
	var year = birthValue.split("-")[0];
	var month = birthValue.split("-")[1];
	
	getYear();
	getMonth();
	var day = validDate(year, month);
	getDay(day);
}

function appendYear() {
	var date = new Date();
	var year = date.getFullYear();
	var selectValue = document.getElementById("yy");

	for(var i=1900;i<=year;i++) {
		selectValue.add(new Option(i+"년",i));                        
	}
}

function appendMonth() {
	var selectValue = document.getElementById("mm"); 

	for(var i=1;i<=12;i++){
		selectValue.add(new Option(i+"월",i));
	}
}

function removeMonth() {
	$("#mm.sel option:gt(0)").remove();
}


function validDate(year, month) {
    var monthArr = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var day;
    if(month !== "2" && $.isNumeric(month)) {
        day = monthArr[Number(month) - 1];
    }
    else {
        if($.isNumeric(year)) {
            var intYear = Number(year);
            if((intYear%4 === 0 && intYear%100 !== 0) || intYear%400 === 0) {
                day = 29;
            }
            else {
                day = 28;
            }
        }
        else {
            day = 31;
        }
    }
    return day;
};

function appendDay(day) {
	var selectValue = document.getElementById("dd");

	for(var i=1;i<=day;i++) {
			selectValue.add(new Option(i+"일",i));
	}
} 

function removeDay() {
	$("#dd.sel option:gt(0)").remove();
}
