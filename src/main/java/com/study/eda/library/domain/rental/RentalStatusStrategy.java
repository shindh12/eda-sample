package com.study.eda.library.domain.rental;

import java.time.LocalDate;

public interface RentalStatusStrategy {
	boolean is(LocalDate returnDate);
}
