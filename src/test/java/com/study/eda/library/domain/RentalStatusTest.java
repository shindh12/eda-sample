package com.study.eda.library.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.eda.library.domain.rental.RentalStatus;

public class RentalStatusTest {

	@DisplayName("연체 상태일 경우 - 반납일 < 오늘")
	@Test
	void is_shouldBeOverdue_whenReturnDateIsBeforeToday() {
		// given
		final var returnDate = LocalDate.now().minusDays(1L);

		// when
		final var actual = RentalStatus.statusFrom(returnDate);

		// then
		assertThat(actual).isEqualTo(RentalStatus.OVERDUE);
	}

	@DisplayName("반납당일 상태일 경우 - 반납일 = 오늘")
	@Test
	void is_shouldBeReturnDate_whenReturnDateIsToday() {
		// given
		final var returnDate = LocalDate.now();

		// when
		final var actual = RentalStatus.statusFrom(returnDate);

		// then
		assertThat(actual).isEqualTo(RentalStatus.RETURN_DATE);
	}

	@DisplayName("반납예정 상태일 경우 - 반납 3일전 < 오늘 < 반납일")
	@Test
	void is_shouldBeDueDate_whenTodayIs2DaysBeforeReturnDate() {
		// given
		final var returnDate = LocalDate.now().plusDays(2L);

		// when
		final var actual = RentalStatus.statusFrom(returnDate);

		// then
		assertThat(actual).isEqualTo(RentalStatus.DUE_DATE);
	}

	@DisplayName("기본 상태일 경우 - 반납 6일전")
	@Test
	void is_shouldBeDueDate_whenTodayIs6DaysBeforeReturnDate() {
		// given
		final var returnDate = LocalDate.now().plusDays(6L);

		// when
		final var actual = RentalStatus.statusFrom(returnDate);

		// then
		assertThat(actual).isEqualTo(RentalStatus.NORMAL);
	}

}
