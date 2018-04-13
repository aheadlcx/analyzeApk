package com.zhihu.matisse.internal.ui.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;

public abstract class RecyclerViewCursorAdapter<VH extends ViewHolder> extends Adapter<VH> {
    private Cursor mCursor;
    private int mRowIDColumn;

    protected abstract int getItemViewType(int i, Cursor cursor);

    protected abstract void onBindViewHolder(VH vh, Cursor cursor);

    public RecyclerViewCursorAdapter(Cursor cursor) {
        setHasStableIds(true);
        swapCursor(cursor);
    }

    public void onBindViewHolder(VH vh, int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot bind view holder when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            onBindViewHolder((ViewHolder) vh, this.mCursor);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to bind view holder");
        }
    }

    public int getItemViewType(int i) {
        if (this.mCursor.moveToPosition(i)) {
            return getItemViewType(i, this.mCursor);
        }
        throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get item view type.");
    }

    public int getItemCount() {
        if (isDataValid(this.mCursor)) {
            return this.mCursor.getCount();
        }
        return 0;
    }

    public long getItemId(int i) {
        if (!isDataValid(this.mCursor)) {
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        } else if (this.mCursor.moveToPosition(i)) {
            return this.mCursor.getLong(this.mRowIDColumn);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get an item id");
        }
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != this.mCursor) {
            if (cursor != null) {
                this.mCursor = cursor;
                this.mRowIDColumn = this.mCursor.getColumnIndexOrThrow("_id");
                notifyDataSetChanged();
                return;
            }
            notifyItemRangeRemoved(0, getItemCount());
            this.mCursor = null;
            this.mRowIDColumn = -1;
        }
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    private boolean isDataValid(Cursor cursor) {
        return (cursor == null || cursor.isClosed()) ? false : true;
    }
}
