package com.meizu.cloud.pushsdk.base;

import com.meizu.cloud.pushsdk.base.reflect.ReflectClass;
import com.meizu.cloud.pushsdk.base.reflect.ReflectResult;

public class BuildExt {
    private static String CLASS_NAME = "android.os.BuildExt";

    public static ReflectResult<Boolean> isInternational() {
        return ReflectClass.forName(CLASS_NAME).method("isProductInternational", new Class[0]).invokeStatic(new Object[0]);
    }

    public static ReflectResult<Boolean> isRom() {
        return ReflectClass.forName(CLASS_NAME).method("isFlymeRom", new Class[0]).invokeStatic(new Object[0]);
    }

    public static ReflectResult<String> getModel() {
        return ReflectClass.forName(CLASS_NAME).field("MZ_MODEL").getStatic();
    }
}
