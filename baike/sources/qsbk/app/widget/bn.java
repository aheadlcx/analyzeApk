package qsbk.app.widget;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;

class bn extends DatePickerDialog {
    final /* synthetic */ DatePickerDialogFragment a;

    bn(DatePickerDialogFragment datePickerDialogFragment, Context context, OnDateSetListener onDateSetListener, int i, int i2, int i3) {
        this.a = datePickerDialogFragment;
        super(context, onDateSetListener, i, i2, i3);
    }

    protected void onStop() {
    }
}
