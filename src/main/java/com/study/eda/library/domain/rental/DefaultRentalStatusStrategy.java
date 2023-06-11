package com.study.eda.library.domain.rental;

import java.time.LocalDate;

public class DefaultRentalStatusStrategy implements RentalStatusStrategy {

	// 오늘 < 반납일
	@Override
	public boolean is(LocalDate returnDate) {
		return false;
	}
}
