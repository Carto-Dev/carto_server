package com.carto.server.modules.user;

import com.carto.server.model.CartoUser;

public interface UserService {

    CartoUser saveNewUser(CartoUser cartoUser);

    CartoUser getUser(String userId);

    CartoUser updateUser(CartoUser cartoUser);

    void deleteUser(CartoUser cartoUser);

}
