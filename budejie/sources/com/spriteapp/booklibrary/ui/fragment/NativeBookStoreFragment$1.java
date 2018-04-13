package com.spriteapp.booklibrary.ui.fragment;

import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.widget.xrecyclerview.XRecyclerView$LoadingListener;

class NativeBookStoreFragment$1 implements XRecyclerView$LoadingListener {
    final /* synthetic */ NativeBookStoreFragment this$0;

    NativeBookStoreFragment$1(NativeBookStoreFragment nativeBookStoreFragment) {
        this.this$0 = nativeBookStoreFragment;
    }

    public void onRefresh() {
        NativeBookStoreFragment.access$000(this.this$0).getUserInfo();
        if (CollectionUtil.isEmpty(NativeBookStoreFragment.access$100(this.this$0))) {
            NativeBookStoreFragment.access$000(this.this$0).getBookShelf();
        }
        NativeBookStoreFragment.access$000(this.this$0).getBookStoreData();
    }

    public void onLoadMore() {
    }
}
