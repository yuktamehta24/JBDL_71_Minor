package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    public void setup() {
        Author author = Author.builder().name("yukta").email("abc@gmail.com").build();
        authorRepository.save(author);

    }

    @Test
    public void fetchAuthorByEmailByNativeQuery_ValidAuthor_ReturnsData() {
        Author returnedAuthor = authorRepository.fetchAuthorByEmailByNativeQuery("abc@gmail.com");
        Assertions.assertEquals("yukta", returnedAuthor.getName());
    }

    @Test
    public void fetchAuthorByEmailByNativeQuery_InvalidAuthor_ReturnsNull() {
        Assertions.assertNull(authorRepository
                .fetchAuthorByEmailByNativeQuery("def@gmail.com"));
    }
}
