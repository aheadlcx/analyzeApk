package com.spriteapp.booklibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.a;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.b.b;

public class TextSizeLayout extends LinearLayout {
    private b mCallBack;
    private Context mContext;
    private TextView mPlusTextView;
    private TextView mSubTextView;
    private int mTextSize;
    private TextSizeView mTextSizeView;
    private View mView;

    public TextSizeLayout(Context context) {
        this(context, null);
    }

    public TextSizeLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextSizeLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.mView = LayoutInflater.from(this.mContext).inflate(e.book_reader_text_size_layout, null);
        addView(this.mView);
        this.mSubTextView = (TextView) this.mView.findViewById(d.book_reader_sub_text_view);
        this.mPlusTextView = (TextView) this.mView.findViewById(d.book_reader_plus_text_view);
        this.mTextSizeView = (TextSizeView) this.mView.findViewById(d.book_reader_text_size_view);
        setListener();
    }

    public void changeMode(boolean z) {
        this.mView.setBackgroundResource(z ? a.book_reader_read_bottom_night_background : a.book_reader_white);
        this.mTextSizeView.changeMode(z);
        this.mPlusTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
        this.mSubTextView.setTextColor(getResources().getColorStateList(z ? a.book_reader_bottom_text_night_selector : a.book_reader_bottom_text_selector));
    }

    private void setListener() {
        this.mSubTextView.setOnClickListener(new TextSizeLayout$1(this));
        this.mPlusTextView.setOnClickListener(new TextSizeLayout$2(this));
    }

    public void setCallBack(b bVar) {
        this.mCallBack = bVar;
        this.mTextSizeView.setCallBack(bVar);
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setPosition(int i) {
        this.mTextSizeView.setPosition(i);
    }
}
