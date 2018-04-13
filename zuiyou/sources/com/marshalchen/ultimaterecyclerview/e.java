package com.marshalchen.ultimaterecyclerview;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public abstract class e<VH extends ViewHolder> extends Adapter<VH> {
    private boolean a = false;
    protected Handler b = new Handler();
    protected com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a c = null;
    protected View d = null;
    protected View e = null;
    public boolean f = false;
    protected int g;
    protected int h;
    protected final Object i = new Object();
    public b j;
    protected a k = null;
    private int l = 0;

    public interface a {
    }

    private class b implements Runnable {
        final /* synthetic */ e a;
        private boolean b;

        public b(e eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        public void run() {
            if (!(this.b || this.a.l <= 0 || this.a.d == null)) {
                int itemCount = this.a.getItemCount();
                if (this.a.a() > 0 && this.a.e != null) {
                    this.a.notifyItemRemoved(itemCount - 1);
                }
                this.a.a(this.a.a(), this.a.getItemCount());
            }
            this.a.f = this.b;
            if (this.b && this.a.d == null) {
                this.a.f = false;
            }
            if (this.b) {
                this.a.i();
            }
        }
    }

    public abstract int a();

    public abstract VH b(ViewGroup viewGroup);

    public abstract VH c(View view);

    public abstract VH d(View view);

    public void a(com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.a aVar) {
        this.c = aVar;
        this.a = true;
    }

    public boolean c() {
        return this.a;
    }

    public final void h(@Nullable View view) {
        this.d = view;
    }

    public final View d() {
        return this.d;
    }

    public final boolean e() {
        return this.f;
    }

    public final void b(boolean z) {
        this.j = new b(this, z);
    }

    public final void f() {
        if (this.j != null) {
            this.b.post(this.j);
            this.l++;
            this.j = null;
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.b.removeCallbacks(this.j);
    }

    public final void c(int i) {
        this.g = i;
    }

    public final void d(int i) {
        this.h = i;
    }

    public final int g() {
        return this.g;
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            VH d = d(this.d);
            this.e = d.itemView;
            if (a() == 0) {
                j();
            }
            if (!this.f || a() <= 0) {
                return d;
            }
            i();
            return d;
        } else if (i == 1) {
            return c(this.c);
        } else {
            if (i == 4) {
                return f(this.c);
            }
            if (i == 5) {
                return g(this.c);
            }
            if (i == 3) {
                return i(this.c);
            }
            return b(viewGroup);
        }
    }

    public VH f(View view) {
        return null;
    }

    public VH g(View view) {
        return null;
    }

    public VH i(View view) {
        return null;
    }

    public int getItemViewType(int i) {
        if (a() == 0) {
            if (i == 0) {
                if (e() && c()) {
                    return 2;
                }
                if (!e() && c()) {
                    return 1;
                }
                if (!e() || c()) {
                    return 3;
                }
                return 2;
            } else if (i != 1) {
                return 3;
            } else {
                if (e() && c()) {
                    return 2;
                }
                if (!e() && c()) {
                    return 3;
                }
                if (!e() || c()) {
                    return 3;
                }
                return 3;
            }
        } else if (a() <= 0) {
            return 0;
        } else {
            if (i == getItemCount() - 1 && e()) {
                return 2;
            }
            if (i == 0 && c()) {
                return 1;
            }
            if (a(i)) {
                return 4;
            }
            if (e(i)) {
                return 4;
            }
            return 0;
        }
    }

    protected boolean a(int i) {
        return false;
    }

    protected boolean e(int i) {
        return false;
    }

    public int getItemCount() {
        return a() + h();
    }

    protected int h() {
        int i = 0;
        if (c()) {
            i = 1;
        }
        if (e()) {
            return i + 1;
        }
        return i;
    }

    protected boolean a(int i, int i2) {
        if (i == 0) {
            if (i2 == 2) {
                if (this.g != UltimateRecyclerView.d) {
                    if (this.g == UltimateRecyclerView.c) {
                        j();
                    } else if (this.g == UltimateRecyclerView.a) {
                        j();
                    }
                }
            } else if (i2 == 1) {
                if (this.g == UltimateRecyclerView.d) {
                    return true;
                }
                if (this.g == UltimateRecyclerView.c) {
                    j();
                    return true;
                } else if (this.g != UltimateRecyclerView.a) {
                    return true;
                } else {
                    j();
                    return true;
                }
            } else if (i2 != 0) {
                return false;
            } else {
                if (this.g == UltimateRecyclerView.d) {
                    notifyDataSetChanged();
                    return true;
                } else if (this.g == UltimateRecyclerView.c) {
                    notifyDataSetChanged();
                    return true;
                } else if (this.g != UltimateRecyclerView.b) {
                    return true;
                } else {
                    notifyDataSetChanged();
                    return true;
                }
            }
        }
        return false;
    }

    protected void i() {
        if (this.e != null && this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
    }

    protected void j() {
        if (this.e != null && this.e.getVisibility() != 8) {
            this.e.setVisibility(8);
        }
    }
}
