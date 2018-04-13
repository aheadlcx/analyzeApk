package qsbk.app.im.group.vote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.HttpTask;
import qsbk.app.im.group.vote.adapter.GroupManagerCandidateAvatarAdapter;
import qsbk.app.im.group.vote.adapter.GroupManagerCandidateProfileAdapter;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;
import qsbk.app.im.group.vote.bean.UserAvatarInfo;
import qsbk.app.im.group.vote.view.GroupManagerCampaignDialog;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

public class GroupManagerVoteActivity extends BaseActionBarActivity {
    private static final String b = GroupManagerVoteActivity.class.getSimpleName();
    protected GroupManagerCampaignDialog a;
    private ArrayList<UserAvatarInfo> c = new ArrayList();
    private ArrayList<GroupManagerCandidate> d = new ArrayList();
    private GroupManagerCandidateAvatarAdapter e;
    private GroupManagerCandidateProfileAdapter f;
    private RelativeLayout g;
    private TextView h;
    private LinearLayout i;
    private ImageView j;
    private TextView k;
    private Gallery l;
    private ViewPager m;
    private ProgressBar n;
    private int o = 0;
    private HttpTask p;
    private int q;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, int i, String str) {
        Intent intent = new Intent(context, GroupManagerVoteActivity.class);
        intent.putExtra("gid", i);
        intent.putExtra(QYQShareInfo.TYPE_VOTE, str);
        context.startActivity(intent);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
    }

    protected String f() {
        return getResources().getString(R.string.group_manager_vote);
    }

    protected int a() {
        return R.layout.activity_group_manager_vote;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        DebugUtil.debug(b, "init");
        this.q = getIntent().getIntExtra("gid", 0);
        String stringExtra = getIntent().getStringExtra(QYQShareInfo.TYPE_VOTE);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        i();
        j();
        try {
            a(stringExtra);
        } catch (JSONException e) {
            e.printStackTrace();
            finish();
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, b);
        this.e.notifyDataSetChanged();
        this.f.notifyDataSetChanged();
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, b);
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.p != null) {
            this.p.cancel(true);
            this.p = null;
        }
    }

    public void finish() {
        super.finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showLoading() {
        setProgressBarIndeterminateVisibility(true);
    }

    public void hideLoading() {
        setProgressBarIndeterminateVisibility(false);
    }

    private void i() {
        this.l = (Gallery) findViewById(R.id.avatars);
        this.g = (RelativeLayout) findViewById(R.id.rl_candidate_info);
        this.h = (TextView) findViewById(R.id.tv_candidate_name);
        this.i = (LinearLayout) findViewById(R.id.ll_candidate_gender_age);
        this.j = (ImageView) findViewById(R.id.iv_candidate_gender);
        this.k = (TextView) findViewById(R.id.tv_candidate_age);
        this.m = (ViewPager) findViewById(R.id.profiles);
        this.n = (ProgressBar) findViewById(R.id.pb_loading);
        this.n.setVisibility(8);
    }

    private void j() {
        DebugUtil.debug(b, "initListener, mCandidates=" + this.d.size());
        this.l.setFocusableInTouchMode(false);
        this.l.setFocusable(false);
        this.l.setOnTouchListener(new a(this));
        this.e = new GroupManagerCandidateAvatarAdapter(this, this.c);
        this.l.setAdapter(this.e);
        this.l.setOnItemSelectedListener(new b(this));
        this.f = new GroupManagerCandidateProfileAdapter(this, this.q, this.d);
        this.m.setAdapter(this.f);
        this.m.setOnPageChangeListener(new c(this));
    }

    private void a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        boolean optBoolean = jSONObject.optBoolean("can_vote");
        int optInt = jSONObject.optInt("voted_id");
        JSONArray optJSONArray = jSONObject.optJSONArray("cand_info");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                this.c.add(new UserAvatarInfo(optJSONObject));
                this.d.add(new GroupManagerCandidate(optJSONObject));
            }
        }
        k();
        l();
        if (this.o < this.d.size()) {
            a((GroupManagerCandidate) this.d.get(this.o), false);
        } else {
            a(null, true);
        }
        this.e.notifyDataSetChanged();
        this.f.setCanVote(optBoolean, optInt);
        this.f.notifyDataSetChanged();
        if (optBoolean) {
            showGMBrowseDialog();
        } else if (optInt != 0) {
            this.m.setCurrentItem(this.f.getCount() - 1);
        }
    }

    private void a(GroupManagerCandidate groupManagerCandidate, boolean z) {
        if (z) {
            this.h.setText("投票");
            this.i.setVisibility(8);
            return;
        }
        this.h.setText(groupManagerCandidate.nickName);
        this.i.setVisibility(0);
        if ("F".equalsIgnoreCase(groupManagerCandidate.gender)) {
            this.j.setImageResource(R.drawable.pinfo_female);
            this.i.setBackgroundResource(R.drawable.pinfo_female_bg);
        } else {
            this.j.setImageResource(R.drawable.pinfo_male);
            this.i.setBackgroundResource(R.drawable.pinfo_man_bg);
        }
        this.k.setText(String.valueOf(groupManagerCandidate.age));
    }

    private void k() {
        this.e.appendUserAvatarInfos(new UserAvatarInfo(100, null, "投票"));
        this.e.notifyDataSetChanged();
    }

    private void l() {
        this.f.appendGMCandidates(new GroupManagerCandidate());
        this.f.notifyDataSetChanged();
    }

    public void showGMBrowseDialog() {
        this.a = new GroupManagerCampaignDialog(this, R.style.group_manager_browse_dialog);
        Window window = this.a.getWindow();
        LayoutParams attributes = window.getAttributes();
        window.setGravity(17);
        attributes.dimAmount = 0.8f;
        window.setAttributes(attributes);
        this.a.setCanceledOnTouchOutside(true);
        this.a.show();
        ((TextView) this.a.findViewById(R.id.tv_browse_btn)).setOnClickListener(new d(this));
    }
}
