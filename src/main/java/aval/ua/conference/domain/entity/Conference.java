package aval.ua.conference.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String theme ;
    private String date ;
    private int prtspscount ;
//    @OneToMany
//    @JoinTable(name = "conf_talk",
//            joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Talk> talks;

}
