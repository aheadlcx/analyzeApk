package com.bdj.picture.edit.sticker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.network.b;
import com.bdj.picture.edit.widget.XListView;
import com.bdj.picture.edit.widget.XListView$a;
import java.util.ArrayList;
import java.util.List;

public class a extends Fragment implements XListView$a {
    public static String a = "STICKER_CATEGORY_KEY";
    public static String b = "STICKER_LIST_KEY";
    com.bdj.picture.edit.network.a c = new a$1(this);
    private StickerCategory d;
    private List<List<StickerItem>> e = new ArrayList();
    private XListView f;
    private c g;
    private Bundle h;
    private String i = "http://api.budejie.com/api/api_open.php?c=sticker&a=list&category_id=";
    private int j = 1;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.h = getArguments();
        if (!(this.h == null || this.h.getSerializable(a) == null)) {
            this.d = (StickerCategory) getArguments().getSerializable(a);
        }
        if (this.h != null && this.h.getSerializable(b) != null) {
            this.e = (List) getArguments().getSerializable(b);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(e.fragment_sticker, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f = (XListView) view.findViewById(d.sticker_listview);
        this.g = new c(this.e, getActivity());
        this.f.setAdapter(this.g);
        this.f.setXListViewListener(this);
        if (this.d != null && "推荐".equalsIgnoreCase(this.d.name.trim())) {
            this.f.setPullLoadEnable(false);
            this.f.setPullRefreshEnable(false);
        } else if (this.d != null) {
            this.f.setPullLoadEnable(true);
            this.f.setPullRefreshEnable(true);
            this.f.b();
        } else {
            this.f.setPullLoadEnable(false);
            this.f.setPullRefreshEnable(false);
        }
    }

    public void a(List<List<StickerItem>> list) {
        if (list == null || list.size() <= 0) {
            Log.e("", "ljj-->sckLists is null: ");
            return;
        }
        this.e = list;
        if (this.g == null) {
            this.g = new c(list, getActivity());
        }
        this.g.a(list);
    }

    public void a() {
        this.i += this.d.id + "&page=" + this.j;
        new b(this.i, this.c).execute(new String[0]);
    }

    public void b() {
        this.j = 1;
        a();
    }

    public void c() {
        this.j++;
        a();
    }
}
