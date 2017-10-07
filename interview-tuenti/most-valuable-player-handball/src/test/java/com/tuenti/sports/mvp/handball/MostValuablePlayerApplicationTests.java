package com.tuenti.sports.mvp.Handball;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuenti.sports.mvp.handball.action.MostValuablePlayerController;
import com.tuenti.sports.mvp.handball.domain.HandballMatch;
import com.tuenti.sports.mvp.handball.domain.HandballPlayer;
import com.tuenti.sports.mvp.handball.service.HandballService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(MostValuablePlayerController.class)
public class MostValuablePlayerApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HandballService HandballService;


	@Test
	public void HandballServiceShouldReturnAPlayer() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("input.json");
		InputStream output = classloader.getResourceAsStream("reponse.json");

		HandballMatch HandballMatch = mapper.readValue(input, HandballMatch.class);
		HandballPlayer HandballPlayer = mapper.readValue(output, HandballPlayer.class);

		when(HandballService.getMostValuablePlayerHandball(HandballMatch))
				.thenReturn(HandballPlayer);

		this.mockMvc.perform(
				post("/mostValuablePlayerHandball")
						.contentType("application/json;charset=UTF-8")
						.content(mapper.writeValueAsString(HandballMatch) ))
				.andExpect(status().isOk())
		;

		verify(HandballService, times(1)).getMostValuablePlayerHandball(HandballMatch);
		verifyNoMoreInteractions(HandballService);
	}


}
