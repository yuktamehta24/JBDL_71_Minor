package org.gfg.JBDL_71_Minor.mapper;

import lombok.experimental.UtilityClass;
import org.gfg.JBDL_71_Minor.dto.AddBookRequest;
import org.gfg.JBDL_71_Minor.model.Author;

@UtilityClass
public class AuthorMapper {

    public Author mapToAuthor(AddBookRequest addBookRequest) {
        return Author.builder()
                .name(addBookRequest.getAuthorName())
                .email(addBookRequest.getAuthorEmail())
                .build();
    }
}
