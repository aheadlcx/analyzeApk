package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.view.SixroomRefreshLoadingView;
import com.handmark.pulltorefresh.library.view.SixroomRefreshPullView;

@SuppressLint({"ViewConstructor"})
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    static final String LOG_TAG = "PullToRefresh-LoadingLayout";
    private RelativeLayout mFooterLayout = ((RelativeLayout) findViewById(R.id.fl_footer_layout));
    private FrameLayout mHeaderLayout = ((FrameLayout) findViewById(R.id.fl_inner));
    protected SixroomRefreshLoadingView mLoadingView;
    protected final Mode mMode;
    private CharSequence mPullLabel;
    protected SixroomRefreshPullView mPullView;
    private CharSequence mRefreshingLabel;
    private CharSequence mReleaseLabel;
    protected final Orientation mScrollDirection;
    protected ImageView mUpImageView;
    protected ProgressBar mUpProgress;
    protected TextView mUpTextView;
    private boolean mUseIntrinsicAnimation;

    protected abstract int getDefaultDrawableResId();

    protected abstract void onLoadingDrawableSet(Drawable drawable);

    protected abstract void onPullImpl(float f);

    protected abstract void pullToRefreshImpl();

    protected abstract void refreshingImpl();

    protected abstract void releaseToRefreshImpl();

    protected abstract void resetImpl();

    public LoadingLayout(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        Drawable drawable;
        ColorStateList colorStateList;
        super(context);
        this.mScrollDirection = orientation;
        this.mMode = mode;
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_sixroom, this);
        if (mode == Mode.PULL_FROM_START) {
            this.mHeaderLayout.setVisibility(0);
            this.mFooterLayout.setVisibility(4);
        } else {
            this.mHeaderLayout.setVisibility(8);
            this.mFooterLayout.setVisibility(0);
        }
        this.mPullView = (SixroomRefreshPullView) findViewById(R.id.id_sixroom_pull_view);
        this.mLoadingView = (SixroomRefreshLoadingView) findViewById(R.id.id_sixroom_loading_view);
        this.mLoadingView.setVisibility(8);
        LayoutParams layoutParams = (LayoutParams) this.mHeaderLayout.getLayoutParams();
        this.mUpImageView = (ImageView) findViewById(R.id.pull_to_load_image);
        this.mUpProgress = (ProgressBar) findViewById(R.id.pull_to_load_progress);
        this.mUpTextView = (TextView) findViewById(R.id.pull_to_load_text);
        switch (mode) {
            case PULL_FROM_END:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? 48 : 3;
                this.mPullLabel = context.getString(R.string.pull_to_refresh_from_bottom_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_from_bottom_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_from_bottom_release_label);
                break;
            default:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? 80 : 5;
                this.mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
                this.mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
                this.mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);
                break;
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderBackground)) {
            drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrHeaderBackground);
            if (drawable != null) {
                ViewCompat.setBackground(this, drawable);
            }
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance, typedValue);
            setTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance)) {
            typedValue = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance, typedValue);
            setSubTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextColor)) {
            colorStateList = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderTextColor);
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderSubTextColor)) {
            colorStateList = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderSubTextColor);
            if (colorStateList != null) {
                setSubTextColor(colorStateList);
            }
        }
        drawable = null;
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawable)) {
            drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawable);
        }
        switch (mode) {
            case PULL_FROM_END:
                if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableEnd)) {
                    if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableBottom)) {
                        Utils.warnDeprecation("ptrDrawableBottom", "ptrDrawableEnd");
                        drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableBottom);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableEnd);
                break;
                break;
            default:
                if (!typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableStart)) {
                    if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableTop)) {
                        Utils.warnDeprecation("ptrDrawableTop", "ptrDrawableStart");
                        drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableTop);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableStart);
                break;
                break;
        }
        if (drawable == null) {
            drawable = context.getResources().getDrawable(getDefaultDrawableResId());
        }
        setLoadingDrawable(drawable);
        reset();
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final int getContentSize() {
        switch (this.mMode) {
            case PULL_FROM_END:
                switch (this.mScrollDirection) {
                    case HORIZONTAL:
                        return this.mFooterLayout.getWidth();
                    default:
                        return this.mFooterLayout.getHeight();
                }
            default:
                switch (this.mScrollDirection) {
                    case HORIZONTAL:
                        return this.mHeaderLayout.getWidth();
                    default:
                        return this.mHeaderLayout.getHeight();
                }
        }
    }

    public final void hideAllViews() {
        if (this.mMode == Mode.PULL_FROM_END && this.mFooterLayout.getVisibility() == 0) {
            this.mFooterLayout.setVisibility(4);
        }
    }

    public final void onPull(float f) {
        onPullImpl(f);
    }

    public final void pullToRefresh() {
        pullToRefreshImpl();
    }

    public final void refreshing() {
        if (!this.mUseIntrinsicAnimation) {
            refreshingImpl();
        }
    }

    public final void releaseToRefresh() {
        releaseToRefreshImpl();
    }

    public final void reset() {
        if (!this.mUseIntrinsicAnimation) {
            resetImpl();
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.mUseIntrinsicAnimation = drawable instanceof AnimationDrawable;
        onLoadingDrawableSet(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.mPullLabel = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.mRefreshingLabel = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.mReleaseLabel = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
    }

    public final void showInvisibleViews() {
        if (this.mMode == Mode.PULL_FROM_END && 4 == this.mFooterLayout.getVisibility()) {
            this.mFooterLayout.setVisibility(0);
        }
    }

    private void setSubHeaderText(CharSequence charSequence) {
    }

    private void setSubTextAppearance(int i) {
    }

    private void setSubTextColor(ColorStateList colorStateList) {
    }

    private void setTextAppearance(int i) {
    }

    private void setTextColor(ColorStateList colorStateList) {
    }
}
