package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class tr implements OnClickListener {
    final /* synthetic */ MemberManagerActivity a;

    tr(MemberManagerActivity memberManagerActivity) {
        this.a = memberManagerActivity;
    }

    public void onClick(View view) {
        this.a.r.setText("");
    }
}
