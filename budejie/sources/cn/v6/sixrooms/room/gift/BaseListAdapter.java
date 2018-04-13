package cn.v6.sixrooms.room.gift;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;

    public BaseListAdapter(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Invalid param: BaseListAdapter context cannot be null");
        }
        this.mContext = context;
    }

    public BaseListAdapter(Context context, List<T> list) {
        this(context);
        this.mData = list;
    }

    public void setData(List<T> list) {
        this.mData = list;
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public T getItem(int i) {
        if (this.mData == null || i < 0 || i >= this.mData.size()) {
            return null;
        }
        return this.mData.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public int getCount() {
        if (this.mData != null) {
            return this.mData.size();
        }
        return 0;
    }
}
