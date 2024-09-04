package org.github.mcmetricscollector.services;

import org.github.mcmetricscollector.gen.model.RegisterUserDTO;

public interface UserService {

    boolean login(String username, String password);

    void registerUser(RegisterUserDTO registerUserDTO);

    void enableDisableUser(String username);
}
