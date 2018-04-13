package com.budejie.www.util;

import android.os.AsyncTask;
import android.view.MotionEvent;

class al$a extends AsyncTask {
    final /* synthetic */ al a;

    al$a(al alVar) {
        this.a = alVar;
    }

    protected void onPreExecute() {
        if (al.g(this.a) != null) {
            al.g(this.a).a();
        }
    }

    protected Object doInBackground(Object[] objArr) {
        try {
            return al.a(this.a, (MotionEvent) objArr[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void onPostExecute(Object obj) {
        String str = (String) obj;
        if (al.g(this.a) != null) {
            al.g(this.a).a(str);
        }
    }
}
