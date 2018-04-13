package qsbk.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class ClockedRankingActivity extends BaseActionBarActivity implements PtrListener {
    private int a = 1;
    private PtrLayout b;
    private ListView c;
    private SimpleHttpTask d;
    private ArrayList<Object> e;
    private ClockedRandkingAdapter f;
    private int g;

    public class ClockedRandkingAdapter extends BaseImageAdapter {
        final /* synthetic */ ClockedRankingActivity a;

        public ClockedRandkingAdapter(ClockedRankingActivity clockedRankingActivity, ArrayList<Object> arrayList, Activity activity) {
            this.a = clockedRankingActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null || !(view.getTag() instanceof a)) {
                aVar = new a();
                view = LayoutInflater.from(this.k).inflate(R.layout.clocked_ranking_item, null);
                view.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
                aVar.f = (TextView) view.findViewById(R.id.age);
                aVar.i = (TextView) view.findViewById(R.id.ranking_num);
                aVar.b = (TextView) view.findViewById(R.id.name);
                aVar.a = (ImageView) view.findViewById(R.id.avatar);
                aVar.c = (TextView) view.findViewById(R.id.info);
                aVar.d = (LinearLayout) view.findViewById(R.id.gender_age);
                aVar.e = (ImageView) view.findViewById(R.id.gender);
                aVar.g = (TextView) view.findViewById(R.id.astrology);
                aVar.h = view.findViewById(R.id.divider);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            ClockedRanking clockedRanking = (ClockedRanking) getItem(i);
            aVar.b.setText(clockedRanking.userName);
            aVar.c.setTextColor(UIHelper.isNightTheme() ? -4359140 : -17664);
            aVar.c.setText(clockedRanking.a + "天");
            aVar.g.setText(clockedRanking.astrology);
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(clockedRanking.userIcon, clockedRanking.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                aVar.a.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                FrescoImageloader.displayAvatar(aVar.a, absoluteUrlOfMediumUserIcon);
            }
            aVar.d.setVisibility(0);
            if (UIHelper.isNightTheme()) {
                aVar.g.setBackgroundColor(this.k.getResources().getColor(R.color.transparent));
                aVar.d.setBackgroundColor(0);
                if ("F".equalsIgnoreCase(clockedRanking.gender)) {
                    aVar.e.setImageResource(R.drawable.pinfo_female_dark);
                    aVar.f.setTextColor(this.k.getResources().getColor(R.color.age_female));
                    aVar.g.setTextColor(this.k.getResources().getColor(R.color.age_female));
                } else if ("M".equalsIgnoreCase(clockedRanking.gender)) {
                    aVar.e.setImageResource(R.drawable.pinfo_male_dark);
                    aVar.f.setTextColor(this.k.getResources().getColor(R.color.age_male));
                    aVar.g.setTextColor(this.k.getResources().getColor(R.color.age_male));
                } else {
                    aVar.d.setVisibility(4);
                }
            } else {
                if ("F".equalsIgnoreCase(clockedRanking.gender)) {
                    aVar.d.setBackgroundResource(R.drawable.pinfo_female_bg);
                    aVar.e.setImageResource(R.drawable.pinfo_female);
                } else if ("M".equalsIgnoreCase(clockedRanking.gender)) {
                    aVar.d.setBackgroundResource(R.drawable.pinfo_man_bg);
                    aVar.e.setImageResource(R.drawable.pinfo_male);
                } else {
                    aVar.d.setVisibility(4);
                }
                aVar.f.setTextColor(-1);
            }
            if (clockedRanking.age <= 0) {
                aVar.d.setVisibility(4);
            }
            aVar.f.setText(String.valueOf(clockedRanking.age));
            int i2 = clockedRanking.b;
            if (i2 == 1) {
                aVar.i.setText("");
                TextView a = aVar.i;
                if (UIHelper.isNightTheme()) {
                    i2 = R.drawable.group_levle_gold_night;
                } else {
                    i2 = R.drawable.group_level_gold;
                }
                a.setBackgroundResource(i2);
            } else if (i2 == 2) {
                aVar.i.setText("");
                aVar.i.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_silver_night : R.drawable.group_level_silver);
            } else if (i2 == 3) {
                aVar.i.setText("");
                aVar.i.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_copper_night : R.drawable.group_level_copper);
            } else {
                aVar.i.setBackgroundColor(0);
                aVar.i.setText(i2 + "");
            }
            aVar.h.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1184275);
            if (i == this.a.e.size() - 1) {
                aVar.h.setVisibility(8);
            } else {
                aVar.h.setVisibility(0);
            }
            return view;
        }
    }

    public class ClockedRanking extends BaseUserInfo {
        int a;
        int b;
        final /* synthetic */ ClockedRankingActivity c;

        public ClockedRanking(ClockedRankingActivity clockedRankingActivity) {
            this.c = clockedRankingActivity;
        }

        public void pasreJson(JSONObject jSONObject) {
            parseBaseInfo(jSONObject);
            this.a = jSONObject.optInt("days");
            this.b = jSONObject.optInt("rank");
        }
    }

    private static class a {
        ImageView a;
        TextView b;
        TextView c;
        LinearLayout d;
        ImageView e;
        TextView f;
        TextView g;
        View h;
        private TextView i;

        private a() {
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected int a() {
        return R.layout.activity_clocked_ranking;
    }

    protected String e() {
        return "连续打卡榜";
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        f();
    }

    private void f() {
        this.g = getIntent().getIntExtra("topicId", 0);
        this.a = 1;
        i();
    }

    private void g() {
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.b.setLoadMoreEnable(false);
        this.b.setPtrListener(this);
        this.c = (ListView) findViewById(R.id.listview);
        this.c.setOnItemClickListener(new jf(this));
        this.e = new ArrayList();
        this.f = new ClockedRandkingAdapter(this, this.e, this);
        this.c.setAdapter(this.f);
    }

    public void onRefresh() {
        if (this.d != null) {
            this.d.cancel(true);
            this.d = null;
        }
        this.a = 1;
        i();
    }

    private void i() {
        this.d = new SimpleHttpTask(String.format(Constants.CLOCKED_RANDING, new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.a), Integer.valueOf(30)}), new jg(this));
        this.d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onLoadMore() {
        i();
    }
}
