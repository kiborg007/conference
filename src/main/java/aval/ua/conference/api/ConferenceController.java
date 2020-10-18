package aval.ua.conference.api;

import aval.ua.conference.api.dto.*;
import aval.ua.conference.domain.mapper.MapperDTO;
import aval.ua.conference.exception.InvalidConferenceException;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameException;
import aval.ua.conference.exception.InvalidTimeRegistrationException;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("conferences")
@RequiredArgsConstructor
public class ConferenceController {
    private final ConferenceService conferenceService ;
    private final MapperDTO mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConferenceResponse> conferences() {
        System.out.println("### RestController conferences");
        List<ConferenceResponse> result = new ArrayList();
        conferenceService.getAll().forEach(conference -> result.add(mapper.mapToDTO(conference)));
        return result;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public long addConference(@RequestBody ConferenceRequest request){
        System.out.println("### RestController addConference: " + request);
        return conferenceService.addConference(mapper.mapFromDTO(request)).getId();
    }


    @GetMapping(path ="/{conference_id}/talks")
    @ResponseStatus(HttpStatus.OK)
    public List<TalkResponse> talks(@PathVariable(value = "conference_id") String conference_id) {
        System.out.println("### RestController getConference"+ conference_id);
        List<TalkResponse> result = new ArrayList();
        conferenceService.getConference(Long.parseLong(conference_id)).getTalks().forEach(talk -> result.add(mapper.mapToDTO(talk)));
        return result;
    }

    @PostMapping(path = "/{conference_id}/talks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ConferenceResponse addTalk(@PathVariable(value = "conference_id") long conference_id, @RequestBody TalkRequest talkRequest) throws IOException {
        System.out.println("### RestController addTalk conference_id:"+conference_id);
        return mapper.mapToDTO(conferenceService.addTalk(conference_id, mapper.mapFromDTO(talkRequest)));
    }

    @ExceptionHandler(InvalidException.class)
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Talk")
    ErrorResponse onSaveError(Exception e) {

        return new ErrorResponse("400", e.toString());
    }

    @ExceptionHandler(InvalidNameException.class)
    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Bad Talk name")
    ErrorResponse onAddError(Exception e) {

        return new ErrorResponse("409", e.toString());
    }

    @ExceptionHandler(InvalidConferenceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Bad conference name")
    ErrorResponse onAddConferenceError(Exception e){

        return  new ErrorResponse("409", e.toString()) ;
    }

    @ExceptionHandler(InvalidTimeRegistrationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Registration time is over")
    ErrorResponse onAddTalkError(Exception e){

        return  new ErrorResponse("409", e.toString()) ;
    }

}
