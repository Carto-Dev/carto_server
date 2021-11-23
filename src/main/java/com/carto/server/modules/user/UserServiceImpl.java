package com.carto.server.modules.user;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.model.CartoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final CartoUserRepository cartoUserRepository;

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public CartoUser saveNewUser(NewUserDto newUserDto) throws FirebaseAuthException {

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setDisplayName(newUserDto.getDisplayName())
                .setEmail(newUserDto.getEmailAddress())
                .setPassword(newUserDto.getPassword())
                .setDisabled(false);

        UserRecord userRecord = this.firebaseAuth.createUser(createRequest);

        CartoUser newCartoUser = new CartoUser();
        newCartoUser.setDisplayName(userRecord.getDisplayName());
        newCartoUser.setFirebaseId(userRecord.getUid());

        return this.cartoUserRepository.save(newCartoUser);
    }

    @Override
    public CartoUser getUser(Long userId) {
        return this.cartoUserRepository.getById(userId);
    }

    @Override
    public CartoUser updateUser(CartoUser cartoUser) {
        return this.cartoUserRepository.save(cartoUser);
    }

    @Override
    public void deleteUser(CartoUser cartoUser) {
        this.cartoUserRepository.delete(cartoUser);
    }
}
