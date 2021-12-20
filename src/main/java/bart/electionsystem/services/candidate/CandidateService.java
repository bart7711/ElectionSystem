package bart.electionsystem.services.candidate;

import bart.electionsystem.entities.Candidate;
import bart.electionsystem.entities.Party;
import bart.electionsystem.repositories.CandidateRepo;
import bart.electionsystem.repositories.PartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService implements CandidateServiceInterface {

    CandidateRepo candidateRepo;
    PartyRepo partyRepo;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo, PartyRepo partyRepo){
        this.candidateRepo = candidateRepo;
        this.partyRepo = partyRepo;
    }

    @Override
    public List<Candidate> getAll() {
        return candidateRepo.findAll();
    }

    @Override
    public Candidate getById(int id) {
        return candidateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no Candidate with id:" + id));
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public Candidate createCandidate(Candidate candidate, int partyId) {
        return candidateRepo.save(new Candidate(candidate.getFullName(),
                partyRepo.findById(partyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Party with this id does not exist:" + partyId))));

    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public void deleteCandidate(int candidateId) {
        if (!candidateRepo.existsById(candidateId)) {
            throw new ResourceNotFoundException("There is no such projection in our system");
        }
        candidateRepo.deleteById(candidateId);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public Candidate editCandidate(int id, int partyId, Candidate candidate) {
        Candidate updatedCandidate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no candidate with id " + id));

        if(candidate.getFullName()!=null){
            updatedCandidate.setFullName(candidate.getFullName());
        }
        if(partyId!=0){
            Party party = partyRepo.findById(partyId)
                    .orElseThrow(() -> new ResourceNotFoundException("There is no party with id" + partyId));
            updatedCandidate.setParty(party);
        }
        return candidateRepo.save(updatedCandidate);
    }

    @Override
    public List<Candidate> getByPartyId(int partyId) {
        List<Candidate> candidates = candidateRepo.findAllByParty_Id(partyId);
        if(candidates.isEmpty()){
            throw new ResourceNotFoundException("There are no candidates in a party with id: "+partyId);
        }
        return candidates;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public void vote(int id) {
        Candidate candidate = candidateRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no Candidate with id:" + id));
        candidate.setVotes(candidate.getVotes()+1);
        candidateRepo.save(candidate);
    }
}
