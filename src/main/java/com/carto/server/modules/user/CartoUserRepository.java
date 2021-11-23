package com.carto.server.modules.user;

import com.carto.server.model.CartoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartoUserRepository extends JpaRepository<CartoUser, Long> {

    CartoUser findCartoUserByFirebaseId(String firebaseId);

}
