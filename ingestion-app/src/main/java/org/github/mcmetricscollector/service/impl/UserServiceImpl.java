package org.github.mcmetricscollector.service.impl;

import com.google.common.hash.Hashing;
import org.github.mcmetricscollector.database.sql.entities.UserEntity;
import org.github.mcmetricscollector.database.sql.repository.RolePermissionRepository;
import org.github.mcmetricscollector.database.sql.repository.UserRepository;
import org.github.mcmetricscollector.database.sql.repository.UserRoleRepository;
import org.github.mcmetricscollector.exceptions.ClientException;
import org.github.mcmetricscollector.exceptions.UserAlreadyExistsException;
import org.github.mcmetricscollector.exceptions.UserNotEnabledException;
import org.github.mcmetricscollector.gen.model.RegisterUserDTO;
import org.github.mcmetricscollector.security.Principal;
import org.github.mcmetricscollector.security.objects.Role;
import org.github.mcmetricscollector.security.objects.RoleProjection;
import org.github.mcmetricscollector.service.UserService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RolePermissionRepository rolePermissionRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
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

    @Override
    public Principal constructPrincipal(String username) {
        List<RoleProjection> rolePermissionProjection = userRoleRepository.findRolesOf(username);
        Map<String, List<String>> rolePermissions = new HashMap<>();

        for (RoleProjection roleProjection : rolePermissionProjection) {
            rolePermissions.putIfAbsent(roleProjection.role(), new ArrayList<>());
            rolePermissions.get(roleProjection.role()).add(roleProjection.permission());
        }

        List<Role> roles = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : rolePermissions.entrySet()) {
            var roleName = entry.getKey();
            var permissions = entry.getValue();
            var role = new Role(roleName, permissions);
            roles.add(role);
        }


        return new Principal.PrincipalImpl(username, roles);
    }
}
