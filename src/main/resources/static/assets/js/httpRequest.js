var xhr = null;

function getXMLHttpRequest() {
	var localXhr=null;
	if(window.XMLHttpRequest){
		localXhr=new XMLHttpRequest();
	}else{
		localXhr=null;
	}
	return localXhr;
}


function sendRequest(url, params, callback, method) {
	
	xhr = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET';
	/*
	if(method!=null && method!=''){
		httpMethod=method;
	}else{
		httpMethod='GET';
	}
	*/
	
	if (httpMethod != 'GET' && httpMethod != 'POST') {
		httpMethod = 'GET';
	}
	
	var httpParams = (params == null || params == '') ? null : params;
	/*
	if(params == null || params == ''){
		httpParams=null;
	}else{
		httpParams=params;
	}
	*/
	var httpUrl = url;
	
	if (httpMethod == 'GET' && httpParams != null) {
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	xhr.open(httpMethod, httpUrl, true);
	xhr.setRequestHeader(
		'Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = callback;
	xhr.send(httpMethod == 'POST' ? httpParams : null);
	/*
	if(httpMethod=='POST'){
		xhr.send(httpParams);
	}else{
		xhr.send(null);
	}
	*/
	
}










