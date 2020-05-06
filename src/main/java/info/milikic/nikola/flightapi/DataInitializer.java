package info.milikic.nikola.flightapi;

import info.milikic.nikola.flightapi.persistence.model.User;
import info.milikic.nikola.flightapi.persistence.repository.UserRepository;
import info.milikic.nikola.flightapi.vo.ApplicationRoles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${users.admin.username:admin}")
    private String adminUsername;

    @Value("${users.admin.password:admin}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        Optional<User> adminUser = userRepository.findByUsername(adminUsername);

        if (adminUser.isEmpty()) {
            userRepository.save(User.builder()
                    .firstName("")
                    .lastName("")
                    .username(adminUsername)
                    .password(this.passwordEncoder.encode(adminPassword))
                    .roles(List.of(ApplicationRoles.ROLE_USER, ApplicationRoles.ROLE_ADMIN))
                    .build());
        }
        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
