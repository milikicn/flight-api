package info.milikic.nikola.flightapi.service.impl;

import info.milikic.nikola.flightapi.controller.dto.UserDto;
import info.milikic.nikola.flightapi.persistence.model.User;
import info.milikic.nikola.flightapi.persistence.repository.UserRepository;
import info.milikic.nikola.flightapi.service.UserService;
import info.milikic.nikola.flightapi.service.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerNewUserAccount(UserDto userDto) {
        if (emailExists(userDto.getUsername())) {
            throw new UserAlreadyExistException(MessageFormat.format("There is an user account with username: {0}", userDto.getUsername()));
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setRoles(List.of("ROLE_USER"));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByUsername(email).isPresent();
    }
}
