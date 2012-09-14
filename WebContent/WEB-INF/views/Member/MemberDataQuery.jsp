<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<title>會員基本資料查詢</title>

<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
 
 
<style type="text/css">
  html { height: 100% }
  body { height: 100%; margin: 0px; padding: 0px }
  #map_canvas { height: 100% }
</style>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=true"></script>

<!-- <script type="text/javascript">
  function initialize() {
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var myOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"),
        myOptions);
  }
</script> -->
<script type="text/javascript">
var disTable; //保留被DataTables enhanced 過的變數
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
        $.blockUI({ message: $('#question'), css: { width: '275px'} });
                
    });
	
	$("#readSendBtn").button({
        icons: {
            primary: "ui-icon-search"
        }
    }).click(function () {
        //$.blockUI({ message: '<div>載入資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
        readData();
    });
	
	// Form Control Define
	$("#updateFrameBtn").button({
        icons: {
            primary: "ui-icon-wrench"
        }
    }).click(function () {
    	$.blockUI({ message: '<div>儲存資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
    	updateData(); 
    });
	
	$("#photosBtn").button().click(function() {
		$("#photos-dialog-form").dialog("open");
	});
	$("#likeUsersBtn").button().click(function() {
		$("#likeUsers-dialog-form").dialog("open");
	});
	$("#visitorsBtn").button().click(function() {
		$("#visitors-dialog-form").dialog("open");
	});
	$("#blockUsersBtn").button().click(function() {
		$("#blockUsers-dialog-form").dialog("open");
	});
	
	
	disTable = $('#jtable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        //"bPaginate": true,
        //"bDeferRender": true,
        "bServerSide":true,
        "sPaginationType":"full_numbers",
        "bProcessing": true, 
        "sAjaxSource": '/funbackend/controller/Member/MemberDataQuery/ReadPages',
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
	
	//photosTable
	/*
	var photosTable = $('#photosTable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        "bPaginate": true,
        "bDeferRender": true,        
        "aoColumns": [{ "bSortable": false},
                      { "bSortable": false},
                      { "bSortable": false},
                      { "bSortable": false},
                      { "bSortable": false}]
	});
	*/
	
	//likeUsersTable
	var likeUsersTable = $('#likeUsersTable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        "bPaginate": true,
        "bDeferRender": true
	});
	
	//visitorsTable
	var visitorsTable = $('#visitorsTable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        "bPaginate": true,
        "bDeferRender": true
	});
	
	//blockUsersTable
	var blockUsersTable = $('#blockUsersTable').dataTable({
        //"sScrollY":  '100%',
        "bJQueryUI": true,
        "bPaginate": true,
        "bDeferRender": true
	});
	
	// Form View	
 	$("#dialog-form").dialog({
 		autoOpen : false,
 		//modal : true,
 		width : 820,
 		height : 650
 	});
	
 	//photos
	$("#photos-dialog-form").dialog({
		resizable: false,
 		autoOpen : false,
 		modal : true,
 		width : 650,
 		height : 600,
 		buttons: {
			"取消": function() {
				
				
				$( this ).dialog( "close" );
			},
			"確定": function() {
				
				
				$( this ).dialog( "close" );
			}
		}
 	});
 	
	
 
	//likeUsers-dialog-form
	$("#likeUsers-dialog-form").dialog({
 		autoOpen : false,
 		modal : true,
 		width : 500,
 		height : 600
 	});
	
	//visitors-dialog-form
	$("#visitors-dialog-form").dialog({
 		autoOpen : false,
 		modal : true,
 		width : 500,
 		height : 600
 	});
	
	//blockUsers-dialog-form
	$("#blockUsers-dialog-form").dialog({
 		autoOpen : false,
 		modal : true,
 		width : 500,
 		height : 600
 	});
	

	
	
	
	changeField("queryFormField");
});

function changeField(fieldName)
{
	$("#queryFormField").hide();
	//$("#updateFormField").hide();
	
	$("#" + fieldName).show();
}

function updateData() {
	var dfGroupElem = $('[groupval=df]');
	
	var updateFiald = "";
	var postData = {
			'id' : $('#id').val(),
			'userName' : $('#userName').val()
	};
	
	$.each(dfGroupElem, function() {
		
		if ($(this).val() != $(this).attr("orgVal"))
		{   
			//alert('aa');
			//alert($(thisObj).val() + " " + $(thisObj).attr("orgVal"));
			//alert($(this).attr("id"));
			//postData['displayName'] = $('#displayName').val();
			postData[$(this).attr("id")] = $(this).val();
			updateFiald = updateFiald + $(this).attr("id") + ":";
		}		
	});
	
	postData["updateFiald"] = updateFiald;
	
	
	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Member/MemberDataQuery/Update",
		data : postData,
		success : function(data) {

				

			

			$.unblockUI();
			
		},
		dataType : "json",
		traditional : true
	});
}

function readData() {

	disTable.fnDraw();
}



function showDetailEvent(id)
{
	//$("#dialog:ui-dialog").dialog( "destroy" );
	
	$.blockUI({ message: '<div>查詢資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
	var postData = {
			id : id,
	};

		$.ajax({
			type : "GET",
			url : "/funbackend/controller/Member/MemberDataQuery/Read/Id",
			data : postData,
			success : function(data) {
				$.unblockUI();
				$("#dialog-form").dialog("open");
				
				// Form Control Value Setting
				
				//記錄原值與值
				$('#id').attr("orgVal", data.id); //使用者名稱
				$('#id').val(data.id); //使用者名稱
				$('#userName').attr("orgVal", data.userName); //使用者名稱	
				$('#userName').val(data.userName); //使用者名稱
				
				
				if(data.pic == null)
				{
					$('#picShow').attr("src", ""); //大頭照
					$('#pic').attr("orgVal", ""); //大頭照
					$('#pic').val(""); //大頭照
				}
				else 
				{
					$('#picShow').attr("src", "/funbackend/controller/Files/get/" + data.pic); //大頭照
					$('#pic').attr("orgVal", data.pic); //大頭照
					$('#pic').val(data.pic); //大頭照
				}
				
				
				if(data.displayName == null)
				{
					$('#displayName').attr("orgVal", ""); //顯示名稱
					$('#displayName').val(""); //顯示名稱
				}
				else 
				{
					$('#displayName').attr("orgVal", data.displayName); //顯示名稱
					$('#displayName').val(data.displayName); //顯示名稱
				}
					
				if(data.fake == null)
				{
					$('#fake').attr("orgVal", ""); //假帳號
					$("#fake").val(""); //假帳號	
				}
				else 
				{
					$('#fake').attr("orgVal", data.fake); //假帳號
					$("#fake").val(data.fake.toString()); //假帳號	
				}
				
				if(data.birthday == null)
				{
					$('#birthday').attr("orgVal", ""); //生日
					$("#birthday").val(""); //生日
				}
				else 
				{
					$('#birthday').attr("orgVal", data.birthday); //生日
					$("#birthday").val(data.birthday); //生日
				}
				
				if(data.email == null)
				{
					$('#email').attr("orgVal", ""); //信箱
					$("#email").val(""); //信箱
				}
				else 
				{
					$('#email').attr("orgVal", data.email); //信箱
					$("#email").val(data.email); //信箱
				}
				
				if(data.phoneNo == null)
				{
					$('#phoneNo').attr("orgVal", ""); //電話號碼
					$("#phoneNo").val(""); //電話號碼
				}
				else 
				{
					$('#phoneNo').attr("orgVal", data.phoneNo); //電話號碼
					$("#phoneNo").val(data.phoneNo); //電話號碼
				}
					
				if(data.countryCode == null)
				{
					$('#countryCode').attr("orgVal", ""); //國碼
					$("#countryCode").val(""); //國碼	
				}
				else 
				{
					$('#countryCode').attr("orgVal", data.countryCode); //國碼
					$("#countryCode").val(data.countryCode); //國碼	
				}
							
				if(data.address == null)
				{	
					$('#address').attr("orgVal", ""); //地址
					$("#address").val(""); //地址
				}
				else 
				{
					$('#address').attr("orgVal", data.address); //地址
					$("#address").val(data.address); //地址
				}
						
				if(data.numOfLikes == null)
				{
					$('#numOfLikes').attr("orgVal", ""); //贊數量
					$("#numOfLikes").val(0); //贊數量
				}
				else 
				{
					$('#numOfLikes').attr("orgVal", data.numOfLikes); //贊數量
					$("#numOfLikes").val(data.numOfLikes); //贊數量
				}
				
				if(data.ranking == null)
				{
					$('#ranking').attr("orgVal", ""); //排名
					$("#ranking").val(""); //排名
				}
				else 
				{
					$('#ranking').attr("orgVal", data.ranking); //排名
					$("#ranking").val(data.ranking); //排名
				}
				
				if(data.locationDateTime == null)
				{	
					$('#locationDateTime').attr("orgVal", ""); //最後定位時間
					$("#locationDateTime").val(""); //最後定位時間
				}
				else 
				{
					$('#locationDateTime').attr("orgVal", data.locationDateTime); //最後定位時間
					$("#locationDateTime").val(data.locationDateTime); //最後定位時間
				}
				
				if(data.placeName == null)
				{
					$('#placeName').attr("orgVal", ""); //最後打卡地點名稱
					$("#placeName").val(""); //最後打卡地點名稱
				}
				else 
				{
					$('#placeName').attr("orgVal", data.placeName); //最後打卡地點名稱
					$("#placeName").val(data.placeName); //最後打卡地點名稱
				}
				
				if(data.interest == null)
				{
					$('#interest').attr("orgVal", ""); //興趣
					$("#interest").val(""); //興趣
				}
				else 
				{
					$('#interest').attr("orgVal", data.interest); //興趣
					$("#interest").val(data.interest); //興趣
				}
				
				if(data.profession == null)
				{
					$("#profession").attr("orgVal", ""); //專長
					$("#profession").val(""); //專長
				}
				else 
				{
					$("#profession").attr("orgVal", data.profession); //專長
					$("#profession").val(data.profession); //專長
				}
			
				if(data.school == null)
				{
					$("#school").attr("orgVal", ""); //學校
					$("#school").val(""); //學校
				}
				else 
				{
					$("#school").attr("orgVal", data.school); //學校
					$("#school").val(data.school); //學校
				}
					
				if(data.description == null)
				{
					$("#description").attr("orgVal", ""); //自我介紹
					$("#description").val(""); //自我介紹
				}
				else 
				{
					$("#description").attr("orgVal", data.description); //自我介紹
					$("#description").val(data.description); //自我介紹
				}
					
				if(data.updateTime == null)
				{
					$("#updateTime").attr("orgVal", ""); //更新時間
					$("#updateTime").val(""); //更新時間
				}
				else 
				{
					$("#updateTime").attr("orgVal", data.updateTime); //更新時間
					$("#updateTime").val(data.updateTime); //更新時間
				}
							
				if(data.monthScore == null)
				{
					$("#monthScore").attr("orgVal", ""); //月得分
					$("#monthScore").val(0); //月得分
				}
				else 
				{
					$("#monthScore").attr("orgVal", data.monthScore); //月得分
					$("#monthScore").val(data.monthScore); //月得分
				}
					
				if(data.totalScore == null)
				{
					$("#totalScore").attr("orgVal", ""); //總得分
					$("#totalScore").val(0); //總得分
				}
				else 
				{
					$("#totalScore").attr("orgVal", data.totalScore); //總得分
					$("#totalScore").val(data.totalScore); //總得分
				}
						
				if(data.rankingCompare == null)
				{
					$("#rankingCompare").attr("orgVal", ""); //排行榜上升或下降
					$("#rankingCompare").val(""); //排行榜上升或下降
				}
				else 
				{
					$("#rankingCompare").attr("orgVal", data.rankingCompare); //排行榜上升或下降
					$("#rankingCompare").val(data.rankingCompare); //排行榜上升或下降
				}
								
				if(data.gender == null)
				{
					$("#gender").attr("orgVal", ""); //性別
					$("#gender").val(""); //性別
				}
				else 
				{
					$("#gender").attr("orgVal", data.gender); //性別
					$("#gender").val(data.gender); //性別
				}
									
				if(data.deleted == null)
				{
					$("#deleted").attr("orgVal", ""); //封存
					$('#deleted').val(""); //封存
				}
				else 
				{
					$("#deleted").attr("orgVal", data.deleted); //封存
					$('#deleted').val(data.deleted.toString()); //封存
				}
			
				//照片管理
				
				$("#gallery").empty();
				$("#trash ul").remove();
				$.each(data.photos, function (k, v) {
					//i++;
					//rowData[i] = "<img name='photosShow' src='/funbackend/controller/Member/file/get/" + 
						//v.photo + "' photoName='" + v.photo + "' style='width:100px; height: 100px' />";
						
						var photoObj = "<li class='ui-widget-content ui-corner-tr'>" +
						"<h5 class='ui-widget-header'></h5>" +
						"<img name='photosShow' photoName='" + v.photo + "' src='/funbackend/controller/Files/get/" + v.photo + "' alt='The peaks of High Tatras' width='96' height='72' />" +
						"<a photoName='" + v.photo + "' href='/funbackend/controller/Files/get/normal/" + v.photo + "' title='View larger image' class='ui-icon ui-icon-zoomin'>View larger</a>" +
						"<a href='link/to/trash/script/when/we/have/js/off' title='Delete this image' class='ui-icon ui-icon-trash'>Delete image</a>" +
						"</li>";
						
						$("#gallery").append(photoObj);
				});
				
				initGrallery();
				
				/*
				var i = -1;
				var rowData = ["","","","",""];
				$('#photosTable').dataTable().fnClearTable(true);
				$.each(data.photos, function (k, v) {
					i++;
					rowData[i] = "<img name='photosShow' src='/funbackend/controller/Member/file/get/" + 
						v.photo + "' photoName='" + v.photo + "' style='width:100px; height: 100px' />";
						
					if(i==5)
					{
						$('#photosTable').dataTable().fnAddData(rowData);
						i = -1;
						rowData = ["","","","",""];
					}
				});
				
				if(i >= 0)
				{
					$('#photosTable').dataTable().fnAddData(rowData);
					i = -1;
					rowData = ["","","","",""];
				}
				*/
				
				//黑名單列表
				$('#blockUsersTable').dataTable().fnClearTable(true);
				$.each(data.blockUsers, function (k, v) {
					
					$('#blockUsersTable').dataTable().fnAddData([                             					    
					    v.userName,
					    v.displayName,
					    v.gender == "M" ? "男" : v.gender == "F" ? "女" : "",
					]); 
					
				});
				
				//拜訪者
				$('#visitorsTable').dataTable().fnClearTable(true);
				$.each(data.visitors, function (k, v) {
					
					$('#visitorsTable').dataTable().fnAddData([                             					    
					    v.createDateTime,
					    v.userName
					]); 
					
				});
				
				//給其它Users贊
				$('#likeUsersTable').dataTable().fnClearTable(true);
				$.each(data.likeUsers, function (k, v) {
					
					$('#likeUsersTable').dataTable().fnAddData([                             					    
					    v.createDateTime,
					    v.userName
					]); 
					
				});				
				
				if(data.location == null)
				{
					//最後定位點
					$("#location\\.lat").attr("orgVal", "");
					$('#location\\.lat').val(""); 
					$("#location\\.lon").attr("orgVal", "");
					$('#location\\.lon').val(""); 
				}
				else 
				{
					$("#location\\.lat").attr("orgVal", data.location["lat"]);
					$("#location\\.lat").val(data.location["lat"]); 
					$("#location\\.lon").attr("orgVal", data.location["lon"]);
					$("#location\\.lon").val(data.location["lon"]); 
					
					 var latlng = new google.maps.LatLng(data.location["lat"], data.location["lon"]);
					    var myOptions = {
					      zoom: 12,
					      center: latlng,
					      mapTypeId: google.maps.MapTypeId.ROADMAP
					    };
					    var currentMap = new google.maps.Map(document.getElementById("map_canvas"),
					        myOptions);
					    
					    var optionOfMarker = {
						    	position: latlng,
						    	map: currentMap,
						    	title: "I am here!"
						   	};
						    
						var mapMarker = new google.maps.Marker(optionOfMarker);
						mapMarker.setAnimation(google.maps.Animation.DROP);
				}
						
				var dfGroupElem = $('[groupval=df]');
				
				$.each(dfGroupElem, function() {
					
					$(this).blur(function() {
					    
						inputOnBlurValid(this);
						
					});
				});
				
				
				
				
			},
			dataType : "json",
			traditional : true
		});
}
	
	
function inputOnBlurValid(thisObj)
{
	if ($(thisObj).val() != $(thisObj).attr("orgVal"))
	{   
		//alert($(thisObj).val() + " " + $(thisObj).attr("orgVal"));
		
		//$(thisObj).css({'background-color' : '#a2d1e7'});
	} else {
		//$(thisObj).css({'background-color' : ''});
	}
}
	
function dataEachRowAdd(data)
{    	 
	$('#jtable').dataTable().fnClearTable(true);


    $.each(data, function (k, v) {
    
        $('#jtable').dataTable().fnAddData([
			"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>",                                
            v.userName,
            v.displayName,
            v.gender == "M" ? "男" : v.gender == "F" ? "女" : "",
            v.countryCode,
            v.online == true ? "是" : "否",
            v.fake == true ? "是" : "否",
            v.numOfLikes,
            v.monthScore,            
            v.totalScore,
            v.ranking,
            "<input name='showDetail' type='button' value='詳細資料' class='gcms-ui-corner-all ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' onclick='showDetailEvent(\"" + v.id + "\")'/>"
        ]);             	   
    });	         
}

</script>

<style>
	#gallery { float: left; width: 60%; min-height: 12em; } * html #gallery { height: 12em; } /* IE6 */
	.gallery.custom-state-active { background: #eee; }
	.gallery li { float: left; width: 96px; padding: 0.4em; margin: 0 0.4em 0.4em 0; text-align: center; }
	.gallery li h5 { margin: 0 0 0.4em; cursor: move; }
	.gallery li a { float: right; }
	.gallery li a.ui-icon-zoomin { float: left; }
	.gallery li img { width: 100%; cursor: move; }

	#trash { overflow:auto; float: right; width: 32%; min-height: 18em; padding: 1%;} * html #trash { height: 18em; } /* IE6 */
	#trash h4 { line-height: 16px; margin: 0 0 0.4em; }
	#trash h4 .ui-icon { float: left; }
	#trash .gallery h5 { display: none; }
</style>
<script>

function initGrallery()
{
	var $gallery = $( "#gallery" );
	var $trash = $( "#trash" );

	// let the gallery items be draggable
	$( "li", $gallery ).draggable({
		cancel: "a.ui-icon", // clicking an icon won't initiate dragging
		revert: "invalid", // when not dropped, the item will revert back to its initial position
		containment: $( "#demo-frame" ).length ? "#demo-frame" : "document", // stick to demo-frame if present
		helper: "clone",
		cursor: "move"
	});

	// let the trash be droppable, accepting the gallery items
	$trash.droppable({
		accept: "#gallery > li",
		activeClass: "ui-state-highlight",
		drop: function( event, ui ) {
			deleteImage( ui.draggable );
		}
	});

	// let the gallery be droppable as well, accepting items from the trash
	$gallery.droppable({
		accept: "#trash li",
		activeClass: "custom-state-active",
		drop: function( event, ui ) {
			recycleImage( ui.draggable );
		}
	});
	
	// resolve the icons behavior with event delegation
	$( "ul.gallery > li" ).click(function( event ) {
		var $item = $( this ),
			$target = $( event.target );

		if ( $target.is( "a.ui-icon-trash" ) ) {
			deleteImage( $item );
		} else if ( $target.is( "a.ui-icon-zoomin" ) ) {
			viewLargerImage( $target );
		} else if ( $target.is( "a.ui-icon-refresh" ) ) {
			recycleImage( $item );
		}

		return false;
	});
	

	// image deletion function
	var recycle_icon = "<a href='link/to/recycle/script/when/we/have/js/off' title='Recycle this image' class='ui-icon ui-icon-refresh'>Recycle image</a>";
	function deleteImage( $item ) {
		$item.fadeOut(function() {
			var $list = $( "ul", $trash ).length ?
				$( "ul", $trash ) :
				$( "<ul class='gallery ui-helper-reset'/>" ).appendTo( $trash );

			$item.find( "a.ui-icon-trash" ).remove();
			$item.append( recycle_icon ).appendTo( $list ).fadeIn(function() {
				$item
					.animate({ width: "48px" })
					.find( "img" )
						.animate({ height: "36px" });
			});
		});
	}

	// image recycle function
	var trash_icon = "<a href='link/to/trash/script/when/we/have/js/off' title='Delete this image' class='ui-icon ui-icon-trash'>Delete image</a>";
	function recycleImage( $item ) {
		$item.fadeOut(function() {
			$item
				.find( "a.ui-icon-refresh" )
					.remove()
				.end()
				.css( "width", "96px")
				.append( trash_icon )
				.find( "img" )
					.css( "height", "72px" )
				.end()
				.appendTo( $gallery )
				.fadeIn();
		});
	}

	// image preview function, demonstrating the ui.dialog used as a modal window
	function viewLargerImage( $link ) {
		
		var src = $link.attr("href");
		var photoName = $link.attr("photoName");
		
		$("#picZoomShow").attr("src", src);	
				
	 	//photos zoomin
	 	$("#photos-zoomin-dialog").dialog({
	 		title: photoName,
			resizable: true,
	 		autoOpen : false,
	 		modal : true,
	 		width : 600,
	 		height : 600
	 	});
	 	
		$("#photos-zoomin-dialog").dialog("open");
	}
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
		<legend>會員基本資料查詢</legend>
		<p />
		<form id="queryform">
			<label>帳號名稱:</label><input id="userNameQ" name="userNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br /> 
			<label>顯示名稱:</label><input id="displayNameQ" name="displayNameQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
			<label>性別 :</label>
				<select id="genderQ" name="genderQ" >
					<option value="All">全部</option>
					<option value="M">男</option>
					<option value="F">女</option>					
				</select><br /><br />
			<label>電話:</label><input id="phoneNoQ" name="phoneNoQ" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/><br /><br />
		</form>			
		<button id="readSendBtn" name="readSendBtn">送出</button>
	</fieldset>
	
	<br/>
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
	
	<div id="photos-zoomin-dialog">
		<img id="picZoomShow" name="picZoomShow" src=""/>
	</div>
	<div id="photos-dialog-form" title="照片管理">
		<ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix">
		
		</ul>
		<div id="trash" class="ui-widget-content ui-state-default">
			<h4 class="ui-widget-header">垃圾筒<span class="ui-icon ui-icon-trash" style="color: white;"></span></h4>
		</div>		
	</div>
	<div id="blockUsers-dialog-form" title="黑名單列表">
		<table id="blockUsersTable"  cellpadding="0" cellspacing="0" border="0" class="display" >
	        <thead>
	            <tr>	         
	                <th>帳號名稱</th>
	                <th>顯示名稱</th>
	                <th>性別</th>	                              
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
	</div>
	
	<div id="likeUsers-dialog-form" title="給其它玩家贊">
		<table id="likeUsersTable"  cellpadding="0" cellspacing="0" border="0" class="display" >
	        <thead>
	            <tr>	         
	            	<th>日期時間</th>    	
	                <th>帳號名稱</th>	                              
	            </tr>
	        </thead>
	        <tbody>
	            <tr class="row">
	                <td></td>
	                <td></td>
	            </tr>
	        </tbody>
    	</table>
	</div>
	
	<div id="visitors-dialog-form" title="拜訪者清單">
		<table id="visitorsTable"  cellpadding="0" cellspacing="0" border="0" class="display" >
	        <thead>
	            <tr>	         
	            	<th>日期時間</th>    	
	                <th>帳號名稱</th>	                              
	            </tr>
	        </thead>
	        <tbody>
	            <tr class="row">
	                <td></td>
	                <td></td>
	            </tr>
	        </tbody>
    	</table>
	</div>
    <div id="dialog-form" title="詳細資料">
    	<form action="UpdateUser" method="post">
        	<table style="border: 1px;" >
        		<!-- <thead>
	        		<tr>
	        			<th class=" ui-state-default" style="width:200px">欄位名稱</th><th  class=" ui-state-default" style="width:100%">欄位資料</th>
	        		</tr>
	        		<tr>
	        			<th class=" ui-state-default" style="width:200px">欄位名稱</th><th  class=" ui-state-default" style="width:100%">欄位資料</th>
	        		</tr>
        		</thead> -->
        		<tbody>
        			<tr>        				
        				<td rowspan="16" valign="top">
        					<img id="picShow" name="picShow" src="" style="width:100px; height: 100px" />
        					<input groupval="df" id="pic" name="pic" type="hidden" value="" />        					
        				</td>
        				<td>帳號名稱</td>
        				<td>
        					<input id="id" name="id" type="hidden" value="" />
        					<input id="userName" name="userName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" />
        				</td>
        				<td>生日</td>
        				<td><input groupval="df" id="birthday" name="birthday" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        				<td>興趣</td>
        				<td><input groupval="df" id="interest" name="interest" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>        				
        				<td>顯示名稱</td>
        				<td><input groupval="df" id="displayName" name="displayName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/></td>
        				<td>信箱</td>
        				<td><input groupval="df" id="email" name="email" type="text" value="" size="20" maxlength="50" class="text ui-widget-content ui-corner-all"  readonly="readonly" style="border: 0px" /></td>
        				<td>專長</td>
        				<td><input groupval="df" id="profession" name="profession" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>        		        		
        			</tr>        			
        			<tr>
        				<td>性別</td>
        				<td>
        					<select groupVal="df" id="gender">
								<option value="M">男</option>
								<option value="F">女</option>					
							</select>
        				</td>
        				<td>手機號碼</td>
        				<td><input groupval="df" id="phoneNo" name="phoneNo" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        				<td>學校</td>
        				<td><input groupval="df" id="school" name="school" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>        				
        			</tr>
        			<tr>
        				<td>國碼</td>
        				<td><input groupval="df" id="countryCode" name="countryCode" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        				<td>地址</td>
        				<td><input groupval="df" id="address" name="address" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        				<td>假帳號</td>
        				<td>
        					<select groupval="df" id="fake">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
        				</td>
        			</tr>
        			<tr>
        				<td valign="top" rowspan="9">最後定位點</td>
        				<td valign="top" colspan="3" rowspan="9">
        					lat:<input id="location.lat" name="location.lat" type="text" value="" size="13" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" />
        					lon:<input id="location.lon" name="location.lon" type="text" value="" size="13" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /><br/>
        					<div id="map_canvas" style="width: 300px; height:200px; border: 1px solid #000;"></div>
        				</td>
        				<td>封存</td>
        				<td>
        				<select groupval="df" id="deleted">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
        				</td>
        				
        			</tr>
        			<tr>
        				<td>贊</td>
        				<td><input id="numOfLikes" name="numOfLikes" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>排行名次</td>
        				<td><input id="ranking" name="ranking" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        				
        			</tr>
        			<tr>
        				<td>月得分</td>
        				<td><input id="monthScore" name="monthScore" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        				
        			</tr>
        			<tr>
        				<td>總得分</td>
        				<td><input id="totalScore" name="totalScore" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        				
        			</tr>
        			<tr>
        				<td>排行榜上升或下降</td>
        				<td><input id="rankingCompare" name="rankingCompare" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>最後定位時間</td>
        				<td><input id="locationDateTime" name="locationDateTime" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>最後打卡地點名稱</td>
        				<td><input id="placeName" name="placeName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>        				
        				<td>更新時間</td>
        				<td><input id="updateTime" name="updateTime" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr> 
        			<tr>
        				<td valign="top">說明</td>
        				<td colspan="5">
        					<textarea groupval="df" id="description" name="description" rows="5" cols="70" class="text ui-widget-content ui-corner-all" >
        					
        					</textarea>
        				</td>   
        				        				     			        			
        			</tr>
        			<tr>
        				<td colspan="6">
        					<table>
        						<tr>
	        						<td>
	        							<input id="photosBtn" type="button" value="照片管理" />
	        						</td>
			        				<td>
			        					<input id="likeUsersBtn" type="button" value="給其它玩家贊" />
			        				</td>
			        				<td>
			        					<input id="visitorsBtn" type="button" value="拜訪者" />
			        				</td>
			        				<td>
			        					<input id="blockUsersBtn" type="button" value="黑名單列表" />
			        				</td>
        						</tr>
        					</table>
        				</td>        				
        			</tr>
        			      			        			
        			<tr>
        				<td colspan="7" align="right"><input id="updateFrameBtn" type="button" value="存檔" /></td>
        			</tr>
        		</tbody>
        	</table>
        </form>	
    	
    </div>
</body>
</html>