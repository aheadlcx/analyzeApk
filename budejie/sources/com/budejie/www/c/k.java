package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.bean.RuleBean;
import org.json.JSONArray;
import org.json.JSONObject;

public class k {
    private Context a;
    private c b = c.a(this.a);
    private SQLiteDatabase c;

    public k(Context context) {
        this.a = context;
    }

    private void b() {
        this.c = this.b.getWritableDatabase();
    }

    private void c() {
        this.c.close();
    }

    public void a(String str) {
        synchronized (c.a) {
            ContentValues contentValues = new ContentValues();
            b();
            this.c.delete("rule", null, null);
            try {
                this.c.beginTransaction();
                RuleBean ruleBean = new RuleBean();
                JSONArray jSONArray = new JSONObject(str).getJSONArray("order_rule");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i).getJSONObject("credit");
                    ruleBean.name = jSONObject.getString("name");
                    ruleBean.money = (float) jSONObject.getInt("money");
                    ruleBean.up_limit = ((float) jSONObject.getInt("up_limit")) / ruleBean.money;
                    contentValues.put("name", ruleBean.name);
                    contentValues.put("money", Float.valueOf(ruleBean.money));
                    contentValues.put("up_limit", Float.valueOf(ruleBean.up_limit));
                    Log.d("RULE", "CON" + this.c.insert("rule", null, contentValues));
                }
                this.c.setTransactionSuccessful();
                this.c.endTransaction();
                c();
            } catch (Exception e) {
                e.printStackTrace();
                this.c.endTransaction();
                c();
            } catch (Throwable th) {
                this.c.endTransaction();
                c();
            }
        }
    }

    public RuleBean a(Context context, String str) {
        RuleBean ruleBean;
        if (a() <= 0) {
            TipPopUp.a(context);
        }
        synchronized (c.a) {
            String[] strArr = new String[]{"name", "money", "up_limit"};
            String[] strArr2 = new String[]{str};
            b();
            Cursor query = this.c.query("rule", strArr, "name=?", strArr2, null, null, null);
            if (query.getCount() != 0) {
                query.moveToFirst();
                ruleBean = new RuleBean();
                try {
                    ruleBean.name = query.getString(0);
                    ruleBean.money = (float) query.getInt(1);
                    ruleBean.up_limit = (float) query.getInt(2);
                } catch (Exception e) {
                }
            } else {
                ruleBean = null;
            }
            query.close();
            c();
        }
        return ruleBean;
    }

    public int a() {
        int count;
        synchronized (c.a) {
            b();
            Cursor query = this.c.query("rule", new String[]{"name"}, null, null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                count = query.getCount();
            } else {
                count = 0;
            }
            c();
        }
        return count;
    }
}
