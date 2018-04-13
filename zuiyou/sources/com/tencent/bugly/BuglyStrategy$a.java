package com.tencent.bugly;

import java.util.Map;

public class BuglyStrategy$a {
    public static final int CRASHTYPE_ANR = 4;
    public static final int CRASHTYPE_BLOCK = 7;
    public static final int CRASHTYPE_COCOS2DX_JS = 5;
    public static final int CRASHTYPE_COCOS2DX_LUA = 6;
    public static final int CRASHTYPE_JAVA_CATCH = 1;
    public static final int CRASHTYPE_JAVA_CRASH = 0;
    public static final int CRASHTYPE_NATIVE = 2;
    public static final int CRASHTYPE_U3D = 3;
    public static final int MAX_USERDATA_KEY_LENGTH = 100;
    public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

    public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
        return null;
    }

    public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
        return null;
    }
}
