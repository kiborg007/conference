package aval.ua.conference.service;

import aval.ua.conference.domain.Conference;
import aval.ua.conference.domain.dto.ConfRequest;

import java.util.List;

public interface ConferenceService {
    List<Conference> getAll();
    Conference addConference(ConfRequest request) ;
}
