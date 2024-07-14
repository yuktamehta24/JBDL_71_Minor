package org.gfg.JBDL_71_Minor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserRequest {

    String userName;

    @NotBlank(message = "User email should not be blank")
    String email;

    String phoneNo;

    String address;

    @NotBlank(message = "User password should not be blank")
    String password;

}
