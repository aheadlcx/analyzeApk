package com.tencent.mm.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.tencent.mm.sdk.c.a.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class a implements SharedPreferences {
    private final ContentResolver a;
    private final String[] b = new String[]{HistoryOpenHelper.COLUMN_ID, "key", "type", "value"};
    private final HashMap<String, Object> c = new HashMap();
    private a d = null;

    private static class a implements Editor {
        private ContentResolver a;
        private Map<String, Object> e = new HashMap();
        private Set<String> f = new HashSet();
        private boolean g = false;

        public a(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        public final void apply() {
        }

        public final Editor clear() {
            this.g = true;
            return this;
        }

        public final boolean commit() {
            ContentValues contentValues = new ContentValues();
            if (this.g) {
                this.a.delete(b.CONTENT_URI, null, null);
                this.g = false;
            }
            for (String str : this.f) {
                this.a.delete(b.CONTENT_URI, "key = ?", new String[]{str});
            }
            for (Entry value : this.e.entrySet()) {
                int i;
                boolean z;
                Object value2 = value.getValue();
                if (value2 == null) {
                    com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
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
                    com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, unknown type=" + value2.getClass().toString());
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
                    this.a.update(b.CONTENT_URI, contentValues, "key = ?", new String[]{(String) value.getKey()});
                }
            }
            return true;
        }

        public final Editor putBoolean(String str, boolean z) {
            this.e.put(str, Boolean.valueOf(z));
            this.f.remove(str);
            return this;
        }

        public final Editor putFloat(String str, float f) {
            this.e.put(str, Float.valueOf(f));
            this.f.remove(str);
            return this;
        }

        public final Editor putInt(String str, int i) {
            this.e.put(str, Integer.valueOf(i));
            this.f.remove(str);
            return this;
        }

        public final Editor putLong(String str, long j) {
            this.e.put(str, Long.valueOf(j));
            this.f.remove(str);
            return this;
        }

        public final Editor putString(String str, String str2) {
            this.e.put(str, str2);
            this.f.remove(str);
            return this;
        }

        public final Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        public final Editor remove(String str) {
            this.f.add(str);
            return this;
        }
    }

    public a(Context context) {
        this.a = context.getContentResolver();
    }

    private Object getValue(String str) {
        try {
            Cursor query = this.a.query(b.CONTENT_URI, this.b, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object a = query.moveToFirst() ? com.tencent.mm.sdk.c.a.a.a(query.getInt(query.getColumnIndex("type")), query.getString(query.getColumnIndex("value"))) : null;
            query.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean contains(String str) {
        return getValue(str) != null;
    }

    public final Editor edit() {
        if (this.d == null) {
            this.d = new a(this.a);
        }
        return this.d;
    }

    public final Map<String, ?> getAll() {
        try {
            Cursor query = this.a.query(b.CONTENT_URI, this.b, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex("key");
            int columnIndex2 = query.getColumnIndex("type");
            int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                this.c.put(query.getString(columnIndex), com.tencent.mm.sdk.c.a.a.a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.c;
        } catch (Exception e) {
            e.printStackTrace();
            return this.c;
        }
    }

    public final boolean getBoolean(String str, boolean z) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Boolean)) ? z : ((Boolean) value).booleanValue();
    }

    public final float getFloat(String str, float f) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Float)) ? f : ((Float) value).floatValue();
    }

    public final int getInt(String str, int i) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Integer)) ? i : ((Integer) value).intValue();
    }

    public final long getLong(String str, long j) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Long)) ? j : ((Long) value).longValue();
    }

    public final String getString(String str, String str2) {
        Object value = getValue(str);
        return (value == null || !(value instanceof String)) ? str2 : (String) value;
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public final void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public final void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
