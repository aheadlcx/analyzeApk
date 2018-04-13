package cn.xiaochuankeji.tieba.ui.auth;

import android.content.Intent;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.xiaochuan.jsbridge.data.d;
import cn.xiaochuankeji.tieba.background.utils.monitor.a.b;
import cn.xiaochuankeji.tieba.push.proto.Push.Packet;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import java.util.HashMap;

class c {
    private static final c a = new c();
    private HashMap<String, Object> b = new HashMap();
    private String c;

    c() {
    }

    public static c a() {
        return a;
    }

    public void a(Intent intent) {
        String stringExtra = intent.getStringExtra("_refer");
        this.c = a(intent.getIntExtra("_src", 0));
        this.b.clear();
        this.b.put("ref", a(stringExtra));
        this.b.put(TimeDisplaySetting.START_SHOW_TIME, Long.valueOf(System.currentTimeMillis() / 1000));
    }

    private String a(String str) {
        return str;
    }

    private String a(int i) {
        switch (i) {
            case -14:
                return "cancel_dislike";
            case -12:
                return "cancel_like";
            case -11:
                return "cancel_favor";
            case -10:
                return "cancel_follow";
            case 1:
                return "create_post";
            case 2:
                return "create_danmaku";
            case 3:
                return "create_ugc";
            case 4:
                return "create_review";
            case 5:
                return "vote";
            case 6:
                return "open_chat";
            case 7:
                return "create_topic";
            case 8:
                return "create_sub_danmaku";
            case 9:
                return "create_tale";
            case 10:
                return "follow";
            case 11:
                return "favor";
            case 12:
                return "like";
            case 14:
                return "dislike";
            case 21:
                return "ban_user";
            case 22:
                return "ban_topic";
            case 31:
                return "view_vote";
            case 41:
                return "create_hollow";
            case 1001:
                return "my_post";
            case 1002:
                return "my_reviews";
            case 1003:
                return "my_favor";
            case 1004:
                return "my_like";
            case Packet.CLIENTVER_FIELD_NUMBER /*1005*/:
                return "my_history";
            case 1006:
                return "my_ugc";
            case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                return "my_tale";
            case PointerIconCompat.TYPE_TEXT /*1008*/:
                return "my_fans";
            case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                return "my_follow";
            case PointerIconCompat.TYPE_ALIAS /*1010*/:
                return "my_cover_upload";
            default:
                return "view_login";
        }
    }

    void b() {
        int intValue;
        if (this.b.containsKey("pwd_bc")) {
            intValue = ((Integer) this.b.get("pwd_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("pwd_bc", Integer.valueOf(intValue));
    }

    void c() {
        int intValue;
        if (this.b.containsKey("sms_bc")) {
            intValue = ((Integer) this.b.get("sms_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("sms_bc", Integer.valueOf(intValue));
    }

    void d() {
        int intValue;
        if (this.b.containsKey("sms_code_bc")) {
            intValue = ((Integer) this.b.get("sms_code_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("sms_code_bc", Integer.valueOf(intValue));
    }

    void e() {
        int intValue;
        if (this.b.containsKey("cant_login_bc")) {
            intValue = ((Integer) this.b.get("cant_login_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("cant_login_bc", Integer.valueOf(intValue));
    }

    void f() {
        int intValue;
        if (this.b.containsKey("forgot_pwd_bc")) {
            intValue = ((Integer) this.b.get("forgot_pwd_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("forgot_pwd_bc", Integer.valueOf(intValue));
    }

    void g() {
        int intValue;
        if (this.b.containsKey("find_account_bc")) {
            intValue = ((Integer) this.b.get("find_account_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("find_account_bc", Integer.valueOf(intValue));
    }

    void h() {
        int intValue;
        if (this.b.containsKey("qq_bc")) {
            intValue = ((Integer) this.b.get("qq_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("qq_bc", Integer.valueOf(intValue));
    }

    void i() {
        int intValue;
        if (this.b.containsKey("wx_bc")) {
            intValue = ((Integer) this.b.get("wx_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("wx_bc", Integer.valueOf(intValue));
    }

    void j() {
        int intValue;
        if (this.b.containsKey("sina_bc")) {
            intValue = ((Integer) this.b.get("sina_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("sina_bc", Integer.valueOf(intValue));
    }

    void k() {
        int intValue;
        if (this.b.containsKey("pwd_login_bc")) {
            intValue = ((Integer) this.b.get("pwd_login_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("pwd_login_bc", Integer.valueOf(intValue));
    }

    void l() {
        int intValue;
        if (this.b.containsKey("sms_login_bc")) {
            intValue = ((Integer) this.b.get("sms_login_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("sms_login_bc", Integer.valueOf(intValue));
    }

    void m() {
        int intValue;
        if (this.b.containsKey("region_bc")) {
            intValue = ((Integer) this.b.get("region_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("region_bc", Integer.valueOf(intValue));
    }

    public void n() {
        int intValue;
        if (this.b.containsKey("register_bc")) {
            intValue = ((Integer) this.b.get("register_bc")).intValue() + 1;
        } else {
            intValue = 1;
        }
        this.b.put("register_bc", Integer.valueOf(intValue));
    }

    public void a(boolean z, String str) {
        if (z) {
            this.b.put("is_register", Integer.valueOf(1));
        }
        this.b.put("login_way", str);
    }

    public void o() {
        if (p()) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            if (this.b.containsKey("ct")) {
                try {
                    this.b.put("duration", Long.valueOf(currentTimeMillis - ((Long) this.b.get("ct")).longValue()));
                } catch (Exception e) {
                }
            }
            this.b.put("et", Long.valueOf(currentTimeMillis));
            b.a().a("view", d.a, 0, 0, this.c, this.b);
            q();
        }
    }

    private boolean p() {
        return !TextUtils.isEmpty(this.c);
    }

    private void q() {
        this.b.clear();
        this.c = null;
    }
}
