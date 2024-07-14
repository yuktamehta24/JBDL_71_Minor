package org.gfg.JBDL_71_Minor.mapper;

import lombok.experimental.UtilityClass;
import org.gfg.JBDL_71_Minor.dto.AddUserRequest;
import org.gfg.JBDL_71_Minor.enums.UserStatus;
import org.gfg.JBDL_71_Minor.model.User;

@UtilityClass
public class UserMapper {

    public User mapToUser(AddUserRequest userRequest) {
        return User.builder()
                .phoneNo(userRequest.getPhoneNo())
                .name(userRequest.getUserName())
                .email(userRequest.getEmail())
                .address(userRequest.getAddress())
                .password(userRequest.getPassword())
                .userStatus(UserStatus.ACTIVE).build();
    }
}
