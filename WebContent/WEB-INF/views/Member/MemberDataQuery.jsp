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
		modal : true,
		width : 600
	});
	
	// Form Control Define
	//$("#fake").combobox();
	//$("#deleted").combobox();
	$("#updateFrameBtn").button({
        icons: {
            primary: "ui-icon-wrench"
        }
    }).click(function () {
    	$.blockUI({ message: '<div>儲存資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
    	updateData(); 
    });
	
});

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
				
				//記錄原值與值
				$('#id').attr("orgVal", data.id); //使用者名稱
				$('#id').val(data.id); //使用者名稱
				$('#userName').attr("orgVal", data.userName); //使用者名稱	
				$('#userName').val(data.userName); //使用者名稱
				
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
			
				//$("#likeUsers").val(data.likeUsers); //給其它Users贊
				//$("#visitors").val(data.visitors); //拜訪者
				//$("#blockUsers").val(data.blockUsers); //黑名單列表
				
				alert('aa');
				alert(data.location["lat"]);
				if(data.location == null)
				{
					//最後定位點
					$("#location.lat").attr("orgVal", "");
					$('#location.lat').val(""); 
				}
				else 
				{
					$("#location.lat").attr("orgVal", data.location.lat);
					$('#location.lat').val(data.location.lat); 
				}
				
			
				//$("#likeUsers").val(data.likeUsers); //給其它Users贊
				//$("#visitors").val(data.visitors); //拜訪者
				//$("#blockUsers").val(data.blockUsers); //黑名單列表
				//$("#location").val(data.location); //最後定位點
				

				
				var dfGroupElem = $('[groupval=df]');
				
				$.each(dfGroupElem, function() {
					
					//alert($(this).val());
					$(this).blur(function() {
					    
						inputOnBlurValid(this);
						
					});
				});
				
				$("#dialog-form").dialog("open");
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
        	<table style="border: 1px;" >
        		<thead>
	        		<tr>
	        			<th class=" ui-state-default" style="width:150px">欄位名稱</th><th  class=" ui-state-default" style="width:450px">欄位資料</th>
	        		</tr>
        		</thead>
        		<tbody>
        			<tr>
        				<td>大頭照</td>
        				<td>&nbsp;pic</td>
        			</tr>    		
        			<tr>
        				<td>帳號名稱</td>
        				<td>
        					<input id="id" name="id" type="hidden" value="" />
        					<input id="userName" name="userName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" />
        				</td>
        			</tr>
        			<tr>
        				<td>顯示名稱</td>
        				<td><input groupval="df" id="displayName" name="displayName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all"/></td>
        			</tr>
        			
        			<tr>
        				<td>性別</td>
        				<td>
        					<select groupVal="df" id="gender">
								<option value="M">男</option>
								<option value="F">女</option>					
							</select>
        				</td>
        			</tr>
        			<tr>
        				<td>生日</td>
        				<td><input groupval="df" id="birthday" name="birthday" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>信箱</td>
        				<td><input groupval="df" id="email" name="email" type="text" value="" size="20" maxlength="50" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>手機號碼</td>
        				<td><input groupval="df" id="phoneNo" name="phoneNo" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>國碼</td>
        				<td><input groupval="df" id="countryCode" name="countryCode" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			<tr>
        				<td>地址</td>
        				<td><input groupval="df" id="address" name="address" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>贊</td>
        				<td><input id="numOfLikes" name="numOfLikes" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>上傳的照片</td>
        				<td>&nbsp;</td>
        			</tr>
        			<tr>
        				<td>排行名次</td>
        				<td><input id="ranking" name="ranking" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>給其它Users贊</td>
        				<td>&nbsp;likeUsers</td>
        			</tr>
        			<tr>
        				<td>拜訪者</td>
        				<td>&nbsp;visitors</td>
        			</tr>
        			<tr>
        				<td>黑名單列表</td>
        				<td>&nbsp;blockUsers</td>
        			</tr>
        			
        			<tr>
        				<td>最後定位點</td>
        				<td>
        					<input id="location.lat" name="location" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" />
        					<input id="location.lon" name="location" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" />
        				</td>
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
        				<td>興趣</td>
        				<td><input groupval="df" id="interest" name="interest" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>專長</td>
        				<td><input groupval="df" id="profession" name="profession" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>學校</td>
        				<td><input groupval="df" id="school" name="school" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>說明</td>
        				<td>
        					<textarea groupval="df" id="description" name="description" rows="5" cols="20" class="text ui-widget-content ui-corner-all" >
        					
        					</textarea>
        				</td>
        			</tr>
        			
        			<tr>
        				<td>假帳號</td>
        				<td>
        					<select groupval="df" id="fake">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
        				</td>
        			</tr>
        			<tr>
        				<td>封存</td>
        				<td>
        				<select groupval="df" id="deleted">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
        				</td>
        			</tr>
        			
        			<tr>
        				<td>更新時間</td>
        				<td><input id="updateTime" name="updateTime" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" readonly="readonly" style="border: 0px" /></td>
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
        				<td colspan="2" align="right"><input id="updateFrameBtn" type="button" value="存檔" /></td>
        			</tr>
        		</tbody>
        	</table>
        </form>
    </div>
</body>
</html>