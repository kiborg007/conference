package aval.ua.conference.api;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.mapper.ConfMapper;
import aval.ua.conference.domain.mapper.ConferenceMapper;
import aval.ua.conference.domain.mapper.TalkMapper;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conferences")
public class ConferenceController {
    private final ConferenceService conferenceService ;
    private final ConfMapper conferenceMapper;
    private final TalkMapper talkMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConferenceRequest> getConferences() {
        return conferenceMapper.mapToConferenceRequestList(conferenceService.getAll());
    }

    @GetMapping("/{conference_id}")
    @ResponseStatus(HttpStatus.OK)
    public ConferenceRequest getConference(@PathVariable long id) {
        return conferenceMapper.mapToConferenceRequest(conferenceService.getConference(id));
    }

    @PostMapping("/{conference_id}/talks")
    @ResponseStatus(HttpStatus.OK)
    public List<ConferenceRequest> addTalk(@PathVariable long id, @Validated @RequestBody TalkRequest talkRequest) {
        return conferenceMapper.mapToConferenceRequestList(conferenceService.addTalk(id, talkMapper.mapToTalk(talkRequest)));
    }

//    @PostMapping(path = "/conference", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    Conference addConference(@RequestBody ConfRequest request){
//        return conferenceService.addConference(request) ;
//    }

}
