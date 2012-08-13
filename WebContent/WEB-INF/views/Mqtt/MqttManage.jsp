<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<script>
	
	$(function() {
		$("#submit").button();
	});
	
</script>
</head>
<body>

<fieldset>
		<legend>MQTT資料管理</legend>
		<p />
		<form action="CreateMessage" method="post">
			<label>發送目標:</label><input id="target" name="target" type="text" value="" size="20" /><br /><br />
			<label>發送訊息:</label><input id="message" name="message" type="text" value="" size="20" /><br /><br /> 			
			<input id="submit" name="submit" type="submit" value="發送" /><br /><br />
		</form>
	</fieldset>
</body>
</html>