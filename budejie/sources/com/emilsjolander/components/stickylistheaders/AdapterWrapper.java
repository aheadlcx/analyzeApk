package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import java.util.LinkedList;
import java.util.List;

class AdapterWrapper extends BaseAdapter implements StickyListHeadersAdapter {
    private final Context mContext;
    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onInvalidated() {
            AdapterWrapper.this.mHeaderCache.clear();
            super.notifyDataSetInvalidated();
        }

        public void onChanged() {
            super.notifyDataSetChanged();
        }
    };
    final StickyListHeadersAdapter mDelegate;
    private Drawable mDivider;
    private int mDividerHeight;
    private final List<View> mHeaderCache = new LinkedList();
    private OnHeaderClickListener mOnHeaderClickListener;

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, int i, long j);
    }

    AdapterWrapper(Context context, StickyListHeadersAdapter stickyListHeadersAdapter) {
        this.mContext = context;
        this.mDelegate = stickyListHeadersAdapter;
        stickyListHeadersAdapter.registerDataSetObserver(this.mDataSetObserver);
    }

    void setDivider(Drawable drawable) {
        this.mDivider = drawable;
    }

    void setDividerHeight(int i) {
        this.mDividerHeight = i;
    }

    public boolean areAllItemsEnabled() {
        return this.mDelegate.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.mDelegate.isEnabled(i);
    }

    public int getCount() {
        return this.mDelegate.getCount();
    }

    public Object getItem(int i) {
        return this.mDelegate.getItem(i);
    }

    public long getItemId(int i) {
        return this.mDelegate.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.mDelegate.hasStableIds();
    }

    public int getItemViewType(int i) {
        return this.mDelegate.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.mDelegate.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.mDelegate.isEmpty();
    }

    private void recycleHeaderIfExists(WrapperView wrapperView) {
        View view = wrapperView.mHeader;
        if (view != null) {
            this.mHeaderCache.add(view);
        }
    }

    private View configureHeader(WrapperView wrapperView, final int i) {
        View headerView = this.mDelegate.getHeaderView(i, wrapperView.mHeader == null ? popHeader() : wrapperView.mHeader, wrapperView);
        if (headerView == null) {
            throw new NullPointerException("Header view must not be null.");
        }
        headerView.setClickable(true);
        headerView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AdapterWrapper.this.mOnHeaderClickListener != null) {
                    AdapterWrapper.this.mOnHeaderClickListener.onHeaderClick(view, i, AdapterWrapper.this.mDelegate.getHeaderId(i));
                }
            }
        });
        return headerView;
    }

    private View popHeader() {
        if (this.mHeaderCache.size() > 0) {
            return (View) this.mHeaderCache.remove(0);
        }
        return null;
    }

    private boolean previousPositionHasSameHeader(int i) {
        return i != 0 && this.mDelegate.getHeaderId(i) == this.mDelegate.getHeaderId(i - 1);
    }

    public WrapperView getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new WrapperView(this.mContext);
        } else {
            WrapperView wrapperView = (WrapperView) view;
        }
        View view2 = this.mDelegate.getView(i, view.mItem, view);
        View view3 = null;
        if (previousPositionHasSameHeader(i)) {
            recycleHeaderIfExists(view);
        } else {
            view3 = configureHeader(view, i);
        }
        if ((view2 instanceof Checkable) && !(view instanceof CheckableWrapperView)) {
            view = new CheckableWrapperView(this.mContext);
        } else if (!(view2 instanceof Checkable) && (view instanceof CheckableWrapperView)) {
            view = new WrapperView(this.mContext);
        }
        view.update(view2, view3, this.mDivider, this.mDividerHeight);
        return view;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mOnHeaderClickListener = onHeaderClickListener;
    }

    public boolean equals(Object obj) {
        return this.mDelegate.equals(obj);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return ((BaseAdapter) this.mDelegate).getDropDownView(i, view, viewGroup);
    }

    public int hashCode() {
        return this.mDelegate.hashCode();
    }

    public void notifyDataSetChanged() {
        ((BaseAdapter) this.mDelegate).notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated() {
        ((BaseAdapter) this.mDelegate).notifyDataSetInvalidated();
    }

    public String toString() {
        return this.mDelegate.toString();
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        return this.mDelegate.getHeaderView(i, view, viewGroup);
    }

    public long getHeaderId(int i) {
        return this.mDelegate.getHeaderId(i);
    }
}
