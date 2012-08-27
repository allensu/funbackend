<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>會員基本資料查詢</title>

<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<!-- <script src="../../ViewScript/MemberDataQueryScript.js" type="text/javascript"></script> -->
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
	
	$("#genderQ").combobox();
		
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
	
	
	
	var oTable = $('#jtable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "aoColumns": [
		              { "bSortable": false },
		              null,
		              null,
		              null,
		              null,
		              null,
		              null,
		              null,
		              null,
		              null,
		              null,
		              { "bSortable": false },
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
	
	// Form View
	$("#dialog-form").dialog({
		autoOpen : false,
		modal : true
	});
	
	// Form Control Define
	$("#fake").combobox();
	$("#deleted").combobox();
	$("#updateSubmitBtn").button();
	
});


function readData() {

    $.getJSON('/funbackend/controller/Member/MemberDataQuery/Read', function (data) {

    	dataEachRowAdd(data);

        $.unblockUI();
    });
}



function showDetailEvent(id)
{
	//alert(id);
	$("#dialog:ui-dialog").dialog( "destroy" );
	
	var postData = {
			id : id,
	};

		$.ajax({
			type : "GET",
			url : "/funbackend/controller/Member/MemberDataQuery/Read/Id",
			data : postData,
			success : function(data) {

				// Form Control Value Setting
				$('#userName').val(data.userName);
				$('#displayName').val(data.displayName);
				
				//alert(data.fake.toString());
				//alert($("#fake option:selected").text());
				$("#fake").val(data.fake.toString());
				//alert($("#fake option:selected").text());
				//$("#fake")
				
				
				
				
				$('#deleted').val(data.deleted);
				
				$("#dialog-form").dialog("open");
			},
			dataType : "json",
			traditional : true
		});
}
	
function dataEachRowAdd(data)
{    	 
	$('#jtable').dataTable().fnClearTable(true);


    $.each(data, function (k, v) {
    
        $('#jtable').dataTable().fnAddData([
			"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>",                                
            v.userName,
            v.displayName,
            v.gender,
            v.countryCode,
            v.online,
            v.fake,
            v.numOfLikes,
            v.monthScore,            
            v.totalScore,
            v.ranking,
            "<input name='showDetail' type='button' value='詳細資料' class='gcms-ui-corner-all ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' onclick='showDetailEvent(\"" + v.id + "\")'/>"
        ]);             	   
    });	         
}

</script>
</head>
<body>

	<fieldset>
		<legend>會員基本資料查詢</legend>
		<p />
			<label>帳號名稱:</label><input id="userNameQ" name="userNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 
			<label>顯示名稱:</label><input id="displayNameQ" name="displayNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
			<label>性別 :</label>
				<select id="genderQ">
					<option value="">全部</option>
					<option value="M">男</option>
					<option value="F">女</option>					
				</select><br /><br /><br />	
	</fieldset>

	<br/>
	<div id="toolBar">
		<button id="createBtn" name="createBtn" disabled="disabled">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="updateBtn" name="updateBtn">修改</button>
	    <button id="deleteBtn" name="deleteBtn" disabled="disabled">刪除</button>
	</div>
    <br/>
    
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll" /></th>
                <th>帳號名稱</th>
                <th>顯示名稱</th>
                <th>性別</th>
                <th>國碼</th>
                <th>線上</th>      
                <th>假帳號</th>
                <th>得贊數</th>
                <th>月得分</th>
                <th>總得分</th>
                <th>名次</th>
                <th></th>                
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
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>

    <div id="dialog-form" title="詳細資料">
    
    	<form action="UpdateUser" method="post">
        <fieldset>
            <div class="editor-label">帳號名稱</div>
            <div class="editor-field">
            	<input id="userName" name="userName" readonly="readonly" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>
            <div class="editor-label">顯示名稱</div>
            <div class="editor-field">
            	<input id="displayName" name="displayName" readonly="readonly" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>
            <div class="editor-label">假帳號</div>
            <div class="editor-field">
            	<select id="fake">
					<option value="false">否</option>
					<option value="true">是</option>
				</select>
			</div>
            <div class="editor-label">封存</div>
            <div class="editor-field">
                <select id="deleted">
					<option value="false">否</option>
					<option value="true">是</option>
				</select>
            </div><br />
            <input id="updateSubmitBtn" type="submit" value="存檔" />
        </fieldset>
        </form>
    </div>
</body>
</html>