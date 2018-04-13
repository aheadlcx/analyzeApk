package qsbk.app.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import qsbk.app.R;

public class PtrFooter {
    private View a;
    private View b;
    private TextView c;
    private int d;

    public void hide() {
        this.a.setVisibility(8);
    }

    public void show() {
        this.a.setVisibility(0);
    }

    public void onInit(ListView listView) {
        View inflate = LayoutInflater.from(listView.getContext()).inflate(R.layout.layout_ptr_footer, listView, false);
        this.a = inflate.findViewById(R.id.ptr_footer_container);
        this.b = this.a.findViewById(R.id.ptr_footer_progressbar);
        this.c = (TextView) this.a.findViewById(R.id.ptr_footer_hintview);
        listView.addFooterView(inflate);
    }

    public void onStateChange(int i, String str) {
        if (this.d != 0 && i == 0) {
            hide();
        }
        if (this.d == 0 && i == 0) {
            hide();
        }
        if (this.d == 0 && i != 0) {
            show();
        }
        this.d = i;
        if (i == 1) {
            this.b.setVisibility(0);
            this.c.setVisibility(4);
        } else {
            this.b.setVisibility(4);
            this.c.setVisibility(0);
        }
        if (str == null) {
            str = "";
        }
        this.c.setText(str);
    }

    public View getContainer() {
        return this.a;
    }
}
