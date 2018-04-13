package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.BaseUserInfo;

class tn implements OnClickListener {
    final /* synthetic */ int[] a;
    final /* synthetic */ BaseUserInfo b;
    final /* synthetic */ MemberManagerActivity c;

    tn(MemberManagerActivity memberManagerActivity, int[] iArr, BaseUserInfo baseUserInfo) {
        this.c = memberManagerActivity;
        this.a = iArr;
        this.b = baseUserInfo;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (this.a[i]) {
            case 1:
                this.c.d(this.b.userId);
                return;
            case 2:
                this.c.a(this.b);
                return;
            case 3:
                this.c.d(this.b.userId, this.b.userName);
                return;
            case 4:
                this.c.b(this.b.userId, this.b.userName);
                return;
            case 5:
                this.c.c(this.b.userId, this.b.userName);
                return;
            default:
                return;
        }
    }
}
