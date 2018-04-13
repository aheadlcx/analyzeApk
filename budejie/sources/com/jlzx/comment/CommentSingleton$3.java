package com.jlzx.comment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class CommentSingleton$3 implements OnCancelListener {
    final /* synthetic */ CommentSingleton this$0;

    CommentSingleton$3(CommentSingleton commentSingleton) {
        this.this$0 = commentSingleton;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.this$0.Reject();
    }
}
