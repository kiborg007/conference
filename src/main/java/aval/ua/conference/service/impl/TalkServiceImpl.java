package aval.ua.conference.service.impl;

import aval.ua.conference.dao.TalkRepository;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidException;
import aval.ua.conference.exception.TalkAlreadyExistException;
import aval.ua.conference.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkServiceImpl implements TalkService {
    private final TalkRepository talkRepository;
    private final ConcurrentMap<String, List<Talk>> cache = new ConcurrentHashMap<>();

    @Override
    public Talk createTalk(Talk talk){
        System.out.println("##### createTalk "+talk);

        if(talkRepository.findByName(talk.getName()).orElse(null) == null){
            return talkRepository.save(talk);
        } else throw new TalkAlreadyExistException("The name of the talk already exists");
    }

    @Override
    public List<Talk> findTalksByDesc(String desc) {
        Assert.hasText(desc, "Desc is empty!");

        return cache.computeIfAbsent(desc, talkRepository::findByDesc);
    }



}

