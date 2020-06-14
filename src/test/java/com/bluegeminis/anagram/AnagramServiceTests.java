package com.bluegeminis.anagram;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AnagramServiceTests {

	@Configuration
	@ComponentScan("com.bluegeminis.anagram.rest.controllers")
	static class ContextConfiguration {

		@Bean
		public AnagramValidator anagramValidator() {
			return new AnagramValidator();
		}

		@Bean
		public AnagramGenerator anagramGenerator() {
			return new AnagramGenerator();
		}
	}

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPositiveAnagramDetectionResponseStatus() throws Exception {
		mockMvc.perform(get("/anagrams/cat/tac")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testPositiveAnagramDetectionResponseBody() throws Exception {
		mockMvc.perform(get("/anagrams/cat/tac")).andExpect(content().json("{\"areAnagrams\": true}"));
	}

	@Test
	public void testNegativeAnagramDetectionResponseStatus() throws Exception {
		mockMvc.perform(get("/anagrams/cat/dog")).andExpect(status().is2xxSuccessful()).andReturn();
	}

	@Test
	public void testNegativeAnagramDetectionResponseBody() throws Exception {
		mockMvc.perform(get("/anagrams/cat/dog")).andExpect(content().json("{\"areAnagrams\": false}"));
	}

	@Test
	public void testNonAlphanumericFirstWordDetection() throws Exception {
		mockMvc.perform(get("/anagrams/c@t/dog")).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void testNonAlphanumericSecondWordDetection() throws Exception {
		mockMvc.perform(get("/anagrams/cat/dog$")).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void testAnagramGeneration() throws Exception {
		mockMvc.perform(get("/anagrams/cat")).andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"anagrams\":[\"cta\",\"atc\",\"act\",\"tca\",\"cat\",\"tac\"]}."));
	}

	@Test
	public void testNonAlphanumericInputToAnagramGeneration() throws Exception {
		mockMvc.perform(get("/anagrams/c@t")).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

}
