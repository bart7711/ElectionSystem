package bart.electionsystem.configuration;

import bart.electionsystem.entities.Candidate;
import bart.electionsystem.entities.Party;
import bart.electionsystem.repositories.CandidateRepo;
import bart.electionsystem.repositories.PartyRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSetup implements CommandLineRunner {
    CandidateRepo candidateRepo;
    PartyRepo partyRepo;

    public DataSetup(CandidateRepo candidateRepo, PartyRepo partyRepo) {
        this.candidateRepo = candidateRepo;
        this.partyRepo = partyRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Party party0 = partyRepo.save(new Party("Socialdemokratiet", 'A', "Social democracy"));
        Party party1 = partyRepo.save(new Party("Det konservative Folkeparti", 'C', "Conservative liberalism"));
        Party party2 = partyRepo.save(new Party("SF, Socialistisk Folkeparti", 'F', "Social liberalism"));
        Party party3 = partyRepo.save(new Party("- Dansk Folkeparti", 'O', "Monarchy"));

        Candidate candidate0 = candidateRepo.save(new Candidate("Marcel Meijer", party0));
        Candidate candidate1 = candidateRepo.save(new Candidate("Michael Kristensen", party0));
        Candidate candidate2 = candidateRepo.save(new Candidate("Helle Hansen", party0));
        Candidate candidate3 = candidateRepo.save(new Candidate("Karina Knobelauch", party0));
        Candidate candidate4 = candidateRepo.save(new Candidate("Stefan Hafstein Wolffbrandt", party0));
        Candidate candidate5 = candidateRepo.save(new Candidate("Robert V. Rasmussen", party0));
        Candidate candidate6 = candidateRepo.save(new Candidate("Pia Ramsing", party1));
        Candidate candidate7 = candidateRepo.save(new Candidate("Louise Bramstorp", party1));
        Candidate candidate8 = candidateRepo.save(new Candidate("Sigfred Jensen", party1));
        Candidate candidate9 = candidateRepo.save(new Candidate("Donald Trump", party1));
        Candidate candidate10 = candidateRepo.save(new Candidate("Mike Whatever", party1));
        Candidate candidate11 = candidateRepo.save(new Candidate("Empty Promises", party1));
        Candidate candidate12 = candidateRepo.save(new Candidate("Surprise Iwaznacee", party2));
        Candidate candidate13 = candidateRepo.save(new Candidate("Donald Clinton", party2));
        Candidate candidate14 = candidateRepo.save(new Candidate("Count Dracula", party2));
        Candidate candidate15 = candidateRepo.save(new Candidate("Per Hingel", party2));
        Candidate candidate16 = candidateRepo.save(new Candidate("Ula Holm", party2));
        Candidate candidate17 = candidateRepo.save(new Candidate("Per Mortensen", party2));
        Candidate candidate18 = candidateRepo.save(new Candidate("Fly Away", party1));
        Candidate candidate19 = candidateRepo.save(new Candidate("John Binden", party2));
        Candidate candidate20 = candidateRepo.save(new Candidate("Osama BinLanden", party1));
        Candidate candidate21 = candidateRepo.save(new Candidate("Barack Obrama", party2));
        Candidate candidate22 = candidateRepo.save(new Candidate("Marcin Najman", party3));

    }
}
