package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.enums.BookType;
import org.gfg.JBDL_71_Minor.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, CustomBookRepository {

    Book findBookByBookNo(String bookNo);

    @Query(value = "select * from Book where :dynamic", nativeQuery = true)
    List<Book> getBooks(String dynamic); //entity manager

    List<Book> findByBookTitleAndBookType(String bookTitle, BookType bookType);
}
