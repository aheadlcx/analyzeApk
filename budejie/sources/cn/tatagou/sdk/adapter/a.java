package cn.tatagou.sdk.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class a<T> extends BaseAdapter {
    protected Context a;
    protected Activity b;
    protected List<T> c;
    protected LayoutInflater d;

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public a(Context context, List<T> list) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.c = list;
    }

    public a(Activity activity, List<T> list) {
        this.b = activity;
        this.c = list;
    }

    public void addItems(List<T> list) {
        if (list != null) {
            if (this.c == null) {
                this.c = list;
            } else {
                this.c.addAll(list);
            }
            notifyDataSetChanged();
        }
    }

    public void setItems(List<T> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public Object getItem(int i) {
        if (this.c == null) {
            return null;
        }
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (dataSetObserver != null) {
            super.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
