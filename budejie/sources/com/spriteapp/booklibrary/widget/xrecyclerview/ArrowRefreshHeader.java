package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.c;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.e.b;
import com.spriteapp.booklibrary.util.DateUtil;
import com.spriteapp.booklibrary.util.ScreenUtil;

public class ArrowRefreshHeader extends LinearLayout implements BaseRefreshHeader {
    private static final int MIN_DP = 3;
    private ImageView mArrowImageView;
    private LinearLayout mContainer;
    private TextView mHeaderTimeTextView;
    public int mMeasuredHeight;
    private int mState;
    private TextView mStatusTextView;
    private TextView mUpdateTextView;

    public ArrowRefreshHeader(Context context) {
        this(context, null);
    }

    public ArrowRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = 0;
        initView();
    }

    private void initView() {
        this.mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(e.book_reader_recyclerview_header, null);
        addView(this.mContainer, new LayoutParams(-1, 0));
        setGravity(80);
        this.mArrowImageView = (ImageView) findViewById(d.book_reader_header_arrow);
        this.mStatusTextView = (TextView) findViewById(d.book_reader_prompt_text_view);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(d.book_reader_header_content);
        this.mHeaderTimeTextView = (TextView) findViewById(d.book_reader_time_text_view);
        this.mUpdateTextView = (TextView) findViewById(d.book_reader_update_text_view);
        measure(-2, -2);
        this.mMeasuredHeight = relativeLayout.getMeasuredHeight() - ScreenUtil.dpToPxInt(3.0f);
        setBackground();
    }

    public void setBackground() {
        com.spriteapp.booklibrary.e.d a = b.a();
        if (a != null) {
            setBackgroundColor(a.m());
            if (this.mStatusTextView != null) {
                this.mStatusTextView.setTextColor(a.n());
            }
            if (this.mHeaderTimeTextView != null) {
                this.mHeaderTimeTextView.setTextColor(a.n());
            }
            if (this.mUpdateTextView != null) {
                this.mUpdateTextView.setTextColor(a.n());
            }
        }
    }

    public void setArrowImageView(int i) {
        this.mArrowImageView.setImageResource(i);
    }

    public void setState(int i) {
        if (i != this.mState) {
            switch (i) {
                case 0:
                    this.mStatusTextView.setText(f.xlistview_header_hint_normal);
                    this.mArrowImageView.setBackgroundResource(c.book_reader_list_view_pull);
                    break;
                case 1:
                    this.mArrowImageView.setBackgroundResource(c.book_reader_list_view_release);
                    this.mStatusTextView.setText(f.xlistview_header_hint_ready);
                    break;
                case 2:
                    this.mArrowImageView.setBackgroundResource(c.book_reader_recyclerview_header_refreshing_anim);
                    ((AnimationDrawable) this.mArrowImageView.getBackground()).start();
                    this.mStatusTextView.setText(f.xlistview_header_hint_loading);
                    break;
            }
            this.mState = i;
        }
    }

    public int getState() {
        return this.mState;
    }

    public void setLastRefreshTime(long j) {
        this.mHeaderTimeTextView.setText(DateUtil.friendlyTime(j));
    }

    public void refreshComplete() {
        setState(3);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ArrowRefreshHeader.this.reset();
            }
        }, 200);
    }

    public void setVisibleHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LayoutParams layoutParams = (LayoutParams) this.mContainer.getLayoutParams();
        layoutParams.height = i;
        this.mContainer.setLayoutParams(layoutParams);
    }

    public int getVisibleHeight() {
        return ((LayoutParams) this.mContainer.getLayoutParams()).height;
    }

    public void onMove(float f) {
        if (getVisibleHeight() > 0 || f > 0.0f) {
            setVisibleHeight(((int) f) + getVisibleHeight());
            if (this.mState > 1) {
                return;
            }
            if (getVisibleHeight() > this.mMeasuredHeight) {
                setState(1);
            } else {
                setState(0);
            }
        }
    }

    public boolean releaseAction() {
        boolean z;
        if (getVisibleHeight() == 0) {
        }
        if (getVisibleHeight() <= this.mMeasuredHeight || this.mState >= 2) {
            z = false;
        } else {
            setState(2);
            z = true;
        }
        if (this.mState != 2) {
            smoothScrollTo(0);
        }
        if (this.mState == 2) {
            smoothScrollTo(this.mMeasuredHeight);
        }
        return z;
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ArrowRefreshHeader.this.setState(0);
            }
        }, 500);
    }

    private void smoothScrollTo(int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getVisibleHeight(), i});
        ofInt.setDuration(300).start();
        ofInt.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ArrowRefreshHeader.this.setVisibleHeight(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        ofInt.start();
    }
}
