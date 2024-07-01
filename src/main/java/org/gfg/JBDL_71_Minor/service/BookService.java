package org.gfg.JBDL_71_Minor.service;

import lombok.extern.slf4j.Slf4j;
import org.gfg.JBDL_71_Minor.dto.AddBookRequest;
import org.gfg.JBDL_71_Minor.enums.BookType;
import org.gfg.JBDL_71_Minor.mapper.AuthorMapper;
import org.gfg.JBDL_71_Minor.mapper.BookMapper;
import org.gfg.JBDL_71_Minor.model.Author;
import org.gfg.JBDL_71_Minor.model.Book;
import org.gfg.JBDL_71_Minor.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

//    @Autowired
//    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    public Book addBook(AddBookRequest bookRequest) {

        Author authorFromDB = authorService.getAuthorByEmail(bookRequest.getAuthorEmail());
        if (authorFromDB == null) {
            authorFromDB = AuthorMapper.mapToAuthor(bookRequest);
            authorFromDB = authorService.addAuthor(authorFromDB);
        }
        Book book = BookMapper.mapToBook(bookRequest);
        book.setAuthor(authorFromDB);
        return bookRepository.save(book);

    }

    public void updateBookMetadata(Book book) {
        bookRepository.save(book);
    }

    public Book getBookByBookNo(String bookNo) {
        return bookRepository.findBookByBookNo(bookNo);
    }

    public List<Book> getBooks(String bookTitle, BookType bookType) {
          log.info("i am in book service");
        List<Book> res =  bookRepository.findBooksByFilters(bookTitle, bookType);
        log.info("i am returning from book service");
        return res;
    }
}
