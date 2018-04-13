package cn.v6.sixrooms.room.dialog;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final class b implements OnEditorActionListener {
    final /* synthetic */ InputSongDialog a;

    b(InputSongDialog inputSongDialog) {
        this.a = inputSongDialog;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            this.a.a();
        }
        return false;
    }
}
