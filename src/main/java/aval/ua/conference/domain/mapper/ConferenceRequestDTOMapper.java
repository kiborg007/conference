package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.ConferenceDTO;
import aval.ua.conference.domain.entity.Conference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConferenceRequestDTOMapper {
    ConferenceDTO mapToConferenceDTO(Conference conference);
}
