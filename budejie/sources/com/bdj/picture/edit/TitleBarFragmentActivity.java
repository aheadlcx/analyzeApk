package com.bdj.picture.edit;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.bdj.picture.edit.a.d;

public class TitleBarFragmentActivity extends FragmentActivity {
    private boolean a;
    private int[] b;
    private int c;
    private OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ TitleBarFragmentActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == d.titlebarBack) {
                this.a.c();
            } else {
                this.a.d();
            }
        }
    };

    protected void onStart() {
        super.onStart();
        View findViewById = findViewById(d.title_bar);
        if (findViewById != null && this.b != null) {
            TextView textView = (TextView) findViewById.findViewById(d.titlebarBack);
            textView.setText(this.b[0]);
            textView.setOnClickListener(this.d);
            if (this.c != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(this.c, 0, 0, 0);
                textView.setCompoundDrawablePadding(com.bdj.picture.edit.util.d.a(this, 5.0f));
            }
            ((TextView) findViewById.findViewById(d.titlebarText)).setText(this.b[1]);
            textView = (TextView) findViewById.findViewById(d.titlebarAction);
            if (this.b[2] != 0) {
                if (!this.a) {
                    textView.setText(this.b[2]);
                    textView.setBackgroundColor(0);
                }
                textView.setOnClickListener(this.d);
                return;
            }
            textView.setVisibility(8);
        }
    }

    protected void a(int i, int i2, int i3, int i4, boolean z) {
        this.b = new int[]{i, i2, i3};
        this.c = i4;
        this.a = z;
    }

    protected void c() {
        finish();
    }

    protected void d() {
    }
}
