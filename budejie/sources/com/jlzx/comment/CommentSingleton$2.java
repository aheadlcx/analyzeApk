package com.jlzx.comment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class CommentSingleton$2 implements OnClickListener {
    final /* synthetic */ CommentSingleton this$0;

    CommentSingleton$2(CommentSingleton commentSingleton) {
        this.this$0 = commentSingleton;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.Reject();
    }
}
