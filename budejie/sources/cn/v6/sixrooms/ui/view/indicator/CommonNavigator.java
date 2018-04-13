package cn.v6.sixrooms.ui.view.indicator;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.ui.view.indicator.NavigatorHelper.OnNavigatorScrollListener;
import java.util.ArrayList;
import java.util.List;

public class CommonNavigator extends FrameLayout implements IPagerNavigator, OnNavigatorScrollListener {
    private HorizontalScrollView a;
    private LinearLayout b;
    private LinearLayout c;
    private IPagerIndicator d;
    private CommonNavigatorAdapter e;
    private NavigatorHelper f = new NavigatorHelper();
    private boolean g;
    private boolean h;
    private float i = 0.85f;
    private boolean j = true;
    private boolean k = true;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p = true;
    private List<PositionData> q = new ArrayList();
    private DataSetObserver r = new a(this);

    public CommonNavigator(Context context) {
        super(context);
        this.f.setNavigatorScrollListener(this);
    }

    public void notifyDataSetChanged() {
        if (this.e != null) {
            this.e.notifyDataSetChanged();
        }
    }

    public boolean isAdjustMode() {
        return this.g;
    }

    public void setAdjustMode(boolean z) {
        this.g = z;
    }

    public CommonNavigatorAdapter getAdapter() {
        return this.e;
    }

    public void setAdapter(CommonNavigatorAdapter commonNavigatorAdapter) {
        if (this.e != commonNavigatorAdapter) {
            if (this.e != null) {
                this.e.unregisterDataSetObserver(this.r);
            }
            this.e = commonNavigatorAdapter;
            if (this.e != null) {
                this.e.registerDataSetObserver(this.r);
                this.f.setTotalCount(this.e.getCount());
                if (this.b != null) {
                    this.e.notifyDataSetChanged();
                    return;
                }
                return;
            }
            this.f.setTotalCount(0);
            a();
        }
    }

    private void a() {
        View inflate;
        removeAllViews();
        if (this.g) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this);
        }
        this.a = (HorizontalScrollView) inflate.findViewById(R.id.scroll_view);
        this.b = (LinearLayout) inflate.findViewById(R.id.title_container);
        this.b.setPadding(this.m, 0, this.l, 0);
        this.c = (LinearLayout) inflate.findViewById(R.id.indicator_container);
        if (this.n) {
            this.c.getParent().bringChildToFront(this.c);
        }
        int totalCount = this.f.getTotalCount();
        for (int i = 0; i < totalCount; i++) {
            IPagerTitleView titleView = this.e.getTitleView(getContext(), i);
            if (titleView instanceof View) {
                LayoutParams layoutParams;
                View view = (View) titleView;
                if (this.g) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    layoutParams.weight = this.e.getTitleWeight(getContext(), i);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                }
                this.b.addView(view, layoutParams);
            }
        }
        if (this.e != null) {
            this.d = this.e.getIndicator(getContext());
            if (this.d instanceof View) {
                this.c.addView((View) this.d, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.e != null) {
            this.q.clear();
            int totalCount = this.f.getTotalCount();
            for (int i5 = 0; i5 < totalCount; i5++) {
                PositionData positionData = new PositionData();
                View childAt = this.b.getChildAt(i5);
                if (childAt != null) {
                    positionData.mLeft = childAt.getLeft();
                    positionData.mTop = childAt.getTop();
                    positionData.mRight = childAt.getRight();
                    positionData.mBottom = childAt.getBottom();
                    if (childAt instanceof IMeasurablePagerTitleView) {
                        IMeasurablePagerTitleView iMeasurablePagerTitleView = (IMeasurablePagerTitleView) childAt;
                        positionData.mContentLeft = iMeasurablePagerTitleView.getContentLeft();
                        positionData.mContentTop = iMeasurablePagerTitleView.getContentTop();
                        positionData.mContentRight = iMeasurablePagerTitleView.getContentRight();
                        positionData.mContentBottom = iMeasurablePagerTitleView.getContentBottom();
                    } else {
                        positionData.mContentLeft = positionData.mLeft;
                        positionData.mContentTop = positionData.mTop;
                        positionData.mContentRight = positionData.mRight;
                        positionData.mContentBottom = positionData.mBottom;
                    }
                }
                this.q.add(positionData);
            }
            if (this.d != null) {
                this.d.onPositionDataProvide(this.q);
            }
            if (this.p && this.f.getScrollState() == 0) {
                onPageSelected(this.f.getCurrentIndex());
                onPageScrolled(this.f.getCurrentIndex(), 0.0f, 0);
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.e != null) {
            this.f.onPageScrolled(i, f, i2);
            if (this.d != null) {
                this.d.onPageScrolled(i, f, i2);
            }
            if (this.a != null && this.q.size() > 0 && i >= 0 && i < this.q.size() && this.k) {
                int min = Math.min(this.q.size() - 1, i);
                float horizontalCenter = ((float) ((PositionData) this.q.get(min)).horizontalCenter()) - (((float) this.a.getWidth()) * this.i);
                this.a.scrollTo((int) (horizontalCenter + (((((float) ((PositionData) this.q.get(Math.min(this.q.size() - 1, i + 1))).horizontalCenter()) - (((float) this.a.getWidth()) * this.i)) - horizontalCenter) * f)), 0);
            }
        }
    }

    public float getScrollPivotX() {
        return this.i;
    }

    public void setScrollPivotX(float f) {
        this.i = f;
    }

    public void onPageSelected(int i) {
        if (this.e != null) {
            this.f.onPageSelected(i);
            if (this.d != null) {
                this.d.onPageSelected(i);
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.e != null) {
            this.f.onPageScrollStateChanged(i);
            if (this.d != null) {
                this.d.onPageScrollStateChanged(i);
            }
        }
    }

    public void onAttachToMagicIndicator() {
        a();
    }

    public void onDetachFromMagicIndicator() {
    }

    public IPagerIndicator getPagerIndicator() {
        return this.d;
    }

    public boolean isEnablePivotScroll() {
        return this.h;
    }

    public void setEnablePivotScroll(boolean z) {
        this.h = z;
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onEnter(i, i2, f, z);
            }
        }
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onLeave(i, i2, f, z);
            }
        }
    }

    public boolean isSmoothScroll() {
        return this.j;
    }

    public void setSmoothScroll(boolean z) {
        this.j = z;
    }

    public boolean isFollowTouch() {
        return this.k;
    }

    public void setFollowTouch(boolean z) {
        this.k = z;
    }

    public boolean isSkimOver() {
        return this.o;
    }

    public void setSkimOver(boolean z) {
        this.o = z;
        this.f.setSkimOver(z);
    }

    public void onSelected(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onSelected(i, i2);
            }
            if (!this.g && !this.k && this.a != null && this.q.size() > 0) {
                PositionData positionData = (PositionData) this.q.get(Math.min(this.q.size() - 1, i));
                if (this.h) {
                    float horizontalCenter = ((float) positionData.horizontalCenter()) - (((float) this.a.getWidth()) * this.i);
                    if (this.j) {
                        this.a.smoothScrollTo((int) horizontalCenter, 0);
                    } else {
                        this.a.scrollTo((int) horizontalCenter, 0);
                    }
                } else if (this.a.getScrollX() > positionData.mLeft) {
                    if (this.j) {
                        this.a.smoothScrollTo(positionData.mLeft, 0);
                    } else {
                        this.a.scrollTo(positionData.mLeft, 0);
                    }
                } else if (this.a.getScrollX() + getWidth() >= positionData.mRight) {
                } else {
                    if (this.j) {
                        this.a.smoothScrollTo(positionData.mRight - getWidth(), 0);
                    } else {
                        this.a.scrollTo(positionData.mRight - getWidth(), 0);
                    }
                }
            }
        }
    }

    public void onDeselected(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof IPagerTitleView) {
                ((IPagerTitleView) childAt).onDeselected(i, i2);
            }
        }
    }

    public IPagerTitleView getPagerTitleView(int i) {
        if (this.b == null) {
            return null;
        }
        return (IPagerTitleView) this.b.getChildAt(i);
    }

    public LinearLayout getTitleContainer() {
        return this.b;
    }

    public int getRightPadding() {
        return this.l;
    }

    public void setRightPadding(int i) {
        this.l = i;
    }

    public int getLeftPadding() {
        return this.m;
    }

    public void setLeftPadding(int i) {
        this.m = i;
    }

    public boolean isIndicatorOnTop() {
        return this.n;
    }

    public void setIndicatorOnTop(boolean z) {
        this.n = z;
    }

    public boolean isReselectWhenLayout() {
        return this.p;
    }

    public void setReselectWhenLayout(boolean z) {
        this.p = z;
    }
}
