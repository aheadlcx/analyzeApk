package com.budejie.www.widget.curtain;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.CommendDetail;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;

public class FloatVideoRootLayout extends MutilKeyboardListenerRelativeLayout {
    private FloatVideoLayout c;
    private RelativeLayout d;
    private CurtainVideoContainerLayout e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;

    public FloatVideoRootLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.curtain_height);
        try {
            this.g = 0;
            if (!(VERSION.SDK_INT < 21 || (context instanceof CommendDetail) || (context instanceof PostDetailActivity))) {
                this.g = getContext().getResources().getDimensionPixelOffset(R.dimen.navigation_height_gap) + an.a(getContext(), 3);
            }
            this.f = obtainStyledAttributes.getDimensionPixelOffset(0, this.f) + this.g;
            this.h = obtainStyledAttributes.getDimensionPixelOffset(1, this.h);
            this.i = obtainStyledAttributes.getBoolean(2, this.i);
            this.j = obtainStyledAttributes.getBoolean(3, this.j);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void a() {
        this.c = (FloatVideoLayout) findViewById(R.id.curtain_layout);
        if (this.c != null) {
            this.c.d = this.g;
            this.c.e = this.f;
            this.c.f = this.h;
            this.c.g = this.i;
            this.c.h = this.j;
            this.e = (CurtainVideoContainerLayout) this.c.findViewById(R.id.curtain_video_container);
        }
        this.d = (RelativeLayout) findViewById(R.id.activity_content_layout);
    }

    public void setmMinTopHeight(int i) {
        this.f = i;
    }

    private boolean c() {
        return (this.c == null || !this.c.isShown() || this.c.m || this.c.n) ? false : true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean c = c();
        if (c) {
            try {
                if (motionEvent.getAction() == 0) {
                    this.c.f();
                } else if (this.e.getScrollSloped() && (motionEvent.getAction() == 1 || motionEvent.getAction() == 3)) {
                    this.d.dispatchTouchEvent(motionEvent);
                    return true;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (c) {
            this.c.a(motionEvent);
            if (this.c.getTouchPostion() == FloatVideoLayout$TouchPosition.VIDEO && !(k.a(getContext()).c() != null && k.a(getContext()).c().isShown() && this.e.a)) {
                if (!this.e.getScrollSloped() && (motionEvent.getAction() == 1 || motionEvent.getAction() == 3)) {
                    return dispatchTouchEvent;
                }
                this.d.dispatchTouchEvent(motionEvent);
            }
        }
        return dispatchTouchEvent;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (c() && motionEvent.getAction() != 0) {
            if (getKeyBoardState() && this.c.getTouchPostion() == FloatVideoLayout$TouchPosition.VIDEO) {
                ao.a((Activity) getContext());
                return true;
            } else if (this.c.getTouchPostion() == FloatVideoLayout$TouchPosition.BG && (motionEvent.getAction() == 1 || motionEvent.getAction() == 3)) {
                return this.c.getCurtainInterceptTouchState();
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
