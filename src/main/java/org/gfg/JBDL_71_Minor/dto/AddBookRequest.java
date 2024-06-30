package org.gfg.JBDL_71_Minor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.gfg.JBDL_71_Minor.enums.BookType;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequest {

    @NotBlank(message = "Book title should not be blank")
    String bookTitle;

    @NotBlank(message = "Book no should not be blank")
    String bookNo;

    @Positive(message = "Security amount should not be negative")
    int securityAmount;

    @NotNull(message = "Book type cannot be null")
    BookType bookType;

    @NotBlank(message = "Author name should not be blank")
    String authorName;

    @NotBlank(message = "Author email should not be blank")
    String authorEmail;
}
