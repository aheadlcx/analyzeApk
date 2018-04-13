package qsbk.app.widget;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogFragment extends DialogFragment implements OnDateSetListener {
    private OnDateSetListener j;
    private long k = -1;
    private long l = -1;

    public DatePickerDialogFragment setDateSetListener(OnDateSetListener onDateSetListener) {
        this.j = onDateSetListener;
        return this;
    }

    public DatePickerDialogFragment setInitialTime(long j) {
        this.k = j;
        return this;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        if (this.j != null) {
            this.j.onDateSet(datePicker, i, i2, i3);
        }
    }

    public DatePickerDialogFragment setMaxDate(long j) {
        this.l = j;
        return this;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Calendar instance = Calendar.getInstance();
        if (this.k != -1) {
            instance.setTime(new Date(this.k));
        }
        Dialog bnVar = new bn(this, getActivity(), this, instance.get(1), instance.get(2), instance.get(5));
        if (this.l != -1) {
            a(bnVar, this.l);
        }
        return bnVar;
    }

    @TargetApi(11)
    private void a(DatePickerDialog datePickerDialog, long j) {
        if (VERSION.SDK_INT >= 11) {
            datePickerDialog.getDatePicker().setMaxDate(j);
        }
    }
}
