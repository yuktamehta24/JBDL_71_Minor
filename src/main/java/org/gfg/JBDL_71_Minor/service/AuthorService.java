package org.gfg.JBDL_71_Minor.service;

import org.gfg.JBDL_71_Minor.model.Author;
import org.gfg.JBDL_71_Minor.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
