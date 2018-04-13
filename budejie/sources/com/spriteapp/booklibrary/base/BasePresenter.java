package com.spriteapp.booklibrary.base;

public interface BasePresenter<T> {
    void attachView(T t);

    void detachView();
}
