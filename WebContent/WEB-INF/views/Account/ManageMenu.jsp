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
	
	$('#selectAll-group').click(function() {
		
		var checkBoxes = $("input[name=groupDataId]");
		
		if ($('#selectAll-group').attr('checked')) {
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
	
	$('#selectAll-item').click(function() {
		
		var checkBoxes = $("input[name=itemDataId]");
		
		if ($('#selectAll-item').attr('checked')) {
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
	
	var oTable = $('#jtable-group').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null
		              ],
		"fnDrawCallback" : function() {
			
			$('#selectAll-group').attr('checked', false);
			var checkBoxes = $("input[name=groupDataId]");
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
		}
	});
	
	var oTable = $('#jtable-item').dataTable({
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null,
		              null
		              ],
		"fnDrawCallback" : function() {
			
			$('#selectAll-item').attr('checked', false);
			var checkBoxes = $("input[name=groupDataId]");
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
		}
	});
	
	
	
	
	
	
	
});

</script>
</head>
<body>
<br/>
	<div id="toolBar-group">
		<button id="createBtn-group" name="createBtn-group">新增</button>	
	    <button id="readBtn-group" name="readBtn-group">查詢</button>
	    <button id="updateBtn-group" name="updateBtn-group">修改</button>
	    <button id="deleteBtn-group" name="deleteBtn-group" disabled="disabled">刪除</button>
	</div>	
    <br/>
    <fieldset>
		<legend>群組管理</legend>
		<p />
		<table id="jtable-group"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll-group" /></th>
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
    	<div id="toolBar-item">
		<button id="createBtn-item" name="createBtn-item">新增</button>	
	    <button id="readBtn-item" name="readBtn-item">查詢</button>
	    <button id="updateBtn-item" name="updateBtn-item">修改</button>
	    <button id="deleteBtn-item" name="deleteBtn-item" disabled="disabled">刪除</button>
	</div>	
    <br/>
	<fieldset>
		<legend>功能項目管理</legend>
		<p />
		<table id="jtable-item"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll-item" /></th>
                <th>功能名稱 </th>
                <th>頁面路徑 </th>
            </tr>
        </thead>
        <tbody>
            <tr class="row">
            	<td></td>   
            	<td></td>  
            	<td></td>          
            </tr>
        </tbody>
    	</table>	
	</fieldset>
</body>
</html>