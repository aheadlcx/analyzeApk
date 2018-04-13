package com.bumptech.glide.d;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.Fragment;
import com.bumptech.glide.k;
import java.util.HashSet;

public class n extends Fragment {
    private k a;
    private final a b;
    private final l c;
    private final HashSet<n> d;
    private n e;

    private class a implements l {
        final /* synthetic */ n a;

        private a(n nVar) {
            this.a = nVar;
        }
    }

    public n() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    public n(a aVar) {
        this.c = new a();
        this.d = new HashSet();
        this.b = aVar;
    }

    public void a(k kVar) {
        this.a = kVar;
    }

    a a() {
        return this.b;
    }

    public k b() {
        return this.a;
    }

    public l c() {
        return this.c;
    }

    private void a(n nVar) {
        this.d.add(nVar);
    }

    private void b(n nVar) {
        this.d.remove(nVar);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.e = k.a().a(getActivity().getSupportFragmentManager());
        if (this.e != this) {
            this.e.a(this);
        }
    }

    public void onDetach() {
        super.onDetach();
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.b.a();
    }

    public void onStop() {
        super.onStop();
        this.b.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.c();
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.a != null) {
            this.a.a();
        }
    }
}
