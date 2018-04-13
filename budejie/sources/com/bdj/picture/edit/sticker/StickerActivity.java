package com.bdj.picture.edit.sticker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.androidexlib.widget.asyncimage.AsyncImageView;
import com.bdj.picture.edit.TitleBarFragmentActivity;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;
import com.bdj.picture.edit.widget.TabHorizontalScrollView;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.pro.x;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StickerActivity extends TitleBarFragmentActivity implements com.bdj.picture.edit.sticker.StickerTabs.a {
    com.bdj.picture.edit.network.a a = new com.bdj.picture.edit.network.a(this) {
        final /* synthetic */ StickerActivity a;

        {
            this.a = r1;
        }

        public void a(JSONObject jSONObject, com.bdj.picture.edit.network.b bVar) {
            try {
                this.a.i.clear();
                this.a.i.add(StickerActivity.a(jSONObject.getJSONArray("new"), "最新上架"));
                this.a.i.add(StickerActivity.a(jSONObject.getJSONArray("newtop"), "最新热门"));
                this.a.i.add(StickerActivity.a(jSONObject.getJSONArray("top"), "人气排行"));
                List a = StickerActivity.a(jSONObject.getJSONArray("categories"));
                if (this.a.a(a)) {
                    this.a.d.a(null, null);
                    this.a.d.a(a);
                    this.a.e();
                    this.a.f();
                    return;
                }
                a = this.a.c.a();
                if (a != null && a.size() > 0) {
                    this.a.i.add(0, a);
                }
                this.a.a().a(this.a.i);
            } catch (JSONException e) {
                Log.e("", "ljj-->onsuccess: " + e.toString());
            }
        }

        public void a(int i, String str, JSONObject jSONObject, com.bdj.picture.edit.network.b bVar) {
            Toast.makeText(this.a.b, "获取数据失败，请稍后再试...", 0).show();
        }

        public void a(String str, com.bdj.picture.edit.network.b bVar) {
            Toast.makeText(this.a.b, "获取数据失败，请稍后再试...", 0).show();
        }

        public void a(com.bdj.picture.edit.network.b bVar) {
        }
    };
    private Context b;
    private com.bdj.picture.edit.b.c c;
    private com.bdj.picture.edit.b.b d;
    private ViewPager e;
    private List<a> f;
    private a g;
    private List<StickerCategory> h = new ArrayList();
    private List<List<StickerItem>> i = new ArrayList();
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private RadioGroup m;
    private List<String> n = new ArrayList();
    private int o;
    private int p = 0;
    private TabHorizontalScrollView q;
    private int r = 5;
    private RelativeLayout s;
    private DisplayMetrics t;
    private int u = 0;
    private int v = 0;

    private class a extends FragmentPagerAdapter {
        final /* synthetic */ StickerActivity a;

        public a(StickerActivity stickerActivity, FragmentManager fragmentManager) {
            this.a = stickerActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.f.get(i);
        }

        public int getCount() {
            return this.a.f.size();
        }

        public Parcelable saveState() {
            return null;
        }
    }

    private class b implements OnPageChangeListener {
        final /* synthetic */ StickerActivity a;

        private b(StickerActivity stickerActivity) {
            this.a = stickerActivity;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.m != null) {
                this.a.m.getChildAt(i).performClick();
            }
        }
    }

    class c implements Comparator {
        final /* synthetic */ StickerActivity a;

        c(StickerActivity stickerActivity) {
            this.a = stickerActivity;
        }

        public int compare(Object obj, Object obj2) {
            return ((StickerCategory) obj).sort_id.compareTo(((StickerCategory) obj2).sort_id);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(e.activity_sticker);
        this.b = this;
        this.c = new com.bdj.picture.edit.b.c(this.b);
        this.d = new com.bdj.picture.edit.b.b(this.b);
        this.f = new ArrayList();
        this.t = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.t);
        a((Context) this);
        g();
        e();
        f();
        b();
    }

    private void b() {
        com.bdj.picture.edit.network.b bVar = new com.bdj.picture.edit.network.b("http://api.budejie.com/api/api_open.php?c=sticker&a=recommend", this.a);
        if (this.n.size() > 0) {
            bVar.execute(new String[0]);
        } else {
            bVar.a(true, this, "正在拼命加载数据...", true).execute(new String[0]);
        }
    }

    private boolean a(List<StickerCategory> list) {
        Collections.sort(list, new c(this));
        if (list != null) {
            try {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (!((StickerCategory) list.get(i)).id.equals(((StickerCategory) this.h.get(i)).id)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    private void e() {
        this.n.clear();
        this.h = this.d.a();
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            this.n.add(((StickerCategory) this.h.get(i)).name);
        }
        if (this.n.size() > 0) {
            if (this.n.size() < this.r) {
                this.r = this.n.size();
            }
            this.o = ((this.t.widthPixels / this.r) * 3) / 2;
            LayoutParams layoutParams = this.j.getLayoutParams();
            layoutParams.width = this.o;
            this.j.setLayoutParams(layoutParams);
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
            this.m.setOnCheckedChangeListener(null);
            this.v = this.m.getChildCount();
            this.m.removeAllViews();
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                RadioButton radioButton = (RadioButton) layoutInflater.inflate(e.nav_rg_item, null);
                radioButton.setLayoutParams(new LinearLayout.LayoutParams(this.o, -1));
                size = this.u;
                this.u = size + 1;
                radioButton.setId(size);
                radioButton.setText((CharSequence) this.n.get(i2));
                this.m.addView(radioButton);
            }
            RadioButton radioButton2 = (RadioButton) this.m.getChildAt(0);
            if (radioButton2 != null) {
                radioButton2.setChecked(true);
            }
            this.q.a(this.s, this.l, this.k, this);
            this.l.setVisibility(4);
            if (this.n.size() <= this.r) {
                this.k.setVisibility(4);
            } else {
                this.k.setVisibility(0);
            }
        }
    }

    private void f() {
        List a = this.c.a();
        if (a != null && a.size() > 0) {
            this.i.add(0, a);
        }
        if (this.h != null) {
            this.h.clear();
        }
        if (this.h == null || this.h.size() <= 0) {
            this.h = this.d.a();
        }
        if (this.h != null) {
            this.f.clear();
            int size = this.h.size();
            for (int i = 0; i < size; i++) {
                a aVar = new a();
                Bundle bundle = new Bundle();
                bundle.putSerializable(a.a, (Serializable) this.h.get(i));
                if (i == 0) {
                    bundle.putSerializable(a.b, (Serializable) this.i);
                }
                aVar.setArguments(bundle);
                aVar.setRetainInstance(false);
                this.f.add(i, aVar);
            }
        }
        this.e.removeAllViews();
        this.g = new a(this, getSupportFragmentManager());
        this.e.setAdapter(this.g);
        this.e.setOffscreenPageLimit(this.f.size());
        this.e.setOnPageChangeListener(new b());
        this.e.setCurrentItem(0);
        this.m.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ StickerActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = 0;
                try {
                    this.a.m.check(i);
                    int b = i - this.a.v;
                    Animation translateAnimation = new TranslateAnimation((float) this.a.p, (float) ((RadioButton) this.a.m.getChildAt(b)).getLeft(), 0.0f, 0.0f);
                    translateAnimation.setInterpolator(new LinearInterpolator());
                    translateAnimation.setDuration(300);
                    translateAnimation.setFillAfter(true);
                    this.a.j.startAnimation(translateAnimation);
                    this.a.p = this.a.m.getChildAt(b).getLeft();
                    this.a.e.setCurrentItem(b);
                    if (b > 0) {
                        i2 = ((RadioButton) this.a.m.getChildAt(b)).getLeft();
                    }
                    this.a.q.smoothScrollTo(i2 - ((RadioButton) this.a.m.getChildAt(2)).getLeft(), 0);
                } catch (Exception e) {
                    Log.e("", "mj-->" + e.toString() + "--checkedId:" + i);
                }
            }
        });
    }

    private void g() {
        a(h.title_cancle, h.edit_picture_function_stickers, 0, 0, false);
        this.e = (ViewPager) findViewById(d.container_viewpager);
        this.s = (RelativeLayout) findViewById(d.rl_nav);
        this.q = (TabHorizontalScrollView) findViewById(d.sv_nav);
        this.m = (RadioGroup) findViewById(d.rg_nav);
        this.j = (ImageView) findViewById(d.iv_nav_indicator);
        this.l = (ImageView) findViewById(d.iv_nav_left);
        this.k = (ImageView) findViewById(d.iv_nav_right);
    }

    public a a() {
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        return (a) this.f.get(this.e.getCurrentItem());
    }

    public void a(Context context) {
        com.nostra13.universalimageloader.core.d.a().a(new com.nostra13.universalimageloader.core.e.a(context).b(5).a().a(new com.nostra13.universalimageloader.a.a.b.c()).a(4).b().d(AsyncImageView.b).a(new com.nostra13.universalimageloader.a.a.a.b(AsyncImageView.c)).c());
        com.nostra13.universalimageloader.b.d.a(new com.nostra13.universalimageloader.b.a(this) {
            final /* synthetic */ StickerActivity a;

            {
                this.a = r1;
            }

            public void a(String str, Throwable th) {
                if (!(th instanceof SocketTimeoutException)) {
                }
            }
        });
    }

    public static List<StickerItem> a(JSONArray jSONArray, String str) {
        List<StickerItem> arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                StickerItem stickerItem = new StickerItem();
                stickerItem.id = com.bdj.picture.edit.util.b.a(jSONObject, "id");
                stickerItem.category_id = com.bdj.picture.edit.util.b.a(jSONObject, "category_id");
                stickerItem.category_parent_id = com.bdj.picture.edit.util.b.a(jSONObject, "category_parent_id");
                stickerItem.name = com.bdj.picture.edit.util.b.a(jSONObject, "name");
                stickerItem.image_url = com.bdj.picture.edit.util.b.a(jSONObject, GameAppOperation.QQFAV_DATALINE_IMAGEURL);
                stickerItem.thumb_url = com.bdj.picture.edit.util.b.a(jSONObject, "thumb_url");
                stickerItem.introduce = com.bdj.picture.edit.util.b.a(jSONObject, "introduce");
                stickerItem.status = com.bdj.picture.edit.util.b.a(jSONObject, "status");
                stickerItem.is_new = com.bdj.picture.edit.util.b.a(jSONObject, "is_new");
                stickerItem.total_times = com.bdj.picture.edit.util.b.a(jSONObject, "total_times");
                stickerItem.used_times = com.bdj.picture.edit.util.b.a(jSONObject, "used_times");
                stickerItem.available_times = com.bdj.picture.edit.util.b.a(jSONObject, "available_times");
                stickerItem.start_time = com.bdj.picture.edit.util.b.a(jSONObject, x.W);
                stickerItem.end_time = com.bdj.picture.edit.util.b.a(jSONObject, x.X);
                stickerItem.create_time = com.bdj.picture.edit.util.b.a(jSONObject, "create_time");
                stickerItem.modify_time = com.bdj.picture.edit.util.b.a(jSONObject, "modify_time");
                stickerItem.admin = com.bdj.picture.edit.util.b.a(jSONObject, "admin");
                stickerItem.sub_category_name = com.bdj.picture.edit.util.b.a(jSONObject, "sub_category_name");
                if (TextUtils.isEmpty(stickerItem.sub_category_name) && !TextUtils.isEmpty(str)) {
                    stickerItem.sub_category_name = str;
                }
                stickerItem.local_path = com.bdj.picture.edit.util.e.a + com.bdj.picture.edit.util.e.a(stickerItem.image_url);
                stickerItem.down_status = "0";
                arrayList.add(stickerItem);
            } catch (JSONException e) {
                Log.e("", "ljj-->parseStickerList: " + e.toString());
            }
        }
        return arrayList;
    }

    public static List<StickerCategory> a(JSONArray jSONArray) {
        List<StickerCategory> arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                StickerCategory stickerCategory = new StickerCategory();
                stickerCategory.id = com.bdj.picture.edit.util.b.a(jSONObject, "id");
                stickerCategory.parent_id = com.bdj.picture.edit.util.b.a(jSONObject, "parent_id");
                stickerCategory.name = com.bdj.picture.edit.util.b.a(jSONObject, "name");
                stickerCategory.sort_id = com.bdj.picture.edit.util.b.a(jSONObject, "sort_id");
                stickerCategory.status = com.bdj.picture.edit.util.b.a(jSONObject, "status");
                stickerCategory.create_time = com.bdj.picture.edit.util.b.a(jSONObject, "create_time");
                stickerCategory.modify_time = com.bdj.picture.edit.util.b.a(jSONObject, "modify_time");
                arrayList.add(stickerCategory);
            } catch (JSONException e) {
                Log.e("", "ljj-->parseStickerCategoryList: " + e.toString());
            }
        }
        return arrayList;
    }

    public void a(int i) {
        this.e.setCurrentItem(i);
    }
}
