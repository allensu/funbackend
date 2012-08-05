<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入 GCMS</title>

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

<script type="text/javascript" language="javascript">
	$(function() {
		$("input:submit").button();
	});
</script>

</head>
<body>
	<center>
		<div style="width: 300px; height: 300px;">
			<h2>系統登入</h2>
			<form action="Login" method="post">

				<div>
					<fieldset>
						<legend>帳戶資訊</legend>
						<p />
						<table width="100%">
							<tr>
								<td>使用者名稱</td>
								<td>
									<div class="editor-field">
										<input class="ui-widget-content ui-corner-all" id="userName"
											name="account" type="text" value="" /> <br />
									</div>
								</td>
							</tr>
							<tr>
								<td>密碼</td>
								<td>
									<div class="editor-field">
										<input class="ui-widget-content ui-corner-all" id="password"
											name="password" type="text" value="" /> <br />
									</div>
								</td>
							</tr>
							<tr>
								<td>主題</td>
								<td>
									<div>
										<select id="Theme" name="Theme">
											<%--<option value="base">Base</option>--%>
											<option value="redmond">Redmond</option>
											<option value="smoothness">Smoothness</option>
											<option value="start">Start</option>
											<option value="ui-lightness">UI lightness</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left"></td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<p>
										<input type="submit" value="登入" />
									</p>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>

			</form>

		</div>
	</center>
</body>
</html>