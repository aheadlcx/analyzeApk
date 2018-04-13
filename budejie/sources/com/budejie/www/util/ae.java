package com.budejie.www.util;

import android.content.Context;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;

public class ae {
    public static void a(BudejieApplication budejieApplication, Context context) {
        if (budejieApplication != null && context != null) {
            if (budejieApplication.a() == null || budejieApplication.a() == Status.end) {
                ac a = ac.a(context);
                if (a.c()) {
                    a.i();
                }
            }
        }
    }
}
