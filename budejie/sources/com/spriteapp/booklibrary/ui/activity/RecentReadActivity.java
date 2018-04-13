package com.spriteapp.booklibrary.ui.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.spriteapp.booklibrary.a;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.d.e;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.ui.adapter.BookShelfAdapter;
import com.spriteapp.booklibrary.ui.fragment.BookshelfFragment;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.widget.recyclerview.SpaceItemDecoration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecentReadActivity extends TitleActivity {
    private static final int HORIZONTAL_SPACE = 2;
    private static final int SHELF_SPAN_COUNT = 3;
    private static final int VERTICAL_SPACE = 5;
    private BookShelfAdapter mAdapter;
    private e mBookDb;
    private ImageView mEmptyImageView;
    private List<BookDetailResponse> mReadList;
    private RecyclerView mRecyclerView;

    public void initData() {
        setTitle(getString(f.book_reader_recent_read_text));
        this.mBookDb = new e(this);
        initRecyclerView();
        setAdapter();
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().add(d.book_shelf_layout, new BookshelfFragment()).commit();
    }

    protected void onResume() {
        super.onResume();
        queryRecentBook();
    }

    private void queryRecentBook() {
        Collection c = this.mBookDb.c();
        if (CollectionUtil.isEmpty(c)) {
            this.mEmptyImageView.setVisibility(0);
            return;
        }
        this.mEmptyImageView.setVisibility(8);
        if (this.mReadList == null) {
            this.mReadList = new ArrayList();
        }
        this.mReadList.clear();
        this.mReadList.addAll(c);
        setAdapter();
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(a.e.book_reader_activity_recent_read, null), -1, -1);
    }

    public void findViewId() {
        super.findViewId();
        this.mRecyclerView = (RecyclerView) findViewById(d.book_reader_recycler_view);
        this.mEmptyImageView = (ImageView) findViewById(d.empty_image_view);
    }

    private void initRecyclerView() {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.mRecyclerView.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dpToPxInt(2.0f), ScreenUtil.dpToPxInt(5.0f)));
    }

    private void setAdapter() {
        if (this.mReadList == null) {
            this.mReadList = new ArrayList();
        }
        if (this.mAdapter == null) {
            this.mAdapter = new BookShelfAdapter(this, this.mReadList, 3, 2, true);
            this.mRecyclerView.setAdapter(this.mAdapter);
            return;
        }
        this.mAdapter.notifyDataSetChanged();
    }
}
