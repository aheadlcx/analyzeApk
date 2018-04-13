package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.budejie.www.R;

public class SideBar extends View {
    public static String[] a = new String[]{"↑", "*", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private a b;
    private int c = -1;
    private Paint d = new Paint();
    private TextView e;

    public interface a {
        void a(String str);
    }

    public void setTextView(TextView textView) {
        this.e = textView;
    }

    public SideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / a.length;
        for (height = 0; height < a.length; height++) {
            this.d.setColor(Color.rgb(33, 65, 98));
            this.d.setTypeface(Typeface.DEFAULT_BOLD);
            this.d.setAntiAlias(true);
            this.d.setTextSize(20.0f);
            if (height == this.c) {
                this.d.setColor(Color.parseColor("#3399ff"));
                this.d.setFakeBoldText(true);
            }
            canvas.drawText(a[height], ((float) (width / 2)) - (this.d.measureText(a[height]) / 2.0f), (float) ((length * height) + length), this.d);
            this.d.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.c;
        a aVar = this.b;
        int height = (int) ((y / ((float) getHeight())) * ((float) a.length));
        switch (action) {
            case 1:
                setBackgroundDrawable(new ColorDrawable(0));
                this.c = -1;
                invalidate();
                if (this.e != null) {
                    this.e.setVisibility(4);
                    break;
                }
                break;
            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if (i != height && height >= 0 && height < a.length) {
                    if (aVar != null) {
                        aVar.a(a[height]);
                    }
                    if (this.e != null) {
                        this.e.setText(a[height]);
                        this.e.setVisibility(0);
                    }
                    this.c = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(a aVar) {
        this.b = aVar;
    }
}
