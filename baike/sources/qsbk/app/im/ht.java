package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NearByActivity;
import qsbk.app.activity.NearByGroupActivity;

class ht implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    ht(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public void onClick(View view) {
        switch (this.a) {
            case 0:
                this.b.a.h();
                break;
            case 1:
                this.b.a.j();
                break;
            case 2:
                this.b.a.i();
                break;
            case 3:
                NearByGroupActivity.launch(this.b.a.getActivity());
                break;
            case 4:
                NearByActivity.launch(this.b.a.getActivity());
                break;
            case 5:
                this.b.a.showDialog();
                break;
        }
        if (this.b.a.c != null) {
            this.b.a.c.dismiss();
        }
    }
}
