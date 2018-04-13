package com.spriteapp.booklibrary.ui.adapter;

import android.view.View;
import android.view.View.OnLongClickListener;

class BookShelfAdapter$2 implements OnLongClickListener {
    final /* synthetic */ BookShelfAdapter this$0;

    BookShelfAdapter$2(BookShelfAdapter bookShelfAdapter) {
        this.this$0 = bookShelfAdapter;
    }

    public boolean onLongClick(View view) {
        if (BookShelfAdapter.access$000(this.this$0) || BookShelfAdapter.access$100(this.this$0)) {
            return false;
        }
        if (BookShelfAdapter.access$200(this.this$0)) {
            return true;
        }
        BookShelfAdapter.access$202(this.this$0, true);
        this.this$0.notifyDataSetChanged();
        return true;
    }
}
