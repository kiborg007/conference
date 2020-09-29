package aval.ua.conference.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConfRequest {
    private String name ;
    private String thema ;
    private String date ;
    private int prtspscount ;

}
