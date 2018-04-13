package com.bdj.picture.edit.util;

public class j {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.bdj.picture.edit.bean.d> a(android.content.Context r8, int r9) {
        /*
        r0 = 0;
        r6 = 1;
        r1 = r8.getResources();
        r3 = r1.getXml(r9);
        if (r3 == 0) goto L_0x0122;
    L_0x000c:
        r1 = r3.getEventType();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r2 = r1;
        r1 = r0;
    L_0x0012:
        if (r2 == r6) goto L_0x0122;
    L_0x0014:
        switch(r2) {
            case 0: goto L_0x0022;
            case 1: goto L_0x0017;
            case 2: goto L_0x002a;
            case 3: goto L_0x0123;
            default: goto L_0x0017;
        };
    L_0x0017:
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x001a:
        r2 = r3.next();	 Catch:{ XmlPullParserException -> 0x0141, IOException -> 0x013c }
        r7 = r0;
        r0 = r1;
        r1 = r7;
        goto L_0x0012;
    L_0x0022:
        r2 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r2.<init>();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r0 = r1;
        r1 = r2;
        goto L_0x001a;
    L_0x002a:
        r2 = r3.getName();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r4 = "material";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x003b;
    L_0x0036:
        r1 = new com.bdj.picture.edit.bean.d;	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.<init>();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x003b:
        r4 = "id";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x004a;
    L_0x0043:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.a(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x004a:
        r4 = "type";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x005d;
    L_0x0052:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.a(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x005d:
        r4 = "name";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x006c;
    L_0x0065:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.b(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x006c:
        r4 = "filter";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x007b;
    L_0x0074:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.h(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x007b:
        r4 = "preview_resourcename";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x008a;
    L_0x0083:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.c(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x008a:
        r4 = "resourcename";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x0099;
    L_0x0092:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.d(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x0099:
        r4 = "format";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x00a8;
    L_0x00a1:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.e(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x00a8:
        r4 = "padding";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x00e8;
    L_0x00b0:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x0115 }
        if (r5 != 0) goto L_0x00e8;
    L_0x00ba:
        r5 = ",";
        r4 = r4.split(r5);	 Catch:{ Exception -> 0x0115 }
        r5 = 0;
        r5 = r4[r5];	 Catch:{ Exception -> 0x0115 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Exception -> 0x0115 }
        r1.b(r5);	 Catch:{ Exception -> 0x0115 }
        r5 = 1;
        r5 = r4[r5];	 Catch:{ Exception -> 0x0115 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Exception -> 0x0115 }
        r1.c(r5);	 Catch:{ Exception -> 0x0115 }
        r5 = 2;
        r5 = r4[r5];	 Catch:{ Exception -> 0x0115 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Exception -> 0x0115 }
        r1.d(r5);	 Catch:{ Exception -> 0x0115 }
        r5 = 3;
        r4 = r4[r5];	 Catch:{ Exception -> 0x0115 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x0115 }
        r1.e(r4);	 Catch:{ Exception -> 0x0115 }
    L_0x00e8:
        r4 = "size";
        r4 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r4 == 0) goto L_0x0101;
    L_0x00f0:
        r4 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r5 != 0) goto L_0x0101;
    L_0x00fa:
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.f(r4);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
    L_0x0101:
        r4 = "color";
        r2 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r2 == 0) goto L_0x0017;
    L_0x0109:
        r2 = r3.nextText();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r1.f(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x001a;
    L_0x0115:
        r4 = move-exception;
        r4 = "XmlParseUtil";
        r5 = "padding parse exception";
        android.util.Log.e(r4, r5);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        goto L_0x00e8;
    L_0x011e:
        r1 = move-exception;
    L_0x011f:
        r1.printStackTrace();
    L_0x0122:
        return r0;
    L_0x0123:
        r2 = r3.getName();	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r4 = "material";
        r2 = r4.equals(r2);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        if (r2 == 0) goto L_0x0017;
    L_0x012f:
        r0.add(r1);	 Catch:{ XmlPullParserException -> 0x011e, IOException -> 0x0137 }
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x001a;
    L_0x0137:
        r1 = move-exception;
    L_0x0138:
        r1.printStackTrace();
        goto L_0x0122;
    L_0x013c:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
        goto L_0x0138;
    L_0x0141:
        r0 = move-exception;
        r7 = r0;
        r0 = r1;
        r1 = r7;
        goto L_0x011f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bdj.picture.edit.util.j.a(android.content.Context, int):java.util.List<com.bdj.picture.edit.bean.d>");
    }
}
