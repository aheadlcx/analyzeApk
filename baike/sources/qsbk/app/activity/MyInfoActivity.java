package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.model.FamilyInfo;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ContactListAdapter;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.UserChatManager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.ArticleImage;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.Medal;
import qsbk.app.model.PicUrl;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.UserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.nearby.api.ObservableScrollView;
import qsbk.app.nearby.api.PersonalListener;
import qsbk.app.nearby.api.PersonalListener.GetPersonalDynamic;
import qsbk.app.nearby.api.PersonalListener.GetPersonalGroup;
import qsbk.app.nearby.api.PersonalListener.GetPersonalInfo;
import qsbk.app.nearby.api.PersonalListener.GetPersonalLive;
import qsbk.app.nearby.api.PersonalListener.GetPersonalMedal;
import qsbk.app.nearby.api.PersonalListener.GetPersonalQiushi;
import qsbk.app.nearby.api.PersonalListener.GetPersonalTopic;
import qsbk.app.nearby.api.PersonalListener.GetQiushiTopic;
import qsbk.app.nearby.api.ScrollViewListener;
import qsbk.app.nearby.ui.InfoCompleteActivity;
import qsbk.app.utils.DateUtil;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.GroupNotifier.OnNotifyListener;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.NumberUtils;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseGroupDialog;
import qsbk.app.widget.BlackReportDialog;
import qsbk.app.widget.QiushiEmotionTextView;
import qsbk.app.widget.QiushiTopicImageSpan;

public class MyInfoActivity extends BasePersonalCenterActivity implements LocationCallBack, GetPersonalDynamic, GetPersonalGroup, GetPersonalInfo, GetPersonalLive, GetPersonalMedal, GetPersonalQiushi, GetPersonalTopic, GetQiushiTopic, ScrollViewListener, OnNotifyListener {
    public static final String CHANGE_REMARK = "_change_remark_";
    public static String[] FANS_ORIGINS = new String[]{"_FROM_QIUSHI", "_FROM_QIUYOUCIRCLE", "_FROM_NEARBY", "_FROM_GROUP", "_FROM_SEARCH", "_FORM_OTHER"};
    public static final String PHOTO_DIR_1 = (Environment.getExternalStorageDirectory() + "/qsbk.app/photo");
    private static final String bB = MyInfoActivity.class.getSimpleName();
    protected String bA;
    private boolean bC = false;
    private Relationship bD;
    private int bE;
    private UserInfo bF;
    private int bG;
    private Timer bH;
    private Handler bI;
    private boolean bJ = true;
    private final Runnable bK = new um(this);
    private final Runnable bL = new ux(this);
    private long bM = SystemClock.elapsedRealtime();
    private int bN;
    private Vibrator bO;
    private int bP;
    private int bQ;
    private boolean bR = false;
    private int bS = -10000;
    private LocalBroadcastManager bT;
    private a bU;
    private boolean bV;
    private RemarkManager bW;
    private String bX = "";
    private String bY;
    private boolean bZ = false;
    protected String bz;
    private ProgressDialog ca = null;
    private final Runnable cb = new vm(this);
    private int cc;
    private int cd;

    private class a extends BroadcastReceiver {
        final /* synthetic */ MyInfoActivity a;

        private a(MyInfoActivity myInfoActivity) {
            this.a = myInfoActivity;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.bR = true;
        }
    }

    public static void launch(Context context, String str, String str2, String str3, String str4, IMChatMsgSource iMChatMsgSource) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("gid", str2);
        intent.putExtra("groupName", str3);
        intent.putExtra("from", str4);
        intent.putExtra("come_from", iMChatMsgSource.encodeToJsonObject().toString());
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, String str2, String str3, String str4) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("gid", str2);
        intent.putExtra("groupName", str3);
        intent.putExtra("from", str4);
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, String str2, IMChatMsgSource iMChatMsgSource) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("from", str2);
        intent.putExtra("come_from", iMChatMsgSource.encodeToJsonObject().toString());
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, String str2, IMChatMsgSource iMChatMsgSource, boolean z) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("from", str2);
        intent.putExtra("come_from", iMChatMsgSource.encodeToJsonObject().toString());
        intent.putExtra("isFromQiuyouFragment", z);
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, String str2) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("from", str2);
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("userId", str);
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("isNoLogin", QsbkApp.currentUser == null);
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, boolean z) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        intent.putExtra("isNoLogin", z);
        intent.putExtra("userId", str);
        context.startActivity(intent);
    }

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night.Transparent);
        } else {
            setTheme(R.style.Day.Transparent);
        }
    }

    protected void onCreate(Bundle bundle) {
        int i = -15263461;
        a();
        requestWindowFeature(1);
        if (b()) {
            getWindow().addFlags(67108864);
        }
        setContentView(R.layout.activity_myinfo);
        super.onCreate(bundle);
        if (b()) {
            this.m.setPadding(0, BasePersonalCenterActivity.getStatusBarHeight(getApplicationContext()), 0, 0);
            this.bv.getLayoutParams().height = (int) ((240.0f * getDensity()) + ((float) BasePersonalCenterActivity.getStatusBarHeight(getApplicationContext())));
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (70.0f * getDensity()), (int) (70.0f * getDensity()));
            layoutParams.setMargins(0, (int) ((60.0f * getDensity()) + ((float) BasePersonalCenterActivity.getStatusBarHeight(getApplicationContext()))), 0, 0);
            layoutParams.gravity = 1;
            this.q.setLayoutParams(layoutParams);
        }
        this.b = getIntent().getStringExtra("userId");
        this.bz = getIntent().getStringExtra("gid");
        this.bA = getIntent().getStringExtra("groupName");
        this.bY = getIntent().getStringExtra("come_from");
        this.bZ = getIntent().getBooleanExtra("isFromQiuyouFragment", false);
        this.bV = getIntent().getBooleanExtra("isNoLogin", false);
        if (!TextUtils.isEmpty(this.b) && this.bV) {
            this.bC = false;
        } else if (TextUtils.isEmpty(this.b) && !this.bV) {
            this.b = QsbkApp.currentUser.userId;
            this.bC = true;
        } else if (this.b.equals(QsbkApp.currentUser.userId)) {
            this.bC = true;
        }
        this.bt = LocalBroadcastManager.getInstance(this);
        this.bI = new Handler();
        this.bO = (Vibrator) getSystemService("vibrator");
        if (this.bC || this.bV) {
            if (this.bV) {
                this.p.setVisibility(8);
                this.o.setVisibility(8);
            }
            if (this.bC) {
                this.p.setVisibility(0);
                this.o.setVisibility(8);
                this.p.setOnClickListener(new vx(this));
            }
        } else {
            this.p.setVisibility(8);
            this.o.setVisibility(0);
            this.o.setOnClickListener(new wi(this));
        }
        this.n.setOnClickListener(new wv(this));
        this.bs = new PersonalListener(this.b, this, this.bV);
        this.bs.setOnListener(this, this, this, this, this, this, this, this);
        startLocate();
        this.h.setOnClickListener(new xa(this));
        this.h.setOnTouchListener(new xf(this));
        this.j.setOnClickListener(new xi(this));
        this.w.setOnClickListener(new un(this));
        this.x.setOnClickListener(new LoginPermissionClickDelegate(new uo(this), null));
        this.x.getViewTreeObserver().addOnPreDrawListener(new up(this));
        this.z.setOnClickListener(new LoginPermissionClickDelegate(new uq(this), null));
        this.y.setOnClickListener(new LoginPermissionClickDelegate(new ur(this), null));
        this.k.setBackgroundColor(UIHelper.isNightTheme() ? -14802909 : -1184272);
        this.A.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1);
        this.j.setTextColor(UIHelper.isNightTheme() ? -4486889 : -17899);
        this.j.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.note_small_night : R.drawable.note_small), null, null, null);
        this.i.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1447447);
        this.j.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1);
        this.e.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1184275);
        LinearLayout linearLayout = this.g;
        if (!UIHelper.isNightTheme()) {
            i = -1;
        }
        linearLayout.setBackgroundColor(i);
        this.M.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
        this.S.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
        this.am.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next), null);
        this.bT = LocalBroadcastManager.getInstance(this);
        this.bU = new a();
        this.bT.registerReceiver(this.bU, new IntentFilter(InfoCompleteActivity.ACTION_CHANGE_USERINFO));
        GroupNotifier.register(this);
        e();
        this.bW = RemarkManager.getRemarkManager();
    }

    public void onGroupNotify(int i, int i2) {
        if (i2 == 3) {
            this.bs.startGetPersonalGroup();
        }
    }

    protected void onDestroy() {
        this.bT.unregisterReceiver(this.bU);
        GroupNotifier.unregister(this);
        super.onDestroy();
    }

    public void popUpMyOverflow(View view) {
        int height = this.o.getHeight() + ((int) (15.0f * getDensity()));
        int width = this.o.getWidth() - ((int) (5.0f * getDensity()));
        View inflate = getLayoutInflater().inflate(R.layout.personal_over_flow, null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(16973826);
        popupWindow.showAtLocation(view, 53, width, height);
        TextView textView = (TextView) inflate.findViewById(R.id.personal_report_black);
        TextView textView2 = (TextView) inflate.findViewById(R.id.personal_black);
        TextView textView3 = (TextView) inflate.findViewById(R.id.personal_remove_fans);
        View findViewById = inflate.findViewById(R.id.personal_black_remove_view);
        TextView textView4 = (TextView) inflate.findViewById(R.id.personal_admin_forbid);
        View findViewById2 = inflate.findViewById(R.id.personal_admin_remove_view);
        TextView textView5 = (TextView) inflate.findViewById(R.id.personal_set_remark);
        inflate = inflate.findViewById(R.id.personal_set_remark_view);
        if ((QsbkApp.currentUser == null || !TextUtils.equals("admin", QsbkApp.currentUser.adminRole)) && (QsbkApp.currentUser == null || !"1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
            findViewById2.setVisibility(8);
            textView4.setVisibility(8);
        } else {
            textView4.setVisibility(0);
            findViewById2.setVisibility(0);
        }
        if (Relationship.BLACK == this.bD) {
            findViewById.setVisibility(8);
            textView3.setVisibility(8);
            textView3.setText("移除黑名单");
            textView3.setOnClickListener(new us(this, popupWindow));
        } else if (Relationship.FAN == this.bD || Relationship.FRIEND == this.bD) {
            findViewById.setVisibility(0);
            textView3.setVisibility(0);
            textView3.setText("移除粉丝");
            textView3.setOnClickListener(new ut(this, popupWindow));
        } else {
            findViewById.setVisibility(8);
            textView3.setVisibility(8);
        }
        if (Relationship.FAN == this.bD || Relationship.FRIEND == this.bD || Relationship.FOLLOW_REPLIED == this.bD || Relationship.FOLLOW_UNREPLIED == this.bD) {
            textView5.setVisibility(0);
            inflate.setVisibility(0);
        } else {
            textView5.setVisibility(8);
            inflate.setVisibility(8);
        }
        textView5.setOnClickListener(new uu(this, popupWindow));
        textView2.setOnClickListener(new uv(this, popupWindow));
        textView.setOnClickListener(new uw(this, popupWindow));
        textView4.setOnClickListener(new uy(this, popupWindow));
    }

    public void showResetDialog(Context context) {
        BaseGroupDialog uzVar = new uz(this, this, RemarkManager.getRemark(this.b), context);
        uzVar.show();
        uzVar.setOnDismissListener(new ve(this));
    }

    private void d() {
        CharSequence remark = RemarkManager.getRemark(this.b);
        if (TextUtils.isEmpty(remark)) {
            this.bw.setVisibility(8);
            this.r.setText(this.bF.userName);
            return;
        }
        this.bw.setVisibility(0);
        this.bw.setText("昵称: " + this.bF.userName);
        this.r.setText(remark);
    }

    private void a(String str) {
        Intent intent = new Intent(CHANGE_REMARK);
        intent.putExtra("userId", str);
        this.bt.sendBroadcast(intent);
    }

    public void blackPersonal(int i) {
        if (this.bF != null) {
            BlackReportDialog blackReportDialog;
            if (i == 1) {
                blackReportDialog = new BlackReportDialog(this, this.bF.userId);
                blackReportDialog.registerListener(new vf(this, blackReportDialog));
                blackReportDialog.showBlackConfirmDialog();
                blackReportDialog.show();
            } else if (i == 2) {
                blackReportDialog = new BlackReportDialog(this, this.bF.userId);
                blackReportDialog.registerListener(new vg(this, blackReportDialog));
                blackReportDialog.showReportDialog();
                blackReportDialog.show();
            } else if (i == 3) {
                blackReportDialog = new BlackReportDialog(this, this.bF.userId);
                blackReportDialog.registerListener(new vh(this, blackReportDialog));
                blackReportDialog.showRemoveFansDialog();
                blackReportDialog.show();
            } else if (i == 4) {
                r0 = "";
                r0 = "";
                r0 = "";
                r2 = "再想想";
                a("是否确定移出黑名单?", r2, "移出黑名单", new vj(this), new vi(this, "正在移出黑名单,请稍后..."));
            } else if (i == 5) {
                r0 = "";
                r0 = "";
                r0 = "";
                r0 = "正在删除并封禁用户,请稍后..";
                r2 = "取消";
                a("是否删除并封禁该用户?", r2, "删除并封禁", new vl(this), new vk(this));
            }
        }
    }

    public void forbidUser(String str, String str2) {
        if (this.ca != null) {
            this.ca.cancel();
        }
        this.ca = ProgressDialog.show(this, null, str2, true, true);
        this.ca.setCancelable(true);
        this.ca.setCanceledOnTouchOutside(true);
        SimpleHttpTask voVar = new vo(this, str, new vn(this));
        voVar.setMapParams(new HashMap());
        this.ca.setOnCancelListener(new vp(this, voVar));
        voVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onResume() {
        super.onResume();
        this.k.setScrollViewListener(this);
        StatService.onPageStart(this, bB);
        if (this.bR && this.bS != -1) {
            updateUserInfo(QsbkApp.currentUser);
            this.bR = false;
        }
        this.bS = -10000;
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, bB);
    }

    private void e() {
        if (!this.bC || QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.bg) || TextUtils.isEmpty(QsbkApp.currentUser.emotion)) {
            this.bs.startGetPersonalInfo();
        } else {
            updateUserInfo(QsbkApp.currentUser);
            this.bs.startGetPersonalInfo();
        }
        this.bs.startGetPersonalGroup();
        this.bs.startGetPersonalQiushi();
        this.bs.startGetPersonalTopic();
        this.bs.startGetPersonalLive();
        this.bs.startGetPersonaMedal();
        this.bs.startGetQiushiTopic();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            e();
            this.bS = i2;
        }
        if (i == 2 && i2 == -1) {
            this.bS = i2;
            if (intent != null) {
                updateUserInfo((UserInfo) intent.getSerializableExtra("user_info"));
            }
        }
        if (i == BasePersonalCenterActivity.LOGIN_REQUEST_CODE && i2 == -1) {
            this.bV = false;
            if (this.b.equals(QsbkApp.currentUser.userId)) {
                this.bC = true;
                this.p.setVisibility(0);
                this.p.setOnClickListener(new vq(this));
            } else {
                this.bC = false;
                this.p.setVisibility(8);
                this.o.setVisibility(0);
                this.o.setOnClickListener(new vr(this));
            }
            if (this.bs == null) {
                this.bs = new PersonalListener(this.b, this, this.bV);
                this.bs.setOnListener(this, this, this, this, this, this, this);
                startLocate();
                e();
                return;
            }
            this.bs.setNoLogin(false);
            if (!this.bC || QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.bg) || TextUtils.isEmpty(QsbkApp.currentUser.emotion)) {
                this.bs.startGetPersonalInfo();
                return;
            }
            updateUserInfo(QsbkApp.currentUser);
            this.bs.startGetPersonalInfo();
        }
    }

    public void updateUserInfo(UserInfo userInfo) {
        int i = -10592144;
        int i2 = -12171438;
        if (this.bC) {
            TextView textView;
            int i3;
            this.c.setVisibility(8);
            this.bF = userInfo;
            if (!TextUtils.isEmpty(userInfo.bg)) {
                this.bQ = Integer.parseInt(userInfo.bg);
                this.bP = this.bQ;
            }
            setPersonalBgImage(userInfo.bg);
            this.l.setOnClickListener(new vs(this));
            a(userInfo.userId, userInfo.userIcon, this.q);
            this.q.setOnClickListener(new vt(this, userInfo));
            CharSequence remark = RemarkManager.getRemark(this.b);
            if (TextUtils.isEmpty(remark)) {
                this.bw.setVisibility(8);
                this.r.setText(userInfo.userName);
            } else {
                this.bw.setVisibility(0);
                this.bw.setText("昵称: " + userInfo.userName);
                this.r.setText(remark);
            }
            if (userInfo.age <= 0) {
                this.s.setVisibility(8);
            } else {
                this.s.setVisibility(0);
                this.u.setText(userInfo.age + "");
            }
            if (userInfo.age > 0) {
                if (userInfo.gender.equals("M")) {
                    this.s.setVisibility(0);
                    this.s.setBackgroundResource(R.drawable.pinfo_man_bg);
                    this.t.setImageResource(R.drawable.pinfo_male);
                } else if (userInfo.gender.equals("F")) {
                    this.s.setVisibility(0);
                    this.s.setBackgroundResource(R.drawable.pinfo_female_bg);
                    this.t.setImageResource(R.drawable.pinfo_female);
                } else {
                    this.s.setVisibility(8);
                    this.t.setVisibility(8);
                    this.u.setVisibility(8);
                }
            }
            if (TextUtils.equals(userInfo.userId, ContactListAdapter.BAOJIE_ID)) {
                this.by.setVisibility(0);
            } else {
                this.by.setVisibility(8);
            }
            remark = "";
            if (!TextUtils.isEmpty(userInfo.astrology)) {
                remark = remark + userInfo.astrology;
            }
            if (!(TextUtils.isEmpty(userInfo.emotion) || "secret".equals(userInfo.emotion))) {
                String str = "";
                if ("single".equals(userInfo.emotion)) {
                    str = "单身";
                } else if ("married".equals(userInfo.emotion)) {
                    str = "已婚";
                } else if ("secret".equals(userInfo.emotion)) {
                    str = "保密";
                } else if ("inlove".equals(userInfo.emotion)) {
                    str = "恋爱中";
                }
                if (TextUtils.isEmpty(remark)) {
                    remark = remark + str;
                } else {
                    remark = remark + " | " + str;
                }
            }
            if (!TextUtils.isEmpty(userInfo.haunt)) {
                if (TextUtils.isEmpty(remark)) {
                    remark = remark + userInfo.haunt;
                } else {
                    remark = remark + " | " + userInfo.haunt;
                }
            }
            if (TextUtils.isEmpty(remark)) {
                this.v.setVisibility(8);
            } else {
                this.v.setText(remark);
            }
            if (TextUtils.isEmpty(userInfo.hometown)) {
                this.bd.setVisibility(8);
            } else {
                this.be.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
                this.bd.setVisibility(0);
                textView = this.bf;
                if (UIHelper.isNightTheme()) {
                    i3 = -10592144;
                } else {
                    i3 = -10987432;
                }
                textView.setTextColor(i3);
                this.bf.setText(userInfo.hometown);
                this.bg.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            }
            if (userInfo.qiubaiAge < 0) {
                this.bi.setVisibility(8);
            } else {
                this.bi.setVisibility(0);
                this.bh.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
                textView = this.bj;
                if (UIHelper.isNightTheme()) {
                    i3 = -10592144;
                } else {
                    i3 = -10987432;
                }
                textView.setTextColor(i3);
                this.bk.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
                if (userInfo.qiubaiAge >= 365) {
                    textView = this.bj;
                    StringBuilder stringBuilder = new StringBuilder();
                    int i4 = userInfo.qiubaiAge / 365;
                    if (userInfo.qiubaiAge % 365 > 200) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    textView.setText(stringBuilder.append(i3 + i4).append("年").toString());
                } else {
                    this.bj.setText((userInfo.qiubaiAge / 30 > 0 ? userInfo.qiubaiAge / 30 : 1) + "个月");
                }
            }
            if (TextUtils.isEmpty(userInfo.job)) {
                this.bm.setVisibility(8);
                return;
            }
            this.bm.setVisibility(0);
            TextView textView2 = this.bl;
            if (!UIHelper.isNightTheme()) {
                i2 = -7500403;
            }
            textView2.setTextColor(i2);
            textView2 = this.bn;
            if (!UIHelper.isNightTheme()) {
                i = -10987432;
            }
            textView2.setTextColor(i);
            this.bn.setText(userInfo.job);
        }
    }

    public void getPersonalInfoFailed() {
        this.bd.setVisibility(8);
    }

    public void getPersonalInfoSucc(UserInfo userInfo) {
        CharSequence remark;
        String str;
        TextView textView;
        int i;
        int i2 = -10592144;
        int i3 = -12171438;
        if (this.bC) {
            this.c.setVisibility(8);
        } else if (this.bV) {
            this.c.setVisibility(0);
            this.bF = userInfo;
            this.bD = Relationship.NO_REL;
            a(this.bD);
            setPersonalBgImage(userInfo.bg);
            a(userInfo.userId, userInfo.userIcon, this.q);
            this.q.setOnClickListener(new vu(this, userInfo));
            remark = RemarkManager.getRemark(this.b);
            if (TextUtils.isEmpty(remark)) {
                this.bw.setVisibility(8);
                this.r.setText(userInfo.userName);
            } else {
                this.bw.setVisibility(0);
                this.bw.setText("昵称: " + userInfo.userName);
                this.r.setText(remark);
            }
            if (userInfo.age <= 0) {
                this.s.setVisibility(8);
            } else {
                this.s.setVisibility(0);
                this.u.setText(userInfo.age + "");
            }
            if (userInfo.age > 0) {
                if (userInfo.gender.equals("M")) {
                    this.s.setVisibility(0);
                    this.s.setBackgroundResource(R.drawable.pinfo_man_bg);
                    this.t.setImageResource(R.drawable.pinfo_male);
                } else if (userInfo.gender.equals("F")) {
                    this.s.setVisibility(0);
                    this.s.setBackgroundResource(R.drawable.pinfo_female_bg);
                    this.t.setImageResource(R.drawable.pinfo_female);
                } else {
                    this.s.setVisibility(8);
                    this.t.setVisibility(8);
                    this.u.setVisibility(8);
                }
            }
            if (TextUtils.equals(userInfo.userId, ContactListAdapter.BAOJIE_ID)) {
                this.by.setVisibility(0);
            } else {
                this.by.setVisibility(8);
            }
            remark = "";
            if (!TextUtils.isEmpty(userInfo.astrology)) {
                remark = remark + userInfo.astrology;
            }
            if (!(TextUtils.isEmpty(userInfo.emotion) || "secret".equals(userInfo.emotion))) {
                str = "";
                if ("single".equals(userInfo.emotion)) {
                    str = "单身";
                } else if ("married".equals(userInfo.emotion)) {
                    str = "已婚";
                } else if ("secret".equals(userInfo.emotion)) {
                    str = "保密";
                } else if ("inlove".equals(userInfo.emotion)) {
                    str = "恋爱中";
                }
                if (TextUtils.isEmpty(remark)) {
                    remark = remark + str;
                } else {
                    remark = remark + " | " + str;
                }
            }
            if (!TextUtils.isEmpty(userInfo.haunt)) {
                if (TextUtils.isEmpty(remark)) {
                    remark = remark + userInfo.haunt;
                } else {
                    remark = remark + " | " + userInfo.haunt;
                }
            }
            if (TextUtils.isEmpty(remark)) {
                this.v.setVisibility(8);
            } else {
                this.v.setText(remark);
            }
            if (TextUtils.isEmpty(userInfo.hometown)) {
                this.bd.setVisibility(8);
            } else {
                this.be.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
                this.bd.setVisibility(0);
                textView = this.bf;
                if (UIHelper.isNightTheme()) {
                    i = -10592144;
                } else {
                    i = -10987432;
                }
                textView.setTextColor(i);
                this.bf.setText(userInfo.hometown);
                this.bg.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            }
            if (userInfo.qiubaiAge < 0) {
                this.bi.setVisibility(8);
            } else {
                this.bi.setVisibility(0);
                this.bh.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
                textView = this.bj;
                if (UIHelper.isNightTheme()) {
                    i = -10592144;
                } else {
                    i = -10987432;
                }
                textView.setTextColor(i);
                this.bk.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
                if (userInfo.qiubaiAge >= 365) {
                    textView = this.bj;
                    StringBuilder stringBuilder = new StringBuilder();
                    int i4 = userInfo.qiubaiAge / 365;
                    if (userInfo.qiubaiAge % 365 > 200) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    textView.setText(stringBuilder.append(i + i4).append("年").toString());
                } else {
                    this.bj.setText((userInfo.qiubaiAge / 30 > 0 ? userInfo.qiubaiAge / 30 : 1) + "个月");
                }
            }
            if (TextUtils.isEmpty(userInfo.job)) {
                this.bm.setVisibility(8);
                this.bk.setVisibility(8);
            } else {
                this.bm.setVisibility(0);
                this.bl.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
                textView = this.bn;
                if (UIHelper.isNightTheme()) {
                    i = -10592144;
                } else {
                    i = -10987432;
                }
                textView.setTextColor(i);
                this.bn.setText(userInfo.job);
            }
        } else {
            this.c.setVisibility(0);
            this.bD = userInfo.relationship;
            a(this.bD);
        }
        this.bF = userInfo;
        if (!TextUtils.isEmpty(userInfo.bg)) {
            this.bQ = Integer.parseInt(userInfo.bg);
            this.bP = this.bQ;
        }
        setPersonalBgImage(userInfo.bg);
        if (this.bC) {
            this.l.setOnClickListener(new vv(this));
        }
        a(userInfo.userId, userInfo.userIcon, this.q);
        this.q.setOnClickListener(new vw(this, userInfo));
        remark = RemarkManager.getRemark(this.b);
        if (TextUtils.isEmpty(remark)) {
            this.bw.setVisibility(8);
            this.r.setText(userInfo.userName);
        } else {
            this.bw.setVisibility(0);
            this.bw.setText("昵称: " + userInfo.userName);
            this.r.setText(remark);
        }
        if (userInfo.age <= 0) {
            this.s.setVisibility(8);
        } else {
            this.s.setVisibility(0);
            this.u.setText(userInfo.age + "");
        }
        if (userInfo.age > 0) {
            if (userInfo.gender.equals("M")) {
                this.s.setVisibility(0);
                this.s.setBackgroundResource(R.drawable.pinfo_man_bg);
                this.t.setImageResource(R.drawable.pinfo_male);
            } else if (userInfo.gender.equals("F")) {
                this.s.setVisibility(0);
                this.s.setBackgroundResource(R.drawable.pinfo_female_bg);
                this.t.setImageResource(R.drawable.pinfo_female);
            } else {
                this.s.setVisibility(8);
                this.t.setVisibility(8);
                this.u.setVisibility(8);
            }
        }
        if (TextUtils.equals(userInfo.userId, ContactListAdapter.BAOJIE_ID)) {
            this.by.setVisibility(0);
        } else {
            this.by.setVisibility(8);
        }
        remark = "";
        if (!TextUtils.isEmpty(userInfo.astrology)) {
            remark = remark + userInfo.astrology;
        }
        if (TextUtils.equals(userInfo.userId, ContactListAdapter.BAOJIE_ID)) {
            this.by.setVisibility(0);
        } else {
            this.by.setVisibility(8);
        }
        if (!(TextUtils.isEmpty(userInfo.emotion) || "secret".equals(userInfo.emotion))) {
            str = "";
            if ("single".equals(userInfo.emotion)) {
                str = "单身";
            } else if ("married".equals(userInfo.emotion)) {
                str = "已婚";
            } else if ("secret".equals(userInfo.emotion)) {
                str = "保密";
            } else if ("inlove".equals(userInfo.emotion)) {
                str = "恋爱中";
            }
            if (TextUtils.isEmpty(remark)) {
                remark = remark + str;
            } else {
                remark = remark + " | " + str;
            }
        }
        if (!TextUtils.isEmpty(userInfo.haunt)) {
            if (TextUtils.isEmpty(remark)) {
                remark = remark + userInfo.haunt;
            } else {
                remark = remark + " | " + userInfo.haunt;
            }
        }
        if (TextUtils.isEmpty(remark)) {
            this.v.setVisibility(8);
        } else {
            this.v.setText(remark);
        }
        if (TextUtils.isEmpty(userInfo.hometown)) {
            this.bd.setVisibility(8);
        } else {
            this.be.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
            this.bd.setVisibility(0);
            textView = this.bf;
            if (UIHelper.isNightTheme()) {
                i = -10592144;
            } else {
                i = -10987432;
            }
            textView.setTextColor(i);
            this.bf.setText(userInfo.hometown);
            this.bg.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        }
        if (userInfo.qiubaiAge < 0) {
            this.bi.setVisibility(8);
        } else {
            this.bi.setVisibility(0);
            this.bh.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
            textView = this.bj;
            if (UIHelper.isNightTheme()) {
                i = -10592144;
            } else {
                i = -10987432;
            }
            textView.setTextColor(i);
            this.bk.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            if (userInfo.qiubaiAge >= 365) {
                textView = this.bj;
                stringBuilder = new StringBuilder();
                i4 = userInfo.qiubaiAge / 365;
                if (userInfo.qiubaiAge % 365 > 200) {
                    i = 1;
                } else {
                    i = 0;
                }
                textView.setText(stringBuilder.append(i + i4).append("年").toString());
            } else {
                this.bj.setText((userInfo.qiubaiAge / 30 > 0 ? userInfo.qiubaiAge / 30 : 1) + "个月");
            }
        }
        if (TextUtils.isEmpty(userInfo.job)) {
            this.bm.setVisibility(8);
            this.bk.setVisibility(8);
            return;
        }
        this.bm.setVisibility(0);
        TextView textView2 = this.bl;
        if (!UIHelper.isNightTheme()) {
            i3 = -7500403;
        }
        textView2.setTextColor(i3);
        textView2 = this.bn;
        if (!UIHelper.isNightTheme()) {
            i2 = -10987432;
        }
        textView2.setTextColor(i2);
        this.bn.setText(userInfo.job);
    }

    public void setPersonalBgImage(String str) {
        if (TextUtils.isEmpty(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[0]));
            this.bE = 1;
        } else if ("1".equals(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[0]));
            this.bE = 1;
        } else if ("2".equals(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[1]));
            this.bE = 2;
        } else if ("3".equals(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[2]));
            this.bE = 3;
        } else if ("4".equals(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[3]));
            this.bE = 4;
        } else if ("5".equals(str)) {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[4]));
            this.bE = 5;
        } else {
            this.bv.setBackgroundDrawable(getResources().getDrawable(PERSONAL_BG_IMAGE[0]));
            this.bE = 1;
        }
    }

    public void getPersonalDynamicFailed() {
        this.B.setVisibility(8);
    }

    public void getPersonalDynamicSucc(CircleArticle circleArticle, int i, int i2, int i3) {
        int i4 = -12171438;
        if (i <= 0) {
            getPersonalDynamicFailed();
            showPersonalScoreSucc(i2, i3);
            return;
        }
        this.B.setVisibility(0);
        StringBuffer Get_DiffDate_Info_Release = DateUtil.Get_DiffDate_Info_Release(this, (long) circleArticle.createAt, 0);
        this.C.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        this.C.setText("动态");
        this.I.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
        this.I.setText(i + "");
        this.B.setOnClickListener(new vy(this));
        this.K.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
        TextView textView = this.L;
        if (!UIHelper.isNightTheme()) {
            i4 = -6052957;
        }
        textView.setTextColor(i4);
        if (circleArticle.type == 10) {
            this.J.setVisibility(0);
            FrescoImageloader.displayImage(this.J, circleArticle.video.picUrl, this.bx);
            this.K.setText(circleArticle.content);
            this.L.setText(circleArticle.distance + " | " + Get_DiffDate_Info_Release.toString());
        } else if (circleArticle.type == 0 || circleArticle.type == 2) {
            this.J.setVisibility(8);
            this.K.setPadding(0, 0, 0, 0);
            this.L.setPadding(0, 0, 0, 0);
            this.K.setText(circleArticle.content);
            this.L.setText(circleArticle.distance + " | " + Get_DiffDate_Info_Release.toString());
        } else if (circleArticle.isForward()) {
            this.J.setVisibility(8);
            this.K.setPadding(0, 0, 0, 0);
            this.L.setPadding(0, 0, 0, 0);
            if (TextUtils.isEmpty(circleArticle.content)) {
                this.K.setText("转发了动态");
            } else {
                this.K.setText(circleArticle.content);
            }
            this.L.setText(circleArticle.distance + " | " + Get_DiffDate_Info_Release.toString());
        } else if (circleArticle.isShare()) {
            this.J.setVisibility(8);
            this.K.setPadding(0, 0, 0, 0);
            this.L.setPadding(0, 0, 0, 0);
            if (TextUtils.isEmpty(circleArticle.content)) {
                this.K.setText("分享了动态");
            } else {
                this.K.setText(circleArticle.content);
            }
            this.L.setText(circleArticle.distance + " | " + Get_DiffDate_Info_Release.toString());
        } else if (circleArticle.picUrls != null && circleArticle.picUrls.size() > 0) {
            this.J.setVisibility(0);
            FrescoImageloader.displayImage(this.J, QsbkApp.absoluteUrlOfCircleWebpImage(((PicUrl) circleArticle.picUrls.get(0)).url, circleArticle.createAt), this.bx);
            this.K.setText(circleArticle.content);
            this.L.setText(circleArticle.distance + " | " + Get_DiffDate_Info_Release.toString());
        }
        this.N.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        getPersonalScoreSucc(i2, i3);
    }

    public void getPersonalLiveFailed() {
        this.aF.setVisibility(8);
        this.ap.setVisibility(8);
    }

    public void getPersonalLiveSucc(BaseUserInfo[] baseUserInfoArr, int i, int i2, int i3, FamilyInfo familyInfo) {
        int i4 = -15526890;
        int i5 = -12171438;
        if (i2 < 0) {
            getPersonalLiveFailed();
            return;
        }
        if (this.bC) {
            QsbkApp.currentUser.remixLevel = i2;
        }
        if (i < 1 || baseUserInfoArr == null) {
            TextView textView = this.aG;
            if (!UIHelper.isNightTheme()) {
                i5 = -7500403;
            }
            textView.setTextColor(i5);
            this.aH.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
            TextView textView2 = this.aH;
            StringBuilder append = new StringBuilder().append("等级  LV ");
            if (i2 <= 0) {
                i2 = 0;
            }
            textView2.setText(append.append(i2).toString());
            this.aI.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
            this.aI.setText("送出  " + NumberUtils.format1(i3));
            this.aK.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            if (this.bC) {
                this.aJ.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_help_night : R.drawable.group_info_help);
                this.aJ.setOnClickListener(new vz(this));
                this.aJ.setVisibility(0);
            }
            this.aF.setVisibility(0);
            if (familyInfo == null) {
                this.aL.setVisibility(8);
                return;
            }
            this.aL.setOnClickListener(new wa(this, familyInfo));
            this.aL.setVisibility(0);
            FrescoImageloader.displayImage(this.aM, familyInfo.getFamilyImg());
            this.aN.setText(familyInfo.getFamilyName());
            if (TextUtils.isEmpty(familyInfo.getFamilyName())) {
                this.aO.setVisibility(8);
                return;
            }
            this.aO.setVisibility(0);
            this.aO.setText(familyInfo.getFamilyBadge());
            return;
        }
        int i6;
        this.ar.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        this.at.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_gold_night : R.drawable.group_level_gold);
        this.au.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_silver_night : R.drawable.group_level_silver);
        this.av.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_levle_copper_night : R.drawable.group_level_copper);
        TextView textView3 = this.ax;
        if (UIHelper.isNightTheme()) {
            i6 = -12171438;
        } else {
            i6 = -6052957;
        }
        textView3.setTextColor(i6);
        textView3 = this.ay;
        if (UIHelper.isNightTheme()) {
            i6 = -12171438;
        } else {
            i6 = -6052957;
        }
        textView3.setTextColor(i6);
        this.ay.setText(NumberUtils.format1(i) + "  礼券");
        this.az.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
        this.aA.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        textView3 = this.aB;
        if (UIHelper.isNightTheme()) {
            i6 = -12171438;
        } else {
            i6 = -6052957;
        }
        textView3.setTextColor(i6);
        textView = this.aB;
        StringBuilder append2 = new StringBuilder().append("等级  LV ");
        if (i2 <= 0) {
            i2 = 0;
        }
        textView.setText(append2.append(i2).toString());
        textView = this.aC;
        if (!UIHelper.isNightTheme()) {
            i5 = -6052957;
        }
        textView.setTextColor(i5);
        this.aC.setText("送出  " + NumberUtils.format1(i3));
        this.aD.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_help_night : R.drawable.group_info_help);
        View view = this.aE;
        if (!UIHelper.isNightTheme()) {
            i4 = -1184275;
        }
        view.setBackgroundColor(i4);
        i5 = 0;
        while (i5 < baseUserInfoArr.length && i5 < this.as.length) {
            BaseUserInfo baseUserInfo = baseUserInfoArr[i5];
            FrescoImageloader.displayAvatar(this.as[i5], QsbkApp.absoluteUrlOfMediumUserIcon(baseUserInfo.userIcon, baseUserInfo.userId));
            this.as[i5].setOnClickListener(new wb(this, baseUserInfo));
            i5++;
        }
        for (i5 = baseUserInfoArr.length; i5 < this.as.length; i5++) {
            this.as[i5].setBackgroundResource(UIHelper.getDefaultAvatar());
        }
        this.aw.setOnClickListener(new wc(this, i));
        if (this.bC) {
            this.aD.setOnClickListener(new wd(this));
            this.aD.setVisibility(0);
        }
        this.ap.setVisibility(0);
        if (familyInfo == null) {
            this.aR.setVisibility(8);
            return;
        }
        this.aR.setOnClickListener(new we(this, familyInfo));
        this.aR.setVisibility(0);
        FrescoImageloader.displayImage(this.aS, familyInfo.getFamilyImg());
        this.aT.setText(familyInfo.getFamilyName());
        if (TextUtils.isEmpty(familyInfo.getFamilyName())) {
            this.aU.setVisibility(8);
            return;
        }
        this.aU.setVisibility(0);
        this.aU.setText(familyInfo.getFamilyBadge());
    }

    public void getPersonalGroupFailed() {
        this.ab.setVisibility(8);
    }

    public void getPersonalGroupSucc(ArrayList<GroupInfo> arrayList, int i) {
        if (arrayList.size() == 0 || i == 0) {
            getPersonalGroupFailed();
            return;
        }
        this.ab.setVisibility(0);
        this.aa.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        if (arrayList.size() == 1) {
            this.ac.setVisibility(8);
            this.ae.setVisibility(8);
        } else if (arrayList.size() > 1) {
            this.ac.setVisibility(0);
            this.ac.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
            this.ac.setText(i + "");
            this.ae.setVisibility(8);
        }
        int size = arrayList.size();
        this.ad.removeAllViews();
        float density = getDensity();
        for (int i2 = 0; i2 < size; i2++) {
            RoundingParams roundingParams;
            GroupInfo groupInfo = (GroupInfo) arrayList.get(i2);
            View linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (50.0f * density)));
            linearLayout.setOrientation(1);
            View linearLayout2 = new LinearLayout(this);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (48.0f * density)));
            linearLayout2.setOrientation(0);
            View simpleDraweeView = new SimpleDraweeView(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (32.0f * density), (int) (32.0f * density));
            layoutParams.gravity = 17;
            simpleDraweeView.setLayoutParams(layoutParams);
            simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
            RoundingParams roundingParams2 = ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).getRoundingParams();
            if (roundingParams2 == null) {
                roundingParams = new RoundingParams();
            } else {
                roundingParams = roundingParams2;
            }
            roundingParams.setRoundAsCircle(true);
            ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(roundingParams);
            FrescoImageloader.displayImage(simpleDraweeView, ((GroupInfo) arrayList.get(i2)).icon, this.bx);
            View textView = new TextView(this);
            layoutParams = new LinearLayout.LayoutParams((int) (140.0f * density), (int) (48.0f * density));
            layoutParams.setMargins((int) (5.0f * density), 0, 0, 0);
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(15.0f);
            textView.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
            textView.setSingleLine(true);
            textView.setEllipsize(TruncateAt.END);
            textView.setText(((GroupInfo) arrayList.get(i2)).name);
            textView.setGravity(16);
            linearLayout2.addView(simpleDraweeView);
            linearLayout2.addView(textView);
            textView = new View(this);
            textView.setLayoutParams(new LayoutParams(-1, (int) Math.round(0.5d * ((double) density))));
            textView.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            linearLayout.addView(linearLayout2);
            linearLayout.addView(textView);
            this.ad.addView(linearLayout);
            linearLayout.setOnClickListener(new wf(this, groupInfo));
        }
    }

    public float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public void getPersonalQiushiFailed() {
        this.O.setVisibility(8);
    }

    public void getPersonalQiushiSucc(ArrayList<Article> arrayList, int i, int i2, int i3, int i4, int i5) {
        showPersonalChosenSucc(i2, i3, i4, i5);
        if (arrayList == null || i == 0 || arrayList.size() == 0) {
            getPersonalQiushiFailed();
            return;
        }
        int size;
        this.O.setVisibility(0);
        this.P.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        this.P.setText("糗事");
        this.Q.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
        this.Q.setText(i + "");
        float density = getDensity();
        int size2 = arrayList.size();
        if (((double) density) <= 1.5d) {
            size = arrayList.size() >= 2 ? 2 : arrayList.size();
        } else {
            size = size2;
        }
        for (int i6 = 0; i6 < size; i6++) {
            Article article = (Article) arrayList.get(i6);
            View simpleDraweeView;
            LayoutParams layoutParams;
            if (article.isVideoArticle()) {
                simpleDraweeView = new SimpleDraweeView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (60.0f * density), (int) (60.0f * density));
                if (i6 == 0) {
                    layoutParams.setMargins(0, 0, 0, 0);
                } else {
                    layoutParams.setMargins((int) (14.0f * density), 0, 0, 0);
                }
                layoutParams.gravity = 17;
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(simpleDraweeView, article.absPicPath, this.bx);
                this.R.addView(simpleDraweeView);
            } else if (article.isImageArticle()) {
                String imageUrl;
                simpleDraweeView = new SimpleDraweeView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (60.0f * density), (int) (60.0f * density));
                layoutParams.gravity = 17;
                if (i6 == 0) {
                    layoutParams.setMargins(0, 0, 0, 0);
                } else {
                    layoutParams.setMargins((int) (14.0f * density), 0, 0, 0);
                }
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
                if (article.imageInfos.size() > 0) {
                    imageUrl = ((ArticleImage) article.imageInfos.get(0)).getImageUrl();
                } else {
                    imageUrl = QsbkApp.absoluteUrlOfMediumContentImage(article.id, article.image);
                }
                FrescoImageloader.displayImage(simpleDraweeView, imageUrl, this.bx);
                this.R.addView(simpleDraweeView);
            } else {
                View qiushiEmotionTextView = new QiushiEmotionTextView(this);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams((int) (60.0f * density), ((int) (60.0f * density)) + 1);
                if (i6 == 0) {
                    layoutParams2.setMargins(0, 0, 0, 0);
                } else {
                    layoutParams2.setMargins((int) (14.0f * density), 0, 0, 0);
                }
                qiushiEmotionTextView.setLayoutParams(layoutParams2);
                qiushiEmotionTextView.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.personal_center_textview_night : R.drawable.personal_center_textview);
                qiushiEmotionTextView.setTextColor(UIHelper.isNightTheme() ? -12171438 : -10000537);
                qiushiEmotionTextView.setTextSize(8.0f);
                qiushiEmotionTextView.setLines(5);
                int i7 = (int) (3.0f * density);
                qiushiEmotionTextView.setPadding(i7, i7, i7, i7);
                qiushiEmotionTextView.setEllipsize(TruncateAt.END);
                qiushiEmotionTextView.setGravity(17);
                if (article.qiushiTopic != null) {
                    CharSequence spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append("搜");
                    QiushiTopicImageSpan qiushiTopicImageSpan = new QiushiTopicImageSpan(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                    qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(this, 5.0f));
                    qiushiTopicImageSpan.setmPaint(qiushiEmotionTextView.getPaint());
                    spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                    Object obj = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.qiushiTopic.content;
                    spannableStringBuilder.append(obj);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, obj.length() + 1, 33);
                    spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.getContent());
                    qiushiEmotionTextView.setText(spannableStringBuilder);
                    qiushiEmotionTextView.setMovementMethod(LinkMovementMethod.getInstance());
                } else {
                    qiushiEmotionTextView.setText(article.content);
                }
                this.R.addView(qiushiEmotionTextView);
            }
        }
        this.T.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        if (this.bC) {
            this.O.setOnClickListener(new wg(this));
        } else {
            this.O.setOnClickListener(new wh(this));
        }
    }

    public void showPersonalChosenSucc(int i, int i2, int i3, int i4) {
        this.cc = i;
        this.cd = i3;
        this.w.setText(NumberUtils.format(i2));
        this.x.setText(NumberUtils.format(i3));
        this.y.setText(NumberUtils.format(i));
        this.z.setText(NumberUtils.format(i4));
    }

    private void a(int i) {
        Intent intent = new Intent(getApplicationContext(), InfoCompleteActivity.class);
        intent.putExtra(InfoCompleteActivity.ACTION_KEY_FROM, i);
        startActivityForResult(intent, i);
    }

    private void a(Relationship relationship) {
        int i = -3947581;
        int i2 = -4486889;
        TextView textView;
        TextView textView2;
        TextView textView3;
        switch (wz.a[relationship.ordinal()]) {
            case 1:
                this.h.setBackgroundColor(UIHelper.isNightTheme() ? -15263461 : -1);
                textView = this.h;
                if (UIHelper.isNightTheme()) {
                    i2 = -10657937;
                } else {
                    i2 = -3947581;
                }
                textView.setTextColor(i2);
                this.h.setText("互粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.mutual_fans_small_night : R.drawable.mutual_fans_small), null, null, null);
                this.j.setVisibility(0);
                this.i.setVisibility(0);
                return;
            case 2:
                textView2 = this.h;
                if (UIHelper.isNightTheme()) {
                    i = -10657937;
                }
                textView2.setTextColor(i);
                this.h.setText("已粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.hava_fans_small_night : R.drawable.hava_fans_small), null, null, null);
                this.j.setVisibility(0);
                this.i.setVisibility(0);
                return;
            case 3:
                textView2 = this.h;
                if (UIHelper.isNightTheme()) {
                    i = -10657937;
                }
                textView2.setTextColor(i);
                this.h.setText("已粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.hava_fans_small_night : R.drawable.hava_fans_small), null, null, null);
                if (this.bz != null) {
                    this.j.setVisibility(0);
                    this.i.setVisibility(0);
                    this.j.setText("临时小纸条");
                    return;
                }
                this.j.setVisibility(8);
                this.i.setVisibility(8);
                return;
            case 4:
                textView3 = this.h;
                if (!UIHelper.isNightTheme()) {
                    i2 = -17899;
                }
                textView3.setTextColor(i2);
                this.h.setText("回粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.fans_small_night : R.drawable.fans_small), null, null, null);
                this.j.setVisibility(0);
                this.i.setVisibility(0);
                return;
            case 5:
            case 6:
                textView3 = this.h;
                if (!UIHelper.isNightTheme()) {
                    i2 = -17899;
                }
                textView3.setTextColor(i2);
                this.h.setText("长按加粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.fans_small_night : R.drawable.fans_small), null, null, null);
                if (this.bz != null) {
                    this.j.setVisibility(0);
                    this.i.setVisibility(0);
                    this.j.setText("临时小纸条");
                    return;
                }
                this.j.setVisibility(8);
                this.i.setVisibility(8);
                return;
            case 7:
                this.j.setVisibility(8);
                this.i.setVisibility(8);
                this.h.setText("移除黑名单");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.forbid_night : R.drawable.forbid), null, null, null);
                textView = this.h;
                if (!UIHelper.isNightTheme()) {
                    i2 = -3947581;
                }
                textView.setTextColor(i2);
                return;
            default:
                textView3 = this.h;
                if (!UIHelper.isNightTheme()) {
                    i2 = -17899;
                }
                textView3.setTextColor(i2);
                this.h.setText("长按加粉");
                this.h.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.fans_small_night : R.drawable.fans_small), null, null, null);
                return;
        }
    }

    private void a(String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && onClickListener != null && onClickListener != null) {
            new Builder(this).setCancelable(true).setMessage(str).setPositiveButton(str2, onClickListener).setNegativeButton(str3, onClickListener2).create().show();
        }
    }

    private void a(int i, int i2) {
        String str = Constants.REL_FOLLOW;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str = String.format(str, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.b);
        hashMap.put("shake_time", Integer.valueOf(i));
        hashMap.put("shake_count", Integer.valueOf(i2));
        if (!TextUtils.isEmpty(this.bY)) {
            hashMap.put("come_from", this.bY);
        }
        a(Constants.REL_FOLLOW, str, hashMap, "正在加粉,请稍后...");
    }

    private void a(String str, String str2, Map<String, Object> map, String str3) {
        if (!isFinishing()) {
            if (this.ca != null) {
                this.ca.cancel();
                this.ca.dismiss();
            }
            this.ca = ProgressDialog.show(this, null, str3, true, true);
            this.ca.setCancelable(true);
            this.ca.setCanceledOnTouchOutside(true);
            SimpleHttpTask wmVar = new wm(this, str2, new wj(this));
            wmVar.setMapParams(map);
            this.ca.setOnCancelListener(new wn(this, wmVar));
            wmVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void a(Relationship relationship, String str) {
        Intent intent = new Intent("QIU_YOU_RELATION_CHANGED");
        intent.putExtra("userId", str);
        intent.putExtra(ConversationActivity.RELATIONSHIP, relationship);
        this.bt.sendBroadcast(intent);
    }

    public void getPersonalScoreSucc(int i, int i2) {
        int i3 = -12171438;
        if (i < 0) {
            getPersonalScoreFailed();
            return;
        }
        int i4;
        this.D.setVisibility(0);
        this.H.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        TextView textView = this.E;
        if (UIHelper.isNightTheme()) {
            i4 = -12171438;
        } else {
            i4 = -6052957;
        }
        textView.setTextColor(i4);
        TextView textView2 = this.F;
        if (!UIHelper.isNightTheme()) {
            i3 = -6052957;
        }
        textView2.setTextColor(i3);
        this.G.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_help_night : R.drawable.group_info_help);
        if (QsbkApp.currentUser != null) {
            QsbkApp.currentUser.circleLevel = i;
        }
        this.E.setText("圈等级  LV " + i);
        if (!this.bC) {
            this.F.setVisibility(8);
            this.G.setVisibility(8);
        } else if (i2 >= 0) {
            this.F.setText("积分  " + NumberUtils.format1(i2));
            this.F.setVisibility(0);
            this.G.setVisibility(0);
            this.G.setOnClickListener(new wo(this));
        } else {
            this.F.setVisibility(8);
            this.G.setVisibility(8);
        }
    }

    public void getPersonalScoreFailed() {
        this.D.setVisibility(8);
    }

    public void showPersonalScoreSucc(int i, int i2) {
        int i3 = -12171438;
        if (i < 0) {
            showPersonalScoreFailed();
            return;
        }
        this.ak.setVisibility(0);
        this.al.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        TextView textView = this.am;
        if (!UIHelper.isNightTheme()) {
            i3 = -6052957;
        }
        textView.setTextColor(i3);
        this.an.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
        this.ao.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
        this.an.setText(" LV " + i);
        if (!this.bC) {
            this.am.setVisibility(8);
        } else if (i2 >= 0) {
            this.am.setText("积分 " + i2);
            this.am.setVisibility(0);
            this.am.setOnClickListener(new wp(this));
        } else {
            this.am.setVisibility(8);
        }
    }

    public void showPersonalScoreFailed() {
        this.ak.setVisibility(8);
    }

    public void getPersonalTopicSucc(ArrayList<CircleTopic> arrayList, int i) {
        if (arrayList.size() == 0 || i == 0) {
            getPersonalTopicFailed();
            return;
        }
        this.ag.setVisibility(0);
        this.af.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        if (arrayList.size() == 1) {
            this.ah.setVisibility(8);
            this.aj.setVisibility(8);
        } else if (arrayList.size() > 1) {
            this.ah.setVisibility(0);
            this.ah.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
            this.ah.setText(i + "");
            this.aj.setVisibility(8);
        }
        int size = arrayList.size();
        this.ai.removeAllViews();
        float density = getDensity();
        for (int i2 = 0; i2 < size; i2++) {
            CircleTopic circleTopic = (CircleTopic) arrayList.get(i2);
            View linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (50.0f * density)));
            linearLayout.setOrientation(1);
            View linearLayout2 = new LinearLayout(this);
            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (48.0f * density)));
            linearLayout2.setOrientation(0);
            View simpleDraweeView = new SimpleDraweeView(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (32.0f * density), (int) (32.0f * density));
            layoutParams.gravity = 17;
            simpleDraweeView.setLayoutParams(layoutParams);
            simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
            if (TextUtils.isEmpty(circleTopic.icon.url)) {
                simpleDraweeView.setImageResource(this.bx);
            } else {
                FrescoImageloader.displayImage(simpleDraweeView, circleTopic.icon.url, this.bx);
            }
            View textView = new TextView(this);
            layoutParams = new LinearLayout.LayoutParams((int) (140.0f * density), (int) (48.0f * density));
            layoutParams.setMargins((int) (5.0f * density), 0, 0, 0);
            textView.setLayoutParams(layoutParams);
            textView.setTextSize(15.0f);
            textView.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
            textView.setSingleLine(true);
            textView.setEllipsize(TruncateAt.END);
            textView.setText(circleTopic.content);
            textView.setGravity(16);
            linearLayout2.addView(simpleDraweeView);
            linearLayout2.addView(textView);
            simpleDraweeView = new View(this);
            simpleDraweeView.setLayoutParams(new LayoutParams(-1, (int) Math.round(0.5d * ((double) density))));
            simpleDraweeView.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            linearLayout.addView(linearLayout2);
            linearLayout.addView(simpleDraweeView);
            this.ai.addView(linearLayout);
            linearLayout.setOnClickListener(new wq(this, circleTopic));
        }
    }

    public void getPersonalTopicFailed() {
        this.ag.setVisibility(8);
    }

    public void getPersonalMedalFailed() {
        this.aX.setVisibility(8);
    }

    public void getPersonalMedalSucc(Medal[] medalArr, int i) {
        if (i > 0) {
            this.aY.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
            this.aZ.setText(i > 0 ? i + "" : "0");
            this.aZ.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
            if (medalArr != null && medalArr.length > 0) {
                int i2 = 0;
                while (i2 < medalArr.length && i2 < this.ba.length) {
                    if (!TextUtils.isEmpty(medalArr[i2].icon)) {
                        this.ba[i2].setVisibility(0);
                        FrescoImageloader.displayImage(this.ba[i2], medalArr[i2].icon);
                    }
                    i2++;
                }
            }
            this.bb.setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
            this.bc.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
            this.aX.setOnClickListener(new wr(this));
            this.aX.setVisibility(0);
        }
    }

    private void f() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0, this.b);
            jSONObject.put("delsession", jSONArray);
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(new ChatMsg(201, jSONObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        this.k.removeScrollViewListener();
        if (this.bP != this.bQ && this.bC) {
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.PERSONA_CHANGE_BG, new ws(this));
            Map hashMap = new HashMap();
            hashMap.put("bg", String.valueOf(this.bP));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        super.onStop();
    }

    public void onLocateFail(int i) {
        this.bs.startGetPersonalDynamic(0.0d, 0.0d);
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        this.bs.startGetPersonalDynamic(d, d2);
    }

    public void onLocateDone() {
        PersonalListener personalListener = this.bs;
        LocationHelper locationHelper = this.bu;
        double longitude = LocationHelper.getLongitude();
        locationHelper = this.bu;
        personalListener.startGetPersonalDynamic(longitude, LocationHelper.getLatitude());
    }

    public void onLocateFailed() {
        this.bs.startGetPersonalDynamic(0.0d, 0.0d);
    }

    public void onScrollChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4) {
        BasePersonalCenterActivity.getStatusBarHeight(getApplicationContext());
        int viewHeight = getViewHeight(this.l);
        int viewHeight2 = getViewHeight(this.m) + i2;
        if (viewHeight2 >= viewHeight - 150) {
            this.m.setOnTouchListener(new wt(this));
        } else {
            this.m.setOnTouchListener(new wu(this));
        }
        if (viewHeight2 >= viewHeight) {
            this.m.setBackgroundColor(-17899);
        } else if (viewHeight2 >= viewHeight - 50 && viewHeight2 < viewHeight) {
            this.m.setBackgroundColor(-1056982507);
        } else if (viewHeight2 >= viewHeight - 75 && viewHeight2 < viewHeight - 50) {
            this.m.setBackgroundColor(-2130724331);
        } else if (viewHeight2 >= viewHeight - 100 && viewHeight2 < viewHeight - 75) {
            this.m.setBackgroundColor(1895807509);
        } else if (viewHeight2 >= viewHeight - 125 && viewHeight2 < viewHeight - 100) {
            this.m.setBackgroundColor(1090501141);
        } else if (viewHeight2 < viewHeight - 150 || viewHeight2 >= viewHeight - 125) {
            this.m.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
        } else {
            this.m.setBackgroundColor(553630229);
        }
    }

    public void getQiushiTopicSucc(List<QiushiTopic> list) {
        if (list == null || (list != null && list.size() == 0)) {
            getQiushiTopicFailed();
            return;
        }
        this.U.setVisibility(0);
        this.W.setTextColor(UIHelper.isNightTheme() ? -12171438 : -7500403);
        int size = list.size();
        if (size == 1) {
            this.X.setVisibility(8);
            this.Z.setVisibility(8);
        } else if (size > 1 && size <= 3) {
            this.X.setVisibility(0);
            this.X.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
            this.X.setText(size + "");
            this.Z.setVisibility(8);
        } else if (size > 3) {
            this.X.setVisibility(0);
            this.X.setTextColor(UIHelper.isNightTheme() ? -10203872 : -8538);
            this.X.setText(size + "");
            this.Z.setVisibility(0);
        }
        float density = getDensity();
        int i;
        QiushiTopic qiushiTopic;
        View linearLayout;
        View linearLayout2;
        View simpleDraweeView;
        LayoutParams layoutParams;
        View textView;
        if (size > 0 && size <= 3) {
            for (i = 0; i < size; i++) {
                qiushiTopic = (QiushiTopic) list.get(i);
                linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (50.0f * density)));
                linearLayout.setOrientation(1);
                linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (48.0f * density)));
                linearLayout2.setOrientation(0);
                simpleDraweeView = new SimpleDraweeView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (32.0f * density), (int) (32.0f * density));
                layoutParams.gravity = 17;
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(simpleDraweeView, qiushiTopic.icon, this.bx);
                textView = new TextView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (140.0f * density), (int) (48.0f * density));
                layoutParams.setMargins((int) (5.0f * density), 0, 0, 0);
                textView.setLayoutParams(layoutParams);
                textView.setTextSize(15.0f);
                textView.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
                textView.setSingleLine(true);
                textView.setEllipsize(TruncateAt.END);
                textView.setText(qiushiTopic.content);
                textView.setGravity(16);
                linearLayout2.addView(simpleDraweeView);
                linearLayout2.addView(textView);
                simpleDraweeView = new View(this);
                simpleDraweeView.setLayoutParams(new LayoutParams(-1, (int) Math.round(0.5d * ((double) density))));
                simpleDraweeView.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
                linearLayout.addView(linearLayout2);
                linearLayout.addView(simpleDraweeView);
                this.Y.addView(linearLayout);
                linearLayout.setOnClickListener(new ww(this, qiushiTopic));
            }
        } else if (list.size() >= 3) {
            for (i = 0; i < 3; i++) {
                qiushiTopic = (QiushiTopic) list.get(i);
                linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (50.0f * density)));
                linearLayout.setOrientation(1);
                linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (48.0f * density)));
                linearLayout2.setOrientation(0);
                simpleDraweeView = new SimpleDraweeView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (32.0f * density), (int) (32.0f * density));
                layoutParams.gravity = 17;
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(simpleDraweeView, qiushiTopic.icon, this.bx);
                textView = new TextView(this);
                layoutParams = new LinearLayout.LayoutParams((int) (140.0f * density), (int) (48.0f * density));
                layoutParams.setMargins((int) (5.0f * density), 0, 0, 0);
                textView.setLayoutParams(layoutParams);
                textView.setTextSize(15.0f);
                textView.setTextColor(UIHelper.isNightTheme() ? -10592144 : -10987432);
                textView.setSingleLine(true);
                textView.setEllipsize(TruncateAt.END);
                textView.setText(qiushiTopic.content);
                textView.setGravity(16);
                linearLayout2.addView(simpleDraweeView);
                linearLayout2.addView(textView);
                simpleDraweeView = new View(this);
                simpleDraweeView.setLayoutParams(new LayoutParams(-1, (int) Math.round(0.5d * ((double) density))));
                simpleDraweeView.setBackgroundColor(UIHelper.isNightTheme() ? -15526890 : -1184275);
                linearLayout.addView(linearLayout2);
                linearLayout.addView(simpleDraweeView);
                this.Y.addView(linearLayout);
                linearLayout.setOnClickListener(new wx(this, qiushiTopic));
            }
            View linearLayout3 = new LinearLayout(this);
            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (50.0f * density)));
            linearLayout3.setOrientation(0);
            View simpleDraweeView2 = new SimpleDraweeView(this);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams((int) (32.0f * density), (int) (32.0f * density));
            layoutParams2.gravity = 17;
            simpleDraweeView2.setLayoutParams(layoutParams2);
            simpleDraweeView2.setScaleType(ScaleType.CENTER);
            simpleDraweeView2.setImageResource(UIHelper.isNightTheme() ? R.drawable.qiushi_topic_night : R.drawable.qiushi_topic_day);
            linearLayout3.addView(simpleDraweeView2);
            int i2 = 3;
            while (true) {
                int i3;
                if (size >= 6) {
                    i3 = 6;
                } else {
                    i3 = size;
                }
                if (i2 >= i3) {
                    break;
                }
                qiushiTopic = (QiushiTopic) list.get(i2);
                linearLayout = new SimpleDraweeView(this);
                LayoutParams layoutParams3 = new LinearLayout.LayoutParams((int) (24.0f * density), (int) (24.0f * density));
                layoutParams3.gravity = 17;
                layoutParams3.setMargins((int) (5.0f * density), 0, 0, 0);
                linearLayout.setLayoutParams(layoutParams3);
                linearLayout.setScaleType(ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(linearLayout, qiushiTopic.icon, this.bx);
                linearLayout3.addView(linearLayout);
                i2++;
            }
            if (size >= 4) {
                View relativeLayout = new RelativeLayout(this);
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                simpleDraweeView2 = new ImageView(this);
                LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams4.addRule(15);
                layoutParams4.addRule(11);
                layoutParams4.setMargins(0, 0, (int) (density * 18.0f), 0);
                simpleDraweeView2.setLayoutParams(layoutParams4);
                simpleDraweeView2.setImageResource(R.drawable.group_info_next);
                relativeLayout.addView(simpleDraweeView2);
                linearLayout3.addView(relativeLayout);
            }
            linearLayout3.setOnClickListener(new wy(this));
            this.Y.addView(linearLayout3);
        }
    }

    public void getQiushiTopicFailed() {
        this.U.setVisibility(8);
    }
}
