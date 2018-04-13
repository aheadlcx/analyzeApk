package qsbk.app.live.widget;

import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.ui.LiveBaseActivity;

class jj extends NetworkCallback {
    final /* synthetic */ User a;
    final /* synthetic */ FrameLayout b;
    final /* synthetic */ TextView c;
    final /* synthetic */ UserCardDialog d;

    jj(UserCardDialog userCardDialog, User user, FrameLayout frameLayout, TextView textView) {
        this.d = userCardDialog;
        this.a = user;
        this.b = frameLayout;
        this.c = textView;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("f_source", this.a.getOrigin() + "");
        hashMap.put("f_uid", this.a.getOriginId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.d.a instanceof LiveBaseActivity) {
            ((LiveBaseActivity) this.d.a).onFollowAnchorSuccess(this.a);
        }
        this.b.setOnClickListener(null);
    }

    public void onFailed(int i, String str) {
        this.a.is_follow = !this.a.is_follow;
        this.c.setText(R.string.user_follow);
        this.c.setCompoundDrawablesWithIntrinsicBounds(R.drawable.live_user_follow, 0, 0, 0);
        this.c.setTextColor(this.d.a.getResources().getColor(R.color.black_41364F));
        ToastUtil.Short(str);
    }
}
