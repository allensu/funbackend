/*
* TableGrid plug-in v0.9.1
* Copyright (c) 2009 James Westgate
* Dual licensed under the MIT and GPL licenses.
*
* @depends: jquery.tablesorter.js
* @depends: jquery.tablesorter.pager.js
*/

//Create closure
(function($) {

    //Plugin definition
    $.fn.extend({

        tablegrid: function(options) {

            //Plug-in defaults
            var defaults = {
                sort: true,
                page: false,
                highlight: true,
                select: true,

                cssAsc: 'ui-grid-th-sortup',
                cssDesc: 'ui-grid-th-sortdown',
                cssSelected: 'ui-grid-tr-selected',
                
                positionFixed: false,
                cssNext: 'li.ui-grid-pager-next',
                cssPrev: 'li.ui-grid-pager-prev',
                cssFirst: 'li.ui-grid-pager-first',
                cssLast: 'li.ui-grid-pager-last'
            };

            //Merge with options to create settings
            var settings = $.extend({}, defaults, options);

            //Return matched set iterator
            return this.each(function() {

                var table = $(this);

                //Wrap a div around the table
                $(this).wrap('<div class="ui-grid ui-widget ui-widget-content ui-corner-all"></div>');

                //Add the appropriate classes
                $(this).addClass('ui-grid-content ui-widget-content');
                $('thead tr th', this).addClass('ui-state-default');
                $('tbody tr td', this).addClass('ui-widget-content');
                $('tbody tr td', this).addClass('tempclear');

                //Wrap header text with anchor and icon
                $('thead tr th', this)
                    .each(function() {
                        $(this).html('<a href="#"><span class="ui-icon ui-icon-carat-2-n-s"></span>' + $(this).text() + '</a>');
                    })
                    .hover(
                        function() { $(this).addClass('ui-state-hover'); },
                        function() { $(this).removeClass('ui-state-hover'); }
                    );

                //Highlighting
                if (settings.highlight) {
                    $('tbody tr', this).hover(
                        function() { $('td', this).addClass('ui-state-hover'); },
                        function() { $('td', this).removeClass('ui-state-hover'); }
                    );
                }

                //Selecting
                if (settings.select) {

                    //On row click
                    $('tbody tr', this).click(function() {
                        $('tbody tr td', table).removeClass('ui-state-highlight ' + settings.cssSelected);
                        $('td', this).addClass('ui-state-highlight ' + settings.cssSelected);
                        $(this).trigger("selectRow");
                    });
                }

                //Apply sortable plug-in
                if (settings.sort && $.fn.tablesorter) {

                    //Call the sortable plug-in with combined options
                    $(this).tablesorter(settings);
                    $(this).bind('sortEnd', function(args) {
                        $('thead tr th span', this).removeClass('ui-icon-triangle-1-n ui-icon-triangle-1-s').addClass('ui-icon-carat-2-n-s');
                        $('thead tr th.' + settings.cssDesc + ' span', this).removeClass('ui-icon-carat-2-n-s').addClass('ui-icon-triangle-1-s');
                        $('thead tr th.' + settings.cssAsc + ' span', this).removeClass('ui-icon-carat-2-n-s').addClass('ui-icon-triangle-1-n');
                    });
                }

                //Apply the pager plug-in
                if (settings.page && $.fn.tablesorterPager) {

                    if (settings.container == null) {

                        var html =
                        '<div class="ui-grid-pager ui-widget">' +
                        '<ul class="ui-widget ui-helper-clearfix ui-grid-icons" style="float:left">' +
                        '<li class="ui-state-default ui-corner-all ui-grid-pager-first"><span class="ui-icon ui-icon-seek-first"></span></li>' +
                        '<li class="ui-state-default ui-corner-all ui-grid-pager-prev"><span class="ui-icon ui-icon-seek-prev"></span></li>' +
                        '</ul>' +

                        '<input type="text" class="pagedisplay" style="display:block; float:left"/>' +

                        '<ul class="ui-widget ui-helper-clearfix ui-grid-icons"  style="float:left">' +
                        '<li class="ui-state-default ui-corner-all ui-grid-pager-next"><span class="ui-icon ui-icon-seek-next"></span></li>' +
                        '<li class="ui-state-default ui-corner-all ui-grid-pager-last"><span class="ui-icon ui-icon ui-icon-seek-end"></span></li>' +
                        '</ul>' +

                        '<select class="pagesize" style="display:block; float:left; min-height:18px">' +
                        '<option selected="selected" value="10">10</option>' +
                        '<option value="20">20</option>' +
                        '<option value="30">30</option>' +
                        '<option value="40">40</option>' +
                        '</select>' +
                        '</div>';

                        //Add pager
                        var pager = $(html).insertAfter('div.ui-grid', this);

                        //Highlight grid buttons
                        $('ul.ui-grid-icons li').hover(
        				    function() { $(this).addClass('ui-state-hover'); },
				            function() { $(this).removeClass('ui-state-hover'); }
			            );

                        //Set the pager in the settings object
                        settings.container = pager;
                    }

                    //Call the pager plug-in
                    $(this).tablesorterPager(settings);
                }
            });
        }
    });

    // end of closure
})(jQuery);
