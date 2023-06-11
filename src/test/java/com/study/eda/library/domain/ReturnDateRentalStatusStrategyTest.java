package com.study.eda.library.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.eda.library.domain.rental.ReturnDateRentalStatusStrategy;

public class ReturnDateRentalStatusStrategyTest {

	@DisplayName("반납당일 상태일 경우 - 반납일이 오늘인 케이스")
	@Test
	void is_shouldBeTrue_whenTodayIsReturnDate() {
		// given
		final var strategy = new ReturnDateRentalStatusStrategy();
		final var returnDate = LocalDate.now();

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isTrue();
	}

	@DisplayName("반납당일 상태가 아닐 경우 - 반납일이 오늘이 아닌 케이스")
	@Test
	void is_shouldBeFalse_whenTodayIsNotReturnDate() {
		// given
		final var strategy = new ReturnDateRentalStatusStrategy();
		final var returnDate = LocalDate.now().minusDays(1L);

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isFalse();
	}
}
