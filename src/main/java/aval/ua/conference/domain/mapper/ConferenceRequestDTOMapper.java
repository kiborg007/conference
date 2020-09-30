package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.entity.Conference;
import org.mapstruct.Mapper;

@Mapper
public interface ConferenceRequestDTOMapper {
    ConferenceRequest mapToConferenceDTO(Conference conference);
}
