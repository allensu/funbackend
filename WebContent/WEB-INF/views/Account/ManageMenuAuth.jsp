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

	disTable = $('#jtable').dataTable({
        "bJQueryUI": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/GraffitiWall/GraffitiWallQuery/ReadPages',
        /* "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#queryform").serializeArray());
        }, */
        "aoColumns": [{ "mDataProp": "userInfo",
			            	  "fnRender": function(oObj)
			            	  {
			      				return oObj.aData.userInfo.accountId;
			            	  }},
			          { "mDataProp": "menuItem",
					          "fnRender": function(oObj)
					          {					      				
					      		return oObj.aData.menuItem.title;
					         }},
					  { "mDataProp": "newAuth",
					        "fnRender": function(oObj)
					        {
					        	return "<input id='newAuth' name='newAuth' type='checkbox' value='" + oObj.aData.newAuth + "'/>";	
					        }},	
		              { "mDataProp": "updateAuth",
			            	"fnRender": function(oObj)
			            	{
			            		return "<input id='updateAuth' name='updateAuth' type='checkbox' value='" + oObj.aData.updateAuth + "'/>";	
			           		}},
			          { "mDataProp": "deleteAuth",
						    "fnRender": function(oObj)
					        {
						    	return "<input id='deleteAuth' name='deleteAuth' type='checkbox' value='" + oObj.aData.deleteAuth + "'/>";	
					        }},	
			          { "mDataProp": "queryAuth",
				          "fnRender": function(oObj)
			              {
				        	  return "<input id='queryAuth' name='queryAuth' type='checkbox' value='" + oObj.aData.queryAuth + "'/>";	
			              }}
		              ],
		"fnDrawCallback" : function() {
			
			$('#selectAll').attr('checked', false);
			var checkBoxes = $("input[name=dataId]");
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
			
			showMap();
		}
	});
});

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
			<label>類別 :</label>
				<select id="categoryQ" name="categoryQ">
					<option value="All">全部</option>
					<option value="Normal">一般</option>
					<option value="Guest">訪客</option>
					<option value="Admin">管理者</option>
				</select><br /><br />
			<label>帳號名稱:</label><input id="accountNameQ" name="accountNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 			
		</form>
	</fieldset>	
	<br/>
    <br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
    	<thead>
    	<tr>
           	<th>帳號名稱</th>
          	<th>功能項目名稱</th>
           	<th>新增</th>
           	<th>修改</th>
          	<th>刪除</th>
           	<th>查詢</th>
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
    			
    			
</body>
</html>