package aval.ua.conference.dao;

import aval.ua.conference.domain.entity.Conference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ConferenceRepositoryTest extends AbstractDaoTest<ConferenceRepository>{
    @Autowired
    private ConferenceRepository cr;

    @Before
    public void init() {
        Conference c = new Conference();
        c.setId(1l);
        c.setName("test");
        cr.save(c);
    }

    @Test
    public void ifThereConferenceWithNameTest() {
        assertThat(cr.findById(1L).get().getName()).isEqualTo("test");
    }

    @Test
    public void ifThereConferenceWithIdTest() {
        assertThat(cr.findByName("test").get().getId()).isEqualTo(1L);
    }

    @Test
    public void ifThereConferencesListSize() {
        assertThat(cr.findAll().size()).isEqualTo(1);
    }
}
