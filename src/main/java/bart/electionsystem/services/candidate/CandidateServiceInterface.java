package bart.electionsystem.services.candidate;

import bart.electionsystem.entities.Candidate;

import java.util.List;

public interface CandidateServiceInterface {
    List<Candidate> getAll();
    Candidate getById(int id);
    Candidate createCandidate(Candidate candidate, int partyId);
    void deleteCandidate(int candidateId);
    Candidate editCandidate(int id, int partyId, Candidate candidate);
    List<Candidate> getByPartyId(int partyId);
    void vote(int id);
}
