package com.chatchatabc.store.book.impl.domain.service;

import com.chatchatabc.store.book.domain.model.Book;
import com.chatchatabc.store.book.domain.repository.BookRepository;
import com.chatchatabc.store.book.domain.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }
}
