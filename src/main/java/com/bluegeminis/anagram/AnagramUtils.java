package com.bluegeminis.anagram;

public class AnagramUtils {

	private AnagramUtils() {};

	public static boolean isAlphanumeric(String word) {

		boolean alphanumeric = false;
		if (word != null) {
			alphanumeric = word.matches("[a-zA-Z0-9]*");
		}
		return alphanumeric;
	}

}
