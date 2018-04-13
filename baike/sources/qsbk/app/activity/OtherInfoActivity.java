package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.readystatesoftware.systembartint.SystemBarTintHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ChatMsg;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.UserChatManager;
import qsbk.app.model.UserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PersonalInfoView;

@Deprecated
public class OtherInfoActivity extends FragmentActivity {
    public static final String AVATAR = "avatar";
    public static final String NAME = "name";
    public static final String UID = "uid";
    private static final String a = OtherInfoActivity.class.getSimpleName();
    private LinearLayout b;
    private PersonalInfoView c;
    private TextView d;
    private ProgressBar e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private Relationship i;
    private String j;
    private UserInfo k;
    private View l;
    private ImageView m;
    private String n;
    private LocalBroadcastManager o;
    private boolean p = false;
    private boolean q = false;
    private ListPopupWindow r;

    protected void onCreate(Bundle bundle) {
        b();
        requestWindowFeature(1);
        super.onCreate(bundle);
        new SystemBarTintHelper(this).setView(R.layout.activity_otherinfo).enableSystembarTint(UIHelper.isNightTheme() ? Color.parseColor("#444f75") : Color.parseColor("#91a7ff"));
        this.j = getIntent().getStringExtra("uid");
        this.n = getIntent().getStringExtra("group_name");
        c();
        a(Constants.URL_USER_INFO, String.format(Constants.URL_USER_INFO, new Object[]{this.j}), null);
        a(Constants.IS_UNSUBSCRIBE_TA, String.format(Constants.IS_UNSUBSCRIBE_TA, new Object[]{this.j}), null);
        this.o = LocalBroadcastManager.getInstance(this);
    }

    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, a);
    }

    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, a);
    }

    private void b() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    private void c() {
        this.l = findViewById(R.id.layerMask);
        if (UIHelper.isNightTheme()) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
        this.d = (TextView) findViewById(R.id.title);
        this.d.setOnClickListener(new yl(this));
        this.e = (ProgressBar) findViewById(R.id.info_loading);
        this.c = (PersonalInfoView) findViewById(R.id.info);
        this.c.setBasicView(this.j, getIntent().getStringExtra("name"), getIntent().getStringExtra("avatar"));
        this.b = (LinearLayout) findViewById(R.id.rel_operate);
        this.b.setVisibility(8);
        this.f = (ImageView) findViewById(R.id.black_report);
        this.f.setOnClickListener(new ym(this));
        this.h = (ImageView) findViewById(R.id.relationship);
        this.h.setOnClickListener(new yo(this));
        this.g = (ImageView) findViewById(R.id.message);
        this.g.setOnClickListener(new yw(this));
        this.m = (ImageView) findViewById(R.id.overflow_button);
        this.m.setOnClickListener(new yx(this));
    }

    private boolean d() {
        if (this.n == null) {
            return false;
        }
        Intent intent = new Intent(this, ConversationActivity.class);
        intent.putExtra("user_id", QsbkApp.currentUser.userId);
        intent.putExtra("to_id", this.k.userId);
        intent.putExtra(IMChatBaseActivity.TO_ICON, this.k.userIcon);
        intent.putExtra(IMChatBaseActivity.TO_NICK, this.k.userName);
        intent.putExtra(ConversationActivity.RELATIONSHIP, this.k.relationship);
        intent.putExtra(ConversationActivity.TEMPORARY, true);
        intent.putExtra("source", new IMChatMsgSource(7, this.k.userId, this.n).encodeToJsonObject().toString());
        startActivity(intent);
        return true;
    }

    private void e() {
        List arrayList = new ArrayList();
        View findViewById = findViewById(R.id.overflow_button);
        this.r = new ListPopupWindow(this);
        this.r.setAnchorView(findViewById);
        this.r.setContentWidth((int) (getResources().getDisplayMetrics().density * 196.0f));
        this.r.setModal(true);
        arrayList.add(this.p ? "订阅TA的糗事" : "不订阅TA的糗事");
        this.r.setAdapter(new ArrayAdapter(this, R.layout.list_popup_window_item, arrayList));
        this.r.setOnItemClickListener(new yy(this));
    }

    private void a(int i, int i2) {
        String str = Constants.REL_FOLLOW;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        str = String.format(str, objArr);
        Map hashMap = new HashMap();
        hashMap.put("uid", this.j);
        hashMap.put("shake_time", Integer.valueOf(i));
        hashMap.put("shake_count", Integer.valueOf(i2));
        hashMap.put("come_from", getIntent().getStringExtra("source"));
        a(Constants.REL_FOLLOW, str, hashMap);
    }

    private void a(Relationship relationship) {
        switch (zd.a[relationship.ordinal()]) {
            case 1:
                this.h.setImageResource(R.drawable.rel_friend_selector);
                break;
            case 2:
                this.h.setImageResource(R.drawable.rel_fan_selector);
                break;
            case 3:
                this.g.setImageResource(R.drawable.rel_message_useless);
                this.h.setImageResource(R.drawable.rel_fan_selector);
                break;
            case 4:
                this.h.setImageResource(R.drawable.rel_cancel_black_selector);
                this.f.setVisibility(4);
                this.g.setVisibility(4);
                break;
            case 5:
            case 6:
                this.h.setImageResource(R.drawable.rel_fan_it_selector);
                break;
            case 7:
                this.f.setVisibility(0);
                this.g.setVisibility(0);
                this.g.setImageResource(R.drawable.rel_message_useless);
                this.h.setImageResource(R.drawable.rel_fan_it_selector);
                break;
        }
        if (this.n != null) {
            this.g.setVisibility(0);
            this.g.setImageResource(R.drawable.rel_message_selector);
        }
    }

    private void a(String str, String str2, Map<String, Object> map) {
        if (Constants.URL_USER_INFO.equalsIgnoreCase(str)) {
            this.e.setVisibility(0);
        }
        HttpTask httpTask = new HttpTask(str, str2, new zb(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }

    private void a(String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && onClickListener != null && onClickListener != null) {
            new Builder(this).setCancelable(true).setMessage(str).setPositiveButton(str2, onClickListener).setNegativeButton(str3, onClickListener2).create().show();
        }
    }

    private void f() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(0, this.j);
            jSONObject.put("delsession", jSONArray);
            UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).onMessageReceived(new ChatMsg(201, jSONObject.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
