package com.budejie.www.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.bean.AdItem;
import com.budejie.www.c.a;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.elves.update.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class SisterReceiver extends BroadcastReceiver {
    Context a;
    a b;
    Handler c = new Handler(this) {
        final /* synthetic */ SisterReceiver a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    try {
                        JSONObject jSONObject = new JSONObject((String) message.obj);
                        aa.a("SisterReceiver", "object:" + jSONObject);
                        if ("1".equals(jSONObject.getString(j.c))) {
                            String string = jSONObject.getString("data_id");
                            if (!TextUtils.isEmpty(string)) {
                                Intent intent = new Intent();
                                intent.setAction("com.elves.sister.receivemessage");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.addFlags(268435456);
                                intent.addFlags(67108864);
                                intent.addFlags(2097152);
                                SharedPreferences sharedPreferences = this.a.a.getSharedPreferences("weiboprefer", 0);
                                if (!string.equals(sharedPreferences.getString("dataid", ""))) {
                                    sharedPreferences.edit().putString("dataid", string).commit();
                                    intent.putExtra("flag", true);
                                    CharSequence string2 = jSONObject.getString("title");
                                    NotificationManager notificationManager = (NotificationManager) this.a.a.getSystemService("notification");
                                    CharSequence format = new SimpleDateFormat("hh:MM").format(new Date());
                                    Notification notification = new Notification(17301554, "新消息", System.currentTimeMillis());
                                    notification.flags |= 16;
                                    notification.contentView = new RemoteViews(this.a.a.getPackageName(), R.layout.notifymessage1);
                                    notification.contentView.setImageViewResource(R.id.notifyLogo, R.drawable.icon_new);
                                    notification.contentView.setTextViewText(R.id.notifyTitle, string2);
                                    notification.contentView.setTextViewText(R.id.notifyTime, format);
                                    notification.contentView.setTextViewText(R.id.notifyMessage, "点击可查看最新内容");
                                    notification.contentIntent = PendingIntent.getActivity(this.a.a, 0, intent, 0);
                                    notificationManager.notify(123456789, notification);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (JSONException e) {
                        return;
                    }
                case 2:
                    Bundle bundle = (Bundle) message.obj;
                    String string3 = bundle.getString(j.c);
                    int i = bundle.getInt("id");
                    if (string3 != null) {
                        this.a.b.a(i);
                        return;
                    } else {
                        this.a.b.a(i, 2);
                        return;
                    }
                default:
                    return;
            }
        }
    };
    ArrayList<AdItem> d = new ArrayList();
    private com.budejie.www.http.j e = new com.budejie.www.http.j();

    public void onReceive(Context context, Intent intent) {
        this.a = context;
        String action = intent.getAction();
        aa.a("SisterReceiver", "onReceive , action =" + action);
        if ("com.budejie.download.successed".equals(action)) {
            int intExtra = intent.getIntExtra("notifyId", 0);
            d.a(context, intent.getStringExtra("fileName"));
            ((NotificationManager) context.getSystemService("notification")).cancel(intExtra);
        } else if ("com.budejie.download.failed".equals(action)) {
            if (an.a(context)) {
                action = intent.getStringExtra("url");
                new d(context).a(Environment.getExternalStorageDirectory().getPath() + "/elves", action, false, "");
                return;
            }
            Toast.makeText(context, context.getString(R.string.nonet), 0).show();
        } else if (!"android.intent.action.BOOT_COMPLETED".equals(action) && "com.elves.budejie.check.unsend.adrequest".equals(action)) {
            if (this.b == null) {
                this.b = new a(context);
            }
            if (an.a(context)) {
                a();
            }
        }
    }

    private boolean a(AdItem adItem) {
        if (this.b == null) {
            return false;
        }
        if (!an.a(this.a)) {
            return false;
        }
        int id = adItem.getId();
        String url = adItem.getUrl();
        this.b.a(id, 1);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        BudejieApplication.a.a(this.a, url, this.c, 2, bundle);
        this.d.remove(0);
        return this.d.isEmpty();
    }

    private void a() {
        new AsyncTask<Void, Void, ArrayList<AdItem>>(this) {
            final /* synthetic */ SisterReceiver a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((ArrayList) obj);
            }

            protected ArrayList<AdItem> a(Void... voidArr) {
                this.a.d = this.a.b.a();
                return this.a.d;
            }

            protected void a(ArrayList<AdItem> arrayList) {
                super.onPostExecute(arrayList);
                while (!arrayList.isEmpty()) {
                    if (this.a.a((AdItem) arrayList.get(0))) {
                        return;
                    }
                }
            }
        }.execute(new Void[0]);
    }
}
