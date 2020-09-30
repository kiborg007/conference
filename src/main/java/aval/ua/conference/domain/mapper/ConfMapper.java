package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.entity.Conference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfMapper {
    TalkMapper talkMapper;

    public List<ConferenceRequest> mapToConferenceRequestList(List<Conference> conferences){
        List<ConferenceRequest> result = new ArrayList();
        conferences.forEach(conference -> result.add(mapToConferenceRequest(conference)));
        return result;
    }

    public ConferenceRequest mapToConferenceRequest(Conference conference){
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        conferenceRequest.setName(conference.getName());
        conferenceRequest.setTheme(conference.getTheme());
        conferenceRequest.setDate(conference.getDate());
        conferenceRequest.setPrtspscount(conference.getPrtspscount());
        conferenceRequest.setTalks_lst(talkMapper.mapToTalkRequestList(conference.getTalks()));
        return conferenceRequest;
    }
}
