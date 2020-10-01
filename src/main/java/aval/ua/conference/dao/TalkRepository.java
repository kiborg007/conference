package aval.ua.conference.dao;


import aval.ua.conference.domain.entity.Talk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TalkRepository extends CrudRepository<Talk, Long > {
    @Override
    List<Talk> findAll();
    Optional<Talk> findById(Long id);
}
