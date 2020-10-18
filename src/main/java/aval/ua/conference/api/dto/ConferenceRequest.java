package aval.ua.conference.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ConferenceRequest {
        private String name ;
        private String theme ;
        private Date date ;

        @Override
        public String toString() {
                return "ConferenceRequest{" +
                        "name='" + name + '\'' +
                        ", theme='" + theme + '\'' +
                        ", date=" + date +
                        '}';
        }
}

