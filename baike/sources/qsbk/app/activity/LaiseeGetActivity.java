package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.LaiseeEventGetFragment;
import qsbk.app.fragments.LaiseeGetErrorFragment;
import qsbk.app.fragments.LaiseeNormalGetFragment;
import qsbk.app.fragments.LaiseeVoiceGetFragment;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeVoice;
import qsbk.app.widget.BlackProgressDialog;

public class LaiseeGetActivity extends BaseActivity {
    private Laisee a;
    private EncryptHttpTask b;
    private BlackProgressDialog c;
    private String d;

    public static void launch(Context context, String str, Laisee laisee) {
        Intent intent = new Intent(context, LaiseeGetActivity.class);
        intent.putExtra("laisee", laisee);
        intent.putExtra("conversationId", str);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        intent.addFlags(65536);
        context.startActivity(intent);
    }

    public static void launch(Context context, Laisee laisee) {
        launch(context, "", laisee);
    }

    protected void onCreate(Bundle bundle) {
        getWindow().requestFeature(1);
        super.onCreate(bundle);
    }

    protected void onDestroy() {
        if (this.b != null) {
            this.b.cancel(true);
        }
        super.onDestroy();
    }

    protected int a() {
        return R.layout.activity_laisee_get;
    }

    protected void a(Bundle bundle) {
        if (getIntent() != null) {
            this.a = (Laisee) getIntent().getSerializableExtra("laisee");
            this.d = getIntent().getStringExtra("conversationId");
        }
        if (this.a == null) {
            finish();
            return;
        }
        this.c = new BlackProgressDialog(this);
        this.c.setCanceledOnTouchOutside(false);
        this.c.setOnCancelListener(new qu(this));
        a(this.a);
        getWindow().getDecorView().postDelayed(new qv(this), 1000);
    }

    private void d() {
        Fragment newInstance;
        if (this.a.isEmpty() || this.a.isExpired()) {
            newInstance = LaiseeGetErrorFragment.newInstance(this.a);
        } else if (this.a.isActivityLaisee()) {
            newInstance = LaiseeEventGetFragment.newInstance(this.a);
        } else if (this.a instanceof LaiseeVoice) {
            newInstance = LaiseeVoiceGetFragment.newInstance((LaiseeVoice) this.a, this.d);
        } else {
            newInstance = LaiseeNormalGetFragment.newInstance(this.a);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content, newInstance).commitNowAllowingStateLoss();
    }

    private void a(Laisee laisee) {
        this.c.show();
        if (laisee != null) {
            this.b = new EncryptHttpTask(null, String.format(Constants.LAISEE_DETAIL, new Object[]{laisee.id}), new qw(this));
            Map hashMap = new HashMap();
            hashMap.put("secret", laisee.secret);
            this.b.setMapParams(hashMap);
            this.b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
