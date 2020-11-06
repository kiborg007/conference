package aval.ua.conference.service.impl;

import aval.ua.conference.dao.ConferenceRepository;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidConferenceException;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.InvalidNameException;
import aval.ua.conference.exception.InvalidTimeRegistrationException;
import aval.ua.conference.service.ConferenceService;
import aval.ua.conference.service.TalkService;
import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final TalkService talkService;

    @Override
    public List<Conference> getAll() {
        return conferenceRepository.findAll();
    }

    @Override
    public Conference getConference(Long conf_id) {
        Conference conference = conferenceRepository.findById(conf_id).orElse(null);
        if(conference != null) {
            return conference;
        } else throw new InvalidException("Conference by ID:" + conf_id + " not found");
    }

    @Override
    public Conference addTalk(long conf_id, Talk talk) throws  InvalidException, InvalidNameException{
        System.out.println("### addTalk talk conf_id:"+conf_id + ", talk"+talk);
        Conference conference = conferenceRepository.findById(conf_id).orElse(null);
        if(conference != null) {
            if (overTime(conference.getDate())) {
                System.out.println("isOverTime");
                throw new InvalidTimeRegistrationException("Registration time is over");
            }
            if (isThreeTalks(conference, talk.getName())) {
                throw new InvalidNameException("Name of talk is more than 3 times");
            }
            conference.getTalks().add(talkService.createTalk(talk));
            int members = conference.getTalks().size();
            conference.setPrtspscount(members);
            conferenceRepository.save(conference);
        } else throw new InvalidException("Conference by ID:" + conf_id + " not found");
        System.out.println("### addTalk conference:"+conference);
        return conference;
    }

    @Counted(value = "Conference.counter", description = "addConference")
    @Override
    public Conference addConference(Conference conf) {
        if(conferenceRepository.findByName(conf.getName()).orElse(null) == null){
            return conferenceRepository.save(conf) ;
        }else{
            throw new InvalidConferenceException("This conference already exist") ;
        }

    }

    private boolean isThreeTalks (Conference conference, String name) {
        long cnt = conference.getTalks().stream().filter(talk -> talk.getName().equals(name)).count();
        System.out.println("isThreeTalks name: " +name);
        System.out.println("isThreeTalks cnt: " +cnt);
        return cnt >= 3 ? true : false;
    }

    private  boolean overTime (LocalDate dateConference) {
        LocalDate c_talk = LocalDate.now();
        LocalDate c_conf = dateConference;
        if(dateConference.getYear() - c_talk.getYear() < 0){
            if(dateConference.getMonth().getValue()- c_talk.getMonth().getValue() < 1) {
                return true;
            } else return false;
        } else return false;
    }
}
