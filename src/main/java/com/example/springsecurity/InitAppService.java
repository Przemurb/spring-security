package com.example.springsecurity;

import com.example.springsecurity.security.domain.Privilege;
import com.example.springsecurity.security.domain.Role;
import com.example.springsecurity.security.domain.RoleEnum;
import com.example.springsecurity.security.domain.User;
import com.example.springsecurity.security.repository.PrivilegeRepository;
import com.example.springsecurity.security.repository.RoleRepository;
import com.example.springsecurity.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitAppService {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String ADMIN_FIRST_NAME = "Jan";
    private static final String ADMIN_LAST_NAME = "Janowski";

    private static final String USER = "user";
    private static final String PASSWORD = "123";
    private static final String FIRST_NAME = "Piotr";
    private static final String LAST_NAME = "Piotrowski";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    public InitAppService(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PrivilegeRepository privilegeRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void initData() {
        final Privilege readPrivilege = newPrivilege("ROLE_READ_PRIVILEGE");
        final Privilege writePrivilege = newPrivilege("ROLE_WRITE_PRIVILEGE");
        final Privilege apiReadPrivilege = newPrivilege("ROLE_API_READ_PRIVILEGE");

        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege, apiReadPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(List.of(readPrivilege));

        final Role roleUser = newRole(RoleEnum.ROLE_USER.getValue(), userPrivileges);
        final Role roleAdmin = newRole(RoleEnum.ROLE_ADMIN.getValue(), adminPrivileges);

        newUser(roleUser);
        newAdmin(roleAdmin);
    }

    private Privilege newPrivilege(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private Role newRole(String name, List<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);

        return role;
    }

    private void newUser(Role roleUser) {
        userCreator(roleUser, USER, PASSWORD, FIRST_NAME, LAST_NAME);
    }

    private void newAdmin(Role roleAdmin) {
        userCreator(roleAdmin, ADMIN_USER, ADMIN_PASSWORD, ADMIN_FIRST_NAME, ADMIN_LAST_NAME);
    }

    private void userCreator(Role role, String adminUser, String password, String firstName, String lastName) {
        User user = new User();
        user.setEmail(adminUser);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        userRepository.save(user);
    }
}