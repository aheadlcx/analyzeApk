package qsbk.app.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.fragments.QiushiNotificationListFragment;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TimeDelta;

public class PromoteFlowerDialog extends Activity {
    public static final int REQUEST_CODE = 1776;
    public static final int TYPE_PREFER = 1;
    public static final int TYPE_PROMOTE = 0;
    TextView a;
    Button b;
    ImageView c;
    private Timer d;
    private TimeDelta e;
    public int type;

    public static void launch(Activity activity, int i, int i2) {
        Intent intent = new Intent(activity, PromoteFlowerDialog.class);
        intent.putExtra("type", i2);
        activity.startActivityForResult(intent, i);
    }

    public static void launch(Activity activity, int i) {
        Intent intent = new Intent(activity, PromoteFlowerDialog.class);
        intent.putExtra("type", i);
        activity.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_promote_flower);
        getWindow().setLayout(-1, -1);
        this.type = getIntent().getIntExtra("type", -1);
        if (this.type < 0) {
            finish();
        }
        this.d = new Timer();
        this.e = new TimeDelta();
        this.a = (TextView) findViewById(R.id.desc);
        this.b = (Button) findViewById(R.id.btn_join);
        this.c = (ImageView) findViewById(R.id.promote_flower_image);
        if (this.type == 0) {
            this.c.setImageResource(R.drawable.promote_flower);
        } else if (this.type == 1) {
            this.c.setImageResource(R.drawable.prefer_flower);
        }
        a();
    }

    private void a() {
        String str = "";
        if (this.type == 0) {
            str = SharePreferenceUtils.getSharePreferencesValue(QiushiNotificationListFragment.SHOW_FLOWER_DESC);
        } else if (this.type == 1) {
            str = SharePreferenceUtils.getSharePreferencesValue(QiushiNotificationListFragment.SHOW_PREFER_DESC);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.optString("tribe_id");
            CharSequence optString = jSONObject.optString("pop_desc");
            CharSequence optString2 = jSONObject.optString("button_desc");
            this.a.setText(optString);
            this.b.setText(optString2);
            this.b.setOnClickListener(new b(this, str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e.getDelta() > 2000) {
            setResult(-1);
            finish();
        }
        return super.onTouchEvent(motionEvent);
    }
}
