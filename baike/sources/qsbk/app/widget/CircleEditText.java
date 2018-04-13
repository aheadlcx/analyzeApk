package qsbk.app.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.utils.LogUtil;

public class CircleEditText extends QiushiEmotionEditText {
    private ArrayList<CircleText> a = new ArrayList();
    private final TextWatcher b = new aq(this);
    private boolean c;
    private boolean d;
    private StringBuilder e;

    public static class CircleText implements Serializable {
        public int index;
        public CharSequence text;
    }

    public class MyArrowKeyMovementMethod extends ArrowKeyMovementMethod {
        final /* synthetic */ CircleEditText a;

        public MyArrowKeyMovementMethod(CircleEditText circleEditText) {
            this.a = circleEditText;
        }

        protected boolean left(TextView textView, Spannable spannable) {
            this.a.c = true;
            boolean left = super.left(textView, spannable);
            this.a.c = false;
            return left;
        }

        protected boolean right(TextView textView, Spannable spannable) {
            this.a.d = true;
            boolean right = super.right(textView, spannable);
            this.a.d = false;
            return right;
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new ar();
        private ArrayList<CircleText> a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            int readInt = parcel.readInt();
            this.a = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.a.add((CircleText) parcel.readSerializable());
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a.size());
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                parcel.writeSerializable((CircleText) it.next());
            }
        }
    }

    public CircleEditText(Context context) {
        super(context);
        a();
    }

    public CircleEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CircleEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void start() {
        this.e = new StringBuilder();
    }

    public void end() {
    }

    public boolean checked(String str) {
        CircleText firstText = getFirstText();
        String obj = getText().toString();
        CharSequence charSequence = firstText != null ? firstText.text.toString() : "";
        this.e.append(String.format("%s : text=%s & topic=%s\n", new Object[]{str, obj, charSequence}));
        if (charSequence.length() == 0 || obj.contains(charSequence)) {
            return true;
        }
        this.e.append("ERROR!!!\n");
        return false;
    }

    public String getLog() {
        return this.e.toString();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.a;
        checked("saveState");
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.a = savedState.a;
            checked("restoreState");
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    private void a() {
        super.addTextChangedListener(this.b);
    }

    public void insertTextBinding(CircleText circleText) {
        replaceOrInsertCircleText(getSelectionStart(), getSelectionEnd(), circleText);
    }

    public void replaceOrInsertCircleText(int i, int i2, CircleText circleText) {
        int length;
        circleText.index = i;
        if (i == i2) {
            getText().insert(i, circleText.text);
        } else {
            length = getText().length();
            getText().replace(Math.max(0, i), Math.min(length, i2), circleText.text);
        }
        int size = this.a.size();
        length = 0;
        while (length < this.a.size()) {
            if (((CircleText) this.a.get(length)).index > i) {
                break;
            }
            length++;
        }
        length = size;
        this.a.add(length, circleText);
    }

    public CircleText getFirstText() {
        return this.a.size() == 0 ? null : (CircleText) this.a.get(0);
    }

    public ArrayList<CircleText> getCircleTexts() {
        return this.a;
    }

    protected MovementMethod getDefaultMovementMethod() {
        return new MyArrowKeyMovementMethod(this);
    }

    protected void onSelectionChanged(int i, int i2) {
        LogUtil.e("on selection changed");
        if (this.a != null) {
            Object obj = i == i2 ? 1 : null;
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                CircleText circleText = (CircleText) it.next();
                int i3 = circleText.index;
                int length = circleText.text.length() + circleText.index;
                if (i3 < i && i < length) {
                    if (this.c) {
                        length = i3;
                    } else if (!this.d && i < ((i3 + length) + 1) / 2) {
                        length = i3;
                    }
                    if (obj != null) {
                        Selection.setSelection(getText(), length);
                        return;
                    } else {
                        Selection.setSelection(getText(), length, i2);
                        return;
                    }
                } else if (i3 < i2 && i2 < length) {
                    if (!this.c) {
                        if (this.d) {
                            i3 = length;
                        } else if (i >= ((i3 + length) + 1) / 2) {
                            i3 = length;
                        }
                    }
                    if (obj != null) {
                        Selection.setSelection(getText(), i3);
                        return;
                    } else {
                        Selection.setSelection(getText(), i, i3);
                        return;
                    }
                }
            }
            super.onSelectionChanged(i, i2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return onTouchEvent;
    }
}
