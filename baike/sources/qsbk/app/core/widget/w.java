package qsbk.app.core.widget;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import qsbk.app.core.widget.SimpleDialog.Builder;

class w implements OnCheckedChangeListener {
    final /* synthetic */ Builder a;

    w(Builder builder) {
        this.a = builder;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.a.i = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
    }
}
