package com.budejie.www.adapter;

import android.view.View;

public abstract class a implements d {
    public abstract void a(b bVar);

    public abstract View b();

    public View a(View view) {
        if (view == null) {
            view = b();
        }
        try {
            a((b) view.getTag());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
