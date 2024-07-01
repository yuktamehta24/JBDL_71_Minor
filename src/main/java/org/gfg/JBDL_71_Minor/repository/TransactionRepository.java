package org.gfg.JBDL_71_Minor.repository;

import org.gfg.JBDL_71_Minor.model.Book;
import org.gfg.JBDL_71_Minor.model.Transaction;
import org.gfg.JBDL_71_Minor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByUserEmailAndBookBookNo(String email, String bookNo);

    Transaction findByUserAndBook(User user, Book book);
}
