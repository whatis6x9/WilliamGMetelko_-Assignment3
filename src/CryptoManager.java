
/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
		for(int i = 0; i < plainText.length(); i++) {
			if((int)(plainText.charAt(i)) < LOWER_RANGE || (int)(plainText.charAt(i)) > UPPER_RANGE) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		if(isStringInBounds(plainText)) {
			String toReturn = "";
			int offset;
			
			//System.out.println(plainText + ": ");
			
			for(int i = 0; i < plainText.length(); i++) {
				
				offset = key;
				
				while(plainText.toUpperCase().charAt(i) + offset > UPPER_RANGE) {
					offset -= RANGE;
				}
				
				//System.out.println(plainText.toUpperCase().charAt(i) + " | " + (int)(plainText.toUpperCase().charAt(i)) + " + " + offset + " = " + (int)(plainText.toUpperCase().charAt(i) + offset) + " = " + (char)(plainText.toUpperCase().charAt(i) + offset));
								
				toReturn += (char)(plainText.toUpperCase().charAt(i) + offset);
			}
			
			return toReturn;
		}else {
			return "The selected string is not in bounds, Try again.";
		}
		
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		String toReturn = "";
		char currentChar;
		int offset;
		
		// System.out.println(encryptedText + ": ");
		
		for(int i = 0; i < encryptedText.length(); i++) {
			
			offset = key;
			
			while(encryptedText.toUpperCase().charAt(i) - offset < LOWER_RANGE) {
				offset -= RANGE;
			}
			
			currentChar = (char)(encryptedText.toUpperCase().charAt(i) - offset);
			
			// System.out.println(encryptedText.toUpperCase().charAt(i) + " | " + (int)(encryptedText.toUpperCase().charAt(i)) + " - " + offset + " = " + (int)(encryptedText.toUpperCase().charAt(i) + offset) + " = " + currentChar);
							
			toReturn += currentChar;
		}
		
		//System.out.println(toReturn);
		
		return toReturn;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		String toReturn = "";
		String modKey = bellasoStr;
		char currPChar;
		char currBChar;
		int offset = 0;
		int keyIterator = 0;
		
		//System.out.println(plainText + ": ");
		
		while (modKey.length() < plainText.length()) {
			
			if(keyIterator == modKey.length()) {
				keyIterator=0;
			}
			modKey += modKey.charAt(keyIterator);
			
			keyIterator++;
		}
		
		for(int i = 0; i < plainText.length(); i++) {
			currPChar = plainText.charAt(i);
			currBChar = modKey.charAt(i);
			
			offset = (int)currBChar + (int)currPChar;
			
			while(offset > UPPER_RANGE) {
				offset -= RANGE;
			}
			
			//System.out.println(plainText.toUpperCase().charAt(i) + " | " + (int)currBChar + " + " +  (int)currPChar + " = " + (char)offset);
			
			toReturn += (char)(offset);
		}
		
		return toReturn;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		String toReturn = "";
		String modKey = bellasoStr;
		char currPChar;
		char currBChar;
		int offset = 0;
		int keyIterator = 0;
		
		//System.out.println(encryptedText + ": ");
		
		while (modKey.length() < encryptedText.length()) {
			
			if(keyIterator == modKey.length()) {
				keyIterator=0;
			}
			modKey += modKey.charAt(keyIterator);
			
			keyIterator++;
		}
		
		for(int i = 0; i < encryptedText.length(); i++) {
			currPChar = encryptedText.charAt(i);
			currBChar = modKey.charAt(i);
			
			offset = (int)currPChar - (int)currBChar;
			
			while(offset < LOWER_RANGE) {
				offset += RANGE;
			}
			
			toReturn += (char)(offset);
		}
		
		return toReturn;
	}
}
