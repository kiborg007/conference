package aval.ua.conference.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONFERENCE")
public class Conference{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "theme")
    private String theme;
    @Column(name = "date")
    private LocalDate date;
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
