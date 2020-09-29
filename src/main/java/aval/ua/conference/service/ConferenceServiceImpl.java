package aval.ua.conference.service;

import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.Conference;
import aval.ua.conference.domain.dto.ConfRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService{
    private final ConferenceRepository repository ;

    @Override
    public Conference addConference(ConfRequest request) {
        Conference conf = new Conference() ;

        conf.setName(request.getName()) ;
        conf.setThema(request.getThema());
        conf.setDate(request.getDate());
        conf.setPrtspscount(request.getPrtspscount());
        repository.save(conf) ;
        return conf;
    }
}
