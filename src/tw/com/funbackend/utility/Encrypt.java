package tw.com.funbackend.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encrypt {
	
	public static String encodePassword(String data) {
		String keyword = "funbackend";
		StringBuilder hexString = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte in[] = md.digest((data + keyword).getBytes());
			for (int j = 0; j < in.length; j++) {
				String hex = Integer.toHexString(0xFF & in[j]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}
}
