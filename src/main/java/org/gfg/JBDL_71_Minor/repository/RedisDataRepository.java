package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDataRepository {

    @Autowired
    RedisTemplate redisTemplate;

    private String AUTHOR_KEY = "author:";


    public void saveAuthorToRedis(Author author) {
        redisTemplate.opsForValue().set(AUTHOR_KEY.concat(author.getEmail()), author);
    }

    public Author getAuthorByEmail(String email) {
        return (Author) redisTemplate.opsForValue().get(AUTHOR_KEY.concat(email));
    }

}
