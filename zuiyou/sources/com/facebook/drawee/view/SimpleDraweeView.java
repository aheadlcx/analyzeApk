package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.drawee.d.d;
import com.facebook.drawee.generic.a;
import javax.annotation.Nullable;

public class SimpleDraweeView extends d {
    private static i<? extends d> a;
    private d b;

    public static void a(i<? extends d> iVar) {
        a = iVar;
    }

    public SimpleDraweeView(Context context, a aVar) {
        super(context, aVar);
        b(context, null);
    }

    public SimpleDraweeView(Context context) {
        super(context);
        b(context, null);
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context, attributeSet);
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context, attributeSet);
    }

    @TargetApi(21)
    public SimpleDraweeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        b(context, attributeSet);
    }

    private void b(Context context, @Nullable AttributeSet attributeSet) {
        if (!isInEditMode()) {
            g.a(a, (Object) "SimpleDraweeView was not initialized!");
            this.b = (d) a.b();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.facebook.drawee.a.a.SimpleDraweeView);
                try {
                    if (obtainStyledAttributes.hasValue(com.facebook.drawee.a.a.SimpleDraweeView_actualImageUri)) {
                        a(Uri.parse(obtainStyledAttributes.getString(com.facebook.drawee.a.a.SimpleDraweeView_actualImageUri)), null);
                    } else if (obtainStyledAttributes.hasValue(com.facebook.drawee.a.a.SimpleDraweeView_actualImageResource)) {
                        int resourceId = obtainStyledAttributes.getResourceId(com.facebook.drawee.a.a.SimpleDraweeView_actualImageResource, -1);
                        if (resourceId != -1) {
                            setActualImageResource(resourceId);
                        }
                    }
                    obtainStyledAttributes.recycle();
                } catch (Throwable th) {
                    obtainStyledAttributes.recycle();
                }
            }
        }
    }

    protected d getControllerBuilder() {
        return this.b;
    }

    public void setImageURI(Uri uri) {
        a(uri, null);
    }

    public void setImageURI(@Nullable String str) {
        a(str, null);
    }

    public void a(Uri uri, @Nullable Object obj) {
        setController(this.b.d(obj).b(uri).b(getController()).p());
    }

    public void a(@Nullable String str, @Nullable Object obj) {
        a(str != null ? Uri.parse(str) : null, obj);
    }

    public void setActualImageResource(@DrawableRes int i) {
        a(i, null);
    }

    public void a(@DrawableRes int i, @Nullable Object obj) {
        a(com.facebook.common.util.d.a(i), obj);
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
    }
}
