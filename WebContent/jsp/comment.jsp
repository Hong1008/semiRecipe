<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="URI" value="${pageContext.request.requestURI}" />

<link rel="stylesheet" href="/semiRecipe/css/comment.css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap"
	rel="stylesheet">
<script type="text/javascript" src="/semiRecipe/js/comment.js"></script>

<%-- <c:forEach items="" var='cmt'>
	<div class='CMT_comment'>
		<div class='CMT_icon'>${cmt.아이콘 }</div>
		<div class='CMT_info'>
			<span class='CMT_ID'>${cmt.user_id }</span><span class='CMT_date'>${cmt.수정된날짜}</span>
		</div>
		<p class='CMT_text'>${cmt.댓글내용 }</p>
	</div>
</c:forEach> --%>

<form action="">
	<div class='CMT_icon'></div>


	<textarea id='CMT_input' autocomplete='off' placeholder="댓글을 추가하세요"
		wrap="hard"></textarea>

	<div id='star_div'>
	<span>만족도를 <br>평가해주세요</span>
		<div class='star' id='star_5'></div>
		<div class='star' id='star_4'></div>
		<div class='star' id='star_3'></div>
		<div class='star' id='star_2'></div>
		<div class='star' id='star_1'></div>
		<input type="hidden" name='rate' id='rate'>
	</div>

	<br> <input type='submit' id='CMT_submit' value='댓글'> <input
		type="reset" id='CMT_reset' value='취소'>
</form>


<%------------------------- 삭제할부분 -------------------------%>
<div class='CMT_comment'>
	<div class='CMT_icon'></div>
	<div class='CMT_info'>
		<span class='CMT_ID'>아스날아스</span><span class='CMT_date'>1개월전
			수정됨</span>
		<div class='rated_div' id="1" >
			<div class= 'rate_num'  id='1'></div>
			<div class='rated' id='rate_5'></div>
			<div class='rated' id='rate_4'></div>
			<div class='rated' id='rate_3'></div>
			<div class='rated' id='rate_2'></div>
			<div class='rated' id='rate_1'></div>
			
		</div>
	</div>
	<p class='CMT_text'>글랜드 프리미어리그(EPL) 아스날 주장이었던 로랑 코시엘니(33)가 최근 황의조가
		이적한 지롱댕 보르도로 향하며 아스날 유니폼을 벗는 영상을 공개해 아스날 팬들의 분노를 샀다. 아스날은 6일(이하 한국시각)
		공식 SNS를 통해 코시엘니가 보르도로 이적했다고 발표했다. 아스날은 “코시엘니가 클럽에 헌신한 것에 감사를 표하며 행운을
		빈다”며 앞날을 응원했다. 코시엘니는 아스날에서 9년 동안 뛰며 지난 시즌 주장도 맡았지만 이번 여름 아스날의 미국 투어
		동행을 거부하며 이적을 요구했다. 결국 아스날은 코시엘니를 붙잡는 것을 포기하는 대신 이적료 약 500만 유로를 챙겼다.

		하지만 코시엘니는 이런 아스날의 배려에 무례한 행동으로 답했다. 코시엘니는 보르도 입단 발표 영상에서 아스날 유니폼을 입고
		있다가 이를 벗어 던지고 그 안에 입고 있던 보르도 유니폼으로 갈아입는 모습을 보였다. 이에 영국 기브미스포츠는 “아스날
		팬들에게 매우 무례한 행동”이라고 비판했다. 아스날 팬들 역시 “역겨운 영상이다. 9년을 보낸 클럽에게 정말 무례한 태도”,
		“용서할 수 없는 무례한 행동이다. 잘 보냈다”, “주장으로서 수치” 등 비판을 쏟아냈다.</p>
</div>

<div class='CMT_comment'>
	<div class='CMT_icon'></div>
	<div class='CMT_info'>
		<span class='CMT_ID'>피케</span><span class='CMT_date'>4시간전</span>
		<div class='rated_div' id="2">
			<div class= 'rate_num'  id='4'></div>
			<div class='rated' id='rate_5'></div>
			<div class='rated' id='rate_4'></div>
			<div class='rated' id='rate_3'></div>
			<div class='rated' id='rate_2'></div>
			<div class='rated' id='rate_1'></div>
		</div>
	</div>
	<p class='CMT_text'>"직접 나와서 말을 해야 한다." 제라드 피케(FC바르셀로나)가
		네이마르(파리생제르맹PSG)에게 말했다. 스포츠전문 매체 ESPN은 7일(한국시각) '피케는 네이마르가 바르셀로나로 복귀하고
		싶다면 반드시 말을 해야한다고 주장했다'고 보도했다.</p>
</div>
<%----------------------------- 삭제할부분 -------------------------%>
