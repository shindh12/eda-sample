package com.study.eda.library.domain.book;

import org.springframework.lang.NonNull;

import lombok.Getter;

@Getter
public class Book {
	@NonNull
	private BookId id;
	@NonNull
	private String title;
	@NonNull
	private BookStatus status;

	public Book(BookId id, String title) {
		this.id = id;
		this.title = title;
		this.status = BookStatus.AVAILABLE;
	}

	public void rented() {
		this.status = BookStatus.RENTED;
	}

	public void returned() {
		this.status = BookStatus.AVAILABLE;
	}
}
