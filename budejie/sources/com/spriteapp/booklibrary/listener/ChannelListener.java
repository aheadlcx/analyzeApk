package com.spriteapp.booklibrary.listener;

import android.content.Context;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;

public interface ChannelListener {
    void showShareDialog(Context context, BookDetailResponse bookDetailResponse, boolean z);

    void toLoginPage(Context context);
}
