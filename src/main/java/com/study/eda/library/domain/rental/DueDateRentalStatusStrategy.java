package com.study.eda.library.domain.rental;

import java.time.LocalDate;

public class DueDateRentalStatusStrategy implements RentalStatusStrategy {
	@Override
	public boolean is(LocalDate returnDate) {
		final var now = LocalDate.now();
		// 반납 3일전 < 오늘 < 반납일
		return returnDate.minusDays(3).isBefore(now) && now.isBefore(returnDate);
	}
}
