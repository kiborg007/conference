package aval.ua.conference.api.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ConferenceRequest {
    @JsonProperty("conference_name")
    private String name ;
    @JsonProperty("conference_theme")
    private String theme ;
    @JsonProperty("conference_date")
    private String date ;
    @JsonProperty("conference_prtspscount")
    private int prtspscount ;
    @JsonProperty("conference_talks")
    private List<TalkRequest> talks_lst;
}
