package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.EditorMsg;
import qsbk.app.model.EditorMsg.Vote;
import qsbk.app.model.Qsjx;
import qsbk.app.model.RssArticle;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;

public class HighLightQiushiActivity extends BaseActionBarActivity {
    public static final String KEY_ARTICLE_IDS = "_ids";
    public static final String KEY_TIME = "_time";
    public static final String KEY_TITLE = "_title";
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy年MM月dd日");
    protected ProgressBar a;
    protected TextView b = null;
    private String d;
    private String e;
    private long f;
    private String g;
    private QiushiListFragment h;
    private String i;

    public static class QiushiListFragment extends BaseArticleListViewFragment {
        private String Q = "";
        private long R;
        private String S;
        private Qsjx T;
        private String U;
        private View V;

        public static QiushiListFragment newInstance(String str, String str2, long j, String str3) {
            QiushiListFragment qiushiListFragment = new QiushiListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ids", str);
            bundle.putString("title", str2);
            bundle.putLong("time", j);
            bundle.putString("msgId", str3);
            qiushiListFragment.setArguments(bundle);
            return qiushiListFragment;
        }

        protected void a() {
            super.a();
            this.U = getArguments().getString("ids", "");
            this.R = getArguments().getLong("time");
            this.Q = getArguments().getString("title");
            this.S = getArguments().getString("msgId", "");
            this.u = String.format(Constants.HIGH_LIGHT, new Object[]{this.U, this.S});
            this.v = "highlight" + this.U.hashCode() + this.S.hashCode();
            this.w = false;
        }

        protected void a(View view) {
            this.l = (PtrLayout) view.findViewById(R.id.ptr);
            this.m = (ListView) view.findViewById(R.id.listview);
            this.l.setLoadMoreEnable(false);
            this.l.setPtrListener(this);
            this.l.setOnScrollListener(this);
            this.i = b();
            this.m.setAdapter(this.i);
            this.l.setLoadMoreEnable(false);
            this.l.setRefreshEnable(false);
        }

        protected BaseImageAdapter b() {
            if (this.m.getHeaderViewsCount() == 0) {
                this.V = LayoutInflater.from(this.B).inflate(R.layout.highligh_qiushit_head, null);
                this.V.setLayoutParams(new LayoutParams(-1, -1));
                this.V.setMinimumHeight(UIHelper.dip2px(this.B, 84.0f));
                c();
                this.m.addHeaderView(this.V);
            }
            return super.b();
        }

        void c() {
            if (this.V != null) {
                ((TextView) this.V.findViewById(R.id.title)).setText(this.Q);
                ((TextView) this.V.findViewById(R.id.time)).setText(HighLightQiushiActivity.c.format(new Date(this.R)));
            }
        }

        protected boolean a(String str) {
            if (getActivity() == null) {
                return false;
            }
            try {
                String optString;
                String optString2;
                CharSequence optString3;
                CharSequence charSequence;
                CharSequence charSequence2;
                String optString4;
                String optString5;
                String str2;
                String str3;
                String str4;
                String str5;
                String optString6;
                String str6;
                String str7;
                String str8;
                if (this.j.contains(this.K)) {
                    this.j.clear();
                }
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("items");
                this.p = 10;
                int length = jSONArray.length();
                this.q = true;
                if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                    this.j.clear();
                }
                for (int i = 0; i < length; i++) {
                    try {
                        if (jSONArray.optJSONObject(i) != null) {
                            RssArticle rssArticle = new RssArticle(jSONArray.optJSONObject(i));
                            if (!this.j.contains(rssArticle)) {
                                this.j.add(rssArticle);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("msg");
                String str9 = "";
                String str10 = "";
                String str11 = "";
                Object obj = "";
                if (optJSONObject != null) {
                    this.Q = optJSONObject.optString("title");
                    this.R = optJSONObject.optLong(QsbkDatabase.CREATE_AT) * 1000;
                    str9 = optJSONObject.optString("push_image");
                    str10 = optJSONObject.optString("push_detail");
                    str11 = optJSONObject.optString("url");
                    obj = optJSONObject.optString("header");
                    if (!TextUtils.isEmpty(obj)) {
                        HighLightQiushiActivity highLightQiushiActivity = (HighLightQiushiActivity) getActivity();
                        ActionBar supportActionBar = highLightQiushiActivity.getSupportActionBar();
                        highLightQiushiActivity.setShareTitle(obj);
                        if (supportActionBar != null) {
                            supportActionBar.setTitle(obj);
                        }
                    }
                    c();
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("editor");
                if (optJSONObject2 != null) {
                    optString = optJSONObject2.optString(QsbkDatabase.LOGIN);
                    optString2 = optJSONObject2.optString("icon");
                    optString3 = optJSONObject2.optString("brief");
                    charSequence = optString2;
                    charSequence2 = optString;
                } else {
                    optString3 = null;
                    charSequence = null;
                    charSequence2 = null;
                }
                JSONObject optJSONObject3 = jSONObject.optJSONObject(QYQShareInfo.TYPE_VOTE);
                if (optJSONObject3 != null) {
                    String optString7 = optJSONObject3.optString("up");
                    optString4 = optJSONObject3.optString("down");
                    optString = optJSONObject3.optString("up_desc");
                    optString2 = optJSONObject3.optString("down_desc");
                    optString5 = optJSONObject3.optString("my");
                    str2 = optString2;
                    str3 = optString;
                    str4 = optString4;
                    str5 = optString7;
                } else {
                    optString5 = null;
                    str2 = null;
                    str3 = null;
                    str4 = null;
                    str5 = null;
                }
                JSONObject optJSONObject4 = jSONObject.optJSONObject("bottom");
                if (optJSONObject4 != null) {
                    optString4 = optJSONObject4.optString(SocialConstants.PARAM_APP_DESC);
                    optString = optJSONObject4.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    optString2 = optJSONObject4.optString("tribe_id");
                    optString6 = optJSONObject4.optString("highlight");
                    str6 = optString2;
                    str7 = optString;
                    str8 = optString4;
                } else {
                    optString6 = null;
                    str6 = null;
                    str7 = null;
                    str8 = null;
                }
                String[] strArr = null;
                if (this.U != null) {
                    strArr = this.U.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                if (!(strArr == null || TextUtils.isEmpty(this.S) || TextUtils.isEmpty(charSequence))) {
                    if (TextUtils.isEmpty(obj)) {
                        this.T = new Qsjx(this.Q, this.R, str9, strArr, this.S, str10, str11, "糗事精选");
                    } else {
                        this.T = new Qsjx(this.Q, this.R, str9, strArr, this.S, str10, str11, obj);
                    }
                }
                if (!(TextUtils.isEmpty(charSequence2) || TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(optString3))) {
                    this.j.add(new EditorMsg(this.S, charSequence2, charSequence, optString3, str8, str7, str6, optString6, new Vote(str5, str4, str3, str2, optString5)));
                }
                if (o()) {
                    v();
                }
                this.o++;
                getActivity().invalidateOptionsMenu();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public Qsjx getQsjx() {
            return this.T;
        }

        protected void d() {
            onRefresh();
        }

        protected void a(int i, boolean z) {
            super.a(i, z);
            a.post(new oh(this, z));
            a.postDelayed(new oi(this), (long) (i + 100));
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void luanchActivity(Activity activity, String str, String str2, long j) {
        Intent intent = new Intent(activity, HighLightQiushiActivity.class);
        intent.putExtra(KEY_ARTICLE_IDS, str);
        intent.putExtra(KEY_TIME, j);
        intent.putExtra("_title", str2);
        activity.startActivity(intent);
    }

    public static void luanchActivity(Activity activity, String str, String str2, long j, String str3) {
        Intent intent = new Intent(activity, HighLightQiushiActivity.class);
        intent.putExtra(KEY_ARTICLE_IDS, str);
        intent.putExtra(KEY_TIME, j);
        intent.putExtra("_title", str2);
        intent.putExtra("_msg_id", str3);
        activity.startActivity(intent);
    }

    public static void luanchActivity(Context context, String str, String str2, long j) {
        Intent intent = new Intent(context, HighLightQiushiActivity.class);
        intent.putExtra(KEY_ARTICLE_IDS, str);
        intent.putExtra(KEY_TIME, j);
        intent.putExtra("_title", str2);
        context.startActivity(intent);
    }

    public static void luanchActivity(Context context, String str) {
        Intent intent = new Intent(context, HighLightQiushiActivity.class);
        intent.putExtra("_msg_id", str);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public void setShareTitle(String str) {
        this.i = str;
    }

    protected String e() {
        return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.higlight_qiushi_activity, menu);
        boolean z = false;
        if (this.h != null) {
            Qsjx qsjx = this.h.getQsjx();
            if (!(qsjx == null || TextUtils.isEmpty(qsjx.msgId))) {
                z = true;
            }
        }
        menu.findItem(R.id.share).setVisible(z);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.share) {
            return super.onOptionsItemSelected(menuItem);
        }
        ShareUtils.openShareDialog(this, InputDeviceCompat.SOURCE_KEYBOARD);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == InputDeviceCompat.SOURCE_KEYBOARD) {
            Qsjx qsjx = this.h == null ? null : this.h.getQsjx();
            if (qsjx == null || TextUtils.isEmpty(qsjx.msgId)) {
                ToastAndDialog.makeText(this, "该精选暂不支持分享").show();
            } else {
                ShareUtils.doShare(i2, this, this.h.getQsjx());
            }
        }
    }

    private void i() {
        if (this.a == null) {
            this.a = new ProgressBar(this);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            addContentView(this.a, layoutParams);
        }
        this.a.setVisibility(0);
    }

    private void j() {
        if (this.a != null) {
            this.a.setVisibility(4);
        }
    }

    protected void f() {
        if (this.a != null) {
            this.a.setVisibility(8);
        }
        if (this.b == null) {
            this.b = new TextView(this);
            this.b.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.b.setText(Html.fromHtml("加载失败，请稍后<font color=#000000>点我</font>重试..."));
            this.b.setOnClickListener(new og(this));
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            addContentView(this.b, layoutParams);
            return;
        }
        this.b.setVisibility(0);
    }

    protected int a() {
        return R.layout.activity_highlight_qiushi;
    }

    protected void a(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra(KEY_ARTICLE_IDS);
        this.d = getIntent().getStringExtra("_msg_id");
        this.f = getIntent().getLongExtra(KEY_TIME, System.currentTimeMillis());
        this.e = getIntent().getStringExtra("_title");
        if ((TextUtils.isEmpty(stringExtra) || stringExtra.length() <= 2) && TextUtils.isEmpty(this.d)) {
            finish();
        } else if (bundle == null) {
            setActionbarBackable();
            if (!TextUtils.isEmpty(this.g)) {
                this.g = stringExtra.replaceAll("[\\[\\]'\"]", "");
            }
            this.h = QiushiListFragment.newInstance(this.g, this.e, this.f, this.d);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, this.h).commit();
        }
    }
}
