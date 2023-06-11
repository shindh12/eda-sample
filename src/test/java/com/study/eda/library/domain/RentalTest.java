package com.study.eda.library.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.study.eda.library.domain.rental.RentalFixture;

public class RentalTest {

	@Test
	void getReturnDate() {
		// given
		final var rentalDate = LocalDate.now().minusDays(7L);
		final var rental = RentalFixture.INSTANCE.rental(rentalDate);

		// when
		final var actual = rental.getReturnDate();

		// then
		assertThat(actual).isEqualTo(LocalDate.now());
	}

	@Test
	void renew() {
		// given
		final var rental = RentalFixture.INSTANCE.rental();

		// when
		rental.renew();
		rental.renew();
		rental.renew();

		// then
		assertThat(rental.canRenew()).isFalse();
		assertThat(rental.getRenewalCount()).isEqualTo(3L);
	}

	@Test
	void renew_shouldBeThrowException_whenRenewalCountIsGreaterThanThree() {
		// given
		final var rental = RentalFixture.INSTANCE.rental();
		rental.renew();
		rental.renew();
		rental.renew();

		// when
		// then
		assertThat(rental.canRenew()).isFalse();
		assertThatThrownBy(() -> rental.renew()).isInstanceOf(RuntimeException.class);
	}
}
