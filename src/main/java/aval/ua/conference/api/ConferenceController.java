package aval.ua.conference.api;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.mapper.ConferenceRequestDTOMapper;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService ;
    //private final ConferenceRequestDTOMapper conferenceRequestDTOMapper;

    @GetMapping
    public List<ConferenceRequest> getConferences() {
        List<ConferenceRequest> result = new ArrayList();
      //  conferenceService.getAll().forEach(conference -> result.add(conferenceRequestDTOMapper.mapToConferenceDTO(conference)));
        return result;
    }

//    @PostMapping(path = "/conference", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    Conference addConference(@RequestBody ConfRequest request){
//        return conferenceService.addConference(request) ;
//    }

}
