package com.carto.server.modules.user;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.user.NewUserDto;
import com.carto.server.dto.user.UpdateUserDto;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.model.CartoUser;
import com.google.firebase.auth.FirebaseAuthException;

public interface UserService {

    CartoUser saveNewUser(NewUserDto newUserDto) throws FirebaseAuthException;

    CartoUser getUser(String firebaseId);

    CartoUser updateUser(CartoUser cartoUser, UpdateUserDto updateUserDto) throws InternalServerErrorException;

    void deleteUser(CartoUser cartoUser);

}
