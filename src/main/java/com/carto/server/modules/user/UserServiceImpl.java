package com.carto.server.modules.user;

import com.carto.server.dto.user.NewUserDto;
import com.carto.server.model.CartoUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
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
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CartoUser cartoUser = this.cartoUserRepository.findCartoUserByFirebaseId(userName);

        if (cartoUser == null) {
            throw new UsernameNotFoundException("User does not exist for the given Firebase ID");
        }

        return new User(cartoUser.getFirebaseId(), null, new ArrayList<>());
    }
}
