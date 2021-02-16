package aval.ua.conference.service;

import aval.ua.conference.domain.entity.Talk;

import java.util.List;

public interface TalkService {

    Talk createTalk(Talk talk);

    List<Talk> findTalksByDesc(String desc);
}
