package aval.ua.conference.dao;

import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TalkRepository extends CrudRepository<Talk, Long > {

    Optional<Talk> findTalkByName(String part) ;
    Optional<Talk> findByName(String name);
}
