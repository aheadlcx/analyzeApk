package qsbk.app.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.R;

public class ReportCallCard {
    public static final ReportCallCard INSTANCE = new ReportCallCard();

    public static View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        if (view == null) {
            return layoutInflater.inflate(R.layout.layout_report_call, null);
        }
        return view;
    }
}
