package qsbk.app.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.AppUtils;

public abstract class BaseRecyclerViewAdapter<T> extends Adapter<ViewHolder> {
    protected Context a;
    protected List<T> b = new ArrayList();

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        final /* synthetic */ BaseRecyclerViewAdapter m;
        private SparseArray<View> n = new SparseArray();
        private View o;

        public ViewHolder(BaseRecyclerViewAdapter baseRecyclerViewAdapter, Context context, View view, ViewGroup viewGroup) {
            this.m = baseRecyclerViewAdapter;
            super(view);
            this.o = view;
        }

        public <X extends View> X getView(int i) {
            View view = (View) this.n.get(i);
            if (view != null) {
                return view;
            }
            X findViewById = this.o.findViewById(i);
            this.n.put(i, findViewById);
            return findViewById;
        }

        public void setText(int i, String str) {
            ((TextView) getView(i)).setText(str);
        }

        public void setText(int i, int i2) {
            ((TextView) getView(i)).setText(AppUtils.getInstance().getAppContext().getResources().getText(i2));
        }
    }

    protected abstract void a(int i, ViewHolder viewHolder, int i2, T t);

    protected abstract int b(int i);

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        this.a = context;
        this.b = list;
    }

    protected int a(int i) {
        return 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, this.a, LayoutInflater.from(this.a).inflate(b(i), viewGroup, false), viewGroup);
    }

    public int getItemCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i < this.b.size()) {
            a(a(i), viewHolder, i, this.b != null ? this.b.get(i) : null);
        }
    }

    public int getItemViewType(int i) {
        return a(i);
    }

    public List getItems() {
        return this.b;
    }
}
