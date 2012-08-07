<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號管理</title>

<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<!-- 
<link href="../../Content/Site.css" rel="stylesheet" type="text/css" />
<link href="../../themes/${userBean.theme}/jquery-ui-1.8.7.custom.css" rel="stylesheet" type="text/css" />
<link href="../../Content/JQueryUICustom.css" rel="stylesheet" type="text/css" />
<link href="../../Content/ui.combobox.css" rel="stylesheet" type="text/css" />
<script src="../../Script/jquery-1.4.1.js" type="text/javascript"></script>
<script src="../../Script/jquery-ui-1.8.7.custom.min.js" type="text/javascript"></script>
<script src="../../Script/jquery.blockUI.js" type="text/javascript"></script>
<script src="../../Script/jquery.gcms.js" type="text/javascript"></script>
<script src="../../Script/jquery.combobox.js" type="text/javascript"></script>
 -->
<script>
	
	$(function() {
		$("#combobox").combobox();
		$("#submit").button();
	});
	
	
	
</script>
</head>
<body>
	<fieldset>
		<legend>帳戶資訊</legend>
		<p />
		<form action="CreateUser" method="post">
			<label>帳號 :</label><input id="accountId" name="accountId" type="text" value="" size="20" /><br /><br />
			<label>密碼 :</label><input id="accountPass" name="accountPass" type="password" value="" size="20" /><br /><br /> 
			<label>名稱 :</label><input id="accountName" name="accountName"type="text" value="" size="20" /><br /><br />
			<!-- <div class="ui-widget"> -->
				<label>帳號類別 :</label>
				<select id="combobox">
					<option value="">Select one...</option>
					<option value="Normal">一般</option>
					<option value="Guest">訪客</option>
					<option value="Admin">管理者</option>
				</select><br /><br />
			<!-- </div> -->
			<input id="submit" name="submit" type="submit" value="建立帳號" /><br /><br />
		</form>
	</fieldset>
</body>
</html>