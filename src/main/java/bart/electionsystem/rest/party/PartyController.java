package bart.electionsystem.rest.party;

import bart.electionsystem.dtos.PartyDTO;
import bart.electionsystem.dtos.converter.DTOConverter;
import bart.electionsystem.services.party.PartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/party")
public class PartyController implements PartyControllerInterface{

    PartyServiceInterface partyService;
    DTOConverter dtoConverter;

    @Autowired
    public PartyController(PartyServiceInterface partyService, DTOConverter dtoConverter) {
        this.partyService = partyService;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<PartyDTO> getAll() {
        return dtoConverter.convertToListOfPartyDTO(partyService.getAll());
    }
}
