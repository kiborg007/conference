package aval.ua.conference.web;

import aval.ua.conference.api.ConferenceController;
import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.domain.mapper.ConfMapper;
import aval.ua.conference.domain.mapper.TalkMapper;
import aval.ua.conference.service.ConferenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.hamcrest.Matchers.*;

import javax.websocket.server.PathParam;
import java.sql.Date;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(MockitoJUnitRunner.class)
public class ConferenceControllerTest {
    @Mock
    private ConferenceService conferenceService;
    @Mock
    private ConfMapper conferenceMapper;
    @Mock
    private TalkMapper talkMapper;

    private MockMvc mockMvc;

    private Talk talk;
    private TalkRequest talkRequest;
    private Conference conference;
    private ConferenceRequest conferenceRequest;

    @Before
    public void init() {
        talk = new Talk();
        talk.setName("Talk 1");
        talk.setDesc("Desc Talk 1");

        talkRequest = new TalkRequest();
        talkRequest.setName("Talk 1");
        talkRequest.setDesc("Desc Talk 1");

        conference = new Conference();
        conference.setId(1L);
        conference.setDate(new Date(2020, 10, 10));
        conference.setTalks(asList(talk));

        conferenceRequest = new ConferenceRequest();
        conferenceRequest.setId(1L);
        conferenceRequest.setDate(new Date(2020, 10, 10));
        conferenceRequest.setTalks_lst(asList(talkRequest));

        mockMvc = MockMvcBuilders
                .standaloneSetup(new ConferenceController(conferenceService, conferenceMapper, talkMapper))
                .build();
    }

    @Test
    public void shouldBeReturnedTalksOfConferenceByID() throws Exception {
        when(conferenceService.getConference(1L)).thenReturn(conference);
        when(talkMapper.mapToTalkRequestList(asList(talk))).thenReturn(asList(talkRequest));
        mockMvc.perform(get("/conferences/talks?conference_id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("Talk 1"));
    }

}

