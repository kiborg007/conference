package aval.ua.conference.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String theme;
    private String date;
    private int prtspscount;
    @OneToMany
    @JoinTable(name = "conftalk",
            joinColumns = {@JoinColumn(name = "conf_id")},
            inverseJoinColumns = {@JoinColumn(name = "talk_id")})
    private List<Talk> talks = new ArrayList<>();

    public void addTalks(Talk... talk) {
        this.talks.addAll(asList(talk));
    }
}
