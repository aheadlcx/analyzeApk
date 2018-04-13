package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class be implements OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow a;

    be(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i != -1) {
            ak akVar = this.a.c;
            if (akVar != null) {
                akVar.setListSelectionHidden(false);
            }
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
