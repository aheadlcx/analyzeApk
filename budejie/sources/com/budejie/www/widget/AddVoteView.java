package com.budejie.www.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import java.util.ArrayList;
import java.util.Iterator;

public class AddVoteView extends RelativeLayout implements OnClickListener {
    private final String a;
    private String[] b;
    private String[] c;
    private Context d;
    private int e;
    private int f;
    private LinearLayout g;
    private LinearLayout h;
    private a i;
    private ArrayList<EditText> j;
    private TextWatcher k;

    public interface a {
        void a(Boolean bool);
    }

    private void a() {
        int i;
        Iterator it = this.j.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((EditText) it.next()).getText().length() > 0) {
                i = i2 + 1;
                if (i == this.f) {
                    break;
                }
            } else {
                i = i2;
            }
            i2 = i;
        }
        i = i2;
        if (i == this.f) {
            this.i.a(Boolean.valueOf(true));
        } else {
            this.i.a(Boolean.valueOf(false));
        }
    }

    public AddVoteView(Context context) {
        super(context);
        this.a = "AddVoteView";
        this.b = new String[]{"一", "二", "三", "四", "五", "六"};
        this.c = new String[]{"A", "B", "C", "D", "E", "F"};
        this.e = 4;
        this.f = 2;
        this.j = new ArrayList();
        this.k = new AddVoteView$1(this);
        a(context);
    }

    public AddVoteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = "AddVoteView";
        this.b = new String[]{"一", "二", "三", "四", "五", "六"};
        this.c = new String[]{"A", "B", "C", "D", "E", "F"};
        this.e = 4;
        this.f = 2;
        this.j = new ArrayList();
        this.k = new AddVoteView$1(this);
        a(context);
    }

    public AddVoteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "AddVoteView";
        this.b = new String[]{"一", "二", "三", "四", "五", "六"};
        this.c = new String[]{"A", "B", "C", "D", "E", "F"};
        this.e = 4;
        this.f = 2;
        this.j = new ArrayList();
        this.k = new AddVoteView$1(this);
        a(context);
    }

    private void a(Context context) {
        this.d = context;
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.view_add_vote, null);
        this.g = (LinearLayout) inflate.findViewById(R.id.vote_container);
        this.h = (LinearLayout) inflate.findViewById(R.id.add_item_layout);
        this.h.setOnClickListener(this);
        addView(inflate, new LayoutParams(-1, -2));
    }

    public void setInitData(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            for (i = 0; i < this.f; i++) {
                a(false);
            }
        } else {
            String[] split = str.split(",");
            i = 0;
            while (i < split.length && i < this.e) {
                a(split[i], false);
                i++;
            }
        }
        a();
    }

    private void a(boolean z) {
        a("", z);
    }

    private void a(String str, boolean z) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.d).inflate(R.layout.item_add_vote, null);
        if (this.g != null) {
            EditText editText = (EditText) relativeLayout.findViewById(R.id.item_edit);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.item_right_cancel);
            editText.addTextChangedListener(this.k);
            editText.setText(str);
            if (z) {
                editText.requestFocus();
            }
            imageView.setTag(relativeLayout);
            imageView.setOnClickListener(this);
            this.g.addView(relativeLayout);
            b();
        }
    }

    private void b() {
        if (this.g != null && this.g.getChildCount() > 0) {
            if (this.g.getChildCount() > this.f) {
                setHintAndCancel(true);
            } else {
                setHintAndCancel(false);
            }
            if (this.g.getChildCount() == this.e) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
            }
        }
    }

    private void setHintAndCancel(boolean z) {
        this.j.clear();
        for (int i = 0; i < this.g.getChildCount(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout) this.g.getChildAt(i);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.item_right_cancel);
            EditText editText = (EditText) relativeLayout.findViewById(R.id.item_edit);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.item_left_icon);
            this.j.add(editText);
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            editText.setHint(this.d.getString(R.string.add_vote_item_edit_hint, new Object[]{this.b[i]}));
            textView.setText(this.c[i]);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_right_cancel:
                this.g.removeView((RelativeLayout) view.getTag());
                b();
                a();
                return;
            case R.id.add_item_layout:
                a(true);
                return;
            default:
                return;
        }
    }

    public String getVoteResult() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            EditText editText = (EditText) it.next();
            if (editText.getText().length() > 0) {
                stringBuilder.append(",").append(editText.getText().toString().replace("\n", ""));
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.substring(1, stringBuilder.length());
        }
        return "";
    }

    public void setEnableListener(a aVar) {
        this.i = aVar;
    }
}
