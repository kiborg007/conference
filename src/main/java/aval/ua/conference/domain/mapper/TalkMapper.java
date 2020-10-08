package aval.ua.conference.domain.mapper;

import aval.ua.conference.api.dto.TalkRequest;
import aval.ua.conference.domain.entity.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TalkMapper {
    public List<TalkRequest> mapToTalkRequestList(List<Talk> talks){
        List<TalkRequest> result = new ArrayList();
        if (talks != null) {
            talks.forEach(talk -> result.add(mapToTalkRequest(talk)));
        }
        return result;
    }

    public TalkRequest mapToTalkRequest(Talk talk){
        TalkRequest talkRequest = new TalkRequest();
        talkRequest.setId(talk.getId());
        talkRequest.setName(talk.getName());
        talkRequest.setDesc(talk.getDesc());
        talkRequest.setPerson(talk.getPerson());
        talkRequest.setType(talk.getType());
        return talkRequest;
    }

    public Talk mapToTalk(TalkRequest talkRequest){
        Talk talk = new Talk();
        talk.setName(talkRequest.getName());
        talk.setDesc(talkRequest.getDesc());
        talk.setPerson(talkRequest.getPerson());
        talk.setType(talkRequest.getType());
        return talk;
    }
}
