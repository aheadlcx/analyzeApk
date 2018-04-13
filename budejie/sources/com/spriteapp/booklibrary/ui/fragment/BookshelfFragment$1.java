package com.spriteapp.booklibrary.ui.fragment;

import com.spriteapp.booklibrary.listener.DeleteBookListener;
import com.spriteapp.booklibrary.util.DialogUtil;

class BookshelfFragment$1 implements DeleteBookListener {
    final /* synthetic */ BookshelfFragment this$0;

    BookshelfFragment$1(BookshelfFragment bookshelfFragment) {
        this.this$0 = bookshelfFragment;
    }

    public void deleteBook() {
        BookshelfFragment.access$100(this.this$0).deleteBook(BookshelfFragment.access$000(this.this$0));
    }

    public void showDeleteDialog(int i, int i2) {
        BookshelfFragment.access$202(this.this$0, i2);
        BookshelfFragment.access$002(this.this$0, i);
        if (BookshelfFragment.access$300(this.this$0) == null) {
            BookshelfFragment.access$302(this.this$0, DialogUtil.getDeleteBookDialog(BookshelfFragment.access$400(this.this$0)));
        }
        BookshelfFragment.access$300(this.this$0).show();
    }
}
