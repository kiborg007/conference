package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.entity.Talk;

import java.util.ArrayList;
import java.util.List;

public class TalkMapper {
    public List<TalkRequest> mapToTalkRequestList(List<Talk> talks){
        List<TalkRequest> result = new ArrayList();
        talks.forEach(talk -> result.add(mapToTalkRequest(talk)));
        return result;
    }

    public TalkRequest mapToTalkRequest(Talk talk){
        TalkRequest talkRequest = new TalkRequest();
        talkRequest.setName(talk.getName());
        talkRequest.setDesc(talk.getDesc());
        talkRequest.setPerson(talk.getPerson());
        talkRequest.setType(talk.getType());
        return talkRequest;
    }
}
