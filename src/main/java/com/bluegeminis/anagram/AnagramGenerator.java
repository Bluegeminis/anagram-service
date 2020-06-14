package com.bluegeminis.anagram;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.bluegeminis.anagram.exceptions.AnagramApplicationException;
import com.bluegeminis.anagram.exceptions.MissingWordException;
import com.bluegeminis.anagram.exceptions.NonAlphanumericWordException;

public class AnagramGenerator {

	private void generateAnagramsOf(char[] wordCharacters, int startIndex, Set<String> anagramsAccumulator) {

		if (startIndex == wordCharacters.length - 1) {
			anagramsAccumulator.add(new String(wordCharacters));
		} else {
			for (int j = startIndex; j < wordCharacters.length; j++) {

				char temp = wordCharacters[startIndex];
				wordCharacters[startIndex] = wordCharacters[j];
				wordCharacters[j] = temp;
				generateAnagramsOf(wordCharacters, startIndex + 1, anagramsAccumulator);

				temp = wordCharacters[startIndex];
				wordCharacters[startIndex] = wordCharacters[j];
				wordCharacters[j] = temp;
			}
		}
	}

	public String[] generateAnagramsOf(String word) throws AnagramApplicationException {

		if (word == null) {
			throw new MissingWordException();
		}

		if (!AnagramUtils.isAlphanumeric(word)) {
			throw new NonAlphanumericWordException(word);
		}

		Set<String> anagrams = new HashSet<>();
		generateAnagramsOf(word.toLowerCase().toCharArray(), 0, anagrams);

		return StringUtils.toStringArray(anagrams);
	}

}
