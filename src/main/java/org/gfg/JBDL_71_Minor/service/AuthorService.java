package org.gfg.JBDL_71_Minor.service;

import lombok.extern.slf4j.Slf4j;
import org.gfg.JBDL_71_Minor.model.Author;
import org.gfg.JBDL_71_Minor.repository.AuthorRepository;
import org.gfg.JBDL_71_Minor.repository.RedisDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    RedisDataRepository redisDataRepository;

    public Author getAuthorByEmail(String email) {
        Author author = redisDataRepository.getAuthorByEmail(email);
        if (author != null) {
            log.info("Author found from redis");
            return author;
        }

        author = authorRepository.fetchAuthorByEmailByNativeQuery(email);

        if (author != null) {
            log.info("Author found from table");
            redisDataRepository.saveAuthorToRedis(author);
            log.info("Author saved in redis");
        }
        return author;
    }

    public Author addAuthor(Author author) {
        Author savedAuthor =  authorRepository.save(author);
        redisDataRepository.saveAuthorToRedis(savedAuthor);
        log.info("Author saved in redis");
        return savedAuthor;
    }
}
