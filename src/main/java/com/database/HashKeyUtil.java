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

	public static boolean verifySecretKey(String secretKey, String hashedKey) {
		String hashedInputKey = hashSecretKey(secretKey);
		return hashedInputKey.equals(hashedKey);
	}
}
