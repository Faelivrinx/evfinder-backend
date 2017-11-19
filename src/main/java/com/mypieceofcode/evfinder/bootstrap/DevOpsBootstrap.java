package com.mypieceofcode.evfinder.bootstrap;

import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.domain.security.Role;
import com.mypieceofcode.evfinder.domain.security.UserRole;
import com.mypieceofcode.evfinder.repository.RoleRepository;
import com.mypieceofcode.evfinder.repository.UserRepository;
import com.mypieceofcode.evfinder.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.applet.AppletListener;

@Component
public class DevOpsBootstrap implements ApplicationListener<ContextRefreshedEvent> {

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    UserRoleRepository userRoleRepository;
//
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        Role role = new Role();
//        role.setName("USER");
//        roleRepository.save(role);
//
//        User user = new User();
//        user.setUsername("Faelivrinx");
//        user.setFirstName("Dominik");
//        user.setDisplayName("Faelivrinx");
//        user.setPassword(passwordEncoder.encode("xxx"));
//        user.setEmail("example@gmail..com");
//
//        userRepository.save(user);
//
//        UserRole userRole = new UserRole(user, role);
//        userRoleRepository.save(userRole);
    }


}
