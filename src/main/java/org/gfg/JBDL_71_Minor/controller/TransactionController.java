package org.gfg.JBDL_71_Minor.controller;

import jakarta.validation.Valid;
import org.gfg.JBDL_71_Minor.dto.TransactionRequest;
import org.gfg.JBDL_71_Minor.exceptions.TransactionException;
import org.gfg.JBDL_71_Minor.model.Transaction;
import org.gfg.JBDL_71_Minor.model.User;
import org.gfg.JBDL_71_Minor.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestBody @Valid TransactionRequest request) throws TransactionException {
//        Transaction createdTransaction = null;
//        try {
//            createdTransaction = transactionService.issueBook(request);
//        } catch (TransactionException transactionException) {
//            return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(createdTransaction, HttpStatus.OK);

        //fetch userdetails from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (!user.getEmail().equals(request.getUserEmail())) {
            throw new TransactionException("You cannot issue the book to some other user");
        }

        Transaction createdTransaction = transactionService.issueBook(request);
        return new ResponseEntity<>(createdTransaction, HttpStatus.OK);
    }

    @PutMapping("/return")
    public ResponseEntity<Integer> returnBook(@RequestBody @Valid TransactionRequest transactionRequest) throws TransactionException {
        Integer settlementAmount = transactionService.returnBook(transactionRequest);
        return new ResponseEntity<>(settlementAmount, HttpStatus.OK);
    }
}
