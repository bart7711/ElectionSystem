package bart.electionsystem.dtos.converter;

import bart.electionsystem.dtos.CandidateDTO;
import bart.electionsystem.dtos.PartyDTO;
import bart.electionsystem.entities.Candidate;
import bart.electionsystem.entities.Party;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    ModelMapper modelMapper;

    @Autowired
    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PartyDTO convertToPartyDTO(Party party){
        return modelMapper.map(party, PartyDTO.class);
    }

    public Party convertToParty(PartyDTO partyDTO){
        return modelMapper.map(partyDTO, Party.class);
    }

    public CandidateDTO convertToCandidateDTO(Candidate candidate){
        CandidateDTO candidateDTO = modelMapper.map(candidate, CandidateDTO.class);
        candidateDTO.setParty(convertToPartyDTO(candidate.getParty()));
        return candidateDTO;
    }

    public Candidate convertToCandidate(CandidateDTO candidateDTO){
        return modelMapper.map(candidateDTO, Candidate.class);
    }
    public List<Candidate> convertToListOfCandidates(List<CandidateDTO> candidatesDTO) {
        return candidatesDTO.stream()
                .map(this::convertToCandidate)
                .collect(Collectors.toList());
    }
    public List<CandidateDTO> convertToListOfCandidateDTOs(List<Candidate> candidates) {
        return candidates.stream()
                .map(this::convertToCandidateDTO)
                .collect(Collectors.toList());
    }

}
