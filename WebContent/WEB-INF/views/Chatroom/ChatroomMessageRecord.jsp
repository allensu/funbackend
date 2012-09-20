<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<script type="text/javascript">

var disTable; //保留被DataTables enhanced 過的變數
$.fx.speeds._default = 1000;
$(function() {
	
	$("#toolBar").buttonset();
	
	$("#chatRoomStyleQ").combobox();
	
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
    	changeField("queryFormField");
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
        //$.blockUI({ message: $('#question'), css: { width: '275px'} });
                
    });
	
	$("#readSendBtn").button({
        icons: {
            primary: "ui-icon-search"
        }
    }).click(function () {
        //$.blockUI({ message: '<div>載入資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
        //readData();
    });
	
	disTable = $('#jtable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        //"bPaginate": true,
        //"bDeferRender": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/Chatroom/ChatroomMessageRecord/ReadPages',
        "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#queryform").serializeArray());
        },
        "aoColumns": [{ "mDataProp": "itemCheckCol", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{ 
		            		return "<input id='dataId' name='dataId' type='checkbox' value='" + oObj.aData.id + "'/>";
		            		//return "<input type='checkbox' name='id' value='"+oObj.aData.id+"'>"
		            	}},
		              { "mDataProp": "userName" },
		              { "mDataProp": "displayName" },
		              { "mDataProp": "gender",
		            	  "fnRender": function(oObj)
		            	  {
		            		  return oObj.aData.gender == "M" ? "男" : oObj.aData.gender == "F" ? "女" : "";
		            	  }},
		              { "mDataProp": "countryCode" },
		              { "mDataProp": "online",
		            	  "fnRender": function(oObj)
		            	  {
		            		  return oObj.aData.online == true ? "是" : "否";
		            	  }},
		              { "mDataProp": "fake",
		            	  "fnRender": function(oObj)
		            	  {
		            		  return oObj.aData.fake == true ? "是" : "否";
		            	  }},
		              { "mDataProp": "numOfLikes" },
		              { "mDataProp": "monthScore" },
		              { "mDataProp": "totalScore" },
		              { "mDataProp": "ranking" },
		              { "mDataProp": "detailBtnCol", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{ 
		            		//\"" + oObj.aData.id + "\"
		            		//alert(oObj.aData.id);
		            		var obj = "<input name='showDetail' type='button' value='修改' class='gcms-ui-corner-all ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' onclick='showDetailEvent(\"" + oObj.aData.id + "\")'/>";
		            		//alert(obj);
		            		return obj;
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

	<fieldset id="queryFormField" style="display: none">
		<legend>聊天室查詢</legend>
		<p />
		<form id="queryform">

			<label>類別 :</label>
				<select id="chatRoomStyleQ" name="chatRoomStyleQ" >
					<option value="All">全部</option>
					<option value="single">私人</option>
					<option value="group">群組</option>
					<option value="geo">地理範圍</option>
					<option value="broadcast">廣播</option>					
				</select><br /><br />
			<!-- <label>帳號名稱:</label><input id="userNameQ" name="userNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 
			<label>顯示名稱:</label><input id="displayNameQ" name="displayNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> -->
		</form>			
		<button id="readSendBtn" name="readSendBtn">送出</button>
	</fieldset>
	
	<br/>
    <br/>

	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll" /></th>
                <th>類別</th>
                <th>人數</th>
                <th></th>                
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







</body>
</html>