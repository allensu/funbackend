<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員黑名單</title>
<jsp:include page="../../views/Common/CommonResource.jsp"></jsp:include>
<script type="text/javascript">

$.fx.speeds._default = 1000;
$(function() {
	
	var oTable = $('#jtable').dataTable({
		// "sScrollY": '100%',
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              null,
		              { "bSortable": false },
		              { "bSortable": false }
		              ]
	});
	
	readData();
});

function readData() {

	$.blockUI({ message: '<div>排行榜資料計算中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
	$.getJSON('/funbackend/controller/Rank/BlockUserRank/Read', function(data) {

		dataEachRowAdd(data);

		$.unblockUI();
	});
}

function dataEachRowAdd(data)
{
	$('#jtable').dataTable().fnClearTable(true);
	
	$.each(data, function(k, v) {
		
		$('#jtable').dataTable().fnAddData([
						v.rankNum, 
						v.userName,
						v.votes ]);			
	});
}

</script>
</head>
<body>
<table id="jtable"  cellpadding="0" cellspacing="0" border="0" class="display" >
        <thead>
            <tr>
                <th>名次</th>
                <th>帳號名稱</th>
                <th>次數</th>
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
</body>
</html>