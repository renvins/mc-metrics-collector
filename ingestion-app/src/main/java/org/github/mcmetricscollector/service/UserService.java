package org.github.mcmetricscollector.service;

import org.github.mcmetricscollector.gen.model.RegisterUserDTO;
import org.github.mcmetricscollector.security.Principal;

public interface UserService {


    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return true if the login was successful, false otherwise
     */
    boolean login(String username, String password);


    /**
     * Registers a new user with the provided user details.
     *
     * @param registerUserDTO the data transfer object containing user details such as username and password
     */
    void registerUser(RegisterUserDTO registerUserDTO);


    /**
     * Enables or disables a user account based on the given username.
     *
     * @param username the username of the user to be enabled or disabled
     */
    void enableDisableUser(String username);

    Principal constructPrincipal(String username);
}
