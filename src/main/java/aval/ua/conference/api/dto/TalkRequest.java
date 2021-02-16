package aval.ua.conference.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TalkRequest {
    private String name;
    private String type;
    private String desc;
    private String person;

    @Override
    public String toString() {
        return "TalkRequest{" +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", person='" + person + '\'' +
                '}';
    }
}
