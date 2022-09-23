package com.totnesjava.bdaypresents.bdd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotEmpty;

public class GuidUtils {

	public static boolean isValidGUID(@NotEmpty String guid) {
		
		// Regex to check valid GUID (Globally Unique Identifier)
		String regex = "^[{]?[0-9a-fA-F]{8}" + "-([0-9a-fA-F]{4}-)" + "{3}[0-9a-fA-F]{12}[}]?$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(guid);

		return m.matches();
		
	}

}
