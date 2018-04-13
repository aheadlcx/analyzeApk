package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.TooltipCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BottomNavigationItemView extends FrameLayout implements ItemView {
    public static final int INVALID_ITEM_POSITION = -1;
    private static final int[] a = new int[]{16842912};
    private final int b;
    private final int c;
    private final float d;
    private final float e;
    private boolean f;
    private ImageView g;
    private final TextView h;
    private final TextView i;
    private int j;
    private MenuItemImpl k;
    private ColorStateList l;

    public BottomNavigationItemView(@NonNull Context context) {
        this(context, null);
    }

    public BottomNavigationItemView(@NonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = -1;
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_text_size);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_text_size);
        this.b = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_margin);
        this.c = dimensionPixelSize - dimensionPixelSize2;
        this.d = (((float) dimensionPixelSize2) * 1.0f) / ((float) dimensionPixelSize);
        this.e = (((float) dimensionPixelSize) * 1.0f) / ((float) dimensionPixelSize2);
        LayoutInflater.from(context).inflate(R.layout.design_bottom_navigation_item, this, true);
        setBackgroundResource(R.drawable.design_bottom_navigation_item_background);
        this.g = (ImageView) findViewById(R.id.icon);
        this.h = (TextView) findViewById(R.id.smallLabel);
        this.i = (TextView) findViewById(R.id.largeLabel);
    }

    public void initialize(MenuItemImpl menuItemImpl, int i) {
        this.k = menuItemImpl;
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitle());
        setId(menuItemImpl.getItemId());
        setContentDescription(menuItemImpl.getContentDescription());
        TooltipCompat.setTooltipText(this, menuItemImpl.getTooltipText());
    }

    public void setItemPosition(int i) {
        this.j = i;
    }

    public int getItemPosition() {
        return this.j;
    }

    public void setShiftingMode(boolean z) {
        this.f = z;
    }

    public MenuItemImpl getItemData() {
        return this.k;
    }

    public void setTitle(CharSequence charSequence) {
        this.h.setText(charSequence);
        this.i.setText(charSequence);
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        this.i.setPivotX((float) (this.i.getWidth() / 2));
        this.i.setPivotY((float) this.i.getBaseline());
        this.h.setPivotX((float) (this.h.getWidth() / 2));
        this.h.setPivotY((float) this.h.getBaseline());
        LayoutParams layoutParams;
        if (this.f) {
            if (z) {
                layoutParams = (LayoutParams) this.g.getLayoutParams();
                layoutParams.gravity = 49;
                layoutParams.topMargin = this.b;
                this.g.setLayoutParams(layoutParams);
                this.i.setVisibility(0);
                this.i.setScaleX(1.0f);
                this.i.setScaleY(1.0f);
            } else {
                layoutParams = (LayoutParams) this.g.getLayoutParams();
                layoutParams.gravity = 17;
                layoutParams.topMargin = this.b;
                this.g.setLayoutParams(layoutParams);
                this.i.setVisibility(4);
                this.i.setScaleX(0.5f);
                this.i.setScaleY(0.5f);
            }
            this.h.setVisibility(4);
        } else if (z) {
            layoutParams = (LayoutParams) this.g.getLayoutParams();
            layoutParams.gravity = 49;
            layoutParams.topMargin = this.b + this.c;
            this.g.setLayoutParams(layoutParams);
            this.i.setVisibility(0);
            this.h.setVisibility(4);
            this.i.setScaleX(1.0f);
            this.i.setScaleY(1.0f);
            this.h.setScaleX(this.d);
            this.h.setScaleY(this.d);
        } else {
            layoutParams = (LayoutParams) this.g.getLayoutParams();
            layoutParams.gravity = 49;
            layoutParams.topMargin = this.b;
            this.g.setLayoutParams(layoutParams);
            this.i.setVisibility(4);
            this.h.setVisibility(0);
            this.i.setScaleX(this.e);
            this.i.setScaleY(this.e);
            this.h.setScaleX(1.0f);
            this.h.setScaleY(1.0f);
        }
        refreshDrawableState();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.h.setEnabled(z);
        this.i.setEnabled(z);
        this.g.setEnabled(z);
        if (z) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        } else {
            ViewCompat.setPointerIcon(this, null);
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.k != null && this.k.isCheckable() && this.k.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, a);
        }
        return onCreateDrawableState;
    }

    public void setShortcut(boolean z, char c) {
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(drawable, this.l);
        }
        this.g.setImageDrawable(drawable);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public boolean showsIcon() {
        return true;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.l = colorStateList;
        if (this.k != null) {
            setIcon(this.k.getIcon());
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.h.setTextColor(colorStateList);
        this.i.setTextColor(colorStateList);
    }

    public void setItemBackground(int i) {
        Drawable drawable;
        if (i == 0) {
            drawable = null;
        } else {
            drawable = ContextCompat.getDrawable(getContext(), i);
        }
        ViewCompat.setBackground(this, drawable);
    }
}
