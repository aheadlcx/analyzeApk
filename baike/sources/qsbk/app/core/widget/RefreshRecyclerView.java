package qsbk.app.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import qsbk.app.core.R;
import qsbk.app.core.adapter.BaseRecyclerViewAdapter;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;

public class RefreshRecyclerView<T> extends SwipeRefreshLayoutBoth {
    private BaseRecyclerViewAdapter c;
    private boolean d = false;
    private boolean e = true;
    private int f = 1;
    private int g;
    private int h = 1;
    private List<T> i;
    private RecyclerView j;
    private GridLayoutManager k;
    private Handler l = new Handler();
    private int m = 1;
    private String n;
    private Map<String, String> o;
    private String p;
    private RefreshListener q;
    private TypeToken<List<T>> r;

    public interface RefreshListener<T> {
        void onFailed(int i, String str);

        List<T> onSuccess(BaseResponse baseResponse);
    }

    public static class SpaceItemDecoration extends ItemDecoration {
        int a;

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            rect.left = this.a;
            rect.right = this.a;
            rect.bottom = this.a;
            rect.top = this.a;
        }

        public SpaceItemDecoration(int i) {
            this.a = i;
        }
    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.q = refreshListener;
    }

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.j = new RecyclerView(getContext());
        addView(this.j, new LayoutParams(-1, -1));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.refreshRecyclerview);
            this.f = obtainStyledAttributes.getInteger(R.styleable.refreshRecyclerview_columnNum, 1);
            this.g = obtainStyledAttributes.getInteger(R.styleable.refreshRecyclerview_itemSpace, 0);
            this.h = obtainStyledAttributes.getInteger(R.styleable.refreshRecyclerview_orient, 1);
        }
    }

    public void init(BaseRecyclerViewAdapter baseRecyclerViewAdapter, String str, Map<String, String> map, String str2, TypeToken<List<T>> typeToken) {
        this.n = str;
        this.o = map;
        this.c = baseRecyclerViewAdapter;
        this.p = str2;
        this.i = baseRecyclerViewAdapter.getItems();
        this.r = typeToken;
        setOnRefreshListener(new t(this));
        this.j.setAdapter(this.c);
        this.j.setItemAnimator(new DefaultItemAnimator());
        this.j.setHasFixedSize(true);
        this.j.addOnScrollListener(new u(this));
        this.k = new GridLayoutManager(getContext(), this.f, this.h, false);
        this.j.setLayoutManager(this.k);
        this.j.addItemDecoration(new SpaceItemDecoration(this.g));
        forceRefresh();
    }

    protected void a() {
        if (this.k.getChildCount() + this.k.findFirstVisibleItemPosition() >= this.k.getItemCount() - this.f) {
            b();
            d();
            return;
        }
        setRefreshing(false);
    }

    protected void b() {
        if (this.m == 1) {
            this.m++;
        }
    }

    protected void c() {
        this.m = 1;
    }

    public void forceRefresh() {
        setVisibility(0);
        setEnabled(true);
        if (!this.d) {
            setRefreshing(true);
            c();
            d();
        }
    }

    private void d() {
        this.d = true;
        NetRequest.getInstance().get(this.n, new v(this));
    }

    protected void a(BaseResponse baseResponse) {
        boolean z = true;
        if (this.m == 1) {
            this.i.clear();
            this.c.notifyDataSetChanged();
        }
        Collection collection = null;
        if (this.q != null) {
            collection = this.q.onSuccess(baseResponse);
        }
        if (collection == null) {
            collection = baseResponse.getListResponse(this.p, this.r);
        }
        if (collection == null || collection.size() <= 0) {
            z = false;
        }
        this.e = z;
        if (this.e) {
            this.i.addAll(collection);
            this.c.notifyDataSetChanged();
        } else if (isRefreshing() && getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(R.string.no_more_content);
        }
        this.m++;
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return this.c;
    }

    public Map<String, String> getParams() {
        return this.o;
    }

    public GridLayoutManager getmGridLayoutManager() {
        return this.k;
    }
}
