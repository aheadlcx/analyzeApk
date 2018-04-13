package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.budejie.www.adapter.a.c;

public class LinearLayoutForListView extends LinearLayout {
    private OnClickListener a = null;

    public void a(Context context, c cVar) {
        int count = cVar.getCount();
        removeAllViews();
        for (int i = 0; i < count; i++) {
            View view = cVar.getView(i, null, null);
            if (view != null) {
                view.setOnClickListener(this.a);
                addView(view, i);
            }
        }
        Log.v("countTAG", "" + count);
    }

    public LinearLayoutForListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
