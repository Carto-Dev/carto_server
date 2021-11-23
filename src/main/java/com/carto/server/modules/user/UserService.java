package com.carto.server.modules.user;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.model.CartoUser;
import com.google.firebase.auth.FirebaseAuthException;

public interface UserService {

    CartoUser saveNewUser(NewUserDto newUserDto) throws FirebaseAuthException;

    CartoUser getUser(Long userId);

    CartoUser updateUser(CartoUser cartoUser);

    void deleteUser(CartoUser cartoUser);

}
