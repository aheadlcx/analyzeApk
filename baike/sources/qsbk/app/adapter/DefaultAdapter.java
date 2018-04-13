package qsbk.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public abstract class DefaultAdapter<T> extends BaseAdapter {
    protected final Activity k;
    protected ListView l;
    protected ArrayList<T> m;
    protected LayoutInflater n;

    public DefaultAdapter(ArrayList<T> arrayList, Activity activity) {
        this.m = arrayList;
        this.k = activity;
        this.n = LayoutInflater.from(activity);
    }

    public int getCount() {
        return this.m.size();
    }

    public T getItem(int i) {
        if (this.m == null || this.m.size() <= 0) {
            return null;
        }
        return this.m.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void clearImageCache() {
    }

    public void onDestroy() {
    }
}
