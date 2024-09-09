package org.github.mcmetricscollector.controllers;

import org.github.mcmetricscollector.gen.api.ManagementAPI;
import org.github.mcmetricscollector.gen.model.EnableDisableUserDTO;
import org.github.mcmetricscollector.gen.model.RegisterUserDTO;
import org.github.mcmetricscollector.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController implements ManagementAPI {

    private final UserService userService;

    public ManagementController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> registerUser(RegisterUserDTO registerUserDTO) {
        userService.registerUser(registerUserDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> enableDisableUser(EnableDisableUserDTO enableDisableUserDTO) {
        userService.enableDisableUser(enableDisableUserDTO.getUsername());
        return ResponseEntity.ok().build();
    }
}
