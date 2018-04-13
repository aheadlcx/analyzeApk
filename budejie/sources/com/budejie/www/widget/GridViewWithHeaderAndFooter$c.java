package com.budejie.www.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

class GridViewWithHeaderAndFooter$c implements Filterable, WrapperListAdapter {
    static final ArrayList<GridViewWithHeaderAndFooter$a> a = new ArrayList();
    ArrayList<GridViewWithHeaderAndFooter$a> b;
    ArrayList<GridViewWithHeaderAndFooter$a> c;
    boolean d;
    private final DataSetObservable e = new DataSetObservable();
    private final ListAdapter f;
    private int g = 1;
    private int h = -1;
    private final boolean i;
    private boolean j = true;
    private boolean k = false;

    public GridViewWithHeaderAndFooter$c(ArrayList<GridViewWithHeaderAndFooter$a> arrayList, ArrayList<GridViewWithHeaderAndFooter$a> arrayList2, ListAdapter listAdapter) {
        boolean z = true;
        this.f = listAdapter;
        this.i = listAdapter instanceof Filterable;
        if (arrayList == null) {
            this.b = a;
        } else {
            this.b = arrayList;
        }
        if (arrayList2 == null) {
            this.c = a;
        } else {
            this.c = arrayList2;
        }
        if (!(a(this.b) && a(this.c))) {
            z = false;
        }
        this.d = z;
    }

    public void a(int i) {
        if (i >= 1 && this.g != i) {
            this.g = i;
            c();
        }
    }

    public void b(int i) {
        this.h = i;
    }

    public int a() {
        return this.b.size();
    }

    public int b() {
        return this.c.size();
    }

    public boolean isEmpty() {
        return this.f == null || this.f.isEmpty();
    }

    private boolean a(ArrayList<GridViewWithHeaderAndFooter$a> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!((GridViewWithHeaderAndFooter$a) it.next()).d) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCount() {
        if (this.f != null) {
            return ((b() + a()) * this.g) + d();
        }
        return (b() + a()) * this.g;
    }

    public boolean areAllItemsEnabled() {
        return this.f == null || (this.d && this.f.areAllItemsEnabled());
    }

    private int d() {
        return (int) (Math.ceil((double) ((1.0f * ((float) this.f.getCount())) / ((float) this.g))) * ((double) this.g));
    }

    public boolean isEnabled(int i) {
        int a = a() * this.g;
        if (i < a) {
            boolean z;
            if (i % this.g == 0 && ((GridViewWithHeaderAndFooter$a) this.b.get(i / this.g)).d) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
        int i2 = i - a;
        if (this.f != null) {
            a = d();
            if (i2 < a) {
                if (i2 >= this.f.getCount() || !this.f.isEnabled(i2)) {
                    return false;
                }
                return true;
            }
        }
        a = 0;
        a = i2 - a;
        if (a % this.g == 0 && ((GridViewWithHeaderAndFooter$a) this.c.get(a / this.g)).d) {
            return true;
        }
        return false;
    }

    public Object getItem(int i) {
        int a = a() * this.g;
        if (i >= a) {
            int i2 = i - a;
            a = 0;
            if (this.f != null) {
                a = d();
                if (i2 < a) {
                    return i2 < this.f.getCount() ? this.f.getItem(i2) : null;
                }
            }
            a = i2 - a;
            return a % this.g == 0 ? ((GridViewWithHeaderAndFooter$a) this.c.get(a)).c : null;
        } else if (i % this.g == 0) {
            return ((GridViewWithHeaderAndFooter$a) this.b.get(i / this.g)).c;
        } else {
            return null;
        }
    }

    public long getItemId(int i) {
        int a = a() * this.g;
        if (this.f != null && i >= a) {
            a = i - a;
            if (a < this.f.getCount()) {
                return this.f.getItemId(a);
            }
        }
        return -1;
    }

    public boolean hasStableIds() {
        return this.f != null && this.f.hasStableIds();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        if (GridViewWithHeaderAndFooter.a) {
            String str = "GridViewHeaderAndFooter";
            String str2 = "getView: %s, reused: %s";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            objArr[1] = Boolean.valueOf(view == null);
            Log.d(str, String.format(str2, objArr));
        }
        int a = a() * this.g;
        View view2;
        if (i < a) {
            view2 = ((GridViewWithHeaderAndFooter$a) this.b.get(i / this.g)).b;
            if (i % this.g == 0) {
                return view2;
            }
            if (view == null) {
                view = new View(viewGroup.getContext());
            }
            view.setVisibility(4);
            view.setMinimumHeight(view2.getHeight());
            return view;
        }
        a = i - a;
        if (this.f != null) {
            i2 = d();
            if (a < i2) {
                if (a < this.f.getCount()) {
                    return this.f.getView(a, view, viewGroup);
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(this.h);
                return view;
            }
        }
        a -= i2;
        if (a < getCount()) {
            view2 = ((GridViewWithHeaderAndFooter$a) this.c.get(a / this.g)).b;
            if (i % this.g == 0) {
                return view2;
            }
            if (view == null) {
                view = new View(viewGroup.getContext());
            }
            view.setVisibility(4);
            view.setMinimumHeight(view2.getHeight());
            return view;
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public int getItemViewType(int i) {
        int i2;
        int a = a() * this.g;
        if (this.f == null) {
            i2 = 0;
        } else {
            i2 = this.f.getViewTypeCount() - 1;
        }
        int i3 = -2;
        if (this.j && i < a) {
            if (i == 0 && this.k) {
                i3 = (((this.b.size() + i2) + this.c.size()) + 1) + 1;
            }
            if (i % this.g != 0) {
                i3 = ((i / this.g) + 1) + i2;
            }
        }
        int i4 = i - a;
        if (this.f != null) {
            int i5;
            a = d();
            if (i4 >= 0 && i4 < a) {
                if (i4 < this.f.getCount()) {
                    i5 = a;
                    a = this.f.getItemViewType(i4);
                    i3 = i5;
                } else if (this.j) {
                    i5 = a;
                    a = (this.b.size() + i2) + 1;
                    i3 = i5;
                }
            }
            i5 = a;
            a = i3;
            i3 = i5;
        } else {
            a = i3;
            i3 = 0;
        }
        if (this.j) {
            i3 = i4 - i3;
            if (i3 >= 0 && i3 < getCount() && i3 % this.g != 0) {
                a = ((i2 + this.b.size()) + 1) + ((i3 / this.g) + 1);
            }
        }
        if (GridViewWithHeaderAndFooter.a) {
            Log.d("GridViewHeaderAndFooter", String.format("getItemViewType: pos: %s, result: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(a), Boolean.valueOf(this.j), Boolean.valueOf(this.k)}));
        }
        return a;
    }

    public int getViewTypeCount() {
        int viewTypeCount = this.f == null ? 1 : this.f.getViewTypeCount();
        if (this.j) {
            int size = (this.b.size() + 1) + this.c.size();
            if (this.k) {
                size++;
            }
            viewTypeCount += size;
        }
        if (GridViewWithHeaderAndFooter.a) {
            Log.d("GridViewHeaderAndFooter", String.format("getViewTypeCount: %s", new Object[]{Integer.valueOf(viewTypeCount)}));
        }
        return viewTypeCount;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.e.registerObserver(dataSetObserver);
        if (this.f != null) {
            this.f.registerDataSetObserver(dataSetObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.e.unregisterObserver(dataSetObserver);
        if (this.f != null) {
            this.f.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public Filter getFilter() {
        if (this.i) {
            return ((Filterable) this.f).getFilter();
        }
        return null;
    }

    public ListAdapter getWrappedAdapter() {
        return this.f;
    }

    public void c() {
        this.e.notifyChanged();
    }
}
