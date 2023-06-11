package com.study.eda.library.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.eda.library.domain.rental.DueDateRentalStatusStrategy;

public class DueDateRentalStatusStrategyTest {

	@DisplayName("반납예정 상태일 경우 - 반납일이 내일인 케이스")
	@Test
	void is_shouldBeTrue_whenTodayIsAfterReturnDate() {
		// given
		final var strategy = new DueDateRentalStatusStrategy();
		final var returnDate = LocalDate.now().plusDays(1L);

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isTrue();
	}

	@DisplayName("반납예정 상태가 아닐 경우 - 반납일이 오늘인 케이스")
	@Test
	void is_shouldBeFalse_whenReturnDateIsToday() {
		// given
		final var strategy = new DueDateRentalStatusStrategy();
		final var returnDate = LocalDate.now();

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isFalse();
	}

	@DisplayName("반납예정 상태가 아닐 경우 - 반납일이 3일 이상 남은 케이스")
	@Test
	void is_shouldBeFalse_whenReturnDateIsLeftOver3Days() {
		// given
		final var strategy = new DueDateRentalStatusStrategy();
		final var returnDate = LocalDate.now().plusDays(3L);

		// when
		final var actual = strategy.is(returnDate);

		// then
		assertThat(actual).isFalse();
	}
}
