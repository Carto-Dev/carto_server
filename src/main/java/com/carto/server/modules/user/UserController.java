package com.carto.server.modules.user;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.user.UpdateUserDto;
import com.carto.server.model.CartoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PutMapping()
    public CartoUser updateUser(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody UpdateUserDto updateUserDto) {
        return this.userService.updateUser(cartoUser, updateUserDto);
    }

}
