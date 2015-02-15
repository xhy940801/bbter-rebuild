package net._100steps.general.util;

import java.util.regex.Pattern;

public class StringVerify
{
	static private Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	
	static public boolean isEmail(String email, int maxLength)
	{
		if(email.length() > maxLength)
			return false;
		return emailPattern.matcher(email).matches();
	}
	
	static public boolean isStrongPassword(String password, int minLength, int maxLength)
	{
		return password.length() >= minLength && password.length() <= maxLength;
	}
	
	static public boolean checkLength(String str, int minLength, int maxLength)
	{
		return str.length() >= minLength && str.length() <= maxLength;
	}
}
