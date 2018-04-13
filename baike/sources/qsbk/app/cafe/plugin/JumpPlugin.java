package qsbk.app.cafe.plugin;

import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.SingleArticle;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.IMChatBaseActivity;
import qsbk.app.im.IMChatMsgSource;

public class JumpPlugin extends Plugin {
    public static final String ACTION_OPEN_LIVE = "open_live";
    public static final String ID = "id";
    public static final String LINK = "link";
    public static final String MODEL = "jump";
    public static final String TO_CIRCLE = "toCircle";
    public static final String TO_IM = "toIM";
    public static final String TO_LIVE = "toLive";
    public static final String TO_LIVE_CHARGE = "toLiveCharge";
    public static final String TO_PUBLISH = "toPublish";
    public static final String TO_QIUSHI = "toQiushi";
    public static final String TO_QIUSHI_ARTICLE = "qbArticleDetail";
    public static final String TO_USER_CENTER = "userCenter";
    private Callback a = null;

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        this.a = callback;
        this.b.setFocusPlugin(this);
        Intent intent = new Intent(this.b.getCurActivity(), MainActivity.class);
        if (TextUtils.equals(TO_PUBLISH, str)) {
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
            intent.putExtra(MainActivity.ACT_ACTION, str);
            this.b.getCurActivity().startActivity(intent);
        } else if (TextUtils.equals(TO_QIUSHI, str)) {
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUSHI_ID);
            this.b.getCurActivity().startActivity(intent);
        } else if (TextUtils.equals(TO_CIRCLE, str)) {
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_QIUYOUCIRCLE_ID);
            this.b.getCurActivity().startActivity(intent);
        } else if (TextUtils.equals(TO_LIVE, str)) {
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_LIVE_ID);
            this.b.getCurActivity().startActivity(intent);
        } else if (TextUtils.equals(TO_LIVE_CHARGE, str)) {
            intent.putExtra(MainActivity.SELECTED_TAB_ID, MainActivity.TAB_LIVE_ID);
            intent.putExtra(MainActivity.ACT_ACTION, str);
            this.b.getCurActivity().startActivity(intent);
        } else if (TextUtils.equals(TO_IM, str)) {
            if (QsbkApp.currentUser == null) {
                this.b.getCurActivity().startActivity(new Intent(this.b.getCurActivity(), ActionBarLoginActivity.class));
                return;
            }
            String str2;
            Intent intent2 = new Intent(this.b.getCurActivity(), ConversationActivity.class);
            CharSequence optString = jSONObject.optString("id");
            intent2.putExtra("to_id", optString);
            intent2.putExtra("user_id", QsbkApp.currentUser.userId);
            CharSequence optString2 = jSONObject.optString("icon");
            CharSequence optString3 = jSONObject.optString("name");
            if (TextUtils.equals("34248060", optString)) {
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = "2017082218261948.PNG";
                }
                if (TextUtils.isEmpty(optString3)) {
                    str2 = optString2;
                    r0 = "糗百客服菌";
                    if (TextUtils.isEmpty(optString) || !TextUtils.isEmpty(r0)) {
                        intent2.putExtra(IMChatBaseActivity.TO_ICON, str2);
                        intent2.putExtra(IMChatBaseActivity.TO_NICK, r0);
                        this.b.getCurActivity().startActivity(intent2);
                    }
                    return;
                }
            }
            CharSequence charSequence = optString3;
            optString3 = optString2;
            optString2 = charSequence;
            if (TextUtils.isEmpty(optString)) {
            }
            intent2.putExtra(IMChatBaseActivity.TO_ICON, str2);
            intent2.putExtra(IMChatBaseActivity.TO_NICK, r0);
            this.b.getCurActivity().startActivity(intent2);
        } else if (TextUtils.equals(TO_USER_CENTER, str)) {
            String string = jSONObject.getString("id");
            if (!TextUtils.isEmpty(string)) {
                MyInfoActivity.launch(getContext().getCurActivity(), string, MyInfoActivity.FANS_ORIGINS[0], new IMChatMsgSource(-1, string, "来自网页"));
            }
        } else if (TextUtils.equals(TO_QIUSHI_ARTICLE, str)) {
            r0 = jSONObject.getString("id");
            if (!TextUtils.isEmpty(r0)) {
                SingleArticle.launch(getContext().getCurActivity(), r0);
            }
        } else if (TextUtils.equals(ACTION_OPEN_LIVE, str)) {
            try {
                String string2 = jSONObject.getString("uid");
                long j = jSONObject.getLong("source");
                AppUtils.getInstance().getUserInfoProvider().toLive(getContext().getCurActivity(), string2, jSONObject.getString("platform_id"), j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDestroy() {
    }
}
