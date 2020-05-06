package info.milikic.nikola.flightapi.service;

import info.milikic.nikola.flightapi.controller.dto.UserDto;
import info.milikic.nikola.flightapi.persistence.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerNewUserAccount(UserDto userDto);

}
