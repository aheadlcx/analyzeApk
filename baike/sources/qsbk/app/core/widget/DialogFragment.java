package qsbk.app.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View.OnClickListener;
import qsbk.app.core.R;

public class DialogFragment extends android.support.v4.app.DialogFragment {
    protected qsbk.app.core.widget.SimpleDialog.Builder j;
    private OnClickListener k = new b(this);

    public interface Builder {
        Dialog build(Context context);

        void onCancel(DialogInterface dialogInterface);

        void onDismiss(DialogInterface dialogInterface);

        void onNegativeActionClicked(DialogFragment dialogFragment);

        void onNeutralActionClicked(DialogFragment dialogFragment);

        void onPositiveActionClicked(DialogFragment dialogFragment);
    }

    public static DialogFragment newInstance(qsbk.app.core.widget.SimpleDialog.Builder builder) {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.j = builder;
        return dialogFragment;
    }

    public SimpleDialog onCreateDialog(Bundle bundle) {
        if (this.j == null) {
            this.j = new qsbk.app.core.widget.SimpleDialog.Builder(getActivity());
        }
        SimpleDialog build = this.j.build(getActivity());
        build.setPositiveListener(this.k);
        build.setNegativeListener(this.k);
        return build;
    }

    public void onStart() {
        super.onStart();
        boolean z = this.j == null || this.j.getStyleId() != R.style.SimpleDialog_Fullscreen;
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(z);
            dialog.setCanceledOnTouchOutside(z);
        }
    }
}
