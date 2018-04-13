package com.c;

import android.content.Context;
import android.view.LayoutInflater;
import com.c.a.c;
import java.util.List;

public abstract class b<T> extends a<T> {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLayoutId;

    protected abstract void convert(c cVar, T t, int i);

    public b(Context context, int i, List<T> list) {
        super(context, list);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = i;
        this.mDatas = list;
        addItemViewDelegate(new e(this, i));
    }
}
