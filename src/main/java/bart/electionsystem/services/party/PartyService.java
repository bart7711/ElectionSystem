package bart.electionsystem.services.party;

import bart.electionsystem.entities.Party;
import bart.electionsystem.repositories.PartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService implements PartyServiceInterface{

    PartyRepo partyRepo;

    @Autowired
    public PartyService(PartyRepo partyRepo){
        this.partyRepo = partyRepo;
    }

    @Override
    public List<Party> getAll() {
        return partyRepo.findAll();
    }
}
