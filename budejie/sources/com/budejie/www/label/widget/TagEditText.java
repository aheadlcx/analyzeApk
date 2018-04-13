package com.budejie.www.label.widget;

import android.app.Activity;
import android.content.Context;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import com.budejie.www.R;
import com.budejie.www.activity.label.LabelBean;
import com.budejie.www.util.ar;
import java.util.ArrayList;
import java.util.Iterator;

public class TagEditText extends AutoLineLinearLayout {
    private EditText a;
    private TextWatcher b;
    private Activity c;
    private ArrayList<LabelBean> d;

    public TagEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = (Activity) context;
        c();
    }

    public TagEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = (Activity) context;
        c();
    }

    private void c() {
        this.d = new ArrayList();
    }

    public void a(TextWatcher textWatcher, OnKeyListener onKeyListener) {
        this.b = textWatcher;
        d();
        this.a.addTextChangedListener(textWatcher);
        this.a.setOnKeyListener(onKeyListener);
        addView(this.a);
    }

    public EditText getEditText() {
        return this.a;
    }

    private void d() {
        this.a = (EditText) this.c.getLayoutInflater().inflate(R.layout.select_label_edit_text, null);
        this.a.setFilters(new InputFilter[]{new LengthFilter(20)});
        this.a.setBackgroundResource(R.drawable.select_label_edit_bg);
    }

    public String getText() {
        if (this.a != null) {
            return this.a.getText().toString();
        }
        return "";
    }

    public void a() {
        if (this.a != null) {
            this.a.setText("");
        }
    }

    public void a(LabelBean labelBean) {
        if (this.d.size() == 0 && this.a != null) {
            this.a.setHint("");
        }
        this.d.add(labelBean);
    }

    public void b(LabelBean labelBean) {
        this.d.remove(this.d.indexOf(labelBean));
        if (this.d.size() == 0 && this.a != null) {
            this.a.setHint("输入标签，标签打得好，精华上的早");
        }
    }

    public void addView(View view, int i) {
        if (view != null) {
            LabelBean labelBean = (LabelBean) view.getTag(R.id.tag_key);
            if (labelBean != null) {
                a(labelBean);
            }
        }
        super.addView(view, i);
    }

    public void removeView(View view) {
        if (view != null) {
            b((LabelBean) view.getTag(R.id.tag_key));
        }
        super.removeView(view);
    }

    public String getSelectTagId() {
        if (this.d == null || this.d.size() <= 0) {
            return "";
        }
        String str = "";
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            String str2;
            LabelBean labelBean = (LabelBean) it.next();
            if (labelBean != null) {
                str2 = str + labelBean.getTheme_id() + ",";
            } else {
                str2 = str;
            }
            str = str2;
        }
        return str.substring(0, str.length() - 1);
    }

    public String getSelectTagName() {
        if (this.d == null || this.d.size() <= 0) {
            return "";
        }
        String str = "";
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            String str2;
            LabelBean labelBean = (LabelBean) it.next();
            if (labelBean != null) {
                str2 = str + labelBean.getTheme_name() + ",";
            } else {
                str2 = str;
            }
            str = str2;
        }
        return str.substring(0, str.length() - 1);
    }

    public String getSelectTagType() {
        if (this.d == null || this.d.size() <= 0) {
            return "";
        }
        String str = "";
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            String str2;
            LabelBean labelBean = (LabelBean) it.next();
            if (labelBean != null) {
                str2 = str + labelBean.getTheme_type() + ",";
            } else {
                str2 = str;
            }
            str = str2;
        }
        return str.substring(0, str.length() - 1);
    }

    public boolean a(String str) {
        if (this.d != null) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                if (str.equals(((LabelBean) it.next()).getTheme_name())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getLabelSize() {
        if (this.d != null) {
            return this.d.size();
        }
        return -1;
    }

    public void b() {
        if (this.a != null) {
            Object a = ar.a(this.a.getText().toString(), 20);
            this.a.setText(a);
            this.a.setSelection(a.length());
        }
    }
}
