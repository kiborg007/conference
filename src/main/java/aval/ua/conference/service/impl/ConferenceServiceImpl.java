package aval.ua.conference.service.impl;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameExcrption;
import aval.ua.conference.service.ConferenceService;
import aval.ua.conference.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Calendar;
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
    public Conference getConference(Long conf_id) { return conferenceRepository.findById(conf_id).get(); }

    @Override
    public Conference addTalk(long conf_id, Talk talk) {
        Conference conference = conferenceRepository.findById(conf_id).get();
        if (overTime(conference.getDate())) {
            throw new InvalidException("Registration time is over");
        }
        if(isThreeTalks(conference, talk.getName())) {
            throw new InvalidNameExcrption("Name of talk is more than 3 times");
        }
        conference.getTalks().add(talkService.createTalk(talk));
        conferenceRepository.save(conference);
        return conference;
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

    private boolean isThreeTalks (Conference conference, String name) {
        long cnt = conference.getTalks().stream().filter(talk -> talk.getName().equals(name)).count();
        System.out.println("isTрreeTalks cnt: " +cnt);
        return cnt >= 3 ? true : false;
    }

    private  boolean overTime (Date dateConference) {
        Calendar c_talk = Calendar.getInstance();
        Calendar c_conf = Calendar.getInstance();
        c_talk.setTime(new Date(System.currentTimeMillis()));
        c_conf.setTime(dateConference);
        if(c_conf.get(Calendar.MONTH) - c_talk.get(Calendar.MONTH) < 1) {
            return true;
        } else return false;
    }
}
