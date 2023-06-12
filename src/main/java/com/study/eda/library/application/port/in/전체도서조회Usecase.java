package com.study.eda.library.application.port.in;

import java.util.List;

import com.study.eda.library.domain.book.Book;

public interface 전체도서조회Usecase {

	전체도서조회DTO getAllBooks();

	record 전체도서조회DTO(List<Book> books) {
	}
}
