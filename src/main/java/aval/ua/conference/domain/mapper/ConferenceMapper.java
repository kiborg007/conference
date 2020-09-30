package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.entity.Conference;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ConferenceMapper {
    List<ConferenceRequest> mapToConferenceRequest(List<Conference> conferences);
}
