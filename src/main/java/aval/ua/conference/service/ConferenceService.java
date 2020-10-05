package aval.ua.conference.service;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.domain.entity.Conference;
import aval.ua.conference.domain.entity.Talk;
import aval.ua.conference.exception.InvalidNameException;

import java.util.List;

public interface ConferenceService {

    //получение списка всех конференций (GET на /conferences)
    List<Conference> getAll();

    Conference getConference(Long conf_id);

    //-	добавление доклада в конференцию (POST на /conferences/{conference_id}/talks) с
    //-	названием, описанием, именем докладчика и типом доклада (доклад, мастер-класс, воркшоп)
    Conference addTalk(long conf_id, Talk talk) throws InvalidNameException;

    //-	добавление новой конференции (POST на /conferences) с названием, тематикой,
    // датами проведения и количеством участников
    Conference addConference(Conference request) ;
}
