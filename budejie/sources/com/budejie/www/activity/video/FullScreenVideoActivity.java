package com.budejie.www.activity.video;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.activity.video.f.d;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.m;
import com.budejie.www.util.q;
import com.budejie.www.widget.FavorLayout;
import com.budejie.www.widget.NewTitleView;
import com.budejie.www.widget.curtain.BarrageStatusManager;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FullScreenVideoActivity extends OauthWeiboBaseAct implements OnClickListener, b, com.budejie.www.f.a {
    public static String a = "video_data";
    private com.budejie.www.http.b A;
    private f B;
    private net.tsz.afinal.a.a<String> C = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ FullScreenVideoActivity a;

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
                final /* synthetic */ AnonymousClass6 a;

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
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    try {
                        this.a.a.k = com.budejie.www.j.a.a(this.a.a, strArr[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute(new String[]{str});
        }
    };
    private OnItemClickListener D = new OnItemClickListener(this) {
        final /* synthetic */ FullScreenVideoActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListItemObject listItemObject = (ListItemObject) adapterView.getItemAtPosition(i);
            if (listItemObject != null) {
                Log.d("FullScreenVideoActivity", "videoItem" + listItemObject.getContent());
                i.a(this.a.getString(R.string.track_event_video_click_recommend), j.a(listItemObject), j.b(this.a, listItemObject));
                Intent intent = new Intent(this.a.c, FullScreenVideoActivity.class);
                intent.putExtra(FullScreenVideoActivity.a, listItemObject);
                this.a.c.startActivity(intent);
                this.a.c.overridePendingTransition(R.anim.switch_style_enter, R.anim.switch_style_exit);
                this.a.c.finish();
            }
        }
    };
    private com.budejie.www.widget.NewTitleView.a E = new com.budejie.www.widget.NewTitleView.a(this) {
        final /* synthetic */ FullScreenVideoActivity a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            if (this.a.f != null && this.a.f.a()) {
                this.a.a(listItemObject, false);
            }
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.B.a("cai", this.a.b, listItemObject);
            this.a.B.a(listItemObject, this.a.b, "cai");
        }

        public void a(View view) {
            this.a.a(view);
        }

        public void a(BarrageState barrageState) {
            this.a.j.a(barrageState);
        }

        public void c(View view, ListItemObject listItemObject) {
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
            bundle.putString(PersonalProfileActivity.d, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            this.a.q.a(7, bundle).onClick(view);
        }
    };
    final Handler b = new Handler(this) {
        final /* synthetic */ FullScreenVideoActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 7) {
                try {
                    an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
                } catch (Exception e) {
                }
            } else if (i == 9) {
                this.a.d.setRepost(String.valueOf(Integer.parseInt(this.a.d.getRepost()) + 1));
                m.a(this.a.c, this.a.b, this.a.d);
            } else if (i == 91) {
                this.a.d.setRepost(String.valueOf(Integer.parseInt(this.a.d.getRepost()) + 1));
            } else if (i == 10) {
                an.a(this.a, this.a.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                CharSequence b = ai.b(this.a);
                if (an.j(this.a) && an.k(this.a) && !b.equals("")) {
                    an.a(this.a, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.z = "add";
                    this.a.A.a(this.a.z, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.c, this.a.c.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a.c))) {
                    this.a.z = "add";
                    this.a.A.a(this.a.z, ai.b(this.a.c), (String) message.obj, 971);
                }
            } else if (i == 829) {
                String str = (String) message.obj;
                if (this.a.v != null) {
                    this.a.v.a("collectTable", str);
                }
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.z = "delete";
                this.a.A.a(this.a.z, ai.b(this.a), str, 971);
            }
        }
    };
    private FullScreenVideoActivity c;
    private ListItemObject d;
    private String e;
    private NewTitleView f;
    private boolean g = false;
    private VideoView h;
    private f i;
    private com.budejie.www.widget.curtain.b j;
    private ArrayList<ListItemObject> k;
    private ArrayList<Fragment> l;
    private RelativeLayout m;
    private ViewPager n;
    private LinearLayout o;
    private ImageView[] p;
    private com.budejie.www.g.b q;
    private HashMap<String, String> r;
    private IWXAPI s;
    private n t;
    private com.budejie.www.c.m u;
    private com.budejie.www.c.b v;
    private com.elves.update.a w;
    private SharedPreferences x;
    private String y;
    private String z = "add";

    public class a implements OnPageChangeListener {
        final /* synthetic */ FullScreenVideoActivity a;

        public a(FullScreenVideoActivity fullScreenVideoActivity) {
            this.a = fullScreenVideoActivity;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageSelected(int i) {
            this.a.b(i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_full_screen_video);
        b();
        e();
        f();
        a();
        g();
        this.h.post(new Runnable(this) {
            final /* synthetic */ FullScreenVideoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                k.a(this.a.c).h();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        try {
            if (!(!this.g || this.h == null || this.m.getVisibility() == 0)) {
                Log.d("FullScreenVideoActivity", "onResume mVideoView.start()");
                this.h.a();
                this.g = false;
            }
        } catch (Exception e) {
            MobclickAgent.onEvent(this.c, "cacheException", "FullScreenVideoActivity onResume:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        this.h.post(new Runnable(this) {
            final /* synthetic */ FullScreenVideoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    LayoutParams layoutParams = this.a.i.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    this.a.i.setLayoutParams(layoutParams);
                } catch (Exception e) {
                    MobclickAgent.onEvent(this.a.c, "cacheException", "FullScreenVideoActivity onResume run:" + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onPause() {
        super.onPause();
        this.g = true;
        this.h.b();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.h.g();
    }

    private void b() {
        this.c = this;
        this.A = com.budejie.www.http.b.a(this.c, this.c);
        this.w = new com.elves.update.a(this);
        this.t = new n(this);
        this.q = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.B = new f(this);
        this.u = new com.budejie.www.c.m(this);
        this.v = new com.budejie.www.c.b(this);
        this.y = ai.b(this);
        this.r = this.t.a(this.y);
        this.x = getSharedPreferences("weiboprefer", 0);
        c();
    }

    private void c() {
        this.s = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.s.registerApp("wx592fdc48acfbe290");
    }

    private void e() {
        this.f = (NewTitleView) findViewById(R.id.new_title_view);
        this.h = (VideoView) findViewById(R.id.video_view);
        this.j = new com.budejie.www.widget.curtain.b(this.c, (com.budejie.www.activity.video.barrage.a.f) findViewById(R.id.barrage_full_screen_container), (FavorLayout) findViewById(R.id.favor_layout));
        this.j.a(BarrageStatusManager.a(this.x));
    }

    private void f() {
        this.l = new ArrayList();
        this.m = (RelativeLayout) findViewById(R.id.rl_video_recommended);
        this.n = (ViewPager) findViewById(R.id.video_recommend_viewpager);
        this.o = (LinearLayout) findViewById(R.id.video_recommend_viewpager_indicator);
    }

    public void a() {
        if (getIntent() != null) {
            this.d = (ListItemObject) getIntent().getSerializableExtra(a);
            if (this.d != null) {
                this.j.a(this.d.getWid());
                this.f.setmTitleLayoutClick(this.E);
                this.f.setmListItemObject(this.d);
                this.f.a(true);
                a(this.d);
                PlateBean plateBean = this.d.getPlateBean(0);
                if (plateBean != null) {
                    this.e = plateBean.theme_id;
                    return;
                }
                return;
            }
            finish();
        }
    }

    private void a(final ListItemObject listItemObject) {
        this.i = new f(this, listItemObject, 0);
        this.i.setDoubleClickCallback(this);
        this.i.setTopContext(this);
        this.i.setFullScreen(true);
        this.i.a(a.a((Activity) this), a.b(this));
        this.i.setWid(listItemObject.getWid());
        this.i.setStartPlayAndPlayScheduleListener(new d(this) {
            final /* synthetic */ FullScreenVideoActivity b;

            public void a() {
                Log.d("FullScreenVideoActivity", "startPlay");
                if (this.b.m.getVisibility() == 0) {
                    this.b.h.b();
                } else if (!(listItemObject == null || TextUtils.isEmpty(listItemObject.getPlaycount()))) {
                    listItemObject.setPlaycount(String.valueOf(Integer.parseInt(listItemObject.getPlaycount()) + 1));
                }
                this.b.i.w();
            }

            public void a(int i) {
                this.b.j.a(i);
            }

            public void b() {
                i.a(this.b.getString(R.string.track_event_video_play_complete), j.a(listItemObject), j.b(this.b, listItemObject));
                this.b.h();
            }

            public void a(boolean z) {
            }

            public void c() {
            }
        });
        this.h.setMircroMediaController(this.i);
        this.h.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ FullScreenVideoActivity b;

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                try {
                    if (TextUtils.isEmpty(listItemObject.getVideouriBackup()) || listItemObject.getVideouri().equals(listItemObject.getVideouriBackup())) {
                        this.b.finish();
                        Toast.makeText(this.b.c, this.b.c.getString(R.string.loading_video_error_tip) + "(" + i + "," + i2 + ")", 0).show();
                        return true;
                    }
                    this.b.h.setVideoPath(listItemObject.getVideouriBackup());
                    listItemObject.setVideouri(listItemObject.getVideouriBackup());
                    this.b.h.a();
                    return true;
                } catch (Exception e) {
                    this.b.finish();
                    Toast.makeText(this.b.c, this.b.c.getString(R.string.loading_video_error_tip) + "  (" + i + "," + i2 + ")", 0).show();
                    e.printStackTrace();
                }
            }
        });
        this.h.setVideoPath(listItemObject.getVideouri());
        Log.d("FullScreenVideoActivity", "mVideoView.start()");
        this.h.a();
    }

    private void g() {
        if (!TextUtils.isEmpty(this.e)) {
            BudejieApplication.a.a(RequstMethod.GET, j.b(this.e, "0", "36", "recommend"), new j(this), this.C);
        }
    }

    private void h() {
        this.k = b(this.k);
        if (this.k == null || this.k.size() <= 0) {
            finish();
            return;
        }
        int size = this.k.size();
        int i = size > 18 ? 3 : size / 6;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = size >= ((i2 + 1) * 6) + 1 ? (i2 + 1) * 6 : size - 1;
            m mVar = new m();
            mVar.a(this.D);
            Serializable arrayList = new ArrayList();
            for (int i4 = i2 * 6; i4 < i3; i4++) {
                arrayList.add(this.k.get(i4));
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("recommend_data_key", arrayList);
            mVar.setArguments(bundle);
            mVar.setRetainInstance(false);
            this.l.add(mVar);
        }
        i();
        this.n.setAdapter(new g(getSupportFragmentManager(), this.l));
        this.n.setCurrentItem(0);
        this.n.setOnPageChangeListener(new a(this));
        this.m.setVisibility(0);
        this.i.a();
    }

    private void i() {
        this.p = new ImageView[this.l.size()];
        for (int i = 0; i < this.l.size(); i++) {
            ImageView imageView = new ImageView(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(15, 0, 15, 0);
            imageView.setLayoutParams(layoutParams);
            this.p[i] = imageView;
            if (i == 0) {
                this.p[i].setBackgroundResource(R.drawable.video_indictor_fucos);
            } else {
                this.p[i].setBackgroundResource(R.drawable.video_indictor_normal);
            }
            this.o.addView(this.p[i]);
        }
    }

    private void b(int i) {
        for (int i2 = 0; i2 < this.p.length; i2++) {
            this.p[i].setBackgroundResource(R.drawable.video_indictor_fucos);
            if (i != i2) {
                this.p[i2].setBackgroundResource(R.drawable.video_indictor_normal);
            }
        }
    }

    public static boolean a(ArrayList arrayList) {
        return arrayList == null || arrayList.size() == 0;
    }

    public static ArrayList b(ArrayList arrayList) {
        if (a(arrayList)) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        do {
            arrayList2.add(arrayList.remove(Math.abs(new Random().nextInt(arrayList.size()))));
        } while (arrayList.size() > 0);
        return arrayList2;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_share:
                Log.d("FullScreenVideoActivity", "video_share");
                a(view);
                return;
            default:
                return;
        }
    }

    private void a(View view) {
        if (this.d != null) {
            view.setTag(this.d);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
            bundle.putSerializable("weiboMap", this.r);
            bundle.putSerializable("data", this.d);
            this.q.a(5, bundle, this.b, this.s, this.u, this.t, this.w, this.x, null).onClick(view);
        }
    }

    public void a(ListItemObject listItemObject, boolean z) {
        TipPopUp.a(this.c, TypeControl.dingtie);
        this.B.a("ding", this.b, listItemObject);
        this.B.a(listItemObject, this.b, "ding");
        if (z) {
            q.a(this.i.b);
        }
    }

    public void a(int i, String str) {
    }

    public void a(int i) {
    }

    public void b_() {
        if (this.f != null && this.f.a()) {
            a(this.d, true);
        }
    }
}
