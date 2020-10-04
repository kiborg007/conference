package aval.ua.conference.api;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.ErrorRequest;
import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.mapper.ConfMapper;
import aval.ua.conference.domain.mapper.TalkMapper;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameExcrption;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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
    public List<ConferenceRequest> conferences() {
        System.out.println("### RestController getConferences");
        return conferenceMapper.mapToConferenceRequestList(conferenceService.getAll());
    }

    @GetMapping(path ="/talks{conference_id}")
    @ResponseStatus(HttpStatus.OK)
    public ConferenceRequest conferenceById(@PathParam(value = "conference_id") long conference_id) {
        System.out.println("### RestController getConference"+ conference_id);
        return conferenceMapper.mapToConferenceRequest(conferenceService.getConference(conference_id));
    }

    @PostMapping(path = "/talks{conference_id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ConferenceRequest addTalk(@PathParam(value = "conference_id") long conference_id, @RequestBody TalkRequest talkRequest) {
        System.out.println("### RestController addTalk"+conference_id);
        return conferenceMapper.mapToConferenceRequest(conferenceService.addTalk(conference_id, talkMapper.mapToTalk(talkRequest)));
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Talk")
    ErrorRequest onSaveError(Exception e) {
        return new ErrorRequest("400", e.toString());
    }

    @ExceptionHandler(InvalidNameExcrption.class)
    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Bad Talk name")
    ErrorRequest onAddError(Exception e) {
        return new ErrorRequest("409", e.toString());
    }

//    @PostMapping(path = "/conference", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    Conference addConference(@RequestBody ConfRequest request){
//        return conferenceService.addConference(request) ;
//    }

}
