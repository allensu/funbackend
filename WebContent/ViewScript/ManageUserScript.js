	$.fx.speeds._default = 1000;
	$(function() {
		 // Form View
		  $("#dialog-form").dialog({
	            autoOpen: false,
	            show: "blind",
	            modal: true,
	            hide: "explode"
	        });
		  
		$("#combobox").combobox();
		$("#createSubmitBtn").button();
		
		// Create Btn
		$("#createBtn").button({
            icons: {
                primary: "ui-icon-circle-plus"
            }
        }).click(function () {
            $("#dialog-form").dialog("open");
            return false;
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
		
		$('#yes').click(function () {

            $.blockUI({ message: '<div>刪除資料中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });

            //取得所選擇的SQ
            var sq = $('.ui-state-highlight')[1].innerText;

            $.ajax({
                type: "POST",
                url: '/GasManager/PaymentControl_Delete/' + sq,
                cache: false,
                dataType: "json",
                success: function (data) {
                    alert(data.result);
                },
                error: function (xhr) {
                    alert(xhr);
                },
                complete: function () {

                    $.unblockUI();
                }
            });
        });

        $('#no').click(function () {
            $.unblockUI();
            return false;
        });
        
        var oTable = $('#jtable').dataTable({
            //"sScrollY":  '100%',
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"
        });
	});
	
	/*
    
	function createBegin() {
        $("#dialog-form").block({ message: '<div>處理中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
    }

    function createComplate() {
        $("#dialog-form").unblock();
    }

    function createSuccess() {
     
    }

    function createFailure() {

    }
    */
    
    function readData() {

        $.getJSON('/funbackend/controller/Account/ReadUser', function (data) {

            //           //清空頁面上的資料
            //                    $('#jtable > tbody > tr').remove();
            $('#jtable').dataTable().fnClearTable(true);
            
            $.each(data, function (k, v) {
            
                $('#jtable').dataTable().fnAddData([
	                v.accountId,
	                v.accountName,
	                v.category,
	                //v.lastLoginDateTime,
	                //v.createDateTime
	                jsonDate2Format(v.lastLoginDateTime, "yyyy/m/d TT hh:MM"),
                    jsonDate2Format(v.createDateTime, "yyyy/m/d TT hh:MM")  
                ]);
            });

            $.unblockUI();
        });
    }