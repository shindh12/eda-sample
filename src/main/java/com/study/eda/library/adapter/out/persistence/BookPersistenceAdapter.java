package com.study.eda.library.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.eda.library.application.port.out.도서조회Port;
import com.study.eda.library.domain.book.Book;
import com.study.eda.library.domain.book.BookId;

@Repository
public class BookPersistenceAdapter implements 도서조회Port {
	@Override
	public List<Book> getAllBooks() {
		return List.of(new Book(new BookId(1L), "도서"));
	}
}
