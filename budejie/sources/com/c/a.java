package com.c;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.c.a.b;
import com.c.a.c;
import com.handmark.pulltorefresh.library.R;
import java.util.List;

public class a<T> extends Adapter<c> {
    private com.c.c.a a = new com.c.c.a(null);
    private boolean b;
    private FrameLayout c;
    private LinearLayout d;
    private boolean e;
    protected Context mContext;
    protected List<T> mDatas;
    protected b mItemViewDelegateManager = new b();
    protected a mOnItemClickListener;

    public interface a {
        void a(View view, ViewHolder viewHolder, int i);

        boolean b(View view, ViewHolder viewHolder, int i);
    }

    public a(Context context, List<T> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    public int getItemViewType(int i) {
        if (!useItemViewDelegateManager()) {
            return super.getItemViewType(i);
        }
        if (a() == 1) {
            switch (i) {
                case 0:
                    if (b() != 1) {
                        return 1000;
                    }
                    return 1001;
                case 1:
                    if (b() == 1) {
                        return 1000;
                    }
                    break;
            }
        }
        if (i < b()) {
            return 1001;
        }
        int b = i - b();
        if (b < this.mDatas.size()) {
            return this.mItemViewDelegateManager.a(this.mDatas.get(b), b);
        }
        return 1002;
    }

    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        c a;
        if (i == 1000) {
            a = c.a(this.mContext, this.c);
            setListener(viewGroup, a, i);
            return a;
        } else if (i == 1001) {
            a = c.a(this.mContext, this.d);
            setListener(viewGroup, a, i);
            return a;
        } else if (i == 1002) {
            a = c.a(this.mContext, viewGroup, R.layout.auto_load_more_footer);
            onViewHolderCreated(a, a.a());
            setListener(viewGroup, a, i);
            return a;
        } else {
            com.c.a.a a2 = this.mItemViewDelegateManager.a(i);
            a = c.a(this.mContext, viewGroup, a2.a());
            onViewHolderCreated(a, a.a());
            a2.a(a, a.a());
            setListener(viewGroup, a, i);
            return a;
        }
    }

    public void onViewHolderCreated(c cVar, View view) {
    }

    public void convert(c cVar, T t) {
        this.mItemViewDelegateManager.a(cVar, t, cVar.getAdapterPosition());
    }

    protected boolean isEnabled(int i) {
        return true;
    }

    protected void setListener(ViewGroup viewGroup, c cVar, int i) {
        if (isEnabled(i)) {
            cVar.a().setOnClickListener(new c(this, cVar));
            cVar.a().setOnLongClickListener(new d(this, cVar));
        }
    }

    public void onBindViewHolder(c cVar, int i) {
        switch (cVar.getItemViewType()) {
            case 1000:
            case 1001:
                return;
            case 1002:
                this.a.a(cVar);
                return;
            default:
                convert(cVar, this.mDatas.get(i - b()));
                return;
        }
    }

    public int getItemCount() {
        if (a() == 1) {
            if (b() == 1) {
                return 2;
            }
            return 1;
        } else if (!this.b || this.mDatas.size() <= 0) {
            return this.mDatas.size() + b();
        } else {
            return (this.mDatas.size() + b()) + 1;
        }
    }

    public int getFooterPosition() {
        if (!this.b || this.mDatas.size() <= 0) {
            return -1;
        }
        return this.mDatas.size() + b();
    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    public a addItemViewDelegate(com.c.a.a<T> aVar) {
        this.mItemViewDelegateManager.a((com.c.a.a) aVar);
        return this;
    }

    public a addItemViewDelegate(int i, com.c.a.a<T> aVar) {
        this.mItemViewDelegateManager.a(i, (com.c.a.a) aVar);
        return this;
    }

    protected boolean useItemViewDelegateManager() {
        return this.mItemViewDelegateManager.a() > 0;
    }

    public com.c.c.a getFooterDelegate() {
        return this.a;
    }

    public boolean isAutoLoadMoreEnabled() {
        return this.b;
    }

    public void setAutoLoadMoreEnabled(boolean z) {
        this.b = z;
    }

    public void setEmptyView(View view) {
        if (this.c == null) {
            this.c = new FrameLayout(this.mContext);
            this.c.setLayoutParams(new LayoutParams(-1, -1));
        }
        this.c.removeAllViews();
        this.c.addView(view);
    }

    public void addHeaderView(View view) {
        addHeaderView(view, -1);
    }

    public void addHeaderView(View view, int i) {
        if (this.d == null) {
            this.d = new LinearLayout(this.mContext);
        }
        this.d.setOrientation(1);
        this.d.setLayoutParams(new LayoutParams(-1, -2));
        int childCount = this.d.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.d.addView(view, i);
    }

    public void setHeaderView(View view) {
        if (this.d != null) {
            this.d.removeAllViews();
        }
        addHeaderView(view);
    }

    public boolean isScrollEnabled() {
        return this.e;
    }

    private int a() {
        this.e = true;
        if (this.c == null || this.c.getChildCount() == 0 || this.mDatas.size() != 0) {
            return 0;
        }
        this.e = false;
        return 1;
    }

    private int b() {
        if (this.d == null || this.d.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public void setOnItemClickListener(a aVar) {
        this.mOnItemClickListener = aVar;
    }
}
