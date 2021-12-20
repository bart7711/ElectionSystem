package bart.electionsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyDTO {
    private int id;
    private String name;
    private Character symbol;
    private String ideology;

    public PartyDTO(String name, Character symbol, String ideology){
        this.name = name;
        this.symbol = symbol;
        this.ideology = ideology;
    }
}
