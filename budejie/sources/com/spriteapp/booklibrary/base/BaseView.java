package com.spriteapp.booklibrary.base;

import android.content.Context;

public interface BaseView<T> {
    void disMissProgress();

    Context getMyContext();

    void onError(Throwable th);

    void setData(Base<T> base);

    void showNetWorkProgress();
}
