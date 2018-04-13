package com.tencent.mm.opensdk.openapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.c.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

class a implements SharedPreferences {
    private final ContentResolver a;
    private final String[] b = new String[]{FileDownloadModel.ID, PayPWDUniversalActivity.KEY, "type", "value"};
    private final HashMap<String, Object> c = new HashMap();
    private a d = null;

    private static class a implements Editor {
        private Map<String, Object> a = new HashMap();
        private Set<String> b = new HashSet();
        private boolean c = false;
        private ContentResolver d;

        public a(ContentResolver contentResolver) {
            this.d = contentResolver;
        }

        public void apply() {
        }

        public Editor clear() {
            this.c = true;
            return this;
        }

        public boolean commit() {
            ContentValues contentValues = new ContentValues();
            if (this.c) {
                this.d.delete(b.CONTENT_URI, null, null);
                this.c = false;
            }
            for (String str : this.b) {
                this.d.delete(b.CONTENT_URI, "key = ?", new String[]{str});
            }
            for (Entry value : this.a.entrySet()) {
                int i;
                boolean z;
                Object value2 = value.getValue();
                if (value2 == null) {
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
                    i = 0;
                } else if (value2 instanceof Integer) {
                    i = 1;
                } else if (value2 instanceof Long) {
                    i = 2;
                } else if (value2 instanceof String) {
                    i = 3;
                } else if (value2 instanceof Boolean) {
                    i = 4;
                } else if (value2 instanceof Float) {
                    i = 5;
                } else if (value2 instanceof Double) {
                    i = 6;
                } else {
                    Log.e("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, unknown type=" + value2.getClass().toString());
                    i = 0;
                }
                if (i == 0) {
                    z = false;
                } else {
                    contentValues.put("type", Integer.valueOf(i));
                    contentValues.put("value", value2.toString());
                    z = true;
                }
                if (z) {
                    this.d.update(b.CONTENT_URI, contentValues, "key = ?", new String[]{(String) value.getKey()});
                }
            }
            return true;
        }

        public Editor putBoolean(String str, boolean z) {
            this.a.put(str, Boolean.valueOf(z));
            this.b.remove(str);
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.a.put(str, Float.valueOf(f));
            this.b.remove(str);
            return this;
        }

        public Editor putInt(String str, int i) {
            this.a.put(str, Integer.valueOf(i));
            this.b.remove(str);
            return this;
        }

        public Editor putLong(String str, long j) {
            this.a.put(str, Long.valueOf(j));
            this.b.remove(str);
            return this;
        }

        public Editor putString(String str, String str2) {
            this.a.put(str, str2);
            this.b.remove(str);
            return this;
        }

        public Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        public Editor remove(String str) {
            this.b.add(str);
            return this;
        }
    }

    public a(Context context) {
        this.a = context.getContentResolver();
    }

    private Object a(String str) {
        try {
            Cursor query = this.a.query(b.CONTENT_URI, this.b, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object a = query.moveToFirst() ? com.tencent.mm.opensdk.utils.c.a.a(query.getInt(query.getColumnIndex("type")), query.getString(query.getColumnIndex("value"))) : null;
            query.close();
            return a;
        } catch (Exception e) {
            Log.e("MicroMsg.SDK.SharedPreferences", "getValue exception:" + e.getMessage());
            return null;
        }
    }

    public boolean contains(String str) {
        return a(str) != null;
    }

    public Editor edit() {
        if (this.d == null) {
            this.d = new a(this.a);
        }
        return this.d;
    }

    public Map<String, ?> getAll() {
        try {
            Cursor query = this.a.query(b.CONTENT_URI, this.b, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex(PayPWDUniversalActivity.KEY);
            int columnIndex2 = query.getColumnIndex("type");
            int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                this.c.put(query.getString(columnIndex), com.tencent.mm.opensdk.utils.c.a.a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.c;
        } catch (Exception e) {
            Log.e("MicroMsg.SDK.SharedPreferences", "getAll exception:" + e.getMessage());
            return this.c;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        Object a = a(str);
        return (a == null || !(a instanceof Boolean)) ? z : ((Boolean) a).booleanValue();
    }

    public float getFloat(String str, float f) {
        Object a = a(str);
        return (a == null || !(a instanceof Float)) ? f : ((Float) a).floatValue();
    }

    public int getInt(String str, int i) {
        Object a = a(str);
        return (a == null || !(a instanceof Integer)) ? i : ((Integer) a).intValue();
    }

    public long getLong(String str, long j) {
        Object a = a(str);
        return (a == null || !(a instanceof Long)) ? j : ((Long) a).longValue();
    }

    public String getString(String str, String str2) {
        Object a = a(str);
        return (a == null || !(a instanceof String)) ? str2 : (String) a;
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
