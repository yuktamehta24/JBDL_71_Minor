package org.gfg.JBDL_71_Minor.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.gfg.JBDL_71_Minor.dto.TransactionRequest;
import org.gfg.JBDL_71_Minor.enums.TransactionStatus;
import org.gfg.JBDL_71_Minor.enums.UserStatus;
import org.gfg.JBDL_71_Minor.enums.UserType;
import org.gfg.JBDL_71_Minor.exceptions.TransactionException;
import org.gfg.JBDL_71_Minor.model.Book;
import org.gfg.JBDL_71_Minor.model.Transaction;
import org.gfg.JBDL_71_Minor.model.User;
import org.gfg.JBDL_71_Minor.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionService {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${book.maximum.validity}")
    int validDays;

    @Value("${book.fine.per.day}")
    int finePerDay;

    public Transaction issueBook(TransactionRequest request) throws TransactionException{
        User user = fetchUser(request);
        if (user.getUserStatus() == UserStatus.BLOCKED) {
            throw new TransactionException("User is blocked, book cannot be issued");
        }
        Book book = fetchBook(request);
        if(book.getUser() != null) {
            throw new TransactionException("Book is already issued to other user");
        }
        return issueBook(user, book);
    }

    @Transactional
    protected Transaction issueBook(User user, Book book) {
        Transaction transaction = Transaction.builder()
                .book(book)
                .user(user)
                .transactionId(UUID.randomUUID().toString().substring(0, 30))
                .settlementAmount(-book.getSecurityAmount())
                .transactionStatus(TransactionStatus.ISSUED)
                .build();

        transaction = transactionRepository.save(transaction);
        book.setUser(user);
        bookService.updateBookMetadata(book);
        return transaction;
    }

    public User fetchUser(TransactionRequest request) throws TransactionException{
        User user = userService.fetchUserByEmail(request.getUserEmail());
        if(user == null) {
            throw new TransactionException("User does not exist in the library");
        }

        if (user.getUserType() != UserType.STUDENT) {
            throw new TransactionException("User is not of type Student");
        }

        return user;
    }


    private Book fetchBook(TransactionRequest request) throws TransactionException{
        Book book = bookService.getBookByBookNo(request.getBookNo());

        if(book == null) {
            throw new TransactionException("Book does not exist in the library");
        }
        return book;
    }

    public Integer returnBook(TransactionRequest request) throws TransactionException {
        User user = fetchUser(request);
        Book book = fetchBook(request);

        if(book.getUser() != user) {
            throw new TransactionException("Book is issued to some other user");
        }

        Transaction transaction = transactionRepository.findByUserEmailAndBookBookNo(request.getUserEmail(),
                request.getBookNo());

        return returnBook(transaction, book);
    }

    @Transactional
    protected Integer returnBook(Transaction transaction, Book book) {

        int amount = calculateFine(transaction);
        transactionRepository.save(transaction);
        book.setUser(null);
        bookService.updateBookMetadata(book);

        return amount;
    }
    public int calculateFine(Transaction transaction) {
        long issueDateInTime = transaction.getCreatedOn().getTime();
        long currenttime = System.currentTimeMillis();

        long timedifference = currenttime - issueDateInTime; //milliseconds
        long days = TimeUnit.MILLISECONDS.toDays(timedifference);

        int amount = 0;
        if (days > validDays) {
            //some fine is there
            int fine =  (int) (days - validDays) * finePerDay;

//            if (fine > Math.abs(transaction.getSettlementAmount())) {
//                //take some money
//                amount = fine - Math.abs(transaction.getSettlementAmount()); //100
//                transaction.setSettlementAmount(-fine); //300
//            } else {
//                // return some money
//                amount = fine - Math.abs(transaction.getSettlementAmount()); //-50
//                transaction.setSettlementAmount(-fine);
//            }
            amount = fine - Math.abs(transaction.getSettlementAmount());
            transaction.setSettlementAmount(-fine);
            transaction.setTransactionStatus(TransactionStatus.FINED);
        } else {
            transaction.setTransactionStatus(TransactionStatus.RETURNED);
            amount = transaction.getSettlementAmount();
            transaction.setSettlementAmount(0);
        }
        return amount;
    }
}
