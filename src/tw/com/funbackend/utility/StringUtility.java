package tw.com.funbackend.utility;

public class StringUtility {

	static public boolean isNotEmpty (String value) {
		
		if(value == null || "".equals(value))
		{
			return false;
		}
		
		return true;
		
	}
}
