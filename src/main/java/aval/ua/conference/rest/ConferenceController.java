package aval.ua.conference.rest;

import aval.ua.conference.domain.Conference;
import aval.ua.conference.domain.dto.ConfRequest;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService ;

    @GetMapping
    public List<Conference> getConferences() {
        return conferenceService.getAll();
    }

    @PostMapping(path = "/conference", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Conference addConference(@RequestBody ConfRequest request){
        return conferenceService.addConference(request) ;
    }
}
