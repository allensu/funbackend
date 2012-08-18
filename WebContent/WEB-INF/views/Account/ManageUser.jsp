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
<!-- 
<style>
	#toolbar {
		padding: 20px 8px;
	}
</style>
 -->
<script>
	$.fx.speeds._default = 1000;
	$(function() {
		 // Form View
		  $("#dialog-form").dialog({
	            autoOpen: false,
	            show: "blind",
	            modal: true,
	            hide: "explode"
	        });
		  
		$("#combobox").combobox();
		$("#createSubmitBtn").button();
		
		// Create Btn
		$("#createBtn").button({
            icons: {
                primary: "ui-icon-circle-plus"
            }
        }).click(function () {
            $("#dialog-form").dialog("open");
            return false;
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
		
		// Update Btn
		$("#updateBtn").button({
            icons: {
                primary: "ui-icon-wrench"
            }
        }).click(function () {

        });
		
		// Delete Btn
		$("#deleteBtn").button({
            icons: {
                primary: "ui-icon-circle-close"
            }
        }).click(function () {
            $.blockUI({ message: $('#question'), css: { width: '275px'} });
        });
		
		$('#yes').click(function () {

            $.blockUI({ message: '<div>刪除資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });

            //取得所選擇的SQ
            var sq = $('.ui-state-highlight')[1].innerText;

            $.ajax({
                type: "POST",
                url: '/GasManager/PaymentControl_Delete/' + sq,
                cache: false,
                dataType: "json",
                success: function (data) {
                    alert(data.result);
                },
                error: function (xhr) {
                    alert(xhr);
                },
                complete: function () {

                    $.unblockUI();
                }
            });
        });

        $('#no').click(function () {
            $.unblockUI();
            return false;
        });
        
        var oTable = $('#jtable').dataTable({
            //"sScrollY":  '100%',
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
	});
	
	/*
    
	function createBegin() {
        $("#dialog-form").block({ message: '<div>處理中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
    }

    function createComplate() {
        $("#dialog-form").unblock();
    }

    function createSuccess() {
     
    }

    function createFailure() {

    }
    */
    
    function readData() {

        $.getJSON('/funbackend/controller/Account/ReadUser', function (data) {

            //           //清空頁面上的資料
            //                    $('#jtable > tbody > tr').remove();
            $('#jtable').dataTable().fnClearTable(true);
            
            $.each(data, function (k, v) {
            
                $('#jtable').dataTable().fnAddData([
	                v.accountId,
	                v.accountName,
	                v.category,
	                //v.lastLoginDateTime,
	                //v.createDateTime
	                jsonDate2Format(v.lastLoginDateTime, "yyyy/m/d TT hh:MM"),
                    jsonDate2Format(v.createDateTime, "yyyy/m/d TT hh:MM")  
                ]);
            });

            $.unblockUI();
        });
    }
    
</script>
</head>
<body>
	<!-- 
	<fieldset>
		<legend>帳戶資訊</legend>
		<p />
		<form action="CreateUser" method="post">
			<label>帳號 :</label><input id="accountId" name="accountId" type="text" value="" size="20" /><br /><br />
			<label>密碼 :</label><input id="accountPass" name="accountPass" type="password" value="" size="20" /><br /><br /> 
			<label>名稱 :</label><input id="accountName" name="accountName"type="text" value="" size="20" /><br /><br />			
				<label>帳號類別 :</label>
				<select id="combobox">
					<option value="">Select one...</option>
					<option value="Normal">一般</option>
					<option value="Guest">訪客</option>
					<option value="Admin">管理者</option>
				</select><br /><br />
			<input id="submit" name="submit" type="submit" value="建立帳號" /><br /><br />
		</form>
	</fieldset>
	-->
	<!-- <input id="createBtn" name="createBtn" class="createBtn" type="button" value="新增2" /> -->
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
                <select id="combobox">
					<option value="">Select one...</option>
					<option value="Normal">一般</option>
					<option value="Guest">訪客</option>
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