package aval.ua.conference.dao;

import aval.ua.conference.domain.entity.Conference;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConferenceRepository extends CrudRepository<Conference, Long > {
    List<Conference> findAll();
}
