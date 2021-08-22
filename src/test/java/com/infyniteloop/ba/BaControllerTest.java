package com.infyniteloop.ba;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.infyniteloop.ba.controller.BaController;
import com.infyniteloop.ba.model.BreathAnalyzer;
import com.infyniteloop.ba.service.BaService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BaController.class)
public class BaControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BaService baService;
	
	BreathAnalyzer breathAnalyzer = new BreathAnalyzer();
	
	
	@Test
	public void retrieveDetailsForCourse() throws Exception {
		
		breathAnalyzer.setCrewId("RTM1001");
		breathAnalyzer.setValue(0);
		breathAnalyzer.setLocation("RTM");
		
		
		Mockito.when(
				baService.getBa(Mockito.anyLong())).thenReturn(Optional.of(breathAnalyzer));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1/ba/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(">>>>>>>>>>>>>>>> " + result.getResponse());
		String expected = "{\"crewId\": \"RTM1002\",\"value\": 12,\"location\": \"RTM\",}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		
		
	}
	
	
	
	
}
