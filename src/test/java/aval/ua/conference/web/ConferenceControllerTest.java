package aval.ua.conference.web;

import aval.ua.conference.api.dto.ConferenceResponse;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.service.ConferenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static java.util.stream.Collectors.joining;

import java.sql.Date;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(MockitoJUnitRunner.class)
public class ConferenceControllerTest {
    @Mock
    private ConferenceService conferenceService;
//    @Mock
//    private TlkMapper tlkMapper;
//    @Mock
//    private ConfMapper conferenceMapper;

    private MockMvc mockMvc;

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
      //  talkRequest.setId(1L);
        talkRequest.setName("Talk 1");
        talkRequest.setType("");
        talkRequest.setDesc("Desc Talk 1");
        talkRequest.setPerson("1");

        conference = new Conference();
        conference.setId(2L);
        conference.setDate(new Date(2020, 10, 10));
        conference.setTalks(asList(talk));

        conferenceRequest = new ConferenceResponse();
        conferenceRequest.setId(2L);
        conferenceRequest.setDate(new Date(2020, 10, 10));
//        conferenceRequest.setTalks_lst(asList(talkRequest));
//
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(new ConferenceController(conferenceService, conferenceMapper, talkMapper))
//                .build();
    }

    @Test
    public void shouldBeReturnedAddTalk() throws Exception {
       // when(tlkMapper.mapToTalk(talkRequest)).thenReturn(talk);
        when(conferenceService.addTalk(2L, talk)).thenReturn(conference);
        //when(conferenceMapper.mapToConferenceRequest(conference)).thenReturn(conferenceRequest);

        doPost("/conferences/talks?conference_id=2",
                "\"id\": 1",
                "\"type\": \"\"",
                "\"name\": \"Talk 1\"",
                "\"desc\": \"Desc Talk 1\"",
                "\"person\": \"1\"")
                .andExpect(status().isOk())
                .andDo(print());
               // .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
               // .andExpect(jsonPath("$[0].name").value("Talk 1"));
    }

    private ResultActions doPost(String path, String... lines) throws Exception {
        return mockMvc.perform(post(path)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(Stream.of(lines)
                        .collect(joining(",\n", "{\n", "\n}"))));
    }

    @Test
    public void shouldBeReturnedTalksOfConferenceByID() throws Exception {
        when(conferenceService.getConference(2L)).thenReturn(conference);
      //  when(tlkMapper.mapToTalkRequestList(asList(talk))).thenReturn(asList(talkRequest));
        mockMvc.perform(get("/conferences/talks?conference_id=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("Talk 1"));
    }


}

