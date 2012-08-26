<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員基本資料查詢</title>

<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<script src="../../ViewScript/MqttManageScript.js" type="text/javascript"></script>

</head>
<body>
	<fieldset>
		<legend>會員基本資料查詢</legend>
		<p />
		<form action="CreateMessage" method="post">
			<label>發送目標:</label><input id="target" name="target" type="text"
				value="" size="20" /><br />
			<br /> <label>發送訊息:</label><input id="message" name="message"
				type="text" value="" size="20" /><br />
			<br /> <input id="submit" name="submit" type="submit" value="發送" /><br />
			<br />
		</form>
	</fieldset>
	<br/>
	<span id="toolbar" class="ui-widget-header ui-corner-all">
		<button id="createBtn" name="createBtn">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="updateBtn" name="updateBtn">修改</button>
	    <button id="deleteBtn" name="deleteBtn">刪除</button>
	</span>
    <br/>
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
                <th>userName</th>
                <th>displayName</th>
                <th>countryCode</th>
                <th>isOnline</th>
                <th>totalScore</th>
            </tr>
        </thead>
        <tbody>
            <tr class="row">
                <td></td>
                <td></td>
                <td></td> 
                <td></td> 
                <td></td>                
            </tr>
        </tbody>
    </table>


</body>
</html>