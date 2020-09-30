package aval.ua.conference.api;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.mapper.ConfMapper;
import aval.ua.conference.domain.mapper.ConferenceMapper;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/conferences")
public class ConferenceController {
    @Autowired
    ConferenceService conferenceService ;
    @Autowired
    ConfMapper conferenceMapper;

    @GetMapping
    public List<ConferenceRequest> getConferences() {
        System.out.println("getConferences");
        List<Conference> all = conferenceService.getAll();
        System.out.println("all" + all);
        return conferenceMapper.mapToConferenceRequestList(all);
    }

//    @PostMapping(path = "/conference", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    Conference addConference(@RequestBody ConfRequest request){
//        return conferenceService.addConference(request) ;
//    }

}
