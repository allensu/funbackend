<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>

<script type="text/javascript">
$.fx.speeds._default = 1000;
$(function() {
	
	var oTable = $('#jtable').dataTable({
		// "sScrollY": '100%',
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aaSorting": [[ 2, "desc" ]],
		"aoColumns": [
		              { "bSortable": false },
		              { "bSortable": false },
		              { "bSortable": true },
		              { "bSortable": true },
		              { "bSortable": true },
		              { "bSortable": true },
		              ]
	});
	
	readData();
});

function readData() {

	$.blockUI({ message: '<div>Po文排行榜資料計算中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
	$.getJSON('/funbackend/controller/Rank/GraffitiWallRank/Read', function(data) {
		
		dataEachRowAdd(data);
	
		$.unblockUI();
	});
}

function dataEachRowAdd(data)
{
	$('#jtable').dataTable().fnClearTable(true);
	
	$.each(data, function(k, v) {
		
		$('#jtable').dataTable().fnAddData([
						v.posterName,
						v.posterDisplayName,
						v.totalCount,
						v.postMessage,
						v.wantTo,
						v.checkIn]);			
	});
}


</script>
</head>
<body>
<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
                <th>帳號名稱</th>
                <th>顯示名稱</th>
                <th>總數</th>
                <th>文字訊息數</th>
                <th>我想...數</th>
                <th>打卡數</th>
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