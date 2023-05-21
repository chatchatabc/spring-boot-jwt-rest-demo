package com.chatchatabc.store.book.application.rest;

import com.chatchatabc.store.book.domain.model.Book;
import com.chatchatabc.store.book.domain.repository.BookRepository;
import com.chatchatabc.store.operator.application.common.OperatorPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public Optional<Book> show(OperatorPrincipal principal, @PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }
}
