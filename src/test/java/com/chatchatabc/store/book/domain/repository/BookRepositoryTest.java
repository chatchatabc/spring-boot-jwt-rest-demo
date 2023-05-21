package com.chatchatabc.store.book.domain.repository;

import com.chatchatabc.store.book.BookSupportBaseTest;
import com.chatchatabc.store.book.domain.model.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest extends BookSupportBaseTest {

    @Test
    public void testFindById() throws Exception {
        final Book book = getBook(1L);
        assertThat(book).isNotNull();
    }

}
