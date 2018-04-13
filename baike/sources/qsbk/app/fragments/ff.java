package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class ff implements OnItemClickListener {
    final /* synthetic */ ManageQiushiAndDynamicFragment a;

    ff(ManageQiushiAndDynamicFragment manageQiushiAndDynamicFragment) {
        this.a = manageQiushiAndDynamicFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a aVar = (a) this.a.c.get(i);
        if (aVar != null) {
            switch (aVar.h) {
                case 1:
                    this.a.c();
                    return;
                case 2:
                    this.a.a(true);
                    return;
                case 3:
                    this.a.a(false);
                    return;
                case 4:
                    this.a.a(view.getContext());
                    return;
                default:
                    return;
            }
        }
    }
}
