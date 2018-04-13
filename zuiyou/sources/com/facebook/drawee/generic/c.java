package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.a.a;
import com.facebook.drawee.drawable.b;
import com.facebook.drawee.drawable.n$b;
import com.facebook.infer.annotation.ReturnsOwnership;
import javax.annotation.Nullable;

public class c {
    public static b a(Context context, @Nullable AttributeSet attributeSet) {
        return a(new b(context.getResources()), context, attributeSet);
    }

    public static b a(b bVar, Context context, @Nullable AttributeSet attributeSet) {
        int[] iArr;
        boolean z;
        boolean z2;
        int i;
        boolean z3 = true;
        int i2 = 0;
        if (attributeSet != null) {
            iArr = a.GenericDraweeHierarchy;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
            try {
                int indexCount = obtainStyledAttributes.getIndexCount();
                iArr = 1;
                z = true;
                z2 = true;
                i = 0;
                for (int i3 = 0; i3 < indexCount; i3++) {
                    int index = obtainStyledAttributes.getIndex(i3);
                    if (index == a.GenericDraweeHierarchy_actualImageScaleType) {
                        bVar.e(a(obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_placeholderImage) {
                        bVar.a(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_pressedStateOverlayImage) {
                        bVar.g(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_progressBarImage) {
                        bVar.d(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_fadeDuration) {
                        bVar.a(obtainStyledAttributes.getInt(index, 0));
                    } else if (index == a.GenericDraweeHierarchy_viewAspectRatio) {
                        bVar.a(obtainStyledAttributes.getFloat(index, 0.0f));
                    } else if (index == a.GenericDraweeHierarchy_placeholderImageScaleType) {
                        bVar.a(a(obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_retryImage) {
                        bVar.b(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_retryImageScaleType) {
                        bVar.b(a(obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_failureImage) {
                        bVar.c(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_failureImageScaleType) {
                        bVar.c(a(obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_progressBarImageScaleType) {
                        bVar.d(a(obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_progressBarAutoRotateInterval) {
                        i = obtainStyledAttributes.getInteger(index, i);
                    } else if (index == a.GenericDraweeHierarchy_backgroundImage) {
                        bVar.e(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_overlayImage) {
                        bVar.f(a(context, obtainStyledAttributes, index));
                    } else if (index == a.GenericDraweeHierarchy_roundAsCircle) {
                        a(bVar).a(obtainStyledAttributes.getBoolean(index, false));
                    } else if (index == a.GenericDraweeHierarchy_roundedCornerRadius) {
                        i2 = obtainStyledAttributes.getDimensionPixelSize(index, i2);
                    } else if (index == a.GenericDraweeHierarchy_roundTopLeft) {
                        z2 = obtainStyledAttributes.getBoolean(index, z2);
                    } else if (index == a.GenericDraweeHierarchy_roundTopRight) {
                        z = obtainStyledAttributes.getBoolean(index, z);
                    } else if (index == a.GenericDraweeHierarchy_roundBottomLeft) {
                        iArr = obtainStyledAttributes.getBoolean(index, iArr);
                    } else if (index == a.GenericDraweeHierarchy_roundBottomRight) {
                        z3 = obtainStyledAttributes.getBoolean(index, z3);
                    } else if (index == a.GenericDraweeHierarchy_roundWithOverlayColor) {
                        a(bVar).a(obtainStyledAttributes.getColor(index, 0));
                    } else if (index == a.GenericDraweeHierarchy_roundingBorderWidth) {
                        a(bVar).b((float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
                    } else if (index == a.GenericDraweeHierarchy_roundingBorderColor) {
                        a(bVar).b(obtainStyledAttributes.getColor(index, 0));
                    } else if (index == a.GenericDraweeHierarchy_roundingBorderPadding) {
                        a(bVar).c((float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
                    }
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            iArr = 1;
            z = true;
            z2 = true;
            i = 0;
        }
        if (bVar.j() != null && i > 0) {
            bVar.d(new b(bVar.j(), i));
        }
        if (i2 > 0) {
            a(bVar).a(z2 ? (float) i2 : 0.0f, z ? (float) i2 : 0.0f, z3 ? (float) i2 : 0.0f, iArr != null ? (float) i2 : 0.0f);
        }
        return bVar;
    }

    @ReturnsOwnership
    private static RoundingParams a(b bVar) {
        if (bVar.s() == null) {
            bVar.a(new RoundingParams());
        }
        return bVar.s();
    }

    @Nullable
    private static Drawable a(Context context, TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, 0);
        return resourceId == 0 ? null : context.getResources().getDrawable(resourceId);
    }

    @Nullable
    private static n$b a(TypedArray typedArray, int i) {
        switch (typedArray.getInt(i, -2)) {
            case -1:
                return null;
            case 0:
                return n$b.a;
            case 1:
                return n$b.b;
            case 2:
                return n$b.c;
            case 3:
                return n$b.d;
            case 4:
                return n$b.e;
            case 5:
                return n$b.f;
            case 6:
                return n$b.g;
            case 7:
                return n$b.h;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }
}
