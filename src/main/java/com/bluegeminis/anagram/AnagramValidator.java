package com.bluegeminis.anagram;

import java.util.Arrays;

import com.bluegeminis.anagram.exceptions.AnagramApplicationException;
import com.bluegeminis.anagram.exceptions.NonAlphanumericWordException;
import com.bluegeminis.anagram.exceptions.MissingWordException;

public class AnagramValidator {

	public boolean isAnagramPair(String word1, String word2) throws AnagramApplicationException {

		boolean isAnagramPair = false;

		if (word1 == null || word2 == null) {
			throw new MissingWordException();
		}

		if (!AnagramUtils.isAlphanumeric(word1)) {
			throw new NonAlphanumericWordException(word1);
		}
		if (!AnagramUtils.isAlphanumeric(word2)) {
			throw new NonAlphanumericWordException(word2);
		}

		char[] characters1 = word1.toLowerCase().toCharArray();
		char[] characters2 = word2.toLowerCase().toCharArray();
		Arrays.sort(characters1);
		Arrays.sort(characters2);

		isAnagramPair = Arrays.equals(characters1, characters2);

		return isAnagramPair;
	}

}
