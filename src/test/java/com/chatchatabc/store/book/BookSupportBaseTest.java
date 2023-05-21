package com.chatchatabc.store.book;

import com.chatchatabc.store.BookStoreBaseTest;
import com.chatchatabc.store.book.domain.model.Book;
import com.chatchatabc.store.book.domain.repository.BookRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.springframework.beans.factory.annotation.Autowired;

@DataSet("db/datasets/book.xml")
public abstract class BookSupportBaseTest extends BookStoreBaseTest {
    @Autowired
    protected BookRepository bookRepository;

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
