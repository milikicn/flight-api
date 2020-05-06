package info.milikic.nikola.flightapi.controller;

import info.milikic.nikola.flightapi.controller.dto.GenericResponse;
import info.milikic.nikola.flightapi.controller.dto.UserDto;
import info.milikic.nikola.flightapi.service.UserService;
import info.milikic.nikola.flightapi.vo.ServiceConst;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = ServiceConst.USER_API)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public GenericResponse<String> registerUserAccount(
            @ApiParam(value = "Model used to initiate user register request", required = true) @Valid @RequestBody UserDto userDto) {
        log.debug("Register new user with information: {}", userDto);

        userService.registerNewUserAccount(userDto);

        return new GenericResponse<>("success");
    }
}
