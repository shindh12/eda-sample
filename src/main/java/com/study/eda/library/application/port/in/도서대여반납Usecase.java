package com.study.eda.library.application.port.in;

import com.study.eda.library.domain.rental.RentalId;

public interface 도서대여반납Usecase {
	void returnBook(도서대여반납Command command);

	record 도서대여반납Command(RentalId rentalId) {
	}
}
