package com.ishumei.d;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Parcel;
import com.ishumei.b.d;
import com.ishumei.dfp.SMSDK;
import dalvik.system.BaseDexClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a b = null;
    private Context a;

    a() {
        this.a = null;
        this.a = d.a;
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private boolean a(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            return false;
        }
        if (!(classLoader instanceof BaseDexClassLoader)) {
            return false;
        }
        try {
            Class cls = Class.forName(com.ishumei.f.d.g("9b9e93899694d18c868c8b9a92d1bb9a87af9e8b97b3968c8b"));
            Method method = Class.forName(com.ishumei.f.d.g("9b9e93899694d18c868c8b9a92d1bb9a87af9e8b97b3968c8bdbba939a929a918b")).getMethod(com.ishumei.f.d.g("8b90ac8b8d969198"), null);
            Field declaredField = cls.getDeclaredField(com.ishumei.f.d.g("9b9a87ba939a929a918b8c"));
            declaredField.setAccessible(true);
            Field declaredField2 = BaseDexClassLoader.class.getDeclaredField(com.ishumei.f.d.g("8f9e8b97b3968c8b"));
            declaredField2.setAccessible(true);
            for (Object invoke : (Object[]) declaredField.get(declaredField2.get(classLoader))) {
                String str2 = (String) method.invoke(invoke, null);
                if (str2 != null && str2.contains(str)) {
                    return true;
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public void a(Map<String, Object> map, String str, boolean z) {
        String str2 = "aenc";
        String str3 = "4";
        try {
            String y1 = SMSDK.y1(z);
            if (y1 == null) {
                map.put(str, "");
                map.put(str2, "0");
                return;
            }
            try {
                map.put(str, new JSONObject(y1));
                map.put(str2, "0");
            } catch (JSONException e) {
                map.put(str, y1);
                map.put(str2, str3);
            } catch (Exception e2) {
                map.put(str, y1);
                map.put(str2, "0");
            }
        } catch (Exception e3) {
            map.put(str, "");
            map.put(str2, "0");
        }
    }

    public boolean a(String str) {
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (a(systemClassLoader, str) || a(systemClassLoader.getParent(), str)) {
                return true;
            }
            systemClassLoader = getClass().getClassLoader();
            if (a(systemClassLoader, str) || a(systemClassLoader.getParent(), str)) {
                return true;
            }
            return false;
        } catch (Exception e) {
        }
    }

    public String b() {
        Parcel obtain;
        Parcel obtain2;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            Field declaredField = Class.forName(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1bd938a9a8b90908b97be9b9e8f8b9a8d")).getDeclaredField(com.ishumei.f.d.g("92ac9a8d89969c9a"));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(defaultAdapter);
            if (obj == null) {
                throw new Exception();
            }
            obj = Class.forName(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97dbac8b8a9ddbaf8d908786")).getMethod(com.ishumei.f.d.g("989a8bbe9b9b8d9a8c8c"), null).invoke(obj, null);
            if (obj != null && (obj instanceof String)) {
                return (String) obj;
            }
            throw new Exception();
        } catch (Exception e) {
            try {
                Class cls = Class.forName(com.ishumei.f.d.g("9e919b8d90969bd1908cd1ac9a8d89969c9ab29e919e989a8d"));
                Class.forName(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8d"));
                Class cls2 = Class.forName(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8ddbac8b8a9d"));
                IBinder iBinder = (IBinder) cls.getMethod(com.ishumei.f.d.g("989a8bac9a8d89969c9a"), new Class[]{String.class}).invoke(null, new Object[]{com.ishumei.f.d.g("9d938a9a8b90908b97a0929e919e989a8d")});
                cls2.getField(com.ishumei.f.d.g("b9b6adacaba0bcbeb3b3a0abadbeb1acbebcabb6b0b1")).getInt(cls2);
                obtain = Parcel.obtain();
                obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken(com.ishumei.f.d.g("9e919b8d90969bd19d938a9a8b90908b97d1b6bd938a9a8b90908b97b29e919e989a8d"));
                if (VERSION.SDK_INT >= 21) {
                    iBinder.transact(5, obtain, obtain2, 0);
                } else {
                    iBinder.transact(10, obtain, obtain2, 0);
                }
                obtain2.readException();
                String readString = obtain2.readString();
                return readString == null ? "" : readString;
            } catch (Exception e2) {
                return "";
            }
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public boolean c() {
        try {
            return a(com.ishumei.f.d.g("a78f908c9a9bbd8d969b989ad1959e8d"));
        } catch (Exception e) {
            return false;
        }
    }
}
