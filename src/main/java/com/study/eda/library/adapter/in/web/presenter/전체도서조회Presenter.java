package com.study.eda.library.adapter.in.web.presenter;

import org.springframework.stereotype.Component;

import com.study.eda.library.adapter.in.web.viewmodel.BookDTO;
import com.study.eda.library.adapter.in.web.viewmodel.전체도서조회ViewModel;
import com.study.eda.library.application.port.in.전체도서조회Usecase;
import com.study.eda.shared.in.web.presenter.Presenter;

@Component
public class 전체도서조회Presenter implements Presenter<전체도서조회Usecase.전체도서조회DTO, 전체도서조회ViewModel> {
	@Override
	public 전체도서조회ViewModel present(전체도서조회Usecase.전체도서조회DTO source) {
		final var dtoList = source.books().stream()
			.map(book -> new BookDTO(book.getId().value(), book.getTitle(), book.getStatus().toString()))
			.toList();
		return new 전체도서조회ViewModel(dtoList);
	}
}
