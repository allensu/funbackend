$.fx.speeds._default = 1000;
$(function() {
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

	// Form View
	$("#dialog-form").dialog({
		autoOpen : false,
		show : "scale",
		modal : true,
		hide : "scale"
	});

	$("#combobox").combobox();
	$("#createSubmitBtn").button();

	// Create Btn
	$("#createBtn").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	}).click(function() {
		$("#dialog-form").dialog("open");
		return false;
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

	// Update Btn
	$("#updateBtn").button({
		icons : {
			primary : "ui-icon-wrench"
		}
	}).click(function() {

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

/*
 * 
 * function createBegin() { $("#dialog-form").block({ message: '<div>處理中...</div>',
 * overlayCSS: { backgroundColor: '#4297D7'} }); }
 * 
 * function createComplate() { $("#dialog-form").unblock(); }
 * 
 * function createSuccess() {
 *  }
 * 
 * function createFailure() {
 *  }
 */

function dataEachRowAdd(data) {
	$('#jtable').dataTable().fnClearTable(true);

	$.each(data, function(k, v) {

		$('#jtable').dataTable()
				.fnAddData(
						[
								"<input id='dataId' name='dataId' type='checkbox' value='"
										+ v.id + "'/>",
								v.accountId,
								v.accountName,
								v.category,
								// v.lastLoginDateTime,
								// v.createDateTime
								jsonDate2Format(v.lastLoginDateTime,
										"yyyy/m/d TT hh:MM"),
								jsonDate2Format(v.createDateTime,
										"yyyy/m/d TT hh:MM") ]);
	});
}

function readData() {

	$.getJSON('/funbackend/controller/Account/ReadUser', function(data) {

		// //清空頁面上的資料
		// $('#jtable > tbody > tr').remove();

		dataEachRowAdd(data);

		$.unblockUI();
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
		url : "/funbackend/controller/Account/DeleteAccount",
		data : postData,
		success : function(data) {

			dataEachRowAdd(data);

			$.unblockUI();

		},
		dataType : "json",
		traditional : true
	});
}