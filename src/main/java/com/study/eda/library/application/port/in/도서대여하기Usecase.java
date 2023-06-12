package com.study.eda.library.application.port.in;

import com.study.eda.library.domain.book.BookId;
import com.study.eda.library.domain.user.UserId;

public interface 도서대여하기Usecase {

	void rent(도서대여Command command);

	record 도서대여Command(UserId renterId, BookId rentedBookId) {
	}
}
