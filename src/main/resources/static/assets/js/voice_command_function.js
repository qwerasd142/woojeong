/**
 * Annyang 을 이용한 음성인식 함수~~
 */

function ifMoving(search){
	if(search == "예약"){
		location.href = "https://192.168.12.12:8443/superTeam/reservation";
	}else if(search == "홈"){
		location.href = "https://192.168.12.12:8443/superTeam/home";
	}
	else if(search == "방"){
		location.href = "https://192.168.12.12:8443/superTeam/rooms";
	}
	else if(search == "룸"){
		location.href = "https://192.168.12.12:8443/superTeam/rooms";
	}
	else if(search == "시설"){
		location.href = "https://192.168.12.12:8443/superTeam/facility";
	}
	else if(search == "뉴스"){
		location.href = "https://192.168.12.12:8443/superTeam/news_list";
	}
	else if(search == "이벤트"){
		location.href = "https://192.168.12.12:8443/superTeam/event_list";
	}
	else if(search == "할인"){
		location.href = "https://192.168.12.12:8443/superTeam/sales_list";
	}
	else if(search == "세일"){
		location.href = "https://192.168.12.12:8443/superTeam/sales_list";
	}
	else if(search == "리뷰"){
		location.href = "https://192.168.12.12:8443/superTeam/review_list";
	}
	else if(search == "큐엔에이"){
		location.href = "https://192.168.12.12:8443/superTeam/qna_list";
	}
}
$(function() {

	$('#myModal')
			.on(
					'show.bs.modal',
					function() {

						if (annyang) {

							// define the functions our commands will run.
							var showFlickr = function(tag) {
								alert(tag);
							};

							var testFunc = function(msg) {
								alert('testFunc');
								alert(msg);
							};

							// Let's define our first command. First the text we
							// expect, and then the function it should call
							var commands = {

								// 페이지 기능 시작
								'예약' : function() {
									
									location.href = "https://192.168.12.12:8443/superTeam/reservation";
								},
								'가입' : function() {
									
									location.href = "https://192.168.12.12:8443/superTeam/member_join_form";
								},
								'회원 가입' : function() {
									
									location.href = "https://192.168.12.12:8443/superTeam/member_join_form";
								},
								'로그인' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/member_login_form";
								},
								'방' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/rooms";
								},
								'룸' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/rooms";
								},
								'예약확인' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/reservation";
								},
								'행사' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/event_list";
								},
								'이벤트' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/event_list";
								},
								'큐엔에이' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/qna_list";
								},
								'질문' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/qna_list";
								},
								'후기' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/review_list";
								},
								'리뷰' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/review_list";
								},
								'세일' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/sales_list";
								},
								'할인' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/sales_list";
								},
								'시설' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/facility";
								},
								'뉴스' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/news_list";
								},
								'홈' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/home";
								},
								'전화' : function() {
									if(confirm("Expedition 펜션에 전화를 하시겠습니까??")){
										callNumber('010-9006-6641');
									}else{
										alert("알겠습니다.");
									}
								},
								'전화걸래' : function() {
									if(confirm("Expedition 펜션에 전화를 하시겠습니까??")){
										callNumber('010-9006-6641');
									}else{
										alert("알겠습니다.");
									}
								},
								'사장나와' : function() {
									if(confirm("Expedition 사장에게 전화를 하시겠습니까??")){
										callNumber('010-9006-6641');
									}else{
										alert("알겠습니다.");
									}
								},
								'전화번호' : function() {
									if(confirm("Expedition 펜션에 전화를 하시겠습니까??")){
										callNumber('010-9006-6641');
									}else{
										alert("알겠습니다.");
									}
								},
								// 페이지 기능 끝
								
								// 다른페이지 이동 시작
								'구글' : function() {
									location.href = "https://www.google.com";
								},
								'google' : function() {
									location.href = "https://www.google.com";
								},
								'네이버' : function() {
									alert('거긴 가기 싫은데요..');
								},
								'뒤로':function() {
									location.href="javascript:history.back()";
								},
								'앞으로':function() {
									location.href="javascript:history.go(1)";
								},
								'새로고침':function() {
									location.href="javascript:location.reload()";
								},
								// 다른 페이지 이동 끝

								// 방 이름 시작
								'주피터' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_jupiter";
								},
								'쥬피터' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_jupiter";
								},
								'쥬피러' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_jupiter";
								},
								'머큐리' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_mercury";
								},
								'먹휴리' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_mercury";
								},
								'머쿠리' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_mercury";
								},
								'마스' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_mars";
								},
								'말스' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_mars";
								},
								'비너스' : function() {
									location.href = "https://192.168.12.12:8443/superTeam/room_venus";
								},
								// 방 이름 끝

								// annyang will capture anything after a splat
								// (*) and pass it to the function.
								// e.g. saying "Show me Batman and Robin" will
								// call showFlickr('Batman and Robin');

								// 문장 받기 시작
								'*search (페이지) 보여 줘' : function(search) {
									alert(search+"으로 이동합니다.");
									ifMoving(search);
								},
								'*search (페이지) 가자' : function(search) {
									alert(search+"으로 이동합니다.");
									ifMoving(search);
								},
								'보여 줘 *msg' : function(search) {
									alert(search+"으로 이동합니다.");
									ifMoving(search);
								},
								// 문장받기 끝

								// 대화 시작
								'안녕' : function() {
									alert('안녕하세요. 펜션봇 입니다.');
								},
								'하이' : function() {
									alert('안녕하세요. 펜션봇 입니다.');
								},
								'안녕하세요' : function() {
									alert('안녕하세요. 펜션봇 입니다.');
								},
								'반가워' : function() {
									alert('방가방가. 펜션봇 입니다.');
								},
								'반갑습니다' : function() {
									alert('반가워요.');
								},
								'*msg 당' : function(msg) {
									alert(msg+'?');
									alert('당? 당이라고~'+'\n'+
											'정렬 정렬 김정렬~'+'\n'+
											'숭그리당당 숭당당 수그수구 당당 숭당당~~');
								},
								'야' : function() {
									alert('왜?');
								},
								'길 찾아줘' : function() {
									alert('길은 예약 페이지에 나옵니다. 예약페이지로 이동합니다.');
									location.href = "https://192.168.12.12:8443/superTeam/reservation";
								},
								'아' : function() {
									alert('말씀하세요');
								},
								'아아' : function() {
									alert('요구사항을 말해주세요');
								}
							// 대화 끝

							};

							annyang.setLanguage("ko");

							// OPTIONAL: activate debug mode for detailed
							// logging in the console
							annyang.debug();

							// Add our commands to annyang
							annyang.addCommands(commands);

							// Start listening. You can call this here, or
							// attach this call to an event, button, etc.
							annyang.start({
								autoRestart : true,
								continuous : false
							});

						}

					});

	$('#myModal').on('hidden.bs.modal', function() {
		// 모달이 닫히면 annyang 끝
		annyang.abort();
	});

	annyang.addCallback('resultMatch',
			function(userSaid, commandText, phrases) {
				console.log(userSaid); // sample output: 'hello'
				console.log(commandText); // sample output: 'hello (there)'
				console.log(phrases); // sample output: ['hello', 'halo',
										// 'yellow', 'polo', 'hello kitty']

				annyang.abort();

				// modal 닫기
				$('#myModal').modal('hide')

			});

	annyang.addCallback('resultNoMatch', function() {
		// alert("다시 말하세요");

		annyang.abort();
		$('#failMsgDiv').css('display', 'block');
		$('.modal-footer').css('display', 'block');
		$('#loadingDiv').css('display', 'none');

	});

	$('#restartVoice').click(function() {

		annyang.resume();
		$('#failMsgDiv').css('display', 'none');
		$('.modal-footer').css('display', 'none');
		$('#loadingDiv').css('display', 'block');

	});

});
