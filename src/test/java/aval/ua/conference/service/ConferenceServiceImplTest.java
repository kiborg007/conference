package aval.ua.conference.service;

import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameException;
import aval.ua.conference.service.impl.ConferenceServiceImpl;
import aval.ua.conference.service.impl.TalkServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceServiceImplTest {
    @Mock
    private TalkService talkService;
    @Mock
    private ConferenceRepository conferenceRepository;

    private ConferenceService conferenceService;

    @Before
    public void init() {
        conferenceService = new ConferenceServiceImpl(conferenceRepository,talkService);
    }

    @Test
    public void ifNoConferencesPassedEmptyListIsReturned() {
        assertThat(conferenceService.getAll(), is(empty()));
    }

    @Test
    public void ifTrueRegistrationTime() {
        Talk talk = new Talk();
        Conference conference = new Conference();
        conference.setId(1L);
        Date currentDate = new Date(System.currentTimeMillis());
        int year = currentDate.getDay();
        conference.setDate(new Date(year+1,10,20));
        when(conferenceRepository.findById(1L)).thenReturn(java.util.Optional.of(conference));
        assertThat(conferenceService.addTalk(1L, talk), is(conference));
    }

    @Test (expected = InvalidException.class)
    public void ifFalseRegistrationTime() {
        InvalidException  invalidException= new InvalidException("Registration time is over");
        Talk talk = new Talk();
        Conference conference = new Conference();
        conference.setId(1L);
        Date currentDate = new Date(System.currentTimeMillis());
        int year = currentDate.getDay();
        conference.setDate(new Date(year-1,10,20));
        when(conferenceRepository.findById(1L)).thenReturn(java.util.Optional.of(conference));
        assertThat(conferenceService.addTalk(1L, talk), is(invalidException));
        assertThat(invalidException.getMessage(), is("Registration time is over"));
    }


}
