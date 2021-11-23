package com.carto.server.modules.user;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.model.CartoUser;

public interface UserService {

    CartoUser saveNewUser(NewUserDto newUserDto);

    CartoUser getUser(String userId);

    CartoUser updateUser(CartoUser cartoUser);

    void deleteUser(CartoUser cartoUser);

}
