package com.study.eda.library.application.port.out;

import java.util.List;

import com.study.eda.library.domain.book.Book;

public interface 도서조회Port {
	List<Book> getAllBooks();
}
