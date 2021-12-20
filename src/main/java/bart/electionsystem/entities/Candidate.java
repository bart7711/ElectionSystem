package bart.electionsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private int votes;

    @ManyToOne
    private Party party;

    public Candidate(String fullName, Party party){
        this.fullName = fullName;
        this.party = party;
        this.votes = 0;
    }
}
