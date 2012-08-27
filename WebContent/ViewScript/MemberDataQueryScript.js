	$.fx.speeds._default = 1000;
	$(function() {
		$("#submit").button();
		
		$( "#toolBar" ).buttonset();

		
		
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
            "sPaginationType": "full_numbers"
        });
		
		
	});
	
	
    function readData() {

        $.getJSON('/funbackend/controller/Member/MemberDataQuery/Read', function (data) {

        	//alert('aa');
        	dataEachRowAdd(data);
        	//alert(data);
            $.unblockUI();
        });
    }
	
    
    function dataEachRowAdd(data)
    {    	 
    	//alert('dataEachRowAdd');
    	$('#jtable').dataTable().fnClearTable(true);
        //alert(data);
        $.each(data, function (k, v) {
        
            $('#jtable').dataTable().fnAddData([
                v.userName,
                v.displayName,
                v.countryCode,
                v.online,
                v.fake,
                v.totalScore 
            ]);
        });	    
    }