package cn.xiaochuankeji.tieba.ui.widget.customtv;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import c.a.i.w;

public class a extends w {
    protected boolean a = false;
    private MotionEvent b;
    private MotionEvent c;
    private boolean d;
    private final Handler e = new b(this);
    private c f;

    public interface c {
        void a();
    }

    private class a {
        ClickableSpan a;
        TextView b;
        final /* synthetic */ a c;

        private a(a aVar) {
            this.c = aVar;
        }
    }

    private class b extends Handler {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 3:
                    a aVar = (a) message.obj;
                    aVar.a.onClick(aVar.b);
                    com.izuiyou.a.a.b.e("ClickableSpanTextView中调用onClick");
                    return;
                default:
                    throw new RuntimeException("Unknown message " + message);
            }
        }
    }

    public a(Context context) {
        super(context);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        CharSequence text = getText();
        if (text == null || text.toString().equals("")) {
            return super.onTouchEvent(motionEvent);
        }
        if (!(text instanceof SpannableString) && !(text instanceof SpannedString)) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 1 || action == 0) {
            ClickableSpan[] clickableSpanArr;
            int x = (((int) motionEvent.getX()) - getTotalPaddingLeft()) + getScrollX();
            int y = (((int) motionEvent.getY()) - getTotalPaddingTop()) + getScrollY();
            Layout layout = getLayout();
            x = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            if (text instanceof SpannableString) {
                clickableSpanArr = (ClickableSpan[]) ((SpannableString) text).getSpans(x, x, ClickableSpan.class);
            } else {
                clickableSpanArr = (ClickableSpan[]) ((SpannedString) text).getSpans(x, x, ClickableSpan.class);
            }
            if (clickableSpanArr.length != 0) {
                if (action == 0) {
                    if (this.b == null || this.c == null || !a(this.b, this.c, motionEvent)) {
                        if (this.b != null) {
                            this.b.recycle();
                        }
                        this.d = false;
                        this.b = MotionEvent.obtain(motionEvent);
                    } else {
                        this.e.removeMessages(3);
                        this.d = true;
                        this.b.recycle();
                        this.b = null;
                        this.c.recycle();
                        this.c = null;
                        if (this.f != null) {
                            com.izuiyou.a.a.b.e("ClickableSpanTextView中的双击事件");
                            this.f.a();
                        }
                        return true;
                    }
                } else if (action == 1) {
                    if (this.d) {
                        return true;
                    }
                    if (motionEvent.getEventTime() - this.b.getEventTime() < 500 && !this.e.hasMessages(3)) {
                        a aVar = new a();
                        aVar.a = clickableSpanArr[0];
                        aVar.b = this;
                        Message message = new Message();
                        message.what = 3;
                        message.obj = aVar;
                        this.e.sendMessageDelayed(message, 300);
                    }
                    if (this.c != null) {
                        this.c.recycle();
                    }
                    this.c = MotionEvent.obtain(motionEvent);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
        long eventTime = motionEvent3.getEventTime() - motionEvent2.getEventTime();
        if (eventTime > 300 || eventTime < 40) {
            return false;
        }
        int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
        int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
        if ((x * x) + (y * y) < 10000) {
            return true;
        }
        return false;
    }

    public void setOnDoubleTapListener(c cVar) {
        this.f = cVar;
    }
}
