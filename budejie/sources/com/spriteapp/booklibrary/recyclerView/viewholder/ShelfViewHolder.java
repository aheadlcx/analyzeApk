package com.spriteapp.booklibrary.recyclerView.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.e.b;
import com.spriteapp.booklibrary.model.BookList;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.recyclerView.adapter.BookStoreAdapter;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.ui.activity.HomeActivity;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.widget.recyclerview.StoreShelfItemDecoration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShelfViewHolder extends BaseViewHolder<Visitable> {
    private static final int SHELF_SHOW_COUNT = 3;
    private static final int SPACE_COUNT = 2;
    private static final int SPACE_WIDTH = 27;
    private static final int SPAN_COUNT = 3;
    private List<BookDetailResponse> bookListData;
    private final View lineView;
    private ImageView mAllBookImageView;
    private final LinearLayout mAllBookLayout;
    private TextView mAllBookTextView;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private BookStoreAdapter mStoreAdapter;
    private final View markView;
    private final TextView tagTextView;

    public ShelfViewHolder(View view, Context context) {
        super(view);
        this.mRecyclerView = (RecyclerView) view.findViewById(d.book_reader_inner_recycler_view);
        this.mAllBookTextView = (TextView) view.findViewById(d.book_reader_see_all_book_text_view);
        this.markView = view.findViewById(d.book_reader_mark_view);
        this.lineView = view.findViewById(d.book_reader_line_view);
        this.tagTextView = (TextView) view.findViewById(d.book_reader_tag_text_view);
        this.mAllBookImageView = (ImageView) view.findViewById(d.book_reader_all_book_image_view);
        this.mAllBookLayout = (LinearLayout) view.findViewById(d.book_reader_see_all_book_layout);
        this.mContext = context;
        initRecyclerView(this.mContext);
    }

    public void bindViewData(Visitable visitable) {
        if (visitable instanceof BookList) {
            Collection detailResponseList = ((BookList) visitable).getDetailResponseList();
            if (!CollectionUtil.isEmpty(detailResponseList)) {
                CharSequence string;
                int size = detailResponseList.size();
                if (detailResponseList.size() > 3) {
                    detailResponseList = detailResponseList.subList(0, 3);
                }
                if (this.bookListData == null) {
                    this.bookListData = new ArrayList();
                }
                this.bookListData.clear();
                this.bookListData.addAll(detailResponseList);
                if (this.mStoreAdapter == null) {
                    this.mStoreAdapter = new BookStoreAdapter(this.mContext, this.bookListData, true, 2, 27);
                    this.mRecyclerView.setAdapter(this.mStoreAdapter);
                } else {
                    this.mStoreAdapter.notifyDataSetChanged();
                }
                if (size == 0) {
                    string = this.mContext.getResources().getString(f.book_reader_see_all_text);
                } else {
                    string = String.format(this.mContext.getResources().getString(f.book_reader_see_all_book_text), new Object[]{Integer.valueOf(size)});
                }
                this.mAllBookTextView.setText(string);
                this.mAllBookLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ActivityUtil.toCommonActivity(ShelfViewHolder.this.mContext, HomeActivity.class);
                    }
                });
                com.spriteapp.booklibrary.e.d a = b.a();
                if (a != null) {
                    this.mAllBookTextView.setTextColor(a.j());
                    this.markView.setBackgroundColor(a.g());
                    this.tagTextView.setTextColor(a.h());
                    this.lineView.setBackgroundColor(a.f());
                    this.mAllBookImageView.setImageResource(a.l());
                }
            }
        }
    }

    private void initRecyclerView(Context context) {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        this.mRecyclerView.addItemDecoration(new StoreShelfItemDecoration(ScreenUtil.dpToPxInt(27.0f)));
    }
}
