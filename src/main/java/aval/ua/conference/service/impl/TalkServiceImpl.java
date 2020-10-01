package aval.ua.conference.service.impl;

import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.service.TalkService;
import aval.ua.conference.utils.IdGenerator;
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
        System.out.println("##### createTalk");
        Long newId;
        do {
            newId = IdGenerator.newId();
        } while (talkRepository.findById(newId) != null);
        talk.setId(newId);
        talkRepository.save(talk);
        return talk;
    }
}

