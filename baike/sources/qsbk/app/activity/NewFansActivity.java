package qsbk.app.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.readystatesoftware.systembartint.SystemBarTintHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.NewFansAvatarAdapter;
import qsbk.app.adapter.PersonalInfoAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ContactListItem;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.NewFan;
import qsbk.app.model.UserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PersonalInfoView;

@Deprecated
public class NewFansActivity extends Activity {
    public static String NEWFANS_ID = "22584215";
    private static final String a = NewFansActivity.class.getSimpleName();
    public static boolean mFromPush = false;
    private ArrayList<NewFan> b = new ArrayList();
    private ArrayList<NewFan> c = new ArrayList();
    private TextView d;
    private TextView e;
    private Gallery f;
    private Gallery g;
    private NewFansAvatarAdapter h;
    private PersonalInfoAdapter i;
    private LinearLayout j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private PersonalInfoView n;
    private ProgressBar o;
    private View p;
    private int q = 1;
    private boolean r = false;
    private Handler s = new Handler();
    private Runnable t = null;
    private String u;
    private int v = 1;
    private boolean w = true;
    private int x = 0;
    private boolean y = false;
    private HttpTask z;

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        new SystemBarTintHelper(this).setView(R.layout.activity_new_fans).enableSystembarTint(UIHelper.isNightTheme() ? Color.parseColor("#444f75") : Color.parseColor("#91a7ff"));
        ((NotificationManager) getSystemService("notification")).cancel(100);
        a();
        c();
    }

    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, a);
        this.h.notifyDataSetChanged();
        this.i.notifyDataSetChanged();
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, a);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.z != null) {
            this.z.cancel(true);
            this.z = null;
        }
    }

    private void a() {
        this.p = findViewById(R.id.layerMask);
        if (UIHelper.isNightTheme()) {
            this.p.setVisibility(0);
        } else {
            this.p.setVisibility(8);
        }
        this.d = (TextView) findViewById(R.id.title);
        this.d.setText("我的新粉丝");
        this.d.setOnClickListener(new xj(this));
        this.e = (TextView) findViewById(R.id.source);
        this.e.setOnClickListener(new xm(this));
        this.f = (Gallery) findViewById(R.id.avatars);
        this.h = new NewFansAvatarAdapter(this.c);
        this.f.setAdapter(this.h);
        this.f.setOnItemSelectedListener(new xn(this));
        this.f.setFocusableInTouchMode(false);
        this.f.setFocusable(false);
        this.f.setOnTouchListener(new xo(this));
        this.g = (Gallery) findViewById(R.id.infos);
        this.i = new PersonalInfoAdapter(this, this.c);
        this.g.setAdapter(this.i);
        this.g.setUnselectedAlpha(100.0f);
        this.g.setOnItemSelectedListener(new xp(this));
        this.j = (LinearLayout) findViewById(R.id.bottom_view);
        this.j.setVisibility(8);
        this.k = (ImageView) findViewById(R.id.blace_report);
        this.k.setOnClickListener(new xr(this));
        this.l = (ImageView) findViewById(R.id.message);
        this.l.setOnClickListener(new xt(this));
        this.m = (ImageView) findViewById(R.id.know);
        this.m.setOnClickListener(new xu(this));
        this.n = (PersonalInfoView) findViewById(R.id.cover);
        this.o = (ProgressBar) findViewById(R.id.newfans_loading);
    }

    private void a(UserInfo userInfo) {
        ContactListItem contactListItem = new ContactListItem();
        contactListItem.id = userInfo.userId;
        contactListItem.name = userInfo.userName;
        contactListItem.icon = userInfo.userIcon;
        QsbkApp.getInstance();
        UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).sendTo(contactListItem, "看到你粉我啦，有空咱们聊聊~", null, false);
        Intent intent = new Intent(this, ConversationActivity.class);
        QsbkApp.getInstance();
        intent.putExtra("user_id", QsbkApp.currentUser.userId);
        intent.putExtra("to_id", userInfo.userId);
        intent.putExtra(IMChatBaseActivity.TO_ICON, userInfo.userIcon);
        intent.putExtra(IMChatBaseActivity.TO_NICK, userInfo.userName);
        intent.putExtra(ConversationActivity.RELATIONSHIP, Relationship.FAN);
        startActivity(intent);
    }

    private void b() {
        this.g.getSelectedView().setVisibility(4);
        this.n.setView((UserInfo) this.c.get(this.g.getSelectedItemPosition()), 1);
        this.n.setVisibility(0);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.n.getTop(), (float) (this.n.getTop() + this.n.getHeight()));
        translateAnimation.setDuration(500);
        this.n.startAnimation(translateAnimation);
        this.s.postDelayed(new xv(this), 500);
    }

    private void a(String str) {
        String str2 = Constants.REL_DELETE_NEW_FANS;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        HttpTask httpTask = new HttpTask("delete_new_fan", String.format(str2, objArr), new xk(this));
        Map hashMap = new HashMap();
        hashMap.put("uid", str);
        httpTask.setMapParams(hashMap);
        httpTask.execute(new Void[0]);
    }

    private void c() {
        if (this.w && !this.y) {
            this.y = true;
            if (1 == this.v) {
                this.o.setVisibility(0);
            }
            String str = Constants.REL_NEW_FAN_LIST;
            r1 = new Object[2];
            QsbkApp.getInstance();
            r1[0] = QsbkApp.currentUser.userId;
            r1[1] = Integer.valueOf(this.v);
            this.u = String.format(str, r1);
            this.z = new HttpTask("newfans", this.u, new xl(this));
            this.z.execute(new Void[0]);
        }
    }

    private void d() {
        LogUtil.d("mNewFans.size()" + this.c.size() + " mSelectionPosition:" + this.q + " hasMore:" + this.w);
        if (this.b.size() - this.q < 5 && this.w) {
            c();
        }
    }

    private void a(NewFan newFan) {
        if (newFan != null) {
            if (newFan.mSource == null) {
                this.e.setText("");
                return;
            }
            switch (newFan.mSource.type) {
                case 1:
                    CharSequence charSequence;
                    if (newFan.mSource.valueObj.distance >= 1000) {
                        charSequence = " 附近" + String.format("%.2f", new Object[]{Double.valueOf(((double) newFan.mSource.valueObj.distance) / 1000.0d)}) + "km";
                    } else {
                        charSequence = " 附近" + newFan.mSource.valueObj.distance + "m";
                    }
                    if (TextUtils.isEmpty(newFan.mSource.valueObj.haunt) || "null".equalsIgnoreCase(newFan.mSource.valueObj.haunt)) {
                        this.e.setText(charSequence);
                    } else {
                        this.e.setText("来自" + newFan.mSource.valueObj.haunt + charSequence);
                    }
                    this.e.setCompoundDrawables(null, null, null, null);
                    return;
                case 2:
                    this.e.setText("来自糗事#" + newFan.mSource.valueObj.artid);
                    this.e.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.source_arrow), null);
                    return;
                case 3:
                    this.e.setText("来自糗事#" + newFan.mSource.valueObj.artid + "的评论");
                    this.e.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.source_arrow), null);
                    return;
                case 4:
                    this.e.setText("来自里屋");
                    this.e.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.source_arrow), null);
                    return;
                case 5:
                    this.e.setText("来自昵称搜索");
                    this.e.setCompoundDrawables(null, null, null, null);
                    return;
                case 6:
                    this.e.setText("来自任意门");
                    this.e.setCompoundDrawables(null, null, null, null);
                    return;
                default:
                    return;
            }
        }
    }

    private void e() {
        int i = 0;
        if (1 != this.v) {
            int i2;
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.x == 0) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(0, NEWFANS_ID);
                    jSONObject.put("delsession", jSONArray);
                    i2 = 201;
                } else {
                    String str = "";
                    int i3 = this.x > 4 ? 4 : this.x;
                    String str2 = str;
                    while (i < i3) {
                        if (i > 0) {
                            str2 = str2 + "，";
                        }
                        str = str2 + ((NewFan) this.c.get(i)).userName;
                        i++;
                        str2 = str;
                    }
                    jSONObject.put("fan_count", 0);
                    jSONObject.put("fan_names", str2);
                    if (mFromPush) {
                        jSONObject.put("last_add_time", System.currentTimeMillis());
                        mFromPush = false;
                    } else {
                        jSONObject.put("last_add_time", 0);
                    }
                    i2 = 200;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                i2 = -1;
            }
            ChatMsg chatMsg = new ChatMsg(i2, jSONObject.toString());
            chatMsg.usertype = 2;
            chatMsg.from = NEWFANS_ID;
            chatMsg.fromnick = "我的新粉丝";
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(chatMsg);
        }
    }

    public void onBackPressed() {
        e();
        super.onBackPressed();
    }
}
