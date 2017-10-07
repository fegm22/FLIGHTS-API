package com.tuenti.sports.mvp.basketball;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuenti.sports.mvp.basketball.action.MostValuablePlayerController;
import com.tuenti.sports.mvp.basketball.domain.BasketballMatch;
import com.tuenti.sports.mvp.basketball.domain.BasketballPlayer;
import com.tuenti.sports.mvp.basketball.service.BasketballService;
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
	private BasketballService basketballService;


	@Test
	public void basketballServiceShouldReturnAPlayer() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("input.json");
		InputStream output = classloader.getResourceAsStream("reponse.json");

		BasketballMatch basketballMatch = mapper.readValue(input, BasketballMatch.class);
		BasketballPlayer basketballPlayer = mapper.readValue(output, BasketballPlayer.class);

		when(basketballService.getMostValuablePlayerBasketball(basketballMatch))
				.thenReturn(basketballPlayer);

		this.mockMvc.perform(
				post("/mostValuablePlayerBasketball")
					.contentType("application/json;charset=UTF-8")
					.content(mapper.writeValueAsString(basketballMatch) ))
				.andExpect(status().isOk())
				.andExpect(jsonPath("playerName", is(basketballPlayer.getPlayerName()) ));
		;

		verify(basketballService, times(1)).getMostValuablePlayerBasketball(basketballMatch);
		verifyNoMoreInteractions(basketballService);
	}


}
