package com.carto.server.modules.user;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.user.UpdateUserDto;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.modelDtos.UserDto;
import com.carto.server.model.CartoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping()
    public UserDto updateUser(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody UpdateUserDto updateUserDto) throws InternalServerErrorException {
        CartoUser user = this.userService.updateUser(cartoUser, updateUserDto);

        UserDto userDto = new UserDto();
        userDto.convertToDto(user);

        return userDto;
    }

    @DeleteMapping()
    public void deleteUser(@LoggedInUser CartoUser cartoUser) {
        this.userService.deleteUser(cartoUser);
    }

}
