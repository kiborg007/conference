package aval.ua.conference.service;

import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidConferenceException;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameException;
import aval.ua.conference.exception.InvalidTimeRegistrationException;
import aval.ua.conference.service.impl.ConferenceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceImplTest {
    @Mock
    private TalkService talkService;
    @Mock
    private ConferenceRepository conferenceRepository;
    @InjectMocks
    private ConferenceServiceImpl conferenceService;

    @Test
    public void ifNoConferencesPassedEmptyListIsReturned() {
        Conference[] list = new Conference[0];
        when(conferenceRepository.findAll()).thenReturn(Arrays.asList(list));
        assertEquals(conferenceService.getAll(), Arrays.asList(list));
    }

    @Test
    public void ifTrueRegistrationTime() {
        Talk talk = new Talk();
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setDate(LocalDate.now().plusYears(1));
        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));
        assertEquals(conferenceService.addTalk(1L, talk), conference);
    }

    @Test
    public void ifFalseRegistrationTime() {
        Talk talk = new Talk();
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setDate(LocalDate.now().minusYears(1));
        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));
        assertThrows(InvalidTimeRegistrationException.class,() ->  conferenceService.addTalk(1L, talk));
    }

    @Test
    public void ifConferenceDidNotFind() {
        assertThrows(InvalidException.class,() ->  conferenceService.addTalk(1L, new Talk()));
    }

    @Test
    public void ifConferenceNameAlreadyExist() {
        Conference conference = new Conference();
        conference.setName("test");
        when(conferenceRepository.findByName("test")).thenReturn(Optional.of(conference));
        assertThrows(InvalidConferenceException.class,() ->  conferenceService.addConference(conference));
    }

    @Test
    public void ifConferenceHasMoreThan3TalksWithSameName() {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setDate(LocalDate.now().plusYears(1));
        Talk talk = new Talk();
        talk.setId(1L);
        talk.setName("test");
        conference.getTalks().add(talk);
        talk = new Talk();
        talk.setId(2L);
        talk.setName("test");
        conference.getTalks().add(talk);
        talk = new Talk();
        talk.setId(3L);
        talk.setName("test");
        conference.getTalks().add(talk);
        Talk talk_new = new Talk();
        talk_new.setId(4L);
        talk_new.setName("test");
        when(conferenceRepository.findById(1L)).thenReturn(Optional.of(conference));
        assertThrows(InvalidNameException.class,() ->  conferenceService.addTalk(1L, talk_new));
    }
}
