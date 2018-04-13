package cn.xiaochuankeji.tieba.widget.rich;

import android.animation.LayoutTransition;
import android.animation.LayoutTransition.TransitionListener;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import com.izuiyou.a.a.b;
import java.util.ArrayList;

public class RichTextEditor extends LinearLayout implements OnFocusChangeListener {
    private int a;
    private OnKeyListener b;
    private OnClickListener c;
    private EditText d;
    private LayoutTransition e;

    public static class a implements Parcelable {
        public static final Creator<a> CREATOR = new Creator<a>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public a a(Parcel parcel) {
                return new a(parcel);
            }

            public a[] a(int i) {
                return new a[i];
            }
        };
        public String a;
        public String b;
        public long c;
        public int d;
        public int e;
        public String f;
        public String g;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
            parcel.writeLong(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
        }

        protected a(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readLong();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readString();
            this.g = parcel.readString();
        }
    }

    public RichTextEditor(Context context) {
        this(context, null);
    }

    public RichTextEditor(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RichTextEditor(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 1;
        this.b = new OnKeyListener(this) {
            final /* synthetic */ RichTextEditor a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((view instanceof EditText) && keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 67) {
                    this.a.a((EditText) view);
                }
                return false;
            }
        };
        this.c = new OnClickListener(this) {
            final /* synthetic */ RichTextEditor a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Log.i("imageClose", "imageCloseListener");
                this.a.a(view, 1);
            }
        };
        setOrientation(1);
        removeAllViews();
        this.a = 1;
        c();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        View a = a("分享与主题相关的故事...");
        this.d = a;
        addView(a, layoutParams);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RichTextEditor a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                for (int childCount = this.a.getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = this.a.getChildAt(childCount);
                    if (childAt instanceof DelegateEditText) {
                        this.a.b((EditText) childAt);
                        return;
                    }
                }
            }
        });
    }

    private void a(EditText editText) {
        if (editText.getSelectionStart() == 0) {
            View childAt = getChildAt(indexOfChild(editText) - 1);
            if (childAt == null) {
                return;
            }
            if (childAt instanceof a) {
                a(childAt, 0);
            } else if (childAt instanceof DelegateEditText) {
                String obj = editText.getText().toString();
                DelegateEditText delegateEditText = (DelegateEditText) childAt;
                String obj2 = delegateEditText.getText().toString();
                setLayoutTransition(null);
                removeView(editText);
                setLayoutTransition(this.e);
                delegateEditText.setText(String.format("%s%s", new Object[]{obj2, obj}));
                delegateEditText.requestFocus();
                delegateEditText.setSelection(obj2.length());
                this.d = delegateEditText;
            }
        }
    }

    private void a(View view, int i) {
        Log.i("imageClose", "onImageCloseClick");
        if (!this.e.isRunning()) {
            View childAt;
            Object tag = view.getTag();
            Log.i("imageClose", "tag:" + tag);
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt2 = getChildAt(i2);
                if (childAt2.getTag() == tag) {
                    i2 = indexOfChild(childAt2);
                    setLayoutTransition(null);
                    removeView(childAt2);
                    setLayoutTransition(this.e);
                    if (i2 > 0) {
                        View childAt3 = getChildAt(i2 - 1);
                        View childAt4 = getChildAt(i2);
                        if ((childAt3 instanceof DelegateEditText) && (childAt4 instanceof DelegateEditText)) {
                            a((EditText) childAt4);
                        }
                    }
                    if (getChildCount() == 1) {
                        childAt = getChildAt(0);
                        if (childAt instanceof EditText) {
                            ((EditText) childAt).setHint("分享与主题相关的故事...");
                            cn.htjyb.c.a.a(childAt);
                            ((EditText) childAt).setSelection(((EditText) childAt).getText().toString().length());
                        }
                    }
                }
            }
            if (getChildCount() == 1) {
                childAt = getChildAt(0);
                if (childAt instanceof EditText) {
                    ((EditText) childAt).setHint("分享与主题相关的故事...");
                    cn.htjyb.c.a.a(childAt);
                    ((EditText) childAt).setSelection(((EditText) childAt).getText().toString().length());
                }
            }
        }
    }

    private EditText a(String str) {
        EditText editText = (EditText) View.inflate(getContext(), R.layout.editor_text, null);
        editText.setOnKeyListener(this.b);
        int i = this.a;
        this.a = i + 1;
        editText.setTag(Integer.valueOf(i));
        editText.setId(this.a);
        editText.setHint(str);
        editText.setGravity(131);
        editText.setOnFocusChangeListener(this);
        return editText;
    }

    private a b() {
        int i = this.a;
        this.a = i + 1;
        Integer valueOf = Integer.valueOf(i);
        a aVar = new a(getContext());
        aVar.setTag(valueOf);
        aVar.setId(this.a);
        aVar.a(valueOf, this.c);
        return aVar;
    }

    public void a(a aVar) {
        String obj = this.d.getText().toString();
        int selectionStart = this.d.getSelectionStart();
        Object trim = obj.substring(0, selectionStart).trim();
        String trim2 = obj.substring(selectionStart).trim();
        int indexOfChild = indexOfChild(this.d);
        b.b("insertTale", new Object[]{"content:" + obj + "  cursor:" + selectionStart + "  pre:" + trim + "  next:" + trim2 + "  index:" + indexOfChild});
        if (obj.length() == 0 || trim.length() == 0) {
            a(indexOfChild, aVar);
        } else {
            if (getChildCount() - 1 == indexOfChild || trim2.length() > 0) {
                this.d.setText(trim);
                a(indexOfChild + 1, trim2);
            }
            a(indexOfChild + 1, aVar);
            this.d.setText(trim2);
            this.d.requestFocus();
            this.d.setSelection(trim2.length());
            b.b("insertTale", new Object[]{"  selection:" + trim.length() + "  editStr2:" + trim2});
        }
        a();
    }

    private void a(int i, a aVar) {
        final View b = b();
        b.a(aVar, b.getId(), new cn.xiaochuankeji.tieba.widget.rich.a.a(this) {
            final /* synthetic */ RichTextEditor b;
        });
        addView(b, i);
        if (this.d != null) {
            this.d.setHint("");
        }
    }

    public void a() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.d.getWindowToken(), 0);
        }
    }

    private void b(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            editText.requestFocus();
            editText.setSelection(editText.getText().toString().length());
            inputMethodManager.showSoftInput(editText, 0);
        }
    }

    private void a(int i, String str) {
        View a = a("");
        a.setText(str);
        this.d = a;
        setLayoutTransition(null);
        addView(a, i);
        setLayoutTransition(this.e);
    }

    private void c() {
        this.e = new LayoutTransition();
        setLayoutTransition(this.e);
        this.e.addTransitionListener(new TransitionListener(this) {
            final /* synthetic */ RichTextEditor a;

            {
                this.a = r1;
            }

            public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
            }

            public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                if (!layoutTransition.isRunning() && i != 1) {
                }
            }
        });
        this.e.setDuration(120);
    }

    @NonNull
    public ArrayList<a> getTale() {
        ArrayList<a> arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof DelegateEditText) {
                EditText editText = (EditText) childAt;
                a aVar = new a();
                aVar.b = editText.getText().toString();
                aVar.a = "txt";
                if (i != childCount - 1) {
                    arrayList.add(aVar);
                } else if (!TextUtils.isEmpty(aVar.b)) {
                    arrayList.add(aVar);
                }
            } else if (childAt instanceof a) {
                arrayList.add(((a) childAt).getTale());
            }
        }
        return arrayList;
    }

    public void onFocusChange(View view, boolean z) {
        if (z && (view instanceof EditText)) {
            this.d = (EditText) view;
        }
    }
}
