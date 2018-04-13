package com.budejie.www.adapter.a;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

class j$a extends AsyncTask<String, Void, Bitmap> {
    final /* synthetic */ j a;
    private String b;

    j$a(j jVar) {
        this.a = jVar;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    protected Bitmap a(String... strArr) {
        this.b = strArr[0];
        Bitmap a = j.a(this.a, strArr[0]);
        if (a != null) {
            this.a.a(strArr[0], a);
        }
        return a;
    }

    protected void a(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView imageView = (ImageView) j.d(this.a).findViewWithTag(this.b);
        if (!(imageView == null || bitmap == null)) {
            imageView.setImageBitmap(bitmap);
        }
        j.e(this.a).remove(this);
    }
}
