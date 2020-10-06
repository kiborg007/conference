package aval.ua.conference.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

import static java.util.Arrays.asList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CONFERENCE")
public class Conference {
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
