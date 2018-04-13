package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.a.c;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.util.ScreenUtil;

public class ReadBottomLayout extends LinearLayout {
    private ImageView mChapterImageView;
    private TextView mChapterTextView;
    private ImageView mModeImageView;
    private TextView mModeTextView;
    private ImageView mProgressImageView;
    private TextView mProgressTextView;
    private ImageView mTextImageView;
    private TextView mTextTextView;
    private View mView;

    public ReadBottomLayout(Context context) {
        this(context, null);
    }

    public ReadBottomLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReadBottomLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(e.book_reader_book_operation_bottom_view, null);
        addView(this.mView);
        LayoutParams layoutParams = (LayoutParams) this.mView.getLayoutParams();
        layoutParams.width = ScreenUtil.getScreenWidth();
        this.mView.setLayoutParams(layoutParams);
        findId();
    }

    private void findId() {
        this.mChapterImageView = (ImageView) findViewById(d.book_reader_chapter_image_view);
        this.mProgressImageView = (ImageView) findViewById(d.book_reader_progress_image_view);
        this.mModeImageView = (ImageView) findViewById(d.book_reader_mode_image_view);
        this.mTextImageView = (ImageView) findViewById(d.book_reader_text_image_view);
        this.mChapterTextView = (TextView) findViewById(d.book_reader_chapter_text_view);
        this.mProgressTextView = (TextView) findViewById(d.book_reader_progress_text_view);
        this.mModeTextView = (TextView) findViewById(d.book_reader_mode_text_view);
        this.mTextTextView = (TextView) findViewById(d.book_reader_text_text_view);
    }

    public void changeMode(boolean z) {
        this.mChapterImageView.setImageResource(z ? c.book_reader_chapter_night_selector : c.book_reader_chapter_selector);
        this.mProgressImageView.setImageResource(z ? c.book_reader_progress_night_selector : c.book_reader_progress_selector);
        this.mModeImageView.setImageResource(z ? c.book_reader_mode_night_selector : c.book_reader_mode_selector);
        this.mTextImageView.setImageResource(z ? c.book_reader_text_size_night_selector : c.book_reader_text_size_selector);
        this.mView.setBackgroundResource(z ? a.book_reader_read_bottom_night_background : a.book_reader_white);
        this.mChapterTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
        this.mProgressTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
        this.mModeTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
        this.mTextTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
        this.mModeTextView.setText(z ? "日间模式" : "夜间模式");
    }

    public void setColor(boolean z) {
        this.mView.setBackgroundResource(z ? a.book_reader_white : a.book_reader_black);
    }
}
