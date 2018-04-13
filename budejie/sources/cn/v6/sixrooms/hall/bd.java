package cn.v6.sixrooms.hall;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final class bd implements OnEditorActionListener {
    final /* synthetic */ MineFragment a;

    bd(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case 6:
                MineFragment.m(this.a);
                break;
        }
        return false;
    }
}
