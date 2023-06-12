package com.study.eda.library.application.port.in;

import com.study.eda.library.domain.rental.RentalId;

public interface 도서대여연장Usecase {
	void renew(도서대여연장Command command);

	record 도서대여연장Command(RentalId rentalId) {
	}
}
