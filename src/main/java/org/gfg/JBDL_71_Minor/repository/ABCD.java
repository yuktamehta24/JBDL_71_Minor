package org.gfg.JBDL_71_Minor.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.gfg.JBDL_71_Minor.enums.BookType;
import org.gfg.JBDL_71_Minor.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ABCD implements CustomBookRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Book> findBooksByFilters(String bookTitle, BookType bookType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (bookTitle != null && !bookTitle.isEmpty()) {
            Predicate titlePredicate = criteriaBuilder.like(bookRoot.get("bookTitle"),
                    "%" + bookTitle + "%");
            predicates.add(titlePredicate);
        }

        if (bookType != null) {
            Predicate typePredicate = criteriaBuilder.equal(bookRoot.get("bookType"),
                    bookType);
            predicates.add(typePredicate);
        }

        Predicate finalPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));

//        Predicate amount = criteriaBuilder.equal(bookRoot.get("securityAmount"),
//                100);
//
//        Predicate final2 = criteriaBuilder.and(finalPredicate, amount);

//        criteriaQuery.select(bookRoot).where(predicates.toArray(new Predicate[0]));
        criteriaQuery.select(bookRoot).where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();

    }

//    @Override
//    public List<Book> findBooksByFilters(String bookTitle, BookType bookType) {
//        return null;
//    }
}
