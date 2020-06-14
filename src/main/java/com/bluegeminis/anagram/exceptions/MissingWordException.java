package com.bluegeminis.anagram.exceptions;

public class MissingWordException extends AnagramApplicationException {

	private static final long serialVersionUID = 1L;

	public MissingWordException() {
		super("Anagram validation requires at least 2 words to compare");
	}
}
