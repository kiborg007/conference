package aval.ua.conference.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ConferenceRequest {
        private String name ;
        private String theme ;
        private LocalDate date ;
        private int members ;

        @Override
        public String toString() {
                return "ConferenceRequest{" +
                        "name='" + name + '\'' +
                        ", theme='" + theme + '\'' +
                        ", date=" + date +
                        '}';
        }
}

