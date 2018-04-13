package in.srain.cube.views.ptr;

import android.content.Context;
import android.util.AttributeSet;

public class PtrClassicFrameLayout extends PtrFrameLayout {
    private PtrClassicDefaultHeader c;

    public PtrClassicFrameLayout(Context context) {
        super(context);
        c();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        this.c = new PtrClassicDefaultHeader(getContext());
        setHeaderView(this.c);
        addPtrUIHandler(this.c);
    }

    public PtrClassicDefaultHeader getHeader() {
        return this.c;
    }

    public void setLastUpdateTimeKey(String str) {
        if (this.c != null) {
            this.c.setLastUpdateTimeKey(str);
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        if (this.c != null) {
            this.c.setLastUpdateTimeRelateObject(obj);
        }
    }
}
