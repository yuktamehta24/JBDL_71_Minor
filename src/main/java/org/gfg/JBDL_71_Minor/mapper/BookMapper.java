package org.gfg.JBDL_71_Minor.mapper;

import lombok.experimental.UtilityClass;
import org.gfg.JBDL_71_Minor.dto.AddBookRequest;
import org.gfg.JBDL_71_Minor.model.Book;

@UtilityClass
public class BookMapper {

    public Book mapToBook(AddBookRequest addBookRequest) {
        return Book.builder()
                .bookNo(addBookRequest.getBookNo())
                .bookTitle(addBookRequest.getBookTitle())
                .bookType(addBookRequest.getBookType())
                .securityAmount(addBookRequest.getSecurityAmount()).build();
    }
}
