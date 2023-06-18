package com.study.eda.library.domain.book;

import static com.study.eda.library.FixtureMonkeySupport.*;

import java.util.List;

import net.jqwik.api.Arbitraries;

public enum BookFixture {
	INSTANCE;

	public Book book() {
		return DEFAULT_FIXTURE.giveMeOne(Book.class);
	}

	public List<Book> books() {
		return DEFAULT_FIXTURE.giveMeBuilder(Book.class)
			.set("id", DEFAULT_FIXTURE.giveMeBuilder(BookId.class))
			.set("title", Arbitraries.strings().alpha())
			.sampleList(10);
	}

	public BookId bookId() {
		return DEFAULT_FIXTURE.giveMeOne(BookId.class);
	}

}
