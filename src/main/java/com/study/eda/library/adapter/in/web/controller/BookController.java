package com.study.eda.library.adapter.in.web.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.eda.library.adapter.in.web.viewmodel.전체도서조회ViewModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Books", description = "Book API")
@RequestMapping("/books")
public interface BookController {
	@GetMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "전체 도서 조회 성공", content = @Content(schema = @Schema(implementation = 전체도서조회ViewModel.class))),
		@ApiResponse(responseCode = "500", description = "조회 실패", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))})
	@Operation(summary = "전체 도서 조회", description = "모든 도서 목록을 조회합니다.")
	전체도서조회ViewModel getAllBooks();
}
