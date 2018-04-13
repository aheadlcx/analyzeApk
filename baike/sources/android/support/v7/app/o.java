package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

@RequiresApi(14)
class o extends AppCompatDelegateImplV9 {
    o(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    public boolean hasWindowFeature(int i) {
        return super.hasWindowFeature(i) || this.b.hasFeature(i);
    }

    View a(View view, String str, Context context, AttributeSet attributeSet) {
        return null;
    }
}
