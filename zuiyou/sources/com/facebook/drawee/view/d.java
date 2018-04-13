package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.generic.a;
import com.facebook.drawee.generic.b;
import com.facebook.drawee.generic.c;
import javax.annotation.Nullable;

public class d extends c<a> {
    public d(Context context, a aVar) {
        super(context);
        setHierarchy(aVar);
    }

    public d(Context context) {
        super(context);
        a(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    @TargetApi(21)
    public d(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet);
    }

    protected void a(Context context, @Nullable AttributeSet attributeSet) {
        b a = c.a(context, attributeSet);
        setAspectRatio(a.c());
        setHierarchy(a.t());
    }
}
