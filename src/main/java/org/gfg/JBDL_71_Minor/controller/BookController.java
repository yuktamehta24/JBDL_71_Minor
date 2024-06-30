package org.gfg.JBDL_71_Minor.controller;

import jakarta.validation.Valid;
import org.gfg.JBDL_71_Minor.dto.AddBookRequest;
import org.gfg.JBDL_71_Minor.model.Book;
import org.gfg.JBDL_71_Minor.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid AddBookRequest bookRequest) {
//        AddBookRequest bookRequest1 = new AddBookRequest();
        //validations
//        if (StringUtils.isEmpty(bookRequest.getBookNo())) {
//            //throwing some exception
//        }
        Book savedBook = bookService.addBook(bookRequest);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}
