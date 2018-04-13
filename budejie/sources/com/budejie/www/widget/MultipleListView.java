package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MultipleListView extends ListView {
    private XListViewFooter a;
    private b b;

    public MultipleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.a = new XListViewFooter(context);
    }

    public void setAdapter(ListAdapter listAdapter) {
        addFooterView(this.a);
        super.setAdapter(listAdapter);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.b = (b) onScrollListener;
        super.setOnScrollListener(onScrollListener);
    }

    public void setPullLoadEnable(boolean z) {
        if (z) {
            this.a.b();
            this.a.setState(0);
        } else {
            this.a.a();
        }
        this.b.a(z);
    }
}
