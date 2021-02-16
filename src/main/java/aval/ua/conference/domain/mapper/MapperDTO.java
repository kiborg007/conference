package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.ConferenceResponse;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.api.dto.TalkResponse;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperDTO {
    @Mappings(value = {
            @Mapping(target = "members", source = "prtspscount")
    })
    ConferenceResponse mapToDTO(Conference conference);

    @Mappings(value = {
            @Mapping(target = "prtspscount", source = "members")
    })
    Conference mapFromDTO(ConferenceRequest conferenceRequest);

    TalkResponse mapToDTO(Talk talk);

    Talk mapFromDTO(TalkRequest TalkRequest);
}
