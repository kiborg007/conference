package aval.ua.conference.service;

import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.service.impl.TalkServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.notNull;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TalkServiceTest {

    @Mock
    private TalkRepository dao;

    private TalkService talkService;

    @Before
    public void init() {
        talkService = new TalkServiceImpl(dao);
    }

    @Test
    public void ifNoTalksFoundForDescReturnEmptyList() {
        when(dao.findByDesc("qwerty")).thenReturn(emptyList());

        assertNoTalksFound("qwerty");
        verify(dao, only()).findByDesc("qwerty");
    }

    private void assertNoTalksFound(String desc) {
        assertThat(talkService.findTalksByDesc(desc), is(empty()));
    }
}
