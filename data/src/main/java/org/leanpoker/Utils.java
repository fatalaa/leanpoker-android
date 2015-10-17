package org.leanpoker;

/**
 * Created by tmolnar on 17/09/15.
 */
public class Utils {
	/**
	 * This method splits a given String by a '=' character and returns the second part
	 *
	 * @param keyValueAsString The given key-value pair as a {@link java.lang.String}
	 */
	public static String getValueFromKeyValuePair(String keyValueAsString) {
		String value = null;
		if (keyValueAsString.length() >= 2) {
			String[] parts = keyValueAsString.split("=");
			if (parts.length >= 2) {
				value = parts[1];
			}
		}
		return value;
	}
}
