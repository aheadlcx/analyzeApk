package com.spriteapp.booklibrary.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.spriteapp.booklibrary.a;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.d.e;
import com.spriteapp.booklibrary.listener.DeleteBookListener;
import com.spriteapp.booklibrary.model.response.BookDetailResponse;
import com.spriteapp.booklibrary.util.CollectionUtil;
import com.spriteapp.booklibrary.util.RecyclerViewUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.StringUtil;
import java.util.List;

public class BookShelfAdapter extends Adapter<BookShelfAdapter$ShelfViewHolder> {
    private static final int ANIMATION_TIME = 300;
    private static final int IMAGE_HEIGHT = 144;
    private static final int IMAGE_WIDTH = 100;
    private static final String TAG = "BookShelfAdapter";
    private static Handler mHandler = new BookShelfAdapter$1();
    private boolean isDeleteBook;
    private boolean isRecentReadBook;
    private boolean isRecommendData;
    private b mBookDb;
    private Context mContext;
    private DeleteBookListener mDeleteListener;
    private List<BookDetailResponse> mDetailList;
    private final int mImageHeight = ((this.mImageWidth * ScreenUtil.dpToPxInt(144.0f)) / ScreenUtil.dpToPxInt(100.0f));
    private final int mImageWidth;
    private LayoutInflater mLayoutInflater;
    private e mRecentBookDb;

    public BookShelfAdapter(Context context, List<BookDetailResponse> list, int i, int i2, boolean z) {
        this.mContext = context;
        this.mDetailList = list;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.isRecentReadBook = z;
        this.mBookDb = new b(context);
        this.mRecentBookDb = new e(context);
        this.mImageWidth = RecyclerViewUtil.getImageWidth(i - 1, i2);
    }

    public BookShelfAdapter$ShelfViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BookShelfAdapter$ShelfViewHolder(this.mLayoutInflater.inflate(a.e.book_reader_item_book_shelf, viewGroup, false));
    }

    public boolean isDeleteBook() {
        return this.isDeleteBook;
    }

    public void setDeleteBook(boolean z) {
        this.isDeleteBook = z;
    }

    public void onBindViewHolder(BookShelfAdapter$ShelfViewHolder bookShelfAdapter$ShelfViewHolder, int i) {
        BookDetailResponse bookDetailResponse = (BookDetailResponse) this.mDetailList.get(i);
        if (bookDetailResponse != null) {
            LayoutParams layoutParams = (LayoutParams) bookShelfAdapter$ShelfViewHolder.mShadowLayout.getLayoutParams();
            layoutParams.height = this.mImageHeight - ScreenUtil.dpToPxInt(12.0f);
            bookShelfAdapter$ShelfViewHolder.mShadowLayout.setLayoutParams(layoutParams);
            i.b(this.mContext).a(bookDetailResponse.getBook_image()).d().a(DiskCacheStrategy.SOURCE).a().a(bookShelfAdapter$ShelfViewHolder.logoImageView);
            if (!StringUtil.isEmpty(bookDetailResponse.getBook_name())) {
                bookShelfAdapter$ShelfViewHolder.titleTextView.setText(bookDetailResponse.getBook_name());
            }
            if (bookDetailResponse.getBook_chapter_total() == 0 || this.isRecentReadBook) {
                bookShelfAdapter$ShelfViewHolder.progressTextView.setVisibility(8);
            } else {
                bookShelfAdapter$ShelfViewHolder.progressTextView.setVisibility(0);
                bookShelfAdapter$ShelfViewHolder.progressTextView.setText(String.format(this.mContext.getResources().getString(f.book_reader_read_progress_text), new Object[]{Integer.valueOf((bookDetailResponse.getLast_chapter_index() * 100) / bookDetailResponse.getBook_chapter_total())}));
            }
            bookShelfAdapter$ShelfViewHolder.logoImageView.setOnLongClickListener(new BookShelfAdapter$2(this));
            bookShelfAdapter$ShelfViewHolder.logoImageView.setOnClickListener(new BookShelfAdapter$3(this, bookShelfAdapter$ShelfViewHolder));
            bookShelfAdapter$ShelfViewHolder.deleteImageView.setOnClickListener(new BookShelfAdapter$4(this, bookDetailResponse, bookShelfAdapter$ShelfViewHolder));
            judgeShowDeleteView(bookShelfAdapter$ShelfViewHolder);
        }
    }

    private void judgeShowDeleteView(BookShelfAdapter$ShelfViewHolder bookShelfAdapter$ShelfViewHolder) {
        if (this.isDeleteBook) {
            AnimatorSet animatorSet = new AnimatorSet();
            bookShelfAdapter$ShelfViewHolder.deleteImageView.setVisibility(0);
            Animator ofFloat = ObjectAnimator.ofFloat(bookShelfAdapter$ShelfViewHolder.deleteImageView, View.SCALE_X, new float[]{0.0f, 1.0f});
            Animator ofFloat2 = ObjectAnimator.ofFloat(bookShelfAdapter$ShelfViewHolder.deleteImageView, View.SCALE_Y, new float[]{0.0f, 1.0f});
            ofFloat.setDuration(300);
            ofFloat2.setDuration(300);
            animatorSet.play(ofFloat).with(ofFloat2);
            animatorSet.start();
            return;
        }
        bookShelfAdapter$ShelfViewHolder.deleteImageView.setVisibility(8);
    }

    public int getItemCount() {
        return CollectionUtil.isEmpty(this.mDetailList) ? 0 : this.mDetailList.size();
    }

    public void setDeleteListener(DeleteBookListener deleteBookListener) {
        this.mDeleteListener = deleteBookListener;
    }

    public void setIsRecommendData(boolean z) {
        this.isRecommendData = z;
    }
}
