<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員管理系統</title>

<link href="../../Content/Site.css" rel="stylesheet" type="text/css" />
<link href="../../themes/${userBean.theme}/jquery-ui-1.8.7.custom.css" rel="stylesheet" type="text/css" />    
<link href="../../Content/JQueryUICustom.css" rel="stylesheet" type="text/css" />
<script src="../../Script/jquery-1.4.1.js" type="text/javascript"></script>
<script src="../../Script/jquery-ui-1.8.7.custom.min.js" type="text/javascript"></script>
<script src="../../Script/jquery.blockUI.js" type="text/javascript"></script>
<script src="../../Script/jquery.gcms.js" type="text/javascript"></script>

<script type="text/javascript" language="javascript">

        $(function () {
            $('#logoutBtn').button().click(function () {                
            	window.location.replace("/funbackend/controller/Account/Logout");
            });
        });
</script>
    
<script type="text/javascript">

        function GetHeight() {
            var y = 0;
            if (self.innerHeight) {
                y = self.innerHeight;
            }
            else if (document.documentElement && document.documentElement.clientHeight) {
                y = document.documentElement.clientHeight;
            }
            else if (document.body) {
                y = document.body.clientHeight;
            }
            return y;
        }

        $(function () {

            $("#tabs").tabs({
                tabTemplate: " <li><a href='#{href}'>#{label}</a><span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
                show: function (obj, tab) {
                    $(tab.panel).find('IFRAME').attr("height", GetHeight() - 80);
                }
            });

            $('#tabs span.ui-icon-close').live('click', function () {
                var index = $('li', $("#tabs")).index($(this).parent());
                $("#tabs").tabs('remove', index);
            });

           
        });

        
        $(function () {

            //至 Server 端取得 Accordion 資料
            $.getJSON('/funbackend/controller/Account/MenuList', function (data) {

                $.each(data, function (k, v) {

                    var content = "";

                    if (v.content != '') {
                        $.each(v.content, function (x, y) {
                            content += "<button class='gcms-ui-corner-all' iId='" + y.id + "' url='" + y.url + "'>" + y.title + "</button><br/>";
                        });
                    }

                    $("#accordion").append("<h3><a href='#'>" + v.title + "</a></h3><div style='padding: 0,0,0,0;'>" + content + "</div>");
                });

                $("#accordion").accordion({
                    autoHeight: false,
                    navigation: true
                });

                $("button.gcms-ui-corner-all").button().click(function () {
                    var currTabCount = $("#tabs").tabs("length");
                    var iId = $(this).attr("iId");
                    var url = $(this).attr("url");
                    currTabCount++;

                    //驗證作業項是否已開啟
                    var tabsIframe = $("#tabs div iframe");
                    var tabsIndex = 1;
                    var isShow = false;
                    tabsIframe.each(function (x, y) {

                        if (y.id == iId) {
                            $("#tabs").tabs("select", tabsIndex);
                            isShow = true;
                        }

                        tabsIndex++;
                    });

                    if (isShow) //此作業項已開啟
                        return false;

                    //顯示作業項
                    var content = "<iframe id='" + iId + "' name='frame" + currTabCount + "' style='' frameborder='0' marginheight='0'";
                    content += "scrolling='yes' onload='this.height=frame" + currTabCount + ".document.body.scrollHeight' src='" + url + "'";
                    content += "width='100%' height='100%'></iframe>";
                    $("<div id='tabs-" + currTabCount + "'>" + content + "</div>").appendTo("#tabs");
                    $("#tabs").tabs("add", "#tabs-" + currTabCount, this.innerText);
                    $("#tabs").tabs("select", currTabCount - 1);
                    $("#tabs-" + currTabCount).block({ message: '<div>系統載入中...</div>', overlayCSS: { backgroundColor: '#4297D7'} });
                    var iframeObj = document.getElementById(iId);
                    iframeObj.onreadystatechange = function () {
                        if (this.readyState == "complete") {
                            $("#tabs-" + currTabCount).unblock();
                        }
                    }
                    iframeObj.onload = function () {
                        $("#tabs-" + currTabCount).unblock();
                    }

                });
            });
        });
        
    </script>
    
</head>
<body>

	<div id="wrapper" class="abs">
		<div id="bar_top" class="abs ui-widget-header ui-corner-all">
			<span class="float_right" id="clock"></span>
			<button id="logoutBtn">系統登出</button>
		</div>
		<div id="desktop" class="abs">
			<table id="mainLayout">
				<tbody>
					<tr>
						<td id="mainLeftLayout" valign="top">
							<div id="navi">
								<div id="accordion"></div>
							</div>
						</td>
						<td id="mainCenterLayout" valign="top">
							<div id="main">
								<div id="tabs">
									<ul>
										<li><a href="#tabs-1">公告</a></li>
									</ul>
									<div id="tabs-1">
										<p>最新公告</p>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>