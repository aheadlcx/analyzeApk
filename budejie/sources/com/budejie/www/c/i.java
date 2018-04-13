package com.budejie.www.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.h;
import com.bdj.picture.edit.util.b;
import com.budejie.www.bean.OperationButton;
import com.budejie.www.bean.OperationItem;
import com.budejie.www.util.aa;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class i {
    Context a;
    SQLiteDatabase b;
    c c;

    public i(Context context) {
        this.a = context;
        this.c = c.a(context);
    }

    private void b() {
        aa.c("OperationDB", "onOpen");
        this.b = this.c.getWritableDatabase();
    }

    private void c() {
        aa.c("OperationDB", "onClose");
        this.b.close();
    }

    public void a(List<OperationItem> list) {
        synchronized (c.a) {
            b();
            this.b.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                OperationItem operationItem = (OperationItem) list.get(i);
                ContentValues contentValues = new ContentValues();
                try {
                    contentValues.put("id", operationItem.id);
                    contentValues.put("is_show", operationItem.is_show);
                    contentValues.put("show_num", operationItem.show_num);
                    contentValues.put("font_color", operationItem.font_color);
                    contentValues.put("title", operationItem.title);
                    contentValues.put("words", operationItem.words);
                    contentValues.put("buttons", operationItem.buttons);
                    contentValues.put(x.X, operationItem.end_time);
                    contentValues.put("backgroud_pic", operationItem.backgroud_pic);
                    contentValues.put("iphone", operationItem.iphone);
                    contentValues.put("ipad", operationItem.ipad);
                    contentValues.put(AlibcConstants.PF_ANDROID, operationItem.android);
                    contentValues.put("recommend_targets", operationItem.recommend_targets);
                    contentValues.put("order_id", operationItem.order_id);
                } catch (Exception e) {
                    aa.e("", "ljj-->fetchOperationInfo insert: " + e.toString());
                }
                this.b.insert("operation", null, contentValues);
            }
            this.b.setTransactionSuccessful();
            this.b.endTransaction();
            c();
        }
    }

    public ArrayList<OperationItem> a() {
        ArrayList<OperationItem> arrayList;
        synchronized (c.a) {
            arrayList = new ArrayList();
            String[] strArr = new String[]{"id", "is_show", "show_num", "font_color", "title", "words", "buttons", x.X, "backgroud_pic", "iphone", "ipad", AlibcConstants.PF_ANDROID, "recommend_targets", "order_id"};
            b();
            Cursor query = this.b.query("operation", strArr, null, null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    OperationItem operationItem = new OperationItem();
                    operationItem.id = query.getString(0);
                    operationItem.is_show = query.getString(1);
                    operationItem.show_num = query.getString(2);
                    operationItem.font_color = query.getString(3);
                    operationItem.title = query.getString(4);
                    operationItem.words = query.getString(5);
                    operationItem.buttons = query.getString(6);
                    operationItem.end_time = query.getString(7);
                    operationItem.backgroud_pic = query.getString(8);
                    operationItem.iphone = query.getString(9);
                    operationItem.ipad = query.getString(10);
                    operationItem.android = query.getString(11);
                    operationItem.recommend_targets = query.getString(12);
                    operationItem.order_id = query.getString(13);
                    try {
                        JSONObject jSONObject;
                        List arrayList2 = new ArrayList();
                        JSONObject jSONObject2 = new JSONObject(Uri.decode(operationItem.buttons.replace("\"{", "{").replace("\\\"", "\"").replace("}\"", h.d)));
                        if (jSONObject2.has("button_one")) {
                            jSONObject = jSONObject2.getJSONObject("button_one");
                            OperationButton operationButton = new OperationButton();
                            operationButton.words = b.a(jSONObject, "words");
                            operationButton.jump_type = b.a(jSONObject, "jump_type");
                            operationButton.type = b.a(jSONObject, "type");
                            operationButton.value = b.a(jSONObject, "value");
                            arrayList2.add(operationButton);
                        }
                        if (jSONObject2.has("button_two")) {
                            jSONObject = jSONObject2.getJSONObject("button_two");
                            OperationButton operationButton2 = new OperationButton();
                            operationButton2.words = b.a(jSONObject, "words");
                            operationButton2.jump_type = b.a(jSONObject, "jump_type");
                            operationButton2.type = b.a(jSONObject, "type");
                            operationButton2.value = b.a(jSONObject, "value");
                            arrayList2.add(operationButton2);
                        }
                        operationItem.operationButtonList = arrayList2;
                    } catch (Exception e) {
                    }
                    arrayList.add(operationItem);
                }
                query.close();
            }
            c();
        }
        return arrayList;
    }

    public void a(String str, String[] strArr) {
        synchronized (c.a) {
            b();
            this.b.delete("operation", str, strArr);
            c();
        }
    }
}
