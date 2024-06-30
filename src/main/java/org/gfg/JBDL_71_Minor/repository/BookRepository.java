package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
