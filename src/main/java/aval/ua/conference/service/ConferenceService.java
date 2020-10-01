package aval.ua.conference.service;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;

import java.util.List;

public interface ConferenceService {

    //получение списка всех конференций (GET на /conferences)
    List<Conference> getAll();

    Conference getConference(long conf_id);

    //-	добавление доклада в конференцию (POST на /conferences/{conference_id}/talks) с
    //-	названием, описанием, именем докладчика и типом доклада (доклад, мастер-класс, воркшоп)
    List<Conference> addTalk(long conf_id, Talk talk);

    //-	добавление новой конференции (POST на /conferences) с названием, тематикой,
    // датами проведения и количеством участников
    Conference addConference(ConferenceRequest request) ;
}
