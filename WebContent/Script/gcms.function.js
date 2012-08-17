var jsonDate2Format = function (srcDate, mask) {
	var dateObj = new Date(srcDate);
	return dateObj.toLocaleString();
};