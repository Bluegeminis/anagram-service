package com.bluegeminis.anagram.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bluegeminis.anagram.AnagramGenerator;
import com.bluegeminis.anagram.AnagramValidator;
import com.bluegeminis.anagram.exceptions.AnagramApplicationException;
import com.bluegeminis.anagram.rest.entities.AnagramValidationResultJson;
import com.bluegeminis.anagram.rest.entities.AnagramsResultJson;

@RestController()
public class AnagramService {

	@Autowired
	private AnagramValidator anagramValidator;

	@Autowired
	private AnagramGenerator anagramGenerator;

	@GetMapping(path = "/anagrams/{string1}/{string2}")
	public AnagramValidationResultJson validateAnagram(@PathVariable("string1") String word1,
			@PathVariable("string2") String word2) throws AnagramApplicationException {

		AnagramValidationResultJson result = new AnagramValidationResultJson();
		result.setAreAnagrams(anagramValidator.isAnagramPair(word1, word2));
		return result;
	}

	@GetMapping(path = "/anagrams/{string1}")
	public AnagramsResultJson getAnagrams(@PathVariable("string1") String word) throws AnagramApplicationException {

		AnagramsResultJson result = new AnagramsResultJson();
		result.setAnagrams(anagramGenerator.generateAnagramsOf(word));
		return result;
	}
}
