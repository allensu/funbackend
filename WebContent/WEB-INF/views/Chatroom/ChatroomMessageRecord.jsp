<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>Insert title here</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0px; padding: 0px }
  #map_canvas { height: 100% }
</style>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=true"></script>

<script type="text/javascript">

var disTable; //保留被DataTables enhanced 過的變數
var chatroomMessageTable; 

$.fx.speeds._default = 1000;
$(function() {
	
	$("#toolBar").buttonset();
	
	$("#chatRoomStyleQ").combobox();
	
	$("#typeQ").combobox();
	
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
        readData();
    });
	
	$("#readMessageSendBtn").button({
        icons: {
            primary: "ui-icon-search"
        }
    }).click(function () {
        //$.blockUI({ message: '<div>載入資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
        readMessageData();
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
		            	}},
		              { "mDataProp": "chatRoomStyle",
			            	"fnRender": function(oObj)
			            	{
			            		if(oObj.aData.chatRoomStyle == "single")
			            			return "私人";
			            		else if(oObj.aData.chatRoomStyle == "group")
			            			return "群組";
			            		else if(oObj.aData.chatRoomStyle == "geo")
			            			return "地理範圍";
			            		else if(oObj.aData.chatRoomStyle == "broadcast")
			            			return "廣播";
			            		else
			            			return "";
			           		}},
		              { "mDataProp": "numOfUser" },		
		              { "mDataProp": "users",
		            	  "fnRender": function(oObj)
		            	  {
		            		// 在線名單
		      				var usersStr = "";
		      				$.each(oObj.aData.users, function (k, v) {
		      					
		      					if(usersStr == "")
		      						usersStr = v;
		      					else 
		      						usersStr = usersStr + ", " + v;
		      				});
		      				
		      				return usersStr;
		            	  }},
		           	  { "mDataProp": "leaveUsers",
			              "fnRender": function(oObj)
			              {
			            	// 離線名單
			      			var usersStr = "";
			      			$.each(oObj.aData.leaveUsers, function (k, v) {
			      					
			  					if(usersStr == "")
		    						usersStr = v;
		      					else 
		      						usersStr = usersStr + ", " + v;
			      			});
			      				
			      			return usersStr;
			              }},
			          { "mDataProp": "location",
				          "fnRender": function(oObj)
			              {
		   	            	// 位置
		   	            	var obj = "";
		   	            	
		   	            	if(oObj.aData.location != null)
		   	            		obj = "<div name='colMap' lat='"+oObj.aData.location["lat"]+"' lon='"+oObj.aData.location["lon"]+"' style='width: 300px; height:200px; border: 1px solid #000;'></div>";
		   	            		 
							return obj;
			              }},
		              { "mDataProp": "detailBtnCol", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{ 
		            		var obj = "<input name='showDetail' type='button' value='內容' class='gcms-ui-corner-all ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' onclick='showDetailEvent(\"" + oObj.aData.id + "\")'/>";
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
			
			showMap();
		}
    });
	
	chatroomMessageTable = $('#jtable-detail').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        //"bPaginate": true,
        //"bDeferRender": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/Chatroom/ChatroomMessageRecord/ReadPages/Message',
        "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#messageQueryform").serializeArray());
        },
        "aoColumns": [{ "mDataProp": "itemCheckCol", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{ 
		            		return "<input id='messageDataId' name='messageDataId' type='checkbox' value='" + oObj.aData.id + "'/>";		            		
		            	}},
		              { "mDataProp": "userName", "bSortable": false},
		              { "mDataProp": "senderName", "bSortable": false },		
		              { "mDataProp": "type", "bSortable": false },
		              { "mDataProp": "message", "bSortable": false },
		              { "mDataProp": "fileName", "bSortable": false ,
		            	  "fnRender": function(oObj)
		            	  {
		            		  if(oObj.aData.type == "photo")
		            		  {
		            			  var imgObj = oObj.aData.fileName + "<br/>";
		            			  imgObj = imgObj + "<img src='/funbackend/controller/Files/get/" + oObj.aData.fileName + "' style='width:100px; height: 100px' />";
		            			  return imgObj;
		            		  } else {
		            			  return oObj.aData.fileName;
		            		  }
		            	  }},
		              { "mDataProp": "fileSize", "bSortable": false },
		              { "mDataProp": "createDateTime", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{
		            		return jsonDate2Format(oObj.aData.createDateTime,"yyyy/m/d TT hh:MM");
		            	}
		              }			            	
		              ],
		"fnDrawCallback" : function() {
			
			$('#detailSelectAll').attr('checked', false);
			var checkBoxes = $("input[name=messageDataId]");
			$.each(checkBoxes, function() {
				if ($(this).attr('checked') == true) {
					$(this).attr('checked', false);
				}
			});
		}
    });
	
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
	
	// 全選
	$('#detailSelectAll').click(function() {

		var checkBoxes = $("input[name=messageDataId]");

		if ($('#detailSelectAll').attr('checked')) {
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
	
	// Detail Form View	
 	$("#detail-dialog-form").dialog({
 		autoOpen : false,
 		//modal : true,
 		width : 800,
 		height : 500
 	});
	
	changeField("queryFormField");
});

function showMap()
{
	$('div[name=colMap]').each(function(i) {
		loadMap(this, $(this).attr("lat"), $(this).attr("lon"));
	});	
}

function loadMap(thisObj, lat, lon) {
	
	  var latlng = new google.maps.LatLng(lat, lon);
	    var myOptions = {
	      zoom: 12,
	      center: latlng,
	      mapTypeId: google.maps.MapTypeId.ROADMAP
	    };
	    var currentMap = new google.maps.Map(thisObj, myOptions);
	    
	    var optionOfMarker = {
		    	position: latlng,
		    	map: currentMap,
		    	title: "Chatroom here!"
		   	};
		    
		var mapMarker = new google.maps.Marker(optionOfMarker);
		mapMarker.setAnimation(google.maps.Animation.DROP);
}

function readData() {

	disTable.fnDraw();
}

function readMessageData() {
	
	chatroomMessageTable.fnDraw();
}

function changeField(fieldName)
{
	$("#queryFormField").hide();
	//$("#updateFormField").hide();
	
	$("#" + fieldName).show();
}

function showDetailEvent(id)
{
	$("#dialog:ui-dialog").dialog( "destroy" );
	
	$('#chatroomIdQ').val(id);
	chatroomMessageTable.fnDraw();
	
	$("#detail-dialog-form").dialog("open");
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
			<label>帳號名稱:</label><input id="userNameQ" name="userNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 
			<!-- <label>顯示名稱:</label><input id="displayNameQ" name="displayNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> -->
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
                <th>名單</th>
                <th>離開名單</th>
                <th>位置</th>
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
            </tr>
        </tbody>
    </table>


    <div id="detail-dialog-form" title="聊天內容">
    	<form id="messageQueryform">
			<input id="chatroomIdQ" name="chatroomIdQ" type="hidden"/>
			<label>收信者:</label><input id="receiverNameQ" name="receiverNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
			<label>發信者:</label><input id="senderNameQ" name="senderNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
			<label>訊息類別 :</label>
			<select id="typeQ" name="typeQ" >
				<option value="All">全部</option>
				<option value="text">文字</option>
				<option value="audio">語音</option>
				<option value="video">影片</option>
				<option value="photo">圖片</option>		
				<option value="location">地圖</option>					
			</select><br /><br />
		</form>
		<button id="readMessageSendBtn" name="readMessageSendBtn">查詢</button><br />		
    	<table id="jtable-detail"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="detailSelectAll" /></th>
                <th>收信者</th>
                <th>發信者</th>
                <th>訊息類別</th>
                <th>訊息文字</th>
                <th>檔案名稱</th>
                <th>檔案大小</th>   
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
                <td></td>
                <td></td>
            </tr>
        </tbody>
   		</table>
    </div>
</body>
</html>