package com.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashKeyUtil {
	public static String hashSecretKey(String secretKey) {
		try {
			// Create a MessageDigest instance for SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Update the digest with the secret key bytes
			byte[] hashedBytes = digest.digest(secretKey.getBytes());

			// Convert the byte array to a hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte hashedByte : hashedBytes) {
				String hex = Integer.toHexString(0xff & hashedByte);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// Handle the exception (e.g., log or throw a custom exception)
			e.printStackTrace();
			return null; // or throw an exception
		}
	}
	// watched from youtube
	public static String reverseHexToString(String hexString) {
	    int length = hexString.length();
	    byte[] bytes = new byte[length / 2];
	    for (int i = 0; i < length; i += 2) {
	        bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
	                + Character.digit(hexString.charAt(i + 1), 16));
	    }
	    return new String(bytes);
	}


	public static boolean verifySecretKey(String secretKey, String hashedKey) {
		String hashedInputKey = hashSecretKey(secretKey);
		return hashedInputKey.equals(hashedKey);
	}
}
