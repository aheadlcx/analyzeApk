package com.ishumei.d;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.ishumei.f.c;
import com.umeng.analytics.a;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.HashMap;

public class d {
    static final Uri a = Phone.CONTENT_URI;
    static final Uri b = Calls.CONTENT_URI;
    private static d d = null;
    private Context c = null;

    private d() {
        try {
            this.c = com.ishumei.b.d.a;
        } catch (Exception e) {
        }
    }

    public static d a() {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d();
                }
            }
        }
        return d;
    }

    public ArrayList<Object> b() {
        Cursor query;
        Exception exception;
        Cursor cursor = null;
        ArrayList<Object> arrayList = new ArrayList();
        try {
            if (this.c != null) {
                query = this.c.getContentResolver().query(b, null, null, null, "date desc");
                if (query == null) {
                    return arrayList;
                }
                int i = 0;
                while (query.moveToNext()) {
                    try {
                        Object obj;
                        int i2;
                        HashMap hashMap = new HashMap();
                        String string = query.getString(query.getColumnIndex("name"));
                        String string2 = query.getString(query.getColumnIndex("number"));
                        long j = query.getLong(query.getColumnIndex("duration"));
                        long j2 = query.getLong(query.getColumnIndex(HistoryOpenHelper.COLUMN_DATE));
                        int i3 = query.getInt(query.getColumnIndex("type"));
                        switch (i3) {
                            case 1:
                                obj = null;
                                i2 = 1;
                                break;
                            case 2:
                                obj = null;
                                i2 = 0;
                                break;
                            case 3:
                                i2 = 2;
                                obj = null;
                                break;
                            default:
                                i2 = i3;
                                obj = 1;
                                break;
                        }
                        if (obj != 1) {
                            hashMap.put("duration", Long.valueOf(j));
                            hashMap.put("name", string);
                            hashMap.put("tel", string2);
                            hashMap.put(AppLinkConstants.TIME, Long.valueOf(j2));
                            hashMap.put("type", Integer.valueOf(i2));
                            arrayList.add(hashMap);
                            i3 = i + 1;
                            if (i3 < 1000) {
                                i = i3;
                            }
                        }
                    } catch (SecurityException e) {
                    } catch (Exception e2) {
                        cursor = query;
                        exception = e2;
                    }
                }
            } else {
                query = null;
            }
        } catch (SecurityException e3) {
            query = null;
        } catch (Exception e4) {
            exception = e4;
            c.d("Contact", "get calllog failed: " + exception.getMessage());
            query = cursor;
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<Object> c() {
        Cursor query;
        Exception exception;
        Cursor cursor = null;
        ArrayList<Object> arrayList = new ArrayList();
        try {
            if (this.c != null) {
                query = this.c.getContentResolver().query(a, null, null, null, null);
                if (query == null) {
                    return arrayList;
                }
                int i = 0;
                do {
                    try {
                        if (!query.moveToNext()) {
                            break;
                        }
                        HashMap hashMap = new HashMap();
                        String string = query.getString(query.getColumnIndex(x.g));
                        String string2 = query.getString(query.getColumnIndex("data1"));
                        hashMap.put("name", string);
                        hashMap.put("tel", string2);
                        arrayList.add(hashMap);
                        i++;
                    } catch (SecurityException e) {
                    } catch (Exception e2) {
                        cursor = query;
                        exception = e2;
                    }
                } while (i < 1000);
            } else {
                query = null;
            }
        } catch (SecurityException e3) {
            query = null;
        } catch (Exception e4) {
            exception = e4;
            c.d("Contact", "get contact failed: " + exception.getMessage());
            query = cursor;
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<Object> d() {
        Cursor query;
        Cursor cursor = null;
        Uri parse = Uri.parse("content://sms/");
        String[] strArr = new String[]{HistoryOpenHelper.COLUMN_ID, "address", a.z, HistoryOpenHelper.COLUMN_DATE, "type"};
        ArrayList<Object> arrayList = new ArrayList();
        try {
            if (this.c != null) {
                ContentResolver contentResolver = this.c.getContentResolver();
                if (contentResolver == null) {
                    return arrayList;
                }
                query = contentResolver.query(parse, strArr, null, null, "date desc");
                if (query == null) {
                    return arrayList;
                }
                int i = 0;
                while (query.moveToNext()) {
                    try {
                        HashMap hashMap = new HashMap();
                        int columnIndex = query.getColumnIndex("address");
                        int columnIndex2 = query.getColumnIndex(a.z);
                        int columnIndex3 = query.getColumnIndex(HistoryOpenHelper.COLUMN_DATE);
                        int columnIndex4 = query.getColumnIndex("type");
                        String string = query.getString(columnIndex);
                        String string2 = query.getString(columnIndex2);
                        long j = query.getLong(columnIndex3);
                        long j2 = query.getLong(columnIndex4);
                        if (j2 == 1 && (string.length() <= 7 || string.startsWith("106"))) {
                            hashMap.put("tel", string);
                            hashMap.put("text", string2);
                            hashMap.put(AppLinkConstants.TIME, Long.valueOf(j / 1000));
                            hashMap.put("type", Long.valueOf(j2));
                            arrayList.add(hashMap);
                            i++;
                            if (i >= 1000) {
                                break;
                            }
                        }
                    } catch (SecurityException e) {
                    } catch (Exception e2) {
                        cursor = query;
                        Exception exception = e2;
                    }
                }
            } else {
                query = null;
            }
        } catch (SecurityException e3) {
            query = null;
        } catch (Exception e4) {
            exception = e4;
            c.d("Contact", "get message failed: " + exception.getMessage());
            query = cursor;
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }
}
