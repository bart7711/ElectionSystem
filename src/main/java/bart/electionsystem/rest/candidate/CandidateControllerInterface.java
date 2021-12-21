package bart.electionsystem.rest.candidate;

import bart.electionsystem.dtos.CandidateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CandidateControllerInterface {

    @GetMapping
    List<CandidateDTO> getAll();

    @GetMapping("/{id}")
    CandidateDTO getById(@PathVariable int id);

    @GetMapping("/party/{partyId}")
    List<CandidateDTO> getByPartyId(@PathVariable int partyId);

    @PostMapping("/party/{partyId}")
    @ResponseStatus(HttpStatus.CREATED)
    CandidateDTO createCandidate(@PathVariable int partyId, @RequestBody CandidateDTO candidateDTO);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCandidateById(@PathVariable("id") int id);

    @PutMapping("/{id}/party/{partyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    CandidateDTO editCandidate(@PathVariable int id, @PathVariable int partyId,
                               @RequestBody CandidateDTO candidateDTO);

    @PutMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    CandidateDTO vote(@PathVariable int id);

}
