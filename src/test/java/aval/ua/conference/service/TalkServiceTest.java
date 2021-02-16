package aval.ua.conference.service;

import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.TalkAlreadyExistException;
import aval.ua.conference.service.impl.TalkServiceImpl;
import org.assertj.core.api.Condition;
import org.assertj.core.description.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.notNull;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TalkServiceTest {

    @Mock
    private TalkRepository dao;
    @InjectMocks
    private TalkServiceImpl talkService;

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

        assertThat(talkService.findTalksByDesc(desc).size()).isEqualTo(0);
    }

    @Test
    public void createTalkIsNotUnique(){
        Talk talkrequest  = createTalk() ;
        Talk talk  = new Talk() ;
        talk.setName("name");
        Mockito.when(dao.findByName(talkrequest.getName())).thenReturn(Optional.of(talk)) ;
        assertThrows(TalkAlreadyExistException.class, () -> {
            talkService.createTalk(talkrequest);
        });


    }

    private Talk createTalk(){
        Talk talk = new Talk() ;
        talk.setName("name");
        talk.setType("type");
        talk.setPerson("person");
        talk.setDesc("desc");
        return talk ;
    }
}
