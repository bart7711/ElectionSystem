package bart.electionsystem.repositories;

import bart.electionsystem.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer> {
    List<Candidate> findAllByParty_Id(int id);
}
