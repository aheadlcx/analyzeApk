package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import qsbk.app.video.VideoRecordActivity.VideoSnippet;

public class VideoRecordProgressBar extends View {
    private boolean a = false;
    private ArrayList<VideoSnippet> b = null;
    private int c;
    private Paint d;

    public VideoRecordProgressBar(Context context) {
        super(context);
        a();
    }

    public VideoRecordProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public VideoRecordProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.d = new Paint();
        this.d.setAntiAlias(true);
    }

    public boolean isSelectedLast() {
        return this.a;
    }

    public void setSelectedLast(boolean z) {
        if (this.a != z) {
            this.a = z;
            invalidate();
        }
    }

    public void setVideoSnippets(ArrayList<VideoSnippet> arrayList) {
        this.b = arrayList;
        update();
    }

    public void setCurrentSnippetTime(int i) {
        this.c = (getWidth() * i) / 300000;
        invalidate();
    }

    public void update() {
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int size = this.b == null ? 0 : this.b.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int width = (((VideoSnippet) this.b.get(i3)).endTime * getWidth()) / 300000;
            if (i3 == size - 1 && this.a) {
                this.d.setColor(-1882551);
            } else {
                this.d.setColor(-155857);
            }
            canvas.drawRect((float) i2, 0.0f, (float) width, (float) getHeight(), this.d);
            i2 = width + 1;
        }
        if (this.c <= i2 || this.a) {
            i = i2;
        } else {
            this.d.setColor(-155857);
            canvas.drawRect((float) i2, 0.0f, (float) this.c, (float) getHeight(), this.d);
            i = this.c + 1;
        }
        this.d.setColor(-1);
        canvas.drawRect((float) (i - 1), 0.0f, (float) (i + 1), (float) getHeight(), this.d);
    }
}
