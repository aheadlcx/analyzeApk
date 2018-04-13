package com.emilsjolander.components.stickylistheaders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import com.emilsjolander.components.stickylistheaders.AdapterWrapper.OnHeaderClickListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class StickyListHeadersListView extends ListView {
    private AdapterWrapper mAdapter;
    private OnHeaderClickListener mAdapterHeaderClickListener;
    private boolean mAreHeadersSticky;
    private final Rect mClippingRect;
    private Boolean mClippingToPadding;
    private Long mCurrentHeaderId;
    private DataSetObserver mDataSetChangedObserver;
    private Drawable mDivider;
    private int mDividerHeight;
    private boolean mDrawingListUnderStickyHeader;
    private ArrayList<View> mFooterViews;
    private View mHeader;
    private boolean mHeaderBeingPressed;
    private int mHeaderBottomPosition;
    private float mHeaderDownY;
    private Integer mHeaderPosition;
    private StickyListHeadersListView$OnHeaderClickListener mOnHeaderClickListener;
    private OnScrollListener mOnScrollListener;
    private OnScrollListener mOnScrollListenerDelegate;
    private Field mSelectorPositionField;
    private Rect mSelectorRect;
    private ViewConfiguration mViewConfig;

    public StickyListHeadersListView(Context context) {
        this(context, null);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public StickyListHeadersListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAreHeadersSticky = true;
        this.mClippingRect = new Rect();
        this.mCurrentHeaderId = null;
        this.mHeaderDownY = -1.0f;
        this.mHeaderBeingPressed = false;
        this.mDrawingListUnderStickyHeader = true;
        this.mSelectorRect = new Rect();
        this.mAdapterHeaderClickListener = new StickyListHeadersListView$1(this);
        this.mDataSetChangedObserver = new StickyListHeadersListView$2(this);
        this.mOnScrollListener = new StickyListHeadersListView$3(this);
        super.setOnScrollListener(this.mOnScrollListener);
        super.setDivider(null);
        super.setDividerHeight(0);
        this.mViewConfig = ViewConfiguration.get(context);
        if (this.mClippingToPadding == null) {
            this.mClippingToPadding = Boolean.valueOf(true);
        }
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mSelectorRect");
            declaredField.setAccessible(true);
            this.mSelectorRect = (Rect) declaredField.get(this);
            this.mSelectorPositionField = AbsListView.class.getDeclaredField("mSelectorPosition");
            this.mSelectorPositionField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            reset();
            scrollChanged(getFirstVisiblePosition());
        }
    }

    private void reset() {
        this.mHeader = null;
        this.mCurrentHeaderId = null;
        this.mHeaderPosition = null;
        this.mHeaderBottomPosition = -1;
    }

    public boolean performItemClick(View view, int i, long j) {
        if (view instanceof WrapperView) {
            view = ((WrapperView) view).mItem;
        }
        return super.performItemClick(view, i, j);
    }

    public void setSelectionFromTop(int i, int i2) {
        if (hasStickyHeaderAtPosition(i)) {
            i2 += getHeaderHeight();
        }
        super.setSelectionFromTop(i, i2);
    }

    @SuppressLint({"NewApi"})
    public void smoothScrollToPositionFromTop(int i, int i2) {
        if (hasStickyHeaderAtPosition(i)) {
            i2 += getHeaderHeight();
        }
        super.smoothScrollToPositionFromTop(i, i2);
    }

    @SuppressLint({"NewApi"})
    public void smoothScrollToPositionFromTop(int i, int i2, int i3) {
        if (hasStickyHeaderAtPosition(i)) {
            i2 += getHeaderHeight();
        }
        super.smoothScrollToPositionFromTop(i, i2, i3);
    }

    private boolean hasStickyHeaderAtPosition(int i) {
        int headerViewsCount = i - getHeaderViewsCount();
        return this.mAreHeadersSticky && headerViewsCount > 0 && headerViewsCount < this.mAdapter.getCount() && this.mAdapter.getHeaderId(headerViewsCount) == this.mAdapter.getHeaderId(headerViewsCount - 1);
    }

    public void setDivider(Drawable drawable) {
        this.mDivider = drawable;
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight >= 0) {
                setDividerHeight(intrinsicHeight);
            }
        }
        if (this.mAdapter != null) {
            this.mAdapter.setDivider(drawable);
            requestLayout();
            invalidate();
        }
    }

    public void setDividerHeight(int i) {
        this.mDividerHeight = i;
        if (this.mAdapter != null) {
            this.mAdapter.setDividerHeight(i);
            requestLayout();
            invalidate();
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListenerDelegate = onScrollListener;
    }

    public void setAreHeadersSticky(boolean z) {
        if (this.mAreHeadersSticky != z) {
            this.mAreHeadersSticky = z;
            requestLayout();
        }
    }

    public boolean getAreHeadersSticky() {
        return this.mAreHeadersSticky;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (isInEditMode()) {
            super.setAdapter(listAdapter);
        } else if (listAdapter == null) {
            this.mAdapter = null;
            reset();
            super.setAdapter(null);
        } else if (listAdapter instanceof StickyListHeadersAdapter) {
            this.mAdapter = wrapAdapter(listAdapter);
            reset();
            super.setAdapter(this.mAdapter);
        } else {
            throw new IllegalArgumentException("Adapter must implement StickyListHeadersAdapter");
        }
    }

    private AdapterWrapper wrapAdapter(ListAdapter listAdapter) {
        AdapterWrapper sectionIndexerAdapterWrapper;
        if (listAdapter instanceof SectionIndexer) {
            sectionIndexerAdapterWrapper = new SectionIndexerAdapterWrapper(getContext(), (StickyListHeadersAdapter) listAdapter);
        } else {
            sectionIndexerAdapterWrapper = new AdapterWrapper(getContext(), (StickyListHeadersAdapter) listAdapter);
        }
        sectionIndexerAdapterWrapper.setDivider(this.mDivider);
        sectionIndexerAdapterWrapper.setDividerHeight(this.mDividerHeight);
        sectionIndexerAdapterWrapper.registerDataSetObserver(this.mDataSetChangedObserver);
        sectionIndexerAdapterWrapper.setOnHeaderClickListener(this.mAdapterHeaderClickListener);
        return sectionIndexerAdapterWrapper;
    }

    public StickyListHeadersAdapter getWrappedAdapter() {
        return this.mAdapter == null ? null : this.mAdapter.mDelegate;
    }

    public View getWrappedView(int i) {
        View childAt = getChildAt(i);
        if (childAt instanceof WrapperView) {
            return ((WrapperView) childAt).mItem;
        }
        return childAt;
    }

    protected void dispatchDraw(Canvas canvas) {
        if (VERSION.SDK_INT < 8) {
            scrollChanged(getFirstVisiblePosition());
        }
        positionSelectorRect();
        if (!this.mAreHeadersSticky || this.mHeader == null) {
            super.dispatchDraw(canvas);
            return;
        }
        if (!this.mDrawingListUnderStickyHeader) {
            this.mClippingRect.set(0, this.mHeaderBottomPosition, getWidth(), getHeight());
            canvas.save();
            canvas.clipRect(this.mClippingRect);
        }
        super.dispatchDraw(canvas);
        if (!this.mDrawingListUnderStickyHeader) {
            canvas.restore();
        }
        drawStickyHeader(canvas);
    }

    private void positionSelectorRect() {
        if (!this.mSelectorRect.isEmpty()) {
            int selectorPosition = getSelectorPosition();
            if (selectorPosition >= 0) {
                View childAt = getChildAt(selectorPosition - fixedFirstVisibleItem(getFirstVisiblePosition()));
                if (childAt instanceof WrapperView) {
                    WrapperView wrapperView = (WrapperView) childAt;
                    this.mSelectorRect.top = wrapperView.mItemTop + wrapperView.getTop();
                }
            }
        }
    }

    private int getSelectorPosition() {
        if (this.mSelectorPositionField == null) {
            for (int i = 0; i < getChildCount(); i++) {
                if (getChildAt(i).getBottom() == this.mSelectorRect.bottom) {
                    return i + fixedFirstVisibleItem(getFirstVisiblePosition());
                }
            }
        } else {
            try {
                return this.mSelectorPositionField.getInt(this);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }

    private void drawStickyHeader(Canvas canvas) {
        int headerHeight = getHeaderHeight();
        int i = this.mHeaderBottomPosition - headerHeight;
        this.mClippingRect.left = getPaddingLeft();
        this.mClippingRect.right = getWidth() - getPaddingRight();
        this.mClippingRect.bottom = headerHeight + i;
        this.mClippingRect.top = this.mClippingToPadding.booleanValue() ? getPaddingTop() : 0;
        canvas.save();
        canvas.clipRect(this.mClippingRect);
        canvas.translate((float) getPaddingLeft(), (float) i);
        this.mHeader.draw(canvas);
        canvas.restore();
    }

    private void measureHeader() {
        int makeMeasureSpec;
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(((getWidth() - getPaddingLeft()) - getPaddingRight()) - (isScrollBarOverlay() ? 0 : getVerticalScrollbarWidth()), 1073741824);
        LayoutParams layoutParams = this.mHeader.getLayoutParams();
        if (layoutParams == null) {
            this.mHeader.setLayoutParams(new MarginLayoutParams(-1, -2));
        }
        if (layoutParams == null || layoutParams.height <= 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        }
        this.mHeader.measure(makeMeasureSpec2, makeMeasureSpec);
        this.mHeader.layout(getPaddingLeft(), 0, getWidth() - getPaddingRight(), this.mHeader.getMeasuredHeight());
    }

    private boolean isScrollBarOverlay() {
        int scrollBarStyle = getScrollBarStyle();
        return scrollBarStyle == 0 || scrollBarStyle == 33554432;
    }

    private int getHeaderHeight() {
        return this.mHeader == null ? 0 : this.mHeader.getMeasuredHeight();
    }

    public void setClipToPadding(boolean z) {
        super.setClipToPadding(z);
        this.mClippingToPadding = Boolean.valueOf(z);
    }

    private void scrollChanged(int i) {
        int i2 = 0;
        int count = this.mAdapter == null ? 0 : this.mAdapter.getCount();
        if (count != 0 && this.mAreHeadersSticky) {
            int headerViewsCount = getHeaderViewsCount();
            int fixedFirstVisibleItem = fixedFirstVisibleItem(i) - headerViewsCount;
            if (fixedFirstVisibleItem < 0 || fixedFirstVisibleItem > count - 1) {
                reset();
                updateHeaderVisibilities();
                invalidate();
                return;
            }
            if (this.mHeaderPosition == null || this.mHeaderPosition.intValue() != fixedFirstVisibleItem) {
                this.mHeaderPosition = Integer.valueOf(fixedFirstVisibleItem);
                this.mCurrentHeaderId = Long.valueOf(this.mAdapter.getHeaderId(fixedFirstVisibleItem));
                this.mHeader = this.mAdapter.getHeaderView(this.mHeaderPosition.intValue(), this.mHeader, this);
                measureHeader();
            }
            int childCount = getChildCount();
            if (childCount != 0) {
                View view = null;
                int i3 = Integer.MAX_VALUE;
                int i4 = 0;
                int i5 = 0;
                while (i4 < childCount) {
                    int i6;
                    int i7;
                    View childAt = super.getChildAt(i4);
                    if (this.mFooterViews == null || !this.mFooterViews.contains(childAt)) {
                        i6 = 0;
                    } else {
                        i6 = 1;
                    }
                    int top = childAt.getTop();
                    if (this.mClippingToPadding.booleanValue()) {
                        count = getPaddingTop();
                    } else {
                        count = 0;
                    }
                    top -= count;
                    if (top < 0 || (view != null && ((i5 != 0 || ((WrapperView) view).hasHeader()) && ((i6 == 0 && !((WrapperView) childAt).hasHeader()) || top >= i3)))) {
                        count = i5;
                        childAt = view;
                        i7 = i3;
                    } else {
                        count = i6;
                        i7 = top;
                    }
                    i4++;
                    i3 = i7;
                    i5 = count;
                    view = childAt;
                }
                int headerHeight = getHeaderHeight();
                if (view == null || (i5 == 0 && !((WrapperView) view).hasHeader())) {
                    if (this.mClippingToPadding.booleanValue()) {
                        i2 = getPaddingTop();
                    }
                    this.mHeaderBottomPosition = headerHeight + i2;
                } else if (fixedFirstVisibleItem != headerViewsCount || super.getChildAt(0).getTop() <= 0 || this.mClippingToPadding.booleanValue()) {
                    if (this.mClippingToPadding.booleanValue()) {
                        i2 = getPaddingTop();
                    }
                    this.mHeaderBottomPosition = Math.min(view.getTop(), headerHeight + i2);
                    if (this.mHeaderBottomPosition < i2) {
                        count = headerHeight + i2;
                    } else {
                        count = this.mHeaderBottomPosition;
                    }
                    this.mHeaderBottomPosition = count;
                } else {
                    this.mHeaderBottomPosition = 0;
                }
            }
            updateHeaderVisibilities();
            invalidate();
        }
    }

    public void addFooterView(View view) {
        super.addFooterView(view);
        if (this.mFooterViews == null) {
            this.mFooterViews = new ArrayList();
        }
        this.mFooterViews.add(view);
    }

    public boolean removeFooterView(View view) {
        if (!super.removeFooterView(view)) {
            return false;
        }
        this.mFooterViews.remove(view);
        return true;
    }

    private void updateHeaderVisibilities() {
        int paddingTop = this.mClippingToPadding.booleanValue() ? getPaddingTop() : 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = super.getChildAt(i);
            if (childAt instanceof WrapperView) {
                WrapperView wrapperView = (WrapperView) childAt;
                if (wrapperView.hasHeader()) {
                    View view = wrapperView.mHeader;
                    if (wrapperView.getTop() < paddingTop) {
                        view.setVisibility(4);
                    } else {
                        view.setVisibility(0);
                    }
                }
            }
        }
    }

    private int fixedFirstVisibleItem(int i) {
        if (VERSION.SDK_INT >= 11) {
            return i;
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2).getBottom() >= 0) {
                i += i2;
                break;
            }
        }
        if (this.mClippingToPadding.booleanValue() || getPaddingTop() <= 0 || super.getChildAt(0).getTop() <= 0 || i <= 0) {
            return i;
        }
        return i - 1;
    }

    public void setOnHeaderClickListener(StickyListHeadersListView$OnHeaderClickListener stickyListHeadersListView$OnHeaderClickListener) {
        this.mOnHeaderClickListener = stickyListHeadersListView$OnHeaderClickListener;
    }

    public void setDrawingListUnderStickyHeader(boolean z) {
        this.mDrawingListUnderStickyHeader = z;
    }

    public boolean isDrawingListUnderStickyHeader() {
        return this.mDrawingListUnderStickyHeader;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 || motionEvent.getY() > ((float) this.mHeaderBottomPosition)) {
            if (this.mHeaderBeingPressed) {
                if (Math.abs(motionEvent.getY() - this.mHeaderDownY) >= ((float) this.mViewConfig.getScaledTouchSlop())) {
                    this.mHeaderDownY = -1.0f;
                    this.mHeaderBeingPressed = false;
                    this.mHeader.setPressed(false);
                    this.mHeader.invalidate();
                    invalidate(0, 0, getWidth(), this.mHeaderBottomPosition);
                } else if (action != 1 && action != 3) {
                    return true;
                } else {
                    this.mHeaderDownY = -1.0f;
                    this.mHeaderBeingPressed = false;
                    this.mHeader.setPressed(false);
                    this.mHeader.invalidate();
                    invalidate(0, 0, getWidth(), this.mHeaderBottomPosition);
                    if (this.mOnHeaderClickListener == null) {
                        return true;
                    }
                    this.mOnHeaderClickListener.onHeaderClick(this, this.mHeader, this.mHeaderPosition.intValue(), this.mCurrentHeaderId.longValue(), true);
                    return true;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        this.mHeaderDownY = motionEvent.getY();
        this.mHeaderBeingPressed = true;
        this.mHeader.setPressed(true);
        this.mHeader.invalidate();
        invalidate(0, 0, getWidth(), this.mHeaderBottomPosition);
        return true;
    }
}
