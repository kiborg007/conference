package aval.ua.conference.dao.entity;

import aval.ua.conference.domain.Report;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "conference")
public class ConferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String desc;

    private Date date;

    @OneToMany
    @JoinTable(name = "report_list",
            joinColumns = {@JoinColumn(name = "conference_id")},
            inverseJoinColumns = {@JoinColumn(name = "report_id")})
    private Set<Report> reportList;

}

