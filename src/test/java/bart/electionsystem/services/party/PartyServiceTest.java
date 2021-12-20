package bart.electionsystem.services.party;


import bart.electionsystem.repositories.PartyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PartyServiceTest {

    @Autowired
    PartyRepo partyRepo;

    PartyService partyService;

    @BeforeEach
    public void initServices(){
        partyService = new PartyService(partyRepo);
    }

    @Test
    @Sql("/createData.sql")
    void getAll() {
        long count = partyService.getAll().size();
        assertEquals(2, count);
    }
}