package com.mypieceofcode.evfinder.service.reposervices;

import com.mypieceofcode.evfinder.command.ApiKey;
import com.mypieceofcode.evfinder.command.UserCommand;
import com.mypieceofcode.evfinder.converters.network.UserCommandToUser;
import com.mypieceofcode.evfinder.converters.network.UserToUserCommand;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.domain.security.Role;
import com.mypieceofcode.evfinder.domain.security.UserRole;
import com.mypieceofcode.evfinder.repository.RoleRepository;
import com.mypieceofcode.evfinder.repository.UserRepository;
import com.mypieceofcode.evfinder.repository.UserRoleRepository;
import com.mypieceofcode.evfinder.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceRepoImpl implements UserService{
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UserServiceRepoImpl.class);

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;

    final EntityManager entityManager;

    @Autowired
    UserToUserCommand userToUserCommand;

    @Autowired
    UserCommandToUser userCommandToUser;

    @Autowired
    public UserServiceRepoImpl(BCryptPasswordEncoder passwordEncoder,
                               UserRepository userRepository,
                               UserRoleRepository userRoleRepository,
                               RoleRepository roleRepository, EntityManager manager) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.entityManager = manager;
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOG.warn("Username {} not found", username);
            return null;
        }
        return user;
    }


    @Override
    public ApiKey createUserByUserCommand(UserCommand userCommand) {
        User user = userCommandToUser.convert(userCommand);
        User createdUser = createUser(user);
        if (createdUser != null){
            ApiKey apiKey = new ApiKey();
            apiKey.setValue(createdUser.getApiToken());
            return apiKey;
        }
        throw new IllegalStateException("User already exist!");
    }

    @Transactional
    @Override
    public User createUser(User user) {
        User newUser = userRepository.findByUsername(user.getUsername());
        if (newUser == null) {
            user.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setApiToken(passwordEncoder.encode(user.getUsername()+"salt"));
            userRepository.save(user);
            Role role = roleRepository.findByName("USER");
            UserRole userRole = new UserRole(user, role);
            userRoleRepository.save(userRole);
            return user;
        } else {
            LOG.warn("User {} already exists!", newUser.getUsername());
            return null;
        }
    }

    @Override
    public User createUser(User user, Set<UserRole> roles) {
        User newUser = userRepository.findByUsername(user.getUsername());

        if (newUser == null) {
            for (UserRole role : roles) {
                userRoleRepository.save(role);
            }
            user.getUserRoles().addAll(roles);
            return userRepository.save(user);
        }
        LOG.warn("User {} already exists!", newUser.getUsername());
        return null;
    }

    @Transactional
    @Override
    public User findUserByApiKey(String apiKey) {
        if (apiKey != null) {

            User user = userRepository.findByApiToken(apiKey);
            if (user == null) {
                LOG.warn("Username with apikey: {} not found!", apiKey);
                throw new UsernameNotFoundException("Apikey " + apiKey + " not found.");
            }

            return user;
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Iterable<User> all = userRepository.findAll();
        all.forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + id + " not found.");
        }
        return user;
    }
}
