package com.spriteapp.booklibrary.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;

class BookShelfAdapter$4 implements OnClickListener {
    final /* synthetic */ BookShelfAdapter this$0;
    final /* synthetic */ BookDetailResponse val$detail;
    final /* synthetic */ BookShelfAdapter$ShelfViewHolder val$holder;

    BookShelfAdapter$4(BookShelfAdapter bookShelfAdapter, BookDetailResponse bookDetailResponse, BookShelfAdapter$ShelfViewHolder bookShelfAdapter$ShelfViewHolder) {
        this.this$0 = bookShelfAdapter;
        this.val$detail = bookDetailResponse;
        this.val$holder = bookShelfAdapter$ShelfViewHolder;
    }

    public void onClick(View view) {
        if (BookShelfAdapter.access$800(this.this$0) != null) {
            BookShelfAdapter.access$800(this.this$0).showDeleteDialog(this.val$detail.getBook_id(), this.val$holder.getLayoutPosition());
        }
    }
}
