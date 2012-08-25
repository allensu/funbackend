$.fx.speeds._default = 1000;
$(function() {
	$("#submit").button();

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
	
	// Create Btn
	$("#createBtn").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {

		createData();
	});

	// Read Btn
	$("#readBtn").button({
		icons : {
			primary : "ui-icon-search"
		}
	}).click(function() {
		$.blockUI({
			message : '<div>載入資料中...</div>',
			overlayCSS : {
				backgroundColor : '#4297D7'
			}
		});
		readData();
	});

	// Delete Btn
	$("#deleteBtn").button({
		icons : {
			primary : "ui-icon-circle-close"
		}
	}).click(function() {
		var checkedCount = $('#jtable input:checked').length;

		if (checkedCount > 0)
			$.blockUI({
				message : $('#question'),
				css : {
					width : '275px'
				}
			});
	});

	$('#yes').button().click(function() {
		$.blockUI({
			message : '<div>刪除資料中...</div>',
			overlayCSS : {
				backgroundColor : '#4297D7'
			}
		});
		deleteData();
	});

	$('#no').button().click(function() {
		$.unblockUI();
		return false;
	});

	var oTable = $('#jtable').dataTable({
		// "sScrollY": '100%',
		"bJQueryUI" : true,
		"sPaginationType" : "full_numbers",
		"aoColumns": [
		              { "bSortable": false },
		              null,
		              null,
		              null
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

function readData() {

	$.getJSON('/funbackend/controller/Mqtt/ReadMessage', function(data) {

		$('#jtable').dataTable().fnClearTable(true);

		$.each(data, function(k, v) {
			
			$('#jtable').dataTable().fnAddData([
							"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>", 
							v.serial, 
							v.target,
							v.message ]);			
		});

		$.unblockUI();
	});
}

function createData() {
	
	if($('#target').val() == "" || $('#message').val() == "")
	{
		$( "#dialog-message" ).dialog({
			modal: true,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
		return;
	}
	
	var postData = {
		target : $('#target').val(),
		message : $('#message').val()
	};

	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Mqtt/CreateMessage",
		data : postData,
		success : function(data) {

			$('#jtable').dataTable().fnClearTable(true);

			$.each(data, function(k, v) {

				$('#jtable').dataTable().fnAddData([
								"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>", 
								v.serial, 
								v.target,
								v.message ]);
			});

			$.unblockUI();

			$('#target').val("");
			$('#message').val("");
		},
		dataType : "json",
		traditional : true
	});
}

function deleteData() {

	var idsArray = new Array();

	$('#jtable input:checked').each(function() {
		idsArray.push(this.value);
	});

	var postData = {
		ids : idsArray
	};

	$.ajax({
		type : "POST",
		url : "/funbackend/controller/Mqtt/DeleteMessage",
		data : postData,
		success : function(data) {
			// alert(data);

			$('#jtable').dataTable().fnClearTable(true);

			$.each(data, function(k, v) {

				$('#jtable').dataTable().fnAddData([
								"<input id='dataId' name='dataId' type='checkbox' value='" + v.id + "'/>", 
								v.serial, 
								v.target,
								v.message ]);
			});

			$.unblockUI();

		},
		dataType : "json",
		traditional : true
	});
}