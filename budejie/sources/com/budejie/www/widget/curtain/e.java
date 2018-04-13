package com.budejie.www.widget.curtain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.FullScreenVideoActivity;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.video.n;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;

public class e {
    private Context a;
    private SparseArray<ArrayList<ListItemObject>> b;
    private ArrayList<ListItemObject> c;
    private FloatVideoLayout d;
    private final int e = 6;
    private final int f = 3;
    private RelativeLayout g;
    private ViewPager h;
    private LinearLayout i;
    private ImageView[] j;
    private net.tsz.afinal.a.a<String> k = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            new AsyncTask<String, String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    try {
                        this.a.a.c = FullScreenVideoActivity.b(com.budejie.www.j.a.a(this.a.a.a, strArr[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(new String[]{str});
        }
    };
    private OnItemClickListener l = new OnItemClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListItemObject listItemObject = (ListItemObject) adapterView.getItemAtPosition(i);
            if (listItemObject != null) {
                i.a(this.a.a.getString(R.string.track_event_video_click_recommend), j.a(listItemObject), j.b(this.a.a, listItemObject));
                Intent intent = new Intent(this.a.a, FullScreenVideoActivity.class);
                intent.putExtra(FullScreenVideoActivity.a, listItemObject);
                this.a.a.startActivity(intent);
                ((Activity) this.a.a).overridePendingTransition(R.anim.switch_style_enter, R.anim.switch_style_exit);
            }
        }
    };

    private class a extends PagerAdapter {
        final /* synthetic */ e a;
        private SparseArray<a> b;

        public class a {
            n a;
            final /* synthetic */ a b;
            private View c;

            public a(a aVar, View view, n nVar) {
                this.b = aVar;
                this.c = view;
                this.a = nVar;
            }
        }

        private a(e eVar) {
            this.a = eVar;
            this.b = new SparseArray();
        }

        public int getCount() {
            return this.a.b.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(((a) this.b.get(i)).c);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            a aVar = (a) this.b.get(i);
            if (aVar == null) {
                aVar = a((ArrayList) this.a.b.get(i));
                this.b.put(i, aVar);
            } else {
                aVar.a.a((List) this.a.b.get(i));
            }
            viewGroup.addView(aVar.c);
            return aVar.c;
        }

        public int getItemPosition(Object obj) {
            return super.getItemPosition(obj);
        }

        public a a(ArrayList<ListItemObject> arrayList) {
            View inflate = View.inflate(this.a.a, R.layout.recommend_video_fragment_layout, null);
            GridView gridView = (GridView) inflate.findViewById(R.id.gridview);
            Object nVar = new n(this.a.a, arrayList);
            gridView.setAdapter(nVar);
            gridView.setOnItemClickListener(this.a.l);
            return new a(this, inflate, nVar);
        }
    }

    public class b extends SimpleOnPageChangeListener {
        final /* synthetic */ e a;

        public b(e eVar) {
            this.a = eVar;
        }

        public void onPageSelected(int i) {
            a(i);
        }

        private void a(int i) {
            for (int i2 = 0; i2 < this.a.j.length; i2++) {
                this.a.j[i].setBackgroundResource(R.drawable.video_indictor_fucos);
                if (i != i2) {
                    this.a.j[i2].setBackgroundResource(R.drawable.video_indictor_normal);
                }
            }
        }
    }

    public e(Context context, FloatVideoLayout floatVideoLayout) {
        this.a = context;
        this.d = floatVideoLayout;
    }

    public void a(String str) {
        d();
        if (!TextUtils.isEmpty(str)) {
            BudejieApplication.a.a(RequstMethod.GET, j.b(str, "0", "36", "recommend"), new j(), this.k);
        }
    }

    private void d() {
        this.b = null;
        this.c = null;
    }

    boolean a() {
        return this.c != null && this.c.size() > 0;
    }

    public View b() {
        this.g = (RelativeLayout) View.inflate(this.a, R.layout.float_full_screen_over_suggest, null);
        this.h = (ViewPager) this.g.findViewById(R.id.video_recommend_viewpager);
        this.i = (LinearLayout) this.g.findViewById(R.id.video_recommend_viewpager_indicator);
        return this.g;
    }

    public void c() {
        this.b = new SparseArray();
        if (a()) {
            int size = this.c.size();
            int i = size > 18 ? 3 : size / 6;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = size >= ((i2 + 1) * 6) + 1 ? (i2 + 1) * 6 : size - 1;
                ArrayList arrayList = new ArrayList();
                for (int i4 = i2 * 6; i4 < i3; i4++) {
                    arrayList.add(this.c.get(i4));
                }
                this.b.append(i2, arrayList);
            }
            e();
            this.h.setAdapter(new a());
            this.h.setCurrentItem(0);
            this.h.setOnPageChangeListener(new b(this));
            this.g.setVisibility(0);
            k.a(null).b.a();
            return;
        }
        this.d.m();
    }

    private void e() {
        try {
            this.i.removeAllViews();
            this.j = new ImageView[this.b.size()];
            for (int i = 0; i < this.b.size(); i++) {
                ImageView imageView = new ImageView(this.a);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(15, 0, 15, 0);
                imageView.setLayoutParams(layoutParams);
                this.j[i] = imageView;
                if (i == 0) {
                    this.j[i].setBackgroundResource(R.drawable.video_indictor_fucos);
                } else {
                    this.j[i].setBackgroundResource(R.drawable.video_indictor_normal);
                }
                this.i.addView(this.j[i]);
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.a, "cacheException", "RecommendLayoutHandler initDots:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
