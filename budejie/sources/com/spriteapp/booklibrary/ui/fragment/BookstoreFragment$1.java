package com.spriteapp.booklibrary.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

class BookstoreFragment$1 implements OnRefreshListener {
    final /* synthetic */ BookstoreFragment this$0;

    BookstoreFragment$1(BookstoreFragment bookstoreFragment) {
        this.this$0 = bookstoreFragment;
    }

    public void onRefresh() {
        BookstoreFragment.access$000(this.this$0).loadUrl("http://s.hxdrive.net/book_store");
    }
}
