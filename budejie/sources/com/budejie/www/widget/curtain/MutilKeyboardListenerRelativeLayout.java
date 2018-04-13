package com.budejie.www.widget.curtain;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.e.b;
import java.util.ArrayList;
import java.util.List;

public class MutilKeyboardListenerRelativeLayout extends RelativeLayout {
    List<MutilKeyboardListenerRelativeLayout$b> a = new ArrayList();
    int b = 0;
    private boolean c;
    private boolean d;
    private int e = b.a(BudejieApplication.g, 100.0f);

    public boolean getKeyBoardState() {
        return this.c;
    }

    public void b() {
        this.c = false;
        this.d = false;
    }

    public boolean getFocusState() {
        return this.d;
    }

    public MutilKeyboardListenerRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && Math.abs(i2 - i4) >= this.e) {
            boolean z = i2 - i4 <= 0;
            for (MutilKeyboardListenerRelativeLayout$b mutilKeyboardListenerRelativeLayout$b : this.a) {
                MutilKeyboardListenerRelativeLayout$a a = MutilKeyboardListenerRelativeLayout$b.a(mutilKeyboardListenerRelativeLayout$b);
                if (!(a == null || z == this.c)) {
                    a.a(z);
                }
                this.c = z;
                if (!z) {
                    MutilKeyboardListenerRelativeLayout$b.b(mutilKeyboardListenerRelativeLayout$b).clearFocus();
                }
            }
        }
    }

    public void a(View view, EditText editText, MutilKeyboardListenerRelativeLayout$a mutilKeyboardListenerRelativeLayout$a) {
        boolean z = false;
        if (mutilKeyboardListenerRelativeLayout$a != null) {
            this.a.add(0, new MutilKeyboardListenerRelativeLayout$b(this, mutilKeyboardListenerRelativeLayout$a, editText));
        } else {
            this.a.add(new MutilKeyboardListenerRelativeLayout$b(this, mutilKeyboardListenerRelativeLayout$a, editText));
        }
        if (mutilKeyboardListenerRelativeLayout$a != null) {
            z = true;
        }
        editText.setOnFocusChangeListener(new MutilKeyboardListenerRelativeLayout$c(this, view, z));
    }
}
