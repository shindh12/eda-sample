package com.study.eda.shared.in.web.presenter;

public interface Presenter<T, S> {
	S present(T source);
}
