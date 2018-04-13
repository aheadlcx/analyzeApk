package qsbk.app.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;

public class TipsHelper {
    private View a;
    private ImageView b;
    private TextView c;

    public TipsHelper(View view) {
        this.a = view;
        this.b = (ImageView) view.findViewById(R.id.tips_img);
        this.c = (TextView) view.findViewById(R.id.tips_text);
    }

    public TipsHelper(View view, boolean z) {
        this.a = view;
        this.b = (ImageView) view.findViewById(R.id.cmt_empty_tips_img);
        this.c = (TextView) view.findViewById(R.id.cmt_empty_tips_text);
    }

    public void set(int i, String str) {
        this.b.setImageResource(i);
        this.c.setText(str);
    }

    public void show() {
        this.a.setVisibility(0);
    }

    public void hide() {
        this.a.setVisibility(8);
    }

    public View getRoot() {
        return this.a;
    }
}
