package com.study.eda.library.domain.book;

import lombok.Getter;

@Getter
public class Book {
	private BookId id;
	private String title;
	private BookStatus status;

	public void rented() {
		this.status = BookStatus.RENTED;
	}

	public void returned() {
		this.status = BookStatus.AVAILABLE;
	}
}
