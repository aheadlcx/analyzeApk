package com.budejie.www.e;

import android.content.Context;

public class b {
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
