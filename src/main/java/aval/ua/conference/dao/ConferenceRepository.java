package aval.ua.conference.dao;

import aval.ua.conference.domain.Conference;
import org.springframework.data.repository.CrudRepository;

public interface ConferenceRepository extends CrudRepository<Conference, Long > {
}
