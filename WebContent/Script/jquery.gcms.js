jQuery(document).ready(function () {
    GCMS.go();
});


//
// Namespace - Module Pattern.
//
var GCMS = (function ($, window, undefined) {
    // Expose innards of GCMS.
    return {
        go: function () {
            for (var i in GCMS.init) {
                GCMS.init[i]();
            }
        },
        init: {
            //
            // Initialize the clock.
            //
            clock: function () {
                if (!$('#clock').length) {
                    return;
                }

                // Date variables.
                var date_obj = new Date();
                var hour = date_obj.getHours();
                var minute = date_obj.getMinutes();
                var day = date_obj.getDate();
                var year = date_obj.getFullYear();
                var suffix = 'AM';

                // Array for weekday.
                var weekday_en_US = [
					'Sunday',
					'Monday',
					'Tuesday',
					'Wednesday',
					'Thursday',
					'Friday',
					'Saturday'
				];

                var weekday = [
					'星期日',
					'星期一',
					'星期二',
					'星期三',
					'星期四',
					'星期五',
					'星期六'
				];

                // Array for month.
                var month_en_US = [
					'January',
					'February',
					'March',
					'April',
					'May',
					'June',
					'July',
					'August',
					'September',
					'October',
					'November',
					'December'
				];

                var month = [
					'一月',
					'二月',
					'三月',
					'四月',
					'五月',
					'六月',
					'七月',
					'八月',
					'九月',
					'十月',
					'十一月',
					'十二月'
				];

                // Assign weekday, month, date, year.
                weekday = weekday[date_obj.getDay()];
                month = month[date_obj.getMonth()];

                // AM or PM?
                if (hour >= 12) {
                    suffix = 'PM';
                }

                // Convert to 12-hour.
                if (hour > 12) {
                    hour = hour - 12;
                }
                else if (hour === 0) {
                    // Display 12:XX instead of 0:XX.
                    hour = 12;
                }

                // Leading zero, if needed.
                if (minute < 10) {
                    minute = '0' + minute;
                }

                // Build two HTML strings.
                var clock_time = weekday + ' ' + hour + ':' + minute + ' ' + suffix;
                var clock_date = month + ' ' + day + ', ' + year;

                // Shove in the HTML.
                $('#clock').html(clock_time).attr('title', clock_date);

                // Update every 60 seconds.
                setTimeout(GCMS.init.clock, 60000);
            }
    }
};
// Pass in jQuery.
})(jQuery, this);