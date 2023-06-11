package com.study.eda.library.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.eda.library.domain.rental.OverdueRentalStatusStrategy;

public class OverdueRentalStatusStrategyTest {

	@DisplayName("연체된 상태일 경우 - 반납일이 하루 전날인 케이스")
	@Test
	void is_shouldBeTrue_whenTodayIsAfterReturnDate() {
		// given
		final var strategy = new OverdueRentalStatusStrategy();
		final var returnDate = LocalDate.now().minusDays(1L);

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isTrue();
	}

	@DisplayName("연체된 상태가 아닐 경우 - 반납일이 오늘인 케이스")
	@Test
	void is_shouldBeFalse_whenReturnDateIsToday() {
		// given
		final var strategy = new OverdueRentalStatusStrategy();
		final var returnDate = LocalDate.now();

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isFalse();
	}
}
