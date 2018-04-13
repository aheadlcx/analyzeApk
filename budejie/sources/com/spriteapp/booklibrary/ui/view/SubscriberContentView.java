package com.spriteapp.booklibrary.ui.view;

import com.spriteapp.booklibrary.base.BaseView;
import com.spriteapp.booklibrary.model.response.BookChapterResponse;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.model.response.SubscriberContent;
import java.util.List;

public interface SubscriberContentView extends BaseView<SubscriberContent> {
    void setBookDetail(BookDetailResponse bookDetailResponse);

    void setChapter(List<BookChapterResponse> list);

    void toChannelLogin();
}
