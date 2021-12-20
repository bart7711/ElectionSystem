package bart.electionsystem.rest.party;


import bart.electionsystem.dtos.PartyDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface PartyControllerInterface {

    @GetMapping
    List<PartyDTO> getAll();
}
