package bart.electionsystem.repositories;

import bart.electionsystem.entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party,Integer> {
}
