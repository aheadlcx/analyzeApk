package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import qsbk.app.R;

class t implements OnClickListener {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void onClick(View view) {
        int i = this.a.b;
        this.a.b = this.a.a.indexOfChild(view);
        if (this.a.b != i) {
            TextView textView = (TextView) this.a.a.getChildAt(i);
            textView.setBackgroundColor(0);
            textView.setTextColor(-12894910);
            TextView textView2 = (TextView) view;
            textView2.setBackgroundResource(R.drawable.member_mute_time_bg);
            textView2.setTextColor(-1);
        }
    }
}
