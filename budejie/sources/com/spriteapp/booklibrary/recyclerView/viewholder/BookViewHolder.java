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
import com.spriteapp.booklibrary.model.store.BookTypeResponse;
import com.spriteapp.booklibrary.recyclerView.adapter.BookStoreAdapter;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import com.spriteapp.booklibrary.util.WebViewUtil;
import com.spriteapp.booklibrary.widget.recyclerview.StoreItemDecoration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookViewHolder extends BaseViewHolder<Visitable> {
    private static final int SPACE_COUNT = 3;
    private static final int SPACE_WIDTH = 15;
    private static final int SPAN_COUNT = 4;
    private static final String TAG = "BookViewHolder";
    private final View lineView;
    private ImageView mAllBookImageView;
    private final LinearLayout mAllBookLayout;
    private TextView mAllBookTextView;
    private TextView mBookTagTextView;
    private Context mContext;
    private List<BookDetailResponse> mDataList = new ArrayList();
    private RecyclerView mRecyclerView;
    private BookStoreAdapter mStoreAdapter;
    private final View markView;

    public BookViewHolder(View view, Context context) {
        super(view);
        this.mRecyclerView = (RecyclerView) view.findViewById(d.book_reader_inner_recycler_view);
        this.mBookTagTextView = (TextView) view.findViewById(d.book_reader_tag_text_view);
        this.mAllBookTextView = (TextView) view.findViewById(d.book_reader_see_all_book_text_view);
        this.markView = view.findViewById(d.book_reader_mark_view);
        this.lineView = view.findViewById(d.book_reader_line_view);
        this.mAllBookImageView = (ImageView) view.findViewById(d.book_reader_all_book_image_view);
        this.mAllBookLayout = (LinearLayout) view.findViewById(d.book_reader_see_all_book_layout);
        initRecyclerView(context);
        this.mContext = context;
    }

    private void initRecyclerView(Context context) {
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        this.mRecyclerView.addItemDecoration(new StoreItemDecoration(ScreenUtil.dpToPxInt(15.0f), ScreenUtil.dpToPxInt(5.0f)));
    }

    public void bindViewData(Visitable visitable) {
        if (visitable instanceof BookList) {
            final BookTypeResponse typeResponse = ((BookList) visitable).getTypeResponse();
            if (typeResponse != null) {
                Collection lists = typeResponse.getLists();
                if (!CollectionUtil.isEmpty(lists)) {
                    this.mDataList.clear();
                    this.mDataList.addAll(lists);
                    if (!CollectionUtil.isEmpty(this.mDataList)) {
                        CharSequence string;
                        if (this.mStoreAdapter == null) {
                            this.mStoreAdapter = new BookStoreAdapter(this.mContext, this.mDataList, false, 3, 15);
                            this.mRecyclerView.setAdapter(this.mStoreAdapter);
                        } else {
                            this.mStoreAdapter.notifyDataSetChanged();
                        }
                        this.mBookTagTextView.setText(typeResponse.getName());
                        if (typeResponse.getCount() == 0) {
                            string = this.mContext.getResources().getString(f.book_reader_see_all_text);
                        } else {
                            string = String.format(this.mContext.getResources().getString(f.book_reader_see_all_book_text), new Object[]{Integer.valueOf(r0)});
                        }
                        this.mAllBookTextView.setText(string);
                        this.mAllBookLayout.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                String url = typeResponse.getUrl();
                                if (!StringUtil.isEmpty(url)) {
                                    WebViewUtil.getInstance().getJumpUrl(BookViewHolder.this.mContext, url);
                                }
                            }
                        });
                        com.spriteapp.booklibrary.e.d a = b.a();
                        if (a != null) {
                            this.mAllBookTextView.setTextColor(a.j());
                            this.markView.setBackgroundColor(a.g());
                            this.lineView.setBackgroundColor(a.f());
                            this.mBookTagTextView.setTextColor(a.h());
                            this.mAllBookImageView.setImageResource(a.l());
                        }
                    }
                }
            }
        }
    }
}
