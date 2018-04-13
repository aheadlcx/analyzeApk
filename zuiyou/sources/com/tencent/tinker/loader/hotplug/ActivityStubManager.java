package com.tencent.tinker.loader.hotplug;

import java.util.HashMap;
import java.util.Map;

public class ActivityStubManager {
    private static final int[] NEXT_SINGLEINSTANCE_STUB_IDX_SLOTS = new int[]{0, 0};
    private static final int[] NEXT_SINGLETASK_STUB_IDX_SLOTS = new int[]{0, 0};
    private static final int[] NEXT_SINGLETOP_STUB_IDX_SLOTS = new int[]{0, 0};
    private static final int[] NEXT_STANDARD_STUB_IDX_SLOTS = new int[]{0, 0};
    private static final int NOTRANSPARENT_SLOT_INDEX = 0;
    private static final int[] SINGLEINSTANCE_STUB_COUNT_SLOTS = new int[]{10, 3};
    private static final int[] SINGLETASK_STUB_COUNT_SLOTS = new int[]{10, 3};
    private static final int[] SINGLETOP_STUB_COUNT_SLOTS = new int[]{10, 3};
    private static final int[] STANDARD_STUB_COUNT_SLOTS = new int[]{10, 3};
    private static final String TAG = "Tinker.ActivityStubManager";
    private static final int TRANSPARENT_SLOT_INDEX = 1;
    private static Map<String, String> sTargetToStubClassNameMap = new HashMap();

    public static String assignStub(String str, int i, boolean z) {
        String str2 = (String) sTargetToStubClassNameMap.get(str);
        if (str2 != null) {
            return str2;
        }
        int[] iArr;
        int[] iArr2;
        String str3;
        int i2;
        String str4;
        int[] iArr3;
        switch (i) {
            case 1:
                str4 = ActivityStubs.c;
                iArr3 = NEXT_SINGLETOP_STUB_IDX_SLOTS;
                iArr = SINGLETOP_STUB_COUNT_SLOTS;
                iArr2 = iArr3;
                str2 = str4;
                break;
            case 2:
                str4 = ActivityStubs.d;
                iArr3 = NEXT_SINGLETASK_STUB_IDX_SLOTS;
                iArr = SINGLETASK_STUB_COUNT_SLOTS;
                iArr2 = iArr3;
                str2 = str4;
                break;
            case 3:
                str4 = ActivityStubs.e;
                iArr3 = NEXT_SINGLEINSTANCE_STUB_IDX_SLOTS;
                iArr = SINGLEINSTANCE_STUB_COUNT_SLOTS;
                iArr2 = iArr3;
                str2 = str4;
                break;
            default:
                str4 = ActivityStubs.b;
                iArr3 = NEXT_STANDARD_STUB_IDX_SLOTS;
                iArr = STANDARD_STUB_COUNT_SLOTS;
                iArr2 = iArr3;
                str2 = str4;
                break;
        }
        if (z) {
            str3 = str2 + "_T";
            i2 = 1;
        } else {
            str3 = str2;
            i2 = 0;
        }
        int i3 = iArr2[i2];
        iArr2[i2] = i3 + 1;
        if (i3 >= iArr[i2]) {
            iArr2[i2] = 0;
            i2 = 0;
        } else {
            i2 = i3;
        }
        str2 = String.format(str3, new Object[]{Integer.valueOf(i2)});
        sTargetToStubClassNameMap.put(str, str2);
        return str2;
    }

    private ActivityStubManager() {
        throw new UnsupportedOperationException();
    }
}
