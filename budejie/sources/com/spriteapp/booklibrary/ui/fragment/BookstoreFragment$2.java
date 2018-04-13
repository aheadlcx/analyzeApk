package com.spriteapp.booklibrary.ui.fragment;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

class BookstoreFragment$2 extends WebChromeClient {
    final /* synthetic */ BookstoreFragment this$0;

    BookstoreFragment$2(BookstoreFragment bookstoreFragment) {
        this.this$0 = bookstoreFragment;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (i == 100 && BookstoreFragment.access$100(this.this$0).isRefreshing()) {
            BookstoreFragment.access$100(this.this$0).setRefreshing(false);
        }
    }
}
