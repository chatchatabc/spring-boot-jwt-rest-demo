package com.chatchatabc.store.book.impl.domain.service;

import com.chatchatabc.store.book.BookSupportBaseTest;
import com.chatchatabc.store.book.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PodServiceImplTest extends BookSupportBaseTest {
    @Autowired
    private BookServiceImpl podService;
    @Autowired
    private BookRepository podRepository;


}
