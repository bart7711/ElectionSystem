package bart.electionsystem.configuration;

import bart.electionsystem.entities.Candidate;
import bart.electionsystem.entities.Party;
import bart.electionsystem.repositories.CandidateRepo;
import bart.electionsystem.repositories.PartyRepo;
import bart.electionsystem.security.entities.ERole;
import bart.electionsystem.security.entities.Role;
import bart.electionsystem.security.entities.User;
import bart.electionsystem.security.repositories.RoleRepository;
import bart.electionsystem.security.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Profile("!test")
public class DataSetup implements CommandLineRunner {

    CandidateRepo candidateRepo;
    PartyRepo partyRepo;
    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public DataSetup(CandidateRepo candidateRepo, PartyRepo partyRepo, RoleRepository roleRepository,
                     UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepo = candidateRepo;
        this.partyRepo = partyRepo;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName(ERole.ROLE_ADMIN);

        Role customerRole = new Role();
        customerRole.setName(ERole.ROLE_CUSTOMER);

        roleRepository.save(adminRole);
        roleRepository.save(customerRole);

        User customer = new User("voter", "customer@example.com", passwordEncoder.encode("test"));
        User admin = new User("admin", "admin@example.com", passwordEncoder.encode("test"));
        customer.addRole(customerRole);
        admin.addRole(adminRole);

        userRepository.save(customer);
        userRepository.save(admin);


        Party party0 = partyRepo.save(new Party("Socialdemokratiet", 'A', "Social democracy"));
        Party party1 = partyRepo.save(new Party("Det konservative Folkeparti", 'C', "Conservative liberalism"));
        Party party2 = partyRepo.save(new Party("SF, Socialistisk Folkeparti", 'F', "Social liberalism"));
        Party party3 = partyRepo.save(new Party("Dansk Folkeparti", 'O', "Monarchy"));

        candidateRepo.save(new Candidate("Marcel Meijer", party0));
        candidateRepo.save(new Candidate("Michael Kristensen", party0));
        candidateRepo.save(new Candidate("Helle Hansen", party0));
        candidateRepo.save(new Candidate("Karina Knobelauch", party0));
        candidateRepo.save(new Candidate("Stefan Hafstein Wolffbrandt", party0));
        candidateRepo.save(new Candidate("Robert V. Rasmussen", party0));
        candidateRepo.save(new Candidate("Pia Ramsing", party1));
        candidateRepo.save(new Candidate("Louise Bramstorp", party1));
        candidateRepo.save(new Candidate("Sigfred Jensen", party1));
        candidateRepo.save(new Candidate("Donald Trump", party1));
        candidateRepo.save(new Candidate("Mike Whatever", party1));
        candidateRepo.save(new Candidate("Empty Promises", party1));
        candidateRepo.save(new Candidate("Surprise Iwaznacee", party2));
        candidateRepo.save(new Candidate("Donald Clinton", party2));
        candidateRepo.save(new Candidate("Count Dracula", party2));
        candidateRepo.save(new Candidate("Per Hingel", party2));
        candidateRepo.save(new Candidate("Ula Holm", party2));
        candidateRepo.save(new Candidate("Per Mortensen", party2));
        candidateRepo.save(new Candidate("Fly Away", party1));
        candidateRepo.save(new Candidate("John Binden", party2));
        candidateRepo.save(new Candidate("Osama BinLanden", party1));
        candidateRepo.save(new Candidate("Barack Obrama", party2));
        candidateRepo.save(new Candidate("Marcin Najman", party3));

    }
}
