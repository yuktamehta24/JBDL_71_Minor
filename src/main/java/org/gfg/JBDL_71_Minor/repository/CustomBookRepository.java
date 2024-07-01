package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.enums.BookType;
import org.gfg.JBDL_71_Minor.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository {

    List<Book> findBooksByFilters(String bookTitle, BookType bookType);
}
