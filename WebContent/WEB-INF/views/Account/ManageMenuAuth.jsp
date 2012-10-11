<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號功能權限設定</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<script type="text/javascript">

$.fx.speeds._default = 1000;
$(function() {

	$("#toolBar").buttonset();
	
	$("#categoryQ").combobox();
	
	disTable = $('#jtable').dataTable({
        "bJQueryUI": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/Account/ManageMenuAuth/ReadPages',
        "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#queryform").serializeArray());
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

function saveEvent(id)
{
	var menuAuthId = "";
	var newAuth = false;
	var updateAuth = false;
	var deleteAuth = false;
	var queryAuth = false;
	
	$('#jtable tr').each(function() {    
        var rowData = this.cells;
   		//alert($(rowData[6].firstChild).attr("menuAuthId"));
   		
   		//alert(rowData["newAuth"].firstChild.checked);
   		
        if($(rowData[6].firstChild).attr("menuAuthId") == id)
        {
        	menuAuthId = id;
        	newAuth = rowData[2].firstChild.checked;
        	updateAuth = rowData[3].firstChild.checked;
        	deleteAuth = rowData[4].firstChild.checked;
        	queryAuth = rowData[5].firstChild.checked;
        }
	});
	
	
	var postData = {
			menuAuthId : menuAuthId,
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
			<label>帳號名稱:</label><input id="accountNameQ" name="accountNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 			
		</form>
	</fieldset>	
	<br/>
    <br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
    	<thead>
    	<tr>
    		<th>MenuAuthId</th>
           	<th>帳號名稱</th>
          	<th>功能項目名稱</th>
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
       	</tr>
       	</tbody>
   	</table>
    			
    			
</body>
</html>