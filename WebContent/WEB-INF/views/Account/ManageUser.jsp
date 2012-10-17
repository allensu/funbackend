<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號管理</title>

<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<!-- <script src="../../ViewScript/ManageUserScript.js" type="text/javascript"></script> -->
<script type="text/javascript">

$.fx.speeds._default = 1000;
$(function() {
	$("#toolBar").buttonset();
	
	// 全選
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

	// Form View
	$("#dialog-form").dialog({
		autoOpen : false,
		modal : true
	});

	$("#category").combobox();
	$("#createSubmitBtn").button();

	// Create Btn
	$("#createBtn").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {
		
		executeFun("NewAuth");
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

	// Update Btn
	$("#updateBtn").button({
		icons : {
			primary : "ui-icon-wrench"
		}
	}).click(function() {

	});

	// Delete Btn
	$("#deleteBtn").button({
		icons : {
			primary : "ui-icon-circle-close"
		}
	}).click(function() {

		executeFun("DeleteAuth");
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

function executeFun(authType)
{
	var postData = {
			authType : authType,
			menuItemId : "ManageUser"
    	};
    	  
        $.ajax({
      		type : "GET",
      		url : "/funbackend/controller/Account/ManageMenuAuth/HasFunAuth",
      		data : postData,
      		success : function(data) {
      					
				//alert(data.resultCode); 
				
				if(data.resultCode == -1)
				{
					alert("使用者無此權限");
				}
				else if(authType == "NewAuth")
				{
					$("#dialog:ui-dialog").dialog( "destroy" );
					$("#dialog-form").dialog("open");
				}
				else if(authType == "DeleteAuth")
				{
					var checkedCount = $('#jtable input:checked').length;

					if (checkedCount > 0)
						$.blockUI({
							message : $('#question'),
							css : {
								width : '275px'
							}
						});
				}	
      		}
        });	
}

/*
 * 
 * function createBegin() { $("#dialog-form").block({ message: '<div>處理中...</div>',
 * overlayCSS: { backgroundColor: '#4297D7'} }); }
 * 
 * function createComplate() { $("#dialog-form").unblock(); }
 * 
 * function createSuccess() {
 *  }
 * 
 * function createFailure() {
 *  }
 */

function dataEachRowAdd(data) {
	$('#jtable').dataTable().fnClearTable(true);

	$.each(data, function(k, v) {

		$('#jtable').dataTable()
				.fnAddData(
						[
								"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>",
								v.accountId,
								v.accountName,
								v.category,
								// v.lastLoginDateTime,
								// v.createDateTime
								jsonDate2Format(v.lastLoginDateTime,
										"yyyy/m/d TT hh:MM"),
								jsonDate2Format(v.createDateTime,
										"yyyy/m/d TT hh:MM") ]);
	});
}

function readData() {

	$.getJSON('/funbackend/controller/Account/ReadUser', function(data) {

		// //清空頁面上的資料
		// $('#jtable > tbody > tr').remove();

		dataEachRowAdd(data);

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
		url : "/funbackend/controller/Account/DeleteAccount",
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
</head>
<body onload="readData()">
	<br/>
	<div id="toolBar">
		<button id="createBtn" name="createBtn">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="updateBtn" name="updateBtn" disabled="disabled">修改</button>
	    <button id="deleteBtn" name="deleteBtn">刪除</button>
	</div>	
    <br/>
    
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll" /></th>
                <th>帳號 </th>
                <th>名稱</th>
                <th>類別</th>
                <th>最近登入時間</th>
                <th>建立時間</th>
            </tr>
        </thead>
        <tbody>
            <tr class="row">
            	<td></td>
                <td></td>
                <td></td>
                <td></td> 
                <td></td> 
                <td></td>                
            </tr>
        </tbody>
    </table>
    
    <div id="dialog-form" title="新增帳號">
    
    	<form action="CreateUser" method="post">
        <fieldset>
            <div class="editor-label">
                帳號
            </div>
            <div class="editor-field">
            	<input id="accountId" name="accountId" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>
            <div class="editor-label">
                密碼
            </div>
            <div class="editor-field">
            	<input id="accountPass" name="accountPass" type="password" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>
            <div class="editor-label">
                名稱
            </div>
            <div class="editor-field">
            	<input id="accountName" name="accountName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />               
            </div>
            <div class="editor-label">
                帳號類別 
            </div>
            <div class="editor-field">
                <select id="category" name="category">
					<option value="Guest" selected>訪客</option>
					<option value="Normal">一般</option>
					<option value="Admin">管理者</option>
				</select>
            </div><br />
            <input id="createSubmitBtn" type="submit" value="存檔" />
        </fieldset>
        </form>
    </div>
    <div id="question" style="display: none; cursor: default">
        <h1 id="msgCnt">
            確定要刪除此筆資料?</h1>
        <input type="button" id="yes" value="Yes" />
        <input type="button" id="no" value="No" />
    </div>
     
</body>
</html>