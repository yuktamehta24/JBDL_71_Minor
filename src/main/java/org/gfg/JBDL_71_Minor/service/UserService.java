package org.gfg.JBDL_71_Minor.service;

import org.gfg.JBDL_71_Minor.dto.AddUserRequest;
import org.gfg.JBDL_71_Minor.enums.UserType;
import org.gfg.JBDL_71_Minor.mapper.UserMapper;
import org.gfg.JBDL_71_Minor.model.User;
import org.gfg.JBDL_71_Minor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addStudent(AddUserRequest addUserRequest) {
        User user = UserMapper.mapToUser(addUserRequest);
        user.setUserType(UserType.STUDENT);
        return userRepository.save(user);
    }

    public User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
