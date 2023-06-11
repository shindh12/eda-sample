package com.study.eda.library.domain.rental;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RentalStatus {
	NORMAL(DefaultRentalStatusStrategy::new),
	DUE_DATE(DueDateRentalStatusStrategy::new), // 반납 예정
	RETURN_DATE(ReturnDateRentalStatusStrategy::new), // 반납당일
	OVERDUE(OverdueRentalStatusStrategy::new); // 연체
	private final Supplier<RentalStatusStrategy> strategy;

	public static RentalStatus statusFrom(LocalDate returnDate) {
		return Arrays.stream(RentalStatus.values())
			.filter(status -> status.strategy.get().is(returnDate))
			.findFirst()
			.orElseGet(() -> NORMAL);
	}
}
