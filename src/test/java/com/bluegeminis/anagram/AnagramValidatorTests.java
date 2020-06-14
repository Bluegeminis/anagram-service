package com.bluegeminis.anagram;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bluegeminis.anagram.exceptions.AnagramApplicationException;
import com.bluegeminis.anagram.exceptions.NonAlphanumericWordException;
import com.bluegeminis.anagram.exceptions.MissingWordException;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnagramValidatorTests {

	@Autowired
	private AnagramValidator anagramValidator;

	@Configuration
	static class ContextConfiguration {

		@Bean
		public AnagramValidator anagramValidator() {
			return new AnagramValidator();
		}
	}

	@Test
	public void testPositiveAnagramDetection() throws AnagramApplicationException {
		Assert.assertTrue(anagramValidator.isAnagramPair("cat", "tac"));
	}

	@Test
	public void testNegativeAnagramDetection() throws AnagramApplicationException {
		Assert.assertFalse(anagramValidator.isAnagramPair("cat", "dog"));
	}

	@Test(expected = NonAlphanumericWordException.class)
	public void testNonAlphanumericStringDetection() throws AnagramApplicationException {
		Assert.assertFalse(anagramValidator.isAnagramPair("!#$", "dog"));
	}

	@Test(expected = MissingWordException.class)
	public void testMissingStringDetection() throws AnagramApplicationException {
		Assert.assertFalse(anagramValidator.isAnagramPair(null, "dog"));
	}
}
