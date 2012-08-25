<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MQTT資料管理</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<script src="../../ViewScript/MqttManageScript.js" type="text/javascript"></script>
</head>
<body onload="readData()">

<fieldset>
		<legend>MQTT資料管理</legend>
		<p />
		<form action="CreateMessage" method="post">
			<label>發送目標:</label><input id="target" name="target" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br />
			<label>發送訊息:</label><input id="message" name="message" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br /> 			
		</form>
	</fieldset>

	<!-- <span id="toolbar" class="ui-widget-header ui-corner-all"> -->
		<button id="createBtn" name="createBtn">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="deleteBtn" name="deleteBtn">刪除</button>
	<!-- </span> -->
    
	<br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
                <th align="left"><input type="checkbox" id="selectAll" /></th>
                <th>序號</th>
                <th>目標</th>
                <th>訊息</th>
            </tr>
        </thead>
        <tbody>
            <tr class="row">
            	<td></td>
                <td></td>
                <td></td>
                <td></td> 
            </tr>
        </tbody>
    </table>
    
    <div id="dialog-message" title="訊息">
		<p>請輸入必要資料</p>
	</div>

    <div id="question" style="display: none; cursor: default">
        <h1 id="msgCnt">確定要刪除資料?</h1>
        <input type="button" id="yes" name="yes" value="Yes" />
        <input type="button" id="no" name="no" value="No" />
    </div>
</body>
</html>