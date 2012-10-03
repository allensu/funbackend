<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能項目維護</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<script type="text/javascript">

$.fx.speeds._default = 1000;
$(function() {
	$("#toolBar-group").buttonset();
	$("#toolBar-item").buttonset();

	var oTableGroup = $('#jtable-group').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null
		              ],
		"fnDrawCallback" : function() {
			
		}
	});
	
	var oTableItem = $('#jtable-item').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null,
		              null,
		              null
		              ],
		"fnDrawCallback" : function() {
			
		}
	});
	
	// Create Btn
	$("#createBtn-group").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {		
		$("#dialog:ui-dialog").dialog( "destroy" );
		$("#dialog-form-Group").dialog("open");
		return false;
	});
	// Create Btn
	$("#createBtn-item").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {
		$("#dialog:ui-dialog").dialog( "destroy" );
		$("#dialog-form-Item").dialog("open");
		return false;
	});
	
	// Read Btn
	$("#readBtn-group").button({
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
		readDataGroup();
	});
	// Read Btn
 	$("#readBtn-item").button({
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
		readDataMenu();
	});
	
	// Update Btn
	$("#updateBtn-group").button({
		icons : {
			primary : "ui-icon-wrench"
		}
	}).click(function() {

	});
	// Update Btn
	$("#updateBtn-item").button({
		icons : {
			primary : "ui-icon-wrench"
		}
	}).click(function() {

	});
	
	// Delete Btn
	$("#deleteBtn-group").button({
		icons : {
			primary : "ui-icon-circle-close"
		}
	}).click(function() {
		var checkedCount = $('#jtable-group input:checked').length;

		if (checkedCount > 0)
			$.blockUI({
				message : $('#question'),
				css : {
					width : '275px'
				}
			});
	});
	// Delete Btn
	$("#deleteBtn-item").button({
		icons : {
			primary : "ui-icon-circle-close"
		}
	}).click(function() {
		var checkedCount = $('#jtable-item input:checked').length;

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
	
	// Form View
	$("#dialog-form-Group").dialog({
		autoOpen : false,
		modal : true
	});
	$("#dialog-form-Item").dialog({
		autoOpen : false,
		modal : true
	});
	
	$("#createSubmitBtn-Group").button().click(function() {
		createDataGroup();
	});
	$("#createSubmitBtn-Item").button().click(function() {
				
		$('#groupId').val(defaultGroupId);		
		createDataMenuItem();
	});
});

var menuGroupKV = [];
var menuItemKV = [];
var defaultGroupId = 0;
var defaultGroupTitle = "";
var defaultGroupChecked = "";
function readDataGroup()
{
	//menuGroupKV = [];
	menuItemKV = [];
	
	// 資料
    $.getJSON('/funbackend/controller/Account/MenuList', function (data) {

    	showData(data);
    	/*
    	var defaultGroupId = 0;
    	var defaultGroupTitle = "";
    	var defaultGroupChecked = "";
        $.each(data, function (k, v) {
        	
        	if(defaultGroupId == 0)
        	{	
        		defaultGroupId = v.id;
        		defaultGroupTitle = v.title;
        		defaultGroupChecked = "checked";
        	} else {
        		defaultGroupChecked = "";
        	}
        	
        	$('#jtable-group').dataTable().fnAddData([
        	            						"<input id='groupDataId' name='groupDataId' type='radio' value='" + v.id + "' onclick='selectGroup(this)' " + defaultGroupChecked + "/>", 
        	            						v.title]);
        	
        	menuGroupKV[v.id] = v.title;
        	menuItemKV[v.id] = v.content;        	        
        });
        
        $.each(menuItemKV[defaultGroupId], function (k, v) {
    		
    		$('#jtable-item').dataTable().fnAddData([
    	        	            						"<input id='itemDataId' name='itemDataId' type='radio' value='" + v.id + "'/>", 
    	        	            						defaultGroupTitle,
    	        	            						v.title,
    	        	            						v.url]);        		
    	});
        */
        
        $.unblockUI();
    });
}

function showData(data)
{
	$('#jtable-group').dataTable().fnClearTable(true);
	$('#jtable-item').dataTable().fnClearTable(true);
	
    $.each(data, function (k, v) {
    	
    	if(defaultGroupId == 0)
    	{	
    		defaultGroupId = v.id;
    		defaultGroupTitle = v.title;
    		defaultGroupChecked = "checked";
    	} else {
    		defaultGroupChecked = "";
    	}
    	
    	$('#jtable-group').dataTable().fnAddData([
    	            						"<input id='groupDataId' name='groupDataId' type='radio' value='" + v.id + "' onclick='selectGroup(this)' " + defaultGroupChecked + "/>", 
    	            						v.title]);
    	
    	menuGroupKV[v.id] = v.title;
    	menuItemKV[v.id] = v.content;        	        
    });
    
    $.each(menuItemKV[defaultGroupId], function (k, v) {
		
		$('#jtable-item').dataTable().fnAddData([
	        	            						"<input id='itemDataId' name='itemDataId' type='radio' value='" + v.id + "'/>", 
	        	            						defaultGroupTitle,
	        	            						v.title,
	        	            						v.url]);        		
	});
}

function selectGroup(thisObj) 
{
	defaultGroupId = $(thisObj).val();
	defaultGroupTitle = menuGroupKV[$(thisObj).val()];
	
	$('#jtable-item').dataTable().fnClearTable(true);
	$.each(menuItemKV[$(thisObj).val()], function (k, v) {
		
		$('#jtable-item').dataTable().fnAddData([
	        	            						"<input id='itemDataId' name='itemDataId' type='radio' value='" + v.id + "'/>", 
	        	            						defaultGroupTitle,
	        	            						v.title,
	        	            						v.url]);        		
	});
}
 
function createDataMenuItem()
{
	if($('#groupId').val() == "" ||
		$('#itemTitle').val() == "" ||
		$('#itemUrl').val() == "")
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
			groupId : $('#groupId').val(),
			title : $('#itemTitle').val(),
			url : $('#itemUrl').val()
	};
	
	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Account/ManageMenu/MenuItem/Create",
		data : postData,
		success : function(data) {

			showData(data);
		
			//$.unblockUI();

			$('#title').val("");
			
			$("#dialog-form-Item").dialog("close");
		},
		dataType : "json",
		traditional : true
	});
}

function createDataGroup()
{
	if($('#title').val() == "")
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
			title : $('#title').val()
	};
	
	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Account/ManageMenu/GroupItem/Create",
		data : postData,
		success : function(data) {

			showData(data);
		
			//$.unblockUI();

			$('#title').val("");
			
			$("#dialog-form-Group").dialog("close");
		},
		dataType : "json",
		traditional : true
	});
}

function readDataMenu()
{
	$.unblockUI();
}

function deleteData()
{
	$.unblockUI();
}
</script>
</head>
<body>
<br/>
<table style="width: 100%;">
	<tr>
		<td style="width: 50%" valign="top">
    	<fieldset>
    	<div id="toolBar-group">
		<button id="createBtn-group" name="createBtn-group">新增</button>
	    <button id="readBtn-group" name="readBtn-group">查詢</button>
	    <button id="updateBtn-group" name="updateBtn-group">修改</button>
	    <button id="deleteBtn-group" name="deleteBtn-group">刪除</button>
		</div>
		<legend>群組管理</legend>
		<p />
		<table id="jtable-group"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><!-- <input type="checkbox" id="selectAll-group" /> --></th>
                <th>群組名稱 </th>
            </tr>
        </thead>
        <tbody>
            <tr class="row">
            	<td></td>   
            	<td></td>           
            </tr>
        </tbody>
    	</table>	
		</fieldset>
		</td>
		<td style="width: 50%" valign="top">
			<fieldset>
				<div id="toolBar-item">
				<button id="createBtn-item" name="createBtn-item">新增</button>	
	    		<button id="readBtn-item" name="readBtn-item">查詢</button>
	    		<button id="updateBtn-item" name="updateBtn-item">修改</button>
	    		<button id="deleteBtn-item" name="deleteBtn-item">刪除</button>
				</div>
				<legend>功能項目管理</legend>
				<p />
				<table id="jtable-item"  cellpadding="0" cellspacing="0" border="0" class="display" >
        		<thead>
            		<tr>
            		<th align="left"><!-- <input type="checkbox" id="selectAll-item" /> --></th>
            		<th>群組名稱 </th>
                	<th>功能名稱 </th>
                	<th>頁面路徑 </th>
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
			</fieldset>
		</td>
	</tr>
</table>
   <div id="dialog-form-Group" title="新增群組">
    
    	<form action="" method="post">
        <fieldset>
            <div class="editor-label">
                群組名稱
            </div>
            <div class="editor-field">
            	<input id="title" name="title" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>                        
        </fieldset>
        </form>
        <input id="createSubmitBtn-Group" type="submit" value="存檔" />
    </div>    
    <div id="dialog-message" title="訊息" style="display: none;">
		<p>請輸入必要資料</p>
	</div>
	<div id="dialog-form-Item" title="新增功能項目">
    
    	<form action="" method="post">
    	<input id="groupId" name="groupId" type="hidden" value="" />
        <fieldset>
            <div class="editor-label">
                群組名稱
            </div>
            <div class="editor-field">
            	<input id="groupName" name="groupName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"  readonly="readonly" style="border: 0px" />                
            </div>   
            <div class="editor-label">
                功能名稱
            </div>
            <div class="editor-field">
            	<input id="itemTitle" name="itemTitle" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>  
            <div class="editor-label">
                頁面路徑
            </div>
            <div class="editor-field">
            	<input id="itemUrl" name="itemUrl" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" />                
            </div>                      
        </fieldset>
        </form>
        <input id="createSubmitBtn-Item" type="submit" value="存檔" />
    </div>  
    
    <div id="question" style="display: none; cursor: default">
        <h1 id="msgCnt">確定要刪除資料?</h1>
        <input type="button" id="yes" name="yes" value="Yes" />
        <input type="button" id="no" name="no" value="No" />
    </div>
</body>
</html>