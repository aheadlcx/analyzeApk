package com.budejie.www.activity.video;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public class l {
    private static HashMap<Context, l> a = new HashMap();
    private ArrayList<Object> b = new ArrayList();

    private l() {
    }

    public static void a(Context context) {
        l lVar = (l) a.get(context);
        a.remove(context);
    }
}
