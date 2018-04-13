package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;

public class LoadingMoreFooter extends LinearLayout {
    public static final int STATE_COMPLETE = 1;
    public static final int STATE_LOADING = 0;
    public static final int STATE_NOMORE = 2;
    private String loadingDoneHint;
    private String loadingHint;
    private View mContentChildView;
    private View mProgressBar;
    private TextView mTextView;
    private String noMoreHint;

    public LoadingMoreFooter(Context context) {
        super(context);
        initView(context);
    }

    public LoadingMoreFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public void setLoadingHint(String str) {
        this.loadingHint = str;
    }

    public void setNoMoreHint(String str) {
        this.noMoreHint = str;
    }

    public void setLoadingDoneHint(String str) {
        this.loadingDoneHint = str;
    }

    public void initView(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(e.book_reader_recyclerview_footer, null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.mProgressBar = linearLayout.findViewById(d.listview_foot_progress);
        this.mTextView = (TextView) linearLayout.findViewById(d.listview_foot_more);
        this.loadingHint = "正在刷新...";
        this.noMoreHint = "没有更多了";
        this.loadingDoneHint = "加载完成";
    }

    public void setState(int i) {
        switch (i) {
            case 0:
                this.mProgressBar.setVisibility(0);
                this.mTextView.setText(this.loadingHint);
                setVisibility(0);
                return;
            case 1:
                this.mTextView.setText(this.loadingDoneHint);
                setVisibility(4);
                return;
            case 2:
                this.mTextView.setText(this.noMoreHint);
                this.mProgressBar.setVisibility(8);
                setVisibility(0);
                return;
            default:
                return;
        }
    }
}
