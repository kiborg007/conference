package aval.ua.conference.service.impl;

import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkServiceImpl implements TalkService {
    private final TalkRepository talkRepository;

    @Override
    public Talk createTalk(Talk talk){
        System.out.println("##### createTalk "+talk);
        if(talkRepository.findByName(talk.getDesc()).orElse(null) == null){
            return talkRepository.save(talk);
        } else throw new InvalidException("The name of the talk already exists");
    }

}

