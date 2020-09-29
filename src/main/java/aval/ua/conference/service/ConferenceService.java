package aval.ua.conference.service;

import aval.ua.conference.domain.Conference;
import aval.ua.conference.domain.dto.ConfRequest;

public interface ConferenceService {
    Conference addConference(ConfRequest request) ;
}
