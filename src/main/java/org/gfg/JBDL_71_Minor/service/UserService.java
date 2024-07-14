package org.gfg.JBDL_71_Minor.service;

import org.gfg.JBDL_71_Minor.dto.AddUserRequest;
import org.gfg.JBDL_71_Minor.enums.UserType;
import org.gfg.JBDL_71_Minor.mapper.UserMapper;
import org.gfg.JBDL_71_Minor.model.User;
import org.gfg.JBDL_71_Minor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public User addStudent(AddUserRequest addUserRequest) {
        User user = UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.STUDENT);
        user.setAuthorities("STUDENT");
        return userRepository.save(user);
    }

    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username.concat(" does not exist"));
    }

    public User addAdmin(AddUserRequest addUserRequest) {
        User user = UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.ADMIN);
        user.setAuthorities("ADMIN");
        return userRepository.save(user);
    }
}
