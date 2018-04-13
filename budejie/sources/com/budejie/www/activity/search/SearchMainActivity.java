package com.budejie.www.activity.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.video.VideoView;
import com.budejie.www.activity.video.f;
import com.budejie.www.adapter.a.m;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.recommendvideo.AutoPlayVideoListActivity;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.widget.GridViewWithHeaderAndFooter;
import com.budejie.www.widget.PullToRefreshView;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchMainActivity extends BaseActvityWithLoadDailog implements OnClickListener, com.budejie.www.g.a {
    public static int a = 0;
    public static boolean b;
    public static boolean c;
    private static int y;
    private PullToRefreshView A;
    private GridViewWithHeaderAndFooter B;
    private m C;
    private View D;
    private VideoView E;
    private f F;
    private boolean G;
    private j H;
    private boolean I;
    private int J;
    private int K;
    private int L;
    private int M;
    private long N;
    private com.budejie.www.widget.PullToRefreshView.b O = new com.budejie.www.widget.PullToRefreshView.b(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public void a(PullToRefreshView pullToRefreshView) {
            this.a.k();
            SearchMainActivity.a++;
            int size = AutoPlayVideoListActivity.g.size();
            if (SearchMainActivity.c || SearchMainActivity.a >= size) {
                SearchMainActivity.c = false;
                BudejieApplication.a.a(RequstMethod.GET, j.b(), new j(this.a.e), this.a.T);
                return;
            }
            this.a.b();
        }
    };
    private com.budejie.www.widget.PullToRefreshView.a P = new com.budejie.www.widget.PullToRefreshView.a(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public void a(PullToRefreshView pullToRefreshView) {
            NetWorkUtil netWorkUtil = BudejieApplication.a;
            RequstMethod requstMethod = RequstMethod.GET;
            String h = j.h("" + this.a.N);
            this.a.H;
            netWorkUtil.a(requstMethod, h, j.e(this.a.e), this.a.R);
        }
    };
    private net.tsz.afinal.a.a<String> Q = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            List a;
            this.a.e();
            this.a.A.b();
            ListInfo a2 = com.budejie.www.j.a.a(str);
            this.a.M = a2.count;
            this.a.N = a2.np;
            try {
                a = com.budejie.www.j.a.a(this.a.e, str);
            } catch (Exception e) {
                e.printStackTrace();
                a = null;
            }
            if (a != null) {
                this.a.C.getCount();
                if (this.a.a(a)) {
                    an.a(this.a.e, this.a.getString(R.string.load_failed), -1).show();
                } else {
                    this.a.C.a(a);
                }
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
            this.a.A.b();
            an.a(this.a.e, this.a.getString(R.string.load_failed), -1).show();
        }
    };
    private net.tsz.afinal.a.a<String> R = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            List a;
            this.a.e();
            this.a.A.c();
            ListInfo a2 = com.budejie.www.j.a.a(str);
            this.a.M = a2.count;
            this.a.N = a2.np;
            try {
                a = com.budejie.www.j.a.a(this.a.e, str);
            } catch (Exception e) {
                e.printStackTrace();
                a = null;
            }
            if (a == null) {
                an.a(this.a.e, this.a.getString(R.string.load_failed), -1).show();
            } else if (!this.a.a(a)) {
                this.a.C.b(a);
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.e();
            this.a.A.c();
            an.a(this.a.e, this.a.getString(R.string.load_failed), -1).show();
        }
    };
    private TextWatcher S = new TextWatcher(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(this.a.j.getText().toString())) {
                this.a.k.setVisibility(8);
                this.a.u.a();
                this.a.v.a();
                this.a.w.a();
                return;
            }
            this.a.k.setVisibility(0);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    private net.tsz.afinal.a.a<String> T = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ SearchMainActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass2 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((ArrayList) obj);
                }

                protected void a(ArrayList<ListItemObject> arrayList) {
                    try {
                        this.a.a.a((ListItemObject) AutoPlayVideoListActivity.g.get(0));
                    } catch (Exception e) {
                    }
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    String str;
                    ArrayList<ListItemObject> a;
                    String str2 = strArr[0];
                    com.budejie.www.j.a.a(str2);
                    String str3 = "";
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.has("opends")) {
                            str3 = jSONObject.getString("opends");
                        }
                        str = str3;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        str = str3;
                    }
                    try {
                        a = com.budejie.www.j.a.a(this.a.a.e, str2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        a = null;
                    }
                    if (a != null) {
                        System.out.println("--recommend--  onRecommendCallBack 网络请求得到推荐数据，将推荐数据放入缓存");
                        AutoPlayVideoListActivity.g.clear();
                        SearchMainActivity.a = 0;
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            ListItemObject listItemObject = (ListItemObject) it.next();
                            if (listItemObject.getWid() != null) {
                                listItemObject.isreport = false;
                                listItemObject.setAddtime("推荐内容 " + listItemObject.getAddtime());
                                listItemObject.opends = str;
                                if (listItemObject.getHeight() <= listItemObject.getWidth() && !TextUtils.isEmpty(listItemObject.getVideouri())) {
                                    AutoPlayVideoListActivity.g.add(listItemObject);
                                }
                            }
                        }
                    }
                    return a;
                }
            }.execute(new String[]{str});
        }
    };
    List<String> d = new ArrayList();
    private Activity e;
    private InputMethodManager f;
    private ImageView h;
    private TextView i;
    private EditText j;
    private ImageView k;
    private LinearLayout l;
    private LinearLayout m;
    private LinearLayout n;
    private TextView o;
    private TextView p;
    private TextView q;
    private ImageView r;
    private ViewPager s;
    private a t;
    private c u;
    private b v;
    private d w;
    private DisplayMetrics x = new DisplayMetrics();
    private RelativeLayout z;

    public class a extends FragmentPagerAdapter {
        final /* synthetic */ SearchMainActivity a;
        private ArrayList<Fragment> b;

        public a(SearchMainActivity searchMainActivity, FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            this.a = searchMainActivity;
            super(fragmentManager);
            this.b = arrayList;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.b.size();
        }
    }

    public class b implements OnPageChangeListener {
        final /* synthetic */ SearchMainActivity a;

        public b(SearchMainActivity searchMainActivity) {
            this.a = searchMainActivity;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            Matrix matrix = new Matrix();
            switch (i) {
                case 0:
                    Log.d("SearchMainActivity", "onPageScrolled: did--->0");
                    matrix.setTranslate((float) this.a.K, 0.0f);
                    break;
                case 1:
                    matrix.setTranslate((float) (SearchMainActivity.y + this.a.K), 0.0f);
                    break;
                case 2:
                    matrix.setTranslate((float) ((SearchMainActivity.y * 2) + this.a.K), 0.0f);
                    break;
            }
            matrix.postTranslate(((float) SearchMainActivity.y) * f, 0.0f);
            this.a.r.setImageMatrix(matrix);
        }

        public void onPageSelected(int i) {
            if (!TextUtils.isEmpty(this.a.j.getText().toString())) {
                this.a.a(this.a.j.getText().toString());
            }
            this.a.o.setTextColor(this.a.getResources().getColor(R.color.myinfo_label_text_color));
            this.a.p.setTextColor(this.a.getResources().getColor(R.color.myinfo_label_text_color));
            this.a.q.setTextColor(this.a.getResources().getColor(R.color.myinfo_label_text_color));
            switch (i) {
                case 0:
                    this.a.a(this.a.o);
                    return;
                case 1:
                    this.a.a(this.a.p);
                    return;
                case 2:
                    this.a.a(this.a.q);
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.serch_main_layout);
        this.e = this;
        this.L = ai.a(this);
        g();
        h();
        l();
    }

    protected void onResume() {
        super.onResume();
        this.I = false;
    }

    protected void onPause() {
        super.onPause();
        this.I = true;
    }

    private void b() {
        if (this.F != null && AutoPlayVideoListActivity.g != null) {
            ListItemObject listItemObject = (ListItemObject) AutoPlayVideoListActivity.g.get(a);
            this.F.setVideoItem(listItemObject);
            Object videouri = listItemObject.getVideouri();
            if (!TextUtils.isEmpty(videouri)) {
                this.E.setVideoPath(videouri);
                this.E.a();
            }
        }
    }

    private void a(final ListItemObject listItemObject) {
        a = 0;
        b = false;
        c = false;
        this.H = new j();
        ListItemObject listItemObject2 = new ListItemObject();
        listItemObject2.setImgUrl(listItemObject.getImgUrl());
        listItemObject2.setVideouri(listItemObject.getVideouri());
        this.F = new f(this, listItemObject2, 3);
        this.F.a(true);
        this.F.i();
        this.E.setMircroMediaController(this.F);
        this.E.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ SearchMainActivity b;

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                try {
                    if (TextUtils.isEmpty(listItemObject.getVideouriBackup()) || listItemObject.getVideouri().equals(listItemObject.getVideouriBackup())) {
                        if (!this.b.I) {
                            Toast.makeText(this.b.e, this.b.e.getString(R.string.loading_video_error_tip) + "(" + i + ",," + i2 + ")", 0).show();
                        }
                        return true;
                    }
                    this.b.E.setVideoPath(listItemObject.getVideouriBackup());
                    listItemObject.setVideouri(listItemObject.getVideouriBackup());
                    this.b.E.a();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!this.b.I) {
                        Toast.makeText(this.b.e, this.b.e.getString(R.string.loading_video_error_tip) + "  (" + i + ",,," + i2 + ")", 0).show();
                    }
                }
            }
        });
        this.E.setVideoPath(listItemObject.getVideouri());
        this.E.a();
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.B = (GridViewWithHeaderAndFooter) findViewById(R.id.activitiesTopicGridView);
        this.A = (PullToRefreshView) findViewById(R.id.activitiesTopic_refresh_view);
        this.D = this.e.getLayoutInflater().inflate(R.layout.search_main_gridview_header_video, null);
        this.E = (VideoView) this.D.findViewById(R.id.video_view);
        this.i = (TextView) findViewById(R.id.cancel_btn);
        this.h = (ImageView) findViewById(R.id.BackImageView);
        this.j = (EditText) findViewById(R.id.search_keywords_text);
        this.k = (ImageView) findViewById(R.id.del_keywords_btn);
        this.l = (LinearLayout) findViewById(R.id.label_search);
        this.m = (LinearLayout) findViewById(R.id.SerchEditLayout);
        this.z = (RelativeLayout) findViewById(R.id.DataLayout);
        this.n = (LinearLayout) findViewById(R.id.SearchAllLayout);
        this.o = (TextView) findViewById(R.id.SearchPostTextView);
        this.p = (TextView) findViewById(R.id.SearchLabelTextView);
        this.q = (TextView) findViewById(R.id.SearchUserTextView);
        this.r = (ImageView) findViewById(R.id.SearchTabLine);
        this.s = (ViewPager) findViewById(R.id.SearchPager);
        this.J = this.o.getWidth();
        if (this.J == 0) {
            this.o.measure(0, 0);
            this.J = this.o.getMeasuredWidth();
        }
        a(this.o);
    }

    private void h() {
        this.G = false;
        this.C = new m(this);
        this.B.a(this.D, an.a(this.e, 187));
        this.E.setContainerH(an.a(this.e, 187));
        this.B.setAdapter(this.C);
        this.A.setOnHeaderRefreshListener(this.O);
        this.A.setOnFooterRefreshListener(this.P);
        this.i.setOnClickListener(this);
        this.h.setOnClickListener(this);
        j();
        i();
    }

    private void i() {
        this.f = (InputMethodManager) this.j.getContext().getSystemService("input_method");
        this.k.setOnClickListener(this);
        this.j.addTextChangedListener(this.S);
        this.j.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ SearchMainActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.e.getCurrentFocus().getWindowToken(), 2);
                    if (TextUtils.isEmpty(this.a.j.getText().toString())) {
                        Toast.makeText(this.a.e, "请输入要搜索的内容", 0).show();
                    } else {
                        this.a.a(this.a.j.getText().toString());
                    }
                }
                return false;
            }
        });
    }

    private void a(String str) {
        switch (this.s.getCurrentItem()) {
            case 0:
                this.u.b(this.j.getText().toString());
                return;
            case 1:
                this.v.b(this.j.getText().toString());
                return;
            case 2:
                this.w.b(this.j.getText().toString());
                return;
            default:
                return;
        }
    }

    private void j() {
        getWindowManager().getDefaultDisplay().getMetrics(this.x);
        y = this.x.widthPixels / 3;
        this.K = (y - this.J) / 2;
        this.u = new c();
        this.v = new b();
        this.w = new d();
        this.u.a((com.budejie.www.g.a) this);
        this.v.a((com.budejie.www.g.a) this);
        this.w.a((com.budejie.www.g.a) this);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.u);
        arrayList.add(this.v);
        arrayList.add(this.w);
        this.t = new a(this, getSupportFragmentManager(), arrayList);
        this.s.setAdapter(this.t);
        this.s.setOnPageChangeListener(new b(this));
        this.s.setOffscreenPageLimit(2);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), this.L == 0 ? R.drawable.tab_line_day_image : R.drawable.tab_line_night_image);
        int width = decodeResource.getWidth();
        int height = decodeResource.getHeight();
        if (this.J > width) {
            this.J = width;
            this.K = (y - this.J) / 2;
        }
        width = an.b((Context) this, 2.0f);
        if (width <= height) {
            height = width;
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, this.J, height);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) this.K, 0.0f);
        this.r.setImageMatrix(matrix);
        this.r.setImageBitmap(createBitmap);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
    }

    private void k() {
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String h = j.h("0");
        j jVar = this.H;
        netWorkUtil.a(requstMethod, h, j.e(this.e), this.Q);
    }

    public boolean a(List<ListItemObject> list) {
        return list == null || list.isEmpty();
    }

    public void labelSearch$click(View view) {
        if (this.l.isShown()) {
            MobclickAgent.onEvent(this.e, "E06_A17", "搜索框点击");
            this.l.startAnimation(m());
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.E.g();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                finish();
                return;
            case R.id.BackImageView:
                finish();
                return;
            case R.id.del_keywords_btn:
                this.j.requestFocus();
                this.j.getText().clear();
                return;
            case R.id.SearchPostTextView:
                this.s.setCurrentItem(0);
                return;
            case R.id.SearchLabelTextView:
                this.s.setCurrentItem(1);
                return;
            case R.id.SearchUserTextView:
                this.s.setCurrentItem(2);
                return;
            default:
                return;
        }
    }

    private void l() {
        this.z.setVisibility(8);
        this.l.setVisibility(8);
        this.n.setVisibility(0);
        this.m.setVisibility(0);
        this.j.setFocusable(true);
        this.j.setFocusableInTouchMode(true);
        this.j.requestFocus();
        this.f.showSoftInput(this.j, 0);
        this.s.setCurrentItem(0);
        this.u.d();
        this.G = true;
    }

    private Animation m() {
        Animation translateAnimation = new TranslateAnimation(0.0f, (float) (-this.h.getWidth()), 0.0f, 0.0f);
        translateAnimation.setDuration(150);
        translateAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ SearchMainActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.l();
            }
        });
        return translateAnimation;
    }

    private void a(TextView textView) {
        int color;
        if (this.L == 0) {
            color = getResources().getColor(R.color.main_red);
        } else {
            color = getResources().getColor(R.color.main_red_black);
        }
        textView.setTextColor(color);
    }

    public void a(int i, Object obj, boolean z) {
        switch (i) {
            case 0:
                Object obj2 = "" + obj;
                this.j.setText(obj2);
                this.j.setSelection(obj2.length());
                return;
            case 1:
                this.f.hideSoftInputFromWindow(this.j.getWindowToken(), 0);
                this.u.a(true, "" + obj);
                return;
            default:
                return;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }
}
