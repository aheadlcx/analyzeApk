package qsbk.app.utils.json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JSONUtil {
    static HashMap<Class, List<Field>> a = new HashMap();

    private static List<Field> a(Class cls) {
        if (cls == null) {
            return null;
        }
        List<Field> linkedList = new LinkedList();
        Class cls2 = cls;
        do {
            Field[] declaredFields = cls2.getDeclaredFields();
            int i = 0;
            while (i < declaredFields.length) {
                int modifiers = declaredFields[i].getModifiers();
                if ((Modifier.isProtected(modifiers) || Modifier.isPublic(modifiers) || (Modifier.isPrivate(modifiers) && cls2 == cls)) && !Modifier.isStatic(modifiers) && declaredFields[i].getAnnotation(JsonPrivate.class) == null) {
                    linkedList.add(declaredFields[i]);
                }
                i++;
            }
            cls2 = cls2.getSuperclass();
        } while (cls2 != JSONAble.class);
        return linkedList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject encodeToJsonObject(qsbk.app.utils.json.IJSONAble r10) {
        /*
        r6 = 1;
        r4 = 0;
        r1 = r10.getClass();
        r0 = a;
        r0 = r0.get(r1);
        r0 = (java.util.List) r0;
        if (r0 != 0) goto L_0x0019;
    L_0x0010:
        r0 = a(r1);
        r2 = a;
        r2.put(r1, r0);
    L_0x0019:
        r2 = r0;
        r7 = new org.json.JSONObject;
        r7.<init>();
        r3 = r4;
    L_0x0020:
        r0 = r2.size();
        if (r3 >= r0) goto L_0x00df;
    L_0x0026:
        r0 = r2.get(r3);
        r0 = (java.lang.reflect.Field) r0;
        r1 = qsbk.app.utils.json.JsonKeyName.class;
        r1 = r0.getAnnotation(r1);
        r1 = (qsbk.app.utils.json.JsonKeyName) r1;
        if (r1 == 0) goto L_0x0059;
    L_0x0036:
        r5 = r1.value();
        r5 = android.text.TextUtils.isEmpty(r5);
        if (r5 != 0) goto L_0x0059;
    L_0x0040:
        r1 = r1.value();
        r5 = r1;
    L_0x0045:
        r1 = qsbk.app.utils.json.JsonPrivate.class;
        r1 = r0.getAnnotation(r1);
        if (r1 != 0) goto L_0x0055;
    L_0x004d:
        r1 = qsbk.app.utils.json.JsonEncodePrivate.class;
        r1 = r0.getAnnotation(r1);
        if (r1 == 0) goto L_0x005f;
    L_0x0055:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0020;
    L_0x0059:
        r1 = r0.getName();
        r5 = r1;
        goto L_0x0045;
    L_0x005f:
        r1 = r0.getGenericType();
        r8 = 1;
        r0.setAccessible(r8);	 Catch:{ Exception -> 0x0089 }
        r8 = java.lang.Boolean.TYPE;	 Catch:{ Exception -> 0x0089 }
        if (r1 != r8) goto L_0x0091;
    L_0x006b:
        r1 = qsbk.app.utils.json.JsonToInt.class;
        r1 = r0.getAnnotation(r1);	 Catch:{ Exception -> 0x0089 }
        r1 = (qsbk.app.utils.json.JsonToInt) r1;	 Catch:{ Exception -> 0x0089 }
        r8 = r0.getBoolean(r10);	 Catch:{ Exception -> 0x0089 }
        if (r1 == 0) goto L_0x0085;
    L_0x0079:
        if (r8 == 0) goto L_0x0083;
    L_0x007b:
        r1 = r6;
    L_0x007c:
        r7.put(r5, r1);	 Catch:{ Exception -> 0x0089 }
    L_0x007f:
        r0.setAccessible(r4);
        goto L_0x0055;
    L_0x0083:
        r1 = r4;
        goto L_0x007c;
    L_0x0085:
        r7.put(r5, r8);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x0089:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x009d }
        r0.setAccessible(r4);
        goto L_0x0055;
    L_0x0091:
        r8 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0089 }
        if (r1 != r8) goto L_0x00a2;
    L_0x0095:
        r1 = r0.getInt(r10);	 Catch:{ Exception -> 0x0089 }
        r7.put(r5, r1);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x009d:
        r1 = move-exception;
        r0.setAccessible(r4);
        throw r1;
    L_0x00a2:
        r8 = java.lang.Long.TYPE;	 Catch:{ Exception -> 0x0089 }
        if (r1 != r8) goto L_0x00ae;
    L_0x00a6:
        r8 = r0.getLong(r10);	 Catch:{ Exception -> 0x0089 }
        r7.put(r5, r8);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x00ae:
        r8 = java.lang.String.class;
        if (r1 != r8) goto L_0x00bc;
    L_0x00b2:
        r1 = r0.get(r10);	 Catch:{ Exception -> 0x0089 }
        if (r1 == 0) goto L_0x007f;
    L_0x00b8:
        r7.put(r5, r1);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x00bc:
        r8 = java.lang.Double.TYPE;	 Catch:{ Exception -> 0x0089 }
        if (r1 != r8) goto L_0x00c8;
    L_0x00c0:
        r8 = r0.getDouble(r10);	 Catch:{ Exception -> 0x0089 }
        r7.put(r5, r8);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x00c8:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0089 }
        r1.<init>();	 Catch:{ Exception -> 0x0089 }
        r8 = "un handle json field:";
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0089 }
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x0089 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0089 }
        qsbk.app.utils.LogUtil.e(r1);	 Catch:{ Exception -> 0x0089 }
        goto L_0x007f;
    L_0x00df:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.utils.json.JSONUtil.encodeToJsonObject(qsbk.app.utils.json.IJSONAble):org.json.JSONObject");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void parseFromJSONObject(org.json.JSONObject r10, qsbk.app.utils.json.IJSONAble r11) {
        /*
        r3 = 1;
        r4 = 0;
        if (r10 != 0) goto L_0x000a;
    L_0x0004:
        r0 = "obj is null";
        qsbk.app.utils.LogUtil.e(r0);
    L_0x0009:
        return;
    L_0x000a:
        r1 = r11.getClass();
        r0 = a;
        r0 = r0.get(r1);
        r0 = (java.util.List) r0;
        if (r0 != 0) goto L_0x0021;
    L_0x0018:
        r0 = a(r1);
        r2 = a;
        r2.put(r1, r0);
    L_0x0021:
        r2 = r0;
        r11.initJSONDefaultValue();
        r6 = r2.size();
        r5 = r4;
    L_0x002a:
        if (r5 >= r6) goto L_0x0009;
    L_0x002c:
        r0 = r2.get(r5);
        r0 = (java.lang.reflect.Field) r0;
        r1 = qsbk.app.utils.json.JsonKeyName.class;
        r1 = r0.getAnnotation(r1);
        r1 = (qsbk.app.utils.json.JsonKeyName) r1;
        if (r1 == 0) goto L_0x0056;
    L_0x003c:
        r7 = r1.value();
        r7 = android.text.TextUtils.isEmpty(r7);
        if (r7 != 0) goto L_0x0056;
    L_0x0046:
        r1 = r1.value();
    L_0x004a:
        r7 = qsbk.app.utils.json.JsonPrivate.class;
        r7 = r0.getAnnotation(r7);
        if (r7 == 0) goto L_0x005b;
    L_0x0052:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x002a;
    L_0x0056:
        r1 = r0.getName();
        goto L_0x004a;
    L_0x005b:
        r7 = r10.has(r1);
        if (r7 == 0) goto L_0x0052;
    L_0x0061:
        r7 = r10.isNull(r1);
        if (r7 != 0) goto L_0x0052;
    L_0x0067:
        r7 = r0.getGenericType();
        r8 = 1;
        r0.setAccessible(r8);	 Catch:{ Exception -> 0x0099 }
        r8 = java.lang.Boolean.TYPE;	 Catch:{ Exception -> 0x0099 }
        if (r7 != r8) goto L_0x00a6;
    L_0x0073:
        r7 = r10.optString(r1);	 Catch:{ Exception -> 0x0099 }
        r7 = r7.toLowerCase();	 Catch:{ Exception -> 0x0099 }
        r8 = android.text.TextUtils.isDigitsOnly(r7);	 Catch:{ Exception -> 0x0099 }
        if (r8 == 0) goto L_0x0091;
    L_0x0081:
        r1 = java.lang.Integer.parseInt(r7);	 Catch:{ Exception -> 0x0099 }
        if (r1 == 0) goto L_0x008f;
    L_0x0087:
        r1 = r3;
    L_0x0088:
        r0.setBoolean(r11, r1);	 Catch:{ Exception -> 0x0099 }
    L_0x008b:
        r0.setAccessible(r4);
        goto L_0x0052;
    L_0x008f:
        r1 = r4;
        goto L_0x0088;
    L_0x0091:
        r1 = r10.optBoolean(r1);	 Catch:{ Exception -> 0x0099 }
        r0.setBoolean(r11, r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
    L_0x0099:
        r1 = move-exception;
        r7 = "set fields error";
        qsbk.app.utils.LogUtil.e(r7);	 Catch:{ all -> 0x00b2 }
        r1.printStackTrace();	 Catch:{ all -> 0x00b2 }
        r0.setAccessible(r4);
        goto L_0x0052;
    L_0x00a6:
        r8 = java.lang.String.class;
        if (r7 != r8) goto L_0x00b7;
    L_0x00aa:
        r1 = r10.optString(r1);	 Catch:{ Exception -> 0x0099 }
        r0.set(r11, r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
    L_0x00b2:
        r1 = move-exception;
        r0.setAccessible(r4);
        throw r1;
    L_0x00b7:
        r8 = java.lang.Long.TYPE;	 Catch:{ Exception -> 0x0099 }
        if (r7 != r8) goto L_0x00c7;
    L_0x00bb:
        r8 = r10.optLong(r1);	 Catch:{ Exception -> 0x0099 }
        r1 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x0099 }
        r0.set(r11, r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
    L_0x00c7:
        r8 = java.lang.Integer.TYPE;	 Catch:{ Exception -> 0x0099 }
        if (r7 != r8) goto L_0x00d7;
    L_0x00cb:
        r1 = r10.optInt(r1);	 Catch:{ Exception -> 0x0099 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x0099 }
        r0.set(r11, r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
    L_0x00d7:
        r8 = java.lang.Double.TYPE;	 Catch:{ Exception -> 0x0099 }
        if (r7 != r8) goto L_0x00e7;
    L_0x00db:
        r8 = r10.optDouble(r1);	 Catch:{ Exception -> 0x0099 }
        r1 = java.lang.Double.valueOf(r8);	 Catch:{ Exception -> 0x0099 }
        r0.set(r11, r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
    L_0x00e7:
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0099 }
        r7.<init>();	 Catch:{ Exception -> 0x0099 }
        r8 = "unhandeld json field:";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0099 }
        r1 = r7.append(r1);	 Catch:{ Exception -> 0x0099 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0099 }
        qsbk.app.utils.LogUtil.e(r1);	 Catch:{ Exception -> 0x0099 }
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.utils.json.JSONUtil.parseFromJSONObject(org.json.JSONObject, qsbk.app.utils.json.IJSONAble):void");
    }
}
