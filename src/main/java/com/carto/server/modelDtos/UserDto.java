package com.carto.server.modelDtos;

import com.carto.server.model.CartoUser;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firebaseId;
    private String displayName;

    public void convertToDto(CartoUser cartoUser) {
        this.id = cartoUser.getId();
        this.displayName = cartoUser.getDisplayName();
        this.firebaseId = cartoUser.getFirebaseId();
    }
}
