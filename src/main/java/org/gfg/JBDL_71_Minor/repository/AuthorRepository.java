package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query(value = "select * from Author where email = :email", nativeQuery = true)
    Author fetchAuthorByEmailByNativeQuery(String email);

    Author findByEmail(String email);
}
