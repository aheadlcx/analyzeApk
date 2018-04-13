package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class id implements OnClickListener {
    final /* synthetic */ String[] a;
    final /* synthetic */ CircleTopicActivity b;

    id(CircleTopicActivity circleTopicActivity, String[] strArr) {
        this.b = circleTopicActivity;
        this.a = strArr;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        new Builder(this.b).setTitle("举报话题").setMessage("确定举报话题后，将看不到此话题").setPositiveButton(17039370, new ie(this, i)).setNegativeButton(17039360, null).create().show();
    }
}
