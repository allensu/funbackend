var jsonDate2Format = function (srcDate, mask) {
    var dateObj = eval(srcDate.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"));
    return dateObj.format(mask)
};