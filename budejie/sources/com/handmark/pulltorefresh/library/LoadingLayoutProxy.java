package com.handmark.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import java.util.HashSet;
import java.util.Iterator;

public class LoadingLayoutProxy implements ILoadingLayout {
    private final HashSet<LoadingLayout> a = new HashSet();

    LoadingLayoutProxy() {
    }

    public void addLayout(LoadingLayout loadingLayout) {
        if (loadingLayout != null) {
            this.a.add(loadingLayout);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setLastUpdatedLabel(charSequence);
        }
    }

    public void setLoadingDrawable(Drawable drawable) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setLoadingDrawable(drawable);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setRefreshingLabel(charSequence);
        }
    }

    public void setPullLabel(CharSequence charSequence) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setPullLabel(charSequence);
        }
    }

    public void setReleaseLabel(CharSequence charSequence) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setReleaseLabel(charSequence);
        }
    }

    public void setTextTypeface(Typeface typeface) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LoadingLayout) it.next()).setTextTypeface(typeface);
        }
    }
}
