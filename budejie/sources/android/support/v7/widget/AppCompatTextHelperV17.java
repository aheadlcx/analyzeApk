package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

class AppCompatTextHelperV17 extends AppCompatTextHelper {
    private static final int[] VIEW_ATTRS_v17 = new int[]{16843666, 16843667};
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableStartTint;

    AppCompatTextHelperV17(TextView textView) {
        super(textView);
    }

    void loadFromAttributes(AttributeSet attributeSet, int i) {
        super.loadFromAttributes(attributeSet, i);
        Context context = this.mView.getContext();
        AppCompatDrawableManager appCompatDrawableManager = AppCompatDrawableManager.get();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VIEW_ATTRS_v17, i, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            this.mDrawableStartTint = AppCompatTextHelper.createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.getResourceId(0, 0));
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.mDrawableEndTint = AppCompatTextHelper.createTintInfo(context, appCompatDrawableManager, obtainStyledAttributes.getResourceId(1, 0));
        }
        obtainStyledAttributes.recycle();
    }

    void applyCompoundDrawablesTints() {
        super.applyCompoundDrawablesTints();
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }
}
