package bart.electionsystem.security.controllers;


import bart.electionsystem.exceptionHandling.Client4xxException;
import bart.electionsystem.security.entities.ERole;
import bart.electionsystem.security.entities.Role;
import bart.electionsystem.security.entities.User;
import bart.electionsystem.security.jwt.JwtUtils;
import bart.electionsystem.security.payload.request.LoginRequest;
import bart.electionsystem.security.payload.request.SignupRequest;
import bart.electionsystem.security.payload.response.JwtResponse;
import bart.electionsystem.security.payload.response.MessageResponse;
import bart.electionsystem.security.repositories.RoleRepository;
import bart.electionsystem.security.repositories.UserRepository;
import bart.electionsystem.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	static final String ROLE_NOT_FOUND_MESSAGE = "Error: Role is not found.";

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	//public ResponseEntity<?>
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority) //.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
//												 userDetails.getId(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new Client4xxException("Username is already taken");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new Client4xxException(" Email is already in use");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE));
			roles.add(customerRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE));
					roles.add(adminRole);

					break;
				case "manager":
					Role employeeRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_MESSAGE));
					roles.add(employeeRole);
					break;
				default:
					throw new Client4xxException(ROLE_NOT_FOUND_MESSAGE);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
