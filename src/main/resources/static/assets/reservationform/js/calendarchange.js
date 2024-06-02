/**
 * 예약 페이지 달력 바꾸기 함수
 */
var inMonth = new Array(); 
var inDay = new Array();
var outMonth = new Array();
var outDay = new Array();
var roomName = new Array();

var reserveDataListSize;

function reservationListAjax() {
	
	$.ajax({
		url:'getReservationList',
		method:'POST',
		dataType:'json',
		success:function(responseData,textStatus,jqXHR){
			
			reserveDataListSize = responseData.length;
			
			for (var i = 0; i < responseData.length; i++) {
				/*
					 [
						{
							"outMonth": "8",
							"inDay": "24",
							"room": "Mercury",
							"outDay": "24",
							"inMonth": "8"
						},
						{
							"outMonth": "9",
							"inDay": "19",
							"room": "Jupiter",
							"outDay": "22",
							"inMonth": "9"
						},
						...
					 ]
				 */
				
				/** 날짜 관련 배열 초기화 **/
				
				inMonth[i] = responseData[i].inMonth;
				inDay[i] = responseData[i].inDay;
				outMonth[i] = responseData[i].outMonth;
				outDay[i] = responseData[i].outDay;
				roomName[i] = responseData[i].room;
				
				/*************************/
				
			}
	
		},
		error:function(){
			alert('error...');
		}
	});

}

		function initCalendar() {
			/*
			  달력 초기화
				- for문으로 달력을 돌다가 오늘 날짜를 만나면, 그 이후부터 있는 unvailable 클래스 지우기
			 */
			var calendarList = $('table tbody td');
			for(var j = 0; j <= 34; j++){
				if($(calendarList[j]).hasClass('cur-date')){
					
					for(var z = j+1 ; z <= 34; z++){
						if($(calendarList[z]).hasClass('unvailable')) {
							$(calendarList[z]).removeClass('unvailable');
						}
					}
					
					break;
					
				}
			}
			
			for(var j = 35; j <= 69; j++){
			
				if($(calendarList[j]).hasClass('cur-date')){

					for(var z = j+1 ; z <= 69; z++){
						if($(calendarList[z]).hasClass('unvailable')){
							$(calendarList[z]).removeClass('unvailable');
						}
					}
					
					break;
					
				}
			}
			
		}

		function changeCalendar(duration, inM, inD, outM, outD) {
			
			/* 달력에 있는 날짜들 */
			var calendarList = $('table tbody td');

			//첫번째 달력
        	for (var j = 0; j <= 34; j++) {
        		
        			//1. 하루 예약 일 때
					if(duration == 0 && getFirstMonthNumber()==inM && $(calendarList[j]).text()==inD && $(calendarList[j]).hasClass('cur-month')){
						
						$(calendarList[j]).addClass('unvailable');
						
						//$(calendarList[j]).attr('onclick','');
						//$(calendarList[j]).off();
					}
					//2. 이틀이상 예약 일 때
					if(duration > 0 || duration < 0){
						if(inM != outM){
						//2-1. 다른 달 예약일때
							if(getFirstMonthNumber() === inM ){
								//alert("첫번째 달력 월: "+getFirstMonthNumber()+" / 체크아웃 월 : "+outMonth);
								//alert("두번째 달력 월: "+getLastMonthNumber()+" / 체크아웃 월 : "+outMonth);
								for(var a = inD; a <= 31; a++){
									if($(calendarList[j]).text() == a){
										$(calendarList[j]).addClass('unvailable');
									}
									//if(calendarList[j].textContent == '31' || calendarList[j].textContent == '30') break;
								}
							}
							if(getFirstMonthNumber() == outM ){
								
								for(var a = 1; a <= outD ; a++){
									if($(calendarList[j]).text() == a){ 
										$(calendarList[j]).addClass('unvailable');
									}
								}
							}
						}else if(inM == outM && getFirstMonthNumber() === inM && $(calendarList[j]).hasClass('cur-month')){
							//2-2. 같은 달 예약 일 때 
							for (var a = inD; a <= outD; a++) {
								if($(calendarList[j]).text() == a){
									$(calendarList[j]).addClass('unvailable');
								}
							}
						}
					}
				
        	}
    		
    		//두번째 달력
    		for (var j = 35; j <= 69; j++) {
        		
        		//하루 예약 일 때
					if(duration == 0 && getLastMonthNumber()==inM && $(calendarList[j]).text().trim()==inD && $(calendarList[j]).hasClass('cur-month')){
						
						$(calendarList[j]).addClass('unvailable');
						
					}
					//이틀이상 예약 일 때
					if(duration > 0 || duration < 0){
						if(inM != outM){
							if(getLastMonthNumber() === inM){
								
								for(var a = inD; a <= 31; a++){
									if($(calendarList[j]).text() == a){
										$(calendarList[j]).addClass('unvailable');
									}
									
								}
							}
							if(getLastMonthNumber() == outM){
								
								for(var a = 1; a <= outD ; a++){
									if($(calendarList[j]).text() == a){ 
										$(calendarList[j]).addClass('unvailable');
									}
								}
							}
						}else if(inM == outM && getLastMonthNumber() === inM && $(calendarList[j]).hasClass('cur-month')){
							
							for (var a = inD; a <= outD; a++) {
								if($(calendarList[j]).text() == a){
									$(calendarList[j]).addClass('unvailable');
								}
							}
						}
					}
				
        	}
        }
		

			
	    function hideLastDay(){
	    	//달력 처음 상태로 초기화 (다른 방 선택해도 이전 값이 남아있는 문제 때문에 해줌)
	    	initCalendar();
	    	
	    	//1. 현재 선택 되어 있는 방
			var currentRoomType = $('#room-type option:selected').val().trim();
			
			
			//alert("calendarList.length: "+calendarList.length);
			
				
			for (var i = 0; i < reserveDataListSize; i++) {
				/*
				var roomName = $('#room'+i).text();
				var inMonth = $('#inMonth'+i).text();
				var inDay = $('#inDay'+i).text();
				var outMonth = $('#outMonth'+i).text();
				var outDay = $('#outDay'+i).text();
				*/
				//예약기간
				
				var duration = outDay[i] - inDay[i];
				
					switch(currentRoomType) {
					    case "Mercury":
					    	
					        if(roomName[i]=="Mercury"){
					        	changeCalendar(duration, inMonth[i], inDay[i], outMonth[i], outDay[i]);
					        }
					        break;
					    case "Venus":
					        if(roomName[i]=="Venus"){
					        	changeCalendar(duration, inMonth[i], inDay[i], outMonth[i], outDay[i]);
					        }
					        break;
					    case "Mars":
					        if(roomName[i]=="Mars"){
					        	changeCalendar(duration, inMonth[i], inDay[i], outMonth[i], outDay[i]);
					        }
					        break;
					    case "Jupiter":
					    	
					        if(roomName[i]=="Jupiter"){
					        	changeCalendar(duration, inMonth[i], inDay[i], outMonth[i], outDay[i]);
					        }
					        break;
					} 
			} 

		 
	    }
	    
	    

		/*
		 * '월' 영어 ==> 숫자로 변경
		 */
		function getFirstMonthNumber() {
				var monthDiv = $('.paging > div');
				var firstMonth = $(monthDiv[0]).text().split(',')[0].trim();
				
					switch (firstMonth) {
						case "January":
							firstMonth = "1";
							break;
						case "February":
							firstMonth = "2";
							break;
						case "March":
							firstMonth = "3";
							break;
						case "April":
							firstMonth = "4";
							break;
						case "May":
							firstMonth = "5";
							break;
						case "June":
							firstMonth = "6";
							break;
						case "July":
							firstMonth = "7";
							break;
						case "August":
							firstMonth = "8";
							break;
						case "September":
							firstMonth = "9";
							break;
						case "October":
							firstMonth = "10";
							break;
						case "November":
							firstMonth = "11";
							break;
						case "December":
							firstMonth = "12";
							break;
					}
				return firstMonth;
			}
			
		function getLastMonthNumber() {
				var monthDiv = $('.paging > div');
				var secondMonth = $(monthDiv[1]).text().split(',')[0].trim();
				
					switch (secondMonth) {
						case "January":
							secondMonth = "1";
							break;
						case "February":
							secondMonth = "2";
							break;
						case "March":
							secondMonth = "3";
							break;
						case "April":
							secondMonth = "4";
							break;
						case "May":
							secondMonth = "5";
							break;
						case "June":
							secondMonth = "6";
							break;
						case "July":
							secondMonth = "7";
							break;
						case "August":
							secondMonth = "8";
							break;
						case "September":
							secondMonth = "9";
							break;
						case "October":
							secondMonth = "10";
							break;
						case "November":
							secondMonth = "11";
							break;
						case "December":
							secondMonth = "12";
							break;
					}
				return secondMonth;
			}
			
			
