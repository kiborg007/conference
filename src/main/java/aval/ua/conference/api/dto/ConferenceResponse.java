package aval.ua.conference.api.dto;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
public class ConferenceResponse {
    private Long id ;
    private String name ;
    private String theme ;
    private Date date ;
    private int members ;

    @Override
    public String toString() {
        return "ConferenceResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", date=" + date +
                ", members=" + members +
                '}';
    }
}
