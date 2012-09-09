<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MQTT資料管理</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<!-- <script src="../../ViewScript/MqttManageScript.js" type="text/javascript"></script> -->
<script type="text/javascript">

$.fx.speeds._default = 1000;
$(function() {
	$("#toolBar").buttonset();
	
	$("#submit").button();

	$('#selectAll').click(function() {
		
		var checkBoxes = $("input[name=dataId]");
		
		if ($('#selectAll').attr('checked')) {
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == false) {
					$(this).attr('checked', true);
				}
			});
		} else {
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
		}
	});
	
	// Create Btn
	$("#createBtn").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {

		createData();
	});

	// Read Btn
	$("#readBtn").button({
		icons : {
			primary : "ui-icon-search"
		}
	}).click(function() {
		$.blockUI({
			message : '<div>載入資料中...</div>',
			overlayCSS : {
				backgroundColor : '#4297D7'
			}
		});
		readData();
	});

	// Delete Btn
	$("#deleteBtn").button({
		icons : {
			primary : "ui-icon-circle-close"
		}
	}).click(function() {
		var checkedCount = $('#jtable input:checked').length;

		if (checkedCount > 0)
			$.blockUI({
				message : $('#question'),
				css : {
					width : '275px'
				}
			});
	});

	$('#yes').button().click(function() {
		$.blockUI({
			message : '<div>刪除資料中...</div>',
			overlayCSS : {
				backgroundColor : '#4297D7'
			}
		});
		deleteData();
	});

	$('#no').button().click(function() {
		$.unblockUI();
		return false;
	});

	var oTable = $('#jtable').dataTable({
		// "sScrollY": '100%',
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null,
		              null,
		              null
		              ],
		"fnDrawCallback" : function() {
			
			$('#selectAll').attr('checked', false);
			var checkBoxes = $("input[name=dataId]");
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
		}
	});
});

function dataEachRowAdd(data)
{
	$('#jtable').dataTable().fnClearTable(true);
	
	$.each(data, function(k, v) {
		
		$('#jtable').dataTable().fnAddData([
						"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>", 
						v.serial, 
						v.target,
						v.message ]);			
	});
}

function readData() {

	$.getJSON('/funbackend/controller/Mqtt/ReadMessage', function(data) {

		dataEachRowAdd(data);

		$.unblockUI();
	});
}

function createData() {
	
	if($('#target').val() == "" || $('#message').val() == "")
	{
		$( "#dialog-message" ).dialog({
			modal: true,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		return;
	}
	
	var postData = {
		target : $('#target').val(),
		message : $('#message').val()
	};

	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Mqtt/CreateMessage",
		data : postData,
		success : function(data) {

			

			dataEachRowAdd(data);

			$.unblockUI();

			$('#target').val("");
			$('#message').val("");
		},
		dataType : "json",
		traditional : true
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

			dataEachRowAdd(data);

			$.unblockUI();

		},
		dataType : "json",
		traditional : true
	});
}
</script>
</head>
<body onload="readData()">
	<div id="toolBar">
		<button id="createBtn" name="createBtn">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="updateBtn" name="updateBtn">修改</button>
	    <button id="deleteBtn" name="deleteBtn">刪除</button>
	</div>
	<fieldset>
		<legend>MQTT資料管理</legend>
		<p />
		<form action="CreateMessage" method="post">
			<label>發送目標:</label><input id="target" name="target" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br />
			<label>發送訊息:</label><input id="message" name="message" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /><br /><br /> 			
		</form>
	</fieldset>
	<br/>
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
    
    <div id="dialog-message" title="訊息" style="display: none;">
		<p>請輸入必要資料</p>
	</div>

    <div id="question" style="display: none; cursor: default">
        <h1 id="msgCnt">確定要刪除資料?</h1>
        <input type="button" id="yes" name="yes" value="Yes" />
        <input type="button" id="no" name="no" value="No" />
    </div>
</body>
</html>