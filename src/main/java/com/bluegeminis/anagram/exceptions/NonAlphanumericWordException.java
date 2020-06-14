package com.bluegeminis.anagram.exceptions;

public class NonAlphanumericWordException extends AnagramApplicationException {

	private static final long serialVersionUID = 1L;

	public NonAlphanumericWordException(String candidateWord) {
		super(candidateWord + " is an invalid anagram validation candidate. Candidates must contain only alphanumerical characters");
	}
}
