package qsbk.app.im;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class al implements OnItemClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ String[] b;
    final /* synthetic */ String[] c;
    final /* synthetic */ String[] d;
    final /* synthetic */ ChatMsg e;
    final /* synthetic */ z f;

    al(z zVar, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, ChatMsg chatMsg) {
        this.f = zVar;
        this.a = strArr;
        this.b = strArr2;
        this.c = strArr3;
        this.d = strArr4;
        this.e = chatMsg;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f.a.a(this.a[i], this.b[i], this.c[i], this.d[i], this.e.time);
    }
}
