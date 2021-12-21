package bart.electionsystem.rest.candidate;

import bart.electionsystem.dtos.CandidateDTO;
import bart.electionsystem.dtos.converter.DTOConverter;
import bart.electionsystem.entities.Candidate;
import bart.electionsystem.services.candidate.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController implements CandidateControllerInterface{

    CandidateServiceInterface candidateService;
    DTOConverter dtoConverter;

    @Autowired
    public CandidateController(CandidateServiceInterface candidateService, DTOConverter dtoConverter) {
        this.candidateService = candidateService;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public List<CandidateDTO> getAll() {
        return dtoConverter.convertToListOfCandidateDTOs(candidateService.getAll());
    }

    @Override
    public CandidateDTO getById(int id) {
        return dtoConverter.convertToCandidateDTO(candidateService.getById(id));
    }

    @Override
    public List<CandidateDTO> getByPartyId(int partyId) {
        return dtoConverter.convertToListOfCandidateDTOs(candidateService.getByPartyId(partyId));
    }

    @Override
    public CandidateDTO createCandidate(int partyId, CandidateDTO candidateDTO) {
        return dtoConverter.convertToCandidateDTO(candidateService
                .createCandidate(dtoConverter.convertToCandidate(candidateDTO),partyId));
    }

    @Override
    public void deleteCandidateById(int id) {
        candidateService.deleteCandidate(id);
    }

    @Override
    public CandidateDTO editCandidate(int id, int partyId, CandidateDTO candidateDTO) {
        return dtoConverter.convertToCandidateDTO(candidateService
                .editCandidate(id, partyId, dtoConverter.convertToCandidate(candidateDTO)));
    }

    @Override
    public CandidateDTO vote(int id) {
        return dtoConverter.convertToCandidateDTO(candidateService.vote(id));
    }
}
