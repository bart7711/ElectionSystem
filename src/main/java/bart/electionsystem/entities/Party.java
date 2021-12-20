package bart.electionsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Character symbol;

    @Column(nullable = false)
    private String ideology;

    @OneToMany(mappedBy = "party")
    Collection<Candidate> candidates = new ArrayList<>();

    public Party(String name, Character symbol, String ideology){
        this.name = name;
        this.symbol = symbol;
        this.ideology = ideology;
    }
}
