package aval.ua.conference.service.impl;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository ;

    @Override
    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    };

    @Override
    public Conference addConference(ConferenceRequest request) {
        Conference conf = new Conference() ;

        conf.setName(request.getName()) ;
        conf.setTheme(request.getTheme());
        conf.setDate(request.getDate());
        conf.setPrtspscount(request.getPrtspscount());
        conf.setTalkid(request.getTalkid());
        conferenceRepository.save(conf) ;
        return conf;
    }
}
