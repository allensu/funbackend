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
    	//changeField("queryFormField");
    	
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
        
    });
	
	
	disTable = $('#jtable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        //"bPaginate": true,
        //"bDeferRender": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/GraffitiWall/GraffitiWallQuery/ReadPages',
        "fnServerParams": function ( aoData ) {
        	$.merge(aoData, $("#queryform").serializeArray());
        },
        "aoColumns": [{ "mDataProp": "itemCheckCol", "bSortable": false,
		            	"fnRender": function(oObj)
		            	{ 
		            		return "<input id='dataId' name='dataId' type='checkbox' value='" + oObj.aData.id + "'/>";		            		
		            	}},
		              { "mDataProp": "wallOwner",
			            	  "fnRender": function(oObj)
			            	  {
			            		//alert(oObj.aData.wallOwner.userName);
			      				
			      				return oObj.aData.wallOwner.userName;
			            	  }},
			          { "mDataProp": "poster",
					          "fnRender": function(oObj)
					          {
					        	//alert(oObj.aData.poster.userName);
					      				
					      		return oObj.aData.poster.userName;
					         }},
					  { "mDataProp": "timeStamp",
					        "fnRender": function(oObj)
					        {
					        	return jsonDate2Format(oObj.aData.timeStamp,"yyyy/m/d TT hh:MM");
					        }},	
		              { "mDataProp": "category",
			            	"fnRender": function(oObj)
			            	{
			            		if(oObj.aData.category == "post-message")
			            			return "文字訊息";
			            		else if(oObj.aData.category == "want-to")
			            			return "我想...";
			            		else if(oObj.aData.category == "check-in")
			            			return "打卡";
			            		else
			            			return "";
			           		}},
			          { "mDataProp": "message" },	
			          { "mDataProp": "location",
				          "fnRender": function(oObj)
			              {
		   	            	// 位置
		   	            	var obj = "";
		   	            	
		   	            	if(oObj.aData.location != null && 
		   	            			oObj.aData.location["lat"] != null &&
		   	            			oObj.aData.location["lon"] != null
		   	            			)
		   	            		obj = "<div name='colMap' lat='"+oObj.aData.location["lat"]+"' lon='"+oObj.aData.location["lon"]+"' style='width: 300px; height:200px; border: 1px solid #000;'></div>";
		   	            		 
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
		    	title: "CheckIn here!"
		   	};
		    
		var mapMarker = new google.maps.Marker(optionOfMarker);
		mapMarker.setAnimation(google.maps.Animation.DROP);
}

function readData() {

	disTable.fnDraw();
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
		<legend>塗鴉牆查詢</legend>
		<p />
		<form id="queryform">
			<input id="wallOwnerId" name="wallOwnerId" type="hidden"/>
			<input id="posterId" name="posterId" type="hidden"/>
			<label>類別 :</label>
				<select id="categoryQ" name="categoryQ" >
					<option value="All">全部</option>
					<option value="post-message">文字訊息</option>
					<option value="want-to">我想...</option>
					<option value="check-in">打卡</option>			
				</select><br /><br />
			<label>塗鴉牆所屬:</label><input id="wallOwnerQ" name="wallOwnerQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
			<label>PO文者:</label><input id="posterQ" name="posterQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 			
		</form>
	</fieldset>	
	<br/>
    <br/>
	
	<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
            	<th align="left"><input type="checkbox" id="selectAll" /></th>
                <th>塗鴉牆所屬</th>
                <th>PO文者</th>
                <th>時間</th>
                <th>類別</th>
                <th>訊息</th>
                <th>位置</th>            
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
    
    
    	
</body>
</html>