package com.spriteapp.booklibrary.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.base.BaseActivity;
import com.spriteapp.booklibrary.config.HuaXiConfig;
import com.spriteapp.booklibrary.config.HuaXiSDK;

public abstract class TitleActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = "TitleActivity";
    ImageView mBackImageView;
    LinearLayout mCenterLayout;
    FrameLayout mContainerLayout;
    LinearLayout mLeftLayout;
    View mLineView;
    LinearLayout mRightLayout;
    RelativeLayout mTitleLayout;
    TextView mTitleTextView;

    public abstract void addContentView();

    public int getLayoutResId() {
        return e.book_reader_activity_title;
    }

    public void findViewId() {
        this.mBackImageView = (ImageView) findViewById(d.book_reader_back_imageView);
        this.mTitleTextView = (TextView) findViewById(d.book_reader_title_textView);
        this.mLeftLayout = (LinearLayout) findViewById(d.book_reader_left_layout);
        this.mRightLayout = (LinearLayout) findViewById(d.book_reader_right_layout);
        this.mContainerLayout = (FrameLayout) findViewById(d.book_reader_container_layout);
        this.mCenterLayout = (LinearLayout) findViewById(d.book_reader_center_layout);
        this.mTitleLayout = (RelativeLayout) findViewById(d.book_reader_title_layout);
        this.mLineView = findViewById(d.book_reader_title_line_view);
        addContentView();
        this.mLeftLayout.setOnClickListener(this);
    }

    public void configViews() {
        HuaXiConfig config = HuaXiSDK.getInstance().getConfig();
        int titleBackground = config.getTitleBackground();
        if (titleBackground != 0) {
            this.mTitleLayout.setBackgroundColor(titleBackground);
        }
        titleBackground = config.getTitleColor();
        if (titleBackground != 0) {
            this.mTitleTextView.setTextColor(titleBackground);
        }
        int backImageResource = config.getBackImageResource();
        if (backImageResource != 0) {
            this.mBackImageView.setImageResource(backImageResource);
        }
    }

    public void setTitle(int i) {
        this.mTitleTextView.setText(getResources().getString(i));
    }

    public void setTitle(String str) {
        this.mTitleTextView.setText(str);
    }

    public void onClick(View view) {
        if (view == this.mLeftLayout) {
            finish();
        }
    }
}
