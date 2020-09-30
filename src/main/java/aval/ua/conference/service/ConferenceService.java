package aval.ua.conference.service;

import aval.ua.conference.domain.entity.Conference;

import java.util.List;

public interface ConferenceService {

    //получение списка всех конференций (GET на /conferences)
    List<Conference> getAll();

    //-	добавление новой конференции (POST на /conferences) с названием, тематикой,
    // датами проведения и количеством участников
    Conference addConference(Conference request) ;
}
