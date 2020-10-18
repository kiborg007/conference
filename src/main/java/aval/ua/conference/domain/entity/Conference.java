package aval.ua.conference.domain.entity;

import aval.ua.conference.api.dto.ConferenceRequest;
import aval.ua.conference.api.dto.ConferenceResponse;
import aval.ua.conference.domain.mapper.MapperDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONFERENCE")
public class Conference{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "theme")
    private String theme;
    @Column(name = "date")
    private Date date;
    @Column(name = "prtspscount")
    private int prtspscount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "conftalk",
            joinColumns = {@JoinColumn(name = "conf_id")},
            inverseJoinColumns = {@JoinColumn(name = "talk_id")})
    private List<Talk> talks = new ArrayList<Talk>();

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", date=" + date +
                ", prtspscount=" + prtspscount +
                ", talks=" + talks +
                '}';
    }
}
