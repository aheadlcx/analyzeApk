package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.budejie.www.util.aa;

public class PhotoViewContainerScrollView extends ScrollView {
    private a a;
    private boolean b;
    private boolean c;

    public interface a {
        void a(boolean z);

        void b(boolean z);
    }

    public PhotoViewContainerScrollView(Context context) {
        super(context);
    }

    public PhotoViewContainerScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PhotoViewContainerScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        aa.b("PhotoViewContainerScrollView", "onScrollChanged l=" + i);
        aa.b("PhotoViewContainerScrollView", "onScrollChanged t=" + i2);
        aa.b("PhotoViewContainerScrollView", "onScrollChanged oldl=" + i3);
        aa.b("PhotoViewContainerScrollView", "onScrollChanged oldt=" + i4);
        if (i2 - i4 < 450) {
            super.onScrollChanged(i, i2, i3, i4);
        } else {
            scrollTo(i3, i4);
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        aa.b("PhotoViewContainerScrollView", "onStartNestedScroll");
        return super.onStartNestedScroll(view, view2, i);
    }

    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        aa.b("PhotoViewContainerScrollView", "onOverScrolled");
        super.onOverScrolled(i, i2, z, z2);
        if (this.a != null) {
            if (i2 <= 0) {
                this.b = true;
                this.c = false;
            } else if (z2) {
                this.c = true;
                this.b = false;
            } else {
                this.c = false;
                this.b = false;
            }
            this.a.a(this.b);
            this.a.b(this.c);
        }
    }

    public void setScrollBottomListener(a aVar) {
        this.a = aVar;
    }
}
