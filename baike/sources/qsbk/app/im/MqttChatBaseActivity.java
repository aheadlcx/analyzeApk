package qsbk.app.im;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;

public class MqttChatBaseActivity extends Activity implements MsgStateChangeObserver {
    public static final String GROUP_ID = "group_id";
    public static final String TO_ID = "to_id";
    public static final String USER_ID = "user_id";
    protected EditText a;
    protected ChatListAdapter b;
    protected Handler c = new Handler();
    UserChatManager d = null;
    private LayoutInflater e;
    public Button mToContact;

    public String getContent() {
        return this.a.getText().toString().trim();
    }

    public String getUserId() {
        return getIntent().getStringExtra("user_id");
    }

    public String getGroupId() {
        return getIntent().getStringExtra("group_id");
    }

    public String getToId() {
        return getIntent().getStringExtra("to_id");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public LayoutInflater getInflater() {
        if (this.e == null) {
            this.e = (LayoutInflater) getSystemService("layout_inflater");
        }
        return this.e;
    }

    protected void onStop() {
        super.onStop();
    }

    public void onMsgStateChange(long j, int i) {
        this.c.post(new if(this, j, i));
    }
}
