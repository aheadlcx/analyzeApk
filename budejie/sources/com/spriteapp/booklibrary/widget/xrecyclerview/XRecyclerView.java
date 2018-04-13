package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class XRecyclerView extends RecyclerView {
    private static final float DRAG_RATE = 1.8f;
    private static final int HEADER_INIT_INDEX = 10002;
    private static final int TYPE_FOOTER = 10001;
    private static final int TYPE_REFRESH_HEADER = 10000;
    private static List<Integer> sHeaderTypes = new ArrayList();
    private boolean isLoadingData;
    private boolean isNoMore;
    private boolean isRefreshData;
    private boolean loadingMoreEnabled;
    private final AdapterDataObserver mDataObserver;
    private View mEmptyView;
    private View mFootView;
    private ArrayList<View> mHeaderViews;
    private long mLastRefreshTime;
    private float mLastY;
    private XRecyclerView$LoadingListener mLoadingListener;
    private int mPageCount;
    private ArrowRefreshHeader mRefreshHeader;
    private XRecyclerView$WrapAdapter mWrapAdapter;
    private boolean pullRefreshEnabled;
    private XRecyclerView$ScrollAlphaChangeListener scrollAlphaChangeListener;
    private int scrollDyCounter;

    public XRecyclerView(Context context) {
        this(context, null);
    }

    public XRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isLoadingData = false;
        this.isNoMore = false;
        this.mHeaderViews = new ArrayList();
        this.mLastY = -1.0f;
        this.pullRefreshEnabled = true;
        this.loadingMoreEnabled = true;
        this.mLastRefreshTime = System.currentTimeMillis();
        this.mPageCount = 0;
        this.mDataObserver = new XRecyclerView$DataObserver(this, null);
        this.isRefreshData = false;
        this.scrollDyCounter = 0;
        init();
    }

    private void init() {
        if (this.pullRefreshEnabled) {
            this.mRefreshHeader = new ArrowRefreshHeader(getContext());
        }
        this.mFootView = new LoadingMoreFooter(getContext());
        this.mFootView.setVisibility(8);
    }

    public void setFootViewText(String str, String str2) {
        if (this.mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) this.mFootView).setLoadingHint(str);
            ((LoadingMoreFooter) this.mFootView).setNoMoreHint(str2);
        }
    }

    public void addHeaderView(View view) {
        sHeaderTypes.add(Integer.valueOf(this.mHeaderViews.size() + 10002));
        this.mHeaderViews.add(view);
        if (this.mWrapAdapter != null) {
            this.mWrapAdapter.notifyDataSetChanged();
        }
    }

    public void setRefreshHeaderBackground() {
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.setBackground();
        }
    }

    private View getHeaderViewByType(int i) {
        if (isHeaderType(i)) {
            return (View) this.mHeaderViews.get(i - 10002);
        }
        return null;
    }

    private boolean isHeaderType(int i) {
        return this.mHeaderViews.size() > 0 && sHeaderTypes.contains(Integer.valueOf(i));
    }

    private boolean isReservedItemViewType(int i) {
        if (i == 10000 || i == 10001 || sHeaderTypes.contains(Integer.valueOf(i))) {
            return true;
        }
        return false;
    }

    public void setFootView(View view) {
        this.mFootView = view;
    }

    public void loadMoreComplete() {
        this.isLoadingData = false;
        if (this.mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) this.mFootView).setState(1);
        } else {
            this.mFootView.setVisibility(8);
        }
    }

    public void setNoMore(boolean z) {
        this.isLoadingData = false;
        this.isNoMore = z;
        if (this.mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) this.mFootView).setState(this.isNoMore ? 2 : 1);
        } else {
            this.mFootView.setVisibility(8);
        }
    }

    public void refresh() {
        if (this.pullRefreshEnabled && this.mLoadingListener != null && !this.isRefreshData) {
            this.isRefreshData = true;
            this.mRefreshHeader.setState(2);
            this.mRefreshHeader.setVisibleHeight(this.mRefreshHeader.mMeasuredHeight);
            this.mRefreshHeader.setLastRefreshTime(this.mLastRefreshTime);
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager != null) {
                layoutManager.scrollToPosition(0);
            }
            this.mLoadingListener.onRefresh();
        }
    }

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    public void refreshComplete() {
        this.isRefreshData = false;
        this.mRefreshHeader.refreshComplete();
        this.mLastRefreshTime = System.currentTimeMillis();
        setNoMore(false);
    }

    public void setRefreshHeader(ArrowRefreshHeader arrowRefreshHeader) {
        this.mRefreshHeader = arrowRefreshHeader;
    }

    public void setPullRefreshEnabled(boolean z) {
        this.pullRefreshEnabled = z;
    }

    public void setLoadingMoreEnabled(boolean z) {
        this.loadingMoreEnabled = z;
        if (!z && (this.mFootView instanceof LoadingMoreFooter)) {
            ((LoadingMoreFooter) this.mFootView).setState(1);
        }
    }

    public void setArrowImageView(int i) {
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.setArrowImageView(i);
        }
    }

    public void setEmptyView(View view) {
        this.mEmptyView = view;
        this.mDataObserver.onChanged();
    }

    public View getEmptyView() {
        return this.mEmptyView;
    }

    public void setAdapter(Adapter adapter) {
        this.mWrapAdapter = new XRecyclerView$WrapAdapter(this, adapter);
        super.setAdapter(this.mWrapAdapter);
        adapter.registerAdapterDataObserver(this.mDataObserver);
        this.mDataObserver.onChanged();
    }

    public Adapter getAdapter() {
        if (this.mWrapAdapter != null) {
            return this.mWrapAdapter.getOriginalAdapter();
        }
        return null;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (this.mWrapAdapter != null && (layoutManager instanceof GridLayoutManager)) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new XRecyclerView$1(this, gridLayoutManager));
        }
    }

    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        if (i == 0 && this.mLoadingListener != null && !this.isLoadingData && this.loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int findLastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                findLastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(iArr);
                findLastVisibleItemPosition = findMax(iArr);
            } else {
                findLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0 && r0 >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount() && !this.isNoMore && this.mRefreshHeader.getState() < 2) {
                this.isLoadingData = true;
                if (this.mFootView instanceof LoadingMoreFooter) {
                    ((LoadingMoreFooter) this.mFootView).setState(0);
                } else {
                    this.mFootView.setVisibility(0);
                }
                this.mLoadingListener.onLoadMore();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mLastY == -1.0f) {
            this.mLastY = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastY = motionEvent.getRawY();
                break;
            case 2:
                float rawY = motionEvent.getRawY() - this.mLastY;
                this.mLastY = motionEvent.getRawY();
                if (isOnTop() && this.pullRefreshEnabled) {
                    this.mRefreshHeader.onMove(rawY / DRAG_RATE);
                    this.mRefreshHeader.setLastRefreshTime(this.mLastRefreshTime);
                    if (this.mRefreshHeader.getVisibleHeight() > 0 && this.mRefreshHeader.getState() < 2) {
                        return false;
                    }
                }
            default:
                this.mLastY = -1.0f;
                if (isOnTop() && this.pullRefreshEnabled && this.mRefreshHeader.releaseAction() && this.mLoadingListener != null) {
                    this.mLoadingListener.onRefresh();
                    this.mRefreshHeader.setLastRefreshTime(this.mLastRefreshTime);
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    private int findMax(int[] iArr) {
        int i = iArr[0];
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i3 <= i) {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    private boolean isOnTop() {
        if (this.mRefreshHeader.getParent() != null) {
            return true;
        }
        return false;
    }

    public void setLoadingListener(XRecyclerView$LoadingListener xRecyclerView$LoadingListener) {
        this.mLoadingListener = xRecyclerView$LoadingListener;
    }

    public void scrollToPosition(int i) {
        super.scrollToPosition(i);
        if (i == 0) {
            this.scrollDyCounter = 0;
        }
    }

    public void onScrolled(int i, int i2) {
        super.onScrolled(i, i2);
        if (this.scrollAlphaChangeListener != null) {
            int limitHeight = this.scrollAlphaChangeListener.setLimitHeight();
            this.scrollDyCounter += i2;
            if (this.scrollDyCounter <= 0) {
                this.scrollAlphaChangeListener.onAlphaChange(0);
            } else if (this.scrollDyCounter > limitHeight || this.scrollDyCounter <= 0) {
                this.scrollAlphaChangeListener.onAlphaChange(255);
            } else {
                this.scrollAlphaChangeListener.onAlphaChange((int) ((((float) this.scrollDyCounter) / ((float) limitHeight)) * 255.0f));
            }
        }
    }

    public void setScrollAlphaChangeListener(XRecyclerView$ScrollAlphaChangeListener xRecyclerView$ScrollAlphaChangeListener) {
        this.scrollAlphaChangeListener = xRecyclerView$ScrollAlphaChangeListener;
    }
}
