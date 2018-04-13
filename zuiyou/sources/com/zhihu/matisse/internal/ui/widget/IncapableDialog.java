package com.zhihu.matisse.internal.ui.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import com.zhihu.matisse.R;

public class IncapableDialog extends DialogFragment {
    public static final String EXTRA_MESSAGE = "extra_message";
    public static final String EXTRA_TITLE = "extra_title";

    public static IncapableDialog newInstance(String str, String str2) {
        IncapableDialog incapableDialog = new IncapableDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, str);
        bundle.putString(EXTRA_MESSAGE, str2);
        incapableDialog.setArguments(bundle);
        return incapableDialog;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence string = getArguments().getString(EXTRA_TITLE);
        CharSequence string2 = getArguments().getString(EXTRA_MESSAGE);
        Builder builder = new Builder(getActivity());
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage(string2);
        }
        builder.setPositiveButton(R.string.button_ok, new IncapableDialog$1(this));
        return builder.create();
    }
}
