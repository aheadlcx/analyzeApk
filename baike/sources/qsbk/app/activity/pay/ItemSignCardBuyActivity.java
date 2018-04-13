package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.model.Product;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.BlackProgressDialog;

public class ItemSignCardBuyActivity extends BaseActivity {
    public static final String ACTION_BUY_SUCCESS = "qsbk.app.ACTION_ITEM_BUY_SUCESS";
    BlackProgressDialog a;
    HttpTask b;
    EncryptHttpTask c;
    String d;
    String e;
    String f;
    String g;
    int h;

    public static void launch(Context context, String str, String str2, int i) {
        Intent intent = new Intent(context, ItemSignCardBuyActivity.class);
        intent.putExtra("id", str);
        intent.putExtra("name", str2);
        intent.putExtra("count", i);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, String str, String str2, int i, int i2) {
        Intent intent = new Intent(context, ItemSignCardBuyActivity.class);
        intent.putExtra("id", str);
        intent.putExtra("name", str2);
        intent.putExtra("count", i);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            ((Activity) context).startActivityForResult(intent, i2);
            return;
        }
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_item_buy;
    }

    protected void a(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.f = intent.getStringExtra("name");
            this.g = intent.getStringExtra("id");
            this.h = intent.getIntExtra("count", -1);
        }
        if (TextUtils.isEmpty(this.g) || this.h == -1) {
            finish();
            return;
        }
        this.a = new BlackProgressDialog(this);
        this.a.show();
        a(this.g, this.h);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.b == null || this.b.isCancelled())) {
            this.b.cancel(true);
        }
        if (this.c != null && !this.c.isCancelled()) {
            this.c.cancel(true);
        }
    }

    void a(String str, int i) {
        this.b = new EncryptHttpTask(Constants.WALLET_ITEM_PRICE, new a(this));
        Map hashMap = new HashMap();
        hashMap.put("card", str);
        hashMap.put("count", Integer.valueOf(i));
        this.b.setMapParams(hashMap);
        this.b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            Intent intent2 = new Intent(ACTION_BUY_SUCCESS);
            intent2.putExtra("product", new Product(this.f, this.g, this.h, this.d));
            sendBroadcast(intent2);
            ToastAndDialog.makeText(this, "购买成功").show();
            setResult(-1);
        }
        finish();
    }

    void d() {
        this.c = new EncryptHttpTask(Constants.WALLET_BALANCE, new b(this));
        this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
