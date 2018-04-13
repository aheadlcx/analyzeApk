package com.spriteapp.booklibrary.recyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.spriteapp.booklibrary.a;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.d.e;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.RecyclerViewUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import java.util.List;

public class BookStoreAdapter extends Adapter<BookStoreViewHolder> {
    private static final int BOOK_IMAGE_HEIGHT = 100;
    private static final int BOOK_IMAGE_WIDTH = 69;
    private static final int SHELF_BOOK_HEIGHT = 136;
    private static final int SHELF_BOOK_WIDTH = 91;
    private static final String TAG = "BookStoreAdapter";
    private boolean isShelfBook;
    private b mBookDb = new b(this.mContext);
    private Context mContext;
    private List<BookDetailResponse> mDetailList;
    private int mImageHeight;
    private int mImageWidth;
    private LayoutInflater mInflater = LayoutInflater.from(this.mContext);
    private e mRecentBookDb = new e(this.mContext);

    class BookStoreViewHolder extends ViewHolder {
        TextView bookAuthorTextView;
        RelativeLayout bookLayout;
        ImageView bookLogoImageView;
        TextView bookNameTextView;
        LinearLayout parentLayout;
        TextView progressTextView;

        public BookStoreViewHolder(View view) {
            super(view);
            this.bookLogoImageView = (ImageView) view.findViewById(d.book_reader_book_image_view);
            this.bookNameTextView = (TextView) view.findViewById(d.book_reader_book_name_text_view);
            this.bookAuthorTextView = (TextView) view.findViewById(d.book_reader_author_text_view);
            this.bookLayout = (RelativeLayout) view.findViewById(d.book_reader_book_layout);
            this.parentLayout = (LinearLayout) view.findViewById(d.book_reader_parent_layout);
            this.progressTextView = (TextView) view.findViewById(d.book_reader_progress_text_view);
        }
    }

    public BookStoreAdapter(Context context, List<BookDetailResponse> list, boolean z, int i, int i2) {
        this.mContext = context;
        this.mDetailList = list;
        this.isShelfBook = z;
        this.mImageWidth = RecyclerViewUtil.getImageWidth(i, i2);
        if (z) {
            this.mImageHeight = (this.mImageWidth * ScreenUtil.dpToPxInt(136.0f)) / ScreenUtil.dpToPxInt(91.0f);
        } else {
            this.mImageHeight = (this.mImageWidth * ScreenUtil.dpToPxInt(100.0f)) / ScreenUtil.dpToPxInt(69.0f);
        }
    }

    public BookStoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BookStoreViewHolder(this.mInflater.inflate(a.e.book_reader_store_inner_item, viewGroup, false));
    }

    public void onBindViewHolder(final BookStoreViewHolder bookStoreViewHolder, int i) {
        com.spriteapp.booklibrary.e.d a = com.spriteapp.booklibrary.e.b.a();
        setLayoutAttributes(bookStoreViewHolder, i);
        BookDetailResponse bookDetailResponse = (BookDetailResponse) this.mDetailList.get(i);
        i.b(this.mContext).a(bookDetailResponse.getBook_image()).d().a(DiskCacheStrategy.SOURCE).a().a(bookStoreViewHolder.bookLogoImageView);
        CharSequence book_name = bookDetailResponse.getBook_name();
        if (!StringUtil.isEmpty(book_name)) {
            bookStoreViewHolder.bookNameTextView.setText(book_name);
        }
        book_name = bookDetailResponse.getAuthor_name();
        if (!StringUtil.isEmpty(book_name)) {
            bookStoreViewHolder.bookAuthorTextView.setText(book_name);
        }
        if (bookDetailResponse.getBook_chapter_total() != 0) {
            bookStoreViewHolder.progressTextView.setText(String.format(this.mContext.getResources().getString(f.book_reader_read_progress_text), new Object[]{Integer.valueOf((bookDetailResponse.getLast_chapter_index() * 100) / bookDetailResponse.getBook_chapter_total())}));
        } else {
            bookStoreViewHolder.progressTextView.setVisibility(8);
        }
        bookStoreViewHolder.bookLogoImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BookDetailResponse bookDetailResponse = (BookDetailResponse) BookStoreAdapter.this.mDetailList.get(bookStoreViewHolder.getLayoutPosition());
                if (bookDetailResponse != null) {
                    BookStoreAdapter.this.mBookDb.a(bookDetailResponse.getBook_id());
                    BookStoreAdapter.this.mRecentBookDb.a(bookDetailResponse.getBook_id());
                    ActivityUtil.toReadActivity(BookStoreAdapter.this.mContext, bookDetailResponse, true);
                }
            }
        });
        if (a != null) {
            bookStoreViewHolder.bookNameTextView.setTextColor(a.h());
            bookStoreViewHolder.bookAuthorTextView.setTextColor(a.i());
            bookStoreViewHolder.bookLayout.setBackgroundResource(a.k());
        }
    }

    private void setLayoutAttributes(BookStoreViewHolder bookStoreViewHolder, int i) {
        LayoutParams layoutParams = (LayoutParams) bookStoreViewHolder.bookLayout.getLayoutParams();
        if (this.isShelfBook) {
            ViewGroup.LayoutParams layoutParams2 = bookStoreViewHolder.parentLayout.getLayoutParams();
            layoutParams2.height = this.mImageHeight + ScreenUtil.dpToPxInt(36.0f);
            bookStoreViewHolder.parentLayout.setLayoutParams(layoutParams2);
            bookStoreViewHolder.parentLayout.setGravity(80);
            layoutParams.width = i == 0 ? this.mImageWidth + ScreenUtil.dpToPxInt(9.0f) : this.mImageWidth;
            layoutParams.height = i == 0 ? this.mImageHeight + ScreenUtil.dpToPxInt(8.0f) : this.mImageHeight;
            bookStoreViewHolder.bookLayout.setLayoutParams(layoutParams);
            bookStoreViewHolder.bookAuthorTextView.setVisibility(8);
            bookStoreViewHolder.bookNameTextView.setMaxLines(1);
            bookStoreViewHolder.bookNameTextView.setEllipsize(TruncateAt.END);
            bookStoreViewHolder.progressTextView.setVisibility(0);
            return;
        }
        layoutParams.width = this.mImageWidth;
        layoutParams.height = this.mImageHeight;
        bookStoreViewHolder.bookLayout.setLayoutParams(layoutParams);
    }

    public int getItemCount() {
        return CollectionUtil.isEmpty(this.mDetailList) ? 0 : this.mDetailList.size();
    }
}
