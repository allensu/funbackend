<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號功能權限設定</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">

var disTable; //保留被DataTables enhanced 過的變數
$.fx.speeds._default = 1000;
$(function() {

	$("#toolBar").buttonset();
	
	$("#categoryQ").combobox();
	
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
       
    });
	
	$("#readSendBtn").button({
        icons: {
            primary: "ui-icon-search"
        }
    }).click(function () {
        //$.blockUI({ message: '<div>載入資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
        readData();
    });
	
	disTable = $('#jtable').dataTable({
        "bJQueryUI": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sServerMethod": "POST",
        "sAjaxSource": '/funbackend/controller/Account/ManageMenuAuth/ReadPages',
        "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#queryform").serializeArray());
        	
        	//alert($("#queryform").serializeArray());
        	//alert(aoData);
        }, 
        "aoColumns": [{ "mDataProp": "id", "bSortable": false, "bSearchable": false, "bVisible": false},
				      { "mDataProp": "userInfo", "bSortable": false,
			            	  "fnRender": function(oObj)
			            	  {
			      				return oObj.aData.userInfo.accountId;
			            	  }},
			          { "mDataProp": "menuItem", "bSortable": false,
					          "fnRender": function(oObj)
					          {					      				
					      		return oObj.aData.menuItem.title;
					         }},
					  { "mDataProp": "enabled", "bSortable": false,
							  "fnRender": function(oObj)
							  {
							  	var checked = "";
							    if(oObj.aData.enabled)					        	
							    	checked = "checked";					        	
							    else 
							    	checked = "";
							        	
							    return "<input id='enabled' name='enabled' type='checkbox' " + checked + " />";	
							  }},	       
					  { "mDataProp": "newAuth", "bSortable": false,
					        "fnRender": function(oObj)
					        {
					        	var checked = "";
					        	if(oObj.aData.newAuth)					        	
					        		checked = "checked";					        	
					        	else 
					        		checked = "";
					        	
					        	return "<input id='newAuth' name='newAuth' type='checkbox' " + checked + " />";	
					        }},	
		              { "mDataProp": "updateAuth", "bSortable": false,
			            	"fnRender": function(oObj)
			            	{
			            		var checked = "";
					        	if(oObj.aData.updateAuth)					        	
					        		checked = "checked";					        	
					        	else 
					        		checked = "";
					        	
			            		return "<input id='updateAuth' name='updateAuth' type='checkbox' " + checked + " />";	
			           		}},
			          { "mDataProp": "deleteAuth", "bSortable": false,
						    "fnRender": function(oObj)
					        {
						    	var checked = "";
					        	if(oObj.aData.deleteAuth)					        	
					        		checked = "checked";					        	
					        	else 
					        		checked = "";
					        	
						    	return "<input id='deleteAuth' name='deleteAuth' type='checkbox' " + checked + " />";	
					        }},	
			          { "mDataProp": "queryAuth", "bSortable": false,
				          "fnRender": function(oObj)
			              {
				        	  var checked = "";
					        	if(oObj.aData.queryAuth)					        	
					        		checked = "checked";					        	
					        	else 
					        		checked = "";
					        	
				        	  return "<input id='queryAuth' name='queryAuth' type='checkbox' " + checked + " />";	
			              }},
			          { "mDataProp": "saveBtnCol", "bSortable": false,
				          "fnRender": function(oObj)
				          { 
					          //\"" + oObj.aData.id + "\"
					          //alert(oObj.aData.id);
					          var obj = "<input menuAuthId='" + oObj.aData.id + "' name='saveBtn' type='button' value='儲存' class='gcms-ui-corner-all ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' onclick='saveEvent(\"" + oObj.aData.id + "\")'/>";
					          //alert(obj);
					          return obj;
				          }}
		              ],
		"fnDrawCallback" : function() {
			
// 			$('#selectAll').attr('checked', false);
// 			var checkBoxes = $("input[name=dataId]");
// 			$.each(checkBoxes, function() {
// 				if ($(this).attr('checked') == true) {
// 					$(this).attr('checked', false);
// 				}
// 			});
			
			//showMap();
		}
	});
});

function ExecuteFun(authType)
{
	var postData = {
			authType : authType,
			menuItemId : "ManageMenuAuth"
    	};
    	  
        $.ajax({
      		type : "GET",
      		url : "/funbackend/controller/Account/ManageMenuAuth/HasFunAuth",
      		data : postData,
      		success : function(data) {

				if(data.resultCode == -1)
				{
					alert("使用者無此權限");
				}
				else if(authType == "UpdateAuth")
				{
					updateData();
				}		
      		}
        });	
}

function readData() {

	disTable.fnDraw();
}

function updateData()
{

	var menuAuthId = "";
	var enabled = false;
	var newAuth = false;
	var updateAuth = false;
	var deleteAuth = false;
	var queryAuth = false;
	
	$('#jtable tr').each(function() {    
        var rowData = this.cells;
   		//alert($(rowData[6].firstChild).attr("menuAuthId"));
   		
   		//alert(rowData["newAuth"].firstChild.checked);
   		
        if($(rowData[7].firstChild).attr("menuAuthId") == id)
        {
        	menuAuthId = id;
        	enabled = rowData[2].firstChild.checked;
        	newAuth = rowData[3].firstChild.checked;
        	updateAuth = rowData[4].firstChild.checked;
        	deleteAuth = rowData[5].firstChild.checked;
        	queryAuth = rowData[6].firstChild.checked;
        }
	});
	
	
	var postData = {
			menuAuthId : menuAuthId,
			enabled : enabled,
			newAuth : newAuth,
			updateAuth : updateAuth,
			deleteAuth : deleteAuth,
			queryAuth : queryAuth,			
	};
	
	$.blockUI({ message: '<div>儲存資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Account/ManageMenuAuth/Update",
		data : postData,
		success : function(data) {

			$.unblockUI();
		},
		dataType : "json",
		traditional : true
	});
}

function saveEvent(id)
{
	ExecuteFun("UpdateAuth");
}


</script>
</head>
<body>

	<div id="toolBar">
		<button id="createBtn" name="createBtn" disabled="disabled">新增</button>	
	    <button id="readBtn" name="readBtn">查詢</button>
	    <button id="updateBtn" name="updateBtn" disabled="disabled">修改</button>
	    <button id="deleteBtn" name="deleteBtn" disabled="disabled">刪除</button>
	</div>
	
	<fieldset id="queryFormField">
		<legend>帳號查詢</legend>
		<p />
		<form id="queryform">
			<label>帳號名稱:</label><input id="accountIdQ" name="accountIdQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />						
			<label>功能項目名稱:</label><input id="menuTitleQ" name="menuTitleQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
		</form>
		<button id="readSendBtn" name="readSendBtn">送出</button> 
	</fieldset>	
	<br/>
    <br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
    	<thead>
    	<tr>
    		<th>MenuAuthId</th>
           	<th>帳號</th>
          	<th>功能項目名稱</th>
          	<th>有效</th>
           	<th>新增</th>
           	<th>修改</th>
          	<th>刪除</th>
           	<th>查詢</th>
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
       	</tr>
       	</tbody>
   	</table>
    			
    			
</body>
</html>