package aval.ua.conference.service.impl;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.service.ConferenceService;
import aval.ua.conference.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final TalkService talkService;

    @Override
    public List<Conference> getAll() { return conferenceRepository.findAll(); };

    @Override
    public Conference getConference(long conf_id) {
        return conferenceRepository.findById(conf_id).get();
    }

    @Override
    public List<Conference> addTalk(long conf_id, Talk talk) {
        Talk talk_new = talkService.createTalk(talk);
        Conference conference = conferenceRepository.findById(conf_id).get();
        conference.getTalks().add(talk_new);
        conferenceRepository.save(conference);
        return null;
    }

    @Override
    public Conference addConference(ConferenceRequest request) {
        Conference conf = new Conference() ;
        conf.setName(request.getName()) ;
        conf.setTheme(request.getTheme());
        conf.setDate(request.getDate());
        conf.setPrtspscount(request.getPrtspscount());
        conferenceRepository.save(conf) ;
        return conf;
    }
}
