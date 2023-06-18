package com.study.eda.library.domain.rental;

import static com.study.eda.library.FixtureMonkeySupport.*;

import java.time.LocalDate;

public enum RentalFixture {
	INSTANCE;

	public Rental rental() {
		return DEFAULT_FIXTURE.giveMeOne(Rental.class);
	}

	public Rental rental(LocalDate rentalDate) {
		return DEFAULT_FIXTURE.giveMeBuilder(Rental.class)
			.set("rentalDate", rentalDate)
			.sample();
	}

}
