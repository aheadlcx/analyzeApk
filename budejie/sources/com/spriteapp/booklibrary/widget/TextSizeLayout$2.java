package com.spriteapp.booklibrary.widget;

import android.view.View;
import android.view.View.OnClickListener;

class TextSizeLayout$2 implements OnClickListener {
    final /* synthetic */ TextSizeLayout this$0;

    TextSizeLayout$2(TextSizeLayout textSizeLayout) {
        this.this$0 = textSizeLayout;
    }

    public void onClick(View view) {
        TextSizeLayout.access$002(this.this$0, TextSizeLayout.access$100(this.this$0).plusTextSize());
        if (TextSizeLayout.access$200(this.this$0) != null) {
            TextSizeLayout.access$200(this.this$0).sendTextSize(TextSizeLayout.access$000(this.this$0));
        }
    }
}
