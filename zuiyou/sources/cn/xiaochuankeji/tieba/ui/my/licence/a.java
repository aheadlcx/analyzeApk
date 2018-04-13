package cn.xiaochuankeji.tieba.ui.my.licence;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.f;
import com.artitk.licensefragment.model.LicenseType;
import java.util.ArrayList;

public class a extends f {

    private static class a extends Adapter<a> {
        private ArrayList<com.artitk.licensefragment.model.a> a;

        public static class a extends ViewHolder {
            public TextView a;
            public TextView b;

            public a(View view) {
                super(view);
                this.a = (TextView) view.findViewById(R.id.title);
                this.b = (TextView) view.findViewById(R.id.license);
            }
        }

        public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
            a((a) viewHolder, i);
        }

        public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return a(viewGroup, i);
        }

        a(ArrayList<com.artitk.licensefragment.model.a> arrayList) {
            this.a = arrayList;
        }

        public a a(ViewGroup viewGroup, int i) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_license, viewGroup, false));
        }

        public void a(a aVar, int i) {
            com.artitk.licensefragment.model.a aVar2 = (com.artitk.licensefragment.model.a) this.a.get(i);
            aVar.a.setText(aVar2.a());
            aVar.b.setText(aVar2.b());
        }

        public int getItemCount() {
            return this.a.size();
        }
    }

    public static a b() {
        return new a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_recycler_view_license, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "License Fragment", LicenseType.APACHE_LICENSE_20, "2015", "Artit Kiuwilai"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "OkHttp", LicenseType.APACHE_LICENSE_20, "2016", "Square, Inc."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "Retrofit", LicenseType.APACHE_LICENSE_20, "2013", "Square, Inc."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "Fresco", LicenseType.BSD_3_CLAUSE, "2015", "Facebook, Inc."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "ShortcutBadger", LicenseType.APACHE_LICENSE_20, "2014", "Leo Lin"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "Android-Job", LicenseType.APACHE_LICENSE_20, "2007", "Evernote Corporation"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "ReLinker", LicenseType.APACHE_LICENSE_20, "2015", "Keepsafe Software Inc."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "EventBus", LicenseType.APACHE_LICENSE_20, "2012-2017", "Markus Junginger, greenrobot (http://greenrobot.org)"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "RxJava", LicenseType.APACHE_LICENSE_20, "2016-present", "RxJava Contributors."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "Android-skin-support", LicenseType.MIT_LICENSE, "2017", " pengfeng wang"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "TinyPinyin", LicenseType.APACHE_LICENSE_20, "2015", "promeG"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "Matisse", LicenseType.APACHE_LICENSE_20, "2015", "Zhihu Inc."));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "SmartRefreshLayout", LicenseType.APACHE_LICENSE_20, "2017", "scwang90"));
        arrayList.add(new com.artitk.licensefragment.model.a(getContext(), "URL-Detector", LicenseType.APACHE_LICENSE_20, "2015", "LinkedIn Corp."));
        recyclerView.setAdapter(new a(arrayList));
    }
}
