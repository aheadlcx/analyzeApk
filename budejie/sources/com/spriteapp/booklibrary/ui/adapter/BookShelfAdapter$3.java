package com.spriteapp.booklibrary.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;

class BookShelfAdapter$3 implements OnClickListener {
    final /* synthetic */ BookShelfAdapter this$0;
    final /* synthetic */ BookShelfAdapter$ShelfViewHolder val$holder;

    BookShelfAdapter$3(BookShelfAdapter bookShelfAdapter, BookShelfAdapter$ShelfViewHolder bookShelfAdapter$ShelfViewHolder) {
        this.this$0 = bookShelfAdapter;
        this.val$holder = bookShelfAdapter$ShelfViewHolder;
    }

    public void onClick(View view) {
        int layoutPosition = this.val$holder.getLayoutPosition();
        if (!CollectionUtil.isEmpty(BookShelfAdapter.access$300(this.this$0)) && layoutPosition < BookShelfAdapter.access$300(this.this$0).size()) {
            BookDetailResponse bookDetailResponse = (BookDetailResponse) BookShelfAdapter.access$300(this.this$0).get(layoutPosition);
            BookShelfAdapter.access$400(this.this$0).a(bookDetailResponse.getBook_id());
            BookShelfAdapter.access$500(this.this$0).a(bookDetailResponse.getBook_id());
            ActivityUtil.toReadActivity(BookShelfAdapter.access$600(this.this$0), bookDetailResponse);
            BookShelfAdapter.access$700().sendEmptyMessageDelayed(0, 1000);
        }
    }
}
