var jsonDate2Format = function (srcDate, mask) {
	var dateObj = new Date(srcDate);
	var daten = new Date(dateObj.toLocaleString());
	return daten.format(mask);
};

function padDigit(number) 
{
    return (number < 10 ? '0' : '') + number
}

function padMili(number)
{
    if(number<10)
    {
        return "00"+number;
    }
    else if(number >=10 && number<100)
    {
        return "0"+number;
    }
    else
    {
        return number;
    }
}