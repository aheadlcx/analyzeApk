package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.widget.BaseCell;

public class UnknownCell extends BaseCell {

    public static class EmptyView extends View {
        public EmptyView(Context context) {
            super(context);
        }

        public EmptyView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public EmptyView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(0, 1073741824));
        }
    }

    public void onCreate() {
        setCellView(new EmptyView(getContext()));
    }

    public void onUpdate() {
    }
}
