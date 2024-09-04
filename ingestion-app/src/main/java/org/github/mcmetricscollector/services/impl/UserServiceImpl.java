package org.github.mcmetricscollector.services.impl;

import com.google.common.hash.Hashing;
import org.github.mcmetricscollector.database.sql.entities.UserEntity;
import org.github.mcmetricscollector.database.sql.repository.UserRepository;
import org.github.mcmetricscollector.exceptions.ClientException;
import org.github.mcmetricscollector.exceptions.UserAlreadyExistsException;
import org.github.mcmetricscollector.exceptions.UserNotEnabledException;
import org.github.mcmetricscollector.gen.model.RegisterUserDTO;
import org.github.mcmetricscollector.services.UserService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        var user = userRepository.findUsersByUsername(username);
        if (user == null) {
            return false;
        }

        if (Boolean.FALSE.equals(user.getEnabled())) {
            throw new UserNotEnabledException("User not enabled");
        }

        return user.getPassword().equals(password);
    }

    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        UserEntity userEntity = new UserEntity();
        if (userRepository.findUsersByUsername(registerUserDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        userEntity.setUsername(registerUserDTO.getUsername());
        userEntity.setPassword(Hashing.sha256().hashString(registerUserDTO.getPassword(), StandardCharsets.UTF_8).toString());
        userEntity.setEnabled(false);
        userRepository.save(userEntity);
    }

    @Override
    public void enableDisableUser(String username) {
        var user = userRepository.findUsersByUsername(username);
        if (user == null) {
            throw new ClientException("User doesn't exist");
        }

        var enabled = Boolean.TRUE.equals(user.getEnabled());
        user.setEnabled(enabled);
        userRepository.save(user);
    }
}
