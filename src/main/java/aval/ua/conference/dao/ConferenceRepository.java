package aval.ua.conference.dao;

import aval.ua.conference.domain.entity.Conference;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConferenceRepository extends CrudRepository<Conference, Long > {
    @Override
    List<Conference> findAll();

    Optional<Conference> findById(Long id);

    Optional<Conference> findByName(String name);
}
