package aval.ua.conference.web;

import aval.ua.conference.api.ConferenceController;
import aval.ua.conference.api.dto.ConferenceResponse;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.config.MicrometerManager;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.domain.mapper.MapperDTO;
import aval.ua.conference.service.ConferenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import java.time.LocalDate;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ConferenceController.class)
public class ConferenceControllerTest {
    @MockBean
    private ConferenceService conferenceService;
    @MockBean
    private MapperDTO mapper;
    @MockBean
    private MicrometerManager micrometerManager;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;

    private Talk talk;
    private TalkRequest talkRequest;
    private Conference conference;
    private ConferenceResponse conferenceRequest;

    @Before
    public void init() {
        talk = new Talk();
        talk.setId(1L);
        talk.setName("Talk 1");
        talk.setType("");
        talk.setDesc("Desc Talk 1");
        talk.setPerson("1");

        talkRequest = new TalkRequest();
        talkRequest.setName("Talk 1");
        talkRequest.setType("");
        talkRequest.setDesc("Desc Talk 1");
        talkRequest.setPerson("1");

        conference = new Conference();
        conference.setId(2L);
        conference.setName("name");
        conference.setDate(LocalDate.of(2020, 10, 10));
        conference.setTalks(asList(talk));

        conferenceRequest = new ConferenceResponse();
        conferenceRequest.setId(2L);
        conferenceRequest.setName("name");
        conferenceRequest.setDate(LocalDate.of(2020, 10, 10));
    }

    @Test
    public void shouldBeOkAddTalk() throws Exception {
        String body = om.writeValueAsString(talkRequest);
        given(conferenceService.addTalk(2L, talk)).willReturn(conference);
        mockMvc.perform(post("/conferences/2/talks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void getConference() throws Exception {
        conferenceService.addConference(conference);
        given(conferenceService.getAll()).willReturn(Collections.singletonList(conference));
        mockMvc.perform(get("/conferences")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }
}

