package com.study.eda.library.in.web.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import com.study.eda.library.adapter.in.web.controller.BookControllerImpl;
import com.study.eda.library.adapter.in.web.presenter.전체도서조회Presenter;
import com.study.eda.library.application.port.in.전체도서조회Usecase;
import com.study.eda.library.domain.book.BookFixture;

@WebMvcTest(BookControllerImpl.class)
@AutoConfigureMockMvc
public class BookControllerImplTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	전체도서조회Usecase usecase;
	@SpyBean
	전체도서조회Presenter presenter;

	@DisplayName("전체도서조회 - 성공")
	@Test
	void 전체도서조회() throws Exception {
		// given
		final var books = BookFixture.INSTANCE.books();
		final var dto = new 전체도서조회Usecase.전체도서조회DTO(books);

		// when
		doReturn(dto).when(usecase).getAllBooks();

		// then
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andDo(print());
	}

}
