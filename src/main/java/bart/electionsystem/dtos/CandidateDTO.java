package bart.electionsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDTO {
    private int id;
    private String fullName;
    private int votes;
    private PartyDTO party;

    public CandidateDTO(String fullName, int votes, PartyDTO party){
        this.fullName = fullName;
        this.votes = votes;
        this.party=party;
    }
}
