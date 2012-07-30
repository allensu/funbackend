<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號管理</title>

<link href="../../Content/Site.css" rel="stylesheet" type="text/css" />
<link href="../../themes/redmond/jquery-ui-1.8.7.custom.css"
	rel="stylesheet" type="text/css" />
<link href="../../Content/JQueryUICustom.css" rel="stylesheet"
	type="text/css" />
<script src="../../Script/jquery-1.4.1.js" type="text/javascript"></script>
<script src="../../Script/jquery-ui-1.8.7.custom.min.js"
	type="text/javascript"></script>
<script src="../../Script/jquery.blockUI.js" type="text/javascript"></script>
<script src="../../Script/jquery.gcms.js" type="text/javascript"></script>

</head>
<body>
	<fieldset>
		<legend>帳戶資訊</legend>
		<p />
		<form action="CreateUser" method="post">
			帳號 :<input id="accountId" name="accountId" type="text" value="" /><br />
			密碼 :<input id="accountPass" name="accountPass" type="password" value="" /><br /> 
			名稱 :<input id="accountName" name="accountName"type="text" value="" /><br /> 
			<input type="submit" value="建立帳號" /><br />
		</form>
	</fieldset>

</body>
</html>