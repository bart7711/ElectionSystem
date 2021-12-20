package bart.electionsystem.services.candidate;

import bart.electionsystem.entities.Candidate;
import bart.electionsystem.repositories.CandidateRepo;
import bart.electionsystem.repositories.PartyRepo;
import bart.electionsystem.services.party.PartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CandidateServiceTest {

    @Autowired
    PartyRepo partyRepo;

    @Autowired
    CandidateRepo candidateRepo;


    PartyService partyService;
    CandidateService candidateService;

    @BeforeEach
    public void initServices(){
        partyService = new PartyService(partyRepo);
        candidateService = new CandidateService(candidateRepo, partyRepo);
    }


    @Test
    @Sql("/createData.sql")
    void getAll() {
        long count = candidateService.getAll().size();
        assertEquals(3,count);
    }

    @Test
    @Sql("/createData.sql")
    void getById() {
        String name = candidateService.getById(1).getFullName();
        assertEquals("Ted Ask", name);
    }

    @Test
    @Sql("/createData.sql")
    void createCandidate() {
        Candidate newCandidate = new Candidate();
        newCandidate.setFullName("Test test");
        Candidate candidate = candidateService.createCandidate(newCandidate, 1);
        long count = candidateService.getAll().size();
        assertEquals(4,count);
        assertEquals("Test test", candidate.getFullName());
        assertEquals(1,candidate.getParty().getId());
    }

    @Test
    @Sql("/createData.sql")
    void deleteCandidate() {
        candidateService.deleteCandidate(1);
        long count = candidateService.getAll().size();
        assertEquals(2,count);
    }

    @Test
    @Sql("/createData.sql")
    void editCandidate() {
        Candidate edit = new Candidate();
        edit.setFullName("Testing Testing");
        candidateService.editCandidate(1,1,edit);
        Candidate editedCandidate = candidateService.getById(1);
        assertEquals("Testing Testing",editedCandidate.getFullName());
    }

    @Test
    @Sql("/createData.sql")
    void getByPartyId() {
        long count = candidateService.getByPartyId(1).size();
        assertEquals(2, count);
    }
}