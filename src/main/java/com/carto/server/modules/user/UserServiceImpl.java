package com.carto.server.modules.user;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.dto.user.UpdateUserDto;
import com.carto.server.exception.InternalServerErrorException;
import com.carto.server.model.CartoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final CartoUserRepository cartoUserRepository;

    @Override
    public CartoUser saveNewUser(NewUserDto newUserDto) throws FirebaseAuthException {

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setDisplayName(newUserDto.getDisplayName())
                .setEmail(newUserDto.getEmailAddress())
                .setPassword(newUserDto.getPassword())
                .setDisabled(false);

        UserRecord userRecord = firebaseAuth.createUser(createRequest);

        CartoUser newCartoUser = new CartoUser();
        newCartoUser.setDisplayName(userRecord.getDisplayName());
        newCartoUser.setFirebaseId(userRecord.getUid());

        return this.cartoUserRepository.save(newCartoUser);
    }

    @Override
    public CartoUser getUser(String firebaseId) {
        return this.cartoUserRepository.findByFirebaseId(firebaseId);
    }

    @Override
    public CartoUser updateUser(CartoUser cartoUser, UpdateUserDto updateUserDto) throws InternalServerErrorException {

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        UserRecord.UpdateRequest request = new UserRecord
                .UpdateRequest(cartoUser.getFirebaseId())
                .setDisplayName(updateUserDto.getDisplayName());

        try {

            firebaseAuth.updateUser(request);
        } catch (FirebaseAuthException exception) {
            exception.printStackTrace();
            throw new InternalServerErrorException(
                    500,
                    "Something went wrong, please try again later"
            );
        }

        cartoUser.setDisplayName(updateUserDto.getDisplayName());

        return this.cartoUserRepository.save(cartoUser);
    }

    @Override
    public void deleteUser(CartoUser cartoUser) throws InternalServerErrorException {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        try {

            firebaseAuth.deleteUser(cartoUser.getFirebaseId());
        } catch (FirebaseAuthException exception) {
            exception.printStackTrace();
            throw new InternalServerErrorException(
                    500,
                    "Something went wrong, please try again later"
            );
        }

        this.cartoUserRepository.delete(cartoUser);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CartoUser cartoUser = this.cartoUserRepository.findByFirebaseId(userName);

        if (cartoUser == null) {
            throw new UsernameNotFoundException("User does not exist for the given Firebase ID");
        }

        return new User(cartoUser.getFirebaseId(), null, new ArrayList<>());
    }
}
