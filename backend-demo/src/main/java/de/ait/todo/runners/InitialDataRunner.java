package de.ait.todo.runners;

import de.ait.todo.models.User;
import de.ait.todo.repositories.RoomsRepository;
import de.ait.todo.repositories.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

@RequiredArgsConstructor
//@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitialDataRunner implements CommandLineRunner {

    UsersRepository usersRepository;

    @Override
    public void run(String... args) {

        User alisher = null;

        if (!usersRepository.existsById(1L)) {
            User admin = User.builder()
                    .email("admin@ait-tr.de")
                    .role(User.Role.ADMIN)
                    .hashPassword("$2a$10$YijmlwvWMcfIhT2qQOQ7EeRuMiByNjPtKXa78J7Y8z7XZWJJQTDa.") // admin

                    .build();

            alisher = User.builder()
                    .email("alisher@ait-tr.de")
                    .role(User.Role.USER)
                    .hashPassword("$2a$10$RVSHTssubxIkoAl3rQ58UedU8sPMM6FZRxg1icrJg07f.MQAMRpDy") // alisher
                    .build();
            usersRepository.save(admin);
            usersRepository.save(alisher);
        }

    }
}