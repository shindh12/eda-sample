package com.study.eda.library.domain.rental;

import java.time.LocalDate;

public class ReturnDateRentalStatusStrategy implements RentalStatusStrategy {
	@Override
	public boolean is(LocalDate returnDate) {
		return LocalDate.now().isEqual(returnDate);
	}
}
