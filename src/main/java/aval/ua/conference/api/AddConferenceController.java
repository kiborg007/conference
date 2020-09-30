package aval.ua.conference.api;

import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddConferenceController {

    private final ConferenceService service ;
    @PostMapping(path = "/conferences", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Conference addConference(@RequestBody ConferenceRequest request){
        return service.addConference(request) ;
    }
}

