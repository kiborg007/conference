package aval.ua.conference.dao;

import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TalkRepository extends CrudRepository<Talk, Long > {

    List<Talk> findAll();
    Optional<Talk> findById(Long id);
    Optional<Talk> findTalkByName(String part) ;
    Optional<Talk> findByName(String name);
    List<Talk> findByDesc(String name);
}
