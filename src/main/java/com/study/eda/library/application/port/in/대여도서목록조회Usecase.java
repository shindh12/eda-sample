package com.study.eda.library.application.port.in;

import java.util.List;

import com.study.eda.library.domain.book.Book;
import com.study.eda.library.domain.rental.Rental;
import com.study.eda.library.domain.user.UserId;

public interface 대여도서목록조회Usecase {

	List<대여도서목록DTO> getRentalList(대여도서목록조회Query query);

	record 대여도서목록조회Query(UserId renterId) {
	}

	record 대여도서목록DTO(Rental rental, Book book) {
	}
}
