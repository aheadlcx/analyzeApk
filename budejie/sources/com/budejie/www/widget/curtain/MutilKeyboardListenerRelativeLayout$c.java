package com.budejie.www.widget.curtain;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.RelativeLayout.LayoutParams;
import com.budejie.www.activity.video.k;
import com.budejie.www.adapter.b.a;

public class MutilKeyboardListenerRelativeLayout$c implements OnFocusChangeListener {
    final /* synthetic */ MutilKeyboardListenerRelativeLayout a;
    private View b;
    private boolean c;

    public MutilKeyboardListenerRelativeLayout$c(MutilKeyboardListenerRelativeLayout mutilKeyboardListenerRelativeLayout, View view, boolean z) {
        this.a = mutilKeyboardListenerRelativeLayout;
        this.b = view;
        this.c = z;
    }

    @SuppressLint({"NewApi"})
    public void onFocusChange(View view, boolean z) {
        if (this.c) {
            MutilKeyboardListenerRelativeLayout.a(this.a, z);
        }
        FloatVideoLayout floatVideoLayout = k.a(this.a.getContext()).f;
        if (floatVideoLayout != null && floatVideoLayout.isShown()) {
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            if (z) {
                layoutParams.width = this.b.getWidth();
                layoutParams.height = this.b.getHeight();
                this.a.b = layoutParams.bottomMargin;
                layoutParams.bottomMargin = -a.b;
            } else {
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.bottomMargin = this.a.b;
            }
            this.b.setLayoutParams(layoutParams);
        }
    }
}
