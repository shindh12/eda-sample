package com.study.eda.library.adapter.in.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.study.eda.library.adapter.in.web.presenter.전체도서조회Presenter;
import com.study.eda.library.adapter.in.web.viewmodel.전체도서조회ViewModel;
import com.study.eda.library.application.port.in.전체도서조회Usecase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {
	private final 전체도서조회Usecase usecase;
	private final 전체도서조회Presenter presenter;

	@Override
	public 전체도서조회ViewModel getAllBooks() {
		return presenter.present(usecase.getAllBooks());
	}
}
