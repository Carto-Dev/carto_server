package com.carto.server.modules.auth;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.exception.FirebaseException;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.model.CartoUser;
import com.carto.server.modules.user.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping(path = "/register")
    public CartoUser registerNewUser(@Valid @RequestBody NewUserDto newUserDto) throws FirebaseException, InternalServerErrorException {
        try {
            return this.userService.saveNewUser(newUserDto);
        } catch (FirebaseAuthException exception) {
            throw new FirebaseException(500, exception.getMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new InternalServerErrorException(500, "Something went wrong, please try again later.");
        }
    }

}