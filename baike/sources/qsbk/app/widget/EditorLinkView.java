package qsbk.app.widget;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.utils.UIHelper;

public class EditorLinkView extends LinearLayout {
    private Map<String, String> a;

    public EditorLinkView(Context context) {
        this(context, null);
    }

    public EditorLinkView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EditorLinkView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new HashMap();
    }

    public void setLink(Map<String, String> map) {
        this.a = map;
        a();
    }

    private void a() {
        removeViews(0, getChildCount());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        for (String str : this.a.keySet()) {
            if (getChildCount() != 0) {
                layoutParams.topMargin = UIHelper.dip2px(getContext(), 20.0f);
            }
            View b = b();
            b.setText(Html.fromHtml("<u>" + str + "</u>"));
            b.setOnClickListener(new br(this, str));
            addView(b, layoutParams);
        }
    }

    private TextView b() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(13.0f);
        textView.setTextColor(-10248992);
        return textView;
    }
}
