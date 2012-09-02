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
	$("#updateSubmitBtn").button();
	
});


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
				$('#userName').val(data.userName);
				$('#displayName').val(data.displayName);
				
				$("#fake").val(data.fake);
				
				$("#birthday").val(data.birthday);
				$("#email").val(data.email);
				$("#phoneNo").val(data.phoneNo);
				$("#countryCode").val(data.countryCode);
				
				
				$("#address").val(data.address);
				$("#numOfLikes").val(data.numOfLikes);
				$("#ranking").val(data.ranking);
				//$("#likeUsers").val(data.likeUsers);
				//$("#visitors").val(data.visitors);
				//$("#blockUsers").val(data.blockUsers);
				//$("#location").val(data.location);
				$("#locationDateTime").val(data.locationDateTime);
				$("#placeName").val(data.placeName);
				$("#interest").val(data.interest);
				$("#profession").val(data.profession);
				$("#school").val(data.school);
				$("#description").val(data.description);
				$("#updateTime").val(data.updateTime);
				$("#monthScore").val(data.monthScore);
				$("#totalScore").val(data.totalScore);
				$("#rankingCompare").val(data.rankingCompare);

				
				
				$("#gender").val(data.gender);
				$('#deleted').val(data.deleted);
				$("#dialog-form").dialog("open");
			},
			dataType : "json",
			traditional : true
		});
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
        				<td><input id="userName" name="userName" readonly="readonly" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" style="border: 0px" /></td>
        			</tr>
        			<tr>
        				<td>顯示名稱</td>
        				<td><input id="displayName" name="displayName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			<tr>
        				<td>性別</td>
        				<td>
        					<select id="gender">
        						<option value="">未設定</option>
								<option value="M">男</option>
								<option value="F">女</option>					
							</select>
        				</td>
        			</tr>
        			<tr>
        				<td>生日</td>
        				<td><input id="birthday" name="birthday" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>信箱</td>
        				<td><input id="email" name="email" type="text" value="" size="20" maxlength="50" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>手機號碼</td>
        				<td><input id="phoneNo" name="phoneNo" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>國碼</td>
        				<td><input id="countryCode" name="countryCode" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			<tr>
        				<td>地址</td>
        				<td><input id="address" name="address" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>贊</td>
        				<td><input id="numOfLikes" name="numOfLikes" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>上傳的照片</td>
        				<td>&nbsp;</td>
        			</tr>
        			<tr>
        				<td>排行名次</td>
        				<td><input id="ranking" name="ranking" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
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
        				<td>&nbsp;location</td>
        			</tr>
        			
        			<tr>
        				<td>最後定位時間</td>
        				<td><input id="locationDateTime" name="locationDateTime" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>最後打卡地點名稱</td>
        				<td><input id="placeName" name="placeName" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>興趣</td>
        				<td><input id="interest" name="interest" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>專長</td>
        				<td><input id="profession" name="profession" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>學校</td>
        				<td><input id="school" name="school" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>說明</td>
        				<td>
        					<textarea id="description" name="description" rows="5" cols="20" class="text ui-widget-content ui-corner-all" >
        					
        					</textarea>
        				</td>
        			</tr>
        			
        			<tr>
        				<td>假帳號</td>
        				<td>
        					<select id="fake">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
        				</td>
        			</tr>
        			<tr>
        				<td>封存</td>
        				<td>
        				<select id="deleted">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
        				</td>
        			</tr>
        			
        			<tr>
        				<td>更新時間</td>
        				<td><input id="updateTime" name="updateTime" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			<tr>
        				<td>月得分</td>
        				<td><input id="monthScore" name="monthScore" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			<tr>
        				<td>總得分</td>
        				<td><input id="totalScore" name="totalScore" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			<tr>
        				<td>排行榜上升或下降</td>
        				<td><input id="rankingCompare" name="rankingCompare" type="text" value="" size="20" class="text ui-widget-content ui-corner-all" /></td>
        			</tr>
        			
        			
        			
        			<tr>
        				<td colspan="2" align="right"><input id="updateSubmitBtn" type="submit" value="存檔" /></td>
        			</tr>
        		</tbody>
        	</table>
        </form>
    </div>
</body>
</html>