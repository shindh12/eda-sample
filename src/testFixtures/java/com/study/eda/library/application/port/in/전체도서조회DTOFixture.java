package com.study.eda.library.application.port.in;

import static com.study.eda.library.FixtureMonkeySupport.*;

import com.study.eda.library.domain.book.BookFixture;

public enum 전체도서조회DTOFixture {
	INSTANCE;

	public 전체도서조회Usecase.전체도서조회DTO 전체도서조회Dto() {
		return DEFAULT_FIXTURE.giveMeBuilder(전체도서조회Usecase.전체도서조회DTO.class)
			.set("books", BookFixture.INSTANCE.books())
			.sample();
	}
}
