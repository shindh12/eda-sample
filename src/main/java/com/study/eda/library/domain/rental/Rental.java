package com.study.eda.library.domain.rental;

import java.time.LocalDate;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.study.eda.library.domain.book.BookId;
import com.study.eda.library.domain.user.UserId;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Rental {
	private static final int DEFAULT_RENTAL_PERIOD_DAYS = 7;
	@Nullable
	private RentalId id;
	@NonNull
	private BookId rentedBook;
	@NonNull
	private UserId renter;
	@NonNull
	private LocalDate rentalDate;
	@NonNull
	private RentalStatus status;
	private int renewalCount;

	@Builder
	public Rental(@Nullable RentalId id, @NonNull BookId rentedBook, @NonNull UserId renter,
		@NonNull LocalDate rentalDate) {
		this.id = id;
		this.rentedBook = rentedBook;
		this.renter = renter;
		this.rentalDate = rentalDate;
		this.status = RentalStatus.NORMAL;
	}

	public void renew() {
		if (renewalCount >= 3) {
			throw new RuntimeException("연장 불가"); // fixme : Exception 처리 한번에 변경
		}
		renewalCount++;
	}

	public boolean canRenew() {
		return renewalCount < 3;
	}

	public LocalDate getReturnDate() {
		return rentalDate.plusDays(DEFAULT_RENTAL_PERIOD_DAYS * (renewalCount + 1));
	}

	public RentalStatus getStatus() {
		return RentalStatus.statusFrom(getReturnDate());
	}

}
