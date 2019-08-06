var showErrorMsg="";

$(document).ready(function() {
	appendYear();
	
	$('input[value="회원가입"]').click(function(){
		if($('.error_next_box').text()!=""){
			alert("입력한 값을 확인해주세요.");
			return false;
		}else{
			$('form').attr('action','register?id='+$('#id').val());
			$('form').submit();
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
	
	$('input[type="reset"]').on('click', function() {
		var chk = confirm("초기화 하시겠습니까?");
		if(!chk)	// 취소이면
			return false;
	});
	
	$('#id').blur(function(){
		var eventId = $(this).attr('id');
		var id = $("#id").val();
		var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
		
		if (id == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!isID.test(id)) {
			showErrorMsg = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if(id != "" && isID.test(id)){
			$.ajax({
                type:'POST',
                dataType:'text',
                url:'/semiRecipe/recipe/checkID',
                data:'id='+$(this).val(),
                success: function(res) {
                	showErrorMsg = res;
                	changeErrorMsg(eventId, showErrorMsg);
                }
			});
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	$('#pw').blur(function(){
		var eventId = $(this).attr('id');
		var pw = $('#pw').val();
		var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;

		if (pw == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!isPW.test(pw)) {
			showErrorMsg = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			changeErrorMsg(eventId, showErrorMsg);
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	$('#pwConfirm').blur(function(){
		var eventId = $(this).attr('id');
		var pwConfirm = $('#pwConfirm').val();
		
		if ($('#pw').val()!=pwConfirm) {
			showErrorMsg = "비밀번호를 다시 확인해주세요";
			changeErrorMsg(eventId, showErrorMsg);
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	$('#nickname').blur(function() {
		var eventId = $(this).attr('id');
		var nickname = $('#nickname').val();
		var nonchar = /^[가-힣a-zA-Z0-9]{2,10}$/;

		if (nickname == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!nonchar.test(nickname)) {
			showErrorMsg = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if(nickname != "" && nonchar.test(nickname)){
			$.ajax({
                type:'POST',
                dataType:'text',
                url:'/semiRecipe/recipe/checkNickname',
                data:'nickname='+$(this).val(),
                success: function(res) {
                	showErrorMsg = res;
                	changeErrorMsg(eventId, showErrorMsg);
                }
			});
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
		
	});
	
	var year = $('#yy').val();
	var month = $('#mm').val();
	var day = $('#dd').val();
		
});

// option 추가 new Option("option text", "value");
function appendYear() {
	var date = new Date();
	var year = date.getFullYear();
	var selectValue = document.getElementById("yy");

	for(var i=year;i>=1900;i--) {
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

function changeErrorMsg(eventId, showErrorMsg){
	console.log(eventId);
	console.log(showErrorMsg);
	$('#'+eventId+'Msg').text(showErrorMsg);
}