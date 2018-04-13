package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.ColorPaletteView.d;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TemplatedTextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TextStickerDrawable;
import java.util.Iterator;

public class OverlayEditTextContainer extends FrameLayout {
    private a a;
    private boolean b;
    private String c;
    private int d = 0;
    private int e = -1;
    private int f = ViewCompat.MEASURED_STATE_MASK;
    private b g = null;
    private Object h;
    private EditText i;
    private TemplateTextInputView j;
    private ColorPaletteView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextTemplateSelectView o;

    public interface a {
        void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, Object obj);
    }

    public OverlayEditTextContainer(Context context) {
        super(context);
        a(context);
    }

    public OverlayEditTextContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public OverlayEditTextContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.overlay_edit_text_container, this);
        this.i = (EditText) findViewById(R.id.edit_text);
        this.j = (TemplateTextInputView) findViewById(R.id.template_edit_text);
        this.i.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.setTextStyleBy(true);
            }
        });
        this.l = (TextView) findViewById(R.id.btnPure);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d = 0;
                this.a.setTextStyleBy(false);
            }
        });
        this.n = (TextView) findViewById(R.id.btnBackground);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d = 1;
                this.a.setTextStyleBy(false);
            }
        });
        this.m = (TextView) findViewById(R.id.btnStroke);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d = 2;
                this.a.setTextStyleBy(false);
            }
        });
        findViewById(R.id.btn_finish).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g == null) {
                    cn.htjyb.c.a.a(this.a.getContext(), this.a.i);
                } else {
                    cn.htjyb.c.a.a(this.a.getContext(), this.a.j.getEditText());
                }
            }
        });
        this.k = (ColorPaletteView) findViewById(R.id.colorPaletteView);
        this.k.setColorSelectedListener(new d(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.e = i;
                this.a.f = i2;
                this.a.setTextStyleBy(true);
            }
        });
        this.o = (TextTemplateSelectView) findViewById(R.id.textTemplateView);
        this.o.setOnTemplateSelectListener(new cn.xiaochuankeji.tieba.ui.videomaker.edittext.TextTemplateSelectView.a(this) {
            final /* synthetic */ OverlayEditTextContainer a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                this.a.a(bVar);
            }
        });
        setClickable(true);
    }

    private void a(b bVar) {
        boolean z = false;
        String obj;
        if (this.g == null) {
            obj = this.i.getText().toString();
            this.i.getText().clear();
            this.i.clearFocus();
            this.i.setVisibility(8);
            this.j.a(bVar, obj);
            this.g = bVar;
        } else {
            this.g = bVar;
            obj = this.j.getCurrStr();
            if (bVar == null) {
                this.j.a();
                this.i.setVisibility(0);
                this.i.requestFocus();
                if (!TextUtils.isEmpty(obj)) {
                    this.i.setText(obj);
                    this.i.setSelection(obj.length());
                }
            } else {
                this.j.a(bVar, obj);
            }
        }
        if (bVar != null) {
            z = true;
        }
        setStyleViewVisibilityBy(z);
    }

    private void setStyleViewVisibilityBy(boolean z) {
        if (z) {
            this.l.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.k.setVisibility(8);
            return;
        }
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.n.setVisibility(0);
        this.k.setVisibility(0);
    }

    public void setListener(a aVar) {
        this.a = aVar;
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        if (!this.b) {
            boolean z;
            this.b = true;
            if (aVar != null && (aVar instanceof TextStickerDrawable)) {
                z = true;
            } else if (aVar == null || !(aVar instanceof TemplatedTextStickerDrawable)) {
                z = false;
            } else {
                z = true;
            }
            if (true == z) {
                Spannable k = ((TextStickerDrawable) aVar).k();
                Object[] spans = k.getSpans(0, k.length(), c.class);
                if (spans != null && spans.length > 0) {
                    this.d = 1;
                }
                Object[] spans2 = k.getSpans(0, k.length(), b.class);
                if (spans2 != null && spans2.length > 0) {
                    if (1 == this.d) {
                        this.f = ((b) spans2[0]).a();
                        this.e = ((b) spans2[0]).b();
                    } else {
                        this.e = ((b) spans2[0]).a();
                        this.f = ((b) spans2[0]).b();
                        this.d = ((b) spans2[0]).c() ? 2 : 0;
                    }
                }
                this.c = k.toString();
            } else if (true == z) {
                this.c = ((TemplatedTextStickerDrawable) aVar).k().toString();
                int l = ((TemplatedTextStickerDrawable) aVar).l();
                Iterator it = e.a().iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    if (bVar.a == l) {
                        this.g = bVar;
                        break;
                    }
                }
            } else if (!z && a.a(((b) e.a().get(0)).h) != null) {
                this.g = (b) e.a().get(0);
            }
            cn.htjyb.c.a.a(this.g != null ? this.j.getEditText() : this.i);
            this.h = aVar;
        }
    }

    private void b() {
        if (this.b) {
            this.b = false;
            if (this.a != null) {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar;
                Spanned text;
                if (this.g == null) {
                    text = this.i.getText();
                    if (TextUtils.isEmpty(text)) {
                        aVar = null;
                    } else {
                        aVar = new TextStickerDrawable(getContext(), d.a(text, b.class, c.class), this.i.getTextSize(), (this.i.getWidth() - this.i.getPaddingLeft()) - this.i.getPaddingRight(), d(), e(), 0);
                    }
                } else {
                    EditText editText = this.j.getEditText();
                    text = editText.getText();
                    if (TextUtils.isEmpty(text)) {
                        aVar = null;
                    } else {
                        aVar = new TemplatedTextStickerDrawable(getContext(), d.a(text, b.class, c.class), editText.getTextSize(), d(), e(), this.g);
                    }
                }
                c();
                this.a.a(aVar, this.h);
                this.h = null;
            }
        }
    }

    private void c() {
        this.e = -1;
        this.f = ViewCompat.MEASURED_STATE_MASK;
        this.d = 0;
        this.c = null;
        this.g = null;
        this.i.getText().clear();
        this.i.setVisibility(4);
        this.j.a();
        this.o.setCurrent(null);
        this.k.a();
        this.k.setVisibility(8);
    }

    public void a(boolean z, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.topMargin = i2;
        layoutParams.bottomMargin = i;
        setLayoutParams(layoutParams);
        if (z) {
            boolean z2;
            if (this.g == null) {
                this.i.setVisibility(0);
                this.i.requestFocus();
                if (TextUtils.isEmpty(this.c)) {
                    f();
                } else {
                    this.i.getText().append(this.c);
                    setTextStyleBy(false);
                }
                this.o.a();
            } else {
                this.j.a(this.g, this.c);
                this.o.setCurrent(this.g);
            }
            if (this.g != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            setStyleViewVisibilityBy(z2);
            return;
        }
        b();
    }

    public void a() {
        this.o.b();
    }

    private float d() {
        if (this.g == null) {
            return this.i.getTextSize() * 0.3f;
        }
        return 0.0f;
    }

    private float e() {
        if (this.g == null) {
            return this.i.getTextSize() * 0.12f;
        }
        return 0.0f;
    }

    private void setTextStyleBy(boolean z) {
        if (!z) {
            f();
            if (this.d == 0) {
                this.i.setShadowLayer(2.0f, 0.0f, 4.0f, 855638016);
                e.a(this.i, 72.0f);
            } else if (1 == this.d) {
                this.i.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                e.a(this.i, 72.0f);
            } else if (2 == this.d) {
                this.i.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                e.a(this.i, 72.0f);
            }
        }
        g();
    }

    private void f() {
        Drawable drawable;
        Drawable b = c.a.d.a.a.a().b(R.drawable.img_txt_style_select);
        if (b != null) {
            b.setBounds(0, 0, b.getIntrinsicWidth(), b.getIntrinsicHeight());
        }
        Drawable b2 = c.a.d.a.a.a().b(R.drawable.record_text_set_bg_normal);
        if (b2 != null) {
            b2.setBounds(0, 0, b2.getIntrinsicWidth(), b2.getIntrinsicHeight());
        }
        Drawable b3 = c.a.d.a.a.a().b(R.drawable.record_text_set_bg_stroke);
        if (b3 != null) {
            b3.setBounds(0, 0, b3.getIntrinsicWidth(), b3.getIntrinsicHeight());
        }
        Drawable b4 = c.a.d.a.a.a().b(R.drawable.record_text_set_bg_background);
        if (b4 != null) {
            b4.setBounds(0, 0, b4.getIntrinsicWidth(), b4.getIntrinsicHeight());
        }
        this.l.setCompoundDrawables(null, b2, null, this.d == 0 ? b : null);
        TextView textView = this.m;
        if (2 == this.d) {
            drawable = b;
        } else {
            drawable = null;
        }
        textView.setCompoundDrawables(null, b3, null, drawable);
        TextView textView2 = this.n;
        if (1 != this.d) {
            b = null;
        }
        textView2.setCompoundDrawables(null, b4, null, b);
    }

    private void g() {
        boolean z = true;
        int i = 0;
        Spannable text = this.i.getText();
        d.a(text, b.class, c.class);
        if (!TextUtils.isEmpty(text)) {
            if (1 == this.d) {
                for (int i2 = 0; i2 < text.length(); i2++) {
                    if (e.a(text.charAt(i2))) {
                        text.setSpan(new b(this.f, this.e, false, null), i2, i2 + 1, 18);
                    }
                }
                text.setSpan(new c(this.e, this.i.getLayout(), d(), e()), 0, text.length(), 18);
                return;
            }
            if (2 != this.d) {
                z = false;
            }
            while (i < text.length()) {
                if (e.a(text.charAt(i))) {
                    text.setSpan(new b(this.e, this.f, z, null), i, i + 1, 18);
                }
                i++;
            }
        }
    }
}
