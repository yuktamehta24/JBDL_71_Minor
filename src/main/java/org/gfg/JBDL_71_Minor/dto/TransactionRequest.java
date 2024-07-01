package org.gfg.JBDL_71_Minor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {

    @NotBlank(message = "Book no is mandatory")
    String bookNo;

    @NotBlank(message = "User email is mandatory")
    String userEmail;
}
