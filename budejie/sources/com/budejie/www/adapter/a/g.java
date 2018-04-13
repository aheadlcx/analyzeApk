package com.budejie.www.adapter.a;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.bean.NotificationSettingItem;
import com.budejie.www.http.j;
import com.budejie.www.util.c;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.b;

public class g extends BaseAdapter {
    private Activity a;
    private List<NotificationSettingItem> b = new ArrayList();
    private Toast c;
    private SharedPreferences d;
    private Editor e;

    public g(Activity activity, List<NotificationSettingItem> list) {
        this.a = activity;
        this.d = activity.getSharedPreferences("config", 0);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                NotificationSettingItem notificationSettingItem = (NotificationSettingItem) list.get(i);
                if (notificationSettingItem != null && notificationSettingItem.getIs_show() == 1) {
                    this.b.add(notificationSettingItem);
                }
            }
        }
    }

    public int getCount() {
        return this.b.isEmpty() ? 0 : this.b.size();
    }

    public Object getItem(int i) {
        return this.b.isEmpty() ? Integer.valueOf(0) : this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        g$a g_a;
        if (view == null) {
            view = this.a.getLayoutInflater().inflate(R.layout.notification_settings_item, null);
            g$a g_a2 = new g$a(this);
            g_a2.c = (RelativeLayout) view.findViewById(R.id.switch_item_rl);
            g_a2.a = (TextView) view.findViewById(R.id.switch_name);
            g_a2.b = (ImageView) view.findViewById(R.id.switch_state);
            c.a(g_a2.b);
            view.setTag(g_a2);
            g_a = g_a2;
        } else {
            g_a = (g$a) view.getTag();
        }
        a(i, g_a);
        return view;
    }

    private void a(int i, g$a g_a) {
        NotificationSettingItem notificationSettingItem = (NotificationSettingItem) this.b.get(i);
        if (notificationSettingItem != null) {
            int user_setting = notificationSettingItem.getUser_setting();
            String db_key = notificationSettingItem.getDb_key();
            g_a.c.setVisibility(0);
            g_a.a.setText(notificationSettingItem.getName());
            g_a.b.setBackgroundResource(user_setting == 0 ? R.drawable.switch_on : R.drawable.switch_off);
            g_a.b.setTag(notificationSettingItem);
            g_a.b.setOnClickListener(new g$1(this, g_a, db_key));
        }
    }

    public b a(String str, String str2) {
        return new j().i(this.a, str, str2);
    }
}
