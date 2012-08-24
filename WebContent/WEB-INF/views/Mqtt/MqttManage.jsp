<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MQTT資料管理</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<script>
	
	$.fx.speeds._default = 1000;
	$(function() {
		$("#submit").button();
		
		// Create Btn
		$("#createBtn").button({
            icons: {
                primary: "ui-icon-circle-plus"
            }
        }).click(function () {

			
        	
        	
        });
		
		// Read Btn
		$("#readBtn").button({
            icons: {
                primary: "ui-icon-search"
            }
        }).click(function () {
            $.blockUI({ message: '<div>載入資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
            readData();
        });
		
		// Delete Btn
		$("#deleteBtn").button({
            icons: {
                primary: "ui-icon-circle-close"
            }
        }).click(function () {
            $.blockUI({ message: $('#question'), css: { width: '275px'} });
            
            //deleteData();
        });
		
		var oTable = $('#jtable').dataTable({
            //"sScrollY":  '100%',
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
		
		readData();
	});
	

	$('#yes').click(function () {

        $.blockUI({ message: '<div>刪除資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });

        deleteData();
    });

    $('#no').click(function () {
        $.unblockUI();
        return false;
    });
    
    function readData() {

        $.getJSON('/funbackend/controller/Mqtt/ReadMessage', function (data) {

            $('#jtable').dataTable().fnClearTable(true);
            
            $.each(data, function (k, v) {
            
                $('#jtable').dataTable().fnAddData([
                    "<input id='id' name='id' type='checkbox' value='" + v.id + "'/>",                                
	                v.serial,
	                v.target,
	                v.message 
                ]);
            });

            $.unblockUI();
        });
    }
    
    function deleteData() {
    	
		var idsArray = new Array();

		$('#jtable input:checked').each(function() {
			idsArray.push(this.value);
		});

		var postData = {
			ids : idsArray
		};

		$.ajax({
			type : "POST",
			url : "/funbackend/controller/Mqtt/DeleteMessage",
			data : postData,
			success : function(data) {
				//alert(data);
				
				$('#jtable').dataTable().fnClearTable(true);
	            
	            $.each(data, function (k, v) {
	            
	                $('#jtable').dataTable().fnAddData([
	                    "<input id='id' name='id' type='checkbox' value='" + v.id + "'/>",                                
		                v.serial,
		                v.target,
		                v.message 
	                ]);
	            });

	            $.unblockUI();
				
				
				//$.unblockUI();
			},
			dataType : "json",
			traditional : true
		});
	}
</script>
</head>
<body>

<fieldset>
		<legend>MQTT資料管理</legend>
		<p />
		<form action="CreateMessage" method="post">
			<label>發送目標:</label><input id="target" name="target" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br />
			<label>發送訊息:</label><input id="message" name="message" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br /> 			
			<input id="submit" name="submit" type="submit" value="發送" /><br /><br />
		</form>
	</fieldset>
	
	<br/>
	<span id="toolbar" class="ui-widget-header ui-corner-all">
		<button id="createBtn" name="createBtn">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="deleteBtn" name="deleteBtn">刪除</button>
	</span>
    
	<br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
                <th></th>
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
    
    <div id="question" style="display: none; cursor: default">
        <h1 id="msgCnt">確定要刪除資料?</h1>
        <input type="button" id="yes" value="Yes" />
        <input type="button" id="no" value="No" />
    </div>
</body>
</html>