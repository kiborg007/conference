package aval.ua.conference.dao;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConferenceRepositoryTest extends AbstractDaoTest<ConferenceRepository>{
    @Test
    public void ifThereIsNoConferenceWithSuchNameEmptyOptionalIsReturned() {
        assertThat(dao.findById(null), is(equalTo(Optional.empty())));
    }
}
